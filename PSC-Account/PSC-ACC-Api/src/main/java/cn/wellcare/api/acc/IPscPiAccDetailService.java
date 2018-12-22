package cn.wellcare.api.acc;

import java.util.List;
import java.util.Map;

import cn.wellcare.acc.entity.PscPiAccDetail;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.pojo.common.RpcResult;

public interface IPscPiAccDetailService {

	/**
     * 根据id取得psc_pi_acc_detail对象
     * @param  pscPiAccDetailId
     * @return
     */
    RpcResult<PscPiAccDetail> getPscPiAccDetailById(Integer pscPiAccDetailId);
	
	/**
	 * 根据账户主键取得psc_pi_acc_detail对象
	 * 
	 * @param pkPiacc
	 * @param endTime
	 * @param startTime
	 * @return
	 */
	RpcResult<List<PscPiAccDetail>> getPscPiAccDetailByPkPiacc(Integer pkPiacc, String startTime, String endTime);
	/**
     * 根据账户主键、类型取得psc_pi_acc_detail对象
     * @param  pkPiacc
     * @return
     */
    RpcResult<List<PscPiAccDetail>> queryPscPiAccDetailByeuType(Integer pkPiacc,String euType);

    /**
     * 保存psc_pi_acc_detail对象
     * @param  pscPiAccDetail
     * @return
     */
     RpcResult<Integer> savePscPiAccDetail(PscPiAccDetail pscPiAccDetail);
     
     /**
     * 更新psc_pi_acc_detail对象
     * @param  pscPiAccDetail
     * @return
     */
     RpcResult<Integer> updatePscPiAccDetail(PscPiAccDetail pscPiAccDetail);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PscPiAccDetail>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}