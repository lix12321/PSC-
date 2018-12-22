package cn.wellcare.dao.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.order.PayOrder;

@Repository
public interface PayOrderDao {
 
 	/**
 	* 以主键获取支付订单
 	*/
	PayOrder get(java.lang.Integer id);
 	
 	/**
 	* 获取支付订单条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayOrder> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付订单
 	*/
	Integer save(PayOrder payOrder);
	
	/**
 	* 更新支付订单
 	*/
	Integer update(PayOrder payOrder);

	/**
 	* 删除支付订单
 	*/
    Integer del(Integer id);

	List<PayOrder> page(Map<String, Object> queryMap);

	List<PayOrder> queryList(Map<String, Object> map);

	PayOrder queryOrderBySn(String orderId);

	int updateByOrderSn(PayOrder record);

	int cancelUnPaiedOrders(PayOrder payOrder);

	List<PayOrder> getUnPaiedOrders(String cancelTime);

	List<PayOrder> getPayOrderByOrderIdAndStatus(Map<String, List<String>> param);
}