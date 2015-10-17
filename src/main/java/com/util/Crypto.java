package com.util;

import java.util.*;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for crypto logic used for password encrypt & decrypt
 */
public class Crypto {
    private static String ivString = "15iPh8NcRs";
    private static String keyString = "CMPE272_Group8";
    private static byte[] ivBytes = ivString.getBytes();
    private static byte[] keyBytes = keyString.getBytes();
    /**
     * General purpose encryption method
     *
     * @param  message  The text to be encrypted
     * @return          Base64-encoded string
     */
    public static String encrypt(String message) {
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            byte[] stringBytes = message.getBytes(Charset.forName("UTF-8"));
            byte[] raw = cipher.doFinal(stringBytes);
            result = new String(Base64.getEncoder().encodeToString(raw));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * General purpose decryption method
     *
     * @param encrypted Encrypted text to be decrypted
     * @return String
     */
    public static String decrypt(String encrypted) {
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            byte[] raw = Base64.getDecoder().decode(encrypted.getBytes());
            byte[] stringBytes = cipher.doFinal(raw);
            result = new String(stringBytes, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Encrypts and URL-encodes a string so that the encrypted text can be passed as a query arg
     *
     * @param message The text to be encrypted/encoded
     * @return
     */
    public static String encryptAndURLEncode(String message) {
        // encrypt the string
        String result = encrypt(message);

        try {
            // URLencode the base64 string so it can be transferred as a query arg
            result = URLEncoder.encode(result, "UTF-8");
            // we need a 2nd encoding because the browser automatically does a decode
            result = URLEncoder.encode(result, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Decrypts & URL-decodes a string created by encryptAndURLEncode()
     *
     * @param message
     * @return
     */
    public static String decryptAndURLDecode(String message) {
        // first decode the URL encoding
        String result = "";
        try {
            result = URLDecoder.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result = result.replaceAll(" ", "+");
        // then decrypt the decoded string
        result = decrypt(result);
        return result;
    }
}
