<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/payposorder"/>

<script>
    var statusBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("SETTLE_STATUS")})');
		</#noescape>
	});
    function formatStatus(value,row,index){
        return statusBox["SETTLE_STATUS"][value];
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
						<input id='textbox0' class='easyui-textbox' name='q_orderId'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'订单ID',width:'95%',height:'26'" />
					</p>
					<p class="p4 p-item">
						<input id='textbox0' class='easyui-textbox' name='q_userno'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'商户号',width:'95%',height:'26'" />
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
    					,url:'/payposorder/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="orderId" width="60" align="left">订单ID</th>
				<th field="userno" width="40" align="center">商户号</th>
				<th field="bankCode" width="60" align="left">银行行号</th>
				<th field="cardNo" width="40" align="center">卡号</th>
				<th field="trace" width="40" align="center" >POS流水号</th>
				<th field="szOrderTrace" width="40" align="center">收银流水号</th>
				<th field="settleStatus" width="40" align="center" formatter="formatStatus">结算状态</th>
				<th field="date" width="40" align="center">交易日期</th>		
				<th field="dtPayMode" width="40" align="center">POS支付方式</th>
			</tr>
		</thead>
	</table>

</div>

<#include "/commons/_detailfooter.ftl" />
