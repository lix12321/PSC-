package cn.wellcare.model.modules.statistics;

import cn.wellcare.dao.statistics.OrderPayModelCountDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class OrderPayModelCountModel {

    @Resource
    private OrderPayModelCountDao orderPayModelCountDao;

    public List<Map<String,Object>> getChartData(Map<String,Object> paramMap){
        List<Map<String,Object>> resultList = orderPayModelCountDao.findOrderPayModelCount(paramMap);
        return resultList;
    }
}
