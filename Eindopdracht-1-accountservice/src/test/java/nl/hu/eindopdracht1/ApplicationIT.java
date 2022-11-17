package nl.hu.eindopdracht1;

import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.web.dto.UserDto;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ApplicationIT {
    @Autowired
    private TestRestTemplate restTemplate;

    private RestTemplate patchRestTemplate;

    @BeforeEach
    void configureApacheHttpComponents(){
        this.patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

//    @Test
//    void whenUserIsCreatedThenUserIsAvailable() {
//        //Given
//        final var userUri = "http://localhost:" + 8080 + "/users/1";
//        final var registerUri = "http://localhost:" + 8080 + "/users/register";
//        final var userRequest = new UserDto("user", "pass");
//
//        //When
//        HttpEntity<UserDto> requestPost = new HttpEntity<>(userRequest);
//        ResponseEntity<UserDto> response = this.restTemplate.exchange(registerUri, HttpMethod.POST, requestPost, UserDto.class);
//        assertThat(response.hasBody()).isTrue();
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        //Then
//        var user = this.restTemplate.getForEntity(userUri, String.class);
//        assertThat(user.hasBody()).isTrue();
//        assertThat(user.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test
//    void whenUserIsCreatedThenLogInIsAvailable() {
//        //Given
//        final var userUri = "http://localhost:" + 8080 + "/users/login";
//        final var registerUri = "http://localhost:" + 8080 + "/users/register";
//        final var userRequest = new UserDto("user", "pass");
//
//        //When
//        HttpEntity<UserDto> requestPost = new HttpEntity<>(userRequest);
//        ResponseEntity<UserDto> response = this.restTemplate.exchange(registerUri, HttpMethod.POST, requestPost, UserDto.class);
//        assertThat(response.hasBody()).isTrue();
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        //Then
//        HttpEntity<UserDto> requestPostLogin = new HttpEntity<>(userRequest);
//        ResponseEntity<Boolean> response1 = this.restTemplate.exchange(userUri, HttpMethod.POST, requestPostLogin, Boolean.class);
//        assertThat(response1.hasBody()).isTrue();
//        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
//        response1.getBody();
//        assertThat(true);
//
//    }
}
