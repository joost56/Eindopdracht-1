package nl.hu.eindopdracht1;

import io.cucumber.plugin.event.TestCase;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.web.dto.AssignUserToTaskDto;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.verify.VerificationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
class MockServerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ClientAndServer mockServer;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void whenDummyAPIisCalledSalaryIsReturned() {
        mockServer = startClientAndServer(port);
        final MockServerClient mockServerClient = new MockServerClient("localhost", port);

        // Given a mock response from the API
        mockServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/users/joost"),
                        exactly(1) // Serve only once
                ).respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody("true")
                                .withDelay(TimeUnit.SECONDS, 1) // time for response to come back
                );

        // When we query our dummy api
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AssignUserToTaskDto> requestUpdate = new HttpEntity<>(new AssignUserToTaskDto("joost", 1L), httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/boards/tasks/assign", HttpMethod.PUT, requestUpdate, Void.class);

        // Then the correct salary is returned
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        // Then verify the mock request
        mockServerClient.verify(
                request()
                        .withMethod("GET")
                        .withPath("/users/joost"),
                VerificationTimes.exactly(1)
        );
        // Then get and assert the mock request
        final HttpRequest[] httpRequests = mockServerClient.retrieveRecordedRequests(request());
        assertThat(httpRequests.length).isEqualTo(1);
        final HttpRequest httpRequest = httpRequests[0];
        assertThat(httpRequest.getMethod()).isEqualTo("GET");
        assertThat(httpRequest.getPath()).isEqualTo("/users/joost");

        mockServer.stop();
    }
}
