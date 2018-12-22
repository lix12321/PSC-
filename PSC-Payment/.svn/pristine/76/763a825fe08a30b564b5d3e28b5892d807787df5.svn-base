package cn.wellcare.model.modules.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.log.PayLogDao;
import cn.wellcare.entity.log.PayLog;

@Component
public class PayLogModel {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private PayLogDao payLogDao;

	/**
	 * 根据id取得交易记录
	 * 
	 * @param payLogId
	 * @return
	 */
	public PayLog getPayLogById(Integer payLogId) {
		return payLogDao.get(payLogId);
	}

	/**
	 * 以订单id和操作类型获取唯一交易记录
	 * 
	 * @param orderId
	 * @param optype
	 * @return
	 */
	public PayLog getPayLogByOrderIdAndOpType(Integer orderId, int optype) {
		return payLogDao.getPayLogByOrderIdAndOpType(orderId, optype);
	}

	/**
	 * 保存交易记录
	 * 
	 * @param payLog
	 * @return
	 */
	public Integer savePayLog(PayLog payLog) {
		this.dbConstrains(payLog);
		return payLogDao.save(payLog);
	}

	/**
	 * 更新交易记录
	 * 
	 * @param payLog
	 * @return
	 */
	public Integer updatePayLog(PayLog payLog) {
		this.dbConstrains(payLog);
		return payLogDao.update(payLog);
	}

	private void dbConstrains(PayLog payLog) {
		payLog.setHandleName(CommonUtils.dbSafeString(payLog.getHandleName(), true, 32));
		payLog.setHandleNum(CommonUtils.dbSafeString(payLog.getHandleNum(), true, 32));
		payLog.setOuterOrderSn(CommonUtils.dbSafeString(payLog.getOuterOrderSn(), true, 64));
		payLog.setPaymentCode(CommonUtils.dbSafeString(payLog.getPaymentCode(), true, 16));
		payLog.setPaymentName(CommonUtils.dbSafeString(payLog.getPaymentName(), true, 16));
		payLog.setPaySn(CommonUtils.dbSafeString(payLog.getPaySn(), true, 64));
		payLog.setTradeSn(CommonUtils.dbSafeString(payLog.getTradeSn(), true, 64));
	}

	public List<PayLog> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(queryMap);
		Integer start = 0, size = 0;
		if (pager != null) {
			pager.setRowsCount(payLogDao.getCount(param));
			start = pager.getStart();
			size = pager.getPageSize();
		}
		param.put("start", start);
		param.put("size", size);
		List<PayLog> list = payLogDao.getList(param);
		return list;
	}

	public Integer del(Integer id) {
		return payLogDao.del(id);
	}

}