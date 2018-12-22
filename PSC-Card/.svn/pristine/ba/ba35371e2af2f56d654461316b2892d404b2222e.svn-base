package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardRegInfoService;
import cn.wellcare.card.bo.card.CardRegInfoBo;
import cn.wellcare.card.entity.CardReginfo;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardRegInfoModel;
import cn.wellcare.pojo.RpcResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("cardRegInfoService")
public class CardRegInfoService implements ICardRegInfoService {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private CardRegInfoModel cardRegInfoModel;

    /**
     * 获取发卡登记信息
     * @param paramMap
     * @return
     */
    @Override
    public RpcResult<List<CardRegInfoBo>> getCardRegInfo(Map<String, Object> paramMap) {
        RpcResult<List<CardRegInfoBo>> result = new RpcResult<>();
        try {
            List<CardRegInfoBo> resultList = cardRegInfoModel.getCardRegInfo(paramMap);
            result.setData(resultList);
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
    public RpcResult<Boolean> batchSave(List<CardReginfo> cardReginfos) {
        RpcResult<Boolean> result = new RpcResult<>();
        try {
            cardRegInfoModel.batchSave(cardReginfos);
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
    public RpcResult<List<CardReginfo>> getAllCardRegInfo() {
        RpcResult<List<CardReginfo>> result = new RpcResult<>();
        try {
            List<CardReginfo> resultList = cardRegInfoModel.getAllCardRegInfo();
            result.setData(resultList);
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
    public RpcResult<CardReginfo> getCardRegInfoByIdNo(String idNo) {
        RpcResult<CardReginfo> result = new RpcResult<>();
        try {
            result.setData(cardRegInfoModel.getCardRegInfoByIdNo(idNo));
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
    public RpcResult<Integer> save(CardReginfo cardReginfo) {
        RpcResult<Integer> result = new RpcResult<>();
        try {
            result.setData(cardRegInfoModel.save(cardReginfo));
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
    public RpcResult<CardReginfo> getCardRegInfoByCardNo(String cardNo) {
        RpcResult<CardReginfo> result = new RpcResult<>();
        try {
            result.setData(cardRegInfoModel.getCardRegInfoByCardNo(cardNo));
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
    public RpcResult<CardReginfo> getCardRegInfoByCardRegCode(String cardRegCode) {
        RpcResult<CardReginfo> result = new RpcResult<>();
        try {
            result.setData(cardRegInfoModel.getCardRegInfoByCardRegCode(cardRegCode));
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

}


