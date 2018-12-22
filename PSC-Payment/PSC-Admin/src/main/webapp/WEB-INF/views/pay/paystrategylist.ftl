<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/paystrategy"/>

<script>
    var statusBox;
    $(function(){
         <#noescape>
			statusBox = eval('(${initJSCodeContainer("PAY_STRATEGY","USE_YN")})');
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
            $("#paystrategyadd").window({
                width : 550,
                height : 330,
                href : "${currentBaseUrl}/add",
                title : "新增支付策略",
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
            //判断是否有可用的，如果有则只能修改可用的，反之则都可以修改
            if (hasAvaliable()){
                if (selected.status==0){
                    $.messager.alert('提示', '请选择修改可用状态。');
                    return false;
                }
            }
            $("#paystrategyedit").window({
                width : 550,
                height : 330,
                href : "${currentBaseUrl}/edit?id="+ selected.id,
                title : "编辑支付策略",
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
            $.messager.confirm('确认', '确定删除该支付策略吗?', function(r) {
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
        return statusBox["PAY_STRATEGY"][value];
    }
    function formatIsUse(value, row, index) {
        return statusBox["USE_YN"][value];
    }
    function hasAvaliable(){
        var rows = $("#dataGrid").datagrid("getRows");
        for(var i=0; i<rows.length; i++){
            if (rows[i].status==1){
                return true;
            }
        }
    }
//
    function searchData() {
        var param = $("#textbox0").val();
        var paramStr = '{';
        if (param){
            var keyStr;
            Object.keys(statusBox["PAY_STRATEGY"]).forEach(function (key) {
                if (statusBox["PAY_STRATEGY"][key].indexOf(param) != -1)
                    keyStr = key;
            });
            if (keyStr){
                paramStr += '"'+$("#textbox0").attr('textboxname')+'":"'+ keyStr+'"';
            }
        }
        paramStr += '}';
        $('#dataGrid').datagrid('reload', eval('('+paramStr+')'));
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
                        <input id='textbox0' class='easyui-textbox' name='q_payStrategy'
                               require='false'
                               data-options="name:'textbox0',id:'textbox0',type:'text',multiline:false,label:'支付策略',width:'95%',height:'26'" />
                    </p>
                    <p class="p4 p-item">
                        <a href="javascript:void(0)" data-options="height:'26'"
                           class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchData();">查询</a>
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
    					,url:'/paystrategy/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="id" hidden="hidden"></th>

            <th field="orgName" width="60" align="center">机构</th>
            <th field="payStrategy" width="60" align="center" formatter="formatStatus">支付策略</th>
            <th field="status" width="40" align="center" formatter="formatIsUse">是否使用</th>
        </tr>
        </thead>
    </table>

    <div id="gridTools">
		<@shiro.hasPermission name="/paystrategy/add">
			<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/paystrategy/edit">
			<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/paystrategy/del">
			<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
    </div>

    <div class="wrapper" id="paystrategyadd">
    </div>
    <div class="wrapper" id="paystrategyedit">
    </div>
</div>

<#include "/commons/_detailfooter.ftl" />
