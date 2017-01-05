package com.jiuyi.jyplat.service.signature;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface SignManager {
	public static String DEFAULT_RSA_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI2s8bIs1us19VJmAWtAskDsYB3cFMN9jpudtnUTIR3CNed+nlR1Bgly214fX7FY0V5Pbq0DkS8FIvWM/hFO4thflU0scXhBc76zoibafMf14WvMTO+IBQOipgxFemVh/y3xHMA27aCtXi0Aps0VTUaxG6tlk+nEP7rzza5RFFABAgMBAAECgYAJwqdoEgTAcZg0FAj2/Z8KL/6zL+1SRn0jpWrjh+zZIv8ZsrRvtxwyXHJWeqzaB/dsmuZSDxPafG0bI5qBUMD58sfzTX6GKTgF7LFPFojsMvgA45km9f9qlnjS8vC2BDJtYEnVX/6yAbOWcC8d2bBAToQ3+H6FX6K+NPnsT4CPQQJBANFl5hjJ9dDIB6N/1KEpp7VgqG2a6kEe9O3mdtSi5syZsCq7Ndy2vvrHABnYmNNFQjfyoT3v2a0+MwTk/LVoR1kCQQCtNKvFlqHsUqGlhmivmZ542edIlIFFZy/EtuKCYZ4rrsWSty0lw1PBWwJGtdI8IPc3+3tmm0goPHN7AcuqemDpAkEAy2QnyMI6s03oJYaf2N7mGIZHcxb7HL/d9kpZ+BY3Clrpw8fN/JHBFUNANNqvLt9h/4f72HZlwCHfWoy1bdtZGQJAVTotq7RLSPYABGr26DE3AVB3tgcc9QGpR6qWGoHeSrBdF6x60rIibGpT3GdrY5r1Ct78Aak3AFV+nXBsvx4owQJBAIYf8MO/dIQ5JtBV0cENkAB0IY8UxhDOIIJF2eqlyaSehQLxIKNLSvXsWv9Tg3HRxgRa/0m6qAAXNDpwaILh8+g=";

	public static String DEFAULT_RSA_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNrPGyLNbrNfVSZgFrQLJA7GAd3BTDfY6bnbZ1EyEdwjXnfp5UdQYJctteH1+xWNFeT26tA5EvBSL1jP4RTuLYX5VNLHF4QXO+s6Im2nzH9eFrzEzviAUDoqYMRXplYf8t8RzANu2grV4tAKbNFU1GsRurZZPpxD+6882uURRQAQIDAQAB";

	public static String DEFAULT_DSA_PRI_KEY = "MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIULTlhSlEwASbyPlTwM8vNrPWjfNY=";

	public static String DEFAULT_DSA_PUB_KEY = "MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAHbWiqWfzXXs/YtJWM3pZZ5bI1Avnv/R959HjBrBsMQROvFfL06KSBJIfy1p7D/hTwttSWpEU/HyfXwpj3pLjh4t6zedciW0GswimGyBRVAHblb9DsOHXtU1Hs0JLTPBH13PviQSimcj+xOumWjdkYVlPqYOPqtz4KzR16gf9ACE=";

	public static final String MD5_ALGORITHM_NAME = "MD5";
	/** RSA算法名 */
	public static final String RSA_ALGORITHM_NAME = "RSA";
	/** DSA算法名 */
	public static final String DSA_ALGORITHM_NAME = "DSA";
	/** 算法 */
	public static final String RSA_SIGN_ALGORITHMS = "SHA1WithRSA";

	public static final String DSA_SIGN_ALGORITHMS = "DSA";

	public static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 生成BASE64编码后的签名串，支持MD5/RSA/DSA算法
	 * 
	 * @param content
	 * @param signType
	 * @param key
	 * @return
	 */
	public String sign(String content, String signType, String key);

	/**
	 * 校验签名，支持MD5/RSA/DSA算法
	 * 
	 * @param signature
	 * @param content
	 * @param signType
	 * @param key
	 * @return
	 */
	public boolean check(String signature, String content, String signType,
			String key);

	/**
	 * 签名，使用非对称密钥，不支持自制MD5
	 * 
	 * @param content
	 * @param signType
	 * @param priKey
	 * @return
	 */
	public String sign(String content, String signType, PrivateKey priKey);

	/**
	 * 签名校验，使用非对称密钥，不支持自制MD5
	 * 
	 * @param signature
	 * @param content
	 * @param signType
	 * @param pubKey
	 * @return
	 */
	public boolean check(String signature, String content, String signType,
			PublicKey pubKey);
}
