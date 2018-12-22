<script src="/resources/js/jquery.form.js"></script>
<script src="/resources/js/jquery.qrcode.min.js"></script>

<style>
.api-form input[readonly="readonly"]{
	background: #fcfcfc;
	color: #a1a1a1;
}

.accountinfo{
	margin-bottom: 10px;
	color: #6d8331;
}
</style>

<script>
	function dosubmit(obj) {
        var userid = $('input[name="userId"]').val();

        $.ajax({
            url:'demo/order/queryPay',
            type : 'post',
			data:$('.api-form').serialize(),
            async: false,
            success:function(data) {
                if (data.data != null && data.data != "") {
                    $(".resultinfo").text("返回参数：" + JSON.stringify(data.data));
                    $('form.api-form').hide();
                }else {
                    console.log(data);
                    $(".resultinfo").html("错误信息："+"<span style='color:red'>"+data.msgInfo+"</span>");
                    $('form.api-form').hide();
				}
            }
        });
	}
</script>

<div class="resultinfo" style="width: 700px; word-break:break-all;" >
</div>

<form class="api-form" method="post"
	action="demo/refundPayAction/refundPay">
	<input type="hidden" name="payType" value="${payType!}" />

	<p>
		<label>账户ID：</label> <input type="text" name="userId" onchange="getAcc(this.value)"
			placeholder="用户名（账户中心账户）" title="用户名"
			required="required" />
	</p>
	<p>
		<label>机构号：</label> <input type="text" name="orgId"
			placeholder="机构号" value="1" title="机构号"
			required="required" /> </label>
	</p>
	<p>
		<label>商户订单号：</label> <input type="text" name="orderId"
			placeholder="商户订单号" value="" title="交易时的业务订单号"
			required="required" />
	</p>
	<p>
		<label>&nbsp;</label> <input type="button" onclick="dosubmit(this)"
			class="button" value="提交" /> <input type="button" class="showFaqBtn"
			value="遇到问题？" />
	</p>
</form>

<div class="question">
	<hr />
	<h4>测试可能出现的问题</h4>
	<p class="faq">
		<ul style="font-size: .8em;">
			<li style="margin: 10px auto;"><b>时间不合法，请重新发起支付请求</b><br> <em>请刷新此表单后重新发起支付请求，系统允许的发起请求至服务器响应最大时间相差为15秒</em></li>
			<li style="margin: 10px auto;"><b>机构未授权支付服务，请联系管理员</b><br> <em>请确认使用的用户名正常授权</em></li>
			<li style="margin: 10px auto;"><b>无效参数</b><br> <em>请确认请求的参数正确且类型正确</em></li>
			<li style="margin: 10px auto;"><b>服务器繁忙，请稍后重试</b><br> <em>请确认参数正确且非空</em></li>
		</ul>
	</p>

</div>