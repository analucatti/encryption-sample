package com.analucatti.encryption.service;

import com.analucatti.encryption.dto.Data;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.springframework.stereotype.Service;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;

@Service
public class SecretServiceImpl implements SecretService {
    @Override
    public Data createSecret() throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        // Generate key pair public and private
        ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDH", new BouncyCastleProvider());
        keyPairGenerator.initialize(parameterSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Generate secret with created key pair
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", new BouncyCastleProvider());
        keyAgreement.init(keyPair.getPrivate());
        keyAgreement.doPhase(keyPair.getPublic(), true);
        SecretKey secretKey = keyAgreement.generateSecret("AES");
        return new Data(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }
}
