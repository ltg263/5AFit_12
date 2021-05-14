package com.jxkj.fit_5a;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MianJavaDame {
    public static void main(String[] args) {
//        int[] test = {10,-200,3,8,9,2,5};
//        Arrays.sort(test); //首先对数组排序
//        int result = Arrays.binarySearch(test, 100); //在数组中搜索是否含有5
//        System.out.println("-->>>:"+result); //这里的结果是 3
//        double a = 313.83333333333337;
//        float b = 1.5f;
////        System.out.println("-->>>:"+(float)a*b); //这里的结果是 3
//        int number = 10;
//        System.out.println("-->>>:"+ Math.floor(1.4));
        System.out.println("-->>>:"+(int) (129/130));

//        System.out.println("-->>>:"+"getDate"+ (float) 300 / 500.0f);
//        System.out.println("-->>>:"+("0000fff1-0000-1000-8000-00805f9b34fb".indexOf("fff1")));

    }

    public static String getValue(double obj) {
        if(isIntegerForDouble(obj)){
            return String.valueOf((int) obj);
        }
        return String.format("%.2f", obj);
    }

    public static int toInt(byte[] bytes) {
        int number = 0;
        for (int i = 0; i < 4; i++) {
            number += bytes[i] << i * 8;
        }
        return number;
    }
    public static byte[] toBytes(int number){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)number;
        bytes[1] = (byte) (number >> 8);
        bytes[2] = (byte) (number >> 16);
        bytes[3] = (byte) (number >> 24);
        return bytes;
    }

    /**
     * 判断double是否是整数
     * @param obj
     * @return
     */
    public static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;  // 精度范围
        return obj-Math.floor(obj) < eps;
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

