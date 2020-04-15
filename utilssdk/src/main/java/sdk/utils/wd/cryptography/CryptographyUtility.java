/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.cryptography;

import android.util.Base64;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sdk.utils.wd.log.CustomLogUtility;

public class CryptographyUtility {

    public static final int INIT_VECTOR_LENGTH = 16;
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * this utility method is used to decrypt the encrypted information.
     *
     * @param key   secret key which is required to decrypt the information (Key must be of 16bytes)
     * @param value information which is to be decrypted
     * @return string decrypted information
     */
    public static String Decrypt(String value, String key) {

        try {
            // Get raw encoded data
            byte[] encrypted = Base64.decode(value, Base64.DEFAULT);

            // Slice initialization vector
            IvParameterSpec ivParameterSpec = new IvParameterSpec(encrypted, 0, INIT_VECTOR_LENGTH);
            // Set secret password
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance
                    ("AES/CBC/PKCS5Padding"); //this parameters should not be changed
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // Trying to get decrypted text
            String result = new String(cipher.doFinal(encrypted, INIT_VECTOR_LENGTH, encrypted.length - INIT_VECTOR_LENGTH));

            // Return successful decoded object
            return result;

        } catch (Throwable t) {
            t.printStackTrace();
            // Operation failed
            return t.getMessage();
        }
    }

    /**
     * this utility method is used to encrypt the information
     *
     * @param key   secret key which is used to encrypt the information (Key must be of 16bytes)
     * @param value information to be encrypted
     * @return string encrypted information
     */
    public static String Encrypt(String value, String key) {

        String initVector;
        try {
            // Check secret length
            // Get random initialization vector
            SecureRandom secureRandom = new SecureRandom();
            byte[] initVectorBytes = new byte[INIT_VECTOR_LENGTH / 2];
            secureRandom.nextBytes(initVectorBytes);
            initVector = bytesToHex(initVectorBytes);
            initVectorBytes = initVector.getBytes("UTF-8");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(initVectorBytes);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance
                    ("AES/CBC/PKCS5Padding"); //This Value should not be changed in future
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            // Encrypt input text
            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));

            ByteBuffer byteBuffer = ByteBuffer.allocate(initVectorBytes.length + encrypted.length);
            byteBuffer.put(initVectorBytes);
            byteBuffer.put(encrypted);

            // Result is base64-encoded string: initVector + encrypted result
            String result = Base64.encodeToString(byteBuffer.array(), Base64.DEFAULT);
            String replacedString = result.replaceAll("\\+", "~");
            CustomLogUtility.logD("ReplaceDEnc", replacedString);
            return replacedString;
        } catch (Throwable t) {
            t.printStackTrace();
            // Operation failed
            return t.getMessage();
        }
    }

    /**
     * Convert Bytes to HEX
     *
     * @param bytes Bytes array
     * @return String with bytes values
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}

