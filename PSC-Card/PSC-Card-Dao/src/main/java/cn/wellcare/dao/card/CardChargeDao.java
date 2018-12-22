package cn.wellcare.dao.card;

import cn.wellcare.card.entity.CardCharge;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface CardChargeDao {
 
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardCharge> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(CardCharge cardCharge);
	
	/**
 	* 更新
 	*/
	Integer update(CardCharge cardCharge);

	/**
 	* 删除
 	*/
    Integer del(Integer id);

	/**
	 * 批量保存
	 * @param chargeList
	 */
	void batchSave(List<CardCharge> chargeList);
}