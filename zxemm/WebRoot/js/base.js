//字符串去除头尾空格
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g, "");
}

String.prototype.rtrim = function() {
    return this.replace(/(\s*$)/g, "");
}
/**common**/
var J_TOP_DOC = $(window.top.document);
var J_TOP_BODY = $(window.top.document.body);

var Myself = location.pathname;
var objform;
//定义进度条的显示话术
var showSendingText = "";
function $g(elmId) { return document.getElementById(elmId); }
function $j(elmId) { return $("#" + elmId); }
function $tv(elmId) { return $.trim($v(elmId)); }
function $c(elmId) { return $("#" + elmId).attr("checked"); }
function $name(nm) { return document.getElementsByName(nm); }
function $tag(cntr, tagName) {
    var o = cntr;
    if (o != Object) o = $g(cntr);
    return o.getElementsByTagName(tagName);
}


/*奇偶行变色
* escapeRows - 忽略最首行数
* tabName - 表ID
* odd - 奇行的样式或样式类名
* even - 偶行的样式或样式类名
*/
function altRow(escapeRows, tabName, odd, even) {
    var rows = $tag(tabName, "tr");
    for (var i = escapeRows; i < rows.length; ++i) {
        var argSty;
        if (i % 2 == 0) argSty = even;
        else argSty = odd;
        if (typeof (argSty) == "object") {
            for (var sty in argSty) {
                rows[i].style[sty] = argSty[sty];
            }
        } else {
            rows[i].className = argSty;
        }
    }
}
/*鼠标停留在行时变色
* escapeRows - 忽略最首行数
* tabName - 表ID
* over - 鼠标停留时行的样式或样式类名
*/
function hoverRow(escapeRows, tabName, over) {
    var rows = $tag(tabName, "tr");
    for (var i = escapeRows; i < rows.length; ++i) {
        $(rows[i]).hover(function() { $(this).addClass(over); }, function() { $(this).removeClass(over); });
    }
}
/*如果表头列具有排序字段，则鼠标经过表头单元格变色
* tabName - 表元素ID
*/
function hoverTabHeader(tabName, className) {
    if (tabName == null) tabName = "itemListTab";
    $("#" + tabName).find("th").each(function(i) {
        if (i != 0 && this.axis != "") {
            $(this).hover(function() { $(this).addClass(className); }, function() { $(this).removeClass(className); });
        }
    });
}
/*排序
* field - 排序字段
* dirc - 排序类型（升序|降序）
*/
function orderView(field, dirc) {
    location.href = toggleArg("order", field + "|" + dirc);
}
//设置表格全选状态
function selAll(CB) {
    if (CB != null && CB.type == 'checkbox') {
        if (CB.checked) {
            checkAll(true);

        }
        else
        { checkAll(false); }
    }
}

/*为表的每一行添加选中事件
* tabName - 表元素ID
*/
function initRowClick(escRow, tabName) {
    if (escRow == null) escRow = -1;
    if (tabName == null) tabName = "itemListTab";
    $j(tabName).find("tr").each(function(i) {
        if (i > escRow) {
            this.onclick = function() {
                var chk = $(this).find("input[name=chkItem]");
                if (!chk.attr("disabled")) {
                    if (chk.attr("checked")) chk.removeAttr("checked");
                    else chk.attr("checked", "checked");
                    itemCheckbox_changed();
                }
            }
        }
    });
}
/*全选
*/
function checkAll(chk, cntrId) {
    if (cntrId == null) cntrId = "itemListTab";
    var chks = $tag(cntrId, "input");
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].name != "chkAll") {
            if (!$(chks[i]).attr("disabled"))
            { chks[i].checked = chk; }
        }
    }
    itemCheckbox_changed();
}
/*反选
*/
function invertSelect(cntrId, exceptive, escRow) {
    var chks = $tag(cntrId, "input");
    for (var i = 0; i < chks.length; ++i) {
        if (exceptive == null) {
            if (!$(chks[i]).attr("disabled")) chks[i].checked = !chks[i].checked;
        } else {
            if (chks[i].name != exceptive)
                if (!$(chks[i]).attr("disabled")) chks[i].checked = !chks[i].checked;
        }
    }
    if (escRow != null) chks[escRow].checked = false;
    itemCheckbox_changed();
}
function getRowCntr(elm) {
    var prt = elm.parentNode;
    if (prt.tagName.toLowerCase() == "tr") return prt;
    else return getRowCntr(prt);
}
/*项目列表的项目checkbox.checked改变时触发的事件
* 无需传递参数
*/
function itemCheckbox_changed(escRow) {
    var itemChks = $name("chkItem");
    var allChk = $name("chkAll")[0];
    var checkAll = true;
    sltedItemCnt = 0;
    for (var i = 0; i < itemChks.length; ++i) {
        if (itemChks[i].checked) {
            ++sltedItemCnt;
            $(getRowCntr(itemChks[i])).addClass("chkedRow");
        } else {
            $(getRowCntr(itemChks[i])).removeClass("chkedRow");
            //  if(i!=0){
            checkAll = false;
            // }
        }
    }
    
    if (escRow != null) itemChks[escRow].checked = false;
    //allChk.checked = checkAll;
}
/*高iframe自适应高度
*/
function setFrameHeight(ifrmId, ctnrId) {
    if (top == null) {
        return;
    }
    var h = $g(ctnrId).scrollHeight;
    var frm = parent.document.getElementById(ifrmId);
    if (frm != null)
        frm.height = h;
}
/*设置加载提示
*/
function loading(show, msg) {
    if (show == null) show = true;
    var elm = $g("divLoading");
    if (elm == null) { elm = top.document.getElementById("divLoading"); }
    if (elm == null) {
        return;
    }
    if (!show) {
        $(elm).hide();
        return;
    }
    if (msg != null) {
        elm.innerHTML = "<img src=\"Skins\/Blue\/Img\/loading.gif\" alt=\"加载中...\" \/><span style=\"color:#fff\">" + msg + "<\/span>";
    }
    if (show == false) {
        elm.innerHTML = "<img src=\"Skins\/Blue\/Img\/loading.gif\" alt=\"加载中...\" \/><span style=\"color:#fff\">加载中...<\/span>";
    }
    $(elm).show();
}

/*设置顶部消息
*/
function showTopMsg(msg) { top.showMsg(msg); }
/*返回列表首页
*/
function gotoListHome() {
    loading();
    location.href = location.pathname;
}
/*新建对象
*/
function newObject(e) {
    loading();
    edit(e);
}

//格式化金额函数（s为金额数，n为保留小数）
function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 
function switchTable(src, tabId) {
    var jSrc = $(src);
    if (arguments.length == 1) {
        tabId = jSrc.attr("tab_id");
    }
    jSrc.parent().parent().find("a").removeClass("cur");
    jSrc.addClass("cur");
    jSrc.blur();
    $("table[name=editorTab]").hide();
    $j(tabId).show();
    setFrameHeight('frmEditor', 'body');
    var hdnCurTab = $j("hdnCurTab");
    if (hdnCurTab.length > 0) {
        hdnCurTab.val(tabId);
    }
}

function switchTable1(src, tabId) {
    var jSrc = $(src);
    if (arguments.length == 1) {
        tabId = jSrc.attr("tab_id");
    }
    jSrc.parent().parent().find("a").removeClass("cur");
    jSrc.addClass("cur");
    jSrc.blur();
    $("table[name=editorTab1]").hide();
    $j(tabId).show();
    setFrameHeight('frmEditor', 'body');
    var hdnCurTab = $j("hdnCurTab");
    if (hdnCurTab.length > 0) {
        hdnCurTab.val(tabId);
    }
}


