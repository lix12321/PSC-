package cn.wellcare.api.card;

import cn.wellcare.card.bo.card.EmpolyeeRegInfo;
import cn.wellcare.card.entity.CardEmpolyeeinfo;
import cn.wellcare.pojo.RpcResult;

import java.util.List;
import java.util.Map;

public interface ICardEmpolyeeService {

	RpcResult<List<EmpolyeeRegInfo>> getEmpolyeeRegInfo(Map<String, Object> paramMap);

    RpcResult<Boolean> batchSave(List<CardEmpolyeeinfo> cardEmpolyeeinfos);

	RpcResult<CardEmpolyeeinfo> getEmpolyeeRegInfoByRegId(String pkReginfo);

	RpcResult<Integer> save(CardEmpolyeeinfo cardEmpolyeeinfo);

	RpcResult<Integer> update(CardEmpolyeeinfo cardEmpolyeeinfo);

}
