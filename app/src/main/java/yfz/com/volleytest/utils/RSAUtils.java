package yfz.com.volleytest.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


import javax.crypto.Cipher;

/**
 * ***************************************************************************
 * E城到家 Home 页面，这里主要是实现了底部菜单栏
 * ****************************************************************************
 * Authors:chris on 12/25/15 10:17
 * Email：zhangyanlongcodec@gmail.com
 */
public class RSAUtils {

    private static final String CHAR_ENCODE = "UTF-8";
    private static final int KEY_SIZE = 1024;
    private static final String KEY_PAIR_GENERATOR = "RSA";
    private static final String CIPHER_PADDING = "RSA/ECB/PKCS1Padding";
    /**公钥模长*/
    public static final String  PUCLIC_KEY_Modulus = "ALV5iJOYe0ZuGJTMErYo07h628erzJoVHdLux+M/9nGb1XyLmEiMDMlmUhQpzgwsHuaiX34l2Vb/a0WFLGebhCbvT2tc/BNmgEEiED7+9NXu7dEfZLUXCaZ7aWj3dRVWUysvWUlNk3co/x2Qg01XjCAqN09IfbvWOIwR0Ijk+Yqz";
    /**私钥模长*/
//  public static final String PRIVATE_KEY_Modulus = "AI0tx+yl36W569NThTk+IUgx5XTRgf2RMLNdmUIwoCtm/h+kPLAAN8ZcACLYrB6yaMfA66ao1wcSMCdgV0rtlt6EYSgwwgTQZcLl54FBhX2chSRgtNev7vPLUHdyJHs+gALALIa+4NkQKWPak0dRVToV0QW/4PUn9Zk+0Px6MFH9";
    /**公钥指数*/
    public static final String PUCLIC_Exponent = "AQAB";
    /**私钥指数*/
    public static final String PRIVATE_Exponent = "e/9q3s7VjK41hZZbQrRq1ia8fZZZ2v6KvlIBqrlNvxrnqI4Jo6huJD8R3k0iSRbsllJkRWG/O76SoZH5YWHVzU1V/A791bsxpeQ5jJ7VZl4mN7ysxVtSkCE+8B6jIjioMJGdoOWnUDIYJgPCngP9nUFmJgxf0ND/fPE1oIZ7O4E=";

    /**
     * 生成公钥和私钥
     * @throws NoSuchAlgorithmException
     *
     */
    public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException{
        HashMap<String, Object> map = new HashMap<String, Object>();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_PAIR_GENERATOR);
        keyPairGen.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        map.put("public", publicKey);
        map.put("private", privateKey);
        return map;
    }
    /**
     * 使用模和指数生成RSA公钥
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding 】
     * @param modulus 模
     * @param exponent 指数
     * @return
     */
    public static RSAPublicKey getPublicKey(String modulus, String exponent) {
        try {
            BigInteger mod = new BigInteger(1, Base64Decoder(modulus));
            BigInteger exp = new BigInteger(1, Base64Decoder(exponent));
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_PAIR_GENERATOR);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(mod, exp);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用模和指数生成RSA私钥
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
     * @param modulus 模
     * @param exponent 指数
     * @return
     */
    public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
        try {
            BigInteger b1 = new BigInteger(Base64Decoder(modulus));
            BigInteger b2 = new BigInteger(Base64Decoder(exponent));
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_PAIR_GENERATOR);
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 模长
        int maxBlockSize = publicKey.getModulus().bitLength() / 8  - 11;
        // 加密数据长度 <= 模长-11, 如果明文长度大于模长-11则要分组加密
        byte[] plantText = data.getBytes(Charset.forName(CHAR_ENCODE));
        if (plantText.length <= maxBlockSize) {
            return Base64Encoder(cipher.doFinal(plantText));
        } else {
            ByteArrayInputStream bis = new ByteArrayInputStream(plantText);
            ByteArrayOutputStream bos = new  ByteArrayOutputStream();
            byte[] dd = new byte[maxBlockSize];
            int blockSize = bis.read(dd);
            while (blockSize > 0) {
                bos.write(cipher.doFinal(dd));
                blockSize = bis.read(dd);
            }
            return Base64Encoder(bos.toByteArray());
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //模长 如果密文长度大于模长则要分组解密
        int maxBlockSize = privateKey.getModulus().bitLength() / 8;
        byte[] cipherText = Base64Decoder(data);

        if (cipherText.length <= maxBlockSize) {
            return new String(cipher.doFinal(cipherText), Charset.forName(CHAR_ENCODE));
        } else {
            ByteArrayInputStream bis = new ByteArrayInputStream(cipherText);
            ByteArrayOutputStream bos = new  ByteArrayOutputStream();
            byte[] dd = new byte[maxBlockSize];
            int blockSize = bis.read(dd);
            while (blockSize > 0) {
                bos.write(cipher.doFinal(dd));
                blockSize = bis.read(dd);
            }
            return new String(bos.toByteArray(), Charset.forName(CHAR_ENCODE));
        }
    }

    public static String Base64Encoder(byte[] data) {
        return (new BASE64Encoder()).encode(data);
    }

    public static byte[] Base64Decoder(String data){
        try {
            return (new BASE64Decoder()).decodeBuffer(data);
        } catch (IOException e) {
            return null;
        }
    }

}
