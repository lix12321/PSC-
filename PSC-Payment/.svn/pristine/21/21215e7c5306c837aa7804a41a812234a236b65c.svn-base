package cn.wellcare.model.notify.paynotify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.dao.log.PayNotifyLogDao;
import cn.wellcare.dao.notify.PayNotifyDao;
import cn.wellcare.dao.payset.PaySettingAlipayDao;
import cn.wellcare.dao.payset.PaySettingIntegrationDao;
import cn.wellcare.dao.payset.PaySettingWechatDao;
import cn.wellcare.entity.log.PayNotifyLog;
import cn.wellcare.entity.notify.PayNotify;
import cn.wellcare.entity.payset.PaySettingAlipay;
import cn.wellcare.entity.payset.PaySettingIntegration;
import cn.wellcare.entity.payset.PaySettingWechat;

@Component
public class PayNotifyModel {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private PayNotifyDao payNotifyDao;
	@Resource
	private PaySettingWechatDao paySettingWechatDao;
	@Resource
	private PaySettingIntegrationDao aySettingIntegrationDao;
	@Resource
	private PaySettingAlipayDao paySettingAlipayDao;
	@Resource
	private PayNotifyLogDao payNotifyLogDao;

	public boolean insertPayNotify(PayNotify payNotify) {
		if (null != payNotify) {

			return payNotifyDao.save(payNotify) > 0;
		}

		return false;
	}

	public boolean updatePayNotify(PayNotify payNotify) {
		if (null != payNotify) {
			log.debug("更新通知");
			return payNotifyDao.updateByOrderId(payNotify) > 0;
		}
		return false;
	}

	public boolean createNotifyLog(PayNotifyLog log) {
		return payNotifyLogDao.save(log) > 0;
	}

	public PayNotify getPayNotifyByOrderId(Integer orderId) {
		PayNotify payNotify = null;
		if (!CommonUtils.isNull(orderId)) {
			payNotify = payNotifyDao.getPayNotifyByOrderId(orderId);
		}
		return payNotify;
	}

	@Deprecated
	public String getSettingsByOrg(Integer orgId, String type) {
		Assert.notNull(orgId);
		Assert.notNull(type);
		
		int payType = getPayType(type);

		String notifyURL = "";
		Map<String, Object> param = new HashMap<>();
		param.put("orgId", orgId);
		param.put("status", 1);
		switch (payType) {
		case Constants.PAY_SETTING_WECHAT:
			List<PaySettingWechat> wxlist = paySettingWechatDao.getList(param);
			if (wxlist != null && wxlist.size() > 0) {
				notifyURL = wxlist.get(0).getNotifyUrl();
			}
			break;
		case Constants.PAY_SETTING_ALIPAY:
			List<PaySettingAlipay> alipaylist = paySettingAlipayDao.getList(param);
			if (alipaylist != null && alipaylist.size() > 0) {
				notifyURL = alipaylist.get(0).getNotifyUrl();
			}
			break;
		case Constants.PAY_SETTING_JH:
			List<PaySettingIntegration> jhlist = aySettingIntegrationDao.getList(param);
			if (jhlist != null && jhlist.size() > 0) {
				notifyURL = jhlist.get(0).getNotifyUrl();
			}
			break;
		// TODO other
		default:
			break;
		}
		
		log.debug("机构：" + orgId + "\n支付类型：" + payType + "\n通知地址：" + notifyURL);
		return notifyURL;
	}

	/**
	 * 支付类型与设置类型对照
	 * 
	 * @param type
	 * @return
	 */
	private int getPayType(String type) {
		String paytype = PaymentType.getPaymentCodeByNameOrCode(type);
		if (paytype.equals(PaymentType.WECHAT_JSAPI.getPaymentCode())
				|| paytype.equals(PaymentType.WECHAT_NATIVE.getPaymentCode())
				|| paytype.equals(PaymentType.WECHAT_SAOMA.getPaymentCode())) {
			return Constants.PAY_SETTING_WECHAT;
		} else if (paytype.equals(PaymentType.ALIPAY.getPaymentCode())
				|| paytype.equals(PaymentType.ALIPAY_SAOMA.getPaymentCode())) {
			return Constants.PAY_SETTING_ALIPAY;
		} else if (paytype.equals(PaymentType.JUHPAY.getPaymentCode())) {
			return Constants.PAY_SETTING_JH;
		} else if (paytype.equals(PaymentType.UNIONPAY.getPaymentCode())) {
			return Constants.PAY_SETTING_UNION;
		}
		return 0;
	}
}
