package cn.wellcare.model.modules.statistics;

import cn.wellcare.dao.statistics.OrderCompletedCountDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class OrderCompletedCountModel {

    @Resource
    private OrderCompletedCountDao orderTailByOrgAndTimeDao;

    public List<Map<String, Object>> getList(Map<String, Object> queryMap) {
        return orderTailByOrgAndTimeDao.getList(queryMap);
    }
}
