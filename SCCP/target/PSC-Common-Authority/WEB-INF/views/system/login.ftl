<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>佑君中控管理系统-登录</title>
<script type="text/javascript"
	src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/amaze/js/amazeui.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/js/common-function.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/js/jquery.form.js"></script>

<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/amaze/css/admin.css">
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/easyui/themes/bootstrap/easyui.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/jslib/easyui/themes/"
	type="text/css"></link>
<link
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/css/sys_frame1.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/amaze/css/amazeui.css" />
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/amaze/font/iconfont.css">
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/amaze/css/nav.css">
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/css/easyuireset.css">
<link rel="stylesheet"
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/css/ext.css">
<link
	href="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/css/login.css"
	type="text/css" rel="stylesheet">
	
<script>
	//刷新验证码
	function refreshCode() {
		$("#code_img").attr("src",
				"/system/verifyCode?d=" + new Date().getTime());
	}
</script>

</head>
<body>

	<div class="login">
		<div class="message">佑君中控管理系统</div>
		<div id="darkbannerwrap"></div>

		<form method="post" action="/admin/doLogin">
			<input placeholder="用户名" name="name" required autocomplete="off"
				oninvalid="setCustomValidity('请输入用户名')"
				oninput="setCustomValidity('')" type="text">
			<hr class="hr15">
			<input placeholder="密码" name="password" autocomplete="off"
				oninvalid="setCustomValidity('请输入密码')"
				oninput="setCustomValidity('')" required type="password">
			<hr class="hr15">
			<input type="text" name="verifyCode"
				oninvalid="setCustomValidity('请输入验证码')" maxLength="5"
				autocomplete="off" oninput="setCustomValidity('')" required
				placeholder="验证码" style="width: 60%;">
			<div style="float: right; margin-right: 15px; width: 30%">
				<img onclick="refreshCode();" id="code_img"
					src='/system/verifyCode' style="cursor: pointer">
			</div>
			<div class="erro">${(message)!}</div>
			<hr class="hr15">
			<input value="登录" style="width: 100%;" type="submit">
			<hr class="hr20">
		</form>


	</div>

	<div class="copyright">©2018 xxx.保留一切权利</div>

</body>
</html>