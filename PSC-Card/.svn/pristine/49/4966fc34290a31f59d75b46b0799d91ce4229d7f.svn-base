package cn.wellcare.model.card;

import cn.wellcare.card.entity.CardBatchregDetail;
import cn.wellcare.dao.card.CardBatchregDetailDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CardBatchregDetailModel {

    @Resource
    private CardBatchregDetailDao cardBatchregDetailDao;

    public void batchSave(List<CardBatchregDetail> cardBatchregDetails) {
        cardBatchregDetailDao.batchSave(cardBatchregDetails);
    }
}
