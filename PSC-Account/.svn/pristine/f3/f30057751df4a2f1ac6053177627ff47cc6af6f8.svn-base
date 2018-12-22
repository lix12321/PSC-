package cn.wellcare.dao.acc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.acc.entity.PscPiAccDetail;

@Repository
public interface PscPiAccDetailDao {
 
 	/**
 	* 以主键获取
 	*/
	PscPiAccDetail get(java.lang.Integer pkAccdt);
 	/**
 	* 以账户主键获取
 	*/
	List<PscPiAccDetail> getPiaccDetail(Map<String, Object> queryMap);

 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PscPiAccDetail> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(PscPiAccDetail pscPiAccDetail);
	
	/**
 	* 更新
 	*/
	Integer update(PscPiAccDetail pscPiAccDetail);

	/**
 	* 删除
 	*/
    Integer del(Integer id);
 
}