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
        //初始化echars
        loadChart();
        //获取数据
        getChartData();
        //myChart.setOption(option);
    });

    function loadChart() {
        myChart = echarts.init(document.getElementById('chartdiv'));
        var option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            toolbox: {
                show : true,
                feature : {
                    dataView: {show: true, readOnly: false,optionToContent: function(opt) {
                            var series = opt.series[0].data;
                            var table = '<table style="width:100%;text-align:center"><tbody><tr>';

                            for (var j = 0;  j < series.length; j++) {
                                table += '<td>' + series[j].name + '</td>';

                            }
                            table += '</tr><tr>';
                            for (var i = 0, l = series.length; i < l; i++) {
                                table += '<td>' + series[i].value + '</td>';


                            }
                            table += '</tr></tbody></table>';
                            return table;
                        }},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            legend: {
                itemGap:15,
                data: []
            },
            series : [
                {
                    type: 'pie',
                    radius: ['30%', '70%'],
                    data:[]
                }
            ]
        };
        myChart.setOption(option,true);
    }

    function getChartData() {
        var startTime = $("#startTime").datebox('getValue');
        var endTime = $("#endTime").datebox('getValue');
        var opType = $("#opType").val();
        $.ajax({
            type: "get",
            url: "/ordercompletedcount/getChartData",
            data: {q_startTime:startTime,q_endTime:endTime,q_opType:opType},
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
        newOption.legend.data = [];
        newOption.series[0]['data'] = [];
        myChart.setOption(newOption,true,false);
    }

    function delResult(data) {
        var newOption = myChart.getOption();
        if (data) {
            //newOption.legend.data = data['legend'];
            var legend = {data : []};
            $.each(data['series'],function(key,values){
                var obj = {};
                obj['name'] = key;
                obj['value'] = values;
                newOption.series[0].data.push(obj);
                legend.data.push(key);
            });
            newOption.legend = legend;
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
            <form class="form-search" action=""
                  method="get" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item-cho">开始时间:</label>
                        <input class="easyui-datebox" id="startTime" data-options="editable:false">
                    </p>
                    <p class="p4 p-item left15">
                        <label class="lab-item-cho">结束时间:</label>
                        <input class="easyui-datebox" id="endTime" data-options="editable:false">
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
                <label class="lab-item help"> 查询指定日期的订单支付占比 </label>
            </div>
        </dd>
    </dl>

</div>

<#include "/commons/_detailfooter.ftl" />
