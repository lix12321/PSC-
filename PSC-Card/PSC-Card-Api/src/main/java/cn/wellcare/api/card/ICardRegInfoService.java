package cn.wellcare.api.card;

import cn.wellcare.card.bo.card.CardRegInfoBo;
import cn.wellcare.card.entity.CardReginfo;
import cn.wellcare.pojo.RpcResult;

import java.util.List;
import java.util.Map;

public interface ICardRegInfoService {

    /**
     * 获取发卡登记信息
     * @param paramMap
     * @return
     */
	RpcResult<List<CardRegInfoBo>> getCardRegInfo(Map<String, Object> paramMap);

    RpcResult<Boolean> batchSave(List<CardReginfo> cardReginfos);

    RpcResult<List<CardReginfo>> getAllCardRegInfo();

    RpcResult<CardReginfo> getCardRegInfoByIdNo(String idNo);

    RpcResult<Integer> save(CardReginfo cardReginfo);

    RpcResult<CardReginfo> getCardRegInfoByCardNo(String cardNo);

    RpcResult<CardReginfo> getCardRegInfoByCardRegCode(String cardRegCode);
}
