package cn.wellcare.service.transaction.payment.wechat;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.pojo.wechat.WechatJsApiParam;
import cn.wellcare.pojo.wechat.WechatPaymentResult;
import cn.wellcare.service.transaction.payment.wechat.base.WechatPayment;
import cn.wellcare.service.transaction.payment.wechat.util.CommonTools;
import cn.wellcare.service.transaction.payment.wechat.util.RequestHandler;
import cn.wellcare.support.HttpUtil;
import net.sf.json.JSONObject;

/**
 * 微信js API支付
 * 
 * @author zhaihl
 *
 */
@Service("wechatJsAPI")
public class WechatJsAPIService extends WechatPayment implements PaymentApi {
	protected Logger log = Logger.getLogger(this.getClass());

	@Override
	protected String getWechatOrderType() {
		return PaymentType.WECHAT_JSAPI.getPaymentCode();
	}

	@Override
	protected String getWechatPaymentName() {
		return PaymentType.WECHAT_JSAPI.getPaymentName();
	}

	@Override
	@PaymentLog(PayLogHandler.CREATE)
	public RpcResult<PaymentResult> doPay(Map<String, Object> param) {
		RpcResult<PaymentResult> sr = new RpcResult<>();
		try {
			PaymentResult result = null;

			initConfig(Integer.valueOf((String) param.get(BaseParam.ORG_ID)));
			// PaymentResult result = this.wechatJsAPIModel.doPay(param);
			if (!CommonUtils.isNull(param.get(BaseParam.REDIRECT))
					&& Boolean.TRUE == Boolean.valueOf((String) param.get(BaseParam.REDIRECT))) {
				result = doRedirect(param);
			} else {
				result = doWechatJsApi(param);
			}

			sr.setData(result);
		} catch (Exception e) {
			sr.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					sr.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					sr.setMsgInfo(e.getMessage());
				sr.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				if (e instanceof UnauthorizedException) {
					sr.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else {
					e.printStackTrace();
					sr.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
			throw e;
		}
		return sr;
	}

	private PaymentResult doWechatJsApi(Map<String, Object> param) {
		this.log.debug("支付参数:" + param);
		try {
			if (!((String) param.get(BaseParam.PAY_AMOUNT)).matches("[0-9]+")) {
				throw new BusinessException("订单金额不允许出现小数或非数字");
			}

			// 总金额以分为单位，不带小数点
			int total_fee = Integer.parseInt((String) param.get(BaseParam.PAY_AMOUNT));

			String openId = "";
			String URL = super.oauth2Token;
			URL = URL.replace("APPID", this.appid).replace("SECRET", this.appsecret).replace("CODE",
					(String) param.get("code"));

			JSONObject jsonObject = HttpUtil.httpsRequest(URL, "GET", null);
			if (null != jsonObject) {
				this.log.debug("jsonObject=========" + jsonObject.toString());
				openId = jsonObject.getString("openid");
			}

			// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			String currTime = CommonTools.getCurrTime();
			// 8位日期
			String strTime = currTime.substring(8, currTime.length());
			// 四位随机数
			String strRandom = CommonTools.buildRandom(4) + "";
			// 10位序列号,可以自行调整。
			String strReq = strTime + strRandom;

			// 商户号
			String mch_id = mchid;
			// 子商户号 非必输
			// String sub_mch_id="";
			// 随机数
			String nonce_str = strReq;
			// 商品描述
			// String body = describe;

			// 商品描述根据情况修改
			String body = (String) param.get("describe");
			// 附加数据
			String attach = (String) param.get("orderPaySn");
			// 商户订单号
			String out_trade_no = (String) param.get("orderPaySn");

			// 订单生成的机器 IP
			String spbill_create_ip = (String) param.get(BaseParam.CLIENT_IP);

			// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url = DomainUrlUtil.PSC_PAYMENT_URL + "/wxNotify.html";

			String trade_type = "JSAPI";
			String openid = openId;
			// 非必输
			// String product_id = "";
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", this.appid);
			packageParams.put("mch_id", mch_id);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("attach", attach);
			packageParams.put("out_trade_no", out_trade_no);

			// 这里写的金额为1 分到时修改
			packageParams.put("total_fee", total_fee + "");
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);

			packageParams.put("trade_type", trade_type);
			packageParams.put("openid", openid);

			RequestHandler reqHandler = new RequestHandler();
			reqHandler.init(appid, appsecret, apikey);

			String sign = reqHandler.createSign(packageParams);
			String xml = "<xml>" + "<appid>" + this.appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>"
					+ "<nonce_str>" + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA["
					+ body + "]]></body>" + "<attach>" + attach + "</attach>" + "<out_trade_no>" + out_trade_no
					+ "</out_trade_no>" + "<total_fee>" + total_fee + "</total_fee>" + "<spbill_create_ip>"
					+ spbill_create_ip + "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>"
					+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>" + openid + "</openid>" + "</xml>";
			String prepay_id = "";
			prepay_id = HttpUtil.getPayNo(super.createOrderURL, xml);

			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = CommonTools.getTimeStamp();
			String nonceStr2 = nonce_str;
			String prepay_id2 = "prepay_id=" + prepay_id;
			String packages = prepay_id2;
			finalpackage.put("appId", this.appid);
			finalpackage.put("timeStamp", timestamp);
			finalpackage.put("nonceStr", nonceStr2);
			finalpackage.put("package", packages);
			finalpackage.put("signType", "MD5");
			String finalsign = reqHandler.createSign(finalpackage);

			return new WechatJsApiParam(this.appid, timestamp, nonceStr2, packages, finalsign);
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

	private WechatPaymentResult doRedirect(Map<String, Object> param) {
		try {
			String totalFee = (String) param.get(BaseParam.PAY_AMOUNT);
			// 支付的总金额（分）
			BigDecimal needsPay = new BigDecimal(totalFee);
			needsPay = needsPay.multiply(new BigDecimal(100));
			String txnAmt = needsPay.toString().split("\\.")[0]; // 付款金额，单位为分，不能有小数点，去掉

			PayOrder po = payBefore(param);
			String orderPaySn = po.getPaySn();

			// 共账号及商户相关参数
			String backUri = DomainUrlUtil.PSC_PAYMENT_URL + "/wxJSpay/topay";

			backUri = backUri + "?orderPaySn=" + orderPaySn + "&describe=" + Constants.WEIXIN_ORDER_NAME + "&money="
					+ txnAmt;
			// URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
			try {
				backUri = URLEncoder.encode(backUri, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
			String url = super.oauth2URL;
			url = url.replace("APPID", this.appid).replace("REDIRECT_URI", backUri)
					.replace("SCOPE", appScope).replace("STATE", "");

			return new WechatPaymentResult(WechatPaymentResult.REQUEST_REDIRECT, url);
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

	@Override
	public RpcResult<PaymentResult> payQuery(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RpcResult<PaymentResult> payRefund(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RpcResult<PaymentResult> payRefundQuery(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
