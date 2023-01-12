package cz.hronza.rhpoc.easy_be.service;

import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static cz.hronza.rhpoc.easy_be.config.PocEasyBeConfiguration.EASY_BE_REST_TEMPLATE_NAME;

@Service
public class EasyBeClientImpl implements EasyBeClient {

    private final RestTemplate restTemplate;
    public final String hostName;
    public final String token;

    private static final String END_POINT = "/reverse-strings";

    private final Logger log = LoggerFactory.getLogger(EasyBeClientImpl.class);


    public EasyBeClientImpl(
            @Qualifier(EASY_BE_REST_TEMPLATE_NAME) RestTemplate restTemplate,
            @Value("${easy.be.host}") String host,
            @Value("${easy.be.token}") String token) {
        this.restTemplate = restTemplate;
        this.hostName = host;
        this.token = token;
    }

    @Override
    public ResponseEntity<ReverseStringsOutput> getReverseEndpoint(String string01, String string02) {
        return restTemplate
                .exchange(initUrlHost(string01, string02),
                        HttpMethod.GET,
                        new HttpEntity<>("body", initHttpHeaders()),
                        new ParameterizedTypeReference<>() {
                        });

    }

    private URI initUrlHost(String string01, String string02) {
        return UriComponentsBuilder
                .fromHttpUrl(hostName)
                .path(END_POINT)
                .queryParam("string01", string01)
                .queryParam("string02", string02)
                .build()
                .toUri();
    }

    private HttpHeaders initHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("authorization", String.format("Bearer %s", token));

        String correlationId;
        //correlationId = MDC.get("correlationId"); // tohle nic nevrací!
        correlationId = getRandomUUID(); // takže to beru z random UUID

        log.info("correlationId={}", correlationId);
        httpHeaders.add("X-Correlation-Id", correlationId);
        return httpHeaders;

    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
