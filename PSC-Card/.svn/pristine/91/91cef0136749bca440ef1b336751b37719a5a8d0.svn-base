package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardBatchregDetailService;
import cn.wellcare.card.entity.CardBatchregDetail;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardBatchregDetailModel;
import cn.wellcare.pojo.RpcResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardBatchregDetailService implements ICardBatchregDetailService {

    @Resource
    private CardBatchregDetailModel cardBatchregDetailModel;

    @Override
    public RpcResult<Boolean> batchSave(List<CardBatchregDetail> cardBatchregDetails) {
        RpcResult<Boolean> result = new RpcResult<>();
        try {
            cardBatchregDetailModel.batchSave(cardBatchregDetails);
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
}
