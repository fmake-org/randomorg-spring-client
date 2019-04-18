package org.fmake.randomorg.model.randomorg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RandomOrgResponse<T> {
    @JsonProperty("jsonrpc")
    private String apiVersion;
    @JsonProperty("result")
    private ResultObject<T> result;
    @JsonProperty("id")
    private int id;
}
