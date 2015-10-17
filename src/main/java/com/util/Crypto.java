package com.util;

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
    private static byte[] ivBytes = new byte[] {12, 34, 44, 17, 95, 87, 65, 432};
    private static byte[] keyBytes = new byte[] {238, 151, 31, 13, 83, 61, 56, 76};

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
            result = new String(Base64.encode(raw));
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

            byte[] raw = Base64.decode(encrypted.getBytes());
            byte[] stringBytes = cipher.doFinal(raw);
            result = new String(stringBytes, "UTF8");
        } catch (Exception e) {
            LOGGER.error("Decryption error: " + e.getMessage());
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
        }
        result = result.replaceAll(" ", "+");
        // then decrypt the decoded string
        result = decrypt(result);
        return result;
    }
}
