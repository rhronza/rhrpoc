package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.http.ResponseEntity;

public interface ClientEasyBe {

    ResponseEntity<ReverseStringsOutput> reverseEndpointFromEasyBe(String string01, String string02);
}
