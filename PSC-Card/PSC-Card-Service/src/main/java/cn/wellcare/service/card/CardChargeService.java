package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardChargeService;
import cn.wellcare.card.entity.CardCharge;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardChargeModel;
import cn.wellcare.pojo.RpcResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardChargeService implements ICardChargeService {

    @Resource
    private CardChargeModel cardChargeModel;

    @Override
    public RpcResult<Boolean> batchSave(List<CardCharge> chargeList) {
        RpcResult<Boolean> result = new RpcResult<>();
        try {
            cardChargeModel.batchSave(chargeList);
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
