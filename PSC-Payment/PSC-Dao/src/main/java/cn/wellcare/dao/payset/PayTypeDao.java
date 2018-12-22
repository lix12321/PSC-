package cn.wellcare.dao.payset;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.payset.PayType;

@Repository
public interface PayTypeDao {
 
 	/**
 	* 以主键获取支付方式
 	*/
	PayType get(java.lang.Integer id);
 	
 	/**
 	* 获取支付方式条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayType> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付方式
 	*/
	Integer save(PayType payType);
	
	/**
 	* 更新支付方式
 	*/
	Integer update(PayType payType);

	/**
 	* 删除支付方式
 	*/
    Integer del(Integer id);
 
}