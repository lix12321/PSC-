<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/ccp/config"/>
<#-- 导入宏模板 -->
<#import "/commons/_macro_controller.ftl" as cont/>

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
			通道编辑<span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<form method="post" class="validForm" id="addForm" name="addForm" 
				enctype="multipart/form-data" action="${currentBaseUrl}/doAdd"> 
			<input type="hidden" name="id" value="${(obj.id)!}">
			
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>服务名称: </label>
							<input type="text" id="serverName" name="serverName" value="${(obj.serverName)!}"
								class="txt w200 easyui-validatebox"
								missingMessage="机构名称必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>服务编号: </label>
							<input type="text" id="serverCode" name="serverCode"
								class="txt w200 easyui-validatebox" value="${(obj.serverCode)!}"
								missingMessage="服务编号必填，限定3个字符"
								data-options="required:true,validType:'length[3,3]'" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>服务地址: </label>
                          	<input type="text" id="addr" name="addr"
								class="txt w500 easyui-validatebox" value="${(obj.addr)!}"
								missingMessage="服务地址必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>是否启用: </label>
                            <@cont.select class="txt w200 easyui-validatebox" name="enable" id="enable" value="${(obj.enable)!}" codeDiv="ENABLE" />
                        </p>
					</div>
						
				</dd>
			</dl>
			
			<dl class="dl-group helpinfo">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <ul>
							<li>服务编号限定3个字符长度组成，限定为数字</li>
							<li>服务地址请以http://开头，填写此微服务的全地址，不需要端口号</li>
						</ul>
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
