package cn.wellcare.model.acc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.acc.entity.PscPiAccCredit;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.EnumerateParameter;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.acc.PscPiAccCreditDao;

@Component
public class PscPiAccCreditModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PscPiAccCreditDao pscPiAccCreditDao;
    
    /**
     * 根据id取得psc_pi_acc_credit对象
     * @param  pscPiAccCreditId
     * @return
     */
    public PscPiAccCredit getPscPiAccCreditById(Integer pscPiAccCreditId) {
    	return pscPiAccCreditDao.get(pscPiAccCreditId);
    }
    /**
     * 根据账户id取得psc_pi_acc_credit对象
     * @param
     * @return
     */
    public List<PscPiAccCredit> getPscPiAccCreditByPkPiacc(Integer pkPiacc) {
    	return pscPiAccCreditDao.getCredit(pkPiacc);
    }

    /**
     * 保存psc_pi_acc_credit对象
     * @param  pscPiAccCredit
     * @return
     */
     public Integer savePscPiAccCredit(PscPiAccCredit pscPiAccCredit) {
            this.dbConstrains(pscPiAccCredit);
            Date nowTime = new Date();
            pscPiAccCredit.setModityTime(nowTime);
            pscPiAccCredit.setCreateTime(nowTime);
            pscPiAccCredit.setDelFlag(EnumerateParameter.ZERO);
            pscPiAccCredit.setDateOpera(nowTime);
     	return pscPiAccCreditDao.save(pscPiAccCredit);
     }
     
     /**
     * 更新psc_pi_acc_credit对象
     * @param  pscPiAccCredit
     * @return
     */
     public Integer updatePscPiAccCredit(PscPiAccCredit pscPiAccCredit) {
     	this.dbConstrains(pscPiAccCredit);
     	Date nowTime = new Date();
     	pscPiAccCredit.setModityTime(nowTime);
     	return pscPiAccCreditDao.update(pscPiAccCredit);
     }
     
     private void dbConstrains(PscPiAccCredit pscPiAccCredit) {
		pscPiAccCredit.setChkInfo(CommonUtils.dbSafeString(pscPiAccCredit.getChkInfo(), true, 256));
		pscPiAccCredit.setCreator(CommonUtils.dbSafeString(pscPiAccCredit.getCreator(), true, 32));
		pscPiAccCredit.setDelFlag(CommonUtils.dbSafeString(pscPiAccCredit.getDelFlag(), true, 1));
		pscPiAccCredit.setEuDirect(CommonUtils.dbSafeString(pscPiAccCredit.getEuDirect(), true, 2));
		pscPiAccCredit.setModifier(CommonUtils.dbSafeString(pscPiAccCredit.getModifier(), true, 32));
		pscPiAccCredit.setNameEmpChk(CommonUtils.dbSafeString(pscPiAccCredit.getNameEmpChk(), true, 40));
		pscPiAccCredit.setNameEmpOpera(CommonUtils.dbSafeString(pscPiAccCredit.getNameEmpOpera(), true, 120));
		pscPiAccCredit.setNote(CommonUtils.dbSafeString(pscPiAccCredit.getNote(), true, 128));
		pscPiAccCredit.setPkEmpChk(CommonUtils.dbSafeString(pscPiAccCredit.getPkEmpChk(), true, 32));
		pscPiAccCredit.setPkEmpOpera(CommonUtils.dbSafeString(pscPiAccCredit.getPkEmpOpera(), true, 32));
		pscPiAccCredit.setPkOrg(CommonUtils.dbSafeString(pscPiAccCredit.getPkOrg(), true, 32));
		pscPiAccCredit.setPkPi(CommonUtils.dbSafeString(pscPiAccCredit.getPkPi(), true, 32));
     }
     
    public List<PscPiAccCredit> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pscPiAccCreditDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PscPiAccCredit> list = pscPiAccCreditDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return pscPiAccCreditDao.del(id);
     }
     
     
}