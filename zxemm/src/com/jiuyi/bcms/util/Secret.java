/**
 * <p>Title:             Secret.java
 * <p>Description:       加密解密处理类
 * <p>Copyright:         Copyright (C), 2002-2003, Blueder Tech. Co., Ltd.
 * <p>Company:           Blueder
 * @author          	 xuf
 * @version	 		     V1.0
 * @see		      		 com.jiuyi.bcms.util.Secret
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 05/09/2003	          xuf	                             	         v1.0 
 */
package com.jiuyi.bcms.util;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;

public class Secret
{
	final static Logger Log = Logger.getLogger(Secret.class);
	
	//加密
    public static byte[] doEncrypt(byte [] desKey , byte[] plainText) 
    throws Exception 
    {
        //DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;/* 用某种方法获得密匙数据 */
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        // 现在，获取数据并加密
        byte data[] = plainText;/* 用某种方法获取数据 */
        // 正式执行加密操作
        byte encryptedData[] = cipher.doFinal(data);
        return encryptedData;
    }

	//加密 用固定密钥
    public static byte[] doEncrypt( byte[] plainText) 
    throws Exception 
    {
    	byte [] key =  {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11} ;
    	
    	//return doEncrypt ( "z=a~qv!2".getBytes() , plainText ) ;
    	return doEncrypt ( key , plainText ) ;
	}
	
	//解密
   public static byte[] doDecrypt( byte [] desKey , byte[] encryptText) 
   		throws Exception 
   		{
        //      DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey; /* 用某种方法获取原始密匙数据 */
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        // 现在，获取数据并解密
        byte encryptedData[] = encryptText;/* 获得经过加密的数据 */
        // 正式执行解密操作
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return decryptedData;
    }
	
	//解密，用固定密钥
	public static byte[] doDecrypt (  byte[] encryptText ) 
		throws Exception 
	{
		return doDecrypt ( "z=a~qv!2".getBytes() , encryptText ) ;
	}
	
	/**
     * 16进制数据 二进制串到字符串
     * @param value 字节数组
     * @return
     */
    public static String toHexString(byte[] value) {
        String newString = "";
        for (int i = 0; i < value.length; i++) {
            byte b = value[i];
            String str = Integer.toHexString(b);
            if (str.length() > 2) {
                str = str.substring(str.length() - 2);
            }
            if (str.length() < 2) {
                str = "0" + str;
            }
            newString += str;
        }
        return newString.toUpperCase();
    }

	/**
     * 16进制数据 到二进制串
     * @param value 字节数组
     * @return
     */
	public static byte[] HexToByte ( String hexData ) 
	{
	   byte[] bts = new byte[hexData.length() / 2];
   	   for (int i = 0; i < bts.length; i++) 
   	   {
       		bts[i] = (byte) Integer.parseInt(hexData.substring(2*i, 2*i+2), 16);
       }
 	  return bts; 
 	}
	
	/*
	 *对密码加密，加密对象为客户号和用户设置的密码
	 *加密规则：
	 *		用户密码： des -> md5 ->hexstring  des-key= cust_no 4 + "8A!h" 
	 *		凭证密码：                         des-key= 凭证号 4 + "3c?W" 
	 *	
	 */
	public static String encPwd ( String acc_pwd ) 
	{
		return encPwd ( acc_pwd , ""  ) ;
	}
	
	//zmalqp10
	public static String encPwd ( String src_pwd , String src_key ) 
	{
		try
		{
			Log.debug( "密码明文:" + src_pwd ) ;
			//String key = src_key.substring( src_key.length()-4 ) + "8A!h" ;
			String key  ;
//			if ( src_key == null || src_key == "" ) 
//			{
				key = "_3!A+QtV" ;
//			}
//			else
//			{
//				key = src_key.substring( src_key.length()-4 ) + "8A!h" ;
//			}
		//	MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] eData = doEncrypt ( key.getBytes() , src_pwd.getBytes() ) ;
			
			//System.out.println("e data =" + toHexString ( md.digest( edata )  ) )  ;
			
		//	return toHexString ( md.digest( eData )  ) ;
			String encData = toHexString (  eData   ) ;
			Log.debug( "加密后数据：" + encData ) ;
			return encData ;
		}
		catch ( Exception e ) 
		{
			Log.debug( "密码加密错误：" + e ) ;
			return src_pwd ;
		}
	
	}	 
	/**
	* 对输入的字符串进行加密
	*@param String 原始字符串
	*@return String 加密后的16进制字符串
	*@throws BCMSException
	*/
	public static String encrypt(String src_txt)
	{
		MessageDigest digest;
		String algorithm = "MD5";
		try
		{
			digest = MessageDigest.getInstance(algorithm);
	    	digest.update(src_txt.getBytes());
	    	byte[] b = digest.digest();
	    	String s="";
	    	for(int i=0; i<b.length; i++)
	    	{
	    		s = s+ byteHEX(b[i]);
	    	}	    		
	    	return s;
	    }
	  	catch (Exception e)
	  	{
	  		Log.error(e.getMessage());
	  		//throw new EncryptFatalException(e.getMessage());
	  	}
	  	return "" ;
	}
	//产生令牌
	public static String genToken ( byte [] id , byte [] now ) 
	{
		try
		{
	           MessageDigest md = MessageDigest.getInstance("MD5"); 
	           md.update(id); 
	           md.update(now); 
	           return (toHexString(md.digest())); 
	    }
	  	catch (Exception e)
	  	{
	  		Log.error(e.getMessage());
	  		return null ;
	  	}
	}
	/**
	* 将一个字节转换为16进制字符串
	*/
	public static String byteHEX(byte ib)
	{
		/* if want to output normal letter please user DigitNormal */
		char[] DigitNormal = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
	
		/* if want to output capitalization letter please user DigitCap */
		/* char[] DigitCap = { '0','1','2','3','4','5','6','7','8','9',	'A','B','C','D','E','F' }; */
	
		char [] ob = new char[2];
		ob[0] = DigitNormal[(ib >>> 4) & 0X0F];
	
		/* ob[0] = DigitCap[(ib >>> 4) & 0X0F]; */
		ob[1] = DigitNormal[ib & 0X0F];
		/* ob[1] = DigitCap[ib & 0X0F]; */
		String s = new String(ob);
		return s;
	}
	
	public static void main(String[] args)
		throws Exception 
	{
		//System.out.println(Secret.encrypt("157191"));
		//System.out.println(Secret.encrypt("fdsfs@#$%094"));
		/*
		CRC32 mycrc = new CRC32 () ;
		String s = "0000000001" ;
		mycrc.reset() ;
		mycrc.update( s.getBytes() ) ;
		System.out.println( mycrc.getValue( )  );
		s = "00002" ;
		mycrc.reset() ;
		mycrc.update( s.getBytes() ) ;
		System.out.println( mycrc.getValue( )  );
		s = "00003" ;
		mycrc.reset() ;
		mycrc.update( s.getBytes() ) ;
		System.out.println( mycrc.getValue( )  );
		System.out.println( java.lang.StrictMath.random()*100000  );
		System.out.println( java.lang.StrictMath.random()*100000  );
		Random myRand = new Random () ;
		myRand.setSeed( 1 ) ;
		System.out.println( myRand.nextInt(10000)  );
		System.out.println( myRand.nextInt(10000)  );
		System.out.println( myRand.nextLong() );
		System.out.println( myRand.nextLong() );
		
		myRand.setSeed( 2 ) ;
		System.out.println( myRand.nextInt(10000)  );
		System.out.println( myRand.nextInt(10000)  );
		System.out.println( myRand.nextLong()  );
		System.out.println( myRand.nextLong() );
		
		*/
//		String key="12345678" ;
//		String data = "abcdefg" ;
		
//		byte [] edata =  doEncrypt ( key.getBytes() , data.getBytes()  ) ;
//		System.out.println("e data =" +  edata  )  ;
		
//		for ( int i = 0 ; i<edata.length ; i++ )
//			System.out.println("e data(byte) =" + byteHEX (  edata[i])   )  ;
//		System.out.println("e hex data =" + toHexString ( edata ) )  ;

//		MessageDigest md = MessageDigest.getInstance("MD5");
//		System.out.println("e data =" + toHexString ( md.digest( edata )  ) )  ;
//		System.out.println("md5 data =" + toHexString ( md.digest( key.getBytes() )  ) )  ;
		
//		System.out.println("d data =" +  new String ( doDecrypt( key.getBytes() ,  edata  )  ) ) ;
//		System.out.println("d data =" +  new String ( doDecrypt( key.getBytes() ,  HexToByte ( toHexString ( edata )  )  )  ) ) ;
	}
}