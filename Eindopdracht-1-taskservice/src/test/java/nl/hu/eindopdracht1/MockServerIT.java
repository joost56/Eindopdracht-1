package nl.hu.eindopdracht1;

import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class MockServerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ClientAndServer mockServer;

//    @Test
//    void testff() {
//        mockServer = startClientAndServer(9080);
//        final MockServerClient mockServerClient = new MockServerClient("localhost", 9080);
//
//        // Given a mock response from the API
//        mockServerClient
//                .when(
//                        request()
//                                .withMethod("GET")
//                                .withPath("/boards/columns/kolom1"),
//                        exactly(1) // Serve only once
//                ).respond(
//                        response()
//                                .withStatusCode(200)
//                                .withHeaders(
//                                        new Header("Content-Type", "application/json; charset=utf-8")
//                                )
//                                .withBody("{\n" +
//                                        "    \"id\": \"kolom1\",\n" +
//                                        "    \"tasks\": [\n" +
//                                        "        {\n" +
//                                        "            \"id\": 2,\n" +
//                                        "            \"description\": \"task2\",\n" +
//                                        "            \"users\": []\n" +
//                                        "        }\n" +
//                                        "    ]\n" +
//                                        "}")
//                                .withDelay(TimeUnit.SECONDS, 1) // time for response to come back
//                );
//
//        var response1 = this.restTemplate.getForObject("http://localhost:" + port + "/boards/tasks/" + 2L, Task.class);
//
//        // Then the correct salary is returned
//        assertThat(response1).isEqualTo("");
//        // Then verify the mock request
////        mockServerClient.verify(
////                request()
////                        .withMethod("GET")
////                        .withPath("/api/v1/employee/1"),
////                VerificationTimes.exactly(1)
////        );
//        mockServer.stop();
//
//    }

    @Test
    void testff() {


    }

}
