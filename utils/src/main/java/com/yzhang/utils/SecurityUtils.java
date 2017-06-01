package com.yzhang.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by yzhang on 2017/2/9.
 */

public final class SecurityUtils {
    /**
     * MD5 加密算法
     *
     * @param input
     * @return
     */
    public static String MD5Encrypt(String input) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }

    /**
     * RSA加密
     *
     * @param input        需要进行RSA加密的数据
     * @param RSAPublickey RSA公钥
     * @return
     */
    public static String RSAEncrypt(String input, String RSAPublickey) {
        String result = "";
        try {
            byte[] srcData = input.getBytes("UTF-8");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] buffer = Base64.decode(RSAPublickey.getBytes("UTF-8"),
                    Base64.DEFAULT);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(buffer);

            PublicKey publicKey = keyFactory.generatePublic(spec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] toData = cipher.doFinal(srcData);

            result = Base64.encodeToString(toData, Base64.DEFAULT);
            result = result.replace("\n", "");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DES加密函数
     *
     * @param input  加密数据
     * @param DESkey DES密钥
     * @return 返回加密后的数据
     */
    public static String DESEncrypt(String input, String DESkey) {

        try {

            // DES算法要求有一个可信任的随机数源
//            SecureRandom sr = new SecureRandom();

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(DESkey.getBytes());

            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);

            // using DES in ECB mode
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // 执行加密操作
            byte encryptedData[] = cipher.doFinal(input.getBytes());
            String result = Base64.encodeToString(encryptedData, Base64.DEFAULT);
            result = result.replace("\n", "");
            return result;
        } catch (Exception e) {
            System.err.println("DES算法，加密数据出错!");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * DES解密算法
     *
     * @param input
     * @param DESkey
     * @return
     */
    public static String DESecrypt(String input, String DESkey) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            // byte rawKeyData[] = /* 用某种方法获取原始密匙数据 */;

            // 从原始密匙数据创建一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(DESkey.getBytes());

            // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);

            // using DES in ECB mode
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

            // 正式执行解密操作
            byte decryptedData[] = cipher.doFinal(input.getBytes());
            String output = new String(decryptedData, "UTF-8");
            return output;
        } catch (Exception e) {
            System.err.println("DES算法，解密出错。");
            e.printStackTrace();
        }

        return null;
    }
}
