package cn.wellcare.model.modules.payset;

import cn.wellcare.bo.PayStrategyBO;
import cn.wellcare.dao.payset.PayStrategyDao;
import cn.wellcare.entity.payset.PayStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;

@Component
public class PayStrategyModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PayStrategyDao payStrategyDao;
    
    /**
     * 根据id取得支付策略
     * @param  payStrategyId
     * @return
     */
    public PayStrategy getPayStrategyById(Integer payStrategyId) {
    	return payStrategyDao.get(payStrategyId);
    }
    
    /**
     * 保存支付策略
     * @param  payStrategy
     * @return
     */
     public Integer savePayStrategy(PayStrategy payStrategy) {
     	this.dbConstrains(payStrategy);
     	return payStrategyDao.save(payStrategy);
     }
     
     /**
     * 更新支付策略
     * @param  payStrategy
     * @return
     */
     public Integer updatePayStrategy(PayStrategy payStrategy) {
     	this.dbConstrains(payStrategy);
     	return payStrategyDao.update(payStrategy);
     }
     
     private void dbConstrains(PayStrategy payStrategy) {
		payStrategy.setPayStrategy(CommonUtils.dbSafeString(payStrategy.getPayStrategy(), true, 12));
     }
     
    public List<PayStrategy> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(payStrategyDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PayStrategy> list = payStrategyDao.getList(param);
        return list;
    }

    public List<PayStrategyBO> findAllList(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(payStrategyDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<PayStrategyBO> list = payStrategyDao.findAllList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return payStrategyDao.del(id);
     }
     
     
}