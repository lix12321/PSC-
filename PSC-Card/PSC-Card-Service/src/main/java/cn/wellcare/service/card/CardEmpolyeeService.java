package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardEmpolyeeService;
import cn.wellcare.card.bo.card.EmpolyeeRegInfo;
import cn.wellcare.card.entity.CardEmpolyeeinfo;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.DateUtil;
import cn.wellcare.core.utils.RandomUtil;
import cn.wellcare.model.card.CardEmpolyeeModel;
import cn.wellcare.pojo.RpcResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("cardEmpolyeeService")
public class CardEmpolyeeService implements ICardEmpolyeeService {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private CardEmpolyeeModel cardEmpolyeeModel;

    /**
     * 获取员工信息
     * @param paramMap
     * @return
     */
    @Override
	public RpcResult<List<EmpolyeeRegInfo>> getEmpolyeeRegInfo(Map<String, Object> paramMap) {
		RpcResult<List<EmpolyeeRegInfo>> jsonResult = new RpcResult<>();
        try {
            //List<EmpolyeeRegInfo> empolyeeRegInfo = cardEmpolyeeModel.getEmpolyeeRegInfo(paramMap);
     /*   if (null != empolyeeRegInfo && empolyeeRegInfo.size()>0){
            jsonResult.setData(empolyeeRegInfo);
        }*/
            List<EmpolyeeRegInfo> empolyeeRegInfo = new ArrayList<>();
            for (int i=0;i<5;i++){
                EmpolyeeRegInfo empolyeeRegInfo1 = new EmpolyeeRegInfo();
                empolyeeRegInfo1.setRegName(getName());
                empolyeeRegInfo1.setCodeDepartment("12");
                empolyeeRegInfo1.setCodeEmployee("10000"+i);
                empolyeeRegInfo1.setBirthDay(DateUtil.strToDate("1998-10-15 09:16:00"));
                empolyeeRegInfo1.setCredentialNo("612731199"+i+"0"+(i+1)+"2"+(i+3)+RandomUtil.randomNumberic(4));
                empolyeeRegInfo1.setAddressCode("33");
                empolyeeRegInfo1.setDetailedAddress("西安市");
                empolyeeRegInfo1.setMpi(RandomUtil.randomNumber(5));
                empolyeeRegInfo1.setSex("1");
                empolyeeRegInfo1.setTeleNo("1822236554"+i);
                empolyeeRegInfo.add(empolyeeRegInfo1);
            }
            jsonResult.setData(empolyeeRegInfo);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
                    jsonResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                else
                    jsonResult.setMsgInfo(e.getMessage());
                jsonResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                e.printStackTrace();
                jsonResult.setError(ErrorEnum.SERVER_EXCEPTION);
            }
            throw e;
        }
        return jsonResult;
    }


    @Override
    public RpcResult<Boolean> batchSave(List<CardEmpolyeeinfo> cardEmpolyeeinfos) {
        RpcResult<Boolean> result = new RpcResult<>();
        try {
            cardEmpolyeeModel.batchSave(cardEmpolyeeinfos);
            result.setData(true);
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
                e.printStackTrace();
                result.setError(ErrorEnum.SERVER_EXCEPTION);
            }
            throw e;
        }
        return result;
    }

    @Override
    public RpcResult<CardEmpolyeeinfo> getEmpolyeeRegInfoByRegId(String pkReginfo) {
        RpcResult<CardEmpolyeeinfo> result = new RpcResult<>();
        try {
            result.setData(cardEmpolyeeModel.getEmpolyeeRegInfoByRegId(pkReginfo));
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
                e.printStackTrace();
                result.setError(ErrorEnum.SERVER_EXCEPTION);
            }
            throw e;
        }
        return result;
    }

    @Override
    public RpcResult<Integer> save(CardEmpolyeeinfo cardEmpolyeeinfo) {
        RpcResult<Integer> result = new RpcResult<>();
        try {

            result.setData(cardEmpolyeeModel.save(cardEmpolyeeinfo));
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
                e.printStackTrace();
                result.setError(ErrorEnum.SERVER_EXCEPTION);
            }
            throw e;
        }
        return result;
    }

    @Override
    public RpcResult<Integer> update(CardEmpolyeeinfo cardEmpolyeeinfo) {
        RpcResult<Integer> result = new RpcResult<>();
        try {
            result.setData(cardEmpolyeeModel.update(cardEmpolyeeinfo));
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
                e.printStackTrace();
                result.setError(ErrorEnum.SERVER_EXCEPTION);
            }
            throw e;
        }
        return result;
    }

    //获得汉字名字
    public static String getName(){
        String name = "";
        int chineseNameNum = (int)(Math.random()*3 + 2);
        for(int i=1; i<=chineseNameNum;i++){
            name += getChinese();
        }
        return name;
    }

    //获得单个汉字
    public static String getChinese() {
        String chinese = "";
        int i = (int) (Math.random() * 40 + 16);
        int j = (int) (Math.random() * 94 + 1);
        if (i == 55) {
            j = (int) (Math.random() * 89 + 1);
        }
        byte[] bytes = {(byte) (i + 160), (byte) (j + 160)};
        try {
            chinese = new String(bytes, "gb2312");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chinese;
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("a","b","c");
        strList.stream().map(str-> str + str).collect(Collectors.toList()).forEach(System.out::println);

    }


}
