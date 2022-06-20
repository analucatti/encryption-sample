package com.analucatti.encryption.service;

import com.analucatti.encryption.config.JweConfig;
import com.analucatti.encryption.dto.Data;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EncryptionImpl implements Encryption {
    private final JweConfig jweConfig;

    @Override
    public Data encrypt(Data data) throws NoSuchAlgorithmException, JOSEException {
        return new Data(jweObject(data.getData(), jweConfig).serialize());
    }

    private static JWEObject jweObject(String request, JweConfig jweConfig) throws NoSuchAlgorithmException, JOSEException {
        final JWEObject jweObject = new JWEObject(jweConfig.setHeader(), new Payload(request));
        jweObject.encrypt(jweConfig.aesEncrypter());
        return jweObject;
    }
}
