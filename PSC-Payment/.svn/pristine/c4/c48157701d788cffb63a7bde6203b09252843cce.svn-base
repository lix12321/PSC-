package cn.wellcare.service.modules.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.model.modules.statistics.OrderPayModelCountModel;
import cn.wellcare.payment.modules.statistics.IOrderPayModelCountService;

@Service(value = "orderPayModelCountService")
public class OrderPayModelCountService implements IOrderPayModelCountService {

    @Resource
    private OrderPayModelCountModel orderPayModelCountModel;

    @Override
    public Map<String,Object> getChartData(Map<String,Object> paramMap){
        Map<String,Object> resultMap = new HashMap<>();
        //根据条件查询
        List<Map<String,Object>> findResult = orderPayModelCountModel.getChartData(paramMap);
        //分装结果集
        List<String> xAxisList = new ArrayList<>();
        List<Object> seriesList = new ArrayList<>();
        if (null != findResult && !findResult.isEmpty()){
            for (Map<String,Object> map : findResult){
                Map<String,Object> objMap = new HashMap<>();
                objMap.put("name",map.get("paymode"));
                objMap.put("value",map.get("ordermoney"));
                xAxisList.add((String) map.get("paymode"));
                seriesList.add(objMap);
            }
        }
        resultMap.put("legend",xAxisList);
        resultMap.put("series",seriesList);
        return resultMap;
    }
}
