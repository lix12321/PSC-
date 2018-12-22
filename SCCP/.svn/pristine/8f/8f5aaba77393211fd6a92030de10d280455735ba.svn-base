package cn.wellcare.dao.ccp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.ccp.ServiceRegister;

@Repository
public interface ServiceRegisterDao {
 
 	/**
 	* 以主键获取
 	*/
	ServiceRegister get(java.lang.Integer id);
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<ServiceRegister> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(ServiceRegister serviceRegister);
	
	/**
 	* 更新
 	*/
	Integer update(ServiceRegister serviceRegister);

	/**
 	* 删除
 	*/
    Integer del(Integer id);

	ServiceRegister getByTranscode(String transCode);
 
}