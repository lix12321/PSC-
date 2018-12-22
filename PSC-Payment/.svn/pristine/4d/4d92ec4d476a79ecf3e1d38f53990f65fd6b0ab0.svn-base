package cn.wellcare.admin.controller.statistics;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.core.utils.WebUtil;
import cn.wellcare.entity.system.SystemUsers;
import cn.wellcare.payment.modules.statistics.IOrderPayModelCountService;
import cn.wellcare.web.SessionManager;

@Controller
@RequestMapping("orderpaymodelcount")
public class OrderPayModelCountController {

    @Resource
    private IOrderPayModelCountService orderPayModelCountService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        return "statistics/orderpaymodelcount";
    }

    /**
     * 获取图表数据
     * @param request
     * @return
     */
    @RequestMapping(value = "getChartData",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getChartData(HttpServletRequest request){
        Map<String, Object> queryMap = WebUtil.handlerQueryMap(request);
        //获取登录人信息
		SystemUsers adminUser = SessionManager.getAdminUser(request);
        if (null != adminUser){
            queryMap.put("q_orgId",adminUser.getId());
        }
        Map<String, Object> dataMap = orderPayModelCountService.getChartData(queryMap);
        return dataMap;
    }
}
