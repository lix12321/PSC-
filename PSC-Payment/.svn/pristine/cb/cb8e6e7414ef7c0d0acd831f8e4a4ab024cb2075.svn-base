package cn.wellcare.model.modules.payset;

import cn.wellcare.dao.payset.PayTypeDao;
import cn.wellcare.entity.payset.PayType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;

@Component
public class PayTypeModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PayTypeDao payTypeDao;
    
    /**
     * 根据id取得支付方式
     * @param  payTypeId
     * @return
     */
    public PayType getPayTypeById(Integer payTypeId) {
    	return payTypeDao.get(payTypeId);
    }
    
    /**
     * 保存支付方式
     * @param  payType
     * @return
     */
     public Integer savePayType(PayType payType) {
     	this.dbConstrains(payType);
     	return payTypeDao.save(payType);
     }
     
     /**
     * 更新支付方式
     * @param  payType
     * @return
     */
     public Integer updatePayType(PayType payType) {
     	this.dbConstrains(payType);
     	return payTypeDao.update(payType);
     }
     
     private void dbConstrains(PayType payType) {
     }
     
    public List<PayType> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(payTypeDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PayType> list = payTypeDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return payTypeDao.del(id);
     }
     
     
}