package org.fmake.randomorg.model.randomorg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RandomOrgRequest {
    @JsonProperty("jsonrpc")
    private String apiVersion;
    @JsonProperty("method")
    private RandomOrgMethod method;
    @Singular
    @JsonProperty("params")
    private Map<String, Object> params;
    @JsonProperty("id")
    private int id;
}
