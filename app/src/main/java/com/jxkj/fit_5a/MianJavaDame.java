package com.jxkj.fit_5a;


import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;

public class MianJavaDame {
    public static void main(String[] args) {
        byte[] b = new byte[]{3, 16, 61, 1, 41, 1, 86, 13, 1, 1};
//
//        System.out.println("--->>"+ getCHECKSUM(b));
//        System.out.println("--->>"+ (int)getCHECKSUM(b));


//        System.out.println("--->>"+ConstValues_Ly.getBaiShiGeX(12,2));
//        Byte[] a = new Byte[]{1,25,55};
//        TimeThreadUtils.sendDataA6(10000,a);
    }

    public static byte getCHECKSUM(byte[] data) {
        byte data_z = 0;
        for (int i = 0; i < data.length; i++) {
            data_z += data[i];
        }
        return data_z;
    }
}
