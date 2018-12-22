package cn.wellcare.service.modules.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.model.modules.statistics.OrderCompletedCountModel;
import cn.wellcare.payment.modules.statistics.IOrderCompletedCountService;

@Service("orderCompletedCountService")
public class OrderCompletedCountService implements IOrderCompletedCountService {
    private Logger log = Logger.getLogger(this.getClass());
    @Resource
    private OrderCompletedCountModel orderTailByOrgAndTimeModel;


    @Override
    public Map<String, Object> getList(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>();
        //根据条件查询
        List<Map<String,Object>> findResult = orderTailByOrgAndTimeModel.getList(queryMap);
        //分装结果集
        Map<String,Object> objMap = new HashMap();
        if (null != findResult && !findResult.isEmpty()){
            for (Map<String,Object> map : findResult){
                objMap.put("已支付",map.get("pay"));
                objMap.put("未支付",map.get("nopay"));

            }
        }
        resultMap.put("series",objMap);
        return resultMap;
    }
}
