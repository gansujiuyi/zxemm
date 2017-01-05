package com.jiuyi.bcms.util;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;

/**
 * 这个是key的工具类，用来管理我们的key
 * @author sheng.liuzs
 *
 */
public class KeyUtil {
    /**
     * 
     * 这个类用来得到支付宝的公钥，返回一个PublicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getAlipayPubKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 注意这里java在读取文件路径中有空格的情况下是会抛异常的。
        String filename = keyReader.getClass().getResource("lzyh.cer").getFile();
        PublicKey pubKey = (PublicKey) keyReader.fromCerStoredFile(filename);
        // System.out.println("PublicKey => " + new String(Base64.encodeBase64(pubKey.getEncoded())));
        return pubKey;
    }

    /**
     * 
     * 这个类用来得到模拟银行的公钥，返回一个PublicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getMOCKPubKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 注意这里java在读取文件路径中有空格的情况下是会抛异常的。
        String filename = keyReader.getClass().getResource("mock.cer").getFile();
        PublicKey pubKey = (PublicKey) keyReader.fromCerStoredFile(filename);
        // System.out.println("PublicKey => " + new String(Base64.encodeBase64(pubKey.getEncoded())));
        return pubKey;
    }

    /**
     * 
     * 这个类用来得到模拟银行的私钥，返回一个PrivateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getMOCKPriKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 注意这里java在读取文件路径中有空格的情况下是会抛异常的。
        String filename = keyReader.getClass().getResource("mock.pfx").getFile();
        PrivateKey priKey = keyReader.readPrivateKeyfromPKCS12StoredFile(filename, "12345678");
        System.out.println("privateKey => " + new String(Base64.encodeBase64(priKey.getEncoded())));
        return priKey;
    }
    
    /**
     * 获取公钥信息
     * @param args
     */
    public static void main(String[] args) {
        try {
            getMOCKPriKey();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
