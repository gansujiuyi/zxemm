package com.jiuyi.jyplat.util;

public class OperConstant {
	// 操作类型表
	//	id operNo    desc      父类
	//	1  001        操作类型     0
	//	2  001001     操作类型    01

	// 操作动作表
	//  id operNo   desc
	//	1  001       新增           

	/*****************************************/
	/**************** 操作大类 ****************/
	/*****************************************/
	// 操作类型 . 系统管理
	public static final String TYPE_BASE = "001";
	// 操作类型 . 积分管理
	public static final String TYPE_POINT = "002";
	// 操作类型 . 商品管理
	public static final String TYPE_GOODS = "003";
	/*****************************************/
	/**************** 操作大类 ****************/
	/*****************************************/

	/*****************************************/
	/**************** 操作小类 ****************/
	/*****************************************/

	/*****************系统管理*****************/
	// 操作类型 . 系统管理 . 枚举维护
	public static final String TYPE_BASE_ENUM = "1001";
	// 操作类型 . 系统管理 . 枚举明细维护
	public static final String TYPE_BASE_ENUMITEM = "1002";
	// 操作类型 . 系统管理 . 组织机构维护
	public static final String TYPE_BASE_INST = "1003";
	// 操作类型 . 系统管理 . 角色维护
	public static final String TYPE_BASE_ROLE = "1004";
	// 操作类型 . 系统管理 . 角色功能关联
	public static final String TYPE_BASE_ROLEFUN = "1005";
	// 操作类型 . 系统管理 . 操作员维护
	public static final String TYPE_BASE_OPER = "1006";
	// 操作类型 . 系统管理 . 操作员审核
	public static final String TYPE_BASE_AUTHOPER = "1007";
	// 操作类型 . 系统管理 . 功能维护
	public static final String TYPE_BASE_FUNCTION = "1008";
	// 操作类型 . 系统管理 . 功能action关联
	public static final String TYPE_BASE_FUNCACTION = "1009";
	// 操作类型 . 系统管理 . action维护
	public static final String TYPE_BASE_ACTION = "1010";
	// 操作类型 . 系统管理 . 积分类型维护
	public static final String TYPE_BASE_POINTTYPE = "1011";
	// 操作类型 . 系统管理 . 凭证类型维护
	public static final String TYPE_BASE_VOUCHERTYPE = "1012";
	// 操作类型 . 系统管理 . 交易类型维护
	public static final String TYPE_BASE_TRANSTYPE = "1013";
	// 操作类型 . 系统管理 . 报表维护(add by lzb on 2012/06/19)
	public static final String TYPE_BASE_REPORT = "1014";

	// 操作类型 . 系统管理 . 终端设备维护
	public static final String TYPE_BASE_DEVICE = "1014";

	/*****************积分管理*****************/
	// 操作类型 . 积分管理 . 积分规则维护
	public static final String TYPE_POINT_RULE = "2001";
	// 操作类型 . 积分管理 . 积分账户维护
	public static final String TYPE_POINT_ACCOUNT = "2002";
	// 操作类型 . 积分管理 . 积分账户明细维护
	public static final String TYPE_POINT_ACCDETAIL = "2003";
	// 操作类型 . 积分管理 . 积分账户开户
	public static final String TYPE_POINT_CREATEACC = "2004";
	// 操作类型 . 积分管理 . 关联凭证维护
	public static final String TYPE_POINT_VOUCHER = "2005";
	// 操作类型 . 积分管理 . 客户信息维护
	public static final String TYPE_POINT_CUSTOMER = "2006";
	// 操作类型 . 积分管理 . 积分交易分类
	public static final String TYPE_POINT_TRANSTYPE = "2007";
	// 操作类型 . 积分管理 . 积分交易流水
	public static final String TYPE_POINT_TRANSDETAIL = "2008";
	// 操作类型 . 积分管理 . 积分记账贷入
	public static final String TYPE_POINT_CREDIT = "2009";
	// 操作类型 . 积分管理 . 积分记账借出
	public static final String TYPE_POINT_DEBIT = "2010";
	// 操作类型 . 积分管理 . 积分记账冻结
	public static final String TYPE_POINT_FREEZING = "2011";

	/*****************商品管理*****************/
	// 操作类型 . 商品管理 . 商户操作员维护
	public static final String TYPE_BUSINESS_CUSTOPER = "3001";
	// 操作类型 . 商品管理 . 订单维护
	public static final String TYPE_BUSINESS_ORDERINFO = "3002";
	// 操作类型 . 商品管理 . 供应商维护
	public static final String TYPE_BUSINESS_SUPPLIER = "3003";
	// 操作类型 . 商品管理 . 商户维护
	public static final String TYPE_BUSINESS_CUSTINFO = "3004";
	// 操作类型 . 商品管理 . 商品维护
	public static final String TYPE_BUSINESS_GOODSINFO = "3005";
	// 操作类型 . 商品管理 . 商户网点维护
	public static final String TYPE_BUSINESS_CUSTSTORE = "3006";
	// 操作类型 . 商户维护 . 物流承运商维护
	public static final String TYPE_BUSINESS_FASTCONFIGURE = "3007";

	/*****************************************/
	/**************** 操作小类 ****************/
	/*****************************************/

	/*****************************************/
	/**************** 操作动作 ****************/
	/*****************************************/
	public static final String ACT_ADD = "001";
	public static final String ACT_EDIT = "002";
	public static final String ACT_DEL = "003";
	/*****************************************/
	/**************** 操作动作 ****************/
	/*****************************************/

}
