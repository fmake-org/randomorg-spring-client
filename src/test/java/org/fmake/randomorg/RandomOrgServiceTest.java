package org.fmake.randomorg;

import org.fmake.randomorg.model.api.RandomOrgResult;
import org.fmake.randomorg.model.randomorg.RandomObject;
import org.fmake.randomorg.model.randomorg.RandomOrgRequest;
import org.fmake.randomorg.model.randomorg.RandomOrgResponse;
import org.fmake.randomorg.model.randomorg.ResultObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = ClientConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
class RandomOrgServiceTest {

    private final List<Integer> TEST_DATA_INTEGERS = List.of(1,2,3,4);
    private final List<List<Integer>> TEST_DATA_INTEGER_SEQUENCES = List.of(List.of(1,2,3,4), List.of(5,6,7,8));
    private final List<Double> TEST_DATA_DECIMAL_FRACTIONS = List.of(1.1,2.2,3.3,4.4);
    private final List<Integer> TEST_DATA_GAUSSIANS = List.of(1,2,3,4);
    private final List<String> TEST_DATA_STRINGS = List.of("aaa","bbb","ccc","ddd");
    private final List<UUID> TEST_DATA_UUIDS = List.of(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID());
    private final List<byte[]> TEST_DATA_BLOBS = List.of("aaa".getBytes(),"bbb".getBytes(),"ccc".getBytes());

    @Mock
    RestTemplate restTemplate;
    ClientProperties clientProperties = when(mock(ClientProperties.class).getEndpoint()).thenReturn("/endpoint").getMock();
    @InjectMocks
    RandomOrgService randomOrgService;

    private <T> void initMockResponseSuccess(List<T> data) {
        when(
                restTemplate.postForObject(
                        anyString(),
                        any(RandomOrgRequest.class),
                        ArgumentMatchers.any(Class.class)
                )
        )
        .thenReturn(
                RandomOrgResponse.<T>builder()
                .apiVersion("2.0")
                .id(666)
                .result(
                        ResultObject.<T>builder()
                            .advisoryDelay(100)
                            .bitsUsed(1)
                            .bitsLeft(1)
                            .requestsLeft(1)
                            .random(
                                    RandomObject.<T>builder()
                                    .completionTime(ZonedDateTime.now())
                                    .data(data)
                                    .build()
                            )
                        .build()
                )
                .build()
        );
    }

    @Test
    void nothing() {
        assertTrue(true);
    }

    @Test
    void generateIntegersBasic() {
        initMockResponseSuccess(TEST_DATA_INTEGERS);
        assertNotNull(randomOrgService.generateIntegers(1, 0, 100));
    }

    @Test
    void generateIntegersResultDetails() {
        initMockResponseSuccess(TEST_DATA_INTEGERS);
        RandomOrgResult<Integer> result = randomOrgService.generateIntegers(1, 0, 100);
        assertEquals(4, result.getData().size());
        assertEquals(2, (int)result.getData().get(1));
    }


}