package cn.wellcare.model.card;

import cn.wellcare.card.entity.CardCharge;
import cn.wellcare.dao.card.CardChargeDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CardChargeModel {

    @Resource
    private CardChargeDao cardChargeDao;

    public void batchSave(List<CardCharge> chargeList) {
        cardChargeDao.batchSave(chargeList);
    }
}
