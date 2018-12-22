package cn.wellcare.model.card;

import cn.wellcare.card.entity.CardOperate;
import cn.wellcare.dao.card.CardOperateDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CardOperateModel {

    @Resource
    private CardOperateDao cardOperateDao;

    public int save(CardOperate cardOperate) {
        return cardOperateDao.save(cardOperate);
    }
}
