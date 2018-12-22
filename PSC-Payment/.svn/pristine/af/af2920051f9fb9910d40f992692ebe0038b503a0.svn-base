package cn.wellcare.model.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.constant.OrderHandler;
import cn.wellcare.core.constant.annotations.OrderLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.dao.log.PayLogDao;
import cn.wellcare.dao.log.PayOrderLogDao;
import cn.wellcare.dao.order.PayOrderDao;
import cn.wellcare.entity.order.PayOrder;

@Component
public class OrderModel {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private PayOrderDao orderDao;
	@Resource
	private PayOrderLogDao orderLogDao;
	@Resource
	private PayLogDao payLogDao;

	public PayOrder getOrderBySn(String ordersn) {
		Map<String, Object> param = new HashMap<>();
		param.put("outerOrderSn", ordersn);
		List<PayOrder> polist = this.orderDao.queryList(param);
		if (polist != null && polist.size() == 1) {
			return polist.get(0);
		}
		return null;
	}

	@OrderLog(OrderHandler.UPDATE)
	public boolean updateOrder(PayOrder order) {
		log.debug("OrderModel.updateOrder 参数：" + order);
		boolean succ = true;
		succ = this.orderDao.updateByOrderSn(order) > 0;
		return succ;
	}

	@OrderLog(OrderHandler.CREATE)
	public void insert(PayOrder order) {
		this.orderDao.save(order);
	}

	/**
	 * 根据支付订单号查询订单信息。第三方回调时查询订单信息用
	 * @param ordersn
	 * @return
	 */
	public PayOrder queryOrderBySn(String ordersn) {
		Map<String, Object> param = new HashMap<>();
		param.put("paySn", ordersn);
		List<PayOrder> polist = this.orderDao.queryList(param);
		if (polist != null && polist.size() > 0) {
			return polist.get(0);
		}
		throw new BusinessException("没有此订单");
	}

}
