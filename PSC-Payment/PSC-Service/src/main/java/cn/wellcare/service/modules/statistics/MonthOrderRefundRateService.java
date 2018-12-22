package cn.wellcare.service.modules.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.core.utils.DateUtil;
import cn.wellcare.model.modules.statistics.MonthOrderRefundRateModel;
import cn.wellcare.payment.modules.statistics.IMonthOrderRefundRateService;

@Service("monthOrderRefundRateService")
public class MonthOrderRefundRateService implements IMonthOrderRefundRateService {

    @Resource
    private MonthOrderRefundRateModel monthOrderRefundRateModel;

    private static final String ORDER_DATE = "orderdate";

    private static final String ORDER_REFUND_RATE = "refundrate";

    @Override
    public Map<String, Object> getChartData(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>();

        //根据条件查询
        List<Map<String,Object>> result = monthOrderRefundRateModel.getData(queryMap);


        //封装结果集
        List<String> xAxisList = DateUtil.getCurrentMonthDateStr();
        //Map<String,Object> seriesMap = new HashMap<>();
        List<Object> seriesList = new ArrayList<>();
        if (xAxisList != null && xAxisList.size()>0){
            for (String xAxis : xAxisList){
                BigDecimal value = getDateValue(result,xAxis);
                seriesList.add(value);
            }
        }

        //resultMap.put("legend",legendList);
        resultMap.put("xAxis",xAxisList);
        resultMap.put("series",seriesList);
        return resultMap;
    }

    private BigDecimal getDateValue(List<Map<String,Object>> dataList, String dataStr){
        BigDecimal value = new BigDecimal(0);
        if (null != dataList && !dataList.isEmpty()){
            for (Map<String,Object> map : dataList){
                if (dataStr.equals(map.get(this.ORDER_DATE))){
                    if (map.get(this.ORDER_REFUND_RATE) != null){
                        value = (BigDecimal) map.get(this.ORDER_REFUND_RATE);
                    }
                }
            }
        }
        return value;
    }
}
