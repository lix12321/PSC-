package cn.wellcare.service.modules.notify;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.bo.PayNotifyBO;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.model.modules.notify.AdminPayNotifyLogModel;
import cn.wellcare.payment.modules.notify.IPayNotifyLog;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "payNotifyLogService")
public class AdminPayNotifyLogService implements IPayNotifyLog{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private AdminPayNotifyLogModel payNotifyLogModel;

	@Override
	public RpcResult<List<PayNotifyBO>> page(Map<String, Object> queryMap, PagerInfo pager) {
		RpcResult<List<PayNotifyBO>> serviceResult = new RpcResult<List<PayNotifyBO>>();
		try {
			serviceResult.setData(payNotifyLogModel.page(queryMap, pager));
		} catch (Exception e) {
			log.error("[PayNotifyService][page] exception:" + e.getMessage());

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
    
}