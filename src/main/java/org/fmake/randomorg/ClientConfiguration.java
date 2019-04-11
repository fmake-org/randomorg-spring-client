package org.fmake.randomorg;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ClientProperties.class)
public class ClientConfiguration {

    @Bean
    RestTemplate restTemplate(ClientProperties properties) {
        return new RestTemplateBuilder()
                .rootUri(properties.getBaseUri())
                .build();
    }
}
