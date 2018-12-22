package cn.wellcare.model.modules.statistics;

import cn.wellcare.dao.statistics.MonthOrderRefundRateDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class MonthOrderRefundRateModel {

    @Resource
    private MonthOrderRefundRateDao monthOrderRefundRateDao;

    public List<Map<String, Object>> getData(Map<String, Object> queryMap) {
        return monthOrderRefundRateDao.getChartData(queryMap);
    }
}
