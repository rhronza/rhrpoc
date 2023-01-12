package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ClientEasyBeFacade {
    ResponseEntity<ReverseStringsOutput> reverseEndpointFromEasyBe(String string01, String string02);
}
