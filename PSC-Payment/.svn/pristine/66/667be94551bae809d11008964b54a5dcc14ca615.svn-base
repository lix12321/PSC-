package cn.wellcare.model.modules.payset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.payset.PaySettingWechatDao;
import cn.wellcare.entity.payset.PaySettingWechat;

@Component
public class PaySettingWechatModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PaySettingWechatDao paySettingWechatDao;
    
    /**
     * 根据id取得支付设置-微信
     * @param  paySettingWechatId
     * @return
     */
    public PaySettingWechat getPaySettingWechatById(Integer paySettingWechatId) {
    	return paySettingWechatDao.get(paySettingWechatId);
    }
    
    /**
	 * 机构设置
	 * 
	 * @param paySettingWechatId
	 * @return
	 */
	public PaySettingWechat getByOrg(Integer paySettingWechatId) {
		return paySettingWechatDao.getByOrg(paySettingWechatId);
	}

	/**
	 * 保存支付设置-微信
	 * 
	 * @param paySettingWechat
	 * @return
	 */
     public Integer savePaySettingWechat(PaySettingWechat paySettingWechat) {
     	this.dbConstrains(paySettingWechat);
     	return paySettingWechatDao.save(paySettingWechat);
     }
     
     /**
     * 更新支付设置-微信
     * @param  paySettingWechat
     * @return
     */
     public Integer updatePaySettingWechat(PaySettingWechat paySettingWechat) {
     	this.dbConstrains(paySettingWechat);
     	return paySettingWechatDao.update(paySettingWechat);
     }
     
     private void dbConstrains(PaySettingWechat paySettingWechat) {
		paySettingWechat.setAccessToken(CommonUtils.dbSafeString(paySettingWechat.getAccessToken(), true, 255));
		paySettingWechat.setAppid(CommonUtils.dbSafeString(paySettingWechat.getAppid(), true, 32));
		paySettingWechat.setAppScope(CommonUtils.dbSafeString(paySettingWechat.getAppScope(), true, 16));
		paySettingWechat.setAppsecret(CommonUtils.dbSafeString(paySettingWechat.getAppsecret(), true, 64));
		paySettingWechat.setCodeAddr(CommonUtils.dbSafeString(paySettingWechat.getCodeAddr(), true, 128));
		paySettingWechat.setCreateOrderUrl(CommonUtils.dbSafeString(paySettingWechat.getCreateOrderUrl(), true, 255));
		paySettingWechat.setCustomerState(CommonUtils.dbSafeString(paySettingWechat.getCustomerState(), true, 16));
		paySettingWechat.setKey(CommonUtils.dbSafeString(paySettingWechat.getKey(), true, 64));
		paySettingWechat.setMchid(CommonUtils.dbSafeString(paySettingWechat.getMchid(), true, 32));
		paySettingWechat.setNotifyUrl(CommonUtils.dbSafeString(paySettingWechat.getNotifyUrl(), true, 255));
		paySettingWechat.setOauth2Token(CommonUtils.dbSafeString(paySettingWechat.getOauth2Token(), true, 255));
		paySettingWechat.setOauth2Url(CommonUtils.dbSafeString(paySettingWechat.getOauth2Url(), true, 255));
		paySettingWechat.setOrderName(CommonUtils.dbSafeString(paySettingWechat.getOrderName(), true, 32));
		paySettingWechat.setUserInfo(CommonUtils.dbSafeString(paySettingWechat.getUserInfo(), true, 255));
     }
     
    public List<PaySettingWechat> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(paySettingWechatDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PaySettingWechat> list = paySettingWechatDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return paySettingWechatDao.del(id);
     }
     
     
}