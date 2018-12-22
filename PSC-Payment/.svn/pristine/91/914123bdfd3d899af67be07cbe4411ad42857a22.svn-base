package cn.wellcare.model.modules.statistics;

import cn.wellcare.dao.statistics.TodayOrderPayStateCountDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class TodayOrderPayStateCountModel {

    @Resource
    private TodayOrderPayStateCountDao todayOrderPayStateCountDao;

    public List<Map<String, Object>> getChartData(Map<String, Object> paramMap) {

        return todayOrderPayStateCountDao.getChartData(paramMap);
    }
}
