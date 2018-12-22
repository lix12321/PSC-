package cn.wellcare.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.constant.Constants;
import cn.wellcare.entity.SysCodeMaster;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.pojo.CommonResponse;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.service.SysCodeMasterService;
import cn.wellcare.utils.HttpJsonResult;
import cn.wellcare.utils.PagerInfo;
import cn.wellcare.utils.WebUtil;

/**
 * 数据字典相关action
 *                       
 * @Filename: SysCodeMaster.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "syscodemaster")
public class SysCodeMasterController  {
    @Resource
    private SysCodeMasterService sysCodeMasterService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", Constants.DEFAULT_PAGE_SIZE);

        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "system/syscodemasterlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SysCodeMaster>> list(HttpServletRequest request,
                                             ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResponse<List<SysCodeMaster>> serviceResult = sysCodeMasterService.page(
            queryMap, pager);
		if (!serviceResult.getSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<SysCodeMaster>> jsonResult = new HttpJsonResult<List<SysCodeMaster>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
        return "system/syscodemasteradd";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, String codeDiv,String codeCd) {
        if (codeDiv != null&& codeCd != null) {
            SysCodeMaster sysCodeMaster = sysCodeMasterService.getSysCodeMasterById(codeDiv,codeCd)
                .getData();
            dataMap.put("obj", sysCodeMaster);
        }
        return "system/syscodemasteredit";
    }

    /**
     * 新增
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       SysCodeMaster sysCodeMaster) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		ServiceResponse<Integer> serviceResult = null;
        try {
            serviceResult = sysCodeMasterService.saveSysCodeMaster(sysCodeMaster);
            jsonResult.setRows(serviceResult.getData() > 0);
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 编辑
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "doedit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doedit(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       SysCodeMaster sysCodeMaster) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		ServiceResponse<Integer> serviceResult = null;
        try {

            serviceResult = sysCodeMasterService.updateSysCodeMaster(sysCodeMaster);
            jsonResult.setRows(serviceResult.getData() > 0);
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> del(HttpServletRequest request,
                                                     HttpServletResponse response, String codeDiv,String codeCd) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
			CommonResponse<Boolean> serviceResult = sysCodeMasterService.del(codeDiv, codeCd);
			if (!serviceResult.getSuccess()) {
                if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
					throw new RuntimeException(serviceResult.getMsgInfo());
				} else {
					throw new BusinessException(serviceResult.getMsgInfo());
				}
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

}
