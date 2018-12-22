package cn.wellcare.model.modules.order;

import cn.wellcare.core.constant.OrderHandler;
import cn.wellcare.core.constant.annotations.OrderLog;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.order.PayOrderDao;
import cn.wellcare.dao.system.SysCodeMasterDao;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.system.SysCodeMaster;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class PayOrderModel {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private PayOrderDao payOrderDao;

	@Resource
	private SysCodeMasterDao sysCodeMasterDao;

	/**
	 * 根据id取得支付订单
	 * 
	 * @param payOrderId
	 * @return
	 */
	public PayOrder getPayOrderById(Integer payOrderId) {
		return payOrderDao.get(payOrderId);
	}

	/**
	 * 保存支付订单
	 * 
	 * @param payOrder
	 * @return
	 */
	public Integer savePayOrder(PayOrder payOrder) {
		this.dbConstrains(payOrder);
		return payOrderDao.save(payOrder);
	}

	/**
	 * 更新支付订单
	 * 
	 * @param payOrder
	 * @return
	 */
	public Integer updatePayOrder(PayOrder payOrder) {
		this.dbConstrains(payOrder);
		return payOrderDao.update(payOrder);
	}

	private void dbConstrains(PayOrder payOrder) {
		payOrder.setHandleNum(CommonUtils.dbSafeString(payOrder.getHandleNum(), true, 32));
		payOrder.setHandleName(CommonUtils.dbSafeString(payOrder.getHandleName(), true, 32));
		payOrder.setOrderType(CommonUtils.dbSafeString(payOrder.getOrderType(), true, 4));
		payOrder.setOuterOrderSn(CommonUtils.dbSafeString(payOrder.getOuterOrderSn(), true, 64));
		payOrder.setPaymentCode(CommonUtils.dbSafeString(payOrder.getPaymentCode(), true, 16));
		payOrder.setPaymentName(CommonUtils.dbSafeString(payOrder.getPaymentName(), true, 16));
		payOrder.setPaySn(CommonUtils.dbSafeString(payOrder.getPaySn(), true, 64));
		payOrder.setRelationOrderSn(CommonUtils.dbSafeString(payOrder.getRelationOrderSn(), true, 64));
		payOrder.setRemark(CommonUtils.dbSafeString(payOrder.getRemark(), true, 255));
		payOrder.setReqIp(CommonUtils.dbSafeString(payOrder.getReqIp(), true, 32));
		payOrder.setTradeSn(CommonUtils.dbSafeString(payOrder.getTradeSn(), true, 64));
	}

	public List<PayOrder> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(queryMap);
		Integer start = 0, size = 0;
		if (pager != null) {
			pager.setRowsCount(payOrderDao.getCount(param));
			start = pager.getStart();
			size = pager.getPageSize();
		}
		param.put("start", start);
		param.put("size", size);
		List<PayOrder> list = payOrderDao.getList(param);
		return list;
	}

	public Integer del(Integer id) {
		return payOrderDao.del(id);
	}

	@OrderLog(OrderHandler.AUTO_CANCEL)
	public int cancelUnPaiedOrders(PayOrder payOrder) {
		return payOrderDao.cancelUnPaiedOrders(payOrder);
	}

	public List<PayOrder> getUnPaiedOrders() {
		// 获取当前时间1天之前的时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String cancelTime = CommonUtils.getDateTimeString(calendar.getTime());

		// 取消24小时还未付款的订单
		return payOrderDao.getUnPaiedOrders(cancelTime);
	}

	/**
	 * 获取有效时间的订单
	 * @param param
	 * @return
	 */
	public List<PayOrder> getPayOrderByOrderIdAndStatus(Map<String, List<String>> param) {
		List<PayOrder> payOrders = payOrderDao.getPayOrderByOrderIdAndStatus(param);
		if (payOrders == null || payOrders.isEmpty()){
			return  null;
		}
		//查询数据字典
		Map<String,Object> codeMap = new HashMap<>();
		codeMap.put("useYn", SysCodeMaster.STATUS_USE);
		codeMap.put("q_codeDiv","ORDER_TIMEOUT");
        List<SysCodeMaster> sysCodeMasters = sysCodeMasterDao.getList(codeMap);
        Map<String,String> map = new HashMap<>();
        if (sysCodeMasters != null && !sysCodeMasters.isEmpty()){
        	for (SysCodeMaster master : sysCodeMasters){
				if ("ORDER_TIMEOUT_ALI".equals(master.getCodeDiv())){
					map.put("003",master.getCodeCd());
					map.put("004",master.getCodeCd());
				}else if ("ORDER_TIMEOUT_WECHAT".equals(master.getCodeDiv())){
					map.put("001",master.getCodeCd());
					map.put("002",master.getCodeCd());
					map.put("006",master.getCodeCd());
				}else {
					map.put("009",master.getCodeCd());
				}
			}

		}
        //处理已过期的订单信息
		Iterator<PayOrder> iterator = payOrders.iterator();
		while (iterator.hasNext()){
			PayOrder payOrder = iterator.next();
			int days = disTodayDays(payOrder.getPayTime());
			int outTmedays = Integer.valueOf(map.get(payOrder.getOrderType())==null ?"0":map.get(payOrder.getOrderType()));
			if (days > outTmedays){
				iterator.remove();
			}
		}
		return payOrders;
	}

	/**
	 *  距离今天的天数
	 * @param date
	 * @return
	 */
	private int disTodayDays(Date date){
		int days = 0;
		if (date != null){
			days = (int) (( new Date().getTime() - date.getTime()) / (1000*3600*24));
		}
		return days;
	}


}