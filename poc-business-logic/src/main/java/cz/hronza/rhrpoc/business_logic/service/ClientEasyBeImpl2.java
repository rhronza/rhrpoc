package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientEasyBeImpl2 implements ClientEasyBe {
    @Override
    public ResponseEntity<ReverseStringsOutput> reverseEndpointFromEasyBe(String string01, String string02) {
        return ResponseEntity.ok(new ReverseStringsOutput("o", "p"));
    }
}
