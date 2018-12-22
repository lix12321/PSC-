package cn.wellcare.service.transaction.payment.wechat.base;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.payset.PaySettingWechat;
import cn.wellcare.model.modules.payset.PaySettingWechatModel;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.service.transaction.payment.wechat.util.MD5Util;

/**
 * 微信支付
 * 
 * @author zhaihl
 *
 */
public class WechatPayment {
	@Resource
	private IOrderService orderService;
	@Resource
	private PaySettingWechatModel paySettingWechatModel;
	Logger log = Logger.getLogger(this.getClass());

	protected String appid;
	protected String notifyURL;
	protected String mchid;
	protected String appsecret;
	protected String apikey;
	protected String oauth2Token;
	protected String createOrderURL;
	protected String oauth2URL;
	protected String appScope;

	public void initConfig(Integer orgId) {
		// 支付设置
		PaySettingWechat setting = paySettingWechatModel.getByOrg(orgId);
		this.appid = setting.getAppid();
		this.appsecret = setting.getAppsecret();
		this.apikey = setting.getKey();
		this.oauth2Token = setting.getOauth2Token();
		this.createOrderURL = setting.getCreateOrderUrl();
		this.oauth2URL = setting.getOauth2Url();
		this.mchid = setting.getMchid();
		this.appScope = setting.getAppScope();

		this.notifyURL = DomainUrlUtil.PSC_PAYMENT_URL + "/unifyPay/wechatNotify";
	}

	public PayOrder payBefore(Map<String, Object> params) {
		// 1.创建订单
		PayOrder po = this.orderService.createOrder(params, new UnifyPaymentInfo() {

			@Override
			public String getPaymentName() {
				return getWechatPaymentName();
			}

			@Override
			public String getOrderType() {
				return getWechatOrderType();
			}
		});
		// 2.返回订单信息
		return po;
	}

	protected String getWechatOrderType() {
		return null;
	}

	protected String getWechatPaymentName() {
		return null;
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	@SuppressWarnings("rawtypes")
	public String createSign(SortedMap<String, String> packageParams, String key) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);

		this.log.info("WXPay md5 sb:" + sb + "key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		this.log.info("WXPay packge签名:" + sign);

		return sign;
	}
}
