package cn.wellcare.dao.statistics;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TodayOrderPayStateCountDao {

    List<Map<String, Object>> getChartData(Map<String, Object> paramMap);
}
