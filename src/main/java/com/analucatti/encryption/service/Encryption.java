package com.analucatti.encryption.service;

import com.analucatti.encryption.dto.Data;
import com.nimbusds.jose.JOSEException;

import java.security.NoSuchAlgorithmException;

public interface Encryption {
    Data encrypt(Data data) throws NoSuchAlgorithmException, JOSEException;
}
