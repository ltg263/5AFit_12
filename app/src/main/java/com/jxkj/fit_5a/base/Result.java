package com.jxkj.fit_5a.base;

import com.blankj.utilcode.util.ToastUtils;

public class Result<T> {
    private String sub_mesg;
    private int code;
    private T data;

    public String getMesg() {
        return sub_mesg;
    }

    public void setMesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    public int getCode() {
        if(code!=0){
            ToastUtils.showShort(sub_mesg);
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
