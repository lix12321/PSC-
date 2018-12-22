<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/paysettingalipay"/>

<script>
    function hasAvaliable(){
        var rows = $("#dataGrid").datagrid("getRows");
        for(var i=0; i<rows.length; i++){
            if (rows[i].status==1){
                return true;
            }
        }
	}
	var statusBox;
	$(function(){
	    <#noescape>
			statusBox = eval('(${initJSCodeContainer("PAY_STATUS","ENABLE_NOTIFY")})');
		</#noescape>
		$('#btn_add').click(function() {
            var rows = $("#dataGrid").datagrid("getRows");
            if (rows>=5){
                return;
            }else{
                for(var i=0; i<rows.length; i++){
                    if (rows[i].status==1){
                        $.messager.alert('提示', '只能有一种可用状态，请将可用状态置为不可用。');
                        return false;
                    }
                }
            }
			location.href = '${currentBaseUrl}/add';
		});

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
            //判断是否有可用的，如果有则只能修改可用的，反之则都可以修改
			if (hasAvaliable()){
                if (selected.status==0){
                    $.messager.alert('提示', '请选择修改可用状态。');
                    return false;
                }
			}
			location.href = '${currentBaseUrl}/edit?id=' + selected.id;
		});

		$('#btn_del').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.confirm('确认', '确定删除该设置吗?', function(r) {
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
    function formatStatus(value, row, index) {
        return statusBox["PAY_STATUS"][value];
    }
    function formatEnableNotify(value, row, index) {
        return statusBox["ENABLE_NOTIFY"][value];
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
						<input id='textbox0' class='easyui-textbox' name='q_partner'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'合作身份者ID',width:'95%',height:'26'" />
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
    					,url:'/paysettingalipay/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="orderName" width="60" align="left">订单名称</th>
				<th field="partner" width="60" align="center">合作身份者ID</th>
				<th field="sellerEmail" width="40" align="center">收款支付宝账号</th>
				<th field="signType" width="60" align="center" >签名方式</th>
				<th field="timeout" width="40" align="center" >交易超时时间</th>
				<th field="openApiDomain" width="40" align="center">支付宝网关名</th>
				<th field="mcloudApiDomain" width="40" align="center">监控地址域名</th>
				<th field="pid" width="40" align="center">pid</th>
				<th field="appid" width="40" align="center">appid</th>
                <th field="status" width="40" align="center" formatter="formatStatus">状态</th>
                <th field="enableNotify" width="40" align="center" formatter="formatEnableNotify">是否启用通知</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/paysettingalipay/add">
			<button id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</button>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/paysettingalipay/edit">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/paysettingalipay/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>

</div>

<#include "/commons/_detailfooter.ftl" />
