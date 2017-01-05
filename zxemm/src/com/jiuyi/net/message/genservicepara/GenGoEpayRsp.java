package com.jiuyi.net.message.genservicepara;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
/**
 * 生成请求三维易付
 * @author zhangb
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GenGoEpayRsp implements Serializable {
	private static final long serialVersionUID = -8754511449354397960L;
	/** 响应码。说明：0000为正常，否则为错误 */
	private String retcode;
	/** 错误提示。说明Retcode为错误的时候出现错误提示 */
	private String retshow;
	private String formText; //提交支付网关的form表单
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetshow() {
		return retshow;
	}
	public void setRetshow(String retshow) {
		this.retshow = retshow;
	}
	public String getFormText() {
		return formText;
	}
	public void setFormText(String formText) {
		this.formText = formText;
	}
}