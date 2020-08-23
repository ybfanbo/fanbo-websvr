package com.fanbo.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


public class AesUtil_Zshield {
    private final int keySize;
    private final int iterationCount;
    private final Cipher cipher;

    public AesUtil_Zshield(int keySize, int iterationCount) {
        this.keySize = keySize;
        this.iterationCount = iterationCount;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw fail(e);
        }
    }

    public static final String IV = "412a3773646661536977753e78783957";
    public static final String SALT = "4b4c354d784971736977753e7878395751575344464352545e33324d626e";
    public static final int KEY_SIZE = 128;
    public static final int ITERATION_COUNT = 10000;
    public static final String PASSPHRASE = "kl%$a()DFSD43023-fkdL";


//    public String encrypt(String plaintext) {
//        try {
//            SecretKey key = generateKey(SALT, PASSPHRASE);
//            byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, IV, plaintext.getBytes("UTF-8"));
//            return base64(encrypted);
//        }
//        catch (UnsupportedEncodingException e) {
//            throw fail(e);
//        }
//    }

//    public String decrypt(String ciphertext) {
//        try {
//            SecretKey key = generateKey(SALT, PASSPHRASE);
//            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, IV, base64(ciphertext));
//            return new String(decrypted, "UTF-8");
//        }
//        catch (UnsupportedEncodingException e) {
//            throw fail(e);
//        }
//    }

    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
        try {
            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(bytes);
        }
        catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            throw fail(e);
        }
    }

    private SecretKey generateKey(String salt, String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return key;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw fail(e);
        }
    }

//    public static String random(int length) {
//        byte[] salt = new byte[length];
//        new SecureRandom().nextBytes(salt);
//        return hex(salt);
//    }

//    public static String base64(byte[] bytes) {
//        return Base64.encodeBase64String(bytes);
//    }

//    public static byte[] base64(String str) {
//        return Base64.decodeBase64(str);
//    }

//    public static String hex(byte[] bytes) {
//        return Hex.encodeHexString(bytes);
//    }

    public static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }

    private IllegalStateException fail(Exception e) {
        return new IllegalStateException(e);
    }
}