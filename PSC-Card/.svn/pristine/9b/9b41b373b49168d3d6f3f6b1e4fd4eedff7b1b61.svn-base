package cn.wellcare.dao.card;


import cn.wellcare.card.entity.CardBatchreg;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CardBatchregDao {
 
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardBatchreg> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(CardBatchreg cardBatchreg);
	
	/**
 	* 更新
 	*/
	Integer update(CardBatchreg cardBatchreg);

	/**
 	* 删除
 	*/
    Integer del(Integer id);
 
}