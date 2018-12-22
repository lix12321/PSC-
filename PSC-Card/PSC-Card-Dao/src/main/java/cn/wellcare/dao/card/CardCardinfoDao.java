package cn.wellcare.dao.card;

import cn.wellcare.card.entity.CardCardinfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CardCardinfoDao {
 
 	/**
 	* 以主键获取卡记录
 	*/
	CardCardinfo get(java.lang.Integer pkCardinfo);
 	
 	/**
 	* 获取卡记录条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardCardinfo> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存卡记录
 	*/
	Integer save(CardCardinfo cardCardinfo);
	
	/**
 	* 更新卡记录
 	*/
	Integer update(CardCardinfo cardCardinfo);

	/**
 	* 删除
 	*/
    Integer del(Integer id);

	/**
	 * 通过卡号查询卡信息
	 * @param cardNo
	 * @return
	 */
	CardCardinfo getCardCardinfByCardNo(String cardNo);

	/**
	 * 通过登记信息主键查询卡信息
	 * @param pkReginfo
	 * @return
	 */
	CardCardinfo getCardCardinfByPkCardinfo(String pkReginfo);

	void batchSave(List<CardCardinfo> cardCardinfos);
}