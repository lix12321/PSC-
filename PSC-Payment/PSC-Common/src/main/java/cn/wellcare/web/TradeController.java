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
import cn.wellcare.payment.api.PaymentOrderApi;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 交易类基础Controller
 */
public class TradeController extends BaseController{
    protected Logger log = Logger.getLogger(this.getClass());
    protected RpcResult<OrderResult> orderResult = null;

    @ModelAttribute
    public synchronized void order(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param) {
        this.orderResult = new RpcResult<>();
        try {
            Assert.notNull(param.get(BaseParam.ORDER_ID));
            Assert.notNull(param.get(BaseParam.USER_ID));
            Assert.notNull(param.get(BaseParam.ORG_ID));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            this.orderResult.setError(ErrorEnum.PARAM_IS_INVALID);
        } catch (BusinessException e) {
            this.orderResult.setError(e);
        } catch (Exception e) {
            e.printStackTrace();
            this.orderResult.setError(ErrorEnum.SERVER_EXCEPTION);
        }
        orderOperation(param);
    }

    public void orderOperation(Map<String, Object> param) {
        OrderResult or ;
        try {
			PaymentOrderApi po = null;
			po = (PaymentOrderApi) ServiceLocator.getInstance().getBean("tradeOrderService"); // 获取服务的bean
            this.log.debug("确定使用实例：" + po);
            this.orderResult= po.getOrderBySn(param);
        } catch (Exception e) {
            this.orderResult.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.SERVER_EXCEPTION.getErrCode().equals(pe.getCode())){
                    this.orderResult.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());}
                else{
                    this.orderResult.setMsgInfo(e.getMessage());}
            } else {
                if (e instanceof UnauthorizedException) {
                    this.orderResult.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    this.orderResult.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
        }
    }
}
