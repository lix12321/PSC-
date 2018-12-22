__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/"); 
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
}

Date.prototype.format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

var bootPATH = __CreateJSPath("boot.js");


function easyDataFilter(data){
	if(data.status >=0){
		return data.data;
	}else{
		alert(data.desc);
		return [];
	}
}

function easyGridDataFilter(data){
	if(data.status >=0){
		data.rows = data.data;
		return data;
	}else{
		alert(data.desc);
		return {total:0,rows:[]};
	}
}

function comboboxFilter(data){
	if(data.status >=0){
		if ("true"==$(this).attr("shownone")) {
			var title = $(this).attr("nonetitle");
			var value = $(this).attr("nonevalue");
			var rs = data.data;
			var options = $(this).combobox("options");
			var tf = options.textField;
			var vf = options.valueField;
			var b = {};
			b[tf] = title;
			b[vf] = value;
			rs.unshift(b);
			return rs;
		} else {
			return data.data;
		}
	}else{
		alert(data.desc);
		return [];
	}
}

//debugger
zbui_debugger = true;
//
////zbuiui
//document.write('<script src="' + bootPATH + 'jquery.min.js" type="text/javascript"></sc' + 'ript>');
//document.write('<script src="' + bootPATH + 'jquery.easyui.min.js" type="text/javascript" ></sc' + 'ript>');
//// document.write('<script src="/pt/static/js/ui/easy/jquery.easyui.min.js" type="text/javascript" ></sc' + 'ript>');
//document.write('<script src="' + bootPATH + 'easyui-lang-zh_CN.js" type="text/javascript" ></sc' + 'ript>');
//document.write('<script src="' + bootPATH + 'easyui-extend.js" type="text/javascript" ></sc' + 'ript>');
//// document.write('<script src="/pt/static/js/ui/easy/easyui-extend.js" type="text/javascript" ></sc' + 'ript>');
//
//document.write('<script src="' + bootPATH + 'datagrid-groupview.js" type="text/javascript" ></sc' + 'ript>');
//// document.write('<script src="/pt/static/js/ui/easy/datagrid-groupview.js" type="text/javascript" ></sc' + 'ript>');
//
//document.write('<link href="' + bootPATH + 'themes/default/easyui.css" rel="stylesheet" type="text/css" />');
//document.write('<link href="' + bootPATH + 'themes/icon.css" rel="stylesheet" type="text/css" />');
////document.write('<link href="' + bootPATH + 'themes/easyuireset.css" rel="stylesheet" type="text/css" />');
//// document.write('<link href="/pt/static/css/easyuireset.css" rel="stylesheet" type="text/css" />');
//
//// 以下注释部分为原生控件替换时使用
//// document.write('<link href="' + bootPATH + 'themes/myui.css" rel="stylesheet" type="text/css" />');
//// document.write('<link href="/pt/static/css/myui.css" rel="stylesheet" type="text/css" />');
//// document.write('<link href="/pt/static/js/ui/liger/Source/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />');
//// document.write('<script src="/pt/static/js/ui/liger/Source/lib/ligerUI/js/core/base.js" type="text/javascript" ></sc' + 'ript>');
//// document.write('<script src="/pt/static/js/ui/liger/Source/lib/ligerUI/js/ligerui.all.js" type="text/javascript" ></sc' + 'ript>');


////////////////////////////////////////////////////////////////////////////////////////
function getCookie(sName) {
    var aCookie = document.cookie.split("; ");
    var lastMatch = null;
    for (var i = 0; i < aCookie.length; i++) {
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0]) {
            lastMatch = aCrumb;
        }
    }
    if (lastMatch) {
        var v = lastMatch[1];
        if (v === undefined) return v;
        return unescape(v);
    }
    return null;
}

ui = {};

ui.ajax = function(trancode,param,callback,f){
	if (typeof f == "undefined") {
		f = true;
	}
	$.ajax({
		type:'POST',
		data: {param : JSON.stringify(param)},
		async:f,
		url:"proxy/handle?transCode="+trancode,
		success:function(data) {
			if(callback){
				callback(data);
			}
		}
	});
}

function toPoint(percent){
    var str=percent.replace("%","");
    str= str/100;
    return str;
}

ui.openwin = function(url,width,height,title,onbeforeclose,istop) {
	if (istop&&top.ui.openwin) {
		top.ui.openwin(url,width,height,title);
		return;
	}

	if (!$("#win").length>0) {
		var w = $("#win");
		$("body").append("<div id='win'><iframe src="+url+" frameborder='0' scrolling='yes' style='border:0;width:100%;height:100%;'></iframe></div>");
	} else {
		$("#win").find("iframe").attr("src",url);
	}
	var w = document.body.clientWidth;
	var h = document.body.clientHeight;
	if(width.indexOf("%") > 0 ){
		width =  w*toPoint(width);
	}
	if(height.indexOf("%") > 0 ){
		height =  h*toPoint(height);
	}
	$('#win').window({    
		width:width,
		height:height,
		title:title, 
		top:(h-height)/2,
		left:(w-width)/2,
		modal:true,
		onBeforeClose:onbeforeclose
	});
}

ui.closewin = function() {
	parent.$('#win').window("close");
}

ui.textbox = function(textbox){
	this.textbox = textbox;
	this.options ={};
	this.options.label = this.textbox.textbox("options").label;
	this.options.require = this.textbox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.textbox.textbox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
}

ui.label = function(label){
	this.label = label;
}

ui.textbox.prototype = {
	getValue: function(){
		return this.textbox.textbox("getValue");
	},
	setValue: function(value){
		return this.textbox.textbox("setValue",value);
	},
	getText: function(){
		return this.textbox.textbox("getText");
	},
	setText: function(text){
		return this.textbox.textbox("setText",text);
	},
	readonly: function(value) {
		return this.textbox.textbox("readonly",value);
	},
	disable: function(){
		return this.textbox.textbox("disable");
	},
	enable: function(){
		return this.textbox.textbox("enable");
	},
	clear: function(){
		return this.textbox.textbox("setValue","");
	},
	reset: function(){
		return this.textbox.textbox("reset");
	},
	enableValidation: function(){
		return this.textbox.textbox("enableValidation");
	},
	disableValidation: function(){
		return this.textbox.textbox("disableValidation");
	}
}

ui.DataGrid = function(grid,url){
	this.grid = grid;
	this.grid.datagrid('options').url = url;
	this.options = this.grid.datagrid('options');
	this.gridid = this.grid[0].id;
	this.grid.datagrid('options')
	var $form = $("#form"+this.gridid);
	if ($form.length>0) {
		$form.find("input[name='gridpath']").val(pagePath);
		$form.find("input[name='gridid']").val(this.gridid);
		$form.find("input[name='transCode']").val(this.options.trancode);
		var columns = this.options.columns[0],ncolmn=[];
		$.each(columns,function(i,c){
			if (!c.checkbox)
				ncolmn.push(c.field);
		});
		$form.find("input[name='cols']").val(ncolmn.join(","));
		var title;
		if (this.options.title||this.options.title=="") {
			title = this.options.title;
		} else {
			title = this.gridid;
		}
		$form.find("input[name='title']").val(title);
	}
}

ui.DataGrid.prototype = {
	load: function(params){
		if(!params){
			params = {};
		}
		var options = this.grid.datagrid('options');
		if(options.pagination){
			params.pageIndex = options.pageNumber;
			params.pageSize  = options.pageSize;
		}
		this.grid.datagrid("load",{'param': JSON.stringify(params)});
	},
	reload: function(params){
		if(!params){
			params = {};
		}
		var options = this.grid.datagrid('options');
		if(options.pagination){
			params.pageIndex = options.pageNumber;
			params.pageSize  = options.pageSize;
		}

		this.grid.datagrid("reload",{'param': JSON.stringify(params)});
	},
	getEditor: function(param){
		return this.grid.datagrid('getEditor', param);
	},
	getSelected: function(){
		return this.grid.datagrid("getSelected");
	},
	getChecked: function(){
		return this.grid.datagrid("getChecked");
	},
	sort: function(param){
		return this.grid.datagrid("sort",param);
	},
	deleteRow: function(index){
		return this.grid.datagrid("deleteRow",index);
	},
	insertRow: function(param){
		return this.grid.datagrid("insertRow",param);
	},
	appendRow: function(row){
		return this.grid.datagrid("appendRow",row);
	},
	updateRow: function(param){
		return this.grid.datagrid("updateRow",param);
	},
	validateRow: function(index){
		return this.grid.datagrid("validateRow",index);
	},
	refreshRow: function(index){
		return this.grid.datagrid("refreshRow",index);
	},
	highlightRow: function(index){
		return this.grid.datagrid("highlightRow",index);
	},
	scrollTo: function(index){
		return this.grid.datagrid("scrollTo",index);
	},
	getData: function(){		
		return this.grid.datagrid("getData");
	},
	autoSizeColumn: function(field){
		return this.grid.datagrid("autoSizeColumn");
	},
	fitColumns: function(){
		return this.grid.datagrid("fitColumns");
	},
	getColumnOption: function(field){
		return this.grid.datagrid("getColumnOption",field);
	},
	getColumnFields: function(frozen){
		return this.grid.datagrid("getColumnFields",frozen);
	},
	selectAll: function() {
		return this.grid.datagrid("selectAll");
	},
	unselectAll: function() {
		return this.grid.datagrid("unselectAll");
	},
	selectRow: function(index) {
		return this.grid.datagrid("selectRow",index);
	},
	selectRecord: function(idValue) {
		return this.grid.datagrid("selectRecord",idValue);
	},
	unselectRow: function(index) {
		return this.grid.datagrid("unselectRow",index);
	},
	checkAll: function() {
		return this.grid.datagrid("checkAll");
	},
	uncheckAll: function() {
		return this.grid.datagrid("uncheckAll");
	},
	checkRow: function(index) {
		return this.grid.datagrid("checkRow",index);
	},
	uncheckRow: function(index) {
		return this.grid.datagrid("unselectAll",index);
	},
	loadData: function(data) {
		if (data.status!==undefined) {
			return this.grid.datagrid("loadData",data);
		} else {
			return this.grid.datagrid("loadData",{status:0,data:data});
		}
	},
	getRowIndex: function(row) {
		return this.grid.datagrid("getRowIndex",row);
	},
	endEdit: function(index) {
		return this.grid.datagrid("endEdit",index);
	},
	enableCellEditing: function() {
		this.grid.datagrid().datagrid('enableCellEditing');
	},
	disableCellEditing: function() {
 		this.grid.datagrid().datagrid('disableCellEditing');
	},
	exportExcel: function() {
		var $form = $("#form"+this.gridid);
		var nparam = {};
		if (this.options.queryParams&&this.options.queryParams.param)
	 		var param=JSON.parse(this.options.queryParams.param);
	 	    for(var p in param) {
	 	    	if (p!=="pageIndex"&&p!=="pageSize") {
	 	    		nparam[p] = param[p];
	 	    	}
	 	    }
		$form.find("input[name='param']").val(JSON.stringify(nparam));
		$("#form"+this.gridid).submit();
	}
}

ui.combobox = function(combobox,_options){
	this.combobox = combobox;
	this.options ={};
	this.options.label = this.combobox.combobox("options").label;
	this.options.require = this.combobox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.combobox.combobox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	var params = {};
	if(_options.loadParams){
		params = _options.loadParams;
	}
	this.combobox.combobox('options').queryParams = {param : JSON.stringify(params)} ;
	            
	if(_options.autoload == 'true'){
		this.combobox.combobox("reload",_options.url);
	}else{
		this.combobox.combobox('options').url = _options.url;
	}
}

ui.combobox.prototype = {
	load: function(params){
		this.combobox.combobox('options').queryParams = {param : JSON.stringify(params)} ;
		this.combobox.combobox("reload");
	},
	getValue: function(){
		return this.combobox.combobox("getValues").join(",");
	},
	getValues: function(){
		return this.combobox.combobox("getValues");
	},
	setValue: function(value){
		if (!value) {
			this.combobox.combobox("setValue","");
			return;
		}
		value = value+'';
		if (value.indexOf(",")>0) {
			return this.combobox.combobox("setValues",value.split(","));
		} else {
			return this.combobox.combobox("setValue",value);
		}
	},
	setValues: function(values){
		return this.combobox.combobox("setValues",values);
	},
	getText: function(){
		return this.combobox.combobox("getText");
	},
	setText: function(text){
		return this.combobox.combobox("setText",text);
	},
//	reload: function(url){
//		if(!url){
//			return this.combobox.combobox("reload");
//		}else{
//			url = "proxy/handle?transCode="+url;
//			return this.combobox.combobox("reload",url);
//		}
//	},
	select: function(value){
		return this.combobox.combobox("select",value);
	},
	showPanel: function(){
		return this.combobox.combobox("showPanel");
	},
	hidePanel: function(){
		return this.combobox.combobox("hidePanel");
	},
	disable: function(){
		return this.combobox.combobox("disable");
	},
	enable: function(){
		return this.combobox.combobox("enable");
	},
	clear: function(){
		return this.combobox.combobox("clear");
	},
	reset: function(){
		return this.combobox.combobox("reset");
	},
	loadData: function(data) {
		if (data.status!==undefined) {
			return this.combobox.combobox("loadData",data);
		} else {
			return this.combobox.combobox("loadData",{status:0,data:data});
		}
	},
	readonly: function(data){
		return this.combobox.combobox("readonly",data);
	},
	enableValidation: function(){
		return this.combobox.combobox("enableValidation");
	},
	disableValidation: function(){
		return this.combobox.combobox("disableValidation");
	}
}

ui.combogrid  = function(combogrid,_options){
	this.combogrid  = combogrid ;
	this.options ={};
	this.options.label = this.combogrid.combogrid("options").label;
	this.options.require = this.combogrid.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.combogrid.combogrid({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	var params = {};
	if(_options.loadParams){
		params = _options.loadParams;
	}
	this.combogrid.combogrid('options').queryParams = {param : JSON.stringify(params)} ;
	            
	if(_options.autoload == 'true'){
		this.combogrid.combogrid({
			url:_options.url
		});
	}else{
		this.combogrid.combogrid('options').url = _options.url;
	}
}

ui.combogrid.prototype = {
	clear: function(){
		return this.combogrid.combogrid("clear");
	},
	grid: function(){
		return this.combogrid.combogrid("grid");
	},
	options: function(){
		return this.combogrid.combogrid("options");
	},
	setValue: function(value){
		return this.combogrid.combogrid("setValue",value);
	},
	setValues: function(values){
		return this.combogrid.combogrid("setValues",values);
	},
	readonly: function(value){
		return this.combogrid.combogrid("readonly",value);
	},
	getValue: function(){
		return this.combogrid.combogrid("getValue");
	},
	getText: function() {
		return this.combogrid.combogrid("getValue");
	},
	setText: function(text) {
		return this.combogrid.combogrid("setText",text);
	},
	enableValidation: function(){
		return this.combogrid.combogrid("enableValidation");
	},
	disableValidation: function(){
		return this.combogrid.combogrid("disableValidation");
	}
}
	
ui.combotreegrid  = function(combotreegrid,_options){
	this.combotreegrid  = combotreegrid ;
	this.options ={};
	this.options.label = this.combotreegrid.combotreegrid("options").label;
	this.options.require = this.combotreegrid.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.combotreegrid.combotreegrid({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	var params = {};
	if(_options.loadParams){
		params = _options.loadParams;
	}
	this._options = _options;
	this.combotreegrid.combotreegrid('options').queryParams = {isTree:'true',param : JSON.stringify(params)} ;
	            
	if(_options.autoload == 'true'){
		this.combotreegrid.combotreegrid({
			url:_options.url
		});
	}else{
		this.combotreegrid.combotreegrid('options').url = _options.url;
	}
}

ui.combotreegrid.prototype = {
	clear: function(){
		return this.combotreegrid.combotreegrid("clear");
	},
	grid: function(){
		return this.combotreegrid.combotreegrid("grid");
	},
	options: function(){
		return this.combotreegrid.combotreegrid("options");
	},
	setValue: function(value){
		if (!value) {
			this.combotreegrid.combotreegrid("setValue","");
			return;
		}
		value = value+'';
		if (value.indexOf(",")>0) {
			return this.combotreegrid.combotreegrid("setValues",value.split(","));
		} else {
			return this.combotreegrid.combotreegrid("setValue",value);
		}
	},
	setValues: function(values){
		return this.combotreegrid.combotreegrid("setValues",values);
	},
	getValue: function(){
		return this.combotreegrid.combotreegrid("getValues").join(",");
	},
	getValues: function(){
		return this.combotreegrid.combotreegrid("getValues");
	},
	readonly: function(value){
		return this.combotreegrid.combotreegrid("readonly",value);
	},
	enableValidation: function(){
		return this.combotreegrid.combotreegrid("enableValidation");
	},
	disableValidation: function(){
		return this.combotreegrid.combotreegrid("disableValidation");
	}
}

ui.form = function(form,_options){
	this.form = form;
	this.filelds = {};
	this._options = _options;
	if (_options.url) {
		this.url = _options.url;
		this.form.form("options").url = _options.url+"&hasFile=true";
	}
}

ui.form.prototype = {
	getData : function(){
		var datas = {};
		for(var p in this.filelds ){
			datas[p]=this.filelds[p].getValue();
		}
		return datas;
	},
	getNotNullData : function(){
		var datas = {};
		for(var p in this.filelds ){
			var v = this.filelds[p].getValue();
			if(v && v != ''){
				datas[p]=this.filelds[p].getValue();
			}
			
		}
		return datas;
	},
	load: function(rdata){
		 //this.clear();
		for(var p in rdata){
			var item = this.filelds[p];
			var date =rdata[p];
			if(item &&  date){
				if (item.datebox) {
					var odate = parsevartodate(date);
					var mydate = odate.format("yyyy-MM-dd");
					this.filelds[p].setValue(mydate);
				} else if (item.datetimebox) {
					var odate = parsevartodate(date);
					var mydate = odate.format("yyyy-MM-dd hh:mm:ss");
					this.filelds[p].setValue(mydate);
				} else {
					this.filelds[p].setValue(date);
				}
			}
		}
	},
	validate: function(){
		return this.form.form("validate");
	},
	clear: function(){
		for(var p in this.filelds ){
			this.filelds[p].clear();
		}
	},
	reset: function(){
		return this.form.form("reset");
	},
	submit: function(){
		return this.form.form("submit");
	},
	enableValidation: function(){
		return this.form.form("enableValidation");
	},
	disableValidation: function(){
		return this.form.form("disableValidation");
	},
	readonly: function(f){
		for(var p in this.filelds ){
			var item = this.filelds[p];
			try {
				item.readonly(f);
			} catch(err) {
				//没有readonly方法,执行原生执行方式
				if (item.data) {
					var len = item.data.length,a=[];
					for (var i = 0; i < len; i++) {
						a.push(i);
					}
					if (f) {
						item.setdisabled(a);
					} else {
						item.setdisabled([]);
					}
					
				}
			}
		}
	}
}

function parsevartodate(date) {
	if (14==date.length) {
		var year = parseInt(date.substring(0,4));
		var month = parseInt(date.substring(4,6));
		var day = parseInt(date.substring(6,8));
		var hour = parseInt(date.substring(8,10));
		var minutes = parseInt(date.substring(10,12));
		var second = parseInt(date.substring(12,14));
		return new Date(year,month-1,day,hour,minutes,second);
	} else {
		return new Date();
	}
}

ui.combotree  = function(combotree,_options){
	
	this.combotree  = combotree ;
	this.options ={};
	this.options.label = this.combotree.combotree("options").label;
	this.options.require = this.combotree.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.combotree.combotree({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	this._options = _options;
	var params = {};
	if(_options.loadParams){
		params = _options.loadParams;
	}
	var nparam = {isTree:'true',param : JSON.stringify(params)};
    if ("true" == this.combotree.attr("isasyn")) {
		nparam.isasyn = 'true';
	}
	
	if(_options.autoload=='true'){
		this.combotree.combotree({
			url: _options.url,
			queryParams:nparam
		});
	}else{
		this.combotree.combotree('options').url = _options.url;
		this.combotree.combotree('options').queryParams = nparam;
	}
	
}

ui.combotree.prototype = {
	load: function(params){
		this.combotree.combotree({
			url: this._options.url,
			queryParams:{isTree:'true',param : JSON.stringify(params)}
		});
	},
	getValue: function(){
		return this.combotree.combotree("getValues").join(",");
	},
	getValues: function(){
		return this.combotree.combotree("getValues");
	},
	setValue: function(value){
		if (!value) {
			this.combotree.combotree("setValue","");
			return;
		}
		value = value+'';
		if (value.indexOf(",")>0) {
			return this.combotree.combotree("setValues",value.split(","));
		} else {
			return this.combotree.combotree("setValue",value);
		}
	},
	setValues: function(values){
		return this.combotree.combotree("setValues",values);
	},
	readonly: function(value){
		return this.combotree.combotree("readonly",value);
	},
	collapseAll: function(){
		return this.combotree.combotree("tree").tree("collapseAll");
	},
	loadData: function(data) {
		if (data.status!==undefined) {
			return this.combotree.combotree("loadData",data);
		} else {
			return this.combotree.combotree("loadData",{status:0,data:data});
		}
	},
	enableValidation: function(){
		return this.combotree.combotree("enableValidation");
	},
	disableValidation: function(){
		return this.combotree.combotree("disableValidation");
	},
	clear: function(){
		return this.combotree.combotree("clear");
	},
	options: function() {
 		return this.combotree.combotree("options");
	},
	setoptions: function(name,value) {
		return this.combotree.combotree("options")[name] = value;
	},
	getoptions: function(name){
		return this.combotree.combotree("options")[name];
	}
}

ui.switchbutton  = function(switchbutton,_options){
	this.switchbutton  = switchbutton;
	if (_options) {
		this._options = _options;
	}
}

ui.switchbutton.prototype = {
	options: function(){
		return this.switchbutton.switchbutton("options");
	},
	resize: function(param){
		return this.switchbutton.switchbutton("resize",param);
	},
	disable: function(){
		return this.switchbutton.switchbutton("disable");
	},
	enable: function(){
		return this.switchbutton.switchbutton("enable");
	},
	readonly: function(mode){
		return this.switchbutton.switchbutton("readonly",mode);
	},
	check: function(){
		return this.switchbutton.switchbutton("check");
	},
	uncheck: function(){
		return this.switchbutton.switchbutton("uncheck");
	},
	clear: function(){
		return this.switchbutton.switchbutton("clear");
	},
	reset: function(){
		return this.switchbutton.switchbutton("reset");
	},
	setValue: function(value){
		return this.switchbutton.switchbutton("setValue",value);
	}
}

ui.numberbox  = function(numberbox,_options){
	this.numberbox  = numberbox ;
	this.options ={};
	this.options.label = this.numberbox.numberbox("options").label;
	this.options.require = this.numberbox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.numberbox.numberbox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
}

ui.numberbox.prototype = {
	clear: function(){
		return this.numberbox.numberbox("clear");
	},
	destroy: function(){
		return this.numberbox.numberbox("destroy");
	},
	disable: function(){
		return this.numberbox.numberbox("disable");
	},
	enable: function(){
		return this.numberbox.numberbox("enable");
	},
	fix: function(){
		return this.numberbox.numberbox("fix");
	},
	getValue: function(){
		return this.numberbox.numberbox("getValue");
	},
	options: function(){
		return this.numberbox.numberbox("options");
	},
	reset: function(){
		return this.numberbox.numberbox("reset");
	},
	setValue: function(value){
		return this.numberbox.numberbox("setValue",value);
	},
	readonly: function(value){
		return this.numberbox.numberbox("readonly",value);
	},
	enableValidation: function(){
		return this.numberbox.numberbox("enableValidation");
	},
	disableValidation: function(){
		return this.numberbox.numberbox("disableValidation");
	}
}

ui.datebox  = function(datebox,_options){
	this.datebox  = datebox ;
	this.options ={};
	this.options.label = this.datebox.datebox("options").label;
	this.options.require = this.datebox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.datebox.datebox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
	
}

ui.datebox.prototype ={
	setValue:function(r){
		return this.datebox.datebox("setValue",r);
	},
	calendar: function(){
		return this.datebox.datebox("calendar");
	},
	options: function(){
		return this.datebox.datebox("options");
	},
	getValue: function(f){
		if (f) {
			return this.datebox.datebox("getValue");
		}
		else {
			var text = this.datebox.datebox("getText");
			if (text=="") {
				return "";
			} else {
				var date = this.datebox.datebox("calendar").calendar("options").current;
				date = new Date(date);
				return date.format("yyyyMMddhhmmss");
			}
		}
	},
	readonly: function(value){
		return this.datebox.datebox("readonly",value);
	},
	enableValidation: function(){
		return this.datebox.datebox("enableValidation");
	},
	disableValidation: function(){
		return this.datebox.datebox("disableValidation");
	},
	clear: function(){
		return this.datebox.datebox("clear");
	}
}

ui.timespinner = function(timespinner,_options){
	this.timespinner  = timespinner ;
	this.options ={};
	this.options.label = this.timespinner.timespinner("options").label;
	this.options.require = this.timespinner.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.timespinner.timespinner({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
}

ui.timespinner.prototype = {
	options: function() {
		return this.timespinner.timespinner("options");
	},
	setValue: function(value) {
		this.timespinner.timespinner("setValue",value);
	},
	getHours: function() {
		return this.timespinner.timespinner("getHours");
	},
	getMinutes: function() {
		return this.timespinner.timespinner("getMinutes");
	},
	getSeconds: function() {
		return this.timespinner.timespinner("getSeconds");
	},
	getValue: function() {
		return this.timespinner.timespinner("getValue");
	},
	readonly: function(value) {
		this.timespinner.timespinner("readonly",value);
	},
	clear: function(){
		return this.timespinner.timespinner("clear");
	}
}

ui.datetimespinner  = function(datetimespinner,_options){
	this.datetimespinner  = datetimespinner ;
	this.options ={};
	this.options.label = this.datetimespinner.datetimespinner("options").label;
	this.options.require = this.datetimespinner.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.datetimespinner.datetimespinner({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
}

ui.datetimespinner.prototype = {
	getHours: function(){
		return this.datetimespinner.datetimespinner("getHours");
	},
	getMinutes: function(){
		return this.datetimespinner.datetimespinner("getMinutes");
	},
	getSeconds: function(){
		return this.datetimespinner.datetimespinner("getSeconds");
	},
	options: function(){
		return this.datetimespinner.datetimespinner("options");
	},
	setValue: function(value){
		return this.datetimespinner.datetimespinner("setValue");
	},
	readonly: function(value){
		return this.datetimespinner.datetimespinner("readonly",value);
	},
	getValue: function(){
		return this.datetimespinner.datetimespinner("getValue");
	},
	enableValidation: function(){
		return this.datetimespinner.datetimespinner("enableValidation");
	},
	disableValidation: function(){
		return this.datetimespinner.datetimespinner("disableValidation");
	},
	clear: function(){
		return this.datetimespinner.datetimespinner("clear");
	}
}

ui.filebox  = function(filebox,_options){
	this.filebox  = filebox ;
	this.options ={};
	this.options.label = this.filebox.filebox("options").label;
	this.options.require = this.filebox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.filebox.filebox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
}

ui.filebox.prototype = {
	setValue: function(value){
		return this.filebox.textbox("setValue",value);
	},
	getValue: function(){
		return this.filebox.textbox("getValue");
	},
	readonly: function(value){
		return this.filebox.filebox("readonly",value);
	},
	enableValidation: function(){
		return this.filebox.filebox("enableValidation");
	},
	disableValidation: function(){
		return this.filebox.filebox("disableValidation");
	},
	clear: function(){
		return this.filebox.filebox("clear");
	}
}

ui.tree  = function(tree,_options){
	this.tree  = tree ;
	var params = {};
	if(_options.loadParams){
		params = _options.loadParams;
	}
	this._options = _options;
    var nparam = {isTree:'true',param : JSON.stringify(params)};
    if ("true" == this.tree.attr("isasyn")) {
		nparam.isasyn = 'true';
	}

	if(_options.autoload=='true'){
		this.tree.tree({
			url: _options.url,
			queryParams:nparam
		});
	}else{
		this.tree.tree('options').url = _options.url;
		this.tree.tree('options').queryParams = nparam;
	}
}


ui.tree.prototype = {
	load: function(params){
		this.tree.tree({
			url: this._options.url,
			queryParams:{isTree:'true',param : JSON.stringify(params)}
		});
	},
	append: function(param){
		return this.tree.tree("append",param);
	},
	beginEdit: function(target){
		return this.tree.tree("beginEdit",target);
	},
	cancelEdit: function(target){
		return this.tree.tree("cancelEdit",target);
	},
	endEdit: function(target){
		return this.tree.tree("endEdit",target);
	},
	check: function(target){
		return this.tree.tree("check",target);
	},
	collapse: function(target){
		return this.tree.tree("collapse",target);
	},
	collapseAll: function(target){
		return this.tree.tree("collapseAll",target);
	},
	disableDnd: function(){
		return this.tree.tree("disableDnd");
	},
	enableDnd: function(){
		return this.tree.tree("enableDnd");
	},
	expand: function(target){
		return this.tree.tree("expand",target);
	},
	expandAll: function(target){
		return this.tree.tree("expandAll",target);
	},
	expandTo: function(target){
		return this.tree.tree("expandTo",target);
	},
	find: function(id){
		return this.tree.tree("find",id);
	},
	getChecked: function(state){
		return this.tree.tree("getChecked",state);
	},
	getChildren: function(target){
		return this.tree.tree("getChildren",target);
	},
	getData: function(target){
		return this.tree.tree("getData",target);
	},
	getNode: function(target){
		return this.tree.tree("getNode",target);
	},
	getParent: function(target){
		return this.tree.tree("getParent",target);
	},
	getRoot: function(nodeEl){
		return this.tree.tree("getRoot",nodeEl);
	},
	getRoots: function(){
		return this.tree.tree("getRoots");
	},
	getSelected: function(){
		return this.tree.tree("getSelected");
	},
	insert: function(param){
		return this.tree.tree("insert",param);
	},
	isLeaf: function(target){
		return this.tree.tree("isLeaf",target);
	},
	loadData: function(data) {
		if (data.status!==undefined) {
			return this.tree.tree("loadData",data);
		} else {
			return this.tree.tree("loadData",{status:0,data:data});
		}
	},
	options: function() {
 		return this.tree.tree("options");
	},
	getoptions: function(name){
		return this.tree.tree("options")[name];
	},
	setoptions: function(name,value) {
		return this.tree.tree("options")[name] = value;
	},
	pop: function(target){
		return this.tree.tree("pop",target);
	},
	reload: function(target){
		return this.tree.tree("reload",target);
	},
	remove: function(target){
		return this.tree.tree("remove",target);
	},
	scrollTo: function(target){
		return this.tree.tree("scrollTo",target);
	},
	select: function(target){
		return this.tree.tree("select",target);
	},
	toggle: function(target){
		return this.tree.tree("toggle",target);
	},
	uncheck: function(target){
		return this.tree.tree("uncheck",target);
	},
	update: function(param){
		return this.tree.tree("update",param);
	},
	disablecheckbox: function(){
		var $n = this.tree.find('span.tree-checkbox');
		if ($n.length>0) {
			for (var i = 0; i < $n.length; i++) {
				var $span = $($n[i]).parent().find("span");
				if (!$span.hasClass("tree-disabled")) {
					$($n[i]).after("<span class='tree-disabled' style='left:"+$n[i].offsetLeft+"px'></span>");
				}
			}
		}
	},
	enablechekbox: function(){
		this.tree.find("span.tree-disabled").remove();
	}
}


ui.tabs  = function(tabs,_options){
	this.tabs  = tabs ;
	if (_options) {
		this._options = _options;
	}
}

ui.tabs.prototype = {
	add: function(options){
		return this.tabs.tabs("add",options);
	},
	options: function(){
		return this.tabs.tabs("options");
	},
	tabs: function(){
		return this.tabs.tabs("tabs");
	},
	resize: function(){
		return this.tabs.tabs("resize");
	},
	close: function(which){
		return this.tabs.tabs("close",which);
	},
	getTab: function(which){
		return this.tabs.tabs("getTab",which);
	},
	getTabIndex: function(tab){
		return this.tabs.tabs("getTabIndex",tab);
	},
	getSelected: function(){
		return this.tabs.tabs("getSelected");
	},
	select: function(which){
		return this.tabs.tabs("select",which);
	},
	unselect: function(which){
		return this.tabs.tabs("unselect",which);
	},
	showHeader: function(){
		return this.tabs.tabs("showHeader");
	},
	hideHeader: function(){
		return this.tabs.tabs("hideHeader");
	},
	exists: function(which){
		return this.tabs.tabs("exists",which);
	},
	update: function(param){
		return this.tabs.tabs("update",param);
	},
	enableTab: function(which){
		return this.tabs.tabs("enableTab",which);
	},
	disableTab: function(which){
		return this.tabs.tabs("disableTab",which);
	},
	scrollBy: function(deltaX){
		return this.tabs.tabs("scrollBy",deltaX);
	}
}

ui.tabitem  = function(tabitem,_options){
	this.tabitem  = tabitem ;
	this._options = _options;
	var id = "iframe"+this.tabitem[0].id;
	if ($("#"+id).length>0) {
		$("#"+id).attr("src",_options.url);
	}
}

ui.menu  = function(menu,_options){
	this.menu  = menu ;
	if (_options) {
		this._options = _options;
	}
}

ui.menu.prototype = {
	appendItem: function(options){
		return this.menu.menu("appendItem",options);
	},
	destroy: function(){
		return this.menu.menu("destroy");
	},
	disableItem: function(itemEl){
		return this.menu.menu("disableItem",itemEl);
	},
	enableItem: function(itemEl){
		return this.menu.menu("enableItem",itemEl);
	},
	findItem: function(text){
		return this.menu.menu("findItem",text);
	},
	getItem: function(itemEl){
		return this.menu.menu("getItem",itemEl);
	},
	hide: function(){
		return this.menu.menu("hide");
	},
	options: function(){
		return this.menu.menu("options");
	},
	removeItem: function(itemEl){
		return this.menu.menu("removeItem",itemEl);
	},
	setIcon: function(param){
		return this.menu.menu("setIcon",param);
	},
	setText: function(param){
		return this.menu.menu("setText",param);
	},
	show: function(pos){
		return this.menu.menu("show",pos);
	},
}

ui.treegrid  = function(treegrid,_options){
	this.treegrid  = treegrid ;
	var params = {};
	if(_options.loadParams){
		params = _options.loadParams;
	}
	params.isTree = 'true';

	this._options = _options;
	
	if(_options.autoload=='true'){
		this.treegrid.treegrid({
			url: _options.url,
			queryParams:{isTree:'true',param : JSON.stringify(params)}
		});
	}else{
		this.treegrid.treegrid('options').url = _options.url;
	}
}

ui.treegrid.prototype = {
	append: function(param){
		return this.treegrid.treegrid("append",param);
	},
	beginEdit: function(id){
		return this.treegrid.treegrid("beginEdit",id);
	},
	cancelEdit: function(id){
		return this.treegrid.treegrid("cancelEdit",id);
	},
	collapse: function(id){
		return this.treegrid.treegrid("collapse",id);
	},
	collapseAll: function(id){
		return this.treegrid.treegrid("collapseAll",id);
	},
	endEdit: function(id){
		return this.treegrid.treegrid("endEdit",id);
	},
	expand: function(id){
		return this.treegrid.treegrid("expand",id);
	},
	expandAll: function(id){
		return this.treegrid.treegrid("expandAll",id);
	},
	expandTo: function(id){
		return this.treegrid.treegrid("expandTo",id);
	},
	find: function(id){
		return this.treegrid.treegrid("find",id);
	},
	fixRowHeight: function(id){
		return this.treegrid.treegrid("fixRowHeight",id);
	},
	getChildren: function(id){
		return this.treegrid.treegrid("getChildren",id);
	},
	getData: function(){
		return this.treegrid.treegrid("getData");
	},
	getEditor: function(param){
		return this.treegrid.treegrid("getEditor",param);
	},
	getEditors: function(id){
		return this.treegrid.treegrid("getEditors",id);
	},
	getFooterRows: function(){
		return this.treegrid.treegrid("getFooterRows");
	},
	getLevel: function(id){
		return this.treegrid.treegrid("getLevel",id);
	},
	getParent: function(id){
		return this.treegrid.treegrid("getParent",id);
	},
	getRoot: function(){
		return this.treegrid.treegrid("getRoot");
	},
	getRoots: function(){
		return this.treegrid.treegrid("getRoots");
	},
	getSelected: function(){
		return this.treegrid.treegrid("getSelected");
	},
	getSelections: function(){
		return this.treegrid.treegrid("getSelections");
	},
	insert: function(param){
		return this.treegrid.treegrid("insert",param);
	},
	load: function(param){
		return this.treegrid.treegrid("load",{isTree:'true',param : JSON.stringify(param)});
	},
	loadData: function(data) {
		if (data.status!==undefined) {
			return this.treegrid.treegrid("loadData",data);
		} else {
			return this.treegrid.treegrid("loadData",{status:0,data:data});
		}
	},
	options: function(){
		return this.treegrid.treegrid("options");
	},
	pop: function(id){
		return this.treegrid.treegrid("pop",id);
	},
	refresh: function(id){
		return this.treegrid.treegrid("refresh",id);
	},
	reload: function(id){
		return this.treegrid.treegrid("reload",id);
	},
	reloadFooter: function(footer){
		return this.treegrid.treegrid("reloadFooter",footer);
	},
	remove: function(id){
		return this.treegrid.treegrid("remove",id);
	},
	resize: function(options){
		return this.treegrid.treegrid("resize",options);
	},
	select: function(id){
		return this.treegrid.treegrid("select",id);
	},
	selectAll: function(){
		return this.treegrid.treegrid("selectAll");
	},
	toggle: function(id){
		return this.treegrid.treegrid("toggle",id);
	},
	unselect: function(id){
		return this.treegrid.treegrid("unselect",id);
	},
	unselectAll: function(){
		return this.treegrid.treegrid("unselectAll");
	},
	update: function(param){
		return this.treegrid.treegrid("update",param);
	}
}

ui.panel  = function(panel,_options){
	this.panel  = panel ;
	if (_options) {
		this._options = _options;
	}
}

ui.panel.prototype = {
	body: function(){
		return this.panel.panel("body");
	},
	close: function(forceClose){
		return this.panel.panel("close",forceClose);
	},
	collapse: function(animate){
		return this.panel.panel("collapse",animate);
	},
	destroy: function(forceDestroy){
		return this.panel.panel("destroy",forceDestroy);
	},
	expand: function(animate){
		return this.panel.panel("expand",animate);
	},
	header: function(){
		return this.panel.panel("header");
	},
	maximize: function(){
		return this.panel.panel("maximize");
	},
	minimize: function(){
		return this.panel.panel("minimize");
	},
	move: function(options){
		return this.panel.panel("move",options);
	},
	open: function(forceOpen){
		return this.panel.panel("open",forceOpen);
	},
	options: function(){
		return this.panel.panel("options");
	},
	panel: function(){
		return this.panel.panel("panel");
	},
	refresh: function(href){
		return this.panel.panel("refresh",href);
	},
	resize: function(options){
		return this.panel.panel("resize",options);
	},
	restore: function(){
		return this.panel.panel("restore");
	},
	setTitle: function(title){
		return this.panel.panel("setTitle",title);
	}
}

ui.datetimebox  = function(datetimebox,_options){
	this.datetimebox  = datetimebox ;
	this.options ={};
	this.options.label = this.datetimebox.datetimebox("options").label;
	this.options.require = this.datetimebox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.datetimebox.datetimebox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
}

ui.datetimebox.prototype = {
	setValue:function(r){
		return this.datetimebox.datetimebox("setValue",r);
	},
	spinner: function(){
		return this.datetimebox.datetimebox("spinner");
	},
	calendar: function(){
		return this.datetimebox.datebox("calendar");
	},
	readonly: function(value){
		return this.datetimebox.datetimebox("readonly",value);
	},
	getValue: function(f){
		if (f) {
			return this.datetimebox.datetimebox("getValue");
		}
		else {
			var text = this.datetimebox.datetimebox("getText");
			if (text=="") {
				return "";
			} else {
				var date = this.datetimebox.datetimebox("calendar").calendar("options").current;
				date = new Date(date);
				return date.format("yyyyMMddhhmmss");
			}
		}
	},
	enableValidation: function(){
		return this.datetimebox.datetimebox("enableValidation");
	},
	disableValidation: function(){
		return this.datetimebox.datetimebox("disableValidation");
	},
	clear: function(){
		return this.datetimebox.datetimebox("clear");
	}
}

ui.numberspinner  = function(spinner,_options){
	this.spinner  = spinner ;
	this.options ={};
	this.options.label = this.spinner.spinner("options").label;
	this.options.require = this.spinner.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.spinner.spinner({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
	if (_options) {
		this._options = _options;
	}
}

ui.numberspinner.prototype = {
	setValue: function(value){
		return this.spinner.spinner("setValue",value);
	},
	getValue: function(){
		return this.spinner.spinner("getValue");
	},
	enable: function(){
		return this.spinner.spinner("enable");
	},
	disable: function(){
		return this.spinner.spinner("disable");
	},
	readonly: function(value){
		return this.spinner.spinner("readonly",value);
	},
	enableValidation: function(){
		return this.spinner.spinner("enableValidation");
	},
	disableValidation: function(){
		return this.spinner.spinner("disableValidation");
	},
	clear: function(){
		return this.spinner.spinner("clear");
	}
}

ui.checkboxlist  = function(checkboxlist,_options){
	this.checkboxlist  = checkboxlist;

	var params = {
		"param":JSON.stringify(_options.loadParams)
	};
	var self = this;
	this.options = {};
	this.options.require = this.checkboxlist.attr("require");
	this.options.label = this.checkboxlist.find("label").html();
	if('true' == this.options.require){
		this.checkboxlist.find("label").html("<span class='u-red'>*</span>"+this.options.label);
	}
	this._options = _options;
	var mdata;
	if (_options.url) {
		$.ajax({
			type:'POST',
			data:params,
			async:false,
			url:_options.url,
			success:function(data) {
				if(data.status >=0){
					mdata = data.data;
					init(data.data,_options,checkboxlist,"checkbox");

				} else {
					alert(data.desc);
				}
			}
		});
	} else {
		if (_options.value) {
			mdata = JSON.parse(_options.value); 
			init(JSON.parse(_options.value),_options,checkboxlist,"checkbox");
		}
	}
	this.data = mdata;
}

ui.checkboxlist.prototype = {
	getValue: function(){
		var name = this.checkboxlist.attr("id");
		var chk_value =[];
		$('input[name="'+name+'"]:checked').each(function(){
			chk_value.push($(this).val());
		});
		return chk_value.join(",");
	},
	setValue: function(values){
		var a = this.getValue();
		var val = values.split(",");
		var name = this.checkboxlist.attr("id");
		var boxes = document.getElementsByName(name);
		for(i=0;i<boxes.length;i++){
			boxes[i].checked = false;
			for(j=0;j<val.length;j++){
				if(boxes[i].value == val[j]){
					boxes[i].checked = true;
					break;
				}
			}
		}
		if (values!==a) {
			var fun = this.checkboxlist.data("onChange");
			if (fun) {
				fun(a,values);
			}
		}
	},
	getIndexbyValue: function(ids){
		var a = ids.split(","),b=[];
		if (this._options.valueField) {
			var v = this._options.valueField;
			$.each(this.data,function(i,c){
				if ($.inArray(c[v],a)>=0) {
					b.push(i);
				}
			});
		}
		return b;
	},
	setdisabled: function(indexs){
		var id = $(this)[0].checkboxlist[0].id;
		var len = this.data.length;
		for (var i = 0; i < len; i++) {
			var cellid = id + i;
			document.getElementById(cellid).disabled = false;
		}
		$.each(indexs,function(i,c){
			var cellid = id + c;
			document.getElementById(cellid).disabled = true;
		});
	},
	getData: function() {
		return this.data;
	},
	clear: function(){
		return this.setValue("");
	}
}

ui.radiobuttonlist  = function(radiobuttonlist,_options){
	this.radiobuttonlist  = radiobuttonlist;
	var params = {
		"param":JSON.stringify(_options.loadParams)
	};

	this.options = {};
	this.options.require = this.radiobuttonlist.attr("require");
	this.options.label = this.radiobuttonlist.find("label").html();
	if('true' == this.options.require){
		this.radiobuttonlist.find("label").html("<span class='u-red'>*</span>"+this.options.label);
	}
	this._options = _options;
	var mdata;
	if (_options.url) {
		$.ajax({
			type:'POST',
			data:params,
			async:false,
			url:_options.url,
			success:function(data) {
				if(data.status >=0){
					mdata = data.data;
					init(data.data,_options,radiobuttonlist,"radio");
				} else {
					alert(data.desc);
				}
			}
		});
	} else {
		if (_options.value) {
			mdata = JSON.parse(_options.value);
			init(JSON.parse(_options.value),_options,radiobuttonlist,"radio");
		}
	}
	this.data = mdata;
}

ui.radiobuttonlist.prototype = {
	getValue: function(){
		var name = this.radiobuttonlist.attr("id");
		var chk_value;
		chk_value = $('input[name="'+name+'"]:checked').val();
		if ("undefined"== typeof(chk_value)) {
			chk_value = "";
		}
		return chk_value;
	},
	setValue: function(value){
		var a = this.getValue();
		var name = this.radiobuttonlist.attr("id");
		var boxes = document.getElementsByName(name);
		for(i=0;i<boxes.length;i++){
			boxes[i].checked = false;
			if(boxes[i].value == value){
				boxes[i].checked = true;
				break;
			}
		}
		if (value!==a) {
			var fun = this.radiobuttonlist.data("onChange");
			if (fun) {
				fun(a,value);
			}
		}
	},
	getIndexbyValue: function(ids){
		var a = ids.split(","),b=[];
		if (this._options.valueField) {
			var v = this._options.valueField;
			$.each(this.data,function(i,c){
				if ($.inArray(c[v],a)>=0) {
					b.push(i);
				}
			});
		}
		return b;
	},
	setdisabled: function(indexs){
		var id = $(this)[0].radiobuttonlist[0].id;
		var len = this.data.length;
		for (var i = 0; i < len; i++) {
			var cellid = id + i;
			document.getElementById(cellid).disabled = false;
		}
		$.each(indexs,function(i,c){
			var cellid = id + c;
			document.getElementById(cellid).disabled = true;
		});
	},
	getData: function() {
		return this.data;
	},
	clear: function(){
		return this.setValue("");
	}
}

ui.linkbutton = function(button){
	this.button = button;
}

ui.linkbutton.prototype = {
	resize: function(param){
		return this.button.linkbutton("resize",param);
	},
	disable: function(){
		return this.button.linkbutton("disable");
	},
	enable: function(){
		return this.button.linkbutton("enable");
	},
	select: function(){
		return this.button.linkbutton("select");
	},
	unselect: function(){
		return this.button.linkbutton("unselect");
	}
}
ui.searchbox = function(searchbox) {
	this.searchbox = searchbox;
	this.options ={};
	this.options.label = this.searchbox.searchbox("options").label;
	this.options.require = this.searchbox.attr("require");
	if('true' == this.options.require&&null!==this.options.label){
		this.searchbox.searchbox({
			label:"<span class='u-red'>*</span>"+this.options.label
		});
	}
}

ui.searchbox.prototype = {
	readonly: function(value){
		return this.searchbox.searchbox("readonly",value);
	},
	getValue: function(){
		return this.searchbox.searchbox("getValue");
	},
	clear: function() {

	}
}

ui.html = function(html){
	this.options = {};
	this.html = html;
	
}

ui.html.prototype = {
	setData: function(data){
		
		if(this.options.initHtml == null){
			this.options.initHtml =  this.html.html();
		}
		this.html[0].style.display = ''; 
		var str = this.options.initHtml;
		for(var p in data){
			str = str.replace("$!{"+p+"}",data[p]);
		}
		this.html.html(str);
	},

	clear: function() {

	}
}

ui.dateformatter = function(date) {
	if (date.length==14) {
		var year = parseInt(date.substring(0,4));
		var month = parseInt(date.substring(4,6));
		var day = parseInt(date.substring(6,8));
		return new Date(year,month-1,day).format("yyyy-MM-dd");
	} 
	else {
		return date;
	}
}

ui.datetimeformatter = function(datetime) {
	if (datetime.length==14) {
		var year = parseInt(datetime.substring(0,4));
		var month = parseInt(datetime.substring(4,6));
		var day = parseInt(datetime.substring(6,8));
		var hour = parseInt(datetime.substring(8,10));
		var minutes = parseInt(datetime.substring(10,12));
		var second = parseInt(datetime.substring(12,14));
		return new Date(year,month-1,day,hour,minutes,second).format("yyyy-MM-dd hh:mm:ss");
	} 
	else {
		return datetime;
	}
}

ui.childframe = function(iframeid) {
	return $(iframeid)[0].contentWindow;
}

function init (data,_options,checkboxlist,type) {
	var $c = this.checkboxlist;
	createtable(_options,data,checkboxlist,type);
}

function createtable(_options,data,checkboxlist,type) {
	var $content = $(checkboxlist).find("table").empty();
	var g = _getRepeatTable(_options.repeatItems,data);
	var m = "",i=0;
	for (var e = 0, a = g.length; e < a; e++) {
		var h = g[e];
		var $tr = $("<tr></tr>").appendTo($content);
		for (var c = 0, b = h.length; c < b; c++) {
			var f = h[c];
			var $td = $("<td style='white-space: nowrap;'>"+_createItemHtml(f,_options,checkboxlist,i,type)+"</td>").appendTo($tr);

			var id = $(checkboxlist).attr("id");
			(function(i,f,type,id){

				function getv(id) {
					var o;
					o = $('input[name="'+id+'"]:checked').val();
					if ("undefined"== typeof(o)) {
						o = "";
					}
					return o;
				}
				var o_value;

				$td.find("input").mousedown(function(e){
					o_value = getv(id);
				});

				$td.find("input").click(function(e){
					var index = i;
					var cell = f;
					var checked = e.target.checked;
					var fun = $(checkboxlist).data("onClickCell");
					var fun1 = $(checkboxlist).data("onChange");
					var n_value;
					n_value = getv(id);
					if ("undefined"!==typeof(fun)) {
						fun(index,cell,checked);
					}
					if (type=="radio"&&"undefined"!==typeof(fun1)&&o_value!==n_value) {
						fun1(o_value,n_value);
					}
				});
			})(i,f,type,id);
			i++;
		}
	}
	return m;
}

function _getRepeatTable(repeatItems,data) {
	var f = [];
	if (repeatItems > 0) {
		var a = repeatItems > data.length ? data.length : repeatItems;//数据没有单行多则取数据长度，反之则取单行长
	    var i  = 0 ;
		for (var d = 0, b = data.length; d < b; d++) {
			var e = data[d];
			var c = d % repeatItems;
			if(d == 0){
				f.push([]);
				f[i].push(e);
			}else if(c == 0){
				i++;
				f.push([]);
				f[i].push(e);
	
			}else{
				f[i].push(e);
			}
			
		}
		
	}
	return f;
}

function _createItemHtml(f,_options,checkboxlist,i,type) {
	var n = checkboxlist.attr("id");
	var id = n+i;
	return "<input id='"+id+"' type='"+type+"' name='"+n+"' value='"+f[_options.valueField]+"'></input><lable for='"+id+"'>"+f[_options.textField]+"</lable>"
}

function getvaluebypercent(width) {
	return (width.substring(0,width.length-1))/100;
}

