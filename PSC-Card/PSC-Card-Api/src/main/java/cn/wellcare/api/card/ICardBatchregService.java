package cn.wellcare.api.card;

import cn.wellcare.card.entity.CardBatchreg;
import cn.wellcare.pojo.RpcResult;

public interface ICardBatchregService {

    RpcResult<Integer> save(CardBatchreg cardBatchreg);
}
