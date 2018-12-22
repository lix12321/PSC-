package cn.wellcare.portal.controller.card;

import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.api.acc.IPscPiAccService;
import cn.wellcare.api.card.*;
import cn.wellcare.card.bo.card.EmployeeChargeInfo;
import cn.wellcare.card.bo.card.EmpolyeeInfo;
import cn.wellcare.card.bo.card.EmpolyeeRegInfo;
import cn.wellcare.card.entity.*;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.DateUtil;
import cn.wellcare.core.utils.RandomUtil;
import cn.wellcare.pojo.RpcResult;
import cn.wellcare.pojo.ServiceResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("cardEmpolyee")
@Controller
public class CardEmpolyeeController {

    private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ICardEmpolyeeService cardEmpolyeeService;

	@Resource
    private ICardBatchregService cardBatchregService;

	@Resource
    private ICardRegInfoService iCardRegInfoService;

	@Resource
    private ICardCardinfoService iCardCardinfoService;

	@Resource
    private ICardBatchregDetailService iCardBatchregDetailService;

	@Resource
    private ICardBatchchargeService iCardBatchchargeService;

	@Resource
    private ICardChargeService iCardChargeService;

    @Resource(name = "pscPiAccService")
    private IPscPiAccService pscPiAccService;


    /**
     * 查询员工信息
     * @param paramMap
     * @return
     */
    @RequestMapping(value="getEmpolyeeRegInfo",method = RequestMethod.POST)
    @ResponseBody
	public ServiceResult<List<EmpolyeeRegInfo>> getEmpolyeeRegInfo(@RequestParam Map<String, Object> paramMap) {
        RpcResult<List<EmpolyeeRegInfo>> result = new RpcResult<>();
        try {
            if (null == paramMap || paramMap.isEmpty()) {
                throw new BusinessException("卡信息为空");
            }
            result = cardEmpolyeeService.getEmpolyeeRegInfo(paramMap);
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
        return new ServiceResult<List<EmpolyeeRegInfo>>().convert2SR(result);
    }


    /**
     * 员工批量发卡
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "batchSendCard",method = RequestMethod.POST)
    @ResponseBody
	public ServiceResult<String> batchSendCard(@RequestParam Map<String, Object> paramMap) {
        RpcResult<String> result = new RpcResult<>();
        try {
            if (null == paramMap || paramMap.isEmpty()){
                throw new BusinessException("员工卡信息为空");
            }
            //result = cardEmpolyeeService.batchSendCard(paramMap);

            //申请部门
            String codeApplyDepartment = (String) paramMap.get("codeApplyDepartment");
            //申请人
            String codeApplyer = (String) paramMap.get("codeApplyer");
            //原始申请数据
            //String oriCSVData = (String) paramMap.get("oriCSVData");
            // List<EmpolyeeInfo> empolyeeRegInfoList = (List<EmpolyeeInfo>) paramMap.get("empolyees");
            String oridataFilename = (String) paramMap.get("oridataFilename");
            List<EmpolyeeInfo> empolyeeRegInfoList = new Gson().fromJson((String)paramMap.get("buildCardList"), new TypeToken<List<EmpolyeeInfo>>() {}.getType());

            if (null == empolyeeRegInfoList || empolyeeRegInfoList.isEmpty()){
                throw new BusinessException("员工信息为空！");
            }
            // 保存批量发卡信息
            //批量发卡信息
            CardBatchreg cardBatchreg = new CardBatchreg();
            cardBatchreg.setPkBatchreg(RandomUtil.randomNumber(32));
            cardBatchreg.setApplyDatapath(oridataFilename);
            cardBatchreg.setCodeApplydepartment(codeApplyDepartment);
            cardBatchreg.setCodeApplyer(codeApplyer);
            cardBatchreg.setCreator("");
            cardBatchreg.setCreateTime(new Date());
            cardBatchreg.setDelFlag(Constants.DEL_FALG);
            cardBatchregService.save(cardBatchreg);

            List<CardReginfo> cardReginfos = new ArrayList<>();
            List<CardEmpolyeeinfo> cardEmpolyeeinfos = new ArrayList<>();
            List<CardCardinfo> cardCardinfos = new ArrayList<>();
            List<PscPiAcc> emplyeeAccs = new ArrayList<>();
            List<CardBatchregDetail> cardBatchregDetails = new ArrayList<>();
            for (EmpolyeeInfo regInfo : empolyeeRegInfoList){
                //登记信息
                CardReginfo cardReginfo = new CardReginfo();
                cardReginfo.setPkReginfo(RandomUtil.randomNumber(32));
                cardReginfo.setCodeReg(RandomUtil.randomNumber(10));
                cardReginfo.setMpi(regInfo.getmPI());
                cardReginfo.setNameReg(regInfo.getRegName());
                cardReginfo.setTelNo(regInfo.getTeleNo());
                cardReginfo.setIdNo(regInfo.getCredentialNo());
                cardReginfo.setDtSex(regInfo.getSex());
                cardReginfo.setBirthDay(DateUtil.strToDate(regInfo.getBirthDay(),"yyyyMMddHHmmss"));
                cardReginfo.setAddressCode(regInfo.getAddressCode());
                cardReginfo.setDetailedAddress(regInfo.getDetailedAddress());
                cardReginfo.setPostcode(regInfo.getPostCode());
                cardReginfo.setDelFlag(Constants.DEL_FALG);
                cardReginfo.setCreator("");
                cardReginfo.setCreateTime(new Date());
                cardReginfos.add(cardReginfo);

                //员工登记信息
                CardEmpolyeeinfo cardEmpolyeeinfo = new CardEmpolyeeinfo();
                cardEmpolyeeinfo.setPkEmployeeinfo(RandomUtil.randomNumber(32));
                cardEmpolyeeinfo.setPkReginfo(cardReginfo.getPkReginfo());
                cardEmpolyeeinfo.setCodeEmployee(regInfo.getCodeEmployee());
                cardEmpolyeeinfo.setCodeDepartment(regInfo.getCodeDepartment());
                cardEmpolyeeinfo.setCreateTime(new Date());
                cardEmpolyeeinfo.setCreator("");
                cardEmpolyeeinfo.setCodeApplyer(codeApplyer);
                cardEmpolyeeinfo.setCodeApplydepartment(codeApplyDepartment);
                cardEmpolyeeinfo.setDelFlag(Constants.DEL_FALG);
                cardEmpolyeeinfos.add(cardEmpolyeeinfo);

                //员工卡信息
                CardCardinfo cardCardinfo = new CardCardinfo();
                cardCardinfo.setPkCardinfo(RandomUtil.randomNumber(32));
                cardCardinfo.setPkReginfo(cardReginfo.getPkReginfo());
                cardCardinfo.setCardNo(regInfo.getCardNo());
                cardCardinfo.setInnerNo(regInfo.getInnerNo());
                cardCardinfo.setDtCardtype(regInfo.getCardType());
                cardCardinfo.setSortNo(new BigDecimal(Constants.DEFAULT_SORT_NO));
                cardCardinfo.setPaySerialid(regInfo.getPaySerialID());
                cardCardinfo.setDelFlag(Constants.DEL_FALG);
                cardCardinfo.setCreator("");
                cardCardinfo.setCreateTime(new Date());
                cardCardinfos.add(cardCardinfo);

                // 员工账户密码
                PscPiAcc acc = new PscPiAcc();
                acc.setPkPi(regInfo.getmPI());
                acc.setPwd(regInfo.getAccountPassword());
                emplyeeAccs.add(acc);

                //批量发卡明细
                CardBatchregDetail cardBatchregDetail = new CardBatchregDetail();
                cardBatchregDetail.setPkBatchregDetail(RandomUtil.randomNumber(32));
                cardBatchregDetail.setPkBatchcharge(cardBatchreg.getPkBatchreg());
                cardBatchregDetail.setPkReginfo(cardReginfo.getPkReginfo());
                cardBatchregDetail.setDtPaymode(regInfo.getCardType());
                cardBatchregDetail.setCardNo(regInfo.getCardNo());
                cardBatchregDetail.setCreator("");
                cardBatchregDetail.setCreateTime(new Date());
                cardBatchregDetail.setDelFlag(Constants.DEL_FALG);
                cardBatchregDetails.add(cardBatchregDetail);

            }

            //保存员工登记信息
            iCardRegInfoService.batchSave(cardReginfos);

            // 保存员工信息
            cardEmpolyeeService.batchSave(cardEmpolyeeinfos);

            // 保存员工卡信息
            iCardCardinfoService.batchSave(cardCardinfos);

            //保存批量发卡明细信息
            iCardBatchregDetailService.batchSave(cardBatchregDetails);

            //保存员工账户信息
            Map<String,Object> map = new HashMap<>();
            map.put("pscPiAccList",emplyeeAccs);
            log.info("调用账户中心创建账户 参数为："+ map);
            //String resultStr = HttpClientUtil.doPost("http://localhost:8088/pscPiAcc/batchUpdateAdd",map);
            cn.wellcare.pojo.common.RpcResult<Integer> resultStr = pscPiAccService.batchInsertPscPiAcc(map);
            log.info("账户中心返回信息："+ resultStr);
            if (resultStr != null && resultStr.getData()!= null && resultStr.isSuccess()){
                result.setData("批量保存员工信息成功！");

            }else{
                result.setData("批量保存员工信息失败！");
            }

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
     * 批量充值
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "batchCharge",method = RequestMethod.POST)
    @ResponseBody
	public ServiceResult<String> batchCharge(@RequestParam Map<String, Object> paramMap) {
        RpcResult<String> result = new RpcResult<>();
        try {
            if (null == paramMap || paramMap.isEmpty()){
                throw new BusinessException("员工卡信息为空");
            }
            //result = cardEmpolyeeService.batchCharge(paramMap);

            //申请部门
            String codeApplyDepartment = (String) paramMap.get("codeApplyDepartment");
            //申请人
            String codeApplyer = (String) paramMap.get("codeApplyer");
            //原始申请数据
            String oriCSVData = (String) paramMap.get("oriCSVData");
            ObjectMapper mapper = new ObjectMapper();
            List<EmployeeChargeInfo> employeeChargeInfoList = new Gson().fromJson((String)paramMap.get("empolyees"), new TypeToken<List<EmployeeChargeInfo>>() {}.getType());
            if (null == employeeChargeInfoList || employeeChargeInfoList.isEmpty()){
                throw new BusinessException("员工信息为空！");
            }
            //查询登记员工的信息
            RpcResult<List<CardReginfo>> cardRegInfoResult= iCardRegInfoService.getAllCardRegInfo();
            if (cardRegInfoResult.isSuccess() && cardRegInfoResult.getData() == null){
                throw new BusinessException("员工信息未登记");
            }
            List<CardReginfo> cardRegInfoList = cardRegInfoResult.getData();
            if (cardRegInfoList == null || cardRegInfoList.isEmpty()){
                return new ServiceResult().failedResult("员工信息未登记，请先登记员工信息！");
            }
            //将查询出的员工登记信息放入map中，key为mpi value为登记信息的id
            Map<String,String> cardRegInfoMaps = cardRegInfoList.stream().collect(Collectors.toMap(CardReginfo::getMpi,CardReginfo::getPkReginfo));

            //保存批量充值信息
            CardBatchcharge cardBatchcharge = new CardBatchcharge();
            cardBatchcharge.setPkBatchcharge(RandomUtil.randomNumber(32));
            cardBatchcharge.setCodeApplydepartment(codeApplyDepartment);
            cardBatchcharge.setCodeApplyer(codeApplyer);
            cardBatchcharge.setApplyDatapath(oriCSVData);
            cardBatchcharge.setCreator("");
            cardBatchcharge.setCreateTime(new Date());
            cardBatchcharge.setDelFlag(Constants.DEL_FALG);
            iCardBatchchargeService.save(cardBatchcharge);

            List<PscPiAcc> piAccList = new ArrayList<>();
            //批量保存充值明细
            List<CardCharge> chargeList = new ArrayList<>();
            for (EmployeeChargeInfo chargeInfo : employeeChargeInfoList){
                CardCharge cardCharge = new CardCharge();
                cardCharge.setPkCharge(RandomUtil.randomNumber(32));
                //通过员工信息的mpi 获取登记信息的id
                cardCharge.setPkReginfo(cardRegInfoMaps.get(chargeInfo.getMPI()));
                cardCharge.setCodeDepartment(codeApplyDepartment);
                BigDecimal amount = new BigDecimal(0);
                if (!StringUtils.isEmpty(chargeInfo.getAmount())){
                    amount = new BigDecimal(chargeInfo.getAmount());
                }
                cardCharge.setAmount(amount);
                //cardCharge.setp
                chargeList.add(cardCharge);

                //封装账户信息
                PscPiAcc piAcc = new PscPiAcc();
                piAcc.setPkPi(chargeInfo.getMPI());
                piAcc.setAmtAcc(amount);
                piAccList.add(piAcc);
            }
            iCardChargeService.batchSave(chargeList);

            //调用账户中心充值
            Map<String,Object> map = new HashMap<>();
            map.put("pscPiAccList",piAccList);
            log.info("调用账户中心账户批量充值 参数为："+ piAccList);

            cn.wellcare.pojo.common.RpcResult<Integer> accountResult = pscPiAccService.batchUpdatePscPiAcc(map);
            if (accountResult != null && accountResult.getData()!= null && accountResult.isSuccess()){
                result.setData("批量充值成功！");
            }else{
                result.setData("批量充值失败！");
            }

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
}
