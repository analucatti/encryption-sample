package com.analucatti.encryption.controller;

import com.analucatti.encryption.dto.Data;
import com.analucatti.encryption.service.Encryption;
import com.analucatti.encryption.service.SecretService;
import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RestController
@RequestMapping(path = "/data")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EncryptionController {
    private Encryption encryptionService;
    private SecretService secretService;

    @PostMapping(path = "/encrypt")
    public ResponseEntity<Data> encrypt(@RequestBody Data data) throws NoSuchAlgorithmException, JOSEException {
        return ResponseEntity.ok(encryptionService.encrypt(data));
    }

    @PostMapping(path = "/decrypt")
    public ResponseEntity<Data> decrypt(@RequestBody Data data) throws NoSuchAlgorithmException, JOSEException, ParseException {
        return ResponseEntity.ok(encryptionService.decrypt(data));
    }

    @GetMapping(path = "/secret")
    public ResponseEntity<Data> generateSharedSecret() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        return ResponseEntity.ok(secretService.createSecret());
    }
}
