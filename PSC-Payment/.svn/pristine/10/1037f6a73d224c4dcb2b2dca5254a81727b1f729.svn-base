package cn.wellcare.payment.modules.system;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.pojo.common.RpcResult;

public interface ISysCodeMasterService {

	/**
     * 根据id取得数据字典
     * @param  sysCodeMasterId
     * @return
     */
    RpcResult<SysCodeMaster> getSysCodeMasterById(String codeDiv,String codeCd);
    
    /**
     * 保存数据字典
     * @param  sysCodeMaster
     * @return
     */
     RpcResult<Integer> saveSysCodeMaster(SysCodeMaster sysCodeMaster);
     
     /**
     * 更新数据字典
     * @param  sysCodeMaster
     * @return
     */
     RpcResult<Integer> updateSysCodeMaster(SysCodeMaster sysCodeMaster);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<SysCodeMaster>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param codeDiv
     * @param codeCd
     * @return
     */
    RpcResult<Boolean> del(String codeDiv,String codeCd);
}