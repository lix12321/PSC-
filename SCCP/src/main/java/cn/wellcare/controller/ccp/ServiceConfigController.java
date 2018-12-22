package cn.wellcare.controller.ccp;

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
import cn.wellcare.entity.ccp.ServiceConfig;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.service.ccp.ServiceConfigService;
import cn.wellcare.utils.HttpJsonResult;
import cn.wellcare.utils.PagerInfo;
import cn.wellcare.utils.WebUtil;

/**
 * 通道配置相关action
 *                       
 * @Filename: ServiceConfig.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "ccp/config")
public class ServiceConfigController  {
    @Resource
	private ServiceConfigService serviceConfigService;

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
		return "ccp/config/serviceconfiglist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ServiceConfig>> list(HttpServletRequest request,
                                                                         ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResponse<List<ServiceConfig>> serviceResult = serviceConfigService.page(
            queryMap, pager);
		if (!serviceResult.getSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<ServiceConfig>> jsonResult = new HttpJsonResult<List<ServiceConfig>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
		return "ccp/config/serviceconfigadd";
	}

	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            ServiceConfig serviceConfig = serviceConfigService.getServiceConfigById(id)
                .getData();
            dataMap.put("obj", serviceConfig);
        }

		return "ccp/config/serviceconfigedit";
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
                                                       ServiceConfig serviceConfig) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		ServiceResponse<Integer> serviceResult = null;
        try {
            if (serviceConfig.getId() != null && 0 != serviceConfig.getId()) {
                serviceResult = serviceConfigService.updateServiceConfig(serviceConfig);
            } else {
                serviceResult = serviceConfigService.saveServiceConfig(serviceConfig);
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
			ServiceResponse<Boolean> serviceResult = serviceConfigService.del(id);
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
