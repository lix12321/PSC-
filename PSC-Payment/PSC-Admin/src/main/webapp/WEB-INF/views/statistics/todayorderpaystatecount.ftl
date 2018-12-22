<#include "/commons/_detailheader.ftl" />
<script>
    var myChart;

    $(function() {
        $("#queryType").combobox({
            url : "/todayorderpaystatecount/getQueryType",
            method : "get",
            onLoadSuccess:function(){ //默认选中第一条数据
                var data = $(this).combobox("getData");
                if (data.length > 0) {
                    $('#queryType').combobox('select', data[0].codeCd);
                }
            }
        });
        //初始化echars
        loadChart();
        //获取数据
        getChartData();
        //查询事件
        $("#doSearch").click(function() {
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
            calculable : true,
            toolbox: {
                show : true,
                feature : {
                    dataView: {show: true, readOnly: false,optionToContent: function(opt) {
                            var axisData = opt.xAxis[0].data;
                            var series = opt.series;
                            var table = '<table style="width:100%;text-align:center"><tbody><tr>'
                                    + '<td>订单状态</td>';
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
                    magicType : {show: true, type: ['bar','line' ,'stack']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            xAxis : [
                {
                    data : []
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    name: '单位（元）'
                }
            ],
            series : [
                {
                    name:'订单金额',
                    type:'bar',
                    barWidth: '30%',
                    data:[]
                }
            ]
        };
        myChart.setOption(option,true,false);
    }

    function getChartData() {
        var q_type = $("#queryType").val();
        var obj = {};
        if ('2'==q_type) {
            var myDate = new Date();
            obj['q_month'] = myDate.getFullYear()+"-"+(myDate.getMonth()+1);
        }else{
            obj['q_today'] = 1;
        }
        $.ajax({
            type: "get",
            url: "/todayorderpaystatecount/getChartData",
            data: obj,
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
        newOption.series[0].data = [];
        myChart.setOption(newOption,true,false);
    }

    function delResult(data) {
        var newOption = myChart.getOption();
        if(data){
            newOption.xAxis[0].data = data['xAxis'];
            newOption.series[0].data = data['series'];
        }
        myChart.setOption(newOption,true,false);
    }

</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="/orderpaymodeltrendbyday"
				method="get" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
                        <label class="lab-item-cho">查询类型:</label>
                        <input class="easyui-combobox" id="queryType" data-options="
                            valueField: 'codeCd',
                            textField: 'codeText',
                            method: 'get',
                            url:'/todayorderpaystatecount/getQueryType',panelHeight:'auto'" />
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
				<label class="lab-item help"> 查询支付平台指定时间段内按天各支付方式各订单类型的收支总额变化趋势 </label>
			</div>
		</dd>
	</dl>

</div>

<#include "/commons/_detailfooter.ftl" />
