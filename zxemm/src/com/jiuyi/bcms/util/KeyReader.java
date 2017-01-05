package com.jiuyi.bcms.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.*;

public class KeyReader {

	public boolean isPublicKeyValid(String key, String algorithmName) {
		try {
			readPublicKey(key, true, algorithmName);
		} catch (InvalidKeySpecException e) {
			return false;
		}

		return true;
	}

	public boolean isPrivateKeyValid(String key, String algorithmName) {
		try {
			readPrivateKey(key, true, algorithmName);
		} catch (InvalidKeySpecException e) {
			return false;
		}

		return true;
	}

	/**
	 * 读取私钥
	 * 
	 * @param keyStr
	 * @param base64Encoded
	 * @param algorithmName
	 * @return
	 * @throws InvalidKeySpecException
	 */
	public PrivateKey readPrivateKey(String keyStr, boolean base64Encoded,
			String algorithmName) throws InvalidKeySpecException {
		return (PrivateKey) readKey(keyStr, false, base64Encoded, algorithmName);
	}

	/**
	 * 读取公钥
	 * 
	 * @param keyStr
	 * @param base64Encoded
	 * @param algorithmName
	 * @return
	 * @throws InvalidKeySpecException
	 */
	public PublicKey readPublicKey(String keyStr, boolean base64Encoded,
			String algorithmName) throws InvalidKeySpecException {
		return (PublicKey) readKey(keyStr, true, base64Encoded, algorithmName);
	}

	/**
	 * 读取密钥，X509EncodedKeySpec的公钥与PKCS8EncodedKeySpec都可以读取，密钥内容可以为非base64编码过的。
	 * 
	 * @param keyStr
	 * @param isPublicKey
	 * @param base64Encoded
	 * @param algorithmName
	 * @return
	 * @throws InvalidKeySpecException
	 */
	private Key readKey(String keyStr, boolean isPublicKey,
			boolean base64Encoded, String algorithmName)
			throws InvalidKeySpecException {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);

			byte[] encodedKey = keyStr.getBytes("UTF-8");

			if (base64Encoded) {
				encodedKey = Base64.decodeBase64(encodedKey);
			}

			if (isPublicKey) {
				EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);

				return keyFactory.generatePublic(keySpec);
			} else {
				EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);

				return keyFactory.generatePrivate(keySpec);
			}
		} catch (NoSuchAlgorithmException e) {
			// 不可能发生
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 从PKCS12标准存储格式中读取私钥钥，后缀为.pfx文件，该文件中包含私钥
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	public PrivateKey readPrivateKeyfromPKCS12StoredFile(String resourceName,
			String password) throws Exception {
		InputStream istream = null;

		istream = new FileInputStream(resourceName);
		// 使用默认的keyprovider，可能会有问题。
		KeyStore keystore = KeyStore.getInstance("PKCS12");
		keystore.load(istream, password.toCharArray());
		Enumeration enumeration = keystore.aliases();
		String alias = null;
		for (int i = 0; enumeration.hasMoreElements(); i++) {
			alias = enumeration.nextElement().toString();
			if (i >= 1) {
				System.out.println("此文件中含有多个证书!");
			}
		}
		return (PrivateKey) keystore.getKey(alias, password.toCharArray());

	}

	/**
	 * 从X509的标准存储格式中读取公钥
	 * 
	 * @param resourceName
	 *            公钥文件
	 * @param base64Encoded
	 *            该文件存储前是否使用base64编码（转化不可见字符）
	 * @return
	 * @throws Exception
	 */
	public Key fromX509StoredFile(String resourceName, boolean base64Encoded)
			throws Exception {

		byte[] encodedKeyByte = readByteFromFile(resourceName);
		if (base64Encoded) {
			encodedKeyByte = Base64.decodeBase64(encodedKeyByte);
		}

		// return fromByte(encodedKeyByte);
		return null;

	}

	/**
	 * Base64编码X.509格式证书文件中读取公钥
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	public Key fromCerStoredFile(String resourceName) throws Exception {
		FileInputStream inputStream = new FileInputStream(resourceName);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate certificate = cf.generateCertificate(inputStream);

		return (Key) (certificate != null ? certificate.getPublicKey() : null);

	}

	/**
	 * 从PKCS12标准存储格式中读取公钥，后缀为.pfx文件，该文件中包含私钥
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	public Key fromPKCS12StoredFile(String resourceName, String password)
			throws Exception {
		InputStream istream = null;

		istream = new FileInputStream(resourceName);
		// 使用默认的keyprovider，可能会有问题。
		KeyStore keystore = KeyStore.getInstance("PKCS12");
		keystore.load(istream, password.toCharArray());
		Enumeration enumeration = keystore.aliases();
		String alias = null;
		for (int i = 0; enumeration.hasMoreElements(); i++) {
			alias = enumeration.nextElement().toString();
			if (i >= 1) {
				System.out.println("此文件中含有多个证书!");
			}
		}

		Certificate certificate = keystore.getCertificate(alias);
		return certificate.getPublicKey();

	}

	/**
	 * 从文件中读取字节
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	public byte[] readByteFromFile(String resourceName) throws Exception {
		InputStream istream = null;
		ByteArrayOutputStream baos = null;

		try {
			istream = new FileInputStream(resourceName);
			baos = new ByteArrayOutputStream();

			IOUtils.copy(istream, baos);
		} catch (IOException e) {
			throw new Exception("Failed to read key file: " + resourceName, e);
		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (IOException e) {
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
