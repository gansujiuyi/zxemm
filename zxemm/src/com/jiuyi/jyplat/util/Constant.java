package com.jiuyi.jyplat.util;

public class Constant {
	public static String SYS_SERVER_NAME = "zxemm";
	public static String SYS_DB_TYPE = "oracle"; // 系统的数据库类型 SQLServer/oracle
	public static String SYS_BANK_TYPE = "lz"; //
	// 登录的验证码
	public static final String LOGIN_VALIDATE_IMAGE = "login_validate_image";
	// 登录用户session
	public static final String LOGIN_SYSTEM_USER = "login_system_user";

	// 登录用户机构（所属片区）
	public static final String LOGIN_SYSTEM_USER_INSTITUTION = "login_system_user_institution";
	// 系统无登录操作员时的缺省值
	public static final String SYS_DEFAULT_OPERATOR = "SYSTEM";

	public static final String SYS_VOUCHERTYPE_CUST = "CUST";
	// 权限常量
	public static final String AUTH_PASS = "pass";// 权限免疫常量 需要用户登录
	public static final String AUTH_LOGIN_URL = "com.jiuyi.jyplat.web.action.acegiManage.LoginAction.login"; // 登录页面请求URL
	public static final String AUTH_DOLOGIN_URL = "com.jiuyi.jyplat.web.action.acegiManage.LoginAction.doLogin"; // 登录页面请求URL
	public static final String VALI_CODE_URL = "com.jiuyi.jyplat.web.action.acegiManage.LoginAction.valiCode"; // 验证登录短信息请求URL
	public static final String AUTH_EDIT_PW_URL = "com.jiuyi.jyplat.web.action.acegiManage.OperatorAction.modifyPassword"; // 修改密码请求URL
	public static final String AUTH_CHECK_PW_URL = "com.jiuyi.jyplat.web.action.acegiManage.OperatorAction.checkOldPassword"; // 检验原密码请求URL
	public static final String NOTIFY_URL = "com.jiuyi.jyplat.web.action.transport.TransportAction.notify_url"; // 异步通知转账结果URL


	// 系统 超级管理员角色 不能删除
	public static final Integer SYSTEM_ADMIN_ROLE_1 = 1;
	public static final Integer SYSTEM_ADMIN_ROLE_2 = 2;

	// 交易方向代码
	public static final String TRANS_TYPE_DEBIT = "D"; // 借
	public static final String TRANS_TYPE_CREDIT = "C"; // 贷
	public static final String TRANS_TYPE_REVERSED = "R"; // 冲正
	public static final String TRANS_TYPE_FREEZING = "F"; // 冻结

	// 上传图片用到常量
	public static final int BUFFER_SIZE = 16 * 1024;
	public static final String TEXT_TITLE = "uxun";
	public static final String WATER_IMG_NAME = "rzx.jpg";
	public static final String IMAGE_SAVE_FOLDER = "/zxemmimg";

	public static final String SYS_CUST_GROUP_COOPERATION = "100000"; // 合作商户组的组别ID

	// 动态口令部分添加

	public static int LOGIN_ERR = 5; // 定义错误登录用户锁定次数

	public static int SYS_ENDTIMES = 10; // 失效时间，3分钟

	public static int CHECK_ERR = 5; // 用户输入认证码错误次数

	public static String JFY_URL = "";
	public static String UNION_COLLECT = "";
	public static final String Version = "V1.0";
	public static final String TranChannel = "005";
	public static final String AuthCode = "102100009980";

	public static String FJS_URL = "";
	public static String CLIENTPRIKEYFILE = "";
	public static String CLIENTTHREEDESKEY = "";
	public static final String KEY_PASSWORD = "#bank!";// 3dses加密key
	public static String EZEX_ONLINEBANKADDRS;// E住E行调用epaypalt地址


	public static String BANKCODE;// 银行编码

	public static final String SYS_CHANNEL_DEV = "003"; // 自助终端
	public static final String SYS_CHANNEL_POS = "002"; // 渠道-POS
	public static final String SYS_CHANNEL_MALL = "005"; // 商城

	// 新房资金监管预售开户状态
	public static final String account_status_open = "1001";// 账户状态：1001已开启
	public static final String account_status_wait = "1003";// 账户状态1003等待企业信息（待审核）
	public static final String account_status_noopen = "1002";// 账户状态1002：未开启
	public static final String noregulate_status = "0001";// 监管状态0001：未监管
	public static final String regulate_status = "0002";// 监管状态0002：已监管
	public static final String retulate_status_backout = "0003";// 监管状态0003：撤销监管

}
