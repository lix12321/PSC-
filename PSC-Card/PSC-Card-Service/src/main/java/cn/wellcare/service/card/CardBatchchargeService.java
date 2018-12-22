package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardBatchchargeService;
import cn.wellcare.card.entity.CardBatchcharge;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardBatchchargeModel;
import cn.wellcare.pojo.RpcResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CardBatchchargeService implements ICardBatchchargeService {

    @Resource
    private CardBatchchargeModel cardBatchchargeModel;

    @Override
    public RpcResult<Integer> save(CardBatchcharge cardBatchcharge) {
        RpcResult<Integer> result = new RpcResult<>();
        try {
            result.setData(cardBatchchargeModel.save(cardBatchcharge));
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
