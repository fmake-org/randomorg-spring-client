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
public class ResultObject<T> {
    @JsonProperty("random")
    private RandomObject<T> random;
    @JsonProperty("bitsUsed")
    private int bitsUsed;
    @JsonProperty("bitsLeft")
    private int bitsLeft;
    @JsonProperty("requestsLeft")
    private int requestsLeft;
    @JsonProperty("advisoryDelay")
    private int advisoryDelay;
}
