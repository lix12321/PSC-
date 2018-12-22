package cn.wellcare.service.transaction.refund.integrationpay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.refund.PayRefund;
import cn.wellcare.model.modules.refund.PayRefundModel;
import cn.wellcare.payment.api.RefundPayApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.RefundPayResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.pojo.integrationpay.IntegrationPayConfig;
import cn.wellcare.service.transaction.payment.integrationpay.bean.AggregatePay;
import cn.wellcare.service.transaction.payment.integrationpay.bean.AggregatePayOut;
import cn.wellcare.service.transaction.payment.integrationpay.bean.AggregatePayOutVo;
import cn.wellcare.service.transaction.payment.integrationpay.bean.AggregatePayVo;
import cn.wellcare.support.EnumerateParameter;
import cn.wellcare.support.XmlUtil;

@Service
public class IntegrationRefundPayService implements RefundPayApi {
	@Resource
	private IOrderService orderService;
	@Resource
	private PayRefundModel payRefundModel;

    @Override
	public RpcResult<RefundPayResult> refundPay(Map<String, Object> param) throws Exception {
        RpcResult<RefundPayResult> result = new RpcResult<>();
        try {
			// RefundPayResult refundPayResult = refundPayModel.refundPay(param);
			String orderNo = String.valueOf(param.get(BaseParam.OUT_TRADE_NO));
			String refundAmount = String.valueOf(param.get(BaseParam.REFUND_AMOUNT));
			String requestSn = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 请求序列码
			RpcResult<PayOrder> payOrder = orderService.getOrderByOuterSn(orderNo);// 查询出退款的订单信息
			PayOrder order = payOrder.getData();
			if (order.getPaymentStatus().equals(EnumerateParameter.ZERO)) {
				throw new BusinessException("此订单未支付，不能进行退款操作");
			}
			order.setOrderState(Integer.valueOf(EnumerateParameter.TWO));
			orderService.updateOrder(order);
			// 创建退款信息
			PayRefund payRefund = new PayRefund();
			payRefund.setIspartial(Integer.valueOf(EnumerateParameter.ZERO));
			payRefund.setStatus(Integer.valueOf(EnumerateParameter.ONE));
			payRefund.setRefundAmount(new BigDecimal(refundAmount));
			payRefund.setOrderId(order.getId());
			payRefund.setApplyNo(requestSn);
			payRefundModel.savePayRefund(payRefund);

			AggregatePay aggregatePay = new AggregatePay();
			aggregatePay.setMoney(refundAmount);
			aggregatePay.setOrder(orderNo);

			AggregatePayVo aPayVo = new AggregatePayVo();
			String refundCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			aggregatePay.setRefundCode(refundCode);// 退款流水号
			aPayVo.setRequestSn(requestSn);// 请求序列码
			aPayVo.setCustId(IntegrationPayConfig.CUSTID);
			aPayVo.setUserId(IntegrationPayConfig.USER_ID);
			aPayVo.setPassWord(IntegrationPayConfig.PASSWORD);
			aPayVo.setTxCode("5W1004");
			aPayVo.setLanguage("CN");
			aggregatePay.setMoney(aggregatePay.getMoney().replace("-", ""));
			aggregatePay.setOrder(order.getPaySn());
			aPayVo.setAggregatePay(aggregatePay);
			String xml = XmlUtil.beanToXml(aPayVo, aPayVo.getClass());
			Socket socket;

			socket = new Socket("192.168.2.121", 8888);

			OutputStream outputStream = socket.getOutputStream();

			PrintWriter pw = new PrintWriter(outputStream);// 字符输出流
			BufferedWriter bw = new BufferedWriter(pw);// 加上缓冲流

			bw.write(xml);
			bw.flush();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			InputStream is = socket.getInputStream();// 字节输入流
			InputStreamReader isr = new InputStreamReader(is);// 将字节输入流包装成字符输入流
			BufferedReader br = new BufferedReader(isr);// 加上缓冲流，提高效率
			String info = "";
			while (br.ready()) {
				info += br.readLine();
			}
			System.out.println(info);
			socket.shutdownOutput();
			br.close();
			isr.close();
			is.close();
			bw.close();
			pw.close();
			// dStream.close();
			outputStream.close();
			socket.close();
			AggregatePayOutVo aVo = (AggregatePayOutVo) XmlUtil.XmlToBean(info, AggregatePayOutVo.class);
			AggregatePayOut payOut = aVo.getAggregatePayOut();
			if (!aVo.getReturnCode().equals("000000")) {
				throw new BusinessException(
						String.format("聚合支付失败码: %s, 返回信息: %s", aVo.getReturnCode(), aVo.getReturnMsg()));
			}
			// 更新退款表
			payRefund.setStatus(Integer.valueOf(EnumerateParameter.THREE));
			payRefundModel.updatePayRefund(payRefund);
			// 更新订单
			order.setOrderState(Integer.valueOf(EnumerateParameter.THREE));
			order.setUpdateTime(new Date());
			orderService.updateOrder(order); // 更新订单状态
			param.put("orderInfo", order);

			result.setData(new RefundPayResult(orderNo, payOut.getPayAmount(), payOut.getAmount()));
        }catch (Exception e) {
            result.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
                    result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                else
                    result.setMsgInfo(e.getMessage());
                result.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                if (e instanceof UnauthorizedException) {
                    result.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
            throw e;
        }

        return result;
    }
}
