<#assign currentBaseUrl="/paystrategy"/>
<#import "/commons/_macro_controller.ftl" as cont/>
<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#editPayStrategyForm").form('validate');
			if (isValid) {
				$.messager.progress({
					text : "提交中..."
				});
				$("#editPayStrategyForm").form('submit', {
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
		$("#paystrategyedit,window.parent.document").window("close");
	}

</script>
<div class="formbox-a">
	<form id="editPayStrategyForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">
                <input type="hidden" name="id" value="${obj.id}">
                <input type="hidden" name="orgId" value="${obj.orgId}">
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>机构名称: </label>
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
							<@cont.radio id="payStrategy" name="payStrategy" value="${obj.payStrategy}" codeDiv="PAY_STRATEGY" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<@cont.select class="txt w200 easyui-validatebox" id="status" name="status" value="${obj.status}" codeDiv="USE_YN" />
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