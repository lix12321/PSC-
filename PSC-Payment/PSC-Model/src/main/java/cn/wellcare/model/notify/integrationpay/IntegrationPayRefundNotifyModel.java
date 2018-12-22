package cn.wellcare.model.notify.integrationpay;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.NotifyType;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.annotations.Notify;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.entity.order.PayOrder;

@Component
public class IntegrationPayRefundNotifyModel {
	protected Logger logger = Logger.getLogger(this.getClass());

	@PaymentLog(PayLogHandler.UPDATE)
	@Notify(NotifyType.AFTER_NOTIFY)
	public PayOrder integrationPayNotify(Map<String, Object> param) {
		try {
			this.logger.debug("聚合支付通知开始....");
			PayOrder payOrder = (PayOrder) param.get(Constants.ORDERS_INFO);
			return payOrder;
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
						ErrorEnum.SERVER_EXCEPTION.getErrCode());
			}
		}
	}
}
