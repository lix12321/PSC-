package cn.wellcare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.constant.Constants;
import cn.wellcare.entity.SysOrganization;
import cn.wellcare.entity.SystemRoles;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.pojo.CommonResponse;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.service.SysOrganizationService;
import cn.wellcare.service.SystemRolesService;
import cn.wellcare.utils.HttpJsonResult;
import cn.wellcare.utils.PagerInfo;
import cn.wellcare.utils.WebUtil;

/**
 * 机构相关action
 *                       
 * @Filename: SysOrganization.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "sysorganization")
public class SysOrganizationController  {
    @Resource
    private SysOrganizationService sysOrganizationService;

    @Resource
    private SystemRolesService systemRolesService;
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
        return "system/sysorganizationlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SysOrganization>> list(HttpServletRequest request,
                                               ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResponse<List<SysOrganization>> serviceResult = sysOrganizationService.page(
            queryMap, pager);
		if (!serviceResult.getSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<SysOrganization>> jsonResult = new HttpJsonResult<List<SysOrganization>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
        return "system/sysorganizationadd";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            SysOrganization sysOrganization = sysOrganizationService.getSysOrganizationById(id)
                .getData();
            dataMap.put("obj", sysOrganization);
        }
        return "system/sysorganizationedit";
    }

    @RequestMapping(value = "allotRole", method = { RequestMethod.GET })
    public String allotRole(HttpServletRequest request, ModelMap dataMap, Integer id,String orgName){
        if (id != null) {
            dataMap.put("id", id);
        }
        if (!StringUtils.isEmpty(orgName)){
            dataMap.put("orgName", orgName);
        }
		ServiceResponse<List<SystemRoles>> serviceResult = systemRolesService.page(new HashMap<String, Object>(), null);
		if (!serviceResult.getSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
                throw new RuntimeException(serviceResult.getMsgInfo());
            } else {
                throw new BusinessException(serviceResult.getMsgInfo());
            }
        }
        dataMap.put("roles", serviceResult.getData());
        return "system/sysorganizationallotroles";
    }

    /**
     * 新增/编辑
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       SysOrganization sysOrganization) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		ServiceResponse<Integer> serviceResult = null;
        try {
            if (sysOrganization.getId() != null && 0 != sysOrganization.getId()) {
                serviceResult = sysOrganizationService.updateSysOrganization(sysOrganization);
            } else {
                serviceResult = sysOrganizationService.saveSysOrganization(sysOrganization);
            }
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
                                                     HttpServletResponse response, Integer id) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
			CommonResponse<Boolean> serviceResult = sysOrganizationService.del(id);
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
