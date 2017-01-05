/**
 * @{#} MsgRetCode.java Create on 2011年8月9日
 * 前置机接口响应码定义
 * @author Huangwj & Wul
 * @version 1.0
 * Copyright @ 2009 - 2011  
 */
package com.jiuyi.net.message;

public class MsgRetCode {

	/** 交易成功 */
	public static final String Trans_Success_Code = "0000";
	/** 交易成功提示 */
	public static final String Trans_Success_Show = "交易成功";

	/** 数据查询操作失败。进行数据库查询操作时，若失败，则出现该提示 */
	public static final String Database_Query_Code = "0001";
	
	/** 数据查询已经存在。进行数据库查询操作时，若信息已存在，则出现该提示 */
	public static final String Database_Code = "0111";
	/** 数据查询操作失败失败 */
	public static final String Database_Query_Show = "数据库查询操作失败";
	
	/** 数据查询操作信息已存在 */
	public static final String Database_Show = "信息已存在不能重复添加";

	/** 无此客户记录 。根据凭证类型、凭证号，查询客户表，若无记录，则出现该提示 */
	public static final String Customer_Null_Code = "0002";
	/** 无此客户记录提示 */
	public static final String Customer_Null_Show = "客户信息不存在";

	/** 无此客户的积分账户信息。根据客户号，查询积分账户表，若无记录，则出现该提示 */
	public static final String PointsAcc_Null_Code = "0003";
	/** 无此客户的积分账户信息 */
	public static final String PointsAcc_Null_Show = "客户积分账户信息不存在";

	/** 无此机构信息。根据积分类型，查询积分类型表，若无记录，则出现该提示 */
	public static final String Institution_Null_Code = "0004";
	/** 无此机构信息 */
	public static final String Institution_Null_Show = "机构信息不存在";

	/** 原交易不存在。当根据系统跟踪号查询不到相关交易时，则出现该提示 */
	public static final String OldTrans_NotExist_Code = "0005";
	/** 原交易不存在 */
	public static final String OldTrans_NotExist_Show = "原交易不存在 ";

	/** 自动开户失败。当请求银行接口自动开户时产生错误，则出现该提示 */
	public static final String AutoOpenAcc_Fail_Code = "0006";
	/** 自动开户失败 */
	public static final String AutoOpenAcc_Fail_Show = "自动开户失败";

	/** 无法自动开户。凭证类型为C01且凭证号码以62开头才能自动开户 */
	public static final String AutoOpenAcc_Error_Code = "0007";
	/** 无法自动开户 */
	public static final String AutoOpenAcc_Error_Show = "凭证类型和凭证号码不匹配";

	/** 金额格式错误。若请求信息中的金额格式有问题，则出现该提示 */
	public static final String Amount_Format_Code = "0010";
	/** 金额格式错误提示 */
	public static final String Amount_Format_Show = "金额格式错误";

	/** 请求报文数据错误。当请求报文头、请求报文中的必输项数据为空时，则出现该提示 */
	public static final String Requset_Format_Code = "0011";
	/** 请求报文数据错误 */
	public static final String Requset_Format_Show = "请求报文数据不完整";

	/** 日期格式错误。若请求信息中的日期格式有问题，则出现该提示 */
	public static final String Date_Format_Code = "0012";
	/** 日期格式错误提示 */
	public static final String Date_Format_Show = "日期格式应为yyyyMMdd";

	/** 手续费格式错误。若请求信息中的手续费格式有问题，则出现该提示 */
	public static final String Fee_Format_Code = "0013";
	/** 手续费格式错误提示 */
	public static final String Fee_Format_Show = "手续费格式错误";

	/** 查询页码格式错误。若请求报文中的查询页码格式有问题，则出现该提示 */
	public static final String Index_Format_Code = "0014";
	/** 查询页码格式错误 */
	public static final String Index_Format_Show = "查询页码格式错误";

	/** 页大小格式错误。当请求报文中的每页显示记录数的页大小格式错误时，则出现该提示 */
	public static final String PageSize_Format_Code = "0015";
	/** 页大小格式错误 */
	public static final String PageSize_Format_Show = "页大小格式错误";

	/** 格式错误。当请求报文中的的格式错误时，则出现该提示 */
	public static final String Format_Error_Code = "0016";
	/** 数格式错误 */
	public static final String Format_Error_Show = "格式错误";

	/** 借贷标志错误。当请求交易既不是贷入、也不是借出时，出现该提示 */
	public static final String Trans_Flag_Code = "0021";
	/** 借贷标志错误 */
	public static final String Trans_Flag_Show = "借贷标志错误";

	/** 交易失败。各种调用交易方法时抛异常，则出现该提示 */
	public static final String Trans_Error_Code = "0031";
	/** 交易失败 */
	public static final String Trans_Error_Show = "交易时发生异常";
	/** 系统/数据错误。积分账户可用积分不足 */
	public static final String ERROR_TRANS_SYS_001 = "积分账户可用积分不足";
	/** 系统/数据错误。积分账户不存在 */
	public static final String ERROR_TRANS_SYS_002 = "积分账户不存在";
	/** 系统/数据错误。积分账户明细数据不统一 */
	public static final String ERROR_TRANS_SYS_003 = "积分账户明细数据不统一";
	/** 系统/数据错误。积分账户非正常状态 */
	public static final String ERROR_TRANS_SYS_004 = "积分账户非正常状态";
	/** 人为操作/数据输入错误。交易的积分不能小于0 */
	public static final String ERROR_TRANS_MANUAL_001 = "交易的积分不能小于0";
	/** 人为操作/数据输入错误。无对应的数据 */
	public static final String ERROR_TRANS_MANUAL_002 = "无对应的数据";
	/** 人为操作/数据输入错误。数据格式错误 */
	public static final String ERROR_TRANS_MANUAL_003 = "数据格式错误";
	/** 人为操作/数据输入错误。商品不符合销售规则 */
	public static final String ERROR_TRANS_MANUAL_004 = "您不符合该商品的销售规则";

	/** 积分余额信息不存在。当贷入、借出交易成功后 ，查询最新的积分余额时无该记录，则出现该提示 */
	public static final String PointsBalance_Null_Code = "0032";
	/** 积分余额信息不存在 */
	public static final String PointsBalance_Null_Show = "积分余额信息不存在";

	/** 系统跟踪号已存在。当贷入、借出交易时，查询积分交易流水表，若已经存在系统跟踪号，则出现该提示 */
	public static final String SysTransNo_Exists_Code = "0033";
	/** 系统跟踪号已存在 */
	public static final String SysTransNo_Exists_Show = "系统跟踪号已存在";

	/** 冲正成功 */
	public static final String Reverse_Success_Code = "1001";
	/** 冲正成功 */
	public static final String Reverse_Success_Show = "冲正成功";

	/** 原交易不存在。根据系统跟踪号，查询原交易不存在，则出现该提示 */
	public static final String Reverse_NotExist_Code = "1002";
	/** 原交易不存在 */
	public static final String Reverse_NotExist_Show = "原交易不存在";

	/** 原交易已冲正。根据系统跟踪号，查询原交易已冲正，则出现该提示 */
	public static final String Reverse_Done_Code = "1003";
	/** 原交易已冲正 */
	public static final String Reverse_Done_Show = "原交易已冲正";

	/** 冲正失败。当冲正过程中发生问题，导致无法冲正成功 */
	public static final String Reverse_Fail_Code = "1004";
	/** 冲正失败 */
	public static final String Reverse_Fail_Show = "冲正失败";

	/** 银行综合积分编号。目前保存在数据库中的银行综合积分编号为0001。如果更改了编号，此处也需要更改 */
	public static final String Bank_Points_No = "0001";
	/** 分页查询时，每页显示的记录数。目前默认为10。如果更改了每页的记录数，此处也需要更改 */
	public static final int Page_Size = 10;
	/** 商品领取方式。当为该方式时，网点ID必填 */
	public static final String GetType_No = "1";

	/** 目前系统只支持银行综合积分，如果请求报文中不是银行综合积分，则出现该提示 */
	public static final String PointsTypeNo_Support_Code = "0041";
	/** 目前系统只支持银行综合积分 */
	public static final String PointsTypeNo_Support_Show = "目前系统只支持银行综合积分";

	/** 商品信息不存在。 当根据请求报文中的条件，查询不到商品信息时，则出现该提示 */
	public static final String GoodsInfo_Null_Code = "0042";
	/** 商品信息不存在 */
	public static final String GoodsInfo_Null_Show = "商品信息不存在或不在该渠道显示";

	/** 门户会员信息不存在。当根据查询不到门户会员信息时，则出现该提示 */
	public static final String MemberNo_Null_Code = "0051";
	/** 门户会员信息不存在 */
	public static final String MemberNo_Null_Show = "会员信息不存在";

	/** 无常用地址信息。当查询不到客户的常用地址信息时，则出现该提示 */
	public static final String Address_Null_Code = "0052";
	/** 无常用地址信息 */
	public static final String Address_Null_Show = "无常用地址信息";

	/** 订单信息为空。 当根据请求报文中的条件，查询不到订单信息时，则出现该提示 */
	public static final String OrderInfo_Null_Code = "0053";
	/** 订单信息为空 */
	public static final String OrderInfo_Null_Show = "订单信息为空";
	/** 订单信息为空 */
	public static final String OrderInfo_ORDER_Show = "订单客户信息与所给凭证号客户信息不一致";

	/** 地址信息为空。 当根据请求报文中的条件，查询不到地址信息时，则出现该提示 */
	public static final String UsualAddress_Null_Code = "0054";
	/** 地址信息为空 */
	public static final String UsualAddress_Null_Show = "地址信息为空";
	/** 目前不支持现金支付 */
	public static final String UnSupport_Cashs_Code = "0055";
	/** 目前不支持现金支付 */
	public static final String UnSupport_Cashs_Show = "目前不支持现金支付";

	/** 校验常用地址信息 **/
	public static final String ORDER_CHECK_Code = "0056";
	/** 支付类型为全积分支付，现金不允许支付 */
	public static final String ORDER_CHECK_CHSHS = "支付类型为全积分支付，现金不允许支付";
	/** 支付类型为全现金支付，积分不允许支付 **/
	public static final String ORDER_CHECK_PRICE = "支付类型为全现金支付，积分不允许支付";
	/** 积分总价数额不对 */
	public static final String ORDER_CHECK_CHSHS_ALL = "积分总价数额不对";
	/** 现金总价数额不对 **/
	public static final String ORDER_CHECK_PRICE_ALL = "现金总价数额不对";
	/** 积分总价数额与明细之和不一致 */
	public static final String ORDER_CHECK_CHSHS_SUM = "积分总价数额与明细之和不一致";
	/** 现金积分总价数额与明细之和不一致 **/
	public static final String ORDER_CHECK_PRICE_SUM = "现金积分总价数额与明细之和不一致";
	/** 库存不够 **/
	public static final String ORDER_CHECK_PRICE_LACK = "库存不够";

	/** 该订单号不存在 **/
	public static final String ORDER_CHECK_CUST = "该订单号不存在";
	/** 系统暂时不支持现金支付 **/
	public static final String ORDER_CHECK_PRICET = "系统暂时不支持现金支付";
	/** 该订单号非待支付 **/
	public static final String ORDER_CHECK_STATE = "该订单号非待支付";

	/** 订单明细信息为空。 当根据请求报文中的条件，查询不到订单明细信息时，则出现该提示 */
	public static final String OrderDetails_Null_Code = "0057";
	/** 订单明细信息为空 */
	public static final String OrderDetails_Null_Show = "订单明细信息为空";

	/** 地址ID不能为空。当请求报文中必输项地址ID为空时，则出现该提示 */
	public static final String UsualAddressNo_Null_Code = "0059";
	/** 地址ID不能为空 */
	public static final String UsualAddressNo_Null_Show = "地址ID不能为空";

	/** 商户信息不存在 */
	public static final String Cust_Null_Code = "0060";
	/** 商户信息不存在 */
	public static final String Cust_Null_Show = "商户信息不存在";

	/** 明细中金额格式错误。若商品明细或订单明细中，金额的格式错误，则出现该提示 */
	public static final String Detail_Amount_Error_Code = "0070";
	/** 明细中金额格式错误 */
	public static final String Detail_Amount_Error_Show = "明细中金额格式错误";

	/** 手机号不正确 */
	public static final String MobileNo_Error_Code = "0100";
	/** 手机号不正确 */
	public static final String MobileNo_Error_Show = "手机号不正确";
	/** 暂不支持短信认证码提货方式 */
	public static final String SMS_NotSupport_Code = "0101";
	/** 暂不支持短信认证码提货方式 */
	public static final String SMS_NotSupport_Show = "暂不支持短信认证码提货方式";

	/** 没有待发送短信 */
	public static final String SMS_Null_Code = "0102";
	/** 没有待发送短信 */
	public static final String SMS_Null_Show = "没有待发送短信";

	/** 网点Id为空 */
	public static final String StoreId_Null_Code = "0103";
	/** 网点Id为空 */
	public static final String StoreId_Null_Show = "网点Id为空";

	/** 创建活动时间错误 **/
	public static final String GAME_DATE_Error_CODE = "2001";
	public static final String GAME_DATE_Error_Show = "创建活动时间错误";
	/** 活动已经存在 **/
	public static final String GAME_DATE_Error_HAS_CODE = "2002";
	/** 活动已经存在 **/
	public static final String GAME_DATE_Error_HAS_SHOW = "活动已经存在";

	/** 抽奖数据不准确 **/
	public static final String GAME_ERROR_DATE_CODE = "2003";
	/** 抽奖数据不准确 **/
	public static final String GAME_ERROR_DATE_SHOW = "抽奖数据不准确";
	/** 抽奖活动不存在 **/
	public static final String GAME_ERROR_NONE_CODE = "2004";
	/** 抽奖活动不存在 **/
	public static final String GAME_ERROR_NONE_SHOW = "抽奖活动不存在";
	/** 抽奖活动已经开始 **/
	public static final String GAME_ERROR_BEGIN_CODE = "2005";
	/** 抽奖活动已经开始 **/
	public static final String GAME_ERROR_BEGIN_SHOW = "活动已经开始";
	/** 活动状态不对 **/
	public static final Object GAME_DATE_STAUTS_CODE = "2006";
	/** 活动状态不对 **/
	public static final Object GAME_DATE_STAUTS_SHOW = "活动状态不对";

	/** 此活动当前状态不能参与抽奖 **/
	public static final Object GAME_ADD_STAUTS_CODE = "2007";
	/** 此活动当前状态不能参与抽奖 **/
	public static final Object GAME_ADD_STAUTS_SHOW = "此活动当前状态不能参与抽奖";

	/** 活动已经存在 **/
	public static final String GAME_CUST_Error_NONE_CODE = "2011";
	/** 活动已经存在 **/
	public static final String GAME_CUST_Error_NONE_SHOW = "商户不存在";
	/** 活动已经存在 **/
	public static final String GAME_DATE_Error_NONE_CODE = "2012";
	/** 活动已经存在 **/
	public static final String GAME_DATE_Error_NONE_SHOW = "活动不存在";

	public static final String Success_Code = "0000";
	public static final String Eoor_Code = "0001";

	/** 凭证类型和凭证号码不能为空。当请求报文中必输项凭证类型、凭证号码为空时，则出现该提示 */
	public static final String Voucher_NullError_Code = "0058";
	public static final String Voucher_NullError_Show = "凭证类型和凭证号码不能为空";
	public static final String Vocuher_NO_NotEq = "凭证号不匹配";
	public static final String Vocuher_Type_NotEq = "凭证类型不匹配";
	public static final String Vocuher_OpenDate_NotEq = "凭证开户日期不匹配";
	public static final String Vocuher_Branch_NotEq = "凭证开户网点不匹配";
	public static final String Vocuher_NotExist = "凭证不匹配";
	public static final String Mdaddress_Add_NullError_Show = "凭证类型、凭证号码、省、市、市的id、区、详细地址、联系电话、收货人不能为空";

	public static final String POS_DATE_ERROR_CODE = "9998";
	public static final String POS_DATE_ERROR_SHOW = "不能识别的消息类型";
	public static final String POS_DATE_PWD_CODE = "9001";
	public static final String POS_DATE_PWD_SHOW = "帐号密码校验错误";

	public static final String SEND_CODE_FAIL = "发送短信失败";
	public static final String ECODE_VALIDATE_FAIL = "动态码认证失败";

	public static final String CARD_BIND_FALL_CODE = "cdbinfall";
	public static final String CARD_BIND_FALL_SHQW = "积分卡绑定失败";
	public static final String CARD_LOSS_FALL_CODE = "cdlossfall";
	public static final String CARD_LOSS_FALL_SHOW = "积分卡挂失失败";
	public static final String CARD_UPDATEPWD_FALL_CODE = "cdudpwdfall";
	public static final String CARD_UPDATEPWD_FALL_SHOW = "积分卡改密失败";
	public static final String CARD_UPDATEPWD_format_FALL_CODE = "cdudpwdfall";
	public static final String CARD_UPDATEPWD_format_FALL_SHOW = "积分卡密码长度错误，密码长度必须是6位数字";

	public static final String regOperate_fall_code = "regOperatefall";
	public static final String regOperate_fall_show = "签约成功后，查询客户信息异常";

	public static final String CUSTOPER_LOGIN_FALSE_CODE = "0301";
	public static final String CUSTOPER_LOGIN_FALSE_SHOW = "查询用户信息异常";

	public static final String USERNAME_DISABLED_CODE = "0302";
	public static final String USERNAME_DISABLED_SHOW = "账号不存在";

	public static final String PASSWORD_DISABLED_CODE = "0303";
	public static final String PASSWORD_DISABLED_SHOW = "密码错误";

	public static final String CUSTOPER_USELESS_CODE = "0304";
	public static final String CUSTOPER_USELESS_SHOW = "您的账号已经被锁定";

	public static final String MEMBER_LASTLOGIN_FAIL_CODE = "0305";
	public static final String MEMBER_LASTLOGIN_FAIL_SHOW = "设置会员最后登录时间失败";

	public static final String PASSWORD_PAY_DIS_CODE = "0306";
	public static final String PASSWORD_PAY_DIS_SHOW = "支付密码错误";

	public static final String CUSTOPER_LOCK__CODE = "0307";
	public static final String CUSTOPER_LOCK_SHOW = "您输入错误密码次数超过5次，账户被锁定";

	public static final String CUSTOPER_NOACTIVATION__CODE = "0308";
	public static final String CUSTOPER_NOACTIVATION_SHOW = "账户未激活";

	public static final String CUSTOPER_CANCELLATION__CODE = "0309";
	public static final String CUSTOPER_CANCELLATION_SHOW = "账户已注销";

	public static final String MEMBER_UPDATE__CODE = "0310";
	public static final String MEMBER_UPDATE_SHOW = "更新账户失败";

	public static final String MEMBER_PAY_PASSWORD__CODE = "0311";
	public static final String MEMBER_PAY_PASSWORD_SHOW = "支付密码输入超过3次，账户被锁定";

	public static final String OPER_SUCCESS_CODE = "0550";
	public static final String OPER_SUCCESS_SHOW = "操作成功";
	public static final String OPER_FAIL_CODE = "0551";
	public static final String OPER_FAIL_SHOW = "操作失败";
	public static final String OPER_EXCEPTION_CODE = "0552";
	public static final String OPER_EXCEPTION_SHOW = "操作发生异常";

	public static final String CODE_5005 = "5005";
	public static final String CODE_5005_SHOW = "验证码发送失败";
	
	public static final String CODE_6005 = "6005";
	public static final String CODE_6005_SHOW = "短信发送失败";

	public static final String CODE_5006 = "5006";
	public static final String CODE_5006_SHOW = "短信内容不能为空";

	public static final String SHIP_1001 = "1001";
	public static final String SHIP_1002 = "物流模板查询为空";

	public static final String PAY_MD5ERROR_CODE = "0901";
	public static final String PAY_MD5ERROR_SHOW = "数据加密错误，数据被篡改";

	public static final String LOGINFO_FORMAT_CODE = "0902";
	public static final String LOGINFO_FORMAT_SHOW = "物流同步信息格式错误";

	public static final String REFUND_NOT_SEND_CODE = "0903";
	public static final String REFUND_NOT_SEND_SHOW = "退款信息不是待发货状态,不允许发货";

	public static final String REFUND_STATUS_ERROR_CODE = "0904";
	public static final String REFUND_STATUS_ERROR_SHOW = "该状态不允许进行退款";

	public static final String REFUND_ERROR_CODE = "0905";
	public static final String REFUND_ERROR_SHOW = "退款错误";

	public static final String REFUND_EXIT_CODE = "0906";
	public static final String REFUND_EXIT_SHOW = "该商品已经提交了退款申请,不能再重复提交";

	public static final String REFUND_EPAYPLAT_ERROR_CODE = "0906";
	public static final String REFUND_EPAYPLAT_ERROR_SHOW = "调用三维易付退款失败";

	/*********收藏相关************/
	/**重复收藏错误*/
	public static final String Collect_REPEAT_CODE = "1001";
	/**重复收藏错误*/
	public static final String Collect_REPEAT_SHOW = "重复收藏错误";
	//重复关注错误
	public static final String Attent_REPEAT_SHOW = "重复关注错误";
	/*********收藏相关************/

	public static final String GOODS_NOTEXIST_CODE = "1111";
	public static final String GOODS_NOTEXIST_SHOW = "商品不存在";

	public static final String SHOPCAR_OPERERROR_CODE = "0801";

	public static final String REDPAC_GETTED_CODE = "3001";
	public static final String REDPAC_GETTED_SHOW = "红包已被领取过";
	public static final String REDPAC_MORE_CODE = "3002";
	public static final String REDPAC_MORE_SHOW = "待领取的红包不存在或存在多个";
	public static final String REDPAC_LESS_CODE = "3003";
	public static final String REDPAC_LESS_SHOW = "红包已被领取完";
	public static final String REDPAC_TIMEOUT_CODE = "3004";
	public static final String REDPAC_TIMEOUT_SHOW = "红包活动未开始或已过期";
	public static final String REDPAC_STATUSERROR_CODE = "3005";
	public static final String REDPAC_STATUSERROR_SHOW = "红包活动状态不正确";

	/**
	 * 手机端验证令牌不通过
	 */
	public static final String PHONE_TOKEN_ERROR_CODE = "8001";
	public static final String PHONE_TOKEN_ERROR_SHOW = "手机端验证令牌不通过";
	public static final String PHONE_TOKEN_NO_CODE = "8002";
	public static final String PHONE_TOKEN_NO_SHOW = "令牌为空";
	public static final String PHONE_TOKEN_LOCKED_CODE = "8003";
	public static final String PHONE_TOKEN_LOCKED_SHOW = "手机号已被锁定";
	public static final String PHONE_TOKEN_TIMEOUT_CODE = "8004";
	public static final String PHONE_TOKEN_TIMEOUT_SHOW = "令牌超时";

	/** 业务异常 */
	public static final String BUSS_ERROR_CODE = "9999";

	public static final String CONVENIENCE_PARAMERROR_CODE = "2100";
	public static final String CONVENIENCE_PARAMERROR_SHOW = "非法参数";
	public static final String CONVENIENCE_ERROR_CODE = "2101";
	public static final String CONVENIENCE_ERROR_SHOW = "缴费发生异常";

	/**针对验证web service 协议参数  head 中 认证码的验证 */
	public static String TRANS_AUTHCODE_ERROR_CODE = "01110";
	/**针对验证web service 协议参数  head 中 认证码的验证 */
	public static String TRANS_AUTHCODE_ERROR_SHOW = "认证码错误，非法的调用";

	public static final String INCLUDE_KEYWORD_CODE = "1101";
	public static final String INCLUDE_KEYWORD_SHOW = "接口参数中包含敏感字";
	public static final String GETPARAM_ERROR_CODE = "1102";
	public static final String GETPARAM_ERROR_SHOW = "解析接口中的参数发生异常";
	
	
	/**不支持代付,错误*/
	public static String PAY_DAIFU_ERROR_CODE = "01103";
	/**不支持代付,错误*/
	public static String PAY_DAIFU_ERROR_SHOW = "该笔订单生成流水会员信息与支付会员信息不一致，不支持代付";

}
