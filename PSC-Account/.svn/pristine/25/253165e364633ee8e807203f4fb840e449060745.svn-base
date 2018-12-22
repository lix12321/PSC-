package cn.wellcare.dao.acc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.wellcare.acc.entity.PscPiAcc;

@Repository
public interface PscPiAccDao {
 
 	/**
 	* 以主键获取
 	*/
	PscPiAcc get(java.lang.Integer pkPiacc);

	/**
	 * 以患者主键获取账户信息
	 */
	PscPiAcc getAccount(String pkPi);
	/**
	 * 以患者账户编码获取账户信息
	 */
	PscPiAcc getAccountBycode(String codeAcc);

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
	 * 批量插入数据
	 *
	 * @param pscPiAccsList
	 * @return
	 */
	Integer batchInsertPscPiAcc(@Param("pscPiAccsList")List<PscPiAcc> pscPiAccsList);
	
	/**
 	* 更新
 	*/
	Integer update(PscPiAcc pscPiAcc);

	/**
	 * 批量更新数据
	 *
	 * @param pscPiAccsList
	 * @return
	 */
	Integer batchUpdatePscPiAcc(@Param("pscPiAccsList")List<PscPiAcc> pscPiAccsList);

	/**
 	* 删除
 	*/
    Integer del(Integer id);
 
}