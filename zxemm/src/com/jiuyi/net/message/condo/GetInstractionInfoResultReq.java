package com.jiuyi.net.message.condo;

import java.io.Serializable;

public class GetInstractionInfoResultReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String buiding; //地幢号
	
	private String instruction;//指令类型  1：开户操作   2：划款出账操作  3： 购房入账通知  4： 账户变更通知

	public String getBuiding() {
		return buiding;
	}

	public void setBuiding(String buiding) {
		this.buiding = buiding;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
}
