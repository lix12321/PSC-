package cn.wellcare.service.card;

import cn.wellcare.api.card.ICardOperateService;
import cn.wellcare.card.entity.CardOperate;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.model.card.CardOperateModel;
import cn.wellcare.pojo.RpcResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CardOperateService implements ICardOperateService {

    @Resource
    private CardOperateModel cardOperateModel;
    @Override
    public RpcResult<Integer> save(CardOperate cardOperate) {
        RpcResult<Integer> result = new RpcResult<>();
        try {
            result.setData(cardOperateModel.save(cardOperate));
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
