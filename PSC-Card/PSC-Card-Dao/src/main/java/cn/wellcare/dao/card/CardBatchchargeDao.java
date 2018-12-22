package cn.wellcare.dao.card;
import cn.wellcare.card.entity.CardBatchcharge;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CardBatchchargeDao {
 
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardBatchcharge> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(CardBatchcharge cardBatchcharge);
	
	/**
 	* 更新
 	*/
	Integer update(CardBatchcharge cardBatchcharge);

	/**
 	* 删除
 	*/
    Integer del(Integer id);
 
}