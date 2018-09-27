package com.aifeng.ddrent.common.util.security;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSASigner {
    public static String sign(String signatureAlgorithm, String content, String privateKey) throws Exception {
        return sign(signatureAlgorithm, content.getBytes(), privateKey);
    }

    public static String sign(String signatureAlgorithm, byte[] content, String privateKey) throws Exception {
        PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey priKey = keyFactory.generatePrivate(priKeySpec);
        Signature signature = Signature.getInstance(signatureAlgorithm);
        signature.initSign(priKey);
        signature.update(content);
        return new String(Base64.getEncoder().encode(signature.sign()));
    }

    public static boolean verify(String signatureAlgorithm, String content, String sign, String publicKey) throws Exception {
        return verify(signatureAlgorithm, content.getBytes(), sign, publicKey);
    }

    public static boolean verify(String signatureAlgorithm, byte[] content, String sign, String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] encodedKey = Base64.getMimeDecoder().decode(publicKey);
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        Signature signature = Signature.getInstance(signatureAlgorithm);
        signature.initVerify(pubKey);
        signature.update(content);
        return signature.verify(Base64.getMimeDecoder().decode(sign));
    }
}
