package cn.wellcare.model.modules.payset;

import cn.wellcare.dao.payset.PaySettingIntegrationDao;
import cn.wellcare.entity.payset.PaySettingIntegration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;

@Component
public class PaySettingIntegrationModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PaySettingIntegrationDao paySettingIntegrationDao;
    
    /**
     * 根据id取得支付设置-建行聚合支付
     * @param  paySettingIntegrationId
     * @return
     */
    public PaySettingIntegration getPaySettingIntegrationById(Integer paySettingIntegrationId) {
    	return paySettingIntegrationDao.get(paySettingIntegrationId);
    }
    
    /**
     * 保存支付设置-建行聚合支付
     * @param  paySettingIntegration
     * @return
     */
     public Integer savePaySettingIntegration(PaySettingIntegration paySettingIntegration) {
     	this.dbConstrains(paySettingIntegration);
     	return paySettingIntegrationDao.save(paySettingIntegration);
     }
     
     /**
     * 更新支付设置-建行聚合支付
     * @param  paySettingIntegration
     * @return
     */
     public Integer updatePaySettingIntegration(PaySettingIntegration paySettingIntegration) {
     	this.dbConstrains(paySettingIntegration);
     	return paySettingIntegrationDao.update(paySettingIntegration);
     }
     
     private void dbConstrains(PaySettingIntegration paySettingIntegration) {
		paySettingIntegration.setBankId(CommonUtils.dbSafeString(paySettingIntegration.getBankId(), true, 16));
		paySettingIntegration.setBankUrl(CommonUtils.dbSafeString(paySettingIntegration.getBankUrl(), true, 32));
		paySettingIntegration.setCurcode(CommonUtils.dbSafeString(paySettingIntegration.getCurcode(), true, 4));
		paySettingIntegration.setCustid(CommonUtils.dbSafeString(paySettingIntegration.getCustid(), true, 32));
		paySettingIntegration.setMerchTid(CommonUtils.dbSafeString(paySettingIntegration.getMerchTid(), true, 32));
		paySettingIntegration.setOrderName(CommonUtils.dbSafeString(paySettingIntegration.getOrderName(), true, 32));
		paySettingIntegration.setPosId(CommonUtils.dbSafeString(paySettingIntegration.getPosId(), true, 16));
		paySettingIntegration.setPub32tr2(CommonUtils.dbSafeString(paySettingIntegration.getPub32tr2(), true, 32));
		paySettingIntegration.setPublicKey(CommonUtils.dbSafeString(paySettingIntegration.getPublicKey(), true, 255));
		paySettingIntegration.setPwd(CommonUtils.dbSafeString(paySettingIntegration.getPwd(), true, 32));
		paySettingIntegration.setReturnType(CommonUtils.dbSafeString(paySettingIntegration.getReturnType(), true, 2));
		paySettingIntegration.setTxcode(CommonUtils.dbSafeString(paySettingIntegration.getTxcode(), true, 8));
		paySettingIntegration.setUserId(CommonUtils.dbSafeString(paySettingIntegration.getUserId(), true, 32));
     }
     
    public List<PaySettingIntegration> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(paySettingIntegrationDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PaySettingIntegration> list = paySettingIntegrationDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return paySettingIntegrationDao.del(id);
     }
     
     
}