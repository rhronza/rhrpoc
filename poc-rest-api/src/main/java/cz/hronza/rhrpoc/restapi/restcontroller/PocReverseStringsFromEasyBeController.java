package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.facade.ClientEasyBeFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApiReverseEndpointFromEasyBe;
import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PocReverseStringsFromEasyBeController implements PocRestApiReverseEndpointFromEasyBe {

    private final ClientEasyBeFacade clientEasyBeFacade;

    private final Logger log = LoggerFactory.getLogger(PocReverseStringsFromEasyBeController.class);

    public PocReverseStringsFromEasyBeController(ClientEasyBeFacade clientEasyBeFacade) {
        this.clientEasyBeFacade = clientEasyBeFacade;
    }

    @Override
    public ResponseEntity<ReverseStringsOutput> reverseStringsFromEasyBe(String string01, String string02) {
        log.info("strings before reversing, string01={}, string02 ={}", string01, string02);
        ResponseEntity<ReverseStringsOutput> outputDtoResponseEntity = clientEasyBeFacade.reverseEndpointFromEasyBe(string01, string02);
        log.info("strings after reversing {}", outputDtoResponseEntity.getBody());
        return outputDtoResponseEntity;
    }
}
