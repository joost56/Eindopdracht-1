package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.domain.config.ConfigUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HttpRequestService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getUserById(String uri, String userId) {
        String url = uri + userId;
        return this.restTemplate.getForEntity(url, String.class);
    }

    public boolean userExists (String username) {
        return Boolean.parseBoolean(getUserById("http://load-balancer-eindopdracht-1-199010695.us-east-1.elb.amazonaws.com/users/", username).getBody());
    }

}
