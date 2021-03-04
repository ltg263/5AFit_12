package com.jxkj.fit_5a;


import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MianJavaDame {
    public static void main(String[] args) {
//
//        String[] a = new String[1];
//        a[0]="哈哈";

        System.out.println("--->>"+ new Date(2021,03,04).getTime());
        System.out.println("--->>"+ new Date(2021,03,03).getTime());
        int a = 1;
        int b = 1;
        int c = 1;
        new Date(a+"-"+b+"-"+c);
    }

    public static String getBigWeek(int dayofweek) {
//Calendar中1-星期天，2-星期一，3-星期二，4-星期三，5-星期四，6-星期五，7-星期六
        String[] wee = { "", "天", "一", "二", "三", "四", "五", "六" };
        return wee[dayofweek];

    }

    private static void aa(){
        Calendar cal = Calendar.getInstance();
        int flag = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1-flag);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);

    }

    /**
     * 获取过去7天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals -1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }
    /**
     * 获取过去第几天的日期
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
}

