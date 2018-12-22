package cn.wellcare.dao.card;

import cn.wellcare.card.bo.card.EmpolyeeRegInfo;
import cn.wellcare.card.entity.CardEmpolyeeinfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CardEmpolyeeinfoDao {
 
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardEmpolyeeinfo> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(CardEmpolyeeinfo cardEmpolyeeinfo);
	
	/**
 	* 更新
 	*/
	Integer update(CardEmpolyeeinfo cardEmpolyeeinfo);

	/**
 	* 删除
 	*/
    Integer del(Integer id);

	/**
	 * 查询员工信息
	 * @param paramMap
	 * @return
	 */
	List<EmpolyeeRegInfo> getEmpolyeeRegInfo(Map<String, Object> paramMap);

	void batchSave(List<CardEmpolyeeinfo> cardEmpolyeeinfos);
}