package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardCardinfoService;
import cn.wellcare.card.entity.CardCardinfo;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardCardinfoModel;
import cn.wellcare.pojo.RpcResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardCardinfoService implements ICardCardinfoService {

    @Resource
    private CardCardinfoModel cardCardinfoModel;

    @Override
    public RpcResult<Boolean> batchSave(List<CardCardinfo> cardCardinfos) {
        RpcResult<Boolean> result = new RpcResult<>();
        try {
            cardCardinfoModel.batchSave(cardCardinfos);
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
    public RpcResult<CardCardinfo> getCardCardinfByPkCardinfo(String pkReginfo) {
        RpcResult<CardCardinfo> result = new RpcResult<>();
        try {
            result.setData(cardCardinfoModel.getCardCardinfByPkCardinfo(pkReginfo));
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
    public RpcResult<Integer> save(CardCardinfo cardCardinfo) {
        RpcResult<Integer> result = new RpcResult<>();
        try {
            result.setData(cardCardinfoModel.save(cardCardinfo));
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
    public RpcResult<CardCardinfo> getCardCardinfByCardNo(String cardNo) {
        RpcResult<CardCardinfo> result = new RpcResult<>();
        try {
            result.setData(cardCardinfoModel.getCardCardinfByCardNo(cardNo));
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
    public RpcResult<Integer> update(CardCardinfo cardCardinfo) {
        RpcResult<Integer> result = new RpcResult<>();
        try {
            result.setData(cardCardinfoModel.update(cardCardinfo));
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
