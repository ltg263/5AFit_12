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
//        10   220
//        TimeThreadUtils.sendDataA6(10000,a);

        int max = 209;
        int min = 11;
        System.out.println("--->>"+((max-min)*0.5+min));
        System.out.println("--->>"+((max-min)*0.6+min));
        System.out.println("--->>"+((max-min)*0.7+min));
        System.out.println("--->>"+((max-min)*0.8+min));
        System.out.println("--->>"+((max-min)*0.9+min));
        System.out.println("--->>"+((max-min)*1+min));



        Calendar cal = Calendar.getInstance();
//cal.set(2002, 2, 28);// 2002-03-28 星期四
        System.out.println("今天是：" + cal.get(Calendar.YEAR)
                + (cal.get(Calendar.MONTH) + 1)//Month 值是基于 0 的。例如，0 表示bai January
                + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("星期" + getBigWeek(cal.get(Calendar.DAY_OF_WEEK)));
        int flag = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1-flag);
/////////////////////
        System.out.println("本周从");
        System.out.println("" + cal.get(Calendar.YEAR)
                + (cal.get(Calendar.MONTH) + 1)//Month 值是基于 0 的。例如，0 表示 January
                + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("星期" + getBigWeek(cal.get(Calendar.DAY_OF_WEEK)));

/////////////////////////
        System.out.println("到");
        cal.add(Calendar.DATE, 6);
///////////////////////
        System.out.println("" + cal.get(Calendar.YEAR)
                + (cal.get(Calendar.MONTH) + 1)//Month 值是基于 0 的。例如，0 表示 January
                + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("星期" + getBigWeek(cal.get(Calendar.DAY_OF_WEEK)));
        aa();
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

