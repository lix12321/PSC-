package cn.wellcare.service.settins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.wellcare.pojo.wechat.WXPay;
import cn.wellcare.pojo.wechat.WXPayConstants.SignType;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WXPayByCard {
	public Map<String, Object> pay(Map<String, String> resdata, WechatSettings wechatSettings) {
		Map<String, Object> resultMap = new HashMap<>();
		WXPay wxpay;
		if (wechatSettings.isSandboxnewOpen()) {
			wxpay = new WXPay(wechatSettings, SignType.MD5, true);
		} else {
			wxpay = new WXPay(wechatSettings);
		}

		try {
			Map<String, String> resp = wxpay.microPay(resdata);
			String returnCode = resp.get("return_code");
			boolean result;
			String msg;
			if (returnCode.equals("FAIL")) {
				result = false;
				msg = "交易失败，无需撤销";
				resultMap.put("result", result);
				resultMap.put("msg", msg);
				resultMap.put("data", resp);
				return resultMap;
			}

			String resultCode = resp.get("result_code");
			if (resultCode.equals("SUCCESS")) {
				result = true;
				msg = "交易成功";
				resultMap.put("result", result);
				resultMap.put("msg", msg);
				resultMap.put("data", resp);
				return resultMap;
			}

			String errCode = resp.get("err_code");
			List<String> errCodeList = new ArrayList();
			errCodeList.add("SYSTEMERROR");
			errCodeList.add("BANKERROR");
			errCodeList.add("USERPAYING");
			if (!errCodeList.contains(errCode)) {
				result = false;
				msg = "交易失败，无需撤销";
				resultMap.put("result", result);
				resultMap.put("msg", msg);
				resultMap.put("data", resp);
				return resultMap;
			}

			boolean csBool = false;

			for (int i = 0; i < 6; ++i) {
				long timeInterval = 5000L;
				Thread.sleep(5000L);
				Map<String, String> queData = new HashMap();
				queData.put("out_trade_no", resdata.get("out_trade_no"));
				Map<String, String> quesp = wxpay.orderQuery(queData);
				String queReturnCode = quesp.get("return_code");
				if (!queReturnCode.equals("FAIL")) {
					String queResultCode = quesp.get("result_code");
					if (!queResultCode.equals("FAIL")) {
						String queTradeState = quesp.get("trade_state");
						if (queTradeState.equals("SUCCESS")) {
							result = true;
							msg = "交易成功";
							resultMap.put("result", result);
							resultMap.put("msg", msg);
							resultMap.put("data", quesp);
							csBool = true;
							break;
						}

						if (!queTradeState.equals("USERPAYING")
								&& (queTradeState.equals("CLOSED") || queTradeState.equals("PAYERROR"))) {
							result = false;
							msg = "交易失败，无需撤销";
							resultMap.put("result", result);
							resultMap.put("msg", msg);
							resultMap.put("data", quesp);
							csBool = true;
							break;
						}
					}
				}
			}

			if (!csBool) {
				Map<String, String> cxData = new HashMap();
				cxData.put("out_trade_no", resdata.get("out_trade_no"));
				Map<String, String> cxres = wxpay.reverse(cxData);
				String cxresReturnCode = cxres.get("return_code");
				if (cxresReturnCode.equals("FAIL")) {
					result = false;
					msg = "交易失败，无法撤销，请人工核对对账单";
					resultMap.put("result", result);
					resultMap.put("msg", msg);
					resultMap.put("data", cxres);
					return resultMap;
				}

				String cxresResultCode = cxres.get("result_code");
				if (cxresResultCode.equals("SUCCESS")) {
					result = false;
					msg = "交易失败，已经撤销";
					resultMap.put("result", result);
					resultMap.put("msg", msg);
					resultMap.put("data", cxres);
					return resultMap;
				}

				result = false;
				msg = "交易失败，无法撤销，请人工核对对账单";
				resultMap.put("result", result);
				resultMap.put("msg", msg);
				resultMap.put("data", cxres);
				return resultMap;
			}
		} catch (Exception var22) {
			var22.printStackTrace();
		}

		return resultMap;
	}

}
