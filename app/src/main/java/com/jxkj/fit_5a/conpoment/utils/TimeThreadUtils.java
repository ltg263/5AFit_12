package com.jxkj.fit_5a.conpoment.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;


public class TimeThreadUtils {

    private static Ble4_0Util lanyan1 = PopupWindowLanYan.ble4Util;

    public static void sendDataA0() {
        new Thread(new ThreadShowA0()).start();
    }

    public static void sendDataA2() {
        new Thread(new ThreadShowA2()).start();
    }

    public static void sendDataA6(long time,byte[] load) {
        System.out.println("receive....time："+time);
        new Thread(new ThreadShowA6(time,load)).start();
    }


    static class ThreadShowA0 implements Runnable {
        // handler类接收数据
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    lanyan1.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
                    System.out.println("receive....");
                }
            }
        };
        @Override
        public void run() {
            while (lanyan1.isConnect()) {
                try {
                    Thread.sleep(5000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    System.out.println("send...");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }

    static class ThreadShowA2 implements Runnable {
        // handler类接收数据
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    lanyan1.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A2));
                    System.out.println("receive....");
                }
            }
        };
        @Override
        public void run() {
            while (lanyan1.isConnect()) {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    System.out.println("send...");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }

    static class ThreadShowA6 implements Runnable {
        int pos = 0;
        long time;
        byte[] load;

        public ThreadShowA6(long time, byte[] load) {
            this.time=time;
            this.load = load;
        }
        // handler类接收数据
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1 && load.length>pos) {
                    System.out.println("receive....");
                    lanyan1.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A6,load[pos]));
                    pos++;
                }
            }
        };

        @Override
        public void run() {
            while (lanyan1.isConnect() && load.length>pos && ConstValues_Ly.CURRENT_STATE==1) {
                try {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    System.out.println("receive....");
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }

}
