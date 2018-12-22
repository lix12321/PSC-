package cn.wellcare.model.modules.statistics;

import cn.wellcare.dao.statistics.OrderPayModelTrendByHourDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class OrderPayModelTrendByHourModel {

    @Resource
    private OrderPayModelTrendByHourDao orderPayModelTrendByHourDao;

    public List<Map<String, Object>> getChartData(Map<String, Object> paramMap) {
        List<Map<String,Object>> resultList = orderPayModelTrendByHourDao.findOrderPayModelTrendByHour(paramMap);
        return resultList;
    }
}
