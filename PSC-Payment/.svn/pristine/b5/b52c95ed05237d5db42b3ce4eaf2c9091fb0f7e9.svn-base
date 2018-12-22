<#include "/commons/_detailheader.ftl" />
<#import "/commons/_macro_controller.ftl" as cont/>

<script>
    var myChart;

    $(function() {
       $('#startTime').datebox('setValue', getDay(-7));
       $('#endTime').datebox().datebox('setValue', 'calendar').calendar({
            validator: function (date) {
                var now = new Date();
                var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                return date <= d1;
            }
        });
        //初始化echars
        loadChart();
        //获取数据
        getChartData();
        //查询事件
        $("#doSearch").click(function() {
            if (!$("#startTime").val()) {
                $.messager.alert('提示', '请选择开始时间');
                return;
            }
            if (!$("#endTime").val()) {
                $.messager.alert('提示', '请选择结束时间');
                return;
            }
            //$("#queryForm").submit();
            getChartData();
        });
	});
    
    function loadChart() {
        myChart = echarts.init(document.getElementById('chartdiv'));
        var option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:[]
            },
            calculable : true,
            toolbox: {
                show : true,
                feature : {
                    dataView: {show: true, readOnly: false,optionToContent: function(opt) {
                            var axisData = opt.xAxis[0].data;
                            var series = opt.series;
                            var table = '<table style="width:100%;text-align:center"><tbody><tr>'
                                    + '<td>时间</td>';
                            for (var j = 0;  j < series.length; j++) {
                                table += '<td>' + series[j].name + '</td>';

                            }
                            + '</tr>';
                            for (var i = 0, l = axisData.length; i < l; i++) {
                                table += '<tr>'
                                        + '<td>' + axisData[i] + '</td>';
                                for (var t = 0; t < series.length; t++) {
                                    table += '<td>' + series[t].data[i] + '</td>';
                                }
                                + '</tr>';
                            }
                            table += '</tbody></table>';
                            return table;
                        }},
                    magicType : {show: true, type: ['line', 'bar','stack']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            xAxis : [
                {
                    type : 'category',
                    data : []
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : []
        };
        myChart.setOption(option,true);
    }

    function getChartData() {
        var startTime = $("#startTime").datebox('getValue');
        var endTime = $("#endTime").datebox('getValue');
        var opType = $("#opType").val();
        var paymentType = $("#paymentType").val();
        $.ajax({
            type: "get",
            url: "/orderpaymodeltrendbyhour/getChartData",
            data: {q_startTime:startTime,q_endTime:endTime,q_opType:opType,q_paymentType:paymentType},
            beforeSend:function(){
                // 清除旧的查询
                clearChart();
            },
            success: function(data){
                delResult(data);
            }
        });
    }
    
    function clearChart() {
        var newOption = myChart.getOption();
        newOption.xAxis[0].data = [];
        newOption.legend = [];
        newOption.series = [];
        myChart.setOption(newOption,true);
    }
    
    function delResult(data) {
        var newOption = myChart.getOption();
        if(data){
            newOption.xAxis[0].data = data['xAxis'];
            //newOption.legend.data = data['legend'];
            newOption.legend.data = [];
            var legend = {data:data['legend']};
            newOption.legend = legend;
            $.each(data['series'],function(key,values){
                var obj = {};
                obj['name'] = key;
                obj['data'] = values;
                obj['type'] = 'line';
                //newOption.legend.data.push(key);
                newOption.series.push(obj);
            });
        }
        myChart.setOption(newOption,true);
    }
    function getDay(AddDayCount){
        var dd = new Date();
        dd.setDate(dd.getDate() + AddDayCount);   //获取AddDayCount天后的日期
        var year = dd.getFullYear();
        var mon = dd.getMonth()+1;                             //获取当前月份的日期
        var day = dd.getDate();
        return year + '-' + ( mon < 10 ? ( '0' + mon ) : mon ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
    }
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action=""
				method="get" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item-cho">开始时间:</label> <#--<input
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});"
							type="text" class="txt inputtxt" id="startTime" name="startTime"
							value="" />-->
                        <input class="easyui-datebox" id="startTime" data-options="editable:false">
					</p>
					<p class="p4 p-item left15">
						<label class="lab-item-cho">结束时间:</label> <#--<input
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"
							type="text" class="txt inputtxt" id="endTime" name="endTime"
							value="" />-->
                        <input class="easyui-datebox" id="endTime" data-options="editable:false">
					</p>
                    <p class="p2 p-item left15">
                        <label class="lab-item-cho">收支类型:</label>
                        <@cont.select class="easyui-validatebox" name="opType" id="opType"  codeDiv="OP_TYPE" />
                    </p>
                    <p class="p2 p-item left15">
                        <label class="lab-item-cho">支付类型:</label>
                        <input class="easyui-combobox" id="paymentType" data-options="
                            valueField: 'codeCd',
                            textField: 'codeText',
                            multiple:true,
                            method: 'get',
                            url:'/orderpaymodeltrendbyhour/getPaymentType'" />
                        <#--<select  class="txt w200 easyui-combobox" id="paymentType">
                            <#list paymentType as item>
                                <option  value="${item.codeCd}">${item.codeText}</option>
                            </#list>
                        </select>-->
                    </p>
					<p class="p-item p4 report-query-p">
						<input type="button" id="doSearch" class="easyui-linkbutton" value="提交" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<div id="chartdiv" style="width: 100%; height: 100%;"></div>
</div>
<div data-options="region:'south'" style="height: 100px;">
	<dl class="dl-group">
		<dt class="dt-group">
			<span class="s-icon"></span>帮助
		</dt>
		<dd class="dd-group">
			<div class="fluidbox">
				<label class="lab-item help"> 查询支付平台指定时间段内每天每小时的各支付方式各订单类型的支付笔数变化趋势 </label>
			</div>
		</dd>
	</dl>

</div>

<#include "/commons/_detailfooter.ftl" />
