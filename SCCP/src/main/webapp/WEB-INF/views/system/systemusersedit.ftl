<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/systemusers"/>

<style>
.dd-group .fluidbox .lab-item {
	margin-top: 3px;
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
			编辑用户<span class="s-poar"> <a
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
                    <input type="hidden" name="id" value="${obj.id}">
					<div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>用户名: </label>
                            <input type="text" id="loginName" name="loginName"
                                   class="txt w200 easyui-validatebox"
                                   missingMessage="用户名必填，2-20个字符"
                                   data-options="required:true,validType:'length[2,20]'" value="${obj.loginName}"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>密码: </label>
                            <input type="password" id="pwd" name="pwd"
                                   class="txt w200 easyui-validatebox"
                                   missingMessage="密码必填，2-20个字符"
                                   data-options="required:true,validType:'length[6,20]'" value="${obj.pwd}"/>
                        </p>
					<#--</div>
					<div class="fluidbox">-->
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>备注: </label>
                            <input type="text" id="remark" name="remark"
                                   class="txt w200 easyui-validatebox"
                                   missingMessage="备注必填，2-20个字符"
                                   data-options="required:false,validType:'length[2,20]'" value="${obj.remark}"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>机构: </label>
                            <input type="text" id="orgId" name="orgId"
                                   class="txt w200 easyui-validatebox"
                                   missingMessage="机构必填，2-20个字符"
                                   data-options="required:true" value="${obj.orgId}"/>
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
