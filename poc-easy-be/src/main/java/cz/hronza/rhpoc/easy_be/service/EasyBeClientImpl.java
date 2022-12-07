package cz.hronza.rhpoc.easy_be.service;

import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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

    private static final String END_POINT_NAME = "/reverse-endpoint";

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
    public ResponseEntity<OutputDto> getReverseEndpoint(String id, String name) {
        return restTemplate
                .exchange(initUrlHost(id, name),
                        HttpMethod.GET,
                        new HttpEntity<>("body", initHttpHeaders()),
                        new ParameterizedTypeReference<OutputDto>() {
                        });

    }

    private URI initUrlHost(String id, String name) {
        return UriComponentsBuilder
                .fromHttpUrl(hostName)
                .path(END_POINT_NAME)
                .queryParam("id", id)
                .queryParam("name", name)
                .build()
                .toUri();
    }

    private HttpHeaders initHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("authorization", String.format("Bearer %s", token));

        String correlationId = MDC.get("correlationId"); // tohle nic nevrací!!
        correlationId = getRandomUUID(); // takže si to beru random UUID

        log.info("correlationId={}", correlationId);
        httpHeaders.add("X-Correlation-Id", correlationId);
        return httpHeaders;

    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
