package cn.wellcare.dao.order;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.order.PayPosOrder;

@Repository
public interface PayPosOrderDao {
 
 	/**
 	* 以主键获取MisPOS订单
 	*/
	PayPosOrder get(java.lang.Integer id);
 	
 	/**
 	* 获取MisPOS订单条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayPosOrder> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存MisPOS订单
 	*/
	Integer save(PayPosOrder payPosOrder);
	
	/**
 	* 更新MisPOS订单
 	*/
	Integer update(PayPosOrder payPosOrder);

	/**
 	* 删除MisPOS订单
 	*/
    Integer del(Integer id);
 
}