<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/ccp"/>
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
			通道注册<span class="s-poar"> <a
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
							<label class="lab-item"><font class="red">*</font>操作号: </label>
							<input type="text" id="orgName" name="transCode"
								class="txt w200 easyui-validatebox" value="${transcode!}" readonly
								missingMessage="机构名称必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>系统服务号: </label>
							 <select class="txt w200 easyui-validatebox" name="serverCode" id="serverCode">
							 	<#list serverCodeList as sc>
							 		<option value="${(sc.serverCode)!}">${(sc.serverName)!}</option>
							 	</#list>
							 </select>
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>入参格式: </label>
                            <textarea name="inFormat" rows="2" cols="120" id="inFormat" 
                            	style="width:61.6%" data-options="required:true"
                            	class="txt" ></textarea>
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>出参格式: </label>
                            <textarea name="outFormat" rows="2" cols="120" id="outFormat" 
                            	style="width:61.6%" data-options="required:true"
                            	class="txt" ></textarea>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>请求域: </label>
                            <input type="text" id="orgIndex" name="requestDomain"
                                   class="txt w200 easyui-validatebox"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>请求方法: </label>
                            <input type="text" id="orgIndex" name="requestMethod"
                                   class="txt w200 easyui-validatebox"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>请求端口: </label>
                            <input type="text" id="orgIndex" name="requestPort"
                                   class="txt w200 easyui-numberbox" value="80"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>请求类型: </label>
                            <@cont.select class="txt w200 easyui-validatebox" name="requestType" id="requestType" codeDiv="REQUEST_TYPE" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>超时时间: </label>
                            <input type="text" id="orgIndex" name="timeOut"
                                   class="txt w200 easyui-validatebox"
                                   data-options="required:true,validType:'length[2,40]'" />
                        </p>
                        	<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>是否缓存: </label>
                            <@cont.select class="txt w200 easyui-validatebox" name="cache" id="cache" codeDiv="YES_NO" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>缓存时间: </label>
							<input type="text" id="pyCode" name="cacheDuration"
								class="txt w200 easyui-numberbox"
								missingMessage="拼音码必填，2-20个字符"
								data-options="required:false,validType:'length[1,20]'" />
						</p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>数据格式: </label>
                            <@cont.select class="txt w200 easyui-validatebox" name="dataType" id="dataType" codeDiv="DATA_TYPE" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>是否启用: </label>
                            <@cont.select class="txt w200 easyui-validatebox" name="enable" id="enable" value="1" codeDiv="ENABLE" />
                        </p>
					</div>
						
				</dd>
			</dl>
			
			<dl class="dl-group helpinfo">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <ul>
							<li>操作号由系统自动生成</li>
							<li>启用缓存：系统会将此通道的路由及数据缓存，以提高访问效率，如果需要时时获取数据，请禁用缓存</li>
							<li>缓存时间单位为天，请输入整数。-1表示永久，0或不填表示不缓存</li>
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
