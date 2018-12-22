<#assign currentBaseUrl="/paystrategy"/>
<#import "/commons/_macro_controller.ftl" as cont/>

<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#addPayStrategyForm").form('validate');
			if (isValid) {
				$.messager.progress({
					text : "提交中..."
				});
				$("#addPayStrategyForm").form('submit', {
					url : "${currentBaseUrl}/doAdd",
					success : function(e) {
						$.messager.progress('close');
						closeW();
                        location.href = '${currentBaseUrl}';
					}
				});
			}
		});
	});
	function closeW(){
		$("#paystrategyadd,window.parent.document").window("close");
	}

</script>
<div class="formbox-a">
	<form id="addPayStrategyForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">

				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>机构名称: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="orgName"
								name="orgName" value="${orgName}" readonly
								data-options="required:false"
								class="txt w400" />
							</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>&nbsp;支付策略:
							</label> 
							<@cont.radio id="payStrategy" name="payStrategy" value="" codeDiv="PAY_STRATEGY" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
                           <@cont.select class="txt w200 easyui-validatebox" id="status" name="status" codeDiv="USE_YN" />
						</p>

					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<a id="addBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete" onclick="closeW()">关闭</a>
			</p>
		</div>
	</form>
</div>