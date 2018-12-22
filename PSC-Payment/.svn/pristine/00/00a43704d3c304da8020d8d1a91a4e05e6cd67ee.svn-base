package cn.wellcare.model.modules.statistics;

import cn.wellcare.dao.statistics.OrderPayModelTrendByDayDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class OrderPayModelTrendByDayModel {

    @Resource
    private OrderPayModelTrendByDayDao orderPayModelTrendByDayDao;

    public List<Map<String, Object>> getChartData(Map<String, Object> paramMap) {
        List<Map<String,Object>> resultList = orderPayModelTrendByDayDao.findOrderPayModelTrendByDay(paramMap);
        return resultList;
    }
}
