package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.service.ClientEasyBe;
import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientEasyBeFacadeImpl implements ClientEasyBeFacade {

    private final ClientEasyBe clientEasyBe;

    public ClientEasyBeFacadeImpl(@Qualifier("clientEasyBeImpl") ClientEasyBe clientEasyBe) {
        this.clientEasyBe = clientEasyBe;
    }


    @Override
    public ResponseEntity<ReverseStringsOutput> reverseEndpointFromEasyBe(String string01, String string02) {
        return clientEasyBe.reverseEndpointFromEasyBe(string01, string02);
    }
}
