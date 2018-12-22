<#assign currentBaseUrl="/systemresources"/>

<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#editResourcesForm").form('validate');
			if (isValid) {
				var type = $('#type').combobox('getValue');
				if (type == "") {
					$.messager.alert('提示', '请选择资源类型。');
					return;
				}
				
				$.messager.progress({
					text : "提交中..."
				});
				$("#editResourcesForm").form('submit', {
					url : "${currentBaseUrl}/doAdd",
					success : function(e) {
						$.messager.progress('close');
						$('#dataGrid,window.parent.document').treegrid('reload');
						closeW();
					}
				});
			}
		});
	});
	
	function closeW(){
		$("#editResources,window.parent.document").window("close");
	}
	
	function ls2(data, obj){
		var t = $("#pid").combotree('tree');
	    var node = t.tree('getSelected');
	    if (node){
			t.tree('expandTo', node.target);
	    }
	    dataGridLoadSuccess(data, obj);
	}
</script>
<div class="formbox-a">
	<form id="editResourcesForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">

				<dd class="dd-group">
                    <input type="hidden" name="id" value="${obj.id}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源名称: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="content"
								name="content"
								data-options="required:true,validType:'length[1,40]'"
								class="txt w400" value="${obj.content}"/> <span class="title_span">长度为1-40个字符</span>

							</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>&nbsp;父&nbsp;资&nbsp;源:
							</label> 
							<input id="pid" class="txt w200 easyui-combotree" name="pid"
								value="${obj.pid}"
								data-options="
								url:'${currentBaseUrl}/resTree',
								method:'post',
								onLoadSuccess:ls2,
								required:true" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源链接: </label>
							<input class="txt w500 easyui-validatebox" type="text" id="url"
								name="url" 
								data-options="required:true,validType:'length[1,255]'" value="${obj.url}"/>
							<span class="title_span">长度为1-255个字符</span>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								填写完成该资源操作需要的所有链接地址，并用英文逗号分隔，不能有空格或其他特殊字符；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								菜单类型的资源链接的第一个链接必须填写打开菜单所指向资源的链接，如下/admin/order为打开列表页的链接，后两个为获取数据的链接；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								新增编辑数据页面，需要填写打开页面链接、保存数据的链接，[/admin/resource/add,/admin/resource/create]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								如果打开页面还会调用其他资源链接，请用逗号分隔添加在之后，如[/admin/resource/add,/admin/resource/create,/admin/resource/listparent]；
								</font>
							</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源类型: </label>
							<select id="type" class="txt w200 easyui-combobox" name="type"
								editable="false" data-options="required:true">
								<option value="2" <#if obj.type == 2>selected</#if>>按钮</option>
								<option value="1" <#if obj.type == 1>selected</#if>>菜单</option>
							</select>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<select id="scope" class="txt w200 easyui-combobox" name="status"
								editable="false" data-options="required:true">
								<option value="1" <#if obj.status == 1>selected</#if>>使用</option>
								<option value="2" <#if obj.status == 2>selected</#if>>未使用</option>
							</select>
						</p>
					</div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>资源图标id: </label>
                            <input class="txt w200 easyui-validatebox" type="text" id="resId"
                                    name="resId"
                                    data-options="required:true,validType:'length[1,40]'"
                                    class="txt w400" value="${obj.resId}"/> <span class="title_span">长度为1-40个字符</span>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>资源图标: </label>
                            <input class="txt w200 easyui-validatebox" type="text" id="resIcon"
                                   name="resIcon"
                                   data-options="required:true,validType:'length[1,40]'"
                                   class="txt w400" value="${obj.resIcon}"/> <span class="title_span">长度为1-40个字符</span>
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