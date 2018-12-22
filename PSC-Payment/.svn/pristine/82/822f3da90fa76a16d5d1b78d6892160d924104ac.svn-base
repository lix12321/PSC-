<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/order"/>

<script>

</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto; background: #fff;" border="false">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<input id='textbox0' class='easyui-textbox' name='q_accountName'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'账户名',width:'95%',height:'26'" />
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
    					,url:'/paylog/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="accountId" width="60" align="center">账户ID</th>
				<th field="accountName" width="60" align="center">账户名</th>
				<th field="outerOrderSn" width="60" align="center">订单号</th>
				<th field="paymentName" width="60" align="center">支付方式名称</th>
				<th field="paySn" width="60" align="center">支付订单号</th>
				<th field="tradeSn" width="60" align="center">交易流水号</th>
				<th field="createTime" width="60" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

</div>

<#include "/commons/_detailfooter.ftl" />
