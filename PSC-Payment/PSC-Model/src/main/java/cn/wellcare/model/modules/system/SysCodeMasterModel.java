package cn.wellcare.model.modules.system;

import cn.wellcare.dao.system.SysCodeMasterDao;
import cn.wellcare.entity.system.SysCodeMaster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;

@Component
public class SysCodeMasterModel {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private SysCodeMasterDao sysCodeMasterDao;
    
    /**
     * 根据id取得数据字典
     * @param  codeDiv
     * @param  codeCd
     * @return
     */
    public SysCodeMaster getSysCodeMasterById(String codeDiv,String codeCd) {
    	return sysCodeMasterDao.get(codeDiv,codeCd);
    }
    
    /**
     * 保存数据字典
     * @param  sysCodeMaster
     * @return
     */
     public Integer saveSysCodeMaster(SysCodeMaster sysCodeMaster) {
     	this.dbConstrains(sysCodeMaster);
     	return sysCodeMasterDao.save(sysCodeMaster);
     }
     
     /**
     * 更新数据字典
     * @param  sysCodeMaster
     * @return
     */
     public Integer updateSysCodeMaster(SysCodeMaster sysCodeMaster) {
     	this.dbConstrains(sysCodeMaster);
     	return sysCodeMasterDao.update(sysCodeMaster);
     }
     
     private void dbConstrains(SysCodeMaster sysCodeMaster) {
		sysCodeMaster.setCodeCd(CommonUtils.dbSafeString(sysCodeMaster.getCodeCd(), false, 16));
		sysCodeMaster.setCodeDiv(CommonUtils.dbSafeString(sysCodeMaster.getCodeDiv(), false, 16));
		sysCodeMaster.setCodeText(CommonUtils.dbSafeString(sysCodeMaster.getCodeText(), true, 16));
     }
     
    public List<SysCodeMaster> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sysCodeMasterDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<SysCodeMaster> list = sysCodeMasterDao.getList(param);
        return list;
    }
     
      public Integer del(String codeDiv,String codeCd) {
        return sysCodeMasterDao.del(codeDiv,codeCd);
     }
     
     
}