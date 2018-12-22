package cn.wellcare.service.modules.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.statistics.TodayOrderPayStateCountModel;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.payment.modules.statistics.ITodayOrderPayStateCountService;

@Service("todayOrderPayStateCountService")
public class TodayOrderPayStateCountService implements ITodayOrderPayStateCountService {

    @Resource
    private TodayOrderPayStateCountModel todayOrderPayStateCountModel;

    @Resource
    private SysCodeMasterModel sysCodeMasterModel;

    private static final String ORDER_STATE = "orderstate";

    private static final String ORDER_MONEY = "ordermoney";

    @Override
    public Map<String,Object> getChartData(Map<String,Object> paramMap){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("codeDiv","ORDER_STATE");

        //根据条件查询
        List<Map<String,Object>> findResult = todayOrderPayStateCountModel.getChartData(paramMap);
        List<SysCodeMaster> scmList = null;
        try {
            scmList = sysCodeMasterModel.page(queryMap, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //分装结果集
       List<String> xAxisList = new ArrayList<>();

        List<Object> seriesList = new ArrayList<>();
        for (SysCodeMaster sysCodeMaster : scmList){
            String orderState = sysCodeMaster.getCodeCd();
            xAxisList.add(sysCodeMaster.getCodeText());
            BigDecimal value = getDateValue(findResult,orderState);
            seriesList.add(value);
        }
        resultMap.put("xAxis",xAxisList);
        resultMap.put("series",seriesList);
        return resultMap;
    }

    private BigDecimal getDateValue(List<Map<String,Object>> dataList,String codeCd){
        BigDecimal value = new BigDecimal(0);
        if (null != dataList && !dataList.isEmpty()){
            for (Map<String,Object> map : dataList){
                if (codeCd.equals(String.valueOf(map.get(this.ORDER_STATE))))

                    value = (BigDecimal) map.get(this.ORDER_MONEY);
            }
        }
        return value;
    }
}
