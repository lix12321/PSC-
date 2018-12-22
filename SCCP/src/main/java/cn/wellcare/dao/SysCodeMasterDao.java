package cn.wellcare.dao;

import cn.wellcare.entity.SysCodeMaster;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysCodeMasterDao {
 
 	/**
 	* 以主键获取数据字典
 	*/
	SysCodeMaster get(String codeDiv, String codeCd);

 	/**
 	* 获取数据字典条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<SysCodeMaster> getList(Map<String, Object> queryMap);

 	/**
 	* 保存数据字典
 	*/
	Integer save(SysCodeMaster sysCodeMaster);

	/**
 	* 更新数据字典
 	*/
	Integer update(SysCodeMaster sysCodeMaster);

	/**
 	* 删除数据字典
 	*/
    Integer del(String codeDiv, String codeCd);
 
}