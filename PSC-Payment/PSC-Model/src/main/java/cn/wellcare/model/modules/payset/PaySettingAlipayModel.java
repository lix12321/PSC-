package cn.wellcare.model.modules.payset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.payset.PaySettingAlipayDao;
import cn.wellcare.entity.payset.PaySettingAlipay;

@Component
public class PaySettingAlipayModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PaySettingAlipayDao paySettingAlipayDao;
    
    /**
     * 根据id取得支付设置-支付宝
     * @param  paySettingAlipayId
     * @return
     */
    public PaySettingAlipay getPaySettingAlipayById(Integer paySettingAlipayId) {
    	return paySettingAlipayDao.get(paySettingAlipayId);
    }
    
    /**
     * 保存支付设置-支付宝
     * @param  paySettingAlipay
     * @return
     */
     public Integer savePaySettingAlipay(PaySettingAlipay paySettingAlipay) {
     	this.dbConstrains(paySettingAlipay);
     	return paySettingAlipayDao.save(paySettingAlipay);
     }
     
     /**
     * 更新支付设置-支付宝
     * @param  paySettingAlipay
     * @return
     */
     public Integer updatePaySettingAlipay(PaySettingAlipay paySettingAlipay) {
     	this.dbConstrains(paySettingAlipay);
     	return paySettingAlipayDao.update(paySettingAlipay);
     }
     
     private void dbConstrains(PaySettingAlipay paySettingAlipay) {
		paySettingAlipay.setAppid(CommonUtils.dbSafeString(paySettingAlipay.getAppid(), true, 32));
		paySettingAlipay.setInputCharset(CommonUtils.dbSafeString(paySettingAlipay.getInputCharset(), true, 6));
		paySettingAlipay.setMcloudApiDomain(CommonUtils.dbSafeString(paySettingAlipay.getMcloudApiDomain(), true, 32));
		paySettingAlipay.setOpenApiDomain(CommonUtils.dbSafeString(paySettingAlipay.getOpenApiDomain(), true, 32));
		paySettingAlipay.setOrderName(CommonUtils.dbSafeString(paySettingAlipay.getOrderName(), false, 32));
		paySettingAlipay.setPartner(CommonUtils.dbSafeString(paySettingAlipay.getPartner(), true, 32));
		paySettingAlipay.setPid(CommonUtils.dbSafeString(paySettingAlipay.getPid(), true, 32));
		paySettingAlipay.setSellerEmail(CommonUtils.dbSafeString(paySettingAlipay.getSellerEmail(), true, 32));
		paySettingAlipay.setSignType(CommonUtils.dbSafeString(paySettingAlipay.getSignType(), true, 8));
		paySettingAlipay.setTimeout(CommonUtils.dbSafeString(paySettingAlipay.getTimeout(), true, 4));
     }
     
    public List<PaySettingAlipay> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(paySettingAlipayDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PaySettingAlipay> list = paySettingAlipayDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return paySettingAlipayDao.del(id);
     }

	public PaySettingAlipay getByOrg(Integer org) {
		return paySettingAlipayDao.getByOrg(org);
	}
     
     
}