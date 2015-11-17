package com.util.security;

import java.util.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.crypto.codec.Base64;


/**
 * Utility class for crypto logic used for password encrypt & decrypt
 */
public class Crypto {
    //Hardcode key for now
    private static byte[] ivBytes = new byte[] {-42, -52, 62, 24, -72, -82, -65, -68, 91, 47, -81, -53, 25, -9, -88, -54};
    private static byte[] keyBytes = new byte[] { 33, -39, -96, 71, 40, 102, 92, -6, 94, -101, 42, 3, -32, -41, 46, 14};

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
            e.printStackTrace();
        }

        return result;
    }

}
