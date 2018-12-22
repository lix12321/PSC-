package cn.wellcare.admin.controller.refund;

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
import cn.wellcare.entity.refund.PayRefund;
import cn.wellcare.payment.modules.refund.IPayRefundService;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 退款相关action
 *                       
 * @Filename: PayRefund.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "/admin/payRefund")
public class PayRefundController  {
    @Resource
    private IPayRefundService payRefundService;

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
        return "/admin/payRefund/payRefundlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PayRefund>> list(HttpServletRequest request,
                                                              ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        RpcResult<List<PayRefund>> serviceResult = payRefundService.page(
            queryMap, pager);
        if (!serviceResult.isSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<PayRefund>> jsonResult = new HttpJsonResult<List<PayRefund>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            PayRefund payRefund = payRefundService.getPayRefundById(id)
                .getData();
            dataMap.put("obj", payRefund);
        }

        return "/admin/payRefund/payRefundedit";
    }

    /**
     * 新增/编辑
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       PayRefund payRefund) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        RpcResult<Integer> serviceResult = null;
        try {
            if (payRefund.getId() != null && 0 != payRefund.getId()) {
                serviceResult = payRefundService.updatePayRefund(payRefund);
            } else {
                serviceResult = payRefundService.savePayRefund(payRefund);
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
     * @param
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> del(HttpServletRequest request,
                                                     HttpServletResponse response, Integer id) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            RpcResult<Boolean> serviceResult = payRefundService.del(id);
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
