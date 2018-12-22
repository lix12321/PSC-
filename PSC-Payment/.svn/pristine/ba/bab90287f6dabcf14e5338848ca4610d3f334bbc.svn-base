package cn.wellcare.service.modules.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.wellcare.core.utils.DateUtil;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.statistics.OrderPayModelTrendByDayModel;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.payment.modules.statistics.IOrderPayModelTrendByDayService;

@Service("orderPayModelTrendByDayService")
public class OrderPayModelTrendByDayService implements IOrderPayModelTrendByDayService {

    @Resource
    private OrderPayModelTrendByDayModel orderPayModelTrendByDayModel;

    @Resource
    private SysCodeMasterModel sysCodeMasterModel;

    @Override
    public Map<String,Object> getChartData(Map<String,Object> paramMap){
        Map<String,Object> resultMap = new HashMap<>();
        String paymentType = (String) paramMap.get("q_paymentType");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("codeDiv","PAYMENT_TYPE");
        if (!StringUtils.isEmpty(paymentType)){
            String[] paymentTypeArr = paymentType.split(",");
            paramMap.put("q_paymentType",paymentTypeArr);
            queryMap.put("q_paymentType",paymentTypeArr);
        }
        //根据条件查询
        List<Map<String,Object>> findResult = orderPayModelTrendByDayModel.getChartData(paramMap);
        List<SysCodeMaster> scmList = null;
        try {
            scmList = sysCodeMasterModel.page(queryMap, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //分装结果集
        String startStr = (String) paramMap.get("q_startTime");
        String endStr = (String) paramMap.get("q_endTime");
        List<String> legendList = new ArrayList<>();
        Map<String,List<Object>> seriesMap = new HashMap<>();
        List<String> xAxisList = DateUtil.getDateStr(startStr,endStr);

        for(String dateStr : xAxisList){
            List<Object> seriesList = null;
            for (SysCodeMaster sysCodeMaster : scmList){
                String payModel = sysCodeMaster.getCodeText();
                if (!legendList.contains(payModel)){
                    legendList.add(sysCodeMaster.getCodeText());
                }
                BigDecimal value = getDateValue(dateStr,findResult,sysCodeMaster.getCodeText());
                if (!seriesMap.containsKey(payModel)){
                    seriesList = new ArrayList<>();
                }else{
                    seriesList = seriesMap.get(payModel);
                }
                seriesList.add(value);
                seriesMap.put(payModel,seriesList);
            }
        }

        resultMap.put("legend",legendList);
        resultMap.put("xAxis",xAxisList);
        resultMap.put("series",seriesMap);
        return resultMap;
    }

    private BigDecimal getDateValue(String dataStr, List<Map<String,Object>> dataList,String codeText){
        BigDecimal value = new BigDecimal(0);
        if (null != dataList && !dataList.isEmpty()){
            for (Map<String,Object> map : dataList){
                List<Object> seriesList = null;
                if (dataStr.equals(map.get("payday"))){
                    if (codeText.equals(map.get("paymode")))
                        value = (BigDecimal) map.get("ordermoney");
                }
            }
        }
        return value;
    }
}
