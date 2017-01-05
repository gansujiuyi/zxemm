var usernameDefaultValue = "用户名";
var imagecodeDefaultValue = "验证码";
var yanzhengma = false;
$(function() {
	$("#username").val('');
	$("#password").val('');
	$("#username").focus();
	$("#imagecode").val(imagecodeDefaultValue);
	$("#imagecode").focus(function() {
		var imagecode = $(this).val();
		if (imagecode == imagecodeDefaultValue) {
			$(this).val('');
		}
	});
	$("#imagecode").blur(function() {
		var imagecode = $(this).val();
		if (imagecode == '') {
			$(this).val(imagecodeDefaultValue);
		}
	});
	changeImgCode();
});
usernameblur = function() {
	var username = $("#username").val();
	if (username == '') {
		$("#username").val(usernameDefaultValue);
	}
}
usernamefocus = function() {
	var username = $("#username").val();
	if (username == usernameDefaultValue) {
		$("#username").val('');
	}
}
/**
 * 修改验证码图片
 */
changeImgCode = function() {
	$("#imagecode").val(imagecodeDefaultValue);
	var verify = $("#imgcode");
	verify.attr('src', projcetname + '/imgcode?rand='+Math.random());
}

valiCode = function() {
	$.ajax({
		url : projcetname + "/login/valiCode.do",
		type : "post",
		async : false,
		data : {
			imagecode : $("#imagecode").val()
		},
		dataType : "json",
		success : function(message) {
			var operateResult = message.operateResult;
			if (operateResult.success) {
				yanzhengma = true;
			} else {
				yanzhengma = false;
			}
		}
	});
}

submitForm = function() {
	var username = $("#username").val();
	var imagecode = $("#imagecode").val();
	if (username == '' || username == usernameDefaultValue) {
		alert( "用户名不能为空" );
		$("#username").focus();
	} else if ($("#password").val() == '') {
		alert( "密码不能为空" );
		$("#password").focus();
	} else if (imagecode == '' || imagecode == imagecodeDefaultValue) {
		alert( "验证码不能为空" );
		$("#imagecode").focus();
	} else {
		valiCode();
		if (yanzhengma) {
			$("#loginForm").submit();
		} else {
			changeImgCode();
			alert('验证码错误');
		}
	}
}

strdown = function(event) {
	if(event.keyCode==13){
		submitForm();
	}
}