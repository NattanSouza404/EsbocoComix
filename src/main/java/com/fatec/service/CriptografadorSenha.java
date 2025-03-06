package com.fatec.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class CriptografadorSenha {
    
    private static final int ITERACOES = 10000;
    private static final int TAMANHO_HASH_EM_BITS = 256;
    private static final String ALGORITMO = "PBKDF2WithHmacSHA512";

    public static String hashSenha(String senha, String salt) throws Exception {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(
                senha.toCharArray(), salt.getBytes(), 
                ITERACOES, TAMANHO_HASH_EM_BITS
            );
            
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITMO);
            byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
            
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception("Erro ao gerar hash: " + e.getMessage(), e);
        }
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

}
