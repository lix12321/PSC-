<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/paynotify"/>

<script>
    var statusBox;
	$(function(){
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("TRADE_STATUS","NOTIFY_STATUS")})');
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
    function formatTradeStatus(value,row,index){
        return statusBox["TRADE_STATUS"][value];
    }
    function formatNotifyStatus(value,row,index){
        return statusBox["NOTIFY_STATUS"][value];
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
						<input id='textbox0' class='easyui-textbox' name='q_orderSn'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'订单号',width:'95%',height:'26'" />
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
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'/paynotify/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="orderId" width="60" align="center">交易订单</th>
				<th field="orderSn" width="60" align="center">订单号</th>
                <th field="body" width="60" align="center">订单描述信息</th>
				<th field="backUrl" width="60" align="center">推送地址</th>
				<th field="notifyTime" width="60" align="center">通知发送时间</th>
                <th field="gmtPayment" width="60" align="center">买家付款时间</th>
                <th field="totalAmount" width="60" align="center">交易金额</th>
                <th field="sendBackFee" width="60" align="center">实际退款金额</th>
                <th field="refundFee" width="60" align="center">退款金额</th>
                <th field="tradeNo" width="60" align="center">交易凭证号</th>
                <th field="tradeStatus" width="60" align="center" formatter="formatTradeStatus">交易状态</th>
                <th field="gmtRefund" width="60" align="center">交易退款时间</th>
				<th field="gmtClose" width="60" align="center">交易结束时间</th>
                <th field="notifyStatus" width="60" align="center" formatter="formatNotifyStatus">通知状态</th>
			</tr>
		</thead>
	</table>

</div>

<#include "/commons/_detailfooter.ftl" />
