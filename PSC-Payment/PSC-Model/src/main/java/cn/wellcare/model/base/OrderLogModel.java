package cn.wellcare.model.base;

import javax.annotation.Resource;

import cn.wellcare.dao.log.PayOrderLogDao;
import cn.wellcare.entity.log.PayOrderLog;
import org.springframework.stereotype.Component;

@Component
public class OrderLogModel {
	@Resource
	private PayOrderLogDao orderLogDao;

	public int insert(PayOrderLog log) {
		return this.orderLogDao.save(log);
	}
}
