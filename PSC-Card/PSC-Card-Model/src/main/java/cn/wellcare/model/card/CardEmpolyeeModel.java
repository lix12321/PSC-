package cn.wellcare.model.card;

import cn.wellcare.card.bo.card.EmpolyeeRegInfo;
import cn.wellcare.card.entity.CardEmpolyeeinfo;
import cn.wellcare.dao.card.CardEmpolyeeinfoDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CardEmpolyeeModel {

    @Resource
    private CardEmpolyeeinfoDao cardEmpolyeeinfoDao;

    public List<EmpolyeeRegInfo> getEmpolyeeRegInfo(Map<String, Object> paramMap) {
        return cardEmpolyeeinfoDao.getEmpolyeeRegInfo(paramMap);
    }

    public void batchSave(List<CardEmpolyeeinfo> cardEmpolyeeinfos) {
        cardEmpolyeeinfoDao.batchSave(cardEmpolyeeinfos);
    }


    public int save(CardEmpolyeeinfo cardEmpolyeeinfo) {
        return cardEmpolyeeinfoDao.save(cardEmpolyeeinfo);
    }

    public CardEmpolyeeinfo getEmpolyeeRegInfoByRegId(String pkReginfo) {
        Map<String,Object> map = new HashMap<>();
        map.put("pkReginfo",pkReginfo);
        List<CardEmpolyeeinfo> cardEmpolyeeinfos = cardEmpolyeeinfoDao.getList(map);
        if (cardEmpolyeeinfos == null || cardEmpolyeeinfos.isEmpty()){
            return null;
        }
        return cardEmpolyeeinfos.get(0);
    }

    public Integer update(CardEmpolyeeinfo cardEmpolyeeinfo) {
        return cardEmpolyeeinfoDao.update(cardEmpolyeeinfo);
    }
}
