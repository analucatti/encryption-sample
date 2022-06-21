package com.analucatti.encryption.service;

import com.analucatti.encryption.config.JweConfig;
import com.analucatti.encryption.dto.Data;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.EncryptedJWT;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EncryptionImpl implements Encryption {
    private final JweConfig jweConfig;

    @Override
    public Data encrypt(Data data) throws NoSuchAlgorithmException, JOSEException {
        return new Data(encryptJWE(data.getData(), jweConfig).serialize());
    }

    @Override
    public Data decrypt(Data data) throws NoSuchAlgorithmException, JOSEException, ParseException {
        return new Data(decryptJWE(data.getData(), jweConfig).getPayload().toString());
    }

    private static JWEObject encryptJWE(String request, JweConfig jweConfig) throws NoSuchAlgorithmException, JOSEException {
        final JWEObject jweObject = new JWEObject(jweConfig.setHeader(), new Payload(request));
        jweObject.encrypt(jweConfig.encrypt());
        return jweObject;
    }

    private static EncryptedJWT decryptJWE(String data, JweConfig jweConfig) throws ParseException, NoSuchAlgorithmException, JOSEException {
        final EncryptedJWT encryptedJWT = EncryptedJWT.parse(data);
        encryptedJWT.decrypt(jweConfig.decrypt());
        return encryptedJWT;
    }
}
