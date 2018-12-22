package cn.wellcare.dao.account;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.pojo.account.PscPiAcc;

@Repository
public interface PscPiAccDao {
 
 	/**
 	* 以主键获取
 	*/
	PscPiAcc get(java.lang.Integer pkPiacc);

	/**
	 * 以患者主键获取
	 */
	PscPiAcc getAccount(String pkPi);
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PscPiAcc> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(PscPiAcc pscPiAcc);
	
	/**
 	* 更新
 	*/
	Integer update(PscPiAcc pscPiAcc);

	/**
 	* 删除
 	*/
    Integer del(Integer id);
 
}