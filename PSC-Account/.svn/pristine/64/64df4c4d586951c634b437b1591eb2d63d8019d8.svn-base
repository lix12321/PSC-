package cn.wellcare.controller.acc;

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

import cn.wellcare.acc.entity.PscPiAccCredit;
import cn.wellcare.api.acc.IPscPiAccCreditService;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.HttpJsonResult;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.core.utils.WebUtil;
import cn.wellcare.pojo.common.RpcResult;

/**
 * psc_pi_acc_credit
 *                       
 * @Filename: PscPiAccCredit.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "pscPiAccCredit")
public class PscPiAccCreditController  {
    @Resource
    private IPscPiAccCreditService pscPiAccCreditService;

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
        return "/admin/pscPiAccCredit/pscPiAccCreditlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    @ResponseBody
    public  HttpJsonResult<List<PscPiAccCredit>> list(HttpServletRequest request,
                                                                   ModelMap dataMap) {
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        RpcResult<List<PscPiAccCredit>> serviceResult = pscPiAccCreditService.page(
            queryMap, pager);
        if (!serviceResult.isSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        HttpJsonResult<List<PscPiAccCredit>> jsonResult = new HttpJsonResult<List<PscPiAccCredit>>();
        jsonResult.setRows(serviceResult.getData());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            PscPiAccCredit pscPiAccCredit = pscPiAccCreditService.getPscPiAccCreditById(id)
                .getData();
            dataMap.put("obj", pscPiAccCredit);
        }

        return "/admin/pscPiAccCredit/pscPiAccCreditedit";
    }

    /**
     * 新增/更新信用操作记录
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "doAddCredit")
    @ResponseBody
    public  RpcResult<PscPiAccCredit> doAddCredit(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       PscPiAccCredit pscPiAccCredit) {
        RpcResult<PscPiAccCredit> jsonResult = new RpcResult<>();
        RpcResult<Integer> serviceResult = null;
        try {
            if (pscPiAccCredit.getPkAcccredit() != null && 0 != pscPiAccCredit.getPkAcccredit()) {
                serviceResult = pscPiAccCreditService.updatePscPiAccCredit(pscPiAccCredit);
            } else {
                serviceResult = pscPiAccCreditService.savePscPiAccCredit(pscPiAccCredit);
            }
            jsonResult.setSuccess(serviceResult.getData() > 0);
            jsonResult.setData(pscPiAccCredit);
        } catch (Exception e) {
            jsonResult.setMsgInfo(e.getMessage());
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
    @RequestMapping(value = "del")
    @ResponseBody
    public  HttpJsonResult<Boolean> del(HttpServletRequest request,
                                                     HttpServletResponse response, Integer id) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            RpcResult<Boolean> serviceResult = pscPiAccCreditService.del(id);
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

    /**
     * 根据账户id查询账户流水
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "queryCredit")
    @ResponseBody
    public  RpcResult<List<PscPiAccCredit>> queryDetialByPkPiacc(HttpServletRequest request,
                                                                            HttpServletResponse response,
                                                                            Integer pkPiacc) {
        RpcResult<List<PscPiAccCredit>> jsonResult = new RpcResult<>();
        RpcResult<List<PscPiAccCredit>> serviceResult = null;
        try {
            if (pkPiacc==null) {
                throw new BusinessException("未传入账户主键");
            }
            serviceResult = pscPiAccCreditService.getPscPiAccCreditByPkPiacc(pkPiacc);
            if (serviceResult.getData() == null) {
                throw new BusinessException("未查询到患者信用操作记录信息");
            }
            jsonResult.setSuccess(serviceResult.isSuccess());
            jsonResult.setData(serviceResult.getData());
        } catch (Exception e) {
            jsonResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

}
