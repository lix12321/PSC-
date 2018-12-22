package cn.wellcare.dao.card;

import cn.wellcare.card.entity.CardOperate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface CardOperateDao {
 
 	/**
 	* 以主键获取
 	*/
	CardOperate get(java.lang.String pkOperateDetail);
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<CardOperate> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(CardOperate cardOperate);
	
	/**
 	* 更新
 	*/
	Integer update(CardOperate cardOperate);

	/**
 	* 删除
 	*/
    Integer del(Integer id);
 
}