package com.analucatti.encryption.config;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.AESEncrypter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
@Configuration
public class JweConfig {

    private static final String DIGEST_ALGORITHM_SHA_256 = "SHA-256";
    @Value("${encryption.key}")
    private String key;
    @Value("${encryption.secret}")
    private String secret;

    public JWEHeader setHeader() {
        return new JWEHeader.Builder(JWEAlgorithm.A256GCMKW, EncryptionMethod.A256GCM)
                .keyID(key)
                .build();
    }

    public AESEncrypter encrypt() throws NoSuchAlgorithmException, KeyLengthException {
        return new AESEncrypter(sha256());
    }

    public AESDecrypter decrypt() throws NoSuchAlgorithmException, KeyLengthException {
        return new AESDecrypter(sha256());
    }

    private byte[] sha256() throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance(JweConfig.DIGEST_ALGORITHM_SHA_256);
        messageDigest.update(secret.getBytes(StandardCharsets.UTF_8));
        return messageDigest.digest();
    }

}
