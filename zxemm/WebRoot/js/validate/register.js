//注册，登录表单验证
$(document).ready(function(){
	$.formValidator.initConfig({theme:"Default",submitOnce:true,formID:"register",errorFocus:false,submitAfterAjaxPrompt : '有数据正在异步验证，请稍等'});
	//昵称验证：最小长度验证；非空验证；两边空格符号验证；唯一性ajax验证
	$("#username")
	.formValidator({onShowFixText:" 3-10个字符，可输入数字、中文、英文字符。 ",onShow:"3-10个字符，可输入数字、中文、英文字符。 ",onFocus:"3-10个字符，可输入数字、中文、英文字符。 ",onCorrect:""})
	.functionValidator({fun:function(){if($("#username").val()==""){return "用户名不能为空，请输入";}else{return true;}}})
	.inputValidator({min:3,max:10,onErrorMin:"昵称太短了,至少3个字符",onErrorMax:"昵称太长了，不能超过10个字符"})
	.ajaxValidator({
		dataType : "",
		async : false,
		url : "",
		success : function(data){
            if( data.indexOf("此昵称可以注册!") > 0 ) return true;
            if( data.indexOf("此昵称已存在,请更换!") > 0 ) return false;
			return false;
		},
		buttons: $("#button"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该昵称不可用，请更换",
		onWait : "正在进行校验，请稍候"
	});
	
	//找回密码---用户名验证
	$("#forgot-username")
	.formValidator({onShowFixText:"邮箱/会员账号/手机账号 ",onShow:"邮箱/会员账号/手机账号",onFocus:"请输入用户名",onCorrect:""})
	.inputValidator({min:1,onErrorMin:"用户名不能为空,请输入"});
	
	//找回密码---交易密码验证
	$("#forgot-userpwd")
	.formValidator({onShowFixText:"请输入交易密码",onShow:"请输入交易密码",onFocus:"请输入交易密码",onCorrect:""})
	.inputValidator({min:1,onErrorMin:"密码不能为空,请输入"});
	
	//密码验证（非空，太短，强度不够（连续数字不行，连续字母不行，和用户名相同不行，6个相同的字母或数字不行），密码两边有空格不行,）
	$("#password")
	.formValidator({onShow:"",onFocus:"6-16位字符，可包含大小字母、数字，但不能作为纯字母。",onShow:"6-16位字符，可包含大小字母、数字，但不能作为纯字母。",onEmpty:"密码不能为空",onCorrect:""})
	.inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请输入"})
	.inputValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码不能包含空格！"},min:6,max:20,onErrorMin:"密码太短了，不能少于6位",onErrorMax:"密码太长了，不能超过20位"})
	
	//确认密码验证（非空不行，两边有空格不行，和密码不一致不行）
	$("#rePwd")
	.formValidator({onShowFixText:"请再次输入密码",onShow:"请重新输入一次密码。",onFocus:"请重新输入一次密码",onCorrect:""})
	.inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请输入"})
	.compareValidator({desID:"password",operateor:"=",onError:"两次密码不一致,请确认"});

	//手机验证（非空不行，格式不正确不行，位数不够不行）
	$("#phone")
	.formValidator({onShowFixText:"请输入手机号码，它将作为登录该网站的用户名。",onShow:"请输入手机号码，它将作为登录该网站的用户名。",onFocus:"请输入正确有效的手机号码",onCorrect:""})
	.functionValidator({fun:function(){$("div.righttxt").hide();return true;},onError:""})
	.functionValidator({fun:function(){if($("#phone").val()==""){return "手机号码不能为空，请输入";}else{return true;}}})
	.inputValidator({min:11,max:11,onError:"手机号码是11位的,请确认"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机的格式不正确"})
	.functionValidator({fun:function(){$("div.righttxt").show();return true;},onError:""});
	
	//邮箱验证（非空不行，格式不正确不行,唯一性验证）
	$("#email")
	.formValidator({onShowFixText:"请使用常用Email，它将作为找回用户名/密码的重要方式。",onShow:"请使用常用Email，它将作为找回用户名/密码的重要方式。",onFocus:"请填写您的常用邮箱。",onCorrect:""})
	.inputValidator({min:1,max:100,onErrorMin:"邮箱不能为空，请输入"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"您输入的Email格式不正确，请重新输入！"});
	
	//银行卡卡号验证
	$("#bank-card")
	.formValidator({onShowFixText:"请输入您正确的银行卡卡号。",onShow:"请输入您正确的银行卡卡号。",onFocus:"请输入您正确的银行卡卡号。",onCorrect:"格式正确&nbsp;"})
	.functionValidator({fun:function(){if($("#bank-card").val()==""){return "银行卡号不能为空，请输入";}else{return true;}}})
	.functionValidator({fun:function(code,codeNode){return /^6212000101\d{9}$/.test(code);},onError:"银行卡输入有误，请重新输入"});
	
	//手机验证码（非空不行，格式不正确不行，位数不够不行）
	$("#phone_yzm")
	.formValidator({onShowFixText:"请输入手机验证码。",onShow:"请输入手机验证码。",onFocus:"请输入手机验证码。",onCorrect:""})
	.functionValidator({fun:function(){if($("#phone_yzm").val()==""){return "手机验证码不能为空，请输入";}else{return true;}}})
	.inputValidator({min:6,max:6,onError:"手机验证码是6位数字,请确认"})
	.regexValidator({regExp:"num1",dataType:"enum",onError:"格式不正确"});
	
	//身份证（非空不行，格式不正确不行，位数不够不行）
	$("#idcard")
	.formValidator({onShowFixText:"请输入身份证号码。",onShow:"请输入身份证号码。",onFocus:"请输入身份证号码。",onCorrect:""})
	.functionValidator({fun:function(){if($("#idcard").val()==""){return "身份证号不能为空，请输入";}else{return true;}}})
	.inputValidator({min:18,max:18,onError:"身份证号是18位数字,请确认"})
	.regexValidator({regExp:"num1",dataType:"idcard",onError:"格式不正确"});
	
	//接受用户协议验证（必须选中才能注册）
	$("#accept")
	.formValidator({tipID:"acceptTip",onShow:"",onFocus:"",onCorrect:""})
	.inputValidator({min:1,onError:"必须同意才能注册！"});
	$("#accept1")
	.formValidator({tipID:"acceptTip",onShow:"",onFocus:"",onCorrect:""})
	.inputValidator({min:1,onError:"必须同意才能注册！"});
	
	
	//验证码验证：非空；输入正确；不区分大小写
    $("#regCode_mail")
    .formValidator({onShow:"",onFocus:" ",onCorrect:""})
    .inputValidator({min:1,onError:"验证码不能为空，请重新输入"})
    .functionValidator({fun:function(code,codeNode){
		var hiddenCode=$("input[name='hideCode']").val();
		return code.toLowerCase()==hiddenCode.toLowerCase()
	 },onError:"验证码输入有误，请输入"});
	 
	//登录--用户名
	$("#login_name")
	.formValidator({onShowFixText:"",onShow:"",onFocus:" ",onCorrect:""})
	.functionValidator({fun:function(){if($("#login_name").val()==""){return "用户名不能为空，请输入";}else{return true;}}});
	
	//登录--密码
	$("#login_pwd")
	.formValidator({onShowFixText:" ",onShow:"",onFocus:" ",onCorrect:""})
	.inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请输入"})
	
	//我的积分易--修改密码
	$("#oldPwd").formValidator({ onShowFixText:"",onShow:"请输入现在的密码",onFocus:"请输入现在的密码",onCorrect:"输入正确",defaultValue:""}).functionValidator({fun:function(){if($("#oldPwd").val()==""){return "请输入现在的密码";}else{return true;}}}).ajaxValidator({
		dataType : "json",
		async : true,
		url : "admin.UserCenter/checkPass",
		success : function(data)
		{
				   if( data.state=="success") return true;
		            if( data.state=="fail") return false;
					return false;
		},
		buttons: $("#userEmailBtn"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "密码不正确，请重新输入",
		onWait : "正在进行校验，请稍候"
	});
	$("#newPwd")
	.formValidator({onShow:"6-16位字符，可包含大小字母、数字，但不能作为纯字母",onFocus:"6-16位字符，可包含大小字母、数字，但不能作为纯字母",onEmpty:"密码不能为空",onCorrect:"密码输入正确"})
	.inputValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码不能包含空格！"},min:6,max:20,onErrorMin:"密码太短了，不能少于6位",onErrorMax:"密码太长了，不能超过20位"})
	.compareValidator({desID:"oldPwd",operateor:"!=",onError:"新密码与当前密码不能相同" })

	$("#checkNewPwd")
	.formValidator({onShowFixText:"请再次输入密码 ",onShow:"请再次输入密码",onFocus:"请再次输入密码",onCorrect:"输入正确"})
	.inputValidator({min:1,onErrorMin:"确认密码不能为空，请输入"})
	.compareValidator({desID:"newPwd",operateor:"=",onError:"两次输入的密码必须一致"});
	
	//联系人验证
	$("#person")
	.formValidator({onShowFixText:"请输入联系人",onShow:"请输入联系人",onFocus:"请输入联系人",onCorrect:""})
	.inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"联系人不能为空"},onError:"联系人不能为空"});
	
	//上传身份证件验证
	$("#idcard-img")
	.formValidator({onShowFixText:"请上传身份证附件",onShow:"请上传身份证附件",onFocus:"请上传身份证附件",onCorrect:""})
	.inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"身份证附件不能为空"},onError:"身份证附件不能为空"});
		
		
	 })
