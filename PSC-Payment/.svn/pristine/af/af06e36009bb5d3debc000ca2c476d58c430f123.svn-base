<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/paysettingintegration"/>
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
                $('#addForm').ajaxSubmit(function(){
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
			新增建行聚合支付设置<span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
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
                            <label class="lab-item"><font class="red"></font>订单名称: </label>
                            <input type="text" id="orderName" name="orderName"
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="订单名称必填，2-20个字符"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商户代码: </label>
							<input type="text" id="merchTid" name="merchTid"
								class="txt w550 easyui-validatebox"
								missingMessage="商户代码必填"
								data-options="required:true" value="105000060120010"/>
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公钥: </label>
							<input type="text" id="publicKey" name="publicKey" readonly
                                   value="30819d300d06092a864886f70d010101050003818b0030818702818100c6667db209fc63485e4019ab7d14699225f4c2b2b3ff68b46aa3c1ffc74d120e4617ce1844cfe3681b71c1e0e8da9184b32741352688cb876df0942885bbc7dcfe15c4aafc02e50b7b689778e5ca9706cd96e7c504c51168135bcf3d8d2edd5007c891ee22c05762ce32e7cf8cf28b2f6c3b26333803c2e14454db1d588fd29b020111"
								class="txt w550 easyui-validatebox"
								missingMessage="公钥必填"
								data-options="required:true" />
						</p>
					<#--</div>
					<div class="fluidbox">-->
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商户柜台代码: </label>
							<input type="text" id="posId" name="posId" readonly value="017052253"
								class="txt w550 easyui-validatebox"
								missingMessage="商户柜台代码必填"
								data-options="required:true" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>分行代码: </label>
							<input type="text" id="bankId" name="bankId" value="610000000" readonly
								class="txt w550 easyui-validatebox"
								missingMessage="分行代码必填"
								data-options="required:true" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>币种: </label>
                            <input type="text" id="curcode" name="curcode" value="01" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="币种必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>交易码: </label>
                            <input type="text" id="txcode" name="txcode" value="530550" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="交易码必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>返回类型: </label>
                            <input type="text" id="returnType" name="returnType" value="3" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="返回类型必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>密钥: </label>
                            <input type="text" id="pub32tr2" name="pub32tr2" value="3803c2e14454db1d588fd29b020111" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="密钥必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>网银网关地址: </label>
                            <input type="text" id="bankUrl" name="bankUrl" value="https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="网银网关地址必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商户号: </label>
                            <input type="text" id="custid" name="custid" value="105000060120010" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="商户号必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>操作员号: </label>
                            <input type="text" id="userId" name="userId"
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="操作员号必填，2-40个字符"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>操作员密码: </label>
                            <input type="text" id="pwd" name="pwd"
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="操作员密码必填，2-40个字符"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                style="color: #808080"></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
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
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>是否启用通知: </label>
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
				<input type="button" id="add" class="easyui-linkbutton" value="提交" /> <input
					type="button" id="back" class="easyui-linkbutton" value="返回" />
			</p>
			</form>
		</div>
	</div>
</div>

<#include "/commons/_detailfooter.ftl" />
