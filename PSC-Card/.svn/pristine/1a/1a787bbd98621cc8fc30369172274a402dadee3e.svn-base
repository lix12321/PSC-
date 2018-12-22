package cn.wellcare.portal.controller.card;


import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.api.acc.IPscPiAccService;
import cn.wellcare.api.card.ICardRegInfoService;
import cn.wellcare.card.bo.card.CardRegInfoBo;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.pojo.RpcResult;
import cn.wellcare.pojo.ServiceResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cardRegInfo")
public class CardRegInfoController {

	private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private ICardRegInfoService cardRegInfoService;

    @Resource(name = "pscPiAccService")
	private IPscPiAccService pscPiAccService;

    /**
     * 获取发卡登记信息
     * @return
     */
    @RequestMapping(value = "getCardRegInfo",method = RequestMethod.POST)
	public ServiceResult<List<CardRegInfoBo>> getCardRegInfo(@RequestParam Map<String, Object> paramMap) {
		RpcResult<List<CardRegInfoBo>> result = new RpcResult<>();
		try {
			if (null == paramMap || paramMap.isEmpty()){
				throw new BusinessException("卡信息为空");
			}
			//result = cardRegInfoService.getCardRegInfo(paramMap);

			String cardType = (String)paramMap.get("cardType");
			List<String> cardTypes = null;
			if (!StringUtils.isEmpty(cardType) && !"null".equals(cardType)){
				cardTypes = Arrays.asList((cardType).split(","));
				paramMap.put("cardTypes",cardTypes);
			}
			RpcResult<List<CardRegInfoBo>> cardRegInfoResult = cardRegInfoService.getCardRegInfo(paramMap);
			if (!cardRegInfoResult.isSuccess()||cardRegInfoResult.getData() == null){
				throw new BusinessException("获取登记信息失败！");
			}
			List<CardRegInfoBo> cardRegInfoList = cardRegInfoResult.getData();
			List<CardRegInfoBo> cardRegInfoBos = new ArrayList<>();
			if (null == cardRegInfoList || cardRegInfoList.isEmpty()){
				result.setData(cardRegInfoBos);
				return new ServiceResult<List<CardRegInfoBo>>().convert2SR(result);
			}
			for (CardRegInfoBo cardRegInfo : cardRegInfoList){

				//cardRegInfoBo = resultList.get(0);
				String mpi = cardRegInfo.getMpi();
				log.info("开始调用账户中心  参数为："+mpi);
				cn.wellcare.pojo.common.RpcResult<PscPiAcc> accountResult = pscPiAccService.getPscPiAccBypkPi(mpi);
				//账户余额
				BigDecimal amtAcc = new BigDecimal(0);
				String accountId = "";
				String accountStatus = "";
				String codeAccount = "";
				if (null != accountResult && accountResult.getData() != null){
					log.info("账户余额："+accountResult.getData().getAmtAcc());
					amtAcc = accountResult.getData().getAmtAcc();
					accountId = String.valueOf(accountResult.getData().getPkPiacc());
					accountStatus = accountResult.getData().getEuStatus();
					codeAccount = accountResult.getData().getCodeAcc();
				}
				cardRegInfo.setAccountBalance(amtAcc);
				cardRegInfo.setAccountId(accountId);
				cardRegInfo.setAccountStatus(accountStatus);
				cardRegInfo.setCodeAccount(codeAccount);
				cardRegInfoBos.add(cardRegInfo);
			}
            result.setData(cardRegInfoBos);
		} catch (Exception e) {
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
		}
		return new ServiceResult<List<CardRegInfoBo>>().convert2SR(result);
    }
}
