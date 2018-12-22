<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/ccp"/>

<script>
	var statusBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
		statusBox = eval('(${initJSCodeContainer("ENABLE","REQUEST_TYPE","DATA_TYPE","YES_NO")})');
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
			location.href = '${currentBaseUrl}/edit?id=' + selected.id;
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
	
	function fmtenable(value, row, index) {
		return statusBox["ENABLE"][value];
	}
	
	function fmtdatatype(value, row, index) {
		return statusBox["DATA_TYPE"][value];
	}
	
	function fmtreq(value, row, index) {
		return statusBox["REQUEST_TYPE"][value];
	}
	
	function fmtyn(value, row, index) {
		return statusBox["YES_NO"][value];
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
						<input id='textbox0' class='easyui-textbox' name='q_transCode'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'操作号',labelWidth:'60px',width:'95%',height:'26'" />
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
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="transCode" width="60" align="center">操作号</th>
				<th field="serverCode" width="60" align="center">服务编号</th>
				<th field="cache" width="60" align="center" formatter="fmtyn">是否缓存</th>
				<th field="requestType" width="60" align="center" formatter="fmtreq">请求方式</th>
				<th field="requestDomain" width="60" align="center" >请求域</th>
				<th field="requestPort" width="60" align="center">目标端口</th>
				<th field="requestMethod" width="60" align="center">请求方法</th>
				<th field="dataType" width="60" align="center" formatter="fmtdatatype">数据格式</th>
				<th field="enable" width="60" align="center" formatter="fmtenable">是否启用</th>
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
		<@shiro.hasPermission name="/sysorganization/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>

    <div class="wrapper" id="allotRoleWin">
    </div>

</div>

<#include "/commons/_detailfooter.ftl" />
