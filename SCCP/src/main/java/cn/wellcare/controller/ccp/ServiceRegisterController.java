package cn.wellcare.controller.ccp;

import java.util.HashMap;
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
import cn.wellcare.entity.ccp.ServiceRegister;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.service.ccp.ServiceConfigService;
import cn.wellcare.service.ccp.ServiceRegisterService;
import cn.wellcare.utils.HttpJsonResult;
import cn.wellcare.utils.PagerInfo;
import cn.wellcare.utils.RandomUtil;
import cn.wellcare.utils.WebUtil;

/**
 * service_register
 * 
 * @Filename: ServiceRegister.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "ccp")
public class ServiceRegisterController {
	@Resource
	private ServiceRegisterService serviceRegisterService;
	@Resource
	private ServiceConfigService serviceConfigService;
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
		return "ccp/reg/serviceregisterlist";
	}

	/**
	 * gridDatalist数据
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public @ResponseBody HttpJsonResult<List<ServiceRegister>> list(HttpServletRequest request, ModelMap dataMap) {
		Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResponse<List<ServiceRegister>> serviceResult = serviceRegisterService.page(queryMap, pager);
		if (!serviceResult.getSuccess()) {
			if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
		}

		HttpJsonResult<List<ServiceRegister>> jsonResult = new HttpJsonResult<List<ServiceRegister>>();
		jsonResult.setRows(serviceResult.getData());
		jsonResult.setTotal(pager.getRowsCount());

		return jsonResult;
	}

	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
		// TODO 服务号+请求方式+请求格式+是否缓存+随机码
		String transcode = "T1" + "1" + "1" + "1" + RandomUtil.randomNumberic(5);
		dataMap.put("transcode", transcode);

		Map<String,Object> param  = new HashMap<String, Object>();
		param.put("enable", ServiceConfig.ENABLE);
		ServiceResponse<List<ServiceConfig>> sr = serviceConfigService.page(param, null);
		dataMap.put("serverCodeList", sr.getData());
		return "ccp/reg/serviceregisteradd";
	}

	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
		if (id != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("enable", ServiceConfig.ENABLE);
			ServiceResponse<List<ServiceConfig>> sr = serviceConfigService.page(param, null);
			dataMap.put("serverCodeList", sr.getData());

			ServiceRegister serviceRegister = serviceRegisterService.getServiceRegisterById(id).getData();
			dataMap.put("obj", serviceRegister);
		}

		return "ccp/reg/serviceregisteredit";
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
			ServiceRegister serviceRegister) {
		HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
		ServiceResponse<Integer> serviceResult = null;
		try {
			if (serviceRegister.getId() != null && 0 != serviceRegister.getId()) {
				serviceResult = serviceRegisterService.updateServiceRegister(serviceRegister);
			} else {
				String transcode = "T" + serviceRegister.getServerCode() + serviceRegister.getRequestType()
						+ serviceRegister.getDataType() + serviceRegister.getCache() + RandomUtil.randomNumberic(5);
				serviceRegister.setTransCode(transcode);
				serviceResult = serviceRegisterService.saveServiceRegister(serviceRegister);
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
			ServiceResponse<Boolean> serviceResult = serviceRegisterService.del(id);
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
