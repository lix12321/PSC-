package cn.wellcare.service.acc;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.acc.entity.PscPiAccDetail;
import cn.wellcare.api.trade.IPscAccRefundService;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.EnumerateParameter;
import cn.wellcare.model.acc.PscPiAccDetailModel;
import cn.wellcare.model.acc.PscPiAccModel;
import cn.wellcare.pojo.common.AccPaymentResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 账户退费操作
 */
@Service("refundService")
public class AccountRefundService implements IPscAccRefundService {

	@Resource
	private PscPiAccModel piAccModel;
	@Resource
	private PscPiAccDetailModel pscPiAccDetailModel;

	@Override
	public RpcResult<AccPaymentResult> accRefund(Map<String, Object> param) {
		RpcResult<AccPaymentResult> result = new RpcResult<>();

		try {
			BigDecimal refundAmount = new BigDecimal(String.valueOf(param.get(BaseParam.REFUND_AMOUNT)));// 退款金额
			String pkPi = (String) param.get(BaseParam.USER_ID);// 获取用户主索引
			Integer pkOrg = Integer.valueOf(String.valueOf(param.get(BaseParam.ORG_ID)));
			PscPiAcc pscPiAcc = piAccModel.getPscPiAccBypkPi(pkPi);// 获取用户账户信息

			// 1.更新账户余额
			pscPiAcc.setAmtAcc(pscPiAcc.getAmtAcc().add(refundAmount));// 当前余额加上支付金额
			piAccModel.updatePscPiAcc(pscPiAcc);// 更新账户信息
			// 3.新增账户流水
			PscPiAccDetail pscPiAccDetail = new PscPiAccDetail();
			// pscPiAccDetail.setPkEmpOpera(); //操作人
			pscPiAccDetail.setPkOrg(pkOrg);
			pscPiAccDetail.setPkPiacc(pscPiAcc.getPkPiacc());
			pscPiAccDetail.setAmtBalance(pscPiAcc.getAmtAcc());
			pscPiAccDetail.setAmount(refundAmount);
			pscPiAccDetail.setEuOptype(EnumerateParameter.TWO);
			pscPiAccDetail.setEuDirect(Integer.valueOf(EnumerateParameter.ONE));
			pscPiAccDetailModel.savePscPiAccDetail(pscPiAccDetail);// 新增账户流水

			result.setData(new AccPaymentResult(refundAmount.toString(), String.valueOf(pscPiAcc.getAmtAcc())));
		} catch (Exception e) {
			result.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode())) {
					result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				} else {
					result.setMsgInfo(e.getMessage());
				}
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
