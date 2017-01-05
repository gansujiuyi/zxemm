/**
 * 
 */
package com.jiuyi.jyplat.entity.transport;


/**
 * 打款流水相关枚举
 * @author Administrator
 *
 */
public enum PayTransportEnum {
	/**
	 * 初始化，待支付
	 */
	status_begin("0000"),
	
	/**
	 * 支付中
	 */
	status_transIng("0001"),
	
	/**
	 * 支付成功
	 */
	status_transSuccess("0002"),
	
	/**
	 * 支付失败
	 */
	status_transFail("0003"),
	
	/**
	 * 支付异常
	 */
	status_transAbnormal("0004"),
	
	/**
	 * 打款给卖方，即交易完成进行支付。
	 */
	money_to_seller ("1001"),
	
	/**
	 * 打款给买方，即交易失败进行退款。
	 */
	money_to_buyer ("1002");
	
	String value;
	
	PayTransportEnum(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
