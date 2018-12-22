package cn.wellcare.portal.controller.card;

import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.api.acc.IPscPiAccService;
import cn.wellcare.api.card.ICardCardinfoService;
import cn.wellcare.api.card.ICardEmpolyeeService;
import cn.wellcare.api.card.ICardOperateService;
import cn.wellcare.api.card.ICardRegInfoService;
import cn.wellcare.card.bo.card.CardInfo;
import cn.wellcare.card.bo.card.CardOperateInfo;
import cn.wellcare.card.entity.CardCardinfo;
import cn.wellcare.card.entity.CardEmpolyeeinfo;
import cn.wellcare.card.entity.CardOperate;
import cn.wellcare.card.entity.CardReginfo;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.DateUtil;
import cn.wellcare.core.utils.RandomUtil;
import cn.wellcare.pojo.RpcResult;
import cn.wellcare.pojo.ServiceResult;
import cn.wellcare.web.BaseController;
import com.alibaba.dubbo.common.utils.Assert;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("cardOperate")
public class CardInfoOperateController extends BaseController {

	@Resource
	private ICardRegInfoService iCardRegInfoService;

	@Resource
	private ICardCardinfoService iCardCardinfoService;

	@Resource
	private ICardEmpolyeeService iCardEmpolyeeService;

	@Resource
	private ICardOperateService iCardOperateService;

	@Resource(name = "pscPiAccService")
	private IPscPiAccService pscPiAccService;

    /**
     * 发卡操作
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "sendCard",method = RequestMethod.POST)
    @ResponseBody
	public ServiceResult<CardInfo> SendCard(@RequestParam Map<String, Object> paramMap) {
		RpcResult<CardInfo> result = new RpcResult<>();
		try {
			Assert.notNull(paramMap,"卡信息为空");
			if (paramMap.isEmpty()){
				throw new BusinessException("卡信息为空");
			}
			String operateId = (String) paramMap.get("user");
			String accountPassWord = (String) paramMap.get("accountPassword");
			String cardType = (String) paramMap.get("cardType");

			//查询主索引
			// TODO: 2018/12/6
			String mpi = RandomUtil.randomNumberic(5);
			//创建账户
			log.info("开始调用账户中心创建账户 参数为："+mpi);
			//String resultStr = HttpClientUtil.sendGet("http://localhost:8088/pscPiAcc/doAdd?pkPi="+ mpi +"&pwd="+accountPassWord);
			PscPiAcc pscPiAcc = new PscPiAcc();
			pscPiAcc.setPkPi(mpi);
			pscPiAcc.setPwd(accountPassWord);
			cn.wellcare.pojo.common.RpcResult<PscPiAcc> accountResult = pscPiAccService.savePscPiAcc(pscPiAcc);
			//账户编号
			if (null == accountResult && accountResult.getData() == null && !accountResult.isSuccess()){
				return new ServiceResult().failedResult("账户创建失败！");
			}
			String accountCode = accountResult.getData().getCodeAcc();
			//根据身份证号查询卡登记信息是否存在
			String idNo = (String) paramMap.get("credentialNo");
			if (StringUtils.isEmpty(idNo)){
				return new ServiceResult().failedResult("身份证信息为空!");
			}
			RpcResult<CardReginfo> cardReginfoResult = iCardRegInfoService.getCardRegInfoByIdNo(idNo);
			CardReginfo cardReginfo = cardReginfoResult.getData();
			String pkReginfo = "";
			//保存或是更新登记信息
			if (null == cardReginfo){
				cardReginfo = new CardReginfo();
				cardReginfo.setNameReg((String) paramMap.get("regName"));
				cardReginfo.setIdNo((String) paramMap.get("credentialNo"));
				cardReginfo.setDtSex((String) paramMap.get("sex"));
				cardReginfo.setBirthDay(DateUtil.strToDate((String)paramMap.get("birthDay"),"yyyyMMddHHmmss"));
				cardReginfo.setTelNo((String) paramMap.get("teleNo"));
				cardReginfo.setAddressCode((String) paramMap.get("addressCode"));
				cardReginfo.setDetailedAddress((String) paramMap.get("detailedAddress"));
				cardReginfo.setPostcode((String) paramMap.get("postCode"));
				cardReginfo.setPkReginfo(RandomUtil.randomNumber(32));
				cardReginfo.setCodeReg(RandomUtil.randomNumber(10));
				cardReginfo.setMpi(mpi);
				cardReginfo.setCreateTime(new Date());
				cardReginfo.setCreator(operateId);
				cardReginfo.setDelFlag(Constants.NO_DEL_FALG);
				iCardRegInfoService.save(cardReginfo);
			}else{
                /*pkReginfo = cardReginfo.getPkReginfo();
                mapToCardReginfo(cardReginfo, param);
                cardReginfo.setModityTime(new Date());
                cardReginfo.setModifier(operateId);
                cardRegInfoModel.updateByIdNo(cardReginfo);*/
				//提示补卡
				if (Constants.EMPLOYEE_CARD_TYPE.equals(cardType)){
					return new ServiceResult().failedResult("登记信息已存在，请您补卡！");
				}else{
					return new ServiceResult().failedResult("登记信息已存在，卡片已发！");
				}

			}
			//保存卡记录信息
			RpcResult<CardCardinfo> cardCardinfoResult = iCardCardinfoService.getCardCardinfByPkCardinfo(pkReginfo);
			CardCardinfo cardCardinfo = null;
			if (cardCardinfoResult.isSuccess() && cardCardinfoResult.getData() != null){
				cardCardinfo = cardCardinfoResult.getData();
			}
			BigDecimal sortNo = new BigDecimal(Constants.DEFAULT_SORT_NO);
			if (cardCardinfo != null){
				sortNo = cardCardinfo.getSortNo().add(new BigDecimal(Constants.DEFAULT_SORT_NO));
			}
			cardCardinfo = new CardCardinfo();
			cardCardinfo.setPkCardinfo(RandomUtil.randomNumber(32));
			cardCardinfo.setPkReginfo(cardReginfo.getPkReginfo());
			cardCardinfo.setDelFlag(Constants.NO_DEL_FALG);
			cardCardinfo.setSortNo(sortNo);
			cardCardinfo.setEuStatus(Constants.IS_USER_STATUS);
			cardCardinfo.setCreator(operateId);
			cardCardinfo.setCreateTime(new Date());
			cardCardinfo.setDtCardtype((String) paramMap.get("cardType"));
			cardCardinfo.setCardNo((String) paramMap.get("cardNo"));
			cardCardinfo.setInnerNo((String) paramMap.get("innerNo"));
			cardCardinfo.setPaySerialid((String) paramMap.get("paySerialID"));
			cardCardinfo.setFlagActive(Constants.IS_USER_STATUS);
			iCardCardinfoService.save(cardCardinfo);

			//如果是员工卡 则保存员工登记信息
			if (Constants.EMPLOYEE_CARD_TYPE.equals(cardType)){
				RpcResult<CardEmpolyeeinfo> cardEmpolyeeinfoResult = iCardEmpolyeeService.getEmpolyeeRegInfoByRegId(cardReginfo.getPkReginfo());
				CardEmpolyeeinfo cardEmpolyeeinfo = null;
				if (cardEmpolyeeinfoResult.isSuccess() && cardEmpolyeeinfoResult.getData() != null){
					cardEmpolyeeinfo = cardEmpolyeeinfoResult.getData();
				}
				if (cardEmpolyeeinfo == null){
					cardEmpolyeeinfo = new CardEmpolyeeinfo();
					cardEmpolyeeinfo.setPkEmployeeinfo(RandomUtil.randomNumber(32));
					cardEmpolyeeinfo.setPkReginfo(cardReginfo.getPkReginfo());
					cardEmpolyeeinfo.setCodeEmployee((String) paramMap.get("codeEmployee"));
					cardEmpolyeeinfo.setCodeDepartment((String) paramMap.get("codeDepartment"));
					cardEmpolyeeinfo.setCreateTime(new Date());
					cardEmpolyeeinfo.setCreator("123");
					//cardEmpolyeeinfo.setCodeApplyer(codeApplyer);
					//cardEmpolyeeinfo.setCodeApplydepartment((String) param.get("codeApplyDepartment"));
					cardEmpolyeeinfo.setDelFlag(Constants.NO_DEL_FALG);
					iCardEmpolyeeService.save(cardEmpolyeeinfo);
				}else{
					cardEmpolyeeinfo.setCodeEmployee((String) paramMap.get("codeEmployee"));
					cardEmpolyeeinfo.setCodeDepartment((String) paramMap.get("codeDepartment"));
					cardEmpolyeeinfo.setModifier("");
					cardEmpolyeeinfo.setModityTime(new Date());
					iCardEmpolyeeService.update(cardEmpolyeeinfo);
				}
			}

			//卡操作流水
			CardOperate cardOperate = new CardOperate();
			cardOperate.setPkOperateDetail(RandomUtil.randomNumber(32));
			cardOperate.setPkReginfo(cardReginfo.getPkReginfo());
			cardOperate.setCardNo(cardCardinfo.getCardNo());
			cardOperate.setPkEmpOpera(operateId);
			cardOperate.setDateHap(new Date());
			cardOperate.setEuOptype(Constants.IS_USER_STATUS);
			cardOperate.setCreator(operateId);
			cardOperate.setCreateTime(new Date());
			cardOperate.setDelFlag(Constants.NO_DEL_FALG);
			iCardOperateService.save(cardOperate);

			//封装结果信息
			CardInfo cardInfo = new CardInfo();
			cardInfo.setMPI(mpi);
			cardInfo.setAccountCode(accountCode);
			cardInfo.setCardRegCode(cardReginfo.getCodeReg());
			cardInfo.setSortNo(cardCardinfo.getSortNo().intValue()); //卡序号

			result.setData(cardInfo);

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
		return new ServiceResult<CardInfo>().convert2SR(result);
    }

    /**
     *  挂失/注销/启用操作
     * @param cardOperateInfo
     * @return
     */
    @RequestMapping(value = "cardOperate",method = RequestMethod.POST)
    @ResponseBody
	public ServiceResult<String> cardOperate(CardOperateInfo cardOperateInfo) {
		RpcResult<String> result = new RpcResult<>();
		try {
			Assert.notNull(cardOperateInfo,"卡信息为空");
			//result = cardInfoOperateService.cardOperate(cardOperateInfo);

			String cardNo = cardOperateInfo.getCardNo();
			if (StringUtils.isEmpty(cardNo)){
				return new ServiceResult().failedResult("卡号为空!");
			}
			//查询卡是否存在
			RpcResult<CardCardinfo> cardCardinfoResult = iCardCardinfoService.getCardCardinfByCardNo(cardNo);
			if (null == cardCardinfoResult && cardCardinfoResult.getData() == null){
				return new ServiceResult().failedResult("卡号有误!");
			}
			//通过卡号查询用户的mpi
			RpcResult<CardReginfo> cardReginfoResult = iCardRegInfoService.getCardRegInfoByCardNo(cardNo);
			if (null == cardReginfoResult && cardReginfoResult.getData() == null){
				return new ServiceResult().failedResult("卡号有误!");
			}
			CardCardinfo cardCardinfo = cardCardinfoResult.getData();
			CardReginfo cardReginfo = cardReginfoResult.getData();
			if (cardCardinfo != null && !Constants.EMPLOYEE_CARD_TYPE.equals(cardCardinfo.getDtCardtype())){
				//验证账户密码是否正确
				String accountPassWord = cardOperateInfo.getAccountPassword();
				if (StringUtils.isEmpty(accountPassWord)){
					return new ServiceResult().failedResult("账户密码为空！");
				}
				String mpi = cardReginfo.getMpi();
				Map<String,Object> map = new HashMap<>();
				map.put("pkPi",mpi);
				map.put("encryptPwd",accountPassWord);
                cn.wellcare.pojo.common.RpcResult<String> accountResult = pscPiAccService.passwordCheck(map);
                //String resultStr = HttpClientUtil.sendGet("http://localhost:8088/pscPiAcc/passwordCheck?pkPi="+mpi+"&encryptPwd="+accountPassWord);
				//log.info("账户中心返回信息："+resultStr);
				if (null != accountResult && accountResult.getData() != null){
					String pwd = accountResult.getData();
					if("0".equals(pwd)){
						return  new ServiceResult().failedResult("密码不正确!");
					}
				}
			}

			// 注销/挂失/启用
			CardOperate cardOperate = new CardOperate();
			String operateType = cardOperateInfo.getOperateType();
			if (Constants.LOSS_OF_CARD.equals(operateType)){
				//挂失
				//挂失状态
				if (Constants.LOSS_OF_CARD_STATUS.equals(cardCardinfo.getEuStatus())){
					return  new ServiceResult().failedResult("卡已挂失!");
				}
				// 注销状态
				if (Constants.CANCAL_CARD_STATUS.equals(cardCardinfo.getEuStatus())){
					return  new ServiceResult().failedResult("卡已注销，不能挂失!");
				}

				cardCardinfo.setEuStatus(Constants.LOSS_OF_CARD_STATUS);
				cardOperate.setEuOptype(Constants.LOSS_OF_CARD_STATUS);

			}else if (Constants.ENABLED_CARD.equals(operateType)){
				//启用
				if (Constants.IS_USER_STATUS.equals(cardCardinfo.getEuStatus())){
					return  new ServiceResult().failedResult("卡已启用!");
				}
				// 注销状态
				if (Constants.CANCAL_CARD_STATUS.equals(cardCardinfo.getEuStatus())){
					return  new ServiceResult().failedResult("卡已注销，不能启用!");
				}
				cardCardinfo.setEuStatus(Constants.IS_USER_STATUS);
				cardOperate.setEuOptype(Constants.ENABLED_CARD);
			}else if (Constants.CANCEL_CARD.equals(operateType)){
				//注销
				cardCardinfo.setEuStatus(Constants.CANCAL_CARD_STATUS);
				cardOperate.setEuOptype(Constants.CANCAL_CARD_STATUS);
			}
			cardCardinfo.setModifier(cardOperateInfo.getOperateId());
			cardCardinfo.setModityTime(new Date());
			iCardCardinfoService.update(cardCardinfo);

			//卡操作流水
			cardOperate.setPkOperateDetail(RandomUtil.randomNumber(32));
			cardOperate.setPkReginfo(cardReginfo.getPkReginfo());
			cardOperate.setCardNo(cardCardinfo.getCardNo());
			cardOperate.setPkEmpOpera(cardOperateInfo.getOperateId());
			cardOperate.setDateHap(new Date());
			cardOperate.setCreator(cardOperateInfo.getOperateId());
			cardOperate.setCreateTime(new Date());
			cardOperate.setDelFlag(Constants.NO_DEL_FALG);
			iCardOperateService.save(cardOperate);
			result.setData("操作成功！");

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
		return new ServiceResult<String>().convert2SR(result);
    }

	/**
	 * 补卡
	 * @param cardOperateInfo
	 * @return
	 */
	@RequestMapping(value = "reissueCard", method = RequestMethod.POST)
    @ResponseBody
	public ServiceResult<Map<String, Object>> reissueCard(CardOperateInfo cardOperateInfo) {
		RpcResult<Map<String, Object>> result = new RpcResult<>();
		try {
			Assert.notNull(cardOperateInfo,"卡信息为空");
			//result = cardInfoOperateService.reissueCard(cardOperateInfo);
			String cardRegCode = cardOperateInfo.getCardRegCode();
			String credentialNo = cardOperateInfo.getCredentialNo();
			//验证账户密码
			String accountPassWord = cardOperateInfo.getAccountPassword();
			if (StringUtils.isEmpty(accountPassWord)){
				return new ServiceResult().failedResult("账户密码为空！");
			}
			//根据发卡登记码查询用户登记信息
			RpcResult<CardReginfo> cardReginfoResult = null;
			if (StringUtils.isNotEmpty(cardRegCode)){
				// return JsonResult.failedResult("-1","发卡登记码为空！");
				cardReginfoResult = iCardRegInfoService.getCardRegInfoByCardRegCode(cardRegCode);
			}else if(StringUtils.isNotEmpty(credentialNo)){
				cardReginfoResult = iCardRegInfoService.getCardRegInfoByIdNo(credentialNo);
			}
			//CardReginfo cardReginfo = cardRegInfoModel.getCardRegInfoByCardNo(cardNo);
			if (null == cardReginfoResult && cardReginfoResult.getData() == null){
				return new ServiceResult().failedResult("未找到登记信息!");
			}
			CardReginfo cardReginfo = cardReginfoResult.getData();
			String mpi = cardReginfo.getMpi();
			//调用账户中心的查询账户
            Map<String,Object> map = new HashMap<>();
            map.put("pkPi",mpi);
            map.put("encryptPwd",accountPassWord);
            cn.wellcare.pojo.common.RpcResult<String> accountResult = pscPiAccService.passwordCheck(map);
            //String resultStr = HttpClientUtil.sendGet("http://localhost:8088/pscPiAcc/passwordCheck?pkPi="+mpi+"&encryptPwd="+accountPassWord);
            //log.info("账户中心返回信息："+resultStr);
            if (null != accountResult && accountResult.getData() != null){
                String pwd = accountResult.getData();
                if("0".equals(pwd)){
                    return  new ServiceResult().failedResult("密码不正确!");
                }
            }

			RpcResult<CardCardinfo> cardCardinfoResult = iCardCardinfoService.getCardCardinfByPkCardinfo(cardReginfo.getPkReginfo());
			//更新新卡信息
			if (cardCardinfoResult == null && cardCardinfoResult.getData() == null){
				return new ServiceResult().failedResult("未找到卡信息！");
			}
			CardCardinfo cardCardinfo = cardCardinfoResult.getData();

			if (!Constants.CANCAL_CARD_STATUS.equals(cardCardinfo.getEuStatus())){
				return new ServiceResult().failedResult("卡未注销，请先注销卡！");
			}

			cardCardinfo.setDelFlag(Constants.DEL_FALG);
			iCardCardinfoService.update(cardCardinfo);
			cardCardinfo.setPkCardinfo(RandomUtil.randomNumber(32));
			cardCardinfo.setCardNo(cardOperateInfo.getNewCardNo());
			cardCardinfo.setSortNo(cardCardinfo.getSortNo().add(new BigDecimal(Constants.DEFAULT_SORT_NO)));
			cardCardinfo.setEuStatus(Constants.IS_USER_STATUS);
			cardCardinfo.setPaySerialid(cardOperateInfo.getPaySerialId());
			cardCardinfo.setDelFlag(Constants.NO_DEL_FALG);
			cardCardinfo.setInnerNo(cardOperateInfo.getInnerNo());
			cardCardinfo.setCreateTime(new Date());

			/*cardCardinfo.setModifier(cardOperateInfo.getOperateId());
			cardCardinfo.setModityTime(new Date());*/
			iCardCardinfoService.save(cardCardinfo);
			//卡操作流水
			CardOperate cardOperate = new CardOperate();
			cardOperate.setPkOperateDetail(RandomUtil.randomNumber(32));
			cardOperate.setPkReginfo(cardReginfo.getPkReginfo());
			cardOperate.setCardNo(cardCardinfo.getCardNo());
			cardOperate.setPkEmpOpera(cardOperateInfo.getOperateId());
			cardOperate.setDateHap(new Date());
			cardOperate.setEuOptype(Constants.IS_USER_STATUS);
			cardOperate.setCreator(cardOperateInfo.getOperateId());
			cardOperate.setCreateTime(new Date());
			cardOperate.setDelFlag(Constants.NO_DEL_FALG);
			iCardOperateService.save(cardOperate);

			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("cardSortNo",cardCardinfo.getSortNo());
			result.setData(resultMap);
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
		return new ServiceResult<Map<String, Object>>().convert2SR(result);
    }
}
