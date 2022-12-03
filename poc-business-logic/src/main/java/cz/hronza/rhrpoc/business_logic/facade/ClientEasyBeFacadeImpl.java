package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.service.ClientEasyBe;
import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientEasyBeFacadeImpl implements ClientEasyBeFacade {

    private final ClientEasyBe clientEasyBe;

    public ClientEasyBeFacadeImpl(ClientEasyBe clientEasyBe) {
        this.clientEasyBe = clientEasyBe;
    }


    @Override
    public ResponseEntity<OutputDto> reverseEndpointFromEasyBe(String id, String name) {
        return clientEasyBe.reverseEndpointFromEasyBe(id, name);
    }
}
