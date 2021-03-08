package com.jxkj.fit_5a.alipay;

/**
 * Created by ShiXL on 2018/3/2.
 */

public interface OnAlipayListener {

    /**
     * 支付成功
     */
     void onSuccess();

    /**
     * 支付取消
     */
     void onCancel();

    /**
     * 等待确认
     */
     void onWait();

    /**
     * 支付失败
     */
     void onFail();
}

