//日志对象，用于调试
function Logger() {
};
Logger.prototype.setLevel = function(l){
	this.l = l;
}
Logger.prototype.i = function(msg) {
	if (console && typeof console != undefined) {
		if(this.l){
			var level_ = eval("this.l");
			return eval("console."+level_+"('"+eval('msg')+"')");
		}
	}
}
Logger.prototype.err = function(msg) {
	if (typeof console != 'undefined') {
		return console.error(msg);
	}
}
var log = new Logger();
log.setLevel("debug");

/* 分页函数 */
function pagerFilter(data){
	if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
		data = {
			total: data.length,
			rows: data
		}
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		onSelectPage:function(pageNum, pageSize){
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh',{
				pageNumber:pageNum,
				pageSize:pageSize
			});
			dg.datagrid('loadData',data);
		}
	});
	if (!data.originalRows){
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}

$(function () {

	if($.validator){
		$.validator.setDefaults({//表单提交验证事件
			submitHandler: function(form) {
				if(window.afterValidate){
					$.messager.progress({text:"提交中..."});
					afterValidate();
				}
				form.submit();
			}
		});

		$(".validForm").validate();//form-a 验证
		//$(".op-form-b").validate();//form-b 验证
	}

	if ($('.a-extend').length) {
		var $s=$('#searchbar')
		var sh = $s.panel('options').height;
		$('.a-extend').toggle(function () {
			$(this).addClass('a-intend');
			$s.panel('resize',{height:26});
			$(window).trigger('resize');
		},function () {
			$(this).removeClass('a-intend');
			$s.panel('resize',{height:sh});
			$(window).trigger('resize');
		});
	}

	$('.txt-focus').each(function () {
		$(this).foucsText();
	});

	if ($('.dt-group').length) {
		$('.dt-group').click(function () {
			var $p = $(this).parent();
			if ($p.hasClass('dl-intend')) {
				$p.removeClass('dl-intend');
			}else {
				$p.addClass('dl-intend');
			}
		});
	}
});

function dataGridLoadSuccess(data, obj) {
	var this_ = obj ? $(obj) : $(this);
	// 无数据提示
	if (data.rows.length == 0) {
		// 表开头
		var body1 = this_.data().datagrid.dc.body1;
		// 数据列
		var body2 = this_.data().datagrid.dc.body2;
		body1
				.find('table')
				.html(
						'<tr class="datagrid-row"><td class="datagrid-td-rownumber">'
								+ '<div class="datagrid-cell-rownumber"></div></td></tr>');
		var b2table = body2.find('table');

		var width = b2table.find('tbody tr').width();
		b2table.width(width);

		b2table.find('tbody').html(
				"<tr class='datagrid-row'>"
						+ "<td style='text-align: center;'>没有数据</td></tr>");
	}
	if ('afterDataGridLoaded' in window) {
		afterDataGridLoaded(data);
	}
	var boxer_ = $(".colorbox");
	if(boxer_.length > 0){
		boxer_.boxer();
	}
}


function queryParamsHandler(){
	var strParams = '{';
	$("[name^='q_']").each(function () {
		strParams+='"'+$(this).attr('name')+'"';
		strParams+=':';
		strParams+='"'+$(this).val()+'"';
		strParams+=',';
	});
	strParams = strParams.substr(0, strParams.length-1);
	strParams += '}';
	return eval('('+strParams+')');
}


//根据cookie的name取得value
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=");
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1; 
    c_end=document.cookie.indexOf(";",c_start);
    if (c_end==-1) c_end=document.cookie.length;
    return decodeURIComponent(document.cookie.substring(c_start,c_end));
    } 
  }
return "";
}

function getErrMsg(errMsg) {
	if (errMsg) {
		if (errMsg.indexOf('"') == 0 && errMsg.lastIndexOf('"') == errMsg.length - 1) {
			errMsg = errMsg.substring(1, errMsg.length - 1);
		}
	}
	return errMsg;
}

//ajax防重复提交
function getCSRFTokenParam(){
	var cSRFTokenParam = 'CSRFToken=';
	cSRFTokenParam += $("input[name='CSRFToken']").val();
	cSRFTokenParam += '&CSRFMemKey=';
	cSRFTokenParam += $("input[name='CSRFMemKey']").val();
	return cSRFTokenParam;
}

function refrushCSRFToken(csrfToken){
	$("input[name='CSRFToken']").val(csrfToken);
}

String.prototype.startWith = function(s) { 
    if (s == null || s == "" || this.length == 0 || s.length > this.length) 
        return false; 
    if (this.substr(0, s.length) == s) 
         return true;
    else 
        return false; 
    return true; 
}