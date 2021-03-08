package com.jxkj.fit_5a.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.lang.ref.WeakReference;
import java.util.Map;

//
//import com.alipay.sdk.app.PayTask;

/**
 * Created by ShiXL on 2018/3/2.
 */

public class MyAlipay {

    private static final int SDK_PAY_FLAG = 1;

    private WeakReference<Activity> mActivity;
    private OnAlipayListener mListener;

    public MyAlipay(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

//            返回码	含义
//            9000	订单支付成功
//            8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
//            4000	订单支付失败
//            5000	重复请求
//            6001	用户中途取消
//            6002	网络连接出错
//            6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
//            其它	其它支付错误
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            switch (payResult.getResultStatus()) {
                case "9000":
                    mListener.onSuccess();
                    break;
                case "8000":
                    Toast.makeText(mActivity.get(),"正在处理中", Toast.LENGTH_SHORT).show();
//                    mListener.onWait();
                    mListener.onFail();
                    break;
                case "4000":
                    mListener.onFail();
                    break;
                case "5000":
                    Toast.makeText(mActivity.get(),"重复请求", Toast.LENGTH_SHORT).show();
                    break;
                case "6001":
                    Toast.makeText(mActivity.get(),"已取消支付", Toast.LENGTH_SHORT).show();
//                    mListener.onCancel();
                    mListener.onFail();
                    break;
                case "6002":
                    Toast.makeText(mActivity.get(),"网络连接出错", Toast.LENGTH_SHORT).show();
                    break;
                case "6004":
                    Toast.makeText(mActivity.get(),"正在处理中", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(mActivity.get(),"支付失败", Toast.LENGTH_SHORT).show();
                    mListener.onFail();
                    break;
            }
        }
    };

    /**
     * 签名在服务端来做
     * @param orderInfo
     */
    public void toAliPay(final String orderInfo , OnAlipayListener onAlipayListener) {

        mListener = onAlipayListener;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity.get());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

}
