package cn.wellcare.dao.card;

import cn.wellcare.card.bo.card.CardRegInfoBo;
import cn.wellcare.card.entity.CardReginfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface CardReginfoDao {
 
 	/**
 	* 以主键获取登记信息
 	*/
	CardReginfo get(java.lang.Integer pkReginfo);
 	
 	/**
 	* 获取登记信息条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardReginfo> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存登记信息
 	*/
	Integer save(CardReginfo cardReginfo);
	
	/**
 	* 更新登记信息
 	*/
	Integer update(CardReginfo cardReginfo);

	/**
 	* 删除登记信息
 	*/
    Integer del(Integer id);

	/**
	 * 根据卡号获取发卡登记信息
	 * @param paramMap
	 * @return
	 */
	List<CardRegInfoBo> getCardRegInfo(Map<String, Object> paramMap);

	/**
	 * 通过身份证查询用户登记信息
	 * @param idNo
	 * @return
	 */
	CardReginfo getCardRegInfoByIdNo(String idNo);

	/**
	 * 通过身份证号更新用户登记信息
	 * @param cardReginfo
	 * @return
	 */
	int updateByIdNo(CardReginfo cardReginfo);

	CardReginfo getCardRegInfoByRegCode(String cardRegCode);

    void batchSave(List<CardReginfo> cardReginfos);

    CardReginfo getCardRegInfoByCardNo(String cardNo);
}