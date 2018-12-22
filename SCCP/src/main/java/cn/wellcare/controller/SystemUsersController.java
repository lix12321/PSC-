package cn.wellcare.controller;

import java.io.PrintWriter;
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
import cn.wellcare.entity.SystemRoles;
import cn.wellcare.entity.SystemUserRole;
import cn.wellcare.entity.SystemUsers;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.service.SystemResourcesRolesService;
import cn.wellcare.service.SystemRolesService;
import cn.wellcare.service.SystemUserRoleService;
import cn.wellcare.service.SystemUsersService;
import cn.wellcare.shiro.SessionManager;
import cn.wellcare.utils.HttpJsonResult;
import cn.wellcare.utils.PagerInfo;
import cn.wellcare.utils.WebUtil;

/**
 * 用户表相关action
 * 
 * @Filename: SystemRoles.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "systemusers")
public class SystemUsersController {
	@Resource
	private SystemUsersService systemUsersService;
	@Resource
	private SystemResourcesRolesService resourcesRolesService;

	@Resource
    private SystemRolesService systemRolesService;

	@Resource
    private SystemUserRoleService systemUserRoleService;

	/**
	 * 默认页面
	 * 
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
		dataMap.put("pageSize", Constants.DEFAULT_PAGE_SIZE);

		Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
		dataMap.put("queryMap", queryMap);
		return "system/systemuserslist";
	}

	/**
	 * gridDatalist数据
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public @ResponseBody HttpJsonResult<List<SystemUsers>> list(HttpServletRequest request, ModelMap dataMap) {
		Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResponse<List<SystemUsers>> serviceResult = systemUsersService.page(queryMap, pager);
		if (!serviceResult.getSuccess()) {
			if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
		}

		HttpJsonResult<List<SystemUsers>> jsonResult = new HttpJsonResult<List<SystemUsers>>();
		jsonResult.setRows(serviceResult.getData());
		jsonResult.setTotal(pager.getRowsCount());

		return jsonResult;
	}

	/**
	 * 保存角色资源(权限)
	 * 
	 * @param request
	 * @param response
	 * @param roleId
	 * @param resIds
	 */
	@RequestMapping(value = "saveRoleRes", method = { RequestMethod.POST })
	public void saveRoleRes(HttpServletRequest request, HttpServletResponse response, String roleId, String resIds) {
		response.setContentType("text/html;charset=utf-8");
		String msg = "";
		PrintWriter pw = null;

		String[] resArr = resIds.split(",");
		ServiceResponse<Boolean> serviceResult = new ServiceResponse<Boolean>();
		try {
			pw = response.getWriter();

			serviceResult = resourcesRolesService.saveAuthority(roleId, resArr);
			if (!serviceResult.getSuccess()) {
				if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
					throw new RuntimeException(serviceResult.getMsgInfo());
				} else {
					throw new BusinessException(serviceResult.getMsgInfo());
				}
			}
			msg = serviceResult.getMsgInfo();
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		pw.print(msg);
	}

	@RequestMapping(value = "edituser")
	public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
		if (id != null) {
			SystemUsers systemUsers = systemUsersService.getSystemUsersById(id).getData();
			dataMap.put("obj", systemUsers);
		}
		return "system/systemusersedit";
	}

	@RequestMapping(value = "role2Res")
	public String role2Res(HttpServletRequest request, Integer id, String rolesName, Map<String, Object> dataMap) {
		dataMap.put("id", id);
		dataMap.put("rolesName", rolesName);
		return "system/systemuserallotroles";
	}

	@RequestMapping(value = "adduser")
	public String addWin(HttpServletRequest request) throws Exception {
		return "system/systemusersadd";
	}

	/**
	 * 新增/编辑
	 * 
	 * @param request
	 * @param response
	 * @param cate
	 */
	@RequestMapping(value = "doAdd", method = { RequestMethod.POST })
	public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request, HttpServletResponse response,
													   SystemUsers systemUsers) {
		HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		ServiceResponse<Integer> serviceResult = null;
		try {
			if (systemUsers.getId() != null && 0 != systemUsers.getId()) {
				serviceResult = systemUsersService.updateSystemUsers(systemUsers);
			} else {
				serviceResult = systemUsersService.saveSystemUsers(systemUsers);
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
	 * 
	 * @param request
	 * @param response
	 * @param cate
	 */
	@RequestMapping(value = "del", method = { RequestMethod.GET })
	public @ResponseBody HttpJsonResult<Boolean> del(HttpServletRequest request, HttpServletResponse response,
			Integer id) {
		HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		try {
			ServiceResponse<Boolean> serviceResult = systemUsersService.del(id);
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

    @RequestMapping(value = "allotRole", method = { RequestMethod.GET })
    public String allotRole(HttpServletRequest request, ModelMap dataMap, Integer id,String loginName){
        if (id != null) {
            dataMap.put("id", id);
        }
        if (!StringUtils.isEmpty(loginName)){
            dataMap.put("loginName", loginName);
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
        return "system/systemuserallotroles";
    }

    @RequestMapping("saveRole")
    @ResponseBody
    public HttpJsonResult<Boolean> saveRole(HttpServletRequest request,SystemUserRole systemUserRole){
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        SystemUsers adminUser = SessionManager.getAdminUser(request);
	    if (null != systemUserRole){
            systemUserRole.setId(adminUser.getId());
        }
        try {
			ServiceResponse<Integer> result = systemUserRoleService.saveSystemUserRole(systemUserRole);
            jsonResult.setRows(result.getData() > 0);
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

}
