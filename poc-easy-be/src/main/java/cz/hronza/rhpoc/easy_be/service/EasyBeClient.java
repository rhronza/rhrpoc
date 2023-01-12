package cz.hronza.rhpoc.easy_be.service;

import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.http.ResponseEntity;

/**
 * Restt Client for rhrpoc-easy-be.
 */
public interface EasyBeClient {
    /**
     * Obtain output from rhrpoc-easy-be endpoint: /reverse-endpont
     *
     * @param string01  first string fro reversing
     * @param string02  second string fro reversing
     * @return   object containing 2 reversed strings
     */
    ResponseEntity<ReverseStringsOutput> getReverseEndpoint(String string01, String string02);
}
