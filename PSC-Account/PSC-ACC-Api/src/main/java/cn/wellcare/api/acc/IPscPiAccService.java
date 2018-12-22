package cn.wellcare.api.acc;

import java.util.List;
import java.util.Map;

import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.pojo.common.RpcResult;

public interface IPscPiAccService {

	/**
     * 根据id取得psc_pi_acc对象
     * @param  pscPiAccId
     * @return
     */
    RpcResult<PscPiAcc> getPscPiAccById(Integer pscPiAccId);

    /**
     * 根据用户id获取账户信息
     * @param pkPi
     * @return
     */
    RpcResult<PscPiAcc> getPscPiAccBypkPi(String pkPi);
    /**
     * 根据用户id获取账户信息
     * @param pkPi
     * @return
     */
    RpcResult<PscPiAcc> getPscPiAccByCodeAcc(String codeAcc);

    /**
     * 根据用户主索引获取账户状态
     * @param pkPi
     * @return
     */
    RpcResult<Boolean> getAccountStatus(String pkPi);

    /**
     * 根据账户主键获取账户状态
     * @param id
     * @return
     */
    RpcResult<Boolean> queryAccountStatus(Integer id);
    
    /**
     * 保存psc_pi_acc对象
     * @param  pscPiAcc
     * @return
     */
     RpcResult<PscPiAcc> savePscPiAcc(PscPiAcc pscPiAcc);

    /**
     *批量插入账户信息
     * @param
     * @return
     */
    RpcResult<Integer> batchInsertPscPiAcc(Map<String,Object> map);
     
     /**
     * 更新psc_pi_acc对象
     * @param  pscPiAcc
     * @return
     */
     RpcResult<PscPiAcc> updatePscPiAcc(PscPiAcc pscPiAcc);

    /**
     *批量更新账户信息
     * @param
     * @return
     */
    RpcResult<Integer> batchUpdatePscPiAcc(Map<String,Object> map);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PscPiAcc>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);

    /**
     * 验证账户密码
     * @param param
     * @return
     */
    RpcResult<String> passwordCheck(Map<String,Object> param);
}