package com.jiuyi.jyplat.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Utils {

	static Logger log = Logger.getLogger(Utils.class);

	/**
	 * 根据帐号判断帐号类型
	 * @param voucherno
	 * @return
	 */
	public static String getVouchertypenopkg(String voucherno) {
		String vouchertypeno = voucherno.substring(0, 3);

		if (Constant.SYS_BANK_TYPE.trim().equalsIgnoreCase("gl")) {// 开户接口桂林与泰安不一样
			if (vouchertypeno.substring(0, 3).equals("622")) {
				return "02";
			} else {
				return "01";
			}
		} else if (Constant.SYS_BANK_TYPE.trim().equalsIgnoreCase("ta")) {
			if (vouchertypeno.substring(0, 3).equals("622")) {
				return "2";
			} else {
				return "1";
			}
		}
		return null;
	}

	/**
	 * @param encryptStr
	 * @return
	 */
	public static String md5Encrypt(String encryptStr) {
		try {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			byte[] strTemp = encryptStr.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");

			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest(); // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16
			// 个字节
			int j = md.length;
			char str[] = new char[j * 2]; // j 为32时 每个字节用32进制表示的话，使用两个字符 所以表示成32
			// 进制需要 64 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < j; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				byte b = md[i];
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>>
				// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[b & 0xf]; // 取字节中低 4 位的数字转换
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	/**
	 * 实现Blob对象向十六进制的转换
	 */
	public static byte[] blob2Byte(Blob blob) throws Exception {
		byte[] data;
		BufferedInputStream inputStream = new BufferedInputStream(blob.getBinaryStream());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		data = new byte[(int) blob.length()];
		int len = 0;
		while ((len = inputStream.read(data)) != -1) {
			outputStream.write(data, 0, len);
		}
		return outputStream.toByteArray();
	}

	/**
	 * 计算两个日期内相隔的天数
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {
		// 首先定义一个calendar，必须使用getInstance()进行实例化
		Calendar aCalendar = Calendar.getInstance();
		// 里面野可以直接插入date类型
		aCalendar.setTime(fDate);
		// 计算此日期是一年中的哪一天
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(oDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		// 求出两日期相隔天数
		int days = day2 - day1;
		return days;
	}

	/*
	 * 实现Blob对象向十六进制的转换
	 */
	public static String blob2HexStr(Blob blob) throws Exception {
		byte[] result;
		StringBuffer buffer = new StringBuffer();
		result = blob2Byte(blob);
		for (int i = 0; i < result.length; ++i) {
			String s = Integer.toHexString((result[i]) & 0xFF);
			if (s.length() == 1) {
				buffer.append("0" + s);
			} else {
				buffer.append(s);
			}
		}

		return buffer.toString();
	}

	/*
	 * 实现Byte[]数组向十六进制的转换
	 */
	public static String byte2HexStr(byte[] result) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < result.length; ++i) {
			String s = Integer.toHexString((result[i]) & 0xFF);
			if (s.length() == 1) {
				buffer.append("0" + s);
			} else {
				buffer.append(s);
			}
		}
		return buffer.toString();
	}

	/**
	 * 16进制字符串转 byte数组
	 * 
	 * @param strIn
	 * @return
	 */
	public static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**判断手机号
	 * 国家号码段分配如下：
	移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	联通：130、131、132、152、155、156、185、186
	电信：133、153、180、189、（1349卫通）
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNo(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static boolean createDirectory(String path) {
		File dirFile = null;
		boolean creadok = false;
		try {
			dirFile = new File(path);
			System.out.println(dirFile.getAbsolutePath());
			if (dirFile.exists() && dirFile.isDirectory()) {
				return true;
			}

			if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
				creadok = dirFile.mkdirs();
			}
		} catch (Exception e) {
			return false;
		}
		return creadok;
	}

	public static boolean notEmptyString(String str) {
		if (str == null || str.trim().equals(""))
			return false;
		return true;
	}

	public static Map ckedit() {

		Map ckMap = new HashMap();

		Map<String, String> attr = new HashMap<String, String>();
		attr.put("rows", "8");
		attr.put("cols", "50");

		ckMap.put("attr", attr);

		// CKEditorConfig settings = new CKEditorConfig();
		// List<Object> mainList = new ArrayList<Object>();
		// HashMap<String, Object> toolbarSectionMap = new HashMap<String,
		// Object>();
		// List<String> subList = new ArrayList<String>();
		// subList.add("Source");
		// subList.add("-");
		// subList.add("NewPage");
		// toolbarSectionMap.put("name", "document");
		// toolbarSectionMap.put("items", subList);
		// mainList.add(toolbarSectionMap);
		// mainList.add("/");
		//		
		// toolbarSectionMap = new HashMap<String, Object>();
		// subList = new ArrayList<String>();
		// subList.add("Styles");
		// subList.add("Format");
		// toolbarSectionMap.put("name", "styles");
		// toolbarSectionMap.put("items", subList);
		// mainList.add(toolbarSectionMap);
		// settings.addConfigValue("toolbar","MyToolbar");
		//		
		// ckMap.put("settings", settings);
		//		
		return ckMap;
	}

	// 生成网关通信交易流水号
	public synchronized static int getTranSeqNo() {
		int iTranSeq = 0;
		if (iTranSeq >= 10000)
			iTranSeq = 0;
		return iTranSeq++;
	}

	public static void main(String[] args) throws Exception {
		String a = "111111";
		// System.out.println(a.length());
		System.out.println(md5Encrypt(a));

	}

	/**
	 * 将字符串转换为UTF字节数组
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @return 转换后生成的字节数组
	 */
	public static byte[] String2UByte(String s) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream bos = new DataOutputStream(baos);
			bos.writeUTF(s);

			byte[] bytes = baos.toByteArray();
			// 关闭流
			bos.close();
			baos.close();

			// 2 byte
			if (bytes != null) {
				int iretlen = bytes.length - 2;
				byte[] retbyte = new byte[iretlen];
				for (int i = 0; i < iretlen; i++) {
					retbyte[i] = bytes[2 + i];
				}
				return retbyte;
			}
			return bytes;
		} catch (Exception e) {
			// debug ("String2UByte Error= "+e.toString() );
			System.out.println("String2UByte Error= " + e.toString());
			return null;
		}

	}

	/**
	 * 去空
	 * @param arg
	 * @return String
	 */
	public static String trim(Object arg) {
		return null == arg ? "" : arg.toString().trim();
	}

	/**
	 * double 去小数,转Int
	 * @return
	 */
	public static String double2Int(double d) {
		try {
			DecimalFormat df = new DecimalFormat("#");
			return df.format(d);
		} catch (Exception e) {
			log.debug("------------------double[" + d + "]去小数失败------------------");
			return "0";
		}
	}

	/**
	 * 验证字符串是否全是数字
	 * @param numberStr
	 * @return
	 */
	public static boolean isNumber(String numberStr) {
		String valid = "0123456789";
		String temp = "";
		boolean flag = true;
		if (numberStr == null || numberStr.equals("") || numberStr.equals("null")) {
			flag = false;
		} else {
			for (int i = 0; i < numberStr.length(); i++) {
				temp = "" + numberStr.substring(i, i + 1);
				if (valid.indexOf(temp) == -1) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * 发送短信内容逻辑判断处理
	 * 
	 * @param contMap
	 * @return String
	 * @throws Exception
	 */
	public static String shortMsgContPro(HashMap contMap) {
		String msgContent = null;

		// 模板
		String mouldCont = contMap.get("mouldCont").toString();

		// 来源渠道 002 POS, 005 积分商城, 003 小终端
		String tranChannel = contMap.get("tranChannel").toString();
		String custName = contMap.get("customerName") == null ? "" : contMap.get("customerName").toString();
		String pointsBalance = contMap.get("pointsBalance") == null ? "" : contMap.get("pointsBalance").toString();
		String voucherNo = contMap.get("voucherNo") == null ? "" : contMap.get("voucherNo").toString();
		String voucherTypeNo = contMap.get("voucherTypeNo") == null ? "" : contMap.get("voucherTypeNo").toString();
		String custinfoName = contMap.get("custinfoName") == null ? "" : contMap.get("custinfoName").toString();
		custinfoName = custinfoName.trim().equals("") ? "" : ("(" + custinfoName + ")");

		if (tranChannel.trim().equals(Constant.SYS_CHANNEL_POS)) {
			String v1 = "";
			if (voucherTypeNo.toString().trim().equals(Constant.SYS_VOUCHERTYPE_CUST) || mouldCont.startsWith("尊敬"))
				v1 = custName;
			else {
				v1 = voucherNo.substring(voucherNo.length() - 4);
			}

			msgContent = mouldCont.replace("%1", v1).replace("%2", contMap.get("dateTime").toString()).replace("%3",
					"POS" + custinfoName).replace("%4", contMap.get("pointsAmount").toString()).replace("%5",
					pointsBalance);

			if (v1.trim().equals(""))
				msgContent = msgContent.replace("在POS", "").replace("您尾号的卡", "您");
		} else if (tranChannel.trim().equals(Constant.SYS_CHANNEL_MALL)) {
			if (custName.trim().equals(""))// 如果客户名称为空, 则有可能是从支付宝过来的银行卡快捷支付中集分宝交易,将卡号放入%1
				custName = voucherNo;
			msgContent = mouldCont.replace("%1", custName).replace("%2", contMap.get("dateTime").toString()).replace(
					"%3", "网上" + custinfoName) // 如果商户名称不为空, 则加上商户名称
					.replace("%4", contMap.get("pointsAmount").toString()).replace("%5", pointsBalance);

		} else if (tranChannel.trim().equals(Constant.SYS_CHANNEL_DEV)) {
			// 暂时与商城保持一致
			msgContent = mouldCont.replace("%1", custName).replace("%2", contMap.get("dateTime").toString()).replace(
					"%3", "网上").replace("%4", contMap.get("pointsAmount").toString()).replace("%5", pointsBalance);
		}

		return msgContent;
	}

	/**
	 * 桂林的积分类型
	 * '00%'，'02%'，'03%'，'04%'开 综合积分账户 0001
		'66%'开 藤县桂银积分账户 0003
		'67%'开 横县桂商积分账户 0004 
		'68%'开 兴安民兴积分账户 0005
		'69%'开 容县桂银积分账户 0006
		'70%'开 宝安桂银积分账户 0007
		'71%'开 桂平桂银积分账户 0008
		'72%'开 平南桂银积分账户 0009
	 */
	public static String pointsType4GL(String instNo) {
		String pointsTypeNo = "0001";
		if (instNo != null && !instNo.trim().equals("")) {
			if (instNo.startsWith("00") || instNo.startsWith("02") || instNo.startsWith("03")
					|| instNo.startsWith("04"))
				pointsTypeNo = "0001";
			else if (instNo.startsWith("66"))
				pointsTypeNo = "0003";
			else if (instNo.startsWith("67"))
				pointsTypeNo = "0004";
			else if (instNo.startsWith("68"))
				pointsTypeNo = "0005";
			else if (instNo.startsWith("69"))
				pointsTypeNo = "0006";
			else if (instNo.startsWith("70"))
				pointsTypeNo = "0007";
			else if (instNo.startsWith("71"))
				pointsTypeNo = "0008";
			else if (instNo.startsWith("72"))
				pointsTypeNo = "0009";
		}
		return pointsTypeNo;
	}

	/**
	 * 
	 * <p>判断参数的数据值是否为空</p>
	 * @param args 1-N个字符串参数
	 * @return 如果有为空的参数，则返回true，否则返回false
	 * @author 郭玉壮
	 */
	public static boolean isNullData(String... args) {
		for (int i = 0; i < args.length; i++) {
			if (null == args[i] || args[i].trim().equals(""))
				return true;
		}
		return false;
	}

	/**
	 * 桂林的客户等级
	 * 	前8位为62145609 62145606 白金卡 客户等级设定为2
		前8位为62145608 62145605 金卡 客户等级设定为1
		其他卡号或凭证号默认客户等级为 0
	 * @param voucherNo
	 * @return custLvl
	 */
	public static String custLvl4GL(String voucherNo) {
		String custLvl = "0";
		if (voucherNo != null && !voucherNo.trim().equals("")) {
			if (voucherNo.startsWith("62145609") || voucherNo.startsWith("62145606")) {
				custLvl = "2";
			} else if (voucherNo.startsWith("62145608") || voucherNo.startsWith("62145605"))
				custLvl = "1";
		}
		return custLvl;
	}
}
