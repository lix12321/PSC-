package cn.wellcare.admin.controller.notify;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.bo.PayNotifyBO;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.HttpJsonResult;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.core.utils.WebUtil;
import cn.wellcare.entity.system.SystemUsers;
import cn.wellcare.payment.modules.notify.IPayNotifyLog;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.SessionManager;

@Controller
@RequestMapping("paynotifylog")
public class PayNotifyLogController {

	@Resource
	private IPayNotifyLog iPayNotifyLog;

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
		return "notify/paynotifylist";
	}

	/**
	 * gridDatalist数据
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public @ResponseBody HttpJsonResult<List<PayNotifyBO>> list(HttpServletRequest request, ModelMap dataMap) {
		Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		// 获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
		queryMap.put("orgId", adminUser.getId());
		RpcResult<List<PayNotifyBO>> serviceResult = iPayNotifyLog.page(queryMap, pager);
		if (!serviceResult.isSuccess()) {
			if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
		}

		HttpJsonResult<List<PayNotifyBO>> jsonResult = new HttpJsonResult<List<PayNotifyBO>>();
		jsonResult.setRows(serviceResult.getData());
		jsonResult.setTotal(pager.getRowsCount());

		return jsonResult;
	}

}
