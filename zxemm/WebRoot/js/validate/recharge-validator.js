function ysPoint()
{
	var needPoints = $("#recharge_num").val();
	alert(needPoints);
	return needPoints;
}
//Q币填写充值信息表单验证
$(document).ready(function(){
	$.formValidator.initConfig({theme:"Default",submitOnce:true,formID:"form1",errorFocus:false,submitAfterAjaxPrompt : '有数据正在异步验证，请稍等'});
	//QQ验证
	$("#qqNo")
	.formValidator({onShow:"&nbsp;",onFocus:"&nbsp;",onCorrect:""})
	.functionValidator({fun:function(){if($("#qqNo").val()==""){return "QQ账号格不能为空，请输入";}else{return true;}}})
	.regexValidator({regExp:"qq",dataType:"enum",onError:"您输入的QQ账号格式不正确，请重新输入QQ账号"});
	//重复输入QQ验证
	$("#reqq")
	.formValidator({onShow:"&nbsp;",onFocus:"&nbsp;",onCorrect:""})
	.functionValidator({fun:function(){if($("#reqq").val()==""){return "QQ账号格不能为空，请输入";}else{return true;}}})
	.regexValidator({regExp:"qq",dataType:"enum",onError:"您输入的QQ账号格式不正确，请重新输入QQ账号"})
	.compareValidator({desID:"qqNo",operateor:"=",onError:"两次QQ账号不一致,请重新输入"});
	//充值个数验证
	$("#recharge_num").formValidator({onShow:"&nbsp;",onCorrect:function(code,codeNode){
			return '输入正确，您需要消耗：'+parseInt(code*500)+'积分';
	 	}
	 })
	.regexValidator({regExp:"intege1",dataType:"num1",onError:"您输入的格式不正确"})
	.functionValidator({fun:function(code,codeNode){
		var point = parseInt($("#havPoint").text());
		var needPoint = parseInt(code*500);
		return needPoint<=point;
	 },onError:"您的积分余额不足，请您重新输入"});
	 
	 
	//验证码验证：非空；输入正确；不区分大小写
    $("#regCode_mail")
    .formValidator({onShow:"&nbsp;",onFocus:"&nbsp;",onCorrect:"&nbsp;"})
    .inputValidator({min:1,onError:"验证码不能为空，请重新输入"})
    .functionValidator({fun:function(code,codeNode){
		var hiddenCode=$("input[name='hideCode']").val();
		return code.toLowerCase()==hiddenCode.toLowerCase()
	 },onError:"验证码输入有误，请重新输入"});
	
	//手机验证
	$("#phone1")
	.formValidator({onShowFixText:"密码遗忘或被盗时，可通过手机短信取回密码",onShow:"&nbsp;",onFocus:"&nbsp;",onCorrect:""})
	.functionValidator({fun:function(){$("div.righttxt").hide();return true;},onError:""})
	.functionValidator({fun:function(){if($("#phone1").val()==""){return "手机号码不能为空，请输入";}else{return true;}}})
	.inputValidator({min:11,max:11,onError:"手机号码是11位的,请确认"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机的格式不正确"})
	.functionValidator({fun:function(){$("div.righttxt").show();return true;},onError:""});
	
	$("#rephone1")
	.formValidator({onShowFixText:"密码遗忘或被盗时，可通过手机短信取回密码",onShow:"&nbsp;",onFocus:"&nbsp;",onCorrect:""})
	.functionValidator({fun:function(){$("div.righttxt").hide();return true;},onError:""})
	.functionValidator({fun:function(){if($("#phone1").val()==""){return "手机号码不能为空，请输入";}else{return true;}}})
	.inputValidator({min:11,max:11,onError:"手机号码是11位的,请确认"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机的格式不正确"})
	.functionValidator({fun:function(){$("div.righttxt").show();return true;},onError:""})
	.compareValidator({desID:"phone1",operateor:"=",onError:"两次手机号不一致,请重新输入"});
	
	
	 })
