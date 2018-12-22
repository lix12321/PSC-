package cn.wellcare.service.transaction.notify.integrationpay;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.wellcare.model.notify.integrationpay.IntegrationPayRefundNotifyModel;
import cn.wellcare.payment.notify.PaymentNotifyRpc;
@Service("integrationPayRefundNotifyService")
public class IntegrationPayRefundNotifyService implements PaymentNotifyRpc {
    private IntegrationPayRefundNotifyModel notifyModel;
    @Override
    public boolean doNotify(Map<String, Object> param) {
        boolean success = false;
        try {
            this.notifyModel.integrationPayNotify(param);
        }
        catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }
}
