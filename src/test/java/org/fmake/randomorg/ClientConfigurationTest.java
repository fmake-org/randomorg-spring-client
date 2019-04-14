package org.fmake.randomorg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
class ClientConfigurationTest {

    private static final String BASE_URI = "https://api.random.org";
    private static final String ENDPOINT = "/json-rpc/2/invoke";

    @Autowired
    ClientProperties clientProperties;

    @Test
    void clientProperties() {
        assertAll(
                () -> assertEquals(BASE_URI, clientProperties.getBaseUri()),
                () -> assertEquals(ENDPOINT, clientProperties.getEndpoint()),
                () -> assertNotNull(clientProperties.getApiKey())
        );
    }
}