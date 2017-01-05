package com.jiuyi.jyplat.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SMSUtil {

	private List<String> vars;// 变量集合

	static Logger log = Logger.getLogger(SMSUtil.class);

	public SMSUtil() {
		vars = new ArrayList<String>();
		vars.add(SMSVariables.ACCNO);
		vars.add(SMSVariables.AUTHCODE);
		vars.add(SMSVariables.BALANCE);
		vars.add(SMSVariables.CNAME);
		vars.add(SMSVariables.DAY);
		vars.add(SMSVariables.GOODSNAME);
		vars.add(SMSVariables.HOUR);
		vars.add(SMSVariables.LOGINNAME);
		vars.add(SMSVariables.MINUTE);
		vars.add(SMSVariables.MONTH);
		vars.add(SMSVariables.POINT);
		vars.add(SMSVariables.YEAR);
	}

	/**
	 * 将短信模板转换成短信
	 */
	public String template2sms(String smsTemplStr, SmsTemplateVar entity) {
		try {
			for (String key : vars) {
				while (smsTemplStr.indexOf(key.trim()) >= 0) {// 如果短信模板中有这个参数
					String fild = key;
					String msg_v = findVal(fild, entity);

					if (msg_v == null) {
						smsTemplStr = smsTemplStr.replace(key, "");
					} else {
						smsTemplStr = smsTemplStr.replace(key, msg_v);
					}
				}
			}
			log.debug("转换后的短信：" + smsTemplStr);
		} catch (Exception e) {
			log.error("给短信模板填充参数时出错" + e);
			log.error("短信模板填充出错，填充后的短信内容：" + smsTemplStr);
			e.printStackTrace();
			return null;
		}
		return smsTemplStr;
	}

	/**
	 * 将变量转换为值
	 * @param str 
	 * @param entity
	 * @return
	 */
	private String findVal(String str, SmsTemplateVar entity) {
		if (str == null || str.trim().equals("")) {
			return "";
		}
		str = str.trim();
		if (SMSVariables.ACCNO.equals(str)) {
			return entity.getAccno().trim();
		}
		if (SMSVariables.AUTHCODE.equals(str)) {
			return entity.getAuthcode().trim();
		}
		if (SMSVariables.BALANCE.equals(str)) {
			return entity.getBalance().trim();
		}
		if (SMSVariables.CNAME.equals(str)) {
			return entity.getCname();
		}
		if (SMSVariables.DAY.equals(str)) {
			return entity.getDay();
		}
		if (SMSVariables.GOODSNAME.equals(str)) {
			return entity.getGoodsname();
		}
		if (SMSVariables.HOUR.equals(str)) {
			return entity.getHour();
		}
		if (SMSVariables.LOGINNAME.equals(str)) {
			return entity.getLoginname();
		}
		if (SMSVariables.MINUTE.equals(str)) {
			return entity.getMinute();
		}
		if (SMSVariables.MONTH.equals(str)) {
			return entity.getMonth();
		}
		if (SMSVariables.POINT.equals(str)) {
			return entity.getPoint();
		}
		if (SMSVariables.YEAR.equals(str)) {
			return entity.getYear();
		} else {
			return "";
		}
	}
}
