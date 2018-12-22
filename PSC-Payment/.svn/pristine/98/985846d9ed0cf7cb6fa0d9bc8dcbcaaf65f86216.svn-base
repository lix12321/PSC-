<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/syscodemaster"/>
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
			编辑数据字典<span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<form method="post" class="validForm" id="addForm" name="addForm" 
				enctype="multipart/form-data" action="${currentBaseUrl}/doedit">
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>字典组: </label>
							<input type="text" id="codeDiv" name="codeDiv"
								class="txt w200 easyui-validatebox" readonly
								missingMessage="code组必填"
								data-options="required:true" style="text-transform:uppercase;" value="${obj.codeDiv}"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>字典Id: </label>
							<input type="text" id="codeCd" name="codeCd" readonly
								class="txt w200 easyui-validatebox"
								missingMessage="codeId必填"
								data-options="required:true" value="${obj.codeCd}"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>字典中文名称: </label>
							<input type="text" id="codeText" name="codeText"
								class="txt w200 easyui-validatebox"
								missingMessage="code中文名称必填"
								data-options="required:true" value="${obj.codeText}"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>组内顺序: </label>
							<input type="text" id="sortOrder" name="sortOrder"
								class="txt w200 easyui-validatebox"
								missingMessage="组内顺序必填"
								data-options="required:true" value="${obj.sortOrder}"/>
						</p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>是否使用: </label>
							<@cont.select class="txt w200 easyui-validatebox" id="useYn" name="useYn" value="${obj.useYn}" codeDiv="USE_YN" />
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
