<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>微信H5支付</title>
<script src="/resources/js/jquery-1.8.3.min.js"></script>
</head>

<body>
	<#if error??>
		${error!''}
	<#else>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.PSC_PAYMENT_URL)!}';
		
			$(function() {
				//当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady', jsApiCall,
								false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady', jsApiCall);
						document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
					}
				} else {
					jsApiCall();
				}
			});
	
			//调用微信JS api 支付
			function jsApiCall() {
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : "${(payparms.appid)!}",
					"timeStamp" : "${(payparms.timeStamp)!}",
					"nonceStr" : "${(payparms.nonceStr)!}",
					"package" : "${(payparms.payPackage)!}",
					"signType" : "MD5",
					"paySign" : "${(payparms.sign)!}"
				}, function(res) {
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						var uri = domain + "/wxJSpay/payresult?state=1&res=支付成功";
						location.href = encodeURI(uri);
					} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
						var uri = domain + "/wxJSpay/payresult?res=用户取消支付";
						location.href = encodeURI(uri);
					} else {
						if (res.err_desc)
							location.href = encodeURI(domain
									+ "/wxJSpay/payresult?res=" + res.err_desc);
						else
							location.href = encodeURI(domain
									+ "/wxJSpay/payresult?res=支付失败");
					}
				});
			}
		</script>
	</#if>
</body>
</html>

