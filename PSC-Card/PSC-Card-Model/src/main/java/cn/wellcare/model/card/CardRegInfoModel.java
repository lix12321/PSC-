package cn.wellcare.model.card;

import cn.wellcare.card.bo.card.CardRegInfoBo;
import cn.wellcare.card.entity.CardReginfo;
import cn.wellcare.dao.card.CardReginfoDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CardRegInfoModel {

    @Resource
    private CardReginfoDao cardReginfoDao;

    public List<CardRegInfoBo> getCardRegInfo(Map<String, Object> paramMap) {
        return cardReginfoDao.getCardRegInfo(paramMap);
    }

    public CardReginfo getCardRegInfoByIdNo(String idNo) {
        return cardReginfoDao.getCardRegInfoByIdNo(idNo);
    }

    public int save(CardReginfo cardReginfo) {
        return cardReginfoDao.save(cardReginfo);
    }

    public int update(CardReginfo cardReginfo) {
        return cardReginfoDao.update(cardReginfo);
    }

    public int updateByIdNo(CardReginfo cardReginfo) {
        return cardReginfoDao.updateByIdNo(cardReginfo);
    }

    public CardReginfo getCardRegInfoByRegCode(String cardRegCode) {
        return cardReginfoDao.getCardRegInfoByRegCode(cardRegCode);
    }

    public void batchSave(List<CardReginfo> cardReginfos) {
        cardReginfoDao.batchSave(cardReginfos);
    }

    public CardReginfo getCardRegInfoByCardNo(String cardNo) {
        return cardReginfoDao.getCardRegInfoByCardNo(cardNo);
    }

    public CardReginfo getCardRegInfoByCardRegCode(String cardRegCode) {
        if (StringUtils.isEmpty(cardRegCode)){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("codeReg",cardRegCode);
        List<CardReginfo> cardReginfos = cardReginfoDao.getList(map);
        if (cardReginfos == null || cardReginfos.isEmpty()){
            return null;
        }
        return cardReginfos.get(0);
    }

    public List<CardReginfo> getAllCardRegInfo() {
        return cardReginfoDao.getList(new HashMap<>());
    }
}
