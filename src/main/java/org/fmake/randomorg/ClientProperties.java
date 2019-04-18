package org.fmake.randomorg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "randomorg")
class ClientProperties {
    private String baseUri;
    private String endpoint;
    private String apiKey;
}
