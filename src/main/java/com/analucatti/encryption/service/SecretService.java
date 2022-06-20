package com.analucatti.encryption.service;

import com.analucatti.encryption.dto.Data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface SecretService {
    Data createSecret() throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException;
}
