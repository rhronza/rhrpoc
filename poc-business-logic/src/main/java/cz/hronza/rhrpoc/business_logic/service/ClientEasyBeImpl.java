package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhpoc.easy_be.service.EasyBeClient;
import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ClientEasyBeImpl implements ClientEasyBe {
    private final EasyBeClient easyBeClient;

    public ClientEasyBeImpl(EasyBeClient easyBeClient) {
        this.easyBeClient = easyBeClient;
    }

    @Override
    public ResponseEntity<ReverseStringsOutput> reverseEndpointFromEasyBe(String string01, String string02) {
        return easyBeClient.getReverseEndpoint(string01, string02);
    }
}
