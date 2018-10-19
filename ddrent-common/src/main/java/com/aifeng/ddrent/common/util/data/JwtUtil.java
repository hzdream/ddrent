package com.aifeng.ddrent.common.util.data;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aifeng.ddrent.common.model.auth.JwtToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	/** jwt名字 */
	private static final String JWT_NAME = "JWT";
	
	/** 算法名字  */
	public static final String RSA_ALGORITHM_NAME = "RSA256";
	
	/** 算法名字  */
	public static final String HMAC_ALGORITHM_NAME = "HMAC256";
	
	/** 默认算法对象 */
	private static Algorithm HMACALGORITHM ;
	
	/** 发行方 */
	public static final String Issuser = "www.ddrent.com";
	
	/** 默认失效时间，单位：分钟*/
	public static final Integer DEFAULT_EXPIR = 120;
	
	/** 失效缓冲时间 ，单位：秒*/
	public static final Integer DEFAULT_EXPIR_LEEWAY = 30*60;
	
	private static String DEFAULT_HMACAL_KEY = "pgmeFQEU%P&crfa6";
	
	/**
	 * 初始化签名算法
	 * @param key
	 */
	public static void init(String key) {
		key = StringUtils.isBlank(key) ? DEFAULT_HMACAL_KEY : key;
		/** HMAC256算法初始化*/
		HMACALGORITHM = getHSAlgorithm(key);
	}
	
	/**
	 * 初始化默认算法
	 * @param key
	 * @return
	 */
	private static Algorithm getHSAlgorithm(String key){
		if(null != HMACALGORITHM) {
			return HMACALGORITHM;
		}else {
			Algorithm algorithmHS = null;
			try {
				algorithmHS = Algorithm.HMAC256(key);
			} catch (IllegalArgumentException e) {
				logger.error("JwtUtil.getHSAlgorithm 初始化算法失败:", e);
			} catch (UnsupportedEncodingException e) {
				logger.error("JwtUtil.getHSAlgorithm 初始化算法失败:", e);
			}
			return algorithmHS;
		}
		
	}
	
	/**
	 * 创建jwt token
	 * @param claims
	 * @return
	 */
	public static String encodeToken(Map<String, String> claims) {
		Calendar cal = Calendar.getInstance();
		/** 发行日期 */
		Date iat = cal.getTime();
		/** 不早于……时间 */
		Date nbf = cal.getTime();

		/** 失效时间 */
		cal.add(Calendar.MINUTE, DEFAULT_EXPIR);
		Date expire = cal.getTime();
		
		return encodeToken(iat, expire, nbf, claims);
	}

	/**
	 *
	 * @param jwtToken
	 * @return
	 */
	public static String encodeToken(JwtToken jwtToken){
		return encodeToken(jwtToken.getClaims());
	}
	
	public static void main(String[] args) {
		
		DecodedJWT jwt = verifyDefault("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1MzEyMDY4NTYsImlzcyI6Imlkcy5saWFubGlhbnBheS5jb20iLCJyZXF1ZXN0SXAiOiIxMC4yMC4zNi4zNCIsImV4cCI6MTUzMTIxNDA1NiwiYXV0aFR5cGUiOiIyIiwidG9rZW5UeXBlIjoiMCIsImlhdCI6MTUzMTIwNjg1NiwidXNlcmFjY291bnQiOiJkZW5neGYifQ.izigytl8cyRIx4R5je1QDpfzGsPFfBVtvvFYomJsGcs");
		
		System.out.println(jwt.getClaims());
		
	}

	/**
	 * 创建jwt token, 默认算法为HMACALGORITHM
	 * @param iat		发行时间
	 * @param expir		失效时间
	 * @param nbf		生效时间
	 * @param claims	body参数
	 * @return
	 */
	public static String encodeToken(Date iat, Date expir, Date nbf, Map<String, String> claims) {
		return signWith(iat, expir, nbf, claims, getHSAlgorithm(DEFAULT_HMACAL_KEY));
	}

	/**
	 * 创建jwt token
	 * @param iat		发行时间
	 * @param expir		失效时间
	 * @param nbf		生效时间
	 * @param claims	body参数
	 * @param algorithm	生成算法
	 * @return
	 */
	public static String signWith(Date iat, Date expir, Date nbf, Map<String, String> claims, Algorithm algorithm) {
		try {
			Map<String, Object> head = new HashMap<>();
			head.put("typ", JWT_NAME);
			head.put("alg", RSA_ALGORITHM_NAME);
			Builder builder = JWT.create();
			builder = builder
					.withIssuer(Issuser)
					.withIssuedAt(iat)
					.withExpiresAt(expir)
					.withNotBefore(nbf)
					.withHeader(head);
			
			if(null != claims) {
				for (Map.Entry<String, String> entry : claims.entrySet()) {
					builder.withClaim(entry.getKey(), entry.getValue());
				}
			}
			
			String token = builder.sign(algorithm);
			return token;
		} catch (Exception e) {
		}
		
		return "";
	}
	
	/**
	 * 默认加密算法验证token有效性，不包括可刷新时间
	 * @param token
	 * @return
	 */
	public static DecodedJWT verifyDefault(String token) {
		return verify(token, getHSAlgorithm(DEFAULT_HMACAL_KEY));
	}
	
	/**
	 * 	根据提供的算法，验证token有效性，不包括可刷新时间
	 * @param token
	 * @param algorithm
	 * @return
	 */
	public static DecodedJWT verify(String token, Algorithm algorithm) {
		
		try {
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(Issuser)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			
			return jwt;
		} catch (JWTVerificationException exception){
			logger.info("JwtUtil.verify JWT校验不通过:" + exception.getMessage());
		}
		
		return null;
	}

	/**
	 * 	是否在允许范围类有效，包括可刷新时间
	 * @return
	 */
	public static DecodedJWT verifyWithinLeeway(String token) {
		return verifyWithinLeeway(token, getHSAlgorithm(DEFAULT_HMACAL_KEY));
	}

	public static DecodedJWT verifyWithinLeeway(String token, Algorithm algorithm){
		try {
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(Issuser)
					.acceptExpiresAt(DEFAULT_EXPIR_LEEWAY)
					.build();
			DecodedJWT jwt = verifier.verify(token);

			return jwt;
		} catch (JWTVerificationException exception){
			logger.info("[Jwt 有效时间]校验不通过:" + exception.getMessage());
		}
		return null;
	}
	
	/**
	 * 得到RSA公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到RSA私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

}
