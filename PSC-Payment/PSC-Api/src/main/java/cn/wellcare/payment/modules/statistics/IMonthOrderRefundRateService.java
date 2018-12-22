package cn.wellcare.payment.modules.statistics;

import java.util.Map;

public interface IMonthOrderRefundRateService {

    Map<String, Object> getChartData(Map<String, Object> queryMap);
}
