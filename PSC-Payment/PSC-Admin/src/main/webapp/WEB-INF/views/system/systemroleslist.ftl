<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/systemroles"/>

<script>
    var statusBox;
	$(function(){
	    <#noescape>
			statusBox = eval('(${initJSCodeContainer("RULE_STATUS")})');
		</#noescape>
		$('#btn_add').click(function() {
            $("#addRole").window({
                width : 600,
                height : 500,
                href : "${currentBaseUrl}/addroleswin",
                title : "新增角色",
                modal : true,
                shadow : false,
                collapsible : false,
                minimizable : false,
                maximizable : false
            });
		});

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
            $("#editRole").window({
                width : 600,
                height : 500,
                href : "${currentBaseUrl}/editWin?id="+ selected.id,
                title : "编辑角色",
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
			$.messager.confirm('确认', '确定删除该角色吗?', function(r) {
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
		
		//分配权限
		$("#btn_res").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$("#allotResourceWin").window({
				width : 400,
				height : 510,
				title : "分配权限",
				href : "${currentBaseUrl}/role2Res?id="+selected.id+"&rolesName="+selected.rolesName,
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
			
		});
	});
    function formatStatus(value, row, index) {
        return statusBox["RULE_STATUS"][value];
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
						<input id='textbox0' class='easyui-textbox' name='q_rolesName'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'角色名称',width:'95%',height:'26'" />
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
    					,url:'/systemroles/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="rolesName" width="60" align="center">角色名称</th>
				<th field="content" width="60" align="center">角色描述</th>
				<th field="userId" width="60" align="center">创建人</th>
                <th field="roleCode" width="60" align="center">角色code</th>
				<th field="createTime" width="60" align="center">创建时间</th>
				<th field="updateTime" width="60" align="center" >更新时间</th>
				<th field="status" width="60" align="center" formatter="formatStatus">状态</th>

			</tr>
		</thead>
	</table>
	
	<div id="gridTools">
		<@shiro.hasPermission name="/systemroles/addroleswin">
			<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemroles/editWin">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemroles/role2Res">
			<a id="btn_res" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true">分配权限</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemroles/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
	
	<div id="allotResourceWin">
	</div>
    <div class="wrapper" id="addRole">
    </div>
    <div class="wrapper" id="editRole">
    </div>
</div>

<#include "/commons/_detailfooter.ftl" />
