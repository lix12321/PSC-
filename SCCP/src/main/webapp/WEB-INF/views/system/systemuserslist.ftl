<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/systemusers"/>

<script>
	$(function(){
		$('#btn_add').click(function() {
			location.href = '${currentBaseUrl}/adduser';
		});

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/edituser?id=' + selected.id;
		});
        $('#btn_allot').click(function() {
            var selected = $('#dataGrid').datagrid('getSelected');
            if (!selected) {
                $.messager.alert('提示', '请选择操作行。');
                return;
            }
            $("#allotRoleWin").window({
                width : 460,
                height : 290,
                title : "分配角色",
                href : "${currentBaseUrl}/allotRole?id="+selected.id+"&loginName="+selected.loginName,
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
						<input id='textbox0' class='easyui-textbox' name='q_loginName'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'用户名',labelWidth:'110px',width:'95%',height:'26'" />
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
    					,url:'/systemusers/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="loginName" width="60" align="center">用户名</th>
				<th field="remark" width="60" align="center">备注</th>
               <#-- <th field="orgCode" width="60" align="center">组织机构</th>-->
				<th field="createTime" width="60" align="center">创建时间</th>


			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/systemusers/adduser">
			<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemusers/edituser">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemusers/role2Res">
			<a id="btn_allot" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true">分配角色</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemusers/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>

    <div class="wrapper" id="allotRoleWin">
    </div>

</div>

<#include "/commons/_detailfooter.ftl" />
