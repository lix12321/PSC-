package cn.wellcare.admin.controller.payset;

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

import cn.wellcare.bo.PayStrategyBO;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.HttpJsonResult;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.core.utils.WebUtil;
import cn.wellcare.entity.payset.PayStrategy;
import cn.wellcare.entity.system.SystemUsers;
import cn.wellcare.payment.modules.payset.IPayStrategyService;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.SessionManager;

/**
 * 支付策略相关action
 *                       
 * @Filename: PayStrategy.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "paystrategy")
public class PayStrategyController  {
    @Resource
    private IPayStrategyService payStrategyService;

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
        return "pay/paystrategylist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PayStrategyBO>> list(HttpServletRequest request,
                                                                ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        queryMap.put("orgId",adminUser.getId());
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        RpcResult<List<PayStrategyBO>> serviceResult = payStrategyService.page(
            queryMap, pager);
        if (!serviceResult.isSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<PayStrategyBO>> jsonResult = new HttpJsonResult<List<PayStrategyBO>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        if (null != adminUser){
			// dataMap.put("orgName", adminUser.getOrgName());
        }
        return "pay/paystrategyadd";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        if (null != adminUser){
			// dataMap.put("orgName", adminUser.getOrgName());
        }
        if (id != null) {
            PayStrategy payStrategy = payStrategyService.getPayStrategyById(id)
                .getData();
            dataMap.put("obj", payStrategy);
        }

        return "pay/paystrategyedit";
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
                                                       PayStrategy payStrategy) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        RpcResult<Integer> serviceResult = null;
        try {
            if (payStrategy.getId() != null && 0 != payStrategy.getId()) {
                serviceResult = payStrategyService.updatePayStrategy(payStrategy);
            } else {
                if ( null != adminUser){
                    payStrategy.setOrgId(adminUser.getId());
                }
                serviceResult = payStrategyService.savePayStrategy(payStrategy);
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
            RpcResult<Boolean> serviceResult = payStrategyService.del(id);
           if (!serviceResult.isSuccess()) {
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
