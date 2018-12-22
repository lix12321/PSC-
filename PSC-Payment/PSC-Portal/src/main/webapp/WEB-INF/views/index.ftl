<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>佑君支付平台演示程序</title>
<link rel="stylesheet" href="/resources/jquery-ui.min.css">
<script src="/resources/jquery-1.11.2.min.js"></script>
<script src="/resources/jquery-ui.min.js"></script>
<script src="/resources/demo.js"></script>
<script>
	$(function() {
		setApiDemoTabs("#tabs-df");
		setApiDemoTabs("#tabs-df2");
		setApiDemoTabs("#tabs-df4");
		setApiDemoTabs("#tabs-df5");
		setApiDemoTabs("#tabs-df6");
	});
</script>
<link rel="stylesheet" href="/resources/css/demo.css">
</head>

<body style="background-color: #e5eecc;">
	<div id="wrapper">
		<div id="header">
			<h2>支付中心产品测试示例</h2>

		</div>

		<div id="tabs-api">
			<ul>
				<li><a href="#tabs-api-1">说明</a></li>
				<li><a href="#tabs-api-2">患者扫二维码支付</a></li>
				<li><a href="#tabs-api-3">收费员扫患者手机收费</a></li>
				<li><a href="#tabs-api-4">账户支付</a></li>
				<li><a href="#tabs-api-5">第三方支付退款</a></li>
				<li><a href="#tabs-api-6">交易查询</a></li>
			</ul>

			<div id="tabs-api-1">
				<p>
					<img alt="" src="/resources/images/pay.png">
				</p>
				<p>
					接口规范(请求报文，异步应答报文字段)参考：<a href="">《支付中心接口规范及接入指南》</a>
				</p>
			</div>

			<div id="tabs-api-2">
				<div id="tabs-df">
					<ul>
						<li><a href="#tabs-df-1">说明</a></li>
						<li><a href="demo/view/unifyPay?payType=001">微信扫码</a></li>
						<li><a href="demo/view/unifyPay?payType=003">支付宝扫码</a></li>
						<li><a href="demo/view/unifyPay?payType=009">建行聚合支付</a></li>
<!-- 						<li><a href="demo/view/unifyPay?payType=010">银联扫码</a></li> -->
					</ul>
					<div id="tabs-df-1">
						<div style="font-size: 13px">
							<br> <b>
								业务系统必须按照接口规范进行数据签名，签名规则：所有请求参数按请求键自然排序后，和服务密钥进行Md5摘要签名，得到签名字符串A，将A与所有请求参数传递至支付平台请求即可。
							</b> <br>
							<br> <b>
								主动扫码支付流程：业务系统通过API接入支付平台，通过调用一种或几种类型的支付手段，从支付平台获取到付款二维码，进而使得业务系统的用户通过手机等终端主动进行扫码支付。<br>
								<br> 支付成功后，由支付平台进行根据接入平台时的回调参数进行异步通知（业务系统），业务系统处理订单后续逻辑<br>
								<br>
							</b> <br> 【重要提醒】<br> <br>
							接入支付平台不仅要求数据签名，且支付平台要求服务系统时间至多比业务系统（客户端）传递的时间快15秒，否则为非法请求；<br><br>
							本演示将自动进行数据签名；
							<br><br>
							如果业务系统在收到支付平台的成功通知后，没有按接口规范返回指定消息，则支付平台会每隔一定时间持续发送通知，直到通知次数达阀值。
						</div>
						<br> <br>
					</div>
				</div>
			</div>

			<div id="tabs-api-3">
				<div id="tabs-df2">
					<ul>
						<li><a href="#tabs-df-2">说明</a></li>
						<li><a href="demo/view/unifyPay?payType=006">微信扫码</a></li>
						<li><a href="demo/view/unifyPay?payType=004">支付宝扫码</a></li>
						<li><a href="demo/view/misPos">MisPOS扫码支付</a></li>
<!-- 						<li><a href="#tabs-df-4">银联扫码</a></li> -->
					</ul>
					<div id="tabs-df-2">
						<div style="font-size: 13px">
							<br> <b>
								被动扫码是由用户出示二维码，由业务系统通过手机、MisPOS等终端进行扫码支付的支付方式，由于此方式不需要用户使用终端，用户只要提供付款二维码，在此二维码有效时间范围内，业务系统都可以使用终端进行扫码，因此也称“离线支付”
							</b> <br>
							<br> <b>
								被动扫码支付流程：用户展示二维码，业务系统通过MisPos、扫码枪、手机等终端扫描用户的二维码进行支付。<br>
								<br> 支付成功后，由面对面付款，支付平台不进行成功通知<br>
								<br>
							</b> <br> 【重要提醒】<br> <br>
							被动扫码将要求用户出示二维码，系统对接支付平台后，请求平台支付后即支付成功，当即扣款<br><br>
						</div>
						<br> <br>
					</div>
				</div>
			</div>
			
			<div id="tabs-api-4">
				<div id="tabs-df4">
					<ul>
						<li><a href="#tabs-df-2">说明</a></li>
						<li><a href="demo/view/unifyPay?payType=007">账户支付</a></li>
						<li><a href="demo/view/accPay">账户充值</a></li>
					</ul>
					<div id="tabs-df-2">
						<div style="font-size: 13px">
							<br> <b>
								账户支付是由用户预先充值，消费时由业务系统通过诊疗卡、身份证等可证明患者唯一身份信息获取账户余额进行支付的方式。此方式不需要用户使用终端，用户只要提供可证明身份的证件，在余额可供消费范围内，业务系统都可以进行支付操作。
							</b> <br>
							<br> <b>
								账户支付流程：用户提供诊疗卡、身份证或其他信息，业务系统通过提供的信息获取账户可用余额进行支付。<br>
								<br> 支付成功后，直接提示扣款余额，支付平台不进行成功通知<br>
								<br>
							</b> <br> 【重要提醒】<br> <br>
							账户支付将要求用户出示可证明身份的证件，系统对接支付平台后，请求平台支付后即支付成功，当即扣款<br><br>
						</div>
						<br> <br>
					</div>
				</div>
			</div>
            <div id="tabs-api-5">
                <div id="tabs-df5">
                    <ul>
                        <li><a href="#tabs-df-2">说明</a></li>
                        <li><a href="demo/view/refundPay?payType=003">支付宝退费</a></li>
                        <li><a href="demo/view/refundPay?payType=001">微信退费</a></li>
                        <li><a href="demo/view/refundPay?payType=009">聚合支付退费</a></li>
                    </ul>
                    <div id="tabs-df-2">
                        <div style="font-size: 13px">
                            <br> <b>
                            统一退费是由业务系统根据需要发起退款操作，可通过退款接口将支付款退还给支付方，将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退回。
                        </b> <br>
                            <br> <b>
                            统一退费流程：业务系统通过提供退款金额、退款单号等信息调用对应的退费接口执行退款操作。<br>
                            <br> 退费成功后，由于支付宝等有支付结果通知，支付平台不进行成功通知<br>
                            <br>
                        </b> <br> 【重要提醒】<br> <br>
                            1.系统对接支付平台后，请求平台退费后即退款成功，当即到账。<br>
							<br>2.超过约定时间（签约时设置的可退款时间）的订单无法进行退款。</br><br>
							3.退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。<br><br>
                        </div>
                        <br> <br>
                    </div>
                </div>
            </div>
            <div id="tabs-api-6">
                <div id="tabs-df6">
                    <ul>
                        <li><a href="#tabs-df-2">说明</a></li>
                        <li><a href="demo/view/querypay?payType=003">交易查询</a></li>
                    </ul>
					<div id="tabs-df-2">
						<div style="font-size: 13px">
							<br> <b> 交易查询可查询所有接入支付平台的交易记录流水。 </b> <br> <br> <b>
								交易查询仅用于系统交易流水对账，支付平台不保证其结果的实时性；如果用于即时查询交易状态，请以支付通知为主。<br> <br>
							</b> <br> 【重要提醒】<br> <br>
							1.如果交易为分笔支付，则需要分别使用这些业务订单号发起不同的查询请求。</br><br>
							2.如果想查询具体的交易图表信息，请至支付平台后台管理查看。<br>
							<br>
						</div>
						<br> <br>
					</div>
				</div>
            </div>
		</div>
	</div>

</body>
</html>