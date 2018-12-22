package cn.wellcare.payment.modules.statistics;

import java.util.Map;

public interface ITodayOrderPayStateCountService {
    Map<String, Object> getChartData(Map<String, Object> queryMap);
}
