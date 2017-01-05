//===================================
// 此文件为工具自动生成！
// 请勿手动修改本文件！
//===================================

/**
 * 根据ID创建银行下拉列表。
 * @param id
 */
function mkSelect2(id){
	var selectItem = $("#" + id);
	if(selectItem == null){
		return;
	}
	
	var html = "";
	for(var i = 0; i < banklist.length; i++){
		var bank = banklist[i];
		if(bank == null || bank.bankId == "" || bank.bankName == ""){
			continue;
		}
		html += "<option value='" + bank.bankId + "'>" + bank.bankName + "</option>";
	}
	
	selectItem.html(html);
	selectItem.select2();
}

var banklist = [
<#list banks as bank>
	{"bankId":"${bank.bankId}", "bankName":"${bank.bankName}"}<#rt>
	<#if (banks?size - 1 > bank_index)>
		<#t>,
	</#if>
</#list>
];
