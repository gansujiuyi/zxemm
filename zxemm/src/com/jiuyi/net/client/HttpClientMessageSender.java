package com.jiuyi.net.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.io.IOUtils;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.jiuyi.bcms.util.KeyReader;
import com.jiuyi.jyplat.util.ConfigManager;

/**
 * <P> 实名认证，绑定电子账户接口类</P>
 * @author 孙正斌
 */
public class HttpClientMessageSender {

	private static String threeDesKey = "123456788765432112345678";
	private static final String ALGORITHM = "DESede";
	private SecretKey key = null;
	private HttpConnectionManager connectionManager;
	private String certPath = "G:/lzyh/lzbank.pfx";
	private String certPassword = "lzbank";
	private Cookie cookie;

	/**
	 * <p>测试方法</p>
	 * @param args
	 * @throws Exception
	 * @author 孙正斌
	 */
	public static void main(String[] args) throws Exception {
		// 根据自己需要填写reqXml，这里的reqXml不是文件名，而是文件的内容。
		HttpClientMessageSender messageSender = new HttpClientMessageSender();
		messageSender.sendMessage("");
	}

	/**
	 * 
	 * <p>发送报文</p>
	 * @param reqContext 请求的报文
	 * @return 响应的报文
	 * @author 孙正斌
	 */
	public String sendMessage(String reqContext) {
		String resultXml = null;
		try {
			HttpClientMessageSender messageSender = new HttpClientMessageSender();
			String address = ConfigManager.getString("cer_path", "");
			System.out.println(address);
			if ("".equals(address)) {
				System.out.println("接口地址获取失败");
				return null;
			}
			Map map = messageSender.sign(reqContext);
			String xmlDate = (String) map.get("SignedXmlData");
			System.out.println("请求报文是:" + xmlDate);
			resultXml = messageSender.send(reqContext, address);
			System.out.println("收到的报文是:" + resultXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultXml;
	}

	/**
	 * 
	 * <p>实名认证拼请求报文</p>
	 * @param acNo
	 * @param bindAcNo
	 * @param bindAcName
	 * @return
	 * @author 孙正斌
	 */
	public String returnAutonymXml(String acNo, String bindAcNo, String bindAcName) {
		//说明：LZYH   Body   ChannelId   TransCode   ReqJnlNo   TransDate   TransTime  这些字段是必须的
		//ChannelId:商户ID TransCode:交易码   ReqJnlNo:交易流水   TransDate:交易日期   TransTime:交易时间   memo:交易描述   AcNo:账号
		String transInfo = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		//AcNo:账号(电子账户账号)，BindAcNo:绑定的银行卡账号, BindAcName:绑定银行卡的用户姓名
		transInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LZYH><Message id=\"Message\"><Head><TransCode></TransCode><ChannelId></ChannelId><ReqJnlNo>000000</ReqJnlNo>"
				+ "<TransDate>"
				+ sdf1.format(cal.getTime())
				+ "</TransDate>"
				+ "<TransTime>"
				+ sdf2.format(cal.getTime())
				+ "</TransTime></Head>"
				+ "<Body><AcNo>"
				+ acNo
				+ "</AcNo><BindAcNo>"
				+ bindAcNo + "</BindAcNo>" + "<BindAcName>" + bindAcName + "</BindAcName>" + "</Body></Message></LZYH>";

		return transInfo;
	}

	/**
	 * 
	 * <p>开电子户（登记电子户）拼请求报文</p>
	 * @param deptSeq 机构号(用户名)
	 * @param userName 用户名
	 * @param idNo 身份证号码
	 * @param mobilePhone 手机号码
	 * @return
	 * @author 孙正斌
	 */
	public String returnDredgeEcountXml(String deptSeq, String userName, String idNo, String mobilePhone) {
		//说明：LZYH   Body   ChannelId   TransCode   ReqJnlNo   TransDate   TransTime  这些字段是必须的
		//ChannelId:商户ID TransCode:交易码   ReqJnlNo:交易流水   TransDate:交易日期   TransTime:交易时间   memo:交易描述   AcNo:账号
		String transInfo = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		transInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LZYH><Message id=\"Message\"><Head><TransCode></TransCode><ChannelId></ChannelId><ReqJnlNo>000000</ReqJnlNo>"
				+ "<TransDate>"
				+ sdf1.format(cal.getTime())
				+ "</TransDate>"
				+ "<TransTime>"
				+ sdf2.format(cal.getTime())
				+ "</TransTime></Head>"
				+ "<Body><DeptSeq>"
				+ deptSeq
				+ "</DeptSeq><UserName>"
				+ userName
				+ "</UserName>"
				+ "<IdNo>"
				+ idNo
				+ "</IdNo>"
				+ "<MobilePhone>"
				+ mobilePhone + "</MobilePhone>" + "</Body></Message></LZYH>";

		return transInfo;
	}

	/**
	 * <p>绑定，开通电子账户拼请求报文</p>
	 * @param idNo 身份证号
	 * @param acNo 绑定的银行卡号
	 * @param acName 绑定的银行卡的用户姓名
	 * @param acNoFlag 银行卡标识 0:本行卡 1:他行卡
	 * @param fundCardFlag 若是本行卡，是否作为购买“宝”的卡 0-否    1-是,在本系统中默认为0。
	 * @param trsPassword 电子账户密码
	 * @return
	 * @author 孙正斌
	 */
	public String returnBindingXml(String idNo, String acNo, String acName, String acNoFlag, String fundCardFlag,
			String trsPassword) {
		//说明：LZYH   Body   ChannelId   TransCode   ReqJnlNo   TransDate   TransTime  这些字段是必须的
		//ChannelId:商户ID TransCode:交易码   ReqJnlNo:交易流水   TransDate:交易日期   TransTime:交易时间   memo:交易描述   AcNo:账号
		String transInfo = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

		transInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><LZYH><Message id=\"Message\"><Head><TransCode></TransCode><ChannelId></ChannelId><ReqJnlNo>000000</ReqJnlNo>"
				+ "<TransDate>"
				+ sdf1.format(cal.getTime())
				+ "</TransDate>"
				+ "<TransTime>"
				+ sdf2.format(cal.getTime())
				+ "</TransTime></Head>"
				+ "<Body><IdNo>"
				+ idNo
				+ "</IdNo><AcNo>"
				+ acNo
				+ "</AcNo>"
				+ "<AcName>"
				+ acName
				+ "</AcName>"
				+ "<AcNoFlag>"
				+ acNoFlag
				+ "</AcNoFlag>"
				+ "<FundCardFlag>"
				+ fundCardFlag
				+ "</FundCardFlag>" + "<TrsPassword>" + trsPassword + "</TrsPassword>" + "</Body></Message></LZYH>";

		return transInfo;
	}

	public String send(String reqXml, String postUrl) {
		// 发送报文
		HttpClient httpClient = new HttpClient(connectionManager);
		//构建PostMethod实例
		PostMethod method = new PostMethod(postUrl);
		//附加上一次请求的cookie信息，也就是Sessionid
		if (null != cookie) {
			httpClient.getState().addCookie(cookie);
		}

		method.addRequestHeader("Content-Type", "text/xml; charset=utf-8");
		try {
			method.setRequestEntity(new StringRequestEntity(reqXml, null, "utf-8"));
			httpClient.executeMethod(method);
			Cookie cookies[] = httpClient.getState().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				String name = cookies[i].getName();
				if ("JSESSIONID".equals(name)) {
					System.out.println("SessionId=" + cookies[i].getValue());
				}
			}

			// 获得返回报文
			String resXml = method.getResponseBodyAsString();
			return resXml;
		} catch (Exception e) {
			// 根据需要自行处理日志
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	public HttpClientMessageSender() {
		super();

		//创建一个线程安全的HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		// 连接建立超时
		params.setConnectionTimeout(10000);
		// 数据等待超时
		params.setSoTimeout(30000);
		// 默认每个Host最多10个连接
		params.setDefaultMaxConnectionsPerHost(10);
		// 最大连接数（所有Host加起来）
		params.setMaxTotalConnections(200);

		connectionManager.setParams(params);
	}

	public Map sign(String transInfo) throws Exception {

		StringBuffer tmp = new StringBuffer(transInfo);
		Map signedData = new HashMap();
		String signedXml = null;
		String xmlData = tmp.toString();
		signedXml = sign1(xmlData);
		signedData.put("SignedXmlData", signedXml);
		return signedData;
	}

	public String sign1(String xmlPlain) throws Exception {

		Document doc = String2Doc(xmlPlain);
		XMLSignature sign = new XMLSignature(doc, doc.getDocumentURI(), XMLSignature.ALGO_ID_SIGNATURE_RSA);

		sign.getSignedInfo().addResourceResolver(new OfflineResolver());

		Node tagNode = doc.getElementsByTagName("LZYH").item(0);
		tagNode.appendChild(sign.getElement());

		Transforms transforms = new Transforms(doc);
		transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);

		Node tagNode1 = doc.getElementsByTagName("Message").item(0);
		String plainTagId = tagNode1.getAttributes().getNamedItem("id").getNodeValue();
		sign.addDocument("#" + plainTagId, transforms, "http://www.w3.org/2000/09/xmldsig#sha1");

		//直接读取证书文件获得PrivateKey,不从证书容器获取  Add By YZJ
		KeyReader keyReader = new KeyReader();
		System.out.println("wang----------");
		// 注意这里java在读取文件路径中有空格的情况下是会抛异常
		PrivateKey priKey = keyReader.readPrivateKeyfromPKCS12StoredFile(certPath, certPassword);

		System.out.println("ping--------");
		//签名
		sign.sign(priKey);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLUtils.outputDOM(doc, os);

		return os.toString("utf-8");
	}

	private Document String2Doc(String xml) throws Exception {
		InputStream is = IOUtils.toInputStream(xml, "UTF-8");
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		return docBuilder.parse(is);
	}

	public boolean verify(String xmlData) {
		boolean verifyResult = false;
		File f = new File("E:/lzyh/lzyh.cer");
		CertificateFactory cf;
		PublicKey pk;
		try {
			cf = CertificateFactory.getInstance("X.509");
			InputStream is = new FileInputStream(f);
			X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
			pk = cert.getPublicKey();
			verifyResult = verify(xmlData, pk);
		} catch (Exception e) {

		}

		return verifyResult;
	}

	public boolean verify(String xml, PublicKey pubKey) throws Exception {

		Document doc = String2Doc(xml);

		Element nscontext = XMLUtils.createDSctx(doc, "ds", Constants.SignatureSpecNS);

		Element signElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]", nscontext);

		if (signElement == null) {
			return false;
		}

		XMLSignature signature = new XMLSignature(signElement, doc.getDocumentURI());

		return signature.checkSignatureValue(pubKey);

	}

	/**
	 * 生成密钥
	 * @return
	 * @throws Exception
	 */
	public SecretKey creatDES() throws Exception {
		SecretKey secretKey = new SecretKeySpec(threeDesKey.getBytes(), ALGORITHM);
		return secretKey;
	}

	/**
	 * 3des加密
	 * @return
	 * @throws Exception
	 */
	public String desEncrypt(String srcData, SecretKey dESKey) throws Exception {
		//根据提供的密钥规范（密钥材料）生成 SecretKey 对象。
		key = dESKey;
		//3des
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] data = srcData.getBytes("UTF8");

		byte[] encryptedData = cipher.doFinal(data);

		String enOut = byteArr2HexStr(encryptedData);
		System.out.println("enOut======" + enOut);
		return enOut;
	}

	/**
	 * 3des解密
	 * @return
	 * @throws Exception
	 */
	public String desDecrypt(String srcData, SecretKey dESKey) throws Exception {

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, dESKey);

		byte[] data = hexstr2ByteArr(srcData);

		byte[] decryptedData = cipher.doFinal(data);

		String out = new String(decryptedData, "UTF8");
		System.out.println("out=======" + out);
		return out;
	}

	public String byteArr2HexStr(byte[] arrB) throws IOException {
		int iLen = arrB.length;

		StringBuffer sb = new StringBuffer(iLen * 2);

		for (int i = 0; i < iLen; ++i) {
			int intTmp = arrB[i];

			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}

			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}

		return sb.toString();
	}

	public byte[] hexstr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();

		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];

		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);

			arrOut[(i / 2)] = (byte) Integer.parseInt(strTmp, 16);
		}

		return arrOut;
	}
}