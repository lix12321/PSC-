<#assign currentBaseUrl="/sysorganization"/>

<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#allotroleForm").form('validate');
			if (isValid) {
				$.messager.progress({
					text : "提交中..."
				});
				$("#allotroleForm").form('submit', {
					url : "${currentBaseUrl}/doAdd",
					success : function(e) {
						$.messager.progress('close');
						closeW();
					}
				});
			}
		});
	});
	
	function closeW(){
		$("#allotRoleWin,window.parent.document").window("close");
	}
</script>
<div class="formbox-a">
	<form id="allotroleForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">
                <input type="hidden" name="id" value="${id}">
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>机构名称: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="orgName"
								name="orgName" value="${orgName}" readonly
								class="txt w400" />

							</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>&nbsp;角&nbsp;色&nbsp;:
							</label>
                            <span id="resourcesNameSpan">
								<select class="easyui-combobox" name="roleId" style="width:200px;">
									<#list roles as role>
										<option value="${role.id}">${role.rolesName}</option>
									</#list>
								</select>
							</span>
						</p>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<a id="addBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete" onclick="closeW()">关闭</a> <input
					type="hidden" id="rid" name="rid" value="0">
			</p>
		</div>
	</form>
</div>