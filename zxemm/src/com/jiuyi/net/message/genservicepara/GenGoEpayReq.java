package com.jiuyi.net.message.genservicepara;
import java.io.Serializable;
public class GenGoEpayReq implements Serializable {
	private static final long serialVersionUID = 1399338355867661142L;
	private String memberno; //会员号
	private String tokenid;  //会员登录令牌
	private String transChannel; //pc-电脑，mb-移动互联网
	private String reqSerivceName;; //请求服务名，对应三维易付对应的功能服务名称
	private String attach;         //附加数据，用户扩展接口
	public String getMemberno() {
		return memberno;
	}
	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}
	public String getTokenid(){
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getTransChannel() {
		return transChannel;
	}
	public void setTransChannel(String transChannel) {
		this.transChannel = transChannel;
	}
	public String getReqSerivceName() {
		return reqSerivceName;
	}
	public void setReqSerivceName(String reqSerivceName) {
		this.reqSerivceName = reqSerivceName;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
}
