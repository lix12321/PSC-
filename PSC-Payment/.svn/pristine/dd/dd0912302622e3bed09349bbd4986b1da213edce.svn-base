package cn.wellcare.model.modules.notify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.bo.PayNotifyBO;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.log.PayNotifyLogDao;
import cn.wellcare.dao.notify.PayNotifyDao;

@Component
public class AdminPayNotifyLogModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
	private PayNotifyLogDao payNotifyLogDao;
	@Resource
	private PayNotifyDao payNotifyDao;


	public List<PayNotifyBO> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(queryMap);
		Integer start = 0, size = 0;
		if (pager != null) {
			pager.setRowsCount(payNotifyDao.getCount(param));
			start = pager.getStart();
			size = pager.getPageSize();
		}
		param.put("start", start);
		param.put("size", size);
		List<PayNotifyBO> list = payNotifyDao.getList(param);
		return list;
	}
     
     
}