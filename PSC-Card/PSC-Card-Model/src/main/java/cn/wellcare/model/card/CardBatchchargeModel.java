package cn.wellcare.model.card;

import cn.wellcare.card.entity.CardBatchcharge;
import cn.wellcare.dao.card.CardBatchchargeDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CardBatchchargeModel {

    @Resource
    private CardBatchchargeDao cardBatchchargeDao;

    public Integer save(CardBatchcharge cardBatchcharge) {
        return cardBatchchargeDao.save(cardBatchcharge);
    }
}
