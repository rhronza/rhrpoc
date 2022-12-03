package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ClientEasyBeFacade {
   ResponseEntity<OutputDto> reverseEndpointFromEasyBe(String id, String name);
}
