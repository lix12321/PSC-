package cn.wellcare.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 获取指定时间段的日期Date
     * @param dStart 开始日期
     * @param dEnd 结束日期
     * @return
     */
    public static List<Date> getDates(Date dStart, Date dEnd) {
        List<Date> dateList = new ArrayList();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);
        //起始日期
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(cStart.getTime());
        }
        return dateList;
    }

    /**
     * 获取指定时间段的日期字符串
     * @param start 开始日期
     * @param end 结束日期
     * @return
     */
    public static List<String> getDateStr(String start, String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = new ArrayList();
        Calendar cStart = Calendar.getInstance();
        Date endDate = null;
        try {
            cStart.setTime(sdf.parse(start));
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //起始日期
        dateList.add(start);
        // 此日期是否在指定日期之后
        while (endDate.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(sdf.format(cStart.getTime()));
        }
        return dateList;
    }

    /**
     * 获取一天的24小时
     * @return
     */
    public static List<String> getDayTime(){
        List<String> timeList = new ArrayList<>();
        String timeStr = "";
        for (int i = 0; i < 24 ; i++){
            if (i <= 9 ){
                timeStr = "0"+i;
            }else{
                timeStr = i+"";
            }
            timeList.add(timeStr);
        }
        return timeList;
    }

    /**
     * 获取当前月的日期字符串
     * @return
     */
    public static List<String> getCurrentMonthDateStr(){
        List<String> dateList = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String dataStr = "";
            if (i <= 9){
                dataStr += "0";
            }
            dataStr += i;
            dateList.add(dataStr);
        }
     return dateList;
    }

}
