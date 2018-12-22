package cn.wellcare.model.modules.order;

import cn.wellcare.dao.order.PayPosOrderDao;
import cn.wellcare.entity.order.PayPosOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;

@Component
public class PayPosOrderModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PayPosOrderDao payPosOrderDao;
    
    /**
     * 根据id取得MisPOS订单
     * @param  payPosOrderId
     * @return
     */
    public PayPosOrder getPayPosOrderById(Integer payPosOrderId) {
    	return payPosOrderDao.get(payPosOrderId);
    }
    
    /**
     * 保存MisPOS订单
     * @param  payPosOrder
     * @return
     */
     public Integer savePayPosOrder(PayPosOrder payPosOrder) {
     	this.dbConstrains(payPosOrder);
     	return payPosOrderDao.save(payPosOrder);
     }
     
     /**
     * 更新MisPOS订单
     * @param  payPosOrder
     * @return
     */
     public Integer updatePayPosOrder(PayPosOrder payPosOrder) {
     	this.dbConstrains(payPosOrder);
     	return payPosOrderDao.update(payPosOrder);
     }
     
     private void dbConstrains(PayPosOrder payPosOrder) {
		payPosOrder.setAuth(CommonUtils.dbSafeString(payPosOrder.getAuth(), true, 32));
		payPosOrder.setBankCode(CommonUtils.dbSafeString(payPosOrder.getBankCode(), true, 32));
		payPosOrder.setBatch(CommonUtils.dbSafeString(payPosOrder.getBatch(), true, 32));
		payPosOrder.setCardNo(CommonUtils.dbSafeString(payPosOrder.getCardNo(), true, 32));
		payPosOrder.setDtPayMode(CommonUtils.dbSafeString(payPosOrder.getDtPayMode(), true, 16));
		payPosOrder.setExpr(CommonUtils.dbSafeString(payPosOrder.getExpr(), true, 32));
		payPosOrder.setOldTerno(CommonUtils.dbSafeString(payPosOrder.getOldTerno(), true, 32));
		payPosOrder.setRefer(CommonUtils.dbSafeString(payPosOrder.getRefer(), true, 32));
		payPosOrder.setRespChin(CommonUtils.dbSafeString(payPosOrder.getRespChin(), true, 32));
		payPosOrder.setRespCode(CommonUtils.dbSafeString(payPosOrder.getRespCode(), true, 32));
		payPosOrder.setSzOrderTrace(CommonUtils.dbSafeString(payPosOrder.getSzOrderTrace(), true, 32));
		payPosOrder.setTerno(CommonUtils.dbSafeString(payPosOrder.getTerno(), true, 32));
		payPosOrder.setTrace(CommonUtils.dbSafeString(payPosOrder.getTrace(), true, 32));
		payPosOrder.setUserno(CommonUtils.dbSafeString(payPosOrder.getUserno(), true, 32));
     }
     
    public List<PayPosOrder> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(payPosOrderDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PayPosOrder> list = payPosOrderDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return payPosOrderDao.del(id);
     }
     
     
}