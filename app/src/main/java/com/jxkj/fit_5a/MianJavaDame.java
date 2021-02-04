package com.jxkj.fit_5a;


import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MianJavaDame {
    public static void main(String[] args) {
//
        System.out.println("--->>"+ getDays(7));
        System.out.println("--->>"+ getDays(30));
//        System.out.println("--->>"+ (int)getCHECKSUM(b));


//        System.out.println("--->>"+ConstValues_Ly.getBaiShiGeX(12,2));
//        Byte[] a = new Byte[]{1,25,55};
//        TimeThreadUtils.sendDataA6(10000,a);
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
