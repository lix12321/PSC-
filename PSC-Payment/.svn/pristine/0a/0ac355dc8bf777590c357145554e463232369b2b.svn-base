<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/syscodemaster"/>

<script>

    var statusBox;
	$(function(){
	    <#noescape>
			statusBox = eval('(${initJSCodeContainer("USE_YN")})');
		</#noescape>

		$('#btn_add').click(function() {
			location.href = '${currentBaseUrl}/add';
		});

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/edit?codeDiv=' + selected.codeDiv +"&codeCd=" + selected.codeCd;
		});

		$('#btn_del').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.confirm('确认', '确定删除该条数据吗?', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						url : '${currentBaseUrl}/del?codeDiv=' + selected.codeDiv +"&codeCd=" + selected.codeCd,
						success : function() {
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
						}
					});
				}
			});

		});
	});
    function formatStatus(value, row, index) {
        return statusBox["USE_YN"][value];
    }
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto; background: #fff;" border="false">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<input id='textbox0' class='easyui-textbox' name='q_codeDiv'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'字典组',width:'95%',height:'26'" />
					</p>
					<p class="p4 p-item">
						<a id="btn-search" href="javascript:void(0)" data-options="height:'26'"
							class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'/syscodemaster/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="codeCd" width="60" align="center">字典ID</th>
				<th field="codeDiv" width="60" align="center">字典组</th>
				<th field="codeText" width="60" align="center">字典中文名称</th>
				<th field="sortOrder" width="60" align="center">组内顺序</th>
				<th field="useYn" width="60" align="center" formatter="formatStatus">是否使用</th>
				<th field="createTime" width="60" align="center">创建时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="gridTools">
		<@shiro.hasPermission name="/syscodemaster/add">
			<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/syscodemaster/edit">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/syscodemaster/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
</div>

<#include "/commons/_detailfooter.ftl" />
