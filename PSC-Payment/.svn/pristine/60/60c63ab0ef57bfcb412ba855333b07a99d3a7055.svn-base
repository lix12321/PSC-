package cn.wellcare.admin.controller.order;

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
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.system.SystemUsers;
import cn.wellcare.payment.modules.order.IPayOrderService;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.SessionManager;

/**
 * 支付订单相关action
 *                       
 * @Filename: PayOrder.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "payorder")
public class PayOrderController  {
    @Resource
    private IPayOrderService payOrderService;

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
        return "order/payorderlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PayOrder>> list(HttpServletRequest request,
                                                             ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        queryMap.put("orgId",adminUser.getId());
        RpcResult<List<PayOrder>> serviceResult = payOrderService.page(
            queryMap, pager);
        if (!serviceResult.isSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<PayOrder>> jsonResult = new HttpJsonResult<List<PayOrder>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, ModelMap dataMap, Integer id) {
        return "order/payorderadd";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            PayOrder payOrder = payOrderService.getPayOrderById(id)
                .getData();
            dataMap.put("obj", payOrder);
        }
        return "order/payorderedit";
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
                                                       PayOrder payOrder) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        RpcResult<Integer> serviceResult = null;
        try {
            if (payOrder.getId() != null && 0 != payOrder.getId()) {
                serviceResult = payOrderService.updatePayOrder(payOrder);
            } else {
                serviceResult = payOrderService.savePayOrder(payOrder);
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
            RpcResult<Boolean> serviceResult = payOrderService.del(id);
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
