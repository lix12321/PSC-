<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/systemresources"/>

<script>
    var statusBox;
	$(function(){
	    <#noescape>
			statusBox = eval('(${initJSCodeContainer("RESOURCES_TYPE","RESOURCES_STATUS")})');
		</#noescape>
		$('#btn_add').click(function() {
			var selected = $('#dataGrid').treegrid('getSelected');
			var id = "";
			if(selected)
				id = selected.id;
			else
				id="1";
			$("#addResources").window({
				width : 1100,
				height : 820,
				href : "${currentBaseUrl}/addWin?id="+id,
				title : "新增资源",
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
            var id = "";
            if(selected)
                id = selected.id;
            else
                id="1";
            $("#editResources").window({
                width : 1100,
                height : 820,
                href : "${currentBaseUrl}/editWin?id="+id,
                title : "编辑资源",
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
			$.messager.confirm('确认', '确定删除该资源吗?', function(r) {
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
	
	function bl(row, param) {
		if (!row) {
			param.id = 0;
		}
	}
	
	function loadSuccess(row, data) {
		$('#dataGrid').treegrid('expand',1);
	}

    function formatType(value, row, index) {
        return statusBox["RESOURCES_TYPE"][value];
    }

    function formatStatus(value, row, index) {
        return statusBox["RESOURCES_STATUS"][value];
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
						<input id='textbox0' class='easyui-textbox' name='q_sysId'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'资源表id',width:'95%',height:'26'" />
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
	<table id="dataGrid" class="easyui-treegrid"
		data-options="rownumbers:false
						,singleSelect:true
						,autoRowHeight:true
						,animate:true
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:false
						,pageSize:'2'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:loadSuccess
						,treeField:'content'
						,idField:'id'
    					,method:'post'
						,onBeforeLoad:bl">
		<thead>
			<tr>
				<th field="content" width="120" align="left" halign="center">资源名称</th>
				<th field="url" width="120" align="left" halign="center">资源链接</th>
				<th field="type" width="60" align="center" formatter="formatType">资源类型</th>
				<th field="status" width="60" align="center" formatter="formatStatus">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="gridTools">
		<@shiro.hasPermission name="/systemresources/addWin">
			<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemresources/editWin">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/systemresources/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
	
	<div class="wrapper" id="addResources">
	</div>
    <div class="wrapper" id="editResources">
    </div>
</div>

<#include "/commons/_detailfooter.ftl" />
