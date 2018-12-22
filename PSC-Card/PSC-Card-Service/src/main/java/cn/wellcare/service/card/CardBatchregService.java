package cn.wellcare.service.card;


import cn.wellcare.api.card.ICardBatchregService;
import cn.wellcare.card.entity.CardBatchreg;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardBatchregModel;
import cn.wellcare.pojo.RpcResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CardBatchregService implements ICardBatchregService {

    @Resource
    private CardBatchregModel cardBatchregModel;

    @Override
    public RpcResult<Integer> save(CardBatchreg cardBatchreg) {
        RpcResult<Integer> rpcResult = new RpcResult<>();
        try {
            rpcResult.setData(cardBatchregModel.save(cardBatchreg));
        }catch (Exception e){
            rpcResult.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
                    rpcResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                else
                    rpcResult.setMsgInfo(e.getMessage());
                rpcResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                e.printStackTrace();
                rpcResult.setError(ErrorEnum.SERVER_EXCEPTION);
            }
            throw e;
        }
        return rpcResult;
    }
}
