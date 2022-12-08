package nl.hu.eindopdracht1.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "accountservice")
public class ConfigUri {
    private String uri = "http://load-balancer-eindopdracht-1-199010695.us-east-1.elb.amazonaws.com/users/";

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
