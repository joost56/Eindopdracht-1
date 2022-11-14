package nl.hu.eindopdracht1;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
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
        final var switchTaskBetweenColumnsUri = "http://localhost:" + 8081 + "/boards/columns/switch";
        final var taskUri = "http://localhost:" + 8081 + "/boards/tasks/1";
        final var columnUri1 = "http://localhost:" + 8081 + "/boards/columns/kolom1";
        final var columnUri2 = "http://localhost:" + 8081 + "/boards/columns/kolom2";
        final var switchRequest = new SwitchTaskDto("kolom1", "kolom2", 1L);

        //When
        HttpEntity<SwitchTaskDto> requestUpdate = new HttpEntity<>(switchRequest);
        var response = this.restTemplate.exchange(switchTaskBetweenColumnsUri, HttpMethod.PUT, requestUpdate, SwitchTaskDto.class);
        System.out.println(response);
        assertThat(response.hasBody()).isTrue();
        System.out.println(response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Then
        var task = this.restTemplate.getForEntity(taskUri, Task.class);
        var column1 = this.restTemplate.getForEntity(columnUri1, Column.class);
        var column2 = this.restTemplate.getForEntity(columnUri2, Column.class);
        assertThat(task.hasBody()).isTrue();

//        assertThat(task.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(task.getBody().getColumn().getId()).isEqualTo("kolom2");
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