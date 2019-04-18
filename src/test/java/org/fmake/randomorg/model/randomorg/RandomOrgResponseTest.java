package org.fmake.randomorg.model.randomorg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomOrgResponseTest {

    private static final String API_VERSION = "2.0";
    private static final int ID = 666;
    private static final int ADVISORY_DELAY = 10;
    private static final int BITS_LEFT = 11;
    private static final int BITS_USED = 12;
    private static final int REQUESTS_LEFT = 1;
    private static final List<Integer> DATA = List.of(1, 2, 3, 4);
    private static final ZonedDateTime COMPLETION_TIME = ZonedDateTime
            .parse(
                    "2019-04-17 14:48:13+0000",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")
            )
            .withZoneSameInstant(ZoneId.of("UTC"));

    private static final String JSON_VALUE = "{\"jsonrpc\":\"2.0\",\"result\":{\"random\":{\"data\":[1,2,3,4],\"completionTime\":\"2019-04-17 14:48:13+0000\"},\"bitsUsed\":12,\"bitsLeft\":11,\"requestsLeft\":1,\"advisoryDelay\":10},\"id\":666}";

    @Test
    void jsonMapping() throws IOException {
        RandomOrgResponse<Integer> originalResponse = RandomOrgResponse.<Integer>builder()
                .apiVersion(API_VERSION)
                .id(ID)
                .result(
                        ResultObject.<Integer>builder()
                                .advisoryDelay(ADVISORY_DELAY)
                                .bitsLeft(BITS_LEFT)
                                .bitsUsed(BITS_USED)
                                .requestsLeft(REQUESTS_LEFT)
                                .random(
                                        RandomObject.<Integer>builder()
                                                .completionTime(COMPLETION_TIME)
                                                .data(DATA)
                                                .build()
                                )
                                .build()
                )
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String valueAsString = mapper.writeValueAsString(originalResponse);
        assertEquals(JSON_VALUE, valueAsString);

        RandomOrgResponse<Integer> parsedResponse = mapper.readValue(valueAsString, RandomOrgResponse.class);
        assertEquals(API_VERSION, parsedResponse.getApiVersion());
        assertEquals(ID, parsedResponse.getId());
        assertEquals(ADVISORY_DELAY, parsedResponse.getResult().getAdvisoryDelay());
        assertEquals(BITS_LEFT, parsedResponse.getResult().getBitsLeft());
        assertEquals(BITS_USED, parsedResponse.getResult().getBitsUsed());
        assertEquals(REQUESTS_LEFT, parsedResponse.getResult().getRequestsLeft());
        assertEquals(COMPLETION_TIME, parsedResponse.getResult().getRandom().getCompletionTime());
        assertEquals(DATA.size(), parsedResponse.getResult().getRandom().getData().size());
        assertEquals(DATA.get(0), parsedResponse.getResult().getRandom().getData().get(0));
    }

}