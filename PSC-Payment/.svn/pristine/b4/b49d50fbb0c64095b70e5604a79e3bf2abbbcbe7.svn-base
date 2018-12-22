package cn.wellcare.service.transaction.query.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.model.payment.account.PscPiAccModel;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.account.PscPiAcc;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.wechat.WXPay;
import cn.wellcare.pojo.wechat.WXPayConstants;
import cn.wellcare.service.settins.WechatSettings;
import cn.wellcare.service.transaction.payment.wechat.base.WechatTradeResult;

@Service
public class WechatQueryService {
	@Resource
	private PscPiAccModel piAccModel;
	@Resource
	private SysCodeMasterModel sysCodeMasterModel;
	@Resource
	private WechatSettings wechatSettings;

	private Logger log = Logger.getLogger(this.getClass());

	public OrderResult getOrderBySn(Map<String, Object> param, PayOrder payOrder) {
		String userId = String.valueOf(param.get(BaseParam.USER_ID));
		String orgId = String.valueOf(param.get(BaseParam.ORG_ID));
		String orderState = String.valueOf(payOrder.getOrderState());
		String orderDate = String.valueOf(payOrder.getFinishTime());
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("out_trade_no", payOrder.getPaySn());// 添加订单号（用户传来的）
		Map<String, String> resultMap = new HashMap<String, String>();
		// 初始化商户app_id,mch_id,sign等认证
		WXPay wxpay;
		if (wechatSettings.isSandboxnewOpen()) {
			wxpay = new WXPay(wechatSettings, WXPayConstants.SignType.MD5, true);
		} else {
			wxpay = new WXPay(wechatSettings);
		}
		StringBuffer status = new StringBuffer();
		try {
			resultMap = wxpay.orderQuery(dataMap);
			if (!resultMap.get("return_code").equals("SUCCESS")) { // 通信失败
				throw new BusinessException(String.format("错误信息: %s", resultMap.get("return_msg")));
			} else if (!resultMap.get("result_code").equals("SUCCESS")) {
				throw new BusinessException(String.format("错误信息: %s", resultMap.get("err_code_des")));
			} else  {
				switch (resultMap.get("trade_state")) {

				case "SUCCESS":
					status.append("支付成功:");
					break;
				case "REFUND":
					status.append("转入退款:");
					break;
				case "NOTPAY":
					status.append("未支付:");
					break;
				case "CLOSED":
					status.append("已关闭:");
					break;
				case "REVOKED":
					status.append("已撤销:");
					break;
				case "USERPAYING":
					status.append("用户支付中:");
					break;
				case "PAYERROR":
					status.append("支付失败:");
					break;
				default:
					break;
				}
			}
			String returnCode = Constants.RESONSE_SUCCESS_CODE;
			String returnMsg = Constants.RESONSE_SUCCESS_MSG;

			SysCodeMaster codeMaster = sysCodeMasterModel.getSysCodeMasterById("ORDER_STATE",orderState);//查询订单状态对应的数据字典信息

			PscPiAcc pscPiAcc = piAccModel.getPscPiAccBypkPi(userId);//查询账户信息

			//对返回的参数进行加密
			param.clear();
			param.put("orderId",payOrder.getOuterOrderSn());
			param.put("status",codeMaster.getCodeText());
			param.put("accId", String.valueOf(pscPiAcc.getPkPiacc()));
			param.put("orderDate", orderDate);
			param.put("statusCode",orderState);
			param.put("returnCode",returnCode);
			param.put("returnMsg",returnMsg);
			Map<String, Object> sorted = CommonUtils.sort(param);
			this.log.debug("客户端排序后参数集：" + sorted);
			ISysOrganizationService orgService = (ISysOrganizationService) ServiceLocator.getInstance()
					.getBean("sysOrganizationService");
			String securtkey = orgService.getSysOrganizationById(Integer.valueOf(orgId))
					.getData().getAuthSecret();
			log.debug("机构密钥：" + securtkey);
			String sign = Md5SignUtil.sginMD5(sorted, securtkey);

			return new WechatTradeResult(payOrder.getOuterOrderSn(), codeMaster.getCodeText(), String.valueOf(pscPiAcc.getPkPiacc()), orderDate,
					orderState, sign, returnCode, returnMsg);
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
