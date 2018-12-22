<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>支付中心后台管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="/resources/jslib/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/resources/amaze/js/amazeui.min.js"></script>
<script type="text/javascript" src="/resources/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/resources/jslib/js/common-function.js"></script>
<script type="text/javascript" src="/resources/jslib/js/jquery.form.js"></script>
<script type="text/javascript" src="/resources/jslib/echarts/echarts.min.js"></script>
<script type="text/javascript" src="/resources/jslib/echarts/macarons.js"></script>

<link rel="stylesheet" href="/resources/amaze/css/admin.css">
<link rel="stylesheet" href="/resources/jslib/easyui/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="/resources/jslib/easyui/themes/icon.css" type="text/css"></link>
<link href="/resources/css/sys_frame1.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/amaze/css/amazeui.css" />
<link rel="stylesheet" href="/resources/amaze/font/iconfont.css">
<link rel="stylesheet" href="/resources/amaze/css/nav.css">
<link rel="stylesheet" href="/resources/css/easyuireset.css">
<link rel="stylesheet" href="/resources/css/ext.css">
   
<script>
	var chromestyle = "<style>"+
		".panel-fit body.panel-noscroll {"+
		"	position: initial;"+
		"}"+
	
		".panel-fit {"+
		"	height: 100%;"+
		"	margin: 0;"+
		"	padding: 0;"+
		"	border: 0;"+
		"	overflow: inherit;"+
		"}"+
		"</style>";
	var mozstyle = "<style>"+
		".panel-fit {"+
		"	height: 100%;"+
		"	margin: 0;"+
		"	padding: 0;"+
		"	border: 0;"+
		"	overflow: inherit;"+
		"}"+
		"</style>";
	var brow = $.browser;
	if (brow.chrome) {
		document.write(chromestyle);
	} else{
		document.write(mozstyle);
	}
	
	$(document).keydown(function(e){
		var curKey = e.which;
		if (curKey == 13) {
			return false;
		}
	});
	
	//去掉默认的滚动条空白
	$.extend($.fn.datagrid.defaults,{scrollbarSize:0});
	$.extend($.fn.treegrid.defaults,{scrollbarSize:0});
</script>

</head>
<body class="easyui-layout">
	
<script>
//添加加载中遮罩
(function() {
	var hoverdiv =
	'<div id="loading">'+
	'  <div id="loading-center">'+
	'    <div id="loading-center-absolute">'+
	'      <div class="object" id="object_one"></div>'+
	'      <div class="object" id="object_two"></div>'+
	'      <div class="object" id="object_three"></div>'+
	'      <div class="object" id="object_four"></div>'+
	'      <div class="object" id="object_five"></div>'+
	'      <div class="object" id="object_six"></div>'+
	'      <div class="object" id="object_seven"></div>'+
	'      <div class="object" id="object_eight"></div>'+
	'    </div>'+
	'  </div>'+
	'</div>';
	$(document.body).append(hoverdiv);
})();

$(function(){
	// 查询按钮
	$('#btn-search').click(function() {
		$('#dataGrid').datagrid('reload', queryParamsHandler());
	});
});
</script>