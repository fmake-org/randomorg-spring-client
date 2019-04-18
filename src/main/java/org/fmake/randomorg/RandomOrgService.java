package org.fmake.randomorg;

import lombok.RequiredArgsConstructor;
import org.fmake.randomorg.model.api.RandomOrgResult;
import org.fmake.randomorg.model.randomorg.RandomOrgRequest;
import org.fmake.randomorg.model.randomorg.RandomOrgResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.fmake.randomorg.model.randomorg.RandomOrgMethod.*;

@Service
@RequiredArgsConstructor
public class RandomOrgService {

    private static final String API_VERSION = "2.0";
    private final RestTemplate restTemplate;
    private final ClientProperties clientProperties;

    public RandomOrgResult<Integer> generateIntegers(int n, int min, int max) {
        RandomOrgResponse<Integer> responseTemplate = RandomOrgResponse.<Integer>builder().build();
        RandomOrgResponse<Integer> response = restTemplate.postForObject(
                clientProperties.getEndpoint(),
                RandomOrgRequest.builder()
                        .apiVersion(API_VERSION)
                        .method(GENERATE_INTEGERS)
                        .param("n", n)
                        .param("min", min)
                        .param("max", max)
                        .build(),
                responseTemplate.getClass()
        );
        return RandomOrgResult.<Integer>builder()
                .data(response.getResult().getRandom().getData())
                .build();
    }
}
