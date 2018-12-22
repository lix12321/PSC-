<#assign currentBaseUrl="/systemroles"/>
<#import "/commons/_macro_controller.ftl" as cont/>
<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#addRolesForm").form('validate');
			if (isValid) {
				$.messager.progress({
					text : "提交中..."
				});
				$("#editRolesForm").form('submit', {
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
		$("#editRole,window.parent.document").window("close");
	}

</script>
<div class="formbox-a">
	<form id="editRolesForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">
				<dd class="dd-group">
                    <input type="hidden" name="id" value="${obj.id}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>角色名称: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="rolesName"
								name="rolesName"
								data-options="required:true,validType:'length[1,40]'"
								class="txt w400" value="${obj.rolesName}"/> <span class="title_span">长度为1-40个字符</span>

							</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>&nbsp;角色描述:
							</label> 
							<input id="content" name="content" class="txt w200" value="${obj.content}"/>
                            <span class="title_span">长度为1-40个字符</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<@cont.select class="txt w200 easyui-validatebox" id="status" name="status" value="${obj.status}" codeDiv="RESOURCES_STATUS" />
						</p>

					</div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>&nbsp;角色code:
                            </label>
                            <input id="roleCode" name="roleCode" class="txt w200" value="${obj.roleCode}"/>
                            <span class="title_span">长度为1-40个字符</span>
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