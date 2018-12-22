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
						<input id='textbox0' class='easyui-textbox' name='q_notifyTime'
							require='false'
							data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'通知时间',width:'95%',height:'26'" />
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
    					,url:'/paynotifylog/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'post'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="notifyTime" width="60" align="center">通知时间</th>
				<th field="notifyStatus" width="60" align="center">状态</th>
				<th field="error" width="60" align="center">异常信息</th>
			</tr>
		</thead>
	</table>

</div>

<#include "/commons/_detailfooter.ftl" />
