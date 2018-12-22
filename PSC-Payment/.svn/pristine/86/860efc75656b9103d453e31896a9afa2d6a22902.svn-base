<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/sysorganization"/>

<script>
	$(function(){
		$('#btn_add').click(function() {
			location.href = '${currentBaseUrl}/add';
		});

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/edit?id=' + selected.id;
		});
        $('#btn_allot').click(function() {
            var selected = $('#dataGrid').datagrid('getSelected');
            if (!selected) {
                $.messager.alert('提示', '请选择操作行。');
                return;
            }
            $("#allotRoleWin").window({
                width : 400,
                height : 290,
                title : "分配角色",
                href : "${currentBaseUrl}/allotRole?id="+selected.id+"&orgName="+selected.orgName,
                modal : true,
                shadow : false,
                collapsible : false,
                minimizable : false,
                maximizable : false
            });
        });

		$('#btn_del').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.confirm('确认', '确定删除该机构吗?', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						url : '${currentBaseUrl}/del?id=' + selected.id,
						success : function() {
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
						}
					});
				}
			});

		});
	});
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto; background: #fff;" border="false">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<input id='textbox0' class='easyui-textbox' name='q_orgName'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'机构名称',width:'95%',height:'26'" />
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
    					,url:'/sysorganization/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="orgName" width="60" align="center">机构名称</th>
				<th field="shortName" width="60" align="center">机构简称</th>
				<th field="orgCode" width="60" align="center">机构编码</th>
				<th field="pyCode" width="60" align="center">拼音码</th>
				<th field="authName" width="60" align="center" >认证用户名</th>
				<th field="orgIndex" width="60" align="center">机构主页</th>

			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/sysorganization/add">
			<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/sysorganization/edit">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<#--<@shiro.hasPermission name="/sysorganization/all">
			<a id="btn_allot" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true">分配角色</a>
		</@shiro.hasPermission>-->
		<@shiro.hasPermission name="/sysorganization/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>

    <div class="wrapper" id="allotRoleWin">
    </div>

</div>

<#include "/commons/_detailfooter.ftl" />
