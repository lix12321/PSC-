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

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.HttpJsonResult;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.core.utils.WebUtil;
import cn.wellcare.entity.payset.PaySettingAlipay;
import cn.wellcare.entity.system.SystemUsers;
import cn.wellcare.payment.modules.payset.IPaySettingAlipayService;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.SessionManager;

/**
 * 支付设置-支付宝相关action
 *                       
 * @Filename: PaySettingAlipay.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "paysettingalipay")
public class PaySettingAlipayController  {
    @Resource
    private IPaySettingAlipayService paySettingAlipayService;

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
        return "pay/alipaysetlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PaySettingAlipay>> list(HttpServletRequest request,
                                                                       ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        queryMap.put("orgId",adminUser.getId());
        RpcResult<List<PaySettingAlipay>> serviceResult = paySettingAlipayService.page(
            queryMap, pager);
        if (!serviceResult.isSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<PaySettingAlipay>> jsonResult = new HttpJsonResult<List<PaySettingAlipay>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
        return "pay/paysettingalipayadd";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            PaySettingAlipay paySettingAlipay = paySettingAlipayService.getPaySettingAlipayById(id)
                .getData();
            dataMap.put("obj", paySettingAlipay);
        }

        return "pay/paysettingalipayedit";
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
                                                       PaySettingAlipay paySettingAlipay) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        RpcResult<Integer> serviceResult = null;
        try {
            if (paySettingAlipay.getId() != null && 0 != paySettingAlipay.getId()) {
                serviceResult = paySettingAlipayService.updatePaySettingAlipay(paySettingAlipay);
            } else {
                if (null != adminUser){
					paySettingAlipay.setOrgId(adminUser.getOrgId());
                }
                serviceResult = paySettingAlipayService.savePaySettingAlipay(paySettingAlipay);
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
            RpcResult<Boolean> serviceResult = paySettingAlipayService.del(id);
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
