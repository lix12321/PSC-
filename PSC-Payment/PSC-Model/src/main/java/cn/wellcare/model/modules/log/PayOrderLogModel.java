package cn.wellcare.model.modules.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.log.PayOrderLogDao;
import cn.wellcare.entity.log.PayOrderLog;

@Component
public class PayOrderLogModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PayOrderLogDao payOrderLogDao;
    
    /**
     * 根据id取得订单操作日志
     * @param  payOrderLogId
     * @return
     */
    public PayOrderLog getPayOrderLogById(Integer payOrderLogId) {
    	return payOrderLogDao.get(payOrderLogId);
    }
    
    /**
     * 保存订单操作日志
     * @param  payOrderLog
     * @return
     */
     public Integer savePayOrderLog(PayOrderLog payOrderLog) {
     	this.dbConstrains(payOrderLog);
     	return payOrderLogDao.save(payOrderLog);
     }
     
     /**
     * 更新订单操作日志
     * @param  payOrderLog
     * @return
     */
     public Integer updatePayOrderLog(PayOrderLog payOrderLog) {
     	this.dbConstrains(payOrderLog);
     	return payOrderLogDao.update(payOrderLog);
     }
     
     private void dbConstrains(PayOrderLog payOrderLog) {
		payOrderLog.setHandleName(CommonUtils.dbSafeString(payOrderLog.getHandleName(), true, 32));
		payOrderLog.setHandleNum(CommonUtils.dbSafeString(payOrderLog.getHandleNum(), true, 32));
		payOrderLog.setContent(CommonUtils.dbSafeString(payOrderLog.getContent(), true, 255));
		payOrderLog.setOuterOrderSn(CommonUtils.dbSafeString(payOrderLog.getOuterOrderSn(), true, 64));
     }
     
    public List<PayOrderLog> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(payOrderLogDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PayOrderLog> list = payOrderLogDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return payOrderLogDao.del(id);
     }
     
     
}