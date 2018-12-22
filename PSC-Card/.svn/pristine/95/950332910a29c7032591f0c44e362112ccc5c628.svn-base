package cn.wellcare.model.card;

import cn.wellcare.card.entity.CardBatchreg;
import cn.wellcare.dao.card.CardBatchregDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CardBatchregModel {

    @Resource
    private CardBatchregDao cardBatchregDao;

    public Integer save(CardBatchreg cardBatchreg) {
        return cardBatchregDao.save(cardBatchreg);
    }
}
