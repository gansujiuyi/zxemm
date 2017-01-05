/**
 * 上传图片公共工具
 * @param btnStr 控件ID
 * @param func 回调方法
 */
function uploadTools(btnStr, successfunc){
	$(btnStr).uploadify({
		width         : 118,
		height        : 117,
		'buttonText'  : "",
		'fileSizeLimit' : '2MB',  //文件上传的大小限制，单位是字节  最大4M
		'fileTypeExts' : '*.png;*.gif;*.jpg;*.bmp;*.jpeg', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc 
		'fileDesc' : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)', //上传文件类型说明
		swf           : 'js/uploadify/uploadify.swf',
		uploader      : 'file/uploadImg.do',		
		onUploadSuccess : successfunc
	});
}