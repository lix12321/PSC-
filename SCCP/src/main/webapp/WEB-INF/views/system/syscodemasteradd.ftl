<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/syscodemaster"/>

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
			新增数据字典<span class="s-poar"> <a
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
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>字典组: </label>
							<input type="text" id="codeDiv" name="codeDiv" style="text-transform:uppercase;"
								class="txt w200 easyui-validatebox"
								missingMessage="code组必填"
								data-options="required:true" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>字典Id: </label>
							<input type="text" id="codeCd" name="codeCd"
								class="txt w200 easyui-validatebox"
								missingMessage="codeId必填"
								data-options="required:true" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>字典中文名称: </label>
							<input type="text" id="codeText" name="codeText"
								class="txt w200 easyui-validatebox"
								missingMessage="code中文名称必填"
								data-options="required:true" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>组内顺序: </label>
							<input type="text" id="sortOrder" name="sortOrder"
								class="txt w200 easyui-validatebox"
								missingMessage="组内顺序必填"
								data-options="required:true" />
						</p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>是否使用: </label>
                            <select  class="txt w200 easyui-validatebox" name="useYn">
                                <option value ="1">使用</option>
                                <option value ="0">不使用</option>
                            </select>
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
