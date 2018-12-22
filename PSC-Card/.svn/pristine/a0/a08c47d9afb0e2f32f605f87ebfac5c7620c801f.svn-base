package cn.wellcare.api.card;

import cn.wellcare.card.entity.CardCardinfo;
import cn.wellcare.pojo.RpcResult;

import java.util.List;

public interface ICardCardinfoService {

    RpcResult<Boolean> batchSave(List<CardCardinfo> cardCardinfos);

    RpcResult<CardCardinfo> getCardCardinfByPkCardinfo(String pkReginfo);

    RpcResult<Integer> save(CardCardinfo cardCardinfo);

    RpcResult<CardCardinfo> getCardCardinfByCardNo(String cardNo);

    RpcResult<Integer> update(CardCardinfo cardCardinfo);
}
