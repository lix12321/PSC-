package cn.wellcare.service.acc;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;

import cn.wellcare.acc.entity.PscPiAccDetail;
import cn.wellcare.api.acc.IPscPiAccDetailService;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.model.acc.PscPiAccDetailModel;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "pscPiAccDetailService")
public class PscPiAccDetailService implements IPscPiAccDetailService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PscPiAccDetailModel pscPiAccDetailModel;
    
     /**
     * 根据id取得psc_pi_acc_detail对象
     * @param  pscPiAccDetailId
     * @return
     */
    @Override
    public RpcResult<PscPiAccDetail> getPscPiAccDetailById(Integer pscPiAccDetailId) {
        RpcResult<PscPiAccDetail> serviceResult = new RpcResult<PscPiAccDetail>();
        try {
            serviceResult.setData(pscPiAccDetailModel.getPscPiAccDetailById(pscPiAccDetailId));
        } catch (Exception e) {
            log.error("[IPscPiAccDetailService][getPscPiAccDetailById]根据id["+pscPiAccDetailId+"]取得psc_pi_acc_detail对象时出现未知异常：",
                e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
    }

	/**
	 * 根据账户主键获取账户流水信息
	 * @param  pkPiacc
	 * @return
	 */
	@Override
	public RpcResult<List<PscPiAccDetail>> getPscPiAccDetailByPkPiacc(Integer pkPiacc, String startTime,
			String endTime) {
		RpcResult<List<PscPiAccDetail>> serviceResult = new RpcResult<List<PscPiAccDetail>>();
		try {
			serviceResult.setData(pscPiAccDetailModel.getPscPiAccDetailByPkPiacc(pkPiacc, startTime, endTime));
		} catch (Exception e) {
			log.error("[IPscPiAccDetailService][getPscPiAccDetailById]根据id["+pkPiacc+"]取得psc_pi_acc_detail对象时出现未知异常：",
					e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}
	/**
	 * 根据账户主键、类型取得psc_pi_acc_detail对象
	 * @param  pkPiacc
	 * @return
	 */
	@Override
	public RpcResult<List<PscPiAccDetail>> queryPscPiAccDetailByeuType(Integer pkPiacc, String euType) {
		RpcResult<List<PscPiAccDetail>> serviceResult = new RpcResult<List<PscPiAccDetail>>();
		try {
			List<PscPiAccDetail> piAccDetailList = pscPiAccDetailModel.getPscPiAccDetailByPkPiacc(pkPiacc, null, null);
			if (StringUtils.isNotEmpty(euType)) {
				Iterator<PscPiAccDetail> it = piAccDetailList.iterator();
				while (it.hasNext()) {
					PscPiAccDetail piAccDetail = it.next();
					if (!piAccDetail.getEuOptype().equals(euType)) {
						it.remove();
					}
				}
			}

			serviceResult.setData(piAccDetailList);
		} catch (Exception e) {
			log.error("[IPscPiAccDetailService][getPscPiAccDetailById]根据id["+pkPiacc+"]取得psc_pi_acc_detail对象时出现未知异常：",
					e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	/**
     * 保存psc_pi_acc_detail对象（新增账户流水记录）
     * @param  pscPiAccDetail
     * @return
     */
     @Override
     public RpcResult<Integer> savePscPiAccDetail(PscPiAccDetail pscPiAccDetail) {
     	     RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(pscPiAccDetailModel.savePscPiAccDetail(pscPiAccDetail));
        } catch (Exception e) {
            log.error("[IPscPiAccDetailService][savePscPiAccDetail]保存psc_pi_acc_detail对象时出现未知异常：",
                e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
     }
     
     /**
     * 更新psc_pi_acc_detail对象
     * @param  pscPiAccDetail
     * @return
     * @see
     */
     @Override
     public RpcResult<Integer> updatePscPiAccDetail(PscPiAccDetail pscPiAccDetail) {
			 RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(pscPiAccDetailModel.updatePscPiAccDetail(pscPiAccDetail));
        } catch (Exception e) {
            log.error("[IPscPiAccDetailService][updatePscPiAccDetail]更新psc_pi_acc_detail对象时出现未知异常：",
                e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
     }
     
     
    @Override
    public RpcResult<List<PscPiAccDetail>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PscPiAccDetail>> serviceResult = new RpcResult<List<PscPiAccDetail>>();
        try {
              serviceResult.setData(pscPiAccDetailModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PscPiAccDetailService][page] exception:" + e.getMessage());
            
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			
        }
        return serviceResult;
    }

    @Override
    public RpcResult<Boolean> del(Integer id) {
 		RpcResult<Boolean> serviceResult = new RpcResult<Boolean>();
        try {
            serviceResult.setData(pscPiAccDetailModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PscPiAccDetailService][del] exception:" + e.getMessage());
            
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
    }
}