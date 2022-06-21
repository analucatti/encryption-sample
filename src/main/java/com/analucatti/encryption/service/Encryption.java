package com.analucatti.encryption.service;

import com.analucatti.encryption.dto.Data;
import com.nimbusds.jose.JOSEException;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface Encryption {
    Data encrypt(Data data) throws NoSuchAlgorithmException, JOSEException;
    Data decrypt(Data data) throws NoSuchAlgorithmException, JOSEException, ParseException;
}
