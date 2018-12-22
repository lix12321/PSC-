package cn.wellcare.dao.card;


import cn.wellcare.card.entity.CardBatchregDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CardBatchregDetailDao {
 
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardBatchregDetail> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(CardBatchregDetail cardBatchregDetail);
	
	/**
 	* 更新
 	*/
	Integer update(CardBatchregDetail cardBatchregDetail);

	/**
 	* 删除
 	*/
    Integer del(Integer id);

    void batchSave(List<CardBatchregDetail> cardBatchregDetails);
}