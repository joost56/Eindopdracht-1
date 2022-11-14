package nl.hu.eindopdracht1;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import nl.hu.eindopdracht1.web.dto.CreateTaskDto;
import nl.hu.eindopdracht1.web.dto.SwitchTaskDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ApplicationIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private RestTemplate patchRestTemplate;

    @BeforeEach
    void configureApacheHttpComponents(){
        this.patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Test
    void whenTaskIsCreatedTaskIsAddedToColumn() {
        //Given
        final var columnUri = "http://localhost:" + 8080 + "/boards/columns";
        final var columnRequest = new CreateColumnDto("kolom1");
        final var taskRequest = new CreateTaskDto("kolom1", "Dit is een task");
        final var taskUri = "http://localhost:" + 8080 + "/boards/tasks";
        final var taskGetUri = "http://localhost:" + 8080 + "/boards/tasks/1";

        //When
        HttpEntity<CreateColumnDto> requestPostColumn = new HttpEntity<>(columnRequest);
        ResponseEntity<CreateColumnDto> responseColumn = this.restTemplate.exchange(columnUri, HttpMethod.POST, requestPostColumn, CreateColumnDto.class);
        assertThat(responseColumn.hasBody()).isTrue();
        assertThat(responseColumn.getStatusCode()).isEqualTo(HttpStatus.OK);

        HttpEntity<CreateTaskDto> requestPostTask = new HttpEntity<>(taskRequest);
        ResponseEntity<CreateTaskDto> responseTask = this.restTemplate.exchange(taskUri, HttpMethod.POST, requestPostTask, CreateTaskDto.class);
        assertThat(responseTask.hasBody()).isTrue();
        System.out.println(responseTask.getBody());
        assertThat(responseTask.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Then
        var column = this.restTemplate.getForEntity(taskGetUri, Column.class);
        assertThat(column.hasBody()).isTrue();
        System.out.println(column.hasBody());
        assertThat(column.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(column.getBody().getTasks().contains(responseTask));
    }

//    @Test
//    void assignTaskToNonExistingUser() throws IOException, InterruptedException {
//        //Given
//        User user = new User("username");
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        Task task = new Task("description", users);
//
//        //When
//        Mockito.when(userService.getUserById(null, "username")).thenReturn((java.net.http.HttpResponse<String>) HttpResponse.response("true"));
//        Throwable throwable = catchThrowable(() -> {
//            final List<User> userResult = userService.assignTaskToUserAndUserToTask(user.getUsername(), task.getId());
//        });
//
//        //Then
//        assertThat(throwable)
//                .isInstanceOf(TaskAlreadyAssignedToUser.class)
//                .hasMessageContaining("is already assigned to user");
//        Mockito.verify(userRepository, Mockito.times(0)).save(user);
//    }
}