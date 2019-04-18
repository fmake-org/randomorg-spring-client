package org.fmake.randomorg.model.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class RandomOrgResult<T> {
    @Singular("data") List<T> data;
    LocalDateTime completionTime;
    int bitsUsed;
    int bitsLeft;
    int requestLeft;
    int advisoryDelay;
}
