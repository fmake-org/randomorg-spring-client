package org.fmake.randomorg.model.randomorg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RandomOrgRequestTest {

    private static final String API_VERSION = "2.0";
    private static final RandomOrgMethod METHOD = RandomOrgMethod.GENERATE_INTEGERS;
    private static final int ID = 666;
    private static final String PARAM_1_KEY = "n";
    private static final int PARAM_1_VALUE = 10;
    private static final String PARAM_2_KEY = "min";
    private static final int PARAM_2_VALUE = 100;

    private static final String JSON_VALUE = "{\"jsonrpc\":\"2.0\",\"method\":\"generateIntegers\",\"params\":{\"n\":10,\"min\":100},\"id\":666}";

    @Test
    void jsonMapping() throws IOException {
        RandomOrgRequest originalRequest = RandomOrgRequest.builder()
                .apiVersion(API_VERSION)
                .method(METHOD)
                .id(ID)
                .param(PARAM_1_KEY, PARAM_1_VALUE)
                .param(PARAM_2_KEY, PARAM_2_VALUE)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String valueAsString = mapper.writeValueAsString(originalRequest);
        assertEquals(JSON_VALUE, valueAsString);

        RandomOrgRequest parsedRequest = mapper.readValue(valueAsString, RandomOrgRequest.class);
        assertEquals(ID, parsedRequest.getId());
        assertEquals(METHOD, parsedRequest.getMethod());
        assertEquals(API_VERSION, parsedRequest.getApiVersion());
        assertTrue(parsedRequest.getParams().containsKey(PARAM_1_KEY) && parsedRequest.getParams().containsKey(PARAM_2_KEY));
        assertEquals(PARAM_1_VALUE, parsedRequest.getParams().get(PARAM_1_KEY));
        assertEquals(PARAM_2_VALUE, parsedRequest.getParams().get(PARAM_2_KEY));
    }

}