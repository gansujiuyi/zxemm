package com.jiuyi.jyplat.util;

import java.util.List;

public class ErrorCode {

	/**
	 * 系统/数据错误
	 */
	public static final String ERROR_TRANS_SYS_001 = "错误:积分账户可用积分不足";
	public static final String ERROR_TRANS_SYS_002 = "错误:积分账户不存在";
	public static final String ERROR_TRANS_SYS_003 = "错误:积分账户明细数据不统一";
	public static final String ERROR_TRANS_SYS_004 = "错误:积分账户非正常状态";

	/**
	 * 人为操作/数据输入错误
	 */
	public static final String ERROR_TRANS_MANUAL_001 = "错误:交易的积分不能小于0";
	public static final String ERROR_TRANS_MANUAL_002 = "错误:无对应的数据";
	public static final String ERROR_TRANS_MANUAL_003 = "错误:数据格式错误";
	public static final String ERROR_TRANS_MANUAL_004 = "错误:同一客户不能转让积分";
	public static final String ERROR_TRANS_MANUAL_005 = "错误:积分有效期必须大于当前自然时间";
	public static final String ERROR_TRANS_MANUAL_006 = "错误:交易的手续费不能小于0";
	public static final String ERROR_TRANS_MANUAL_007 = "错误:积分账户暂无冻结的积分";
	public static final String ERROR_TRANS_MANUAL_008 = "错误:积分账户中的冻结积分小于解冻积分";
	public static final String ERROR_TRANS_MANUAL_009 = "错误:非法的转入卡种";

}
