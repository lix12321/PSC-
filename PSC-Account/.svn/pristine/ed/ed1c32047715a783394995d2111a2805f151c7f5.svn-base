package cn.wellcare.model.acc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.acc.entity.PscPiAccDetail;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.EnumerateParameter;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.acc.PscPiAccDetailDao;

@Component
public class PscPiAccDetailModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private PscPiAccDetailDao pscPiAccDetailDao;
    
    /**
     * 根据id取得psc_pi_acc_detail对象
     * @param  pscPiAccDetailId
     * @return
     */
    public PscPiAccDetail getPscPiAccDetailById(Integer pscPiAccDetailId) {
    	return pscPiAccDetailDao.get(pscPiAccDetailId);
    }
    /**
     * 根据账户主键取得psc_pi_acc_detail对象
     * @param
     * @return
     */
	public List<PscPiAccDetail> getPscPiAccDetailByPkPiacc(Integer pkPiacc, String startTime, String endTime) {
		Map<String, Object> map = new HashMap<>();
		map.put("pkPiacc", pkPiacc);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return pscPiAccDetailDao.getPiaccDetail(map);
    }

    /**
     * 保存psc_pi_acc_detail对象
     * @param  pscPiAccDetail
     * @return
     */
     public Integer savePscPiAccDetail(PscPiAccDetail pscPiAccDetail) {
     	this.dbConstrains(pscPiAccDetail);
     	Date nowTime = new Date();
     	pscPiAccDetail.setModityTime(nowTime);
     	pscPiAccDetail.setCreateTime(nowTime);
     	pscPiAccDetail.setDelFlag(EnumerateParameter.ZERO);
     	pscPiAccDetail.setDateHap(nowTime);
     	return pscPiAccDetailDao.save(pscPiAccDetail);
     }
     
     /**
     * 更新psc_pi_acc_detail对象
     * @param  pscPiAccDetail
     * @return
     */
     public Integer updatePscPiAccDetail(PscPiAccDetail pscPiAccDetail) {
     	this.dbConstrains(pscPiAccDetail);
     	Date nowTime = new Date();
     	pscPiAccDetail.setModityTime(nowTime);
     	return pscPiAccDetailDao.update(pscPiAccDetail);
     }
     
     private void dbConstrains(PscPiAccDetail pscPiAccDetail) {
		pscPiAccDetail.setAtmNo(CommonUtils.dbSafeString(pscPiAccDetail.getAtmNo(), true, 30));
		pscPiAccDetail.setDelFlag(CommonUtils.dbSafeString(pscPiAccDetail.getDelFlag(), true, 1));
		pscPiAccDetail.setEuOptype(CommonUtils.dbSafeString(pscPiAccDetail.getEuOptype(), true, 2));
		pscPiAccDetail.setModifier(CommonUtils.dbSafeString(pscPiAccDetail.getModifier(), true, 32));
		pscPiAccDetail.setNameEmpOpera(CommonUtils.dbSafeString(pscPiAccDetail.getNameEmpOpera(), true, 120));
		pscPiAccDetail.setPkDepopi(CommonUtils.dbSafeString(pscPiAccDetail.getPkDepopi(), true, 32));
		pscPiAccDetail.setPkEmpOpera(CommonUtils.dbSafeString(pscPiAccDetail.getPkEmpOpera(), true, 32));
     }
     
    public List<PscPiAccDetail> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pscPiAccDetailDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<PscPiAccDetail> list = pscPiAccDetailDao.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return pscPiAccDetailDao.del(id);
     }
     

}