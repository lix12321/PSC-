package cn.wellcare.model.card;

import cn.wellcare.card.entity.CardCardinfo;
import cn.wellcare.dao.card.CardCardinfoDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CardCardinfoModel {

    @Resource
    private CardCardinfoDao cardCardinfoDao;

    public int save(CardCardinfo cardCardinfo) {
        return cardCardinfoDao.save(cardCardinfo);
    }


    public CardCardinfo getCardCardinfByCardNo(String cardNo) {
        return cardCardinfoDao.getCardCardinfByCardNo(cardNo);
    }

    public CardCardinfo getCardCardinfByPkCardinfo(String pkReginfo) {
        return cardCardinfoDao.getCardCardinfByPkCardinfo(pkReginfo);
    }

    public int update(CardCardinfo cardCardinfo) {
        return cardCardinfoDao.update(cardCardinfo);
    }

    public void batchSave(List<CardCardinfo> cardCardinfos) {
        cardCardinfoDao.batchSave(cardCardinfos);
    }
}
