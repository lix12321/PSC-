package cn.wellcare.dao.refund;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.refund.PayRefund;

@Repository
public interface PayRefundDao {
 
 	/**
 	* 以主键获取退款
 	*/
	PayRefund get(java.lang.Integer id);
 	/**
 	* 以订单主键获取退款
 	*/
	PayRefund queryPayRefund(java.lang.Integer orderId);

 	/**
 	* 获取退款条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayRefund> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存退款
 	*/
	Integer save(PayRefund payRefund);
	
	/**
 	* 更新退款
 	*/
	Integer update(PayRefund payRefund);

	/**
 	* 删除退款
 	*/
    Integer del(Integer id);
 
}