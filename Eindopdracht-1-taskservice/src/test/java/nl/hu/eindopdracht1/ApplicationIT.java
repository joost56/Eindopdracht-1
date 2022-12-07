package nl.hu.eindopdracht1;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import nl.hu.eindopdracht1.web.dto.CreateTaskDto;
import nl.hu.eindopdracht1.web.dto.SwitchTaskDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.assertj.core.api.Assertions.assertThat;

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
        final var columnUri = "http://localhost:" + port + "/boards/columns";
        final var columnRequest = new CreateColumnDto("kolom1");
        final var taskRequest = new CreateTaskDto("kolom1", "Dit is een task");
        final var taskUri = "http://localhost:" + port + "/boards/tasks";
        final var taskGetUri = "http://localhost:" + port + "/boards/tasks/1";

        //When
        HttpEntity<CreateColumnDto> requestPostColumn = new HttpEntity<>(columnRequest);
        ResponseEntity<CreateColumnDto> responseColumn = this.restTemplate.exchange(columnUri, HttpMethod.POST, requestPostColumn, CreateColumnDto.class);
        assertThat(responseColumn.hasBody()).isTrue();
        assertThat(responseColumn.getStatusCode()).isEqualTo(HttpStatus.OK);

        HttpEntity<CreateTaskDto> requestPostTask = new HttpEntity<>(taskRequest);
        ResponseEntity<CreateTaskDto> responseTask = this.restTemplate.exchange(taskUri, HttpMethod.POST, requestPostTask, CreateTaskDto.class);
        assertThat(responseTask.hasBody()).isTrue();
        assertThat(responseTask.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Then
        var column = this.restTemplate.getForEntity(taskGetUri, Column.class);
        assertThat(column.hasBody()).isTrue();
        System.out.println(column.hasBody());
        assertThat(column.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(column.getBody().getTasks().contains(responseTask));
    }

    @Test
    void whenTaskIsSwitchedBetweenColumnsThenTaskHasCorrectColumn() {
        //Given
        final var columnRequest = new CreateColumnDto("kolom1");
        final var columnRequest2 = new CreateColumnDto("kolom2");
        final var columnUri = "http://localhost:" + 8081 + "/boards/columns";
        final var taskRequest = new CreateTaskDto("kolom1", "Dit is een task");
        final var taskUri = "http://localhost:" + 8081 + "/boards/tasks";
        final var switchRequest = new SwitchTaskDto("kolom1", "kolom2", 1L);
        final var switchUri = "http://localhost:" + 8081 + "/boards/columns/switch";
        final var getColumnUri1 = "http://localhost:" + 8081 + "/boards/columns/kolom1";
        final var getColumnUri2 = "http://localhost:" + 8081 + "/boards/columns/kolom2";

        //When
        HttpEntity<CreateColumnDto> requestPostColumn = new HttpEntity<>(columnRequest);
        ResponseEntity<CreateColumnDto> responseColumn = this.restTemplate.exchange(columnUri, HttpMethod.POST, requestPostColumn, CreateColumnDto.class);
        HttpEntity<CreateColumnDto> requestPostColumn2 = new HttpEntity<>(columnRequest2);
        ResponseEntity<CreateColumnDto> responseColumn2 = this.restTemplate.exchange(columnUri, HttpMethod.POST, requestPostColumn2, CreateColumnDto.class);
        assertThat(responseColumn.hasBody()).isTrue();
        assertThat(responseColumn2.hasBody()).isTrue();
        assertThat(responseColumn.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseColumn2.getStatusCode()).isEqualTo(HttpStatus.OK);

        HttpEntity<CreateTaskDto> requestPostTask = new HttpEntity<>(taskRequest);
        ResponseEntity<CreateTaskDto> responseTask = this.restTemplate.exchange(taskUri, HttpMethod.POST, requestPostTask, CreateTaskDto.class);
        assertThat(responseTask.hasBody()).isTrue();
        assertThat(responseTask.getStatusCode()).isEqualTo(HttpStatus.OK);

        HttpEntity<SwitchTaskDto> requestSwitchTask = new HttpEntity<>(switchRequest);
        ResponseEntity<SwitchTaskDto> responseSwitch = this.restTemplate.exchange(switchUri, HttpMethod.PUT, requestSwitchTask, SwitchTaskDto.class);
        assertThat(responseSwitch.hasBody()).isTrue();
        assertThat(responseSwitch.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Then
        var column1 = this.restTemplate.getForEntity(getColumnUri1, Column.class);
        var column2 = this.restTemplate.getForEntity(getColumnUri2, Column.class);
        assertThat(column1.hasBody()).isTrue();
        assertThat(column1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(column2.hasBody()).isTrue();
        assertThat(column2.getStatusCode()).isEqualTo(HttpStatus.OK);
        Task task2 = column2.getBody().getTasks().get(0);
        assertThat(task2.getId() == 1);
    }
}