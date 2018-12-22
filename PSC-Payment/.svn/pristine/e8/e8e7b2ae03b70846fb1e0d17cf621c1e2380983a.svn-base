<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/paysettingalipay" />

<#-- 导入宏模板 -->
<#import "/commons/_macro_controller.ftl" as cont/>

<style>
.dd-group .fluidbox .lab-item {
	margin-top: 3px;
}

.panel-fit body.panel-noscroll {
	overflow-y: scroll;
}

</style>

<script language="javascript">
	$(function() {
		$("#add").click(function() {
			if ($('#addForm').form('validate')) {
				$('#addForm').ajaxSubmit(function() {
					location.href = '${currentBaseUrl}';
				});
			}
		});

		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

	});
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			新增支付宝支付设置<span class="s-poar"> <a class="a-back"
				href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<form method="post" class="validForm" id="addForm" name="addForm"
				enctype="multipart/form-data" action="${currentBaseUrl}/doAdd">
				<dl class="dl-group">
					<dt class="dt-group">
						<span class="s-icon"></span>基本信息
					</dt>
					<dd class="dd-group">
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>订单名称:
								</label> <input type="text" id="orderName" name="orderName"
									class="txt w550 easyui-validatebox"
									missingMessage="订单名称必填，2-20个字符"
									data-options="required:true,validType:'length[2,20]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080"> 发送给支付宝的订单名称</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>合作身份者ID:
								</label> <input type="text" id="partner" name="partner"
									class="txt w550 easyui-validatebox"
									missingMessage="合作身份者ID必填，2-20个字符"
									data-options="required:true,validType:'length[2,20]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080"> 合作身份者ID，以2088开头由16位纯数字组成的字符串 </font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>收款账号:
								</label> <input type="text" id="sellerEmail" name="sellerEmail"
									class="txt w550 easyui-validatebox"
									missingMessage="收款账号必填，2-20个字符"
									data-options="required:true,validType:'length[2,20]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080"> 收款支付宝账号，一般情况下收款账号就是签约账号 </font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>商户的私钥:
								</label> <input type="text" id="key" name="key"
									class="txt w550 easyui-validatebox"
									missingMessage="商户的私钥必填，2-20个字符"
									data-options="required:true,validType:'length[16,20000]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080"> 商户的私钥，从蚂蚁开放平台商户管理里维护生成 </font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>字符编码格式:
								</label> <input type="text" id="inputCharset" name="inputCharset"
									class="txt w550 easyui-validatebox"
									missingMessage="字符编码格式必填，2-20个字符"
									data-options="required:true,validType:'length[2,20]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080"> 字符编码格式 目前支持 gbk 或 utf-8</font>
								</label>
							</p>

						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>签名方式:
								</label> <input type="text" id="signType" name="signType"
									class="txt w550 easyui-validatebox" value="MD5"
									missingMessage="签名方式必填，2-20个字符"
									data-options="required:true,validType:'length[2,20]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">签名方式 ，目前固定MD5</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>交易超时时间:
								</label> <input type="text" id="timeout" name="timeout"
									class="txt w550 easyui-validatebox" value="5m"
									missingMessage="交易超时时间必填，2-20个字符"
									data-options="required:true,validType:'length[2,20]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">支付超时，线下扫码交易定义为5分钟，单位：m</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>支付宝网关名:
								</label> <input type="text" id="openApiDomain" name="openApiDomain"
									class="txt w550 easyui-validatebox"
									missingMessage="支付宝网关名必填，2-20个字符"
									data-options="required:true,validType:'length[2,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">支付宝网关名，固定https://openapi.alipay.com/gateway.do</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>监控地址域名:
								</label> <input type="text" id="mcloudApiDomain" name="mcloudApiDomain"
									class="txt w550 easyui-validatebox"
									missingMessage="监控地址域名必填，2-20个字符"
									data-options="required:true,validType:'length[2,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">监控地址域名，固定http://mcloudmonitor.com/gateway.do</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>pid: </label>
								<input type="text" id="pid" name="pid"
									class="txt w550 easyui-validatebox"
									missingMessage="pid必填，2-20个字符"
									data-options="required:true,validType:'length[2,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">pid，从蚂蚁开放平台商户应用管理-应用查询获取</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">

							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>appid:
								</label> <input type="text" id="appid" name="appid"
									class="txt w550 easyui-validatebox"
									data-options="required:true,validType:'length[2,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">appid，从蚂蚁开放平台商户应用管理-应用查询获取</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>RSA私钥:
								</label> <input type="text" id="privateKey" name="privateKey"
									class="txt w550 easyui-validatebox"
									missingMessage="RSA私钥必填，2-20个字符"
									data-options="required:true,validType:'length[16,20000]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">RSA私钥，使用支付宝签名工具生成</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>公钥:
								</label> <input type="text" id="publicKey" name="publicKey"
									class="txt w550 easyui-validatebox"
									missingMessage="公钥必填，2-20个字符"
									data-options="required:true,validType:'length[16,20000]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">SHA1withRsa对应支付宝公钥，使用支付宝签名工具生成</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>支付宝公钥:
								</label> <input type="text" id="alipayPublicKey" name="alipayPublicKey"
									class="txt w550 easyui-validatebox"
									missingMessage="支付宝公钥必填，2-20个字符"
									data-options="required:true,validType:'length[16,20000]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">SHA256withRsa对应支付宝公钥，使用支付宝签名工具生成</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>最大查询次数:
								</label> <input type="text" id="maxQueryRetry" name="maxQueryRetry"
									class="txt w550 easyui-validatebox"
									missingMessage="最大查询次数必填，2-20个字符"
									data-options="required:true,validType:'length[2,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">当面付最大查询次数和查询间隔（毫秒）</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>当面付最大查询延迟:
								</label> <input type="text" id="queryDuration" name="queryDuration"
									class="txt w550 easyui-validatebox"
									missingMessage="当面付最大查询延迟必填，2-20个字符"
									data-options="required:true,validType:'length[1,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">当面付最大查询延迟（毫秒）</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>最大撤销次数:
								</label> <input type="text" id="maxCancelRetry" name="maxCancelRetry"
									class="txt w550 easyui-validatebox"
									missingMessage="最大撤销次数必填，2-20个字符"
									data-options="required:true,validType:'length[1,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">当面付最大撤销次数和撤销间隔（毫秒）</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>最大撤销次数和撤销延迟:
								</label> <input type="text" id="cancelDuration" name="cancelDuration"
									class="txt w550 easyui-validatebox"
									missingMessage="最大撤销次数和撤销延迟必填，2-20个字符"
									data-options="required:true,validType:'length[1,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">当面付最大撤销次数和撤销间隔（毫秒）</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>第一次调度延迟和调度间隔:
								</label> <input type="text" id="heartbeatDelay" name="heartbeatDelay"
									class="txt w550 easyui-validatebox"
									missingMessage="第一次调度延迟和调度间隔（秒）必填，2-20个字符"
									data-options="required:true,validType:'length[1,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">交易保障线程第一次调度延迟和调度间隔（秒） </font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red">*</font>第一次调度延迟和调度延迟:
								</label> <input type="text" id="heartbeatDuration"
									name="heartbeatDuration" class="txt w550 easyui-validatebox"
									missingMessage="第一次调度延迟和调度延迟必填，2-20个字符"
									data-options="required:true,validType:'length[1,40]'" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">交易保障线程第一次调度延迟和调度延迟（秒） </font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red"></font>状态: </label>
								<@cont.select class="txt w550 easyui-validatebox" name="status" id="status" codeDiv="PAY_STATUS" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">机构下此项支付设置只能有一个是可用状态 </font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p6 p-item">
								<label class="lab-item"><font class="red"></font>是否启用通知:</label>
								<@cont.select class="txt w550 easyui-validatebox" name="enableNotify" id="enableNotify" codeDiv="ENABLE_NOTIFY" />
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label> <label> <font
									style="color: #808080">启用通知后，系统在支付完成会向业务系统异步发送支付成功通知 </font>
								</label>
							</p>
						</div>
					</dd>
				</dl>
            
				<p class="p-item p-btn">
					<input type="button" id="add" class="easyui-linkbutton" value="提交" />
					<input type="button" id="back" class="easyui-linkbutton" value="返回" />
				</p>
			</form>
		</div>
	</div>
</div>

<#include "/commons/_detailfooter.ftl" />
