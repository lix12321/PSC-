package cn.wellcare.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;

public class AccountNotifyBaseController extends BaseController{
    protected Logger log = Logger.getLogger(this.getClass());
    protected RpcResult<PaymentResult> result = null;
    protected Boolean booleanResult = null;

    @ModelAttribute
    public synchronized void common(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam Map<String, Object> params) {
        try {
            this.result = new RpcResult<>();
            params.put(BaseParam.CLIENT_IP, CommonUtils.getRemoteIpAddr(request));
            Assert.notNull(params.get(BaseParam.PAY_TYPE));
            Assert.notNull(params.get(BaseParam.PAY_AMOUNT));
            setParams(params);
            invokeService(params);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            this.result.setError(ErrorEnum.PARAM_IS_INVALID);
        }

    }

    protected void setParams(Map<String, Object> params) {
    }

    protected void invokeService(Map<String, Object> params) {
        try {
            PaymentApi ph = null;
            String payType = (String) params.get(BaseParam.PAY_TYPE);
            Assert.notNull(payType);
                //账户支付
            ph = (PaymentApi) ServiceLocator.getInstance().getBean("accountPayService");
            // TODO others
           // PaymentNotifyRpc nr = (PaymentNotifyRpc) ServiceLocator.getInstance().getBean("accountNativeNotifyService");
            this.log.debug("确定使用实例：" + ph);
            this.result = ph.doPay(params);//调用支付
          //  this.booleanResult = nr.doNotify(params);//调用通知
        } catch (Exception e) {
            this.result.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
                    this.result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                else
                    this.result.setMsgInfo(e.getMessage());
                this.result.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                if (e instanceof UnauthorizedException) {
                    this.result.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    this.result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
        }
    }

}
