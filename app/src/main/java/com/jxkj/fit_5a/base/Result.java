package com.jxkj.fit_5a.base;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

public class Result<T> {
    private String error;

    public String getError() {
        if(StringUtil.isNotBlank(error)){
            return error;
        }
        return "\"status\":"+status;
    }

    public void setError(String error) {
        this.error = error;
    }

    private int status;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
