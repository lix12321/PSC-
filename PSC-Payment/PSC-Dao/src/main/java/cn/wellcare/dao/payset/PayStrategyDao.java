package cn.wellcare.dao.payset;

import cn.wellcare.bo.PayStrategyBO;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.payset.PayStrategy;

@Repository
public interface PayStrategyDao {
 
 	/**
 	* 以主键获取支付策略
 	*/
	PayStrategy get(java.lang.Integer id);
 	
 	/**
 	* 获取支付策略条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayStrategy> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付策略
 	*/
	Integer save(PayStrategy payStrategy);
	
	/**
 	* 更新支付策略
 	*/
	Integer update(PayStrategy payStrategy);

	/**
 	* 删除支付策略
 	*/
    Integer del(Integer id);

	List<PayStrategyBO> findAllList (Map<String, Object> queryMap);
 
}