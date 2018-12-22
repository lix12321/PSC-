package cn.wellcare.controller.acc;

import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.api.acc.IPscPiAccService;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.HttpJsonResult;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.core.utils.StringUtil;
import cn.wellcare.core.utils.WebUtil;
import cn.wellcare.pojo.common.RpcResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * psc_pi_acc
 *                       
 * @Filename: PscPiAcc.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "pscPiAcc")
public class PscPiAccController  {
    @Resource
    private IPscPiAccService pscPiAccService;

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
        return "/admin/pscPiAcc/pscPiAcclist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "accountInquiry",produces = {"application/json"})
    @ResponseBody
    public  RpcResult<List<PscPiAcc>> accountInquiry(HttpServletRequest request, HttpServletResponse response,
                                                         @RequestParam Map<String,Object> dataMap) {
		// Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        RpcResult<List<PscPiAcc>> serviceResult = pscPiAccService.page(
            dataMap, pager);
        if (!serviceResult.isSuccess()) {
            if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getMsgCode())) {
				throw new RuntimeException(serviceResult.getMsgInfo());
			} else {
				throw new BusinessException(serviceResult.getMsgInfo());
			}
        }

        RpcResult<List<PscPiAcc>> jsonResult = new RpcResult<List<PscPiAcc>>();
        jsonResult.setData(serviceResult.getData());
		// jsonResult.setPager(pager);
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            PscPiAcc pscPiAcc = pscPiAccService.getPscPiAccById(id)
                .getData();
            dataMap.put("obj", pscPiAcc);
        }

        return "/admin/pscPiAcc/pscPiAccedit";
    }

    /**
     * 新增/修改账户
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "doAdd",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<PscPiAcc> doAdd(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                   PscPiAcc pscPiAcc) {
        RpcResult<PscPiAcc> piAccServiceResult = null;
        try {
            if (null!=pscPiAcc.getPkPiacc() && 0 != pscPiAcc.getPkPiacc()) {
                piAccServiceResult = pscPiAccService.updatePscPiAcc(pscPiAcc);
            } else {
                piAccServiceResult = pscPiAccService.savePscPiAcc(pscPiAcc);
            }
            piAccServiceResult.setSuccess(piAccServiceResult != null);
            piAccServiceResult.setData(piAccServiceResult.getData());
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }
    /**
     * 批量新增账户
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "batchUpdateAdd",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<Boolean> batchUpdateAdd(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                         @RequestBody Map<String,Object> map) {
        RpcResult<Boolean> piAccServiceResult = new RpcResult<>();
        RpcResult<Integer> serviceResult = null;
        try {
            serviceResult = pscPiAccService.batchInsertPscPiAcc(map);
            piAccServiceResult.setSuccess(serviceResult.getData()>0);
            piAccServiceResult.setData(serviceResult.getData()>0);
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }

    /**
     * 批量更新账户
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "batchUpdate",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<Boolean> batchUpdate(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestBody Map<String,Object> map) {
        RpcResult<Boolean> piAccServiceResult = new RpcResult<>();
        RpcResult<Integer> serviceResult = null;
        try {
            serviceResult = pscPiAccService.batchUpdatePscPiAcc(map);
            piAccServiceResult.setSuccess(serviceResult.getData()>0);
            piAccServiceResult.setData(serviceResult.getData()>0);
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    @ResponseBody
    public  HttpJsonResult<Boolean> del(HttpServletRequest request,
                                                     HttpServletResponse response, Integer id) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            RpcResult<Boolean> serviceResult = pscPiAccService.del(id);
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
     * 查询账户信息根据患者主键（主索引）
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "queryAccount",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<PscPiAcc> queryAccountByPi(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      String pkPi) {
        RpcResult<PscPiAcc> piAccServiceResult = new RpcResult<PscPiAcc>();
        RpcResult<PscPiAcc> serviceResult = null;
        try {
                if (StringUtil.isEmpty(pkPi)) {
                    throw new BusinessException("未传入患者主键");
                }
                serviceResult = pscPiAccService.getPscPiAccBypkPi(pkPi);
                if (serviceResult.getData() == null) {
                    throw new BusinessException("未查询到患者账户信息");
                }
                piAccServiceResult.setSuccess(serviceResult.isSuccess());
                piAccServiceResult.setData(serviceResult.getData());
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }
    /**
     * 查询账户信息根据患者账户编码
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "queryAccountBycode",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<PscPiAcc> queryAccountBycode(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      String codeAcc) {
        RpcResult<PscPiAcc> piAccServiceResult = new RpcResult<PscPiAcc>();
        RpcResult<PscPiAcc> serviceResult = null;
        try {
                if (StringUtil.isEmpty(codeAcc)) {
                    throw new BusinessException("未传入账户编码");
                }
                serviceResult = pscPiAccService.getPscPiAccByCodeAcc(codeAcc);
                if (serviceResult.getData() == null) {
                    throw new BusinessException("未查询到患者账户信息");
                }
                piAccServiceResult.setSuccess(serviceResult.isSuccess());
                piAccServiceResult.setData(serviceResult.getData());
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }

    /**
     * 查询账户信息根据账户主键
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "queryAccountByid",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<PscPiAcc> queryAccountByid(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          Integer id) {
        RpcResult<PscPiAcc> piAccServiceResult = new RpcResult<PscPiAcc>();
        RpcResult<PscPiAcc> serviceResult = null;
        try {
            if (id==null) {
                throw new BusinessException("未传入账户主键");
            }
            serviceResult = pscPiAccService.getPscPiAccById(id);
            if (serviceResult.getData() == null) {
                throw new BusinessException("未查询到患者账户信息");
            }
            piAccServiceResult.setSuccess(serviceResult.isSuccess());
            piAccServiceResult.setData(serviceResult.getData());
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }

    /**
     * 查询账户状态根据账户主键
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "queryStatusByid",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<Boolean> queryAccountStatusByid(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer id) {
        RpcResult<Boolean> piAccServiceResult = new RpcResult<Boolean>();
        RpcResult<Boolean> serviceResult = null;
        try {
            if (id==null) {
                throw new BusinessException("未传入账户主键");
            }
            serviceResult = pscPiAccService.queryAccountStatus(id);
            piAccServiceResult.setSuccess(serviceResult.isSuccess());
            piAccServiceResult.setData(serviceResult.getData());
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }

    /**
     * 查询账户状态根据患者主键（主索引）
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "queryStatus",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<Boolean> queryAccountStatus(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      String pkPi) {
        RpcResult<Boolean> piAccServiceResult = new RpcResult<Boolean>();
        RpcResult<Boolean> serviceResult = null;
        try {
            if (StringUtil.isEmpty(pkPi)) {
                throw new BusinessException("未传入患者主键");
            }
            serviceResult = pscPiAccService.getAccountStatus(pkPi);
            piAccServiceResult.setSuccess(serviceResult.isSuccess());
            piAccServiceResult.setData(serviceResult.getData());
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }

    /**
     * 密码校验服务
     * 1.传入账户id、主索引、账户编码任选其一
     * 2.密码
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "passwordCheck",produces = {"application/json"} )
    @ResponseBody
    public  RpcResult<String> passwordCheck(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestParam Map<String,Object> param) {
        RpcResult<String> piAccServiceResult = new RpcResult<>();
        RpcResult<List<PscPiAcc>> serviceResult = null;
        try {
            piAccServiceResult = pscPiAccService.passwordCheck(param);
        } catch (Exception e) {
            piAccServiceResult.setMsgInfo(e.getMessage());
            e.printStackTrace();
        }
        return piAccServiceResult;
    }
}
