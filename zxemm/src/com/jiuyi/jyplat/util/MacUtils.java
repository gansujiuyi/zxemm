/* 
 * <p>Title:		MacUtils.java
 * <p>Description:	编码与数据类型类
 * <p>Copyright:	uxunchina
 * <p>Company:		http://www.uxunchina.com
 * <p>@author:		zenglj
 * <p>@version:		1.0
 * <p>@see: 					
 */

package com.jiuyi.jyplat.util;

//des 加解密
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MacUtils {
	static Logger Log = Logger.getLogger(MacUtils.class);

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/*
	 * 根据输入的MAC数据、MACKEY计算获得MAC数据
	 * 参与MAC计算的数据
	 * 
	 */
	public static byte[] genMac(byte[] data, byte[] MACKEY) {
		//zak(mackey)
		//byte[] MACKEY = ConvertUtil.strToBcd("8F37EADF07CB5232");
		//待计算数据
		//byte[] data = ConvertUtil.stringToBytes("0200702004C000C010532000008600898000005018600000000000000100000047021F9106323330303030303133303130303230303030303036313000000000000000000047525108980001FFFFFFFF00008600898000005018741ECF4A010000010001863C00000064020000000000017132B6B800114000000800000003303030","iso8859-1");
		//进行分组
		int group = (data.length + (8 - 1)) / 8;
		//偏移量
		int offset = 0;
		//输入计算数据
		byte[] edata = new byte[8];
		for (int i = 0; i < group; i++) {
			byte[] temp = new byte[8];
			if (i != group - 1) {
				System.arraycopy(data, offset, temp, 0, 8);
				offset += 8;
			} else {//只有最后一组数据才进行填充0x00
				System.arraycopy(data, offset, temp, 0, data.length - offset);
			}
			//先异或
			temp = XOR(edata, temp);

			//再加密
			edata = desedeEn(MACKEY, temp);
		}

		//返回数据
		return edata;
	}

	//异或操作
	public static byte[] XOR(byte[] edata, byte[] temp) {
		byte[] result = new byte[8];
		for (int i = 0, j = result.length; i < j; i++) {
			result[i] = (byte) (edata[i] ^ temp[i]);
		}
		return result;
	}

	//DES加密

	public static byte[] desedeEn(byte[] key, byte[] data) {
		byte[] result = null;
		try {
			SecretKey secretKey = getSecretKeySpec(key);
			Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[8]));//初始化项目为0
			result = cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug("DES加密异常" + e.getMessage());
		}
		return result;
	}

	//DES解密
	public static byte[] desedeDec(byte[] key, byte[] data) {
		byte[] result = null;
		try {
			SecretKey secretKey = getSecretKeySpec(key);
			Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[8]));//初始化项目为0
			result = cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug("DES解密异常" + e.getMessage());
		}
		return result;
	}

	private static SecretKey getSecretKeySpec(byte[] keyB) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("Des");
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyB, "Des");
		return secretKeyFactory.generateSecret(secretKeySpec);
	}
	
	/**
	 * 3des加密 message->密文byte[]->16进制密文字符串
	 * @param keyPassword
	 * @param message
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encrypt(String keyPassword, String message) throws NoSuchAlgorithmException,
			NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException {
		KeyGenerator kgen = KeyGenerator.getInstance("DESede");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(keyPassword.getBytes());
		kgen.init(168, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		byte[] byteContent = message.getBytes("UTF-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = cipher.doFinal(byteContent);
		return parseByte2HexStr(result);
	}
	
	/**
	 * 3des解密 16进制密文字符串->byte[]->解密
	 * @param keyPassword
	 * @param message
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(String keyPassword, String message)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator kgen;
		kgen = KeyGenerator.getInstance("DESede");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(keyPassword.getBytes());
		kgen.init(168, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		byte[] byteContent = parseHexStr2Byte(message);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] result = cipher.doFinal(byteContent);
		return new String(result);
	}
	
	/**
	 * 将16进制字符串转化成byte[]
	 * @param buf
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	/**
	 * 将byte[]转化成16进制字符串
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * MD5加密 
	 * @param inStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5Encode(String inStr) throws NoSuchAlgorithmException {
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

}
