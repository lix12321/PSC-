package cn.wellcare.api.acc;

import java.util.List;
import java.util.Map;

import cn.wellcare.acc.entity.PscPiAccCredit;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.pojo.common.RpcResult;

public interface IPscPiAccCreditService {

	/**
     * 根据id取得psc_pi_acc_credit对象
     * @param  pscPiAccCreditId
     * @return
     */
    RpcResult<PscPiAccCredit> getPscPiAccCreditById(Integer pscPiAccCreditId);
	/**
     * 根据账户id取得psc_pi_acc_credit对象
     * @param  pscPiAccCreditId
     * @return
     */
    RpcResult<List<PscPiAccCredit>> getPscPiAccCreditByPkPiacc(Integer pscPiAccCreditId);

    /**
     * 保存psc_pi_acc_credit对象
     * @param  pscPiAccCredit
     * @return
     */
     RpcResult<Integer> savePscPiAccCredit(PscPiAccCredit pscPiAccCredit);
     
     /**
     * 更新psc_pi_acc_credit对象
     * @param  pscPiAccCredit
     * @return
     */
     RpcResult<Integer> updatePscPiAccCredit(PscPiAccCredit pscPiAccCredit);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PscPiAccCredit>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}