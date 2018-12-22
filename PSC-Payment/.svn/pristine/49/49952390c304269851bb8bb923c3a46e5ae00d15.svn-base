<#include "/commons/_detailheader.ftl" />
<#import "/commons/_macro_controller.ftl" as cont/>
<script src='/resources/jslib/My97DatePicker/WdatePicker.js'></script>

<script>
    var myChart;

    $(function() {
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
                formatter: "{b} : {c} (%)"
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
                    type : 'value',
                    name: '单位（%）'
                }
            ],
            series : [
                {
                    name:'',
                    type:'line',
                    data:[]



                }
            ]
        };
        myChart.setOption(option,true,false);
    }

    function getChartData() {

        $.ajax({
            type: "get",
            url: "/monthorderrefundrate/getChartData",
            //data: {q_startTime:startTime,q_endTime:endTime,q_opType:opType,q_paymentType:paymentType},
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
        newOption.legend.data = [];
        newOption.series[0].data = [];
        myChart.setOption(newOption,true,false);
    }

    function delResult(data) {
        var newOption = myChart.getOption();
        if(data){
            console.log(data);
            newOption.xAxis[0].data = data['xAxis'];
            newOption.series[0].data = data['series'];
            /*$.each(data['series'],function(key,values){
                var obj = {};
                obj['name'] = key;
                obj['data'] = values;
                obj['type'] = 'line';
                newOption.series.push(obj);
            });*/
        }
        myChart.setOption(newOption,true,false);
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
			<form class="form-search" action="/orderpaymodeltrendbyday"
				method="get" id="queryForm" name="queryForm">
				<div class="fluidbox">

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
