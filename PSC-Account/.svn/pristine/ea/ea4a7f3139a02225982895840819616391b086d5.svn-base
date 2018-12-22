package cn.wellcare.dao.acc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.acc.entity.PscPiAccCredit;

@Repository
public interface PscPiAccCreditDao {
 
 	/**
 	* 以主键获取
 	*/
	PscPiAccCredit get(java.lang.Integer pkAcccredit);
 	/**
 	* 以账户主键获取
 	*/
	List<PscPiAccCredit> getCredit(Integer pkPiacc);

 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PscPiAccCredit> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(PscPiAccCredit pscPiAccCredit);
	
	/**
 	* 更新
 	*/
	Integer update(PscPiAccCredit pscPiAccCredit);

	/**
 	* 删除
 	*/
    Integer del(Integer id);


}