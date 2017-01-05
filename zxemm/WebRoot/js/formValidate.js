//清空
function ClearFormEelemnts(frmObj, contentCode)
{
  frmObj.reset();	  
  for (var i=0; i< frmObj.elements.length; i++)
  {
		var objTag = frmObj.elements(i);
		if((objTag.tagName.toLowerCase() == "input") || (objTag.tagName.toLowerCase() == "textarea"))
		{		
      if (objTag.type.toUpperCase()=="FILE")
			{
				objTag.fireEvent("onchange");
			}
		}
	}
}
//手机号码验证，验证13系列和150-159(154除外)、180、185、186、187、188、189几种号码，长度11位

function isMobile(value)
{
	if((/^13\d{9}$/g.test(value.trim()))||(/^15[0-35-9]\d{8}$/g.test(value.trim()))|| (/^18\d{9}$/g.test(value.trim())))
	{
		return true;
	}
	else
	{
		alert("手机号码输入有误，请重新输入！");
		return false;
	}
}

//验证邮箱
function isValidEmail(value)
{
	if(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/g.test(value.trim()))
	{
		return true;
	}
	else
	{
		alert("邮箱格式错误，请重新输入！");
		return false;
	}
}


//检验手机号码
function checkMobile(value)
{

	if (value > ""){
		var reg=/13[0,1,2,3,4,5,6,7,8,9]\d{8}/;
  	   	if (value.match(reg)== null){
			return false;
	   }
	}
    else
    {
		return false;
    }
	return true;
}

//检验移动手机号码
//modify by x_tangrc  增加对移动147新号段的识别
function validatePhone(value)
{
/*
   //检测只能为只能为正整数
   //var reg=/13[5,6,7,8,9]\d{8}|134[0,1,2,3,4,5,6,7,8]\d{7}|15[8,9]\d{8}/;
   var reg= /[\D_]/;   
   if(!(reg.exec(value))&&value.length==11){
  	 return true;
   }else{
  	 return false;
   }*/
   
   //var reg=/1[3,5,8]\d{9}/;
   var reg=/1[3,5,8]\d{9}|147\d{8}/;
   if(reg.test(value)&&value.length==11){
    return true;
   }else{
  	 return false;
   }
}

//2到10位数字，用于服务代码的校验
//add by liql
function servidalias(element_value){

	re = /^[\d]{2,10}$/;
	if(!re.test(element_value)){
		return false;
	}
	return true;
}
//根据对象校验手机号码
function checkCMMobile(obj,name,notNull)
{
    if(obj==null||obj.value==null){
        alert("对象不存在！");
        return false;
    }
    if(notNull==true){
        if(obj.value==null||obj.value==""){
            alert("请输入"+name+"移动手机号码！");
            obj.focus();
            return false;
        }
    }
    if (obj.value.length > 0){
        var reg=/^13[4,5,6,7,8,9]\d{8}$/;
        if (obj.value.match(reg)== null){
                 alert("请输入"+ name +"正确的移动手机号码！");
                 obj.focus();
            return false;
       }
    }
    return true;
}
function toChnDigit(num)
{
  var t = parseInt(num);
  if(t==0) return "零";
  if(t==1) return "一";
  if(t==2) return "二";
  if(t==3) return "三";
  if(t==4) return "四";
  if(t==5) return "五";
  if(t==6) return "六";
  if(t==7) return "七";
  if(t==8) return "八";
  if(t==9) return "九";
  return "";
}
//添加屏蔽所有按钮的公用函数
function disableAllButtons(){
	for(var i=0;i<document.all.tags("input").length;i++){
		var tmp = document.all.tags("input")[i];
		if(tmp.type=="button" || tmp.type=="submit" ||tmp.type=="reset"){
			tmp.disabled = true;
		}
	}
}
//函数名：checkNUM
//功能介绍：检查是否为数字
//参数说明：要检查的数字
//返回值：1为是数字，0为不是数字
function checkNum(Num) {
	var i,j,strTemp;
	strTemp = "0123456789.";
	if ( Num.length == 0)
		return 0
	for (i = 0;i < Num.length; i++) {
		j = strTemp.indexOf(Num.charAt(i));
		if (j == -1) {
			//说明有字符不是数字
			return 0;
		}
	}
	//说明是数字
	return 1;
}
//函数名：checkNUM
//功能介绍：检查是否为数字
//参数说明：要检查的数字
//返回值：1为是数字，0为不是数字
function checkIntNum(Num) {
	var i,j,strTemp;
	strTemp = "0123456789";
	if ( Num.length == 0)
		return 0
	for (i = 0;i < Num.length; i++) {
		j = strTemp.indexOf(Num.charAt(i));
		if (j == -1) {
			//说明有字符不是数字
			return 0;
		}
	}
	//说明是数字
	return 1;
}
//函数名：checkEmail
//功能介绍：检查是否为Email Address
//参数说明：要检查的字符串
//返回值：0：不是 1：是
/**@CheckItem@ zhou_jx-20041231 校正对'.'的判断
（判断的规则是不能以'@'或'.'开头，'@'后面至少要有三个字符，不能以'.'结束）
*/
//ADD BY LIQL 与SP资料添加的一致
function checkEmail(a){

		re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		if (!re.test(a)){

			return 0;
			}
		return 1;
	}
//函数名：checkTEL
//功能介绍：检查是否为电话号码
//参数说明：要检查的字符串
//返回值：1为是合法，0为不合法
function checkTel(tel)
{
	var i,j,strTemp;
	strTemp = "0123456789-－+＋";
	for (i=0;i<tel.length;i++)
	{
		j = strTemp.indexOf(tel.charAt(i));
		if (j==-1)
		{
			//说明有字符不合法
			return 0;
		}
	}
	//说明合法
	return 1;
}

//函数名：checkLength
//功能介绍：检查字符串的长度
//参数说明：要检查的字符串
//返回值：长度值
function checkLength(strTemp)
{
	var i,sum;
	sum = 0;
	for(i=0;i<strTemp.length;i++)
	{
//@CheckItem@ BUG-Renhj-20040604 优化：将验证的函数改成128以类的为单字符。避免“·”符号
//		if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255))
		if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=128))
			sum = sum + 1;
		else
			sum = sum + 2;
	}
	return sum;
}

//函数名：checkSafe
//功能介绍：检查是否含有"'", '"',"<", ">"
//参数说明：要检查的字符串
//返回值：0：是 1：不是
function checkSafe(a)
{
	//fibdn = new Array ("'" ,'"',">", "<", "、", ",", ";");
	fibdn = new Array ("'" ,'"',">", "<");
	i = fibdn.length;
	j = a.length;
	for (ii=0;ii<i;ii++)
	{
		for (jj=0;jj<j;jj++)
		{
			temp1 = a.charAt(jj);
			temp2 = fibdn[ii];
			if (temp1==temp2)
			{
				return 0;
			}
		}
	}
	return 1;
}

//函数名：checkChar
//功能介绍：检查是否含有非字母字符
//参数说明：要检查的字符串
//返回值：0：含有 1：全部为字母
function checkChar(str)
{
	var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.()& ";
	var ch;
	var i;
	var temp;

	for (i=0;i<=(str.length-1);i++)
	{
		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp==-1)
		{
			return 0;
		}
	}
	return 1;
}
/*
* 检查只有数字和26个字母
//返回值：0：含有 1：全部为数字或字母
*/
function checkCharOrNum(str)
{
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var ch;
	var i;
	var temp;

	for (i = 0;i<=(str.length-1);i++)
	{

		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1)
		{
			return 0;
		}
	}
	return 1;
}
/*
* 检查业务代码
//返回值：0：不正确 1：正确
*/
function checkIcpServId(str)
{
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-";
	var ch;
	var i;
	var temp;

	for (i = 0;i<=(str.length-1);i++)
	{

		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1)
		{
			return 0;
		}
	}
	return 1;
}
/*
* 检查非法字符
//返回值：0：不正确 1：正确
*/
function checkFormChar(str)
{
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	var ch;
	var i;
	var temp;

	for (i = 0;i<=(str.length-1);i++)
	{

		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1)
		{
			return 0;
		}
	}
	return 1;
}
//函数名：checkCharOrDigital
//功能介绍：检查是否含有非数字或字母
//参数说明：要检查的字符串
//返回值：0：含有 1：全部为数字或字母
function checkCharOrDigital(str)
{
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.()& ";
	var ch;
	var i;
	var temp;

	for (i = 0;i<=(str.length-1);i++)
	{

		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1)
		{
			return 0;
		}
	}
	return 1;
}

//函数名：checkPackageName
//功能介绍：检查是否含有非数字或字母或小数点，减号，下划线
//参数说明：str要检查的字符串
//返回值：0：含有 1：全部为数字或字母或小数点，减号，下划线
function checkPackageName(str)
{
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-_";
	var ch;
	var i;
	var temp;

	for (i = 0;i<=(str.length-1);i++)
	{

		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1)
		{
			return 0;
		}
	}
	return 1;
}

//函数名：checkParenthesis
//功能介绍：检查是否含有半角括号
//参数说明：str要检查的字符串
//返回值：0：不含半角括号 1：含有半角括号
function checkParenthesis(str)
{
	if((str.indexOf('(')==-1)&&(str.indexOf(')')==-1)){
	return 0;
	}
	else{
	return 1;
	}
}

//函数名：checkChinese
//功能介绍：检查是否含有汉字
//参数说明：要检查的字符串
//返回值：0：含有 1：没有
function checkChinese(strTemp)
{
	var i,sum;
	for(i=0;i<strTemp.length;i++)
	{
		if ((strTemp.charCodeAt(i)<0) || (strTemp.charCodeAt(i)>255))
	      return 0;
	}
	return 1;
}

//函数名：compareTime()
//功能介绍： 比较时间大小
//参数说明：beginYear开始年，beginMonth开始月,benginDay开始日,beginH开始小时，beginM开始分钟，
//          endYear结束年，endMonth结束月，endMonth结束日,endH结束小时，endM结束分钟
//返回值：true 表示 开始时间大于结束时间，false 相反
//@CheckItem@ SELBUG-Tanyi-20030707 修改时间校验
function compareTime(beginYear,beginMonth,benginDay,beginH,beginM,endYear,endMonth,endDay,endH,endM){
  var date1 = new Date(beginYear,beginMonth-1,benginDay,beginH,beginM);
  var date2 = new Date(endYear,endMonth-1,endDay,endH,endM);
  if(date1.getTime()>=date2.getTime()){
    return false;
  }
  return true;
}

//函数名：compareDate()
//功能介绍： 比较日期大小
//参数说明：beginYear开始年，beginMonth开始月,benginDay开始日
//          endYear结束年，endMonth结束月，endMonth结束日
//返回值：0：true 表示 开始时间大于结束时间，false 相反
//@CheckItem@ BUG320-TANYI-20040304 修改日期比较方法，将CS和PS统一
//@CheckItem@ BUG320-renhj-20040310 修改日期比较方法，将CS和PS统一
function compareDate(beginYear,beginMonth,benginDay,endYear,endMonth,endDay){
  var date1 = new Date(beginYear,beginMonth-1,benginDay);
  var date2 = new Date(endYear,endMonth-1,endDay);
  if(date1.getTime()>=date2.getTime()){
    return false;
  }
  return true;
}
function compareDate2(beginYear,beginMonth,benginDay,endYear,endMonth,endDay){
  var date1 = new Date(beginYear,beginMonth-1,benginDay);
  var date2 = new Date(endYear,endMonth-1,endDay);
  if(date1.getTime()>date2.getTime()){
    return false;
  }
  return true;
}
//函数名：checkURL
//功能介绍：检查Url是否合法
//参数说明：要检查的字符串
//返回值：true：合法 false：不合法。
function checkURL(strTemp)
{
	if(strTemp.length==0) return false;
	if(checkChinese(strTemp)==0) return false;
	if (strTemp.toUpperCase().indexOf("HTTP://") != 0 && strTemp.toUpperCase().indexOf("HTTPS://") != 0){
		return false;
	}
	return true;
}

//格式化输入的日期串，输入的如："2003-9-12" 输出："2003-09-12"
function formatDateStr(inDate){
  if (inDate==null||inDate=="") return "";
  var beginDate = inDate.split("-");
  var mYear=beginDate[0];
  var mMonth=beginDate[1];
  var mDay=beginDate[2];
  mMonth=((mMonth.length==1)?("0"+mMonth):mMonth);
  mDay=((mDay.length==1)?("0"+mDay):mDay);
  return mYear+"-"+mMonth+"-"+mDay;
  }

//obj:数据对象
//dispStr :失败提示内容显示字符串
function checkUrlValid( obj,  dispStr)
{
	if(obj  == null)
	{
		alert("传入对象为空");
		return false;
	}
	var str = obj.value;

	var  urlpatern0 = /^https?:\/\/.+$/i;
	if(!urlpatern0.test(str))
	{
		alert(dispStr+"不合法：必须以'http:\/\/'或'https:\/\/'开头!");
		obj.focus();
		return false;
	}

	var  urlpatern2= /^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?.+$/i;
	if(!urlpatern2.test(str))
	{
		alert(dispStr+"端口号必须为数字且应在1－65535之间!");
		obj.focus();
		return false;
	}


	var	urlpatern1 =/^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?(\/((\.)?(\?)?=?&?[a-zA-Z0-9_-](\?)?)*)*$/i;

	if(!urlpatern1.test(str))
	{
		alert(dispStr+"不合法,请检查!");
		obj.focus();
		return false;
	}

	var s = "0";
	var t =0;
        var re = new RegExp(":\\d+","ig");
	while((arr = re.exec(str))!=null)
	{

		s = str.substring(RegExp.index+1,RegExp.lastIndex);

		if(s.substring(0,1)=="0")
		{
			alert(dispStr+"端口号不能以0开头!");
			obj.focus();
			return false;
		}

		t = parseInt(s);
		if(t<1 || t >65535)
		{
			alert(dispStr+"端口号必须为数字且应在1－65535之间!");
			obj.focus();
			return false;
		}


	}
	return true;
}

//函数名：checkVisibleEnglishChr
//功能介绍：检查是否为可显示英文字符(  !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~)
//参数说明：要检查的字符串
//返回值：true|false
//add by renhj 2004.01.05
function checkVisibleEnglishChr(strTemp)
{
	var i;
	for(i=0;i<strTemp.length;i++)
	{
		if ((strTemp.charCodeAt(i)<32) || (strTemp.charCodeAt(i)>126))
			return false;
	}
	return true;
}
//函数名：checkareaInput
//功能介绍：检查是否含有非textarea字符
//参数说明：要检查的字符串
//返回值：false：含有 true：全部为可textarea字符
//add by liql 2005.01.05
function checkareaInput(str)
{
	var notinput = "<>";
    var i;
    for (i = 0; notinput != null && i < notinput.length; i++) {
        if (str.indexOf(notinput.charAt(i)) >= 0) {//若有
          return false;
        }
      }
    return true;

}
//函数名：checkInputChr
//功能介绍：检查是否含有非Input字符
//参数说明：要检查的字符串
//返回值：false：含有 true：全部为可Input字符
//add by renhj 2004.01.05
function checkInputChr(str)
{
	var notinput = "\"'<>@#$%^&*()";
    var i;
    for (i = 0; notinput != null && i < notinput.length; i++) {
        if (str.indexOf(notinput.charAt(i)) >= 0) {//若有
          return false;
        }
      }
    return true;

}
//函数名：checktextareaInput
//功能介绍：检查是否含有非Input字符<,>
//参数说明：要检查的字符串
//返回值：false：含有 true：全部为可Input字符
//add by liql 2004.12.31
function checktextareaInput(str)
{
	var notareainput = "<>_";
    var i;
    for (i = 0; notareainput != null && i < notareainput.length; i++) {
        if (str.indexOf(notareainput.charAt(i)) >= 0) {//若有
          return false;
        }
      }
    return true;

}

//函数名：checkNotHalfangleCha
//功能介绍：检查是否含有非Input字符
//参数说明：要检查的字符串
//返回值：false：含有 true：全部为可Input字符
//add by renhj 2004.01.05
function checkNotHalfangleCha(str)
{
	var notinput = "<>_\"'<>@#$%^&*";
    var i;
    for (i = 0; notinput != null && i < notinput.length; i++) {
        if (str.indexOf(notinput.charAt(i)) >= 0) {//若有
          return false;
        }
      }
    return true;

}


//函数名：checkFormdata
//功能介绍：检查Form对象
//参数说明：
//obj：要检查的对象，
//name：要检查的对象的中文名称，
//length：检查的对象的长度（<0不检查），
//notnull:为true则检查非空，
//notSpecChar:为true则检查有无特殊字符，
//notChinessChar:为true则检查有无中文字符，
//numOrLetter:为true则检查只能为数字或英文字母，
//pNumber:为true则检查只能为正整数，
//返回值：false：检查不通过 true：全部为可Input字符
function checkFormdata(obj,name,length,notnull,notSpecChar,notChinessChar,numOrLetter,pNumber){
	//检查对象
	if (!obj) {alert("目标不是对象，处理失败!");return false;}
	var msg;
	var ilen;
	//检测汉字
        if (notChinessChar&&(checkChinese(obj.value) != 1)){
           msg=name+"不能包含汉字！";
           alert(msg);
           obj.select();
           return false;
          }
        //检测特殊字符
         if(notSpecChar){
        	if(obj.type=="textarea"){
       			 if(!checktextareaInput(obj.value)){
	     			 var noinput = " < > _ ";
          			msg=name+"有非法字符（"+noinput+"）！";
          			alert(msg);
          			obj.select();
          			return false;
        			}

		}
		else{
        		if(!checkInputChr(obj.value)){
	     		 var notinput = " \" ' < > @ # $ % ^ & * ( )";
          		msg=name+"有非法字符（"+notinput+"）！";
          		alert(msg);
        		  obj.select();
         		 return false;
        		}

      		  }
	}
        //检测长度
        if(length>=0&&(checkLength(obj.value)>length)){
          ilen=length/2;
          if(pNumber){
              msg=name+"不能超过"+length+"个数字！";
          }else if(notChinessChar){
              msg=name+"不能超过"+length+"个英文！";
          }else if(numOrLetter){
              msg=name+"不能超过"+length+"个英文或数字！";
          }else{
              msg=name+"不能超过"+length+"个英文或"+ilen+"个汉字！";
          }
          alert(msg);
          obj.select();
          return false;
        }
        //检测非空
	if(notnull&&obj.value.trim()==""){
            msg="请输入"+name+"！";
            alert(msg);
	    obj.select();
	    return false;
	}
        //检测只能为数字或英文字母
        re = /[\W_]/;
        if (numOrLetter&&re.exec(obj.value)) {
            msg=name+"只能为数字或英文字母！";
            alert(msg);
	    obj.select();
            return false;
        }
        //检测只能为只能为正整数
        re = /[\D_]/;
        if (pNumber&&re.exec(obj.value)) {
            msg=name+"只能为正整数！";
            alert(msg);
	    obj.select();
            return false;
        }

	return true;

}

function checkLength2(strTemp,key_char)
{
	var sum = 0;
        var c ='';
	for(var i=0;i<strTemp.length;i++)
	{
          c = strTemp.charAt(i);

          if(c==key_char)
          {
            sum = sum + 1;
          }

        }
        	return sum;
}


function  stringToArray2(src,key_char)
    {
       var s =src.indexOf(key_char);
       if(s> -1)return src.substring(0,s);
       else return src;

    }


//函数名：ValidateIP
//功能介绍：验证ip地址格式
//参数说明：
//obj：要检查的对象，
//name：要检查的对象的中文名称，
function ValidateIP(name,obj,notnull){
  var msg="";
  if(obj==null){
    msg="对象不能为空！";
    return false;
  }
  //检测非空
  if(notnull&&obj.value.trim()==""){
    msg="请输入"+name+"！";
    alert(msg);
    obj.focus();
    return false;
  }
  right_title="是一个非法的IP地址段！\nIP段为：xxx.xxx.xxx.xxx（xxx为0-255)！";
  re=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;

  if(!re.test(obj.value)){
    msg=name+right_title;
    alert(msg);
    obj.focus();
    return false;
  }
  return true;
}

function StrToDate(strDate){
var splitArray;
var str="";
str=strDate;
splitArray=str.split("-");
if(splitArray.length!=3)
  return new Date();
//for(var i=0; i<splitArray.length; i++)
//{
//  if( isNaN(splitArray) ) return new Date();
//}
return new Date(splitArray[0],splitArray[1]-1,splitArray[2]);
}

    
    function trimAll(s) {
	 if(s == null || s == "") {
	  return "";
	 }
	 
	 while (s.charAt(0) == " " || s.charAt(0) == "　" || s.charAt(0) == "\t" || s.charAt(0) == "\r" || s.charAt(0) == "\n") {
	  s = s.substring(1,s.length);
	 }
	 
	 while(s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "　" || s.charAt(s.length-1) == "\t" || s.charAt(s.length-1) == "\r" || s.charAt(s.length-1) == "\n") {
	  s = s.substring(0,s.length-1);
	 }
	 return s;
   }
   


//函数名：checkOnlyChinese
//功能介绍：检查只能含有汉字
//参数说明：要检查的字符串
//返回值：0：含有其他字符  1：只有汉字 
function checkOnlyChinese(strTemp)
{
	var i,sum;
	for(i=0;i<strTemp.length;i++)
	{
		if ((strTemp.charCodeAt(i)<0) || (strTemp.charCodeAt(i)>255)){
	      continue;
	      } else {
	      return 0;
	      }
	}
	return 1;
}  


function objFmoney(obj, alertStr){
	if(!obj.value || !obj.value.trim() || obj.value.trim().value=='')return false;
	if(isNaN(obj.value)){ alert(alertStr);	obj.value='';return false;}
	obj.value=fmoney(obj.value,2);
}
function fmoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      //t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
      t += l[i];
   }
   return t.split("").reverse().join("") + "." + r;
}
// 获取日期, 返回当前当前天 + AddDayCount 的日期, spe 为年月日之间的分隔符
// 如 当前日期为 20120202, GetDateStr(1,'/'), 将返回 2012/02/03
function GetDateStr(AddDayCount,spe) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    if(m.toString().length == 1){m = "0" + m;}	//	月份补零
    if(d.toString().length == 1){d = "0" + d;}	//	日期补零
    return y+spe+m+spe+d;
}


//==== 此三个函数为搜索下拉列表使用 =====//
function txtKeyup(selectName, inputText){
	if (selectName.selectedIndex!=-1){
  		selectName.options(selectName.selectedIndex).selected = false;
	}
	for (i=0;i<selectName.options.length;i++){
		if (selectName.options(i).text.indexOf(inputText.value)!=-1){
			selectName.options(i).selected = true;
			return;
  		}
	}
}

//功能：当text得到焦点时，清空内容
function txtOnfocus(oText){
	oText.value="";
}
  
function txtOnblur(oText) {
	if(oText.value=="null" ||oText.value=="" ) {
		oText.value="在此模糊搜索下拉框...";
		return;
	}
}
//==== 此三个函数为搜索下拉列表使用 =====//

//报表如果是html页面展示，则设置form表单target="_blank"，否则target=""
function changeTarget()
{
	var OutType=document.getElementById("OutType");
	if(OutType.value == "html" || OutType.value == "pdf"){
		document.getElementById("PageForm").target="_blank";
	} else {
		document.getElementById("PageForm").target="";
	}
}

// --列头全选框被单击---
function ChkAllClick(sonName, cbAllId){
   	var arrSon = document.getElementsByName(sonName);
	var cbAll = document.getElementById(cbAllId);
 	var tempState=cbAll.checked;
 	for(i=0;i<arrSon.length;i++) {
  		if(arrSon[i].checked!=tempState)
           arrSon[i].click();
 	}
}
 
//--子项复选框被单击---
function ChkSonClick(sonName, cbAllId) {
	 var arrSon = document.getElementsByName(sonName);
	 var cbAll = document.getElementById(cbAllId);
	 for(var i=0; i<arrSon.length; i++) {
	     if(!arrSon[i].checked) {
	     cbAll.checked = false;
	     return;
	     }
	 }
	 cbAll.checked = true;
}
