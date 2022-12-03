package cz.hronza.rhpoc.easy_be.service;

import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import org.springframework.http.ResponseEntity;

/**
 * Restt Client for rhrpoc-easy-be.
 */
public interface EasyBeClient {
    /**
     * Obtain output from rhrpoc-easy-be endpoint: /reverse-endpont
     * 
     * @param id
     * @param name
     * @return
     */
    ResponseEntity<OutputDto> getReverseEndpoint(String id, String name);
}
