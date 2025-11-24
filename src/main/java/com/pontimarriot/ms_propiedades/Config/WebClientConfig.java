// java
package com.pontimarriot.ms_propiedades.Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("propertiesWebClient")
    public WebClient availabilityWebClient(@Value("${external.properties.base-url:http://localhost:8081}") String baseUrl,
                                           ObjectMapper mapper) {
        // ensure Java Time module registered so LocalDate/LocalDateTime deserialize correctly
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(clientCodecs -> {
                    clientCodecs.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper));
                    clientCodecs.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper));
                })
                .build();

        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(strategies)
                .build();
    }
}
