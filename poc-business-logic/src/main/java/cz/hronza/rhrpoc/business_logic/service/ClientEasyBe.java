package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import org.springframework.http.ResponseEntity;

public interface ClientEasyBe {

    ResponseEntity<OutputDto> reverseEndpointFromEasyBe(String id, String name);
}
