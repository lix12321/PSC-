<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/payorder" />

<script>
	var statusBox;
	$(function() {
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
		statusBox = eval('(${initJSCodeContainer("ORDER_STATE","PAYMENT_STATUS")})');
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
			$.messager.confirm('确认', '确定删除该订单吗?', function(r) {
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
		return statusBox["ORDER_STATE"][value];
	}
	function formatPayStatus(value, row, index) {
		return statusBox["PAYMENT_STATUS"][value];
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<input id='textbox0' class='easyui-textbox' name='q_outerOrderSn'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'业务订单号',width:'95%',height:'26'" />
					</p>
					<p class="p4 p-item">
						<a id="btn-search" href="javascript:void(0)"
							data-options="height:'26'" class="easyui-linkbutton"
							iconCls="icon-search" plain="true">查询</a>
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
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'/payorder/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="paySn" width="60" align="left">支付订单号</th>
				<th field="outerOrderSn" width="60" align="left">业务订单号</th>
				<th field="orderType" width="60" align="center">订单类型</th>
				<th field="accountName" width="60" align="center">账户</th>
				<th field="orderState" width="60" align="center"
					formatter="formatStatus">订单状态</th>
				<th field="paymentStatus" width="60" align="center"
					formatter="formatPayStatus">付款状态</th>
				<th field="createTime" width="60" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

</div>

<#include "/commons/_detailfooter.ftl" />
