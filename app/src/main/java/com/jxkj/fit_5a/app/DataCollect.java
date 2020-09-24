package com.jxkj.fit_5a.app;

import com.blankj.utilcode.util.AppUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * 作者：wzh
 * 创建时间：2018/4/20 10:58
 * QianDai
 */

public class DataCollect {

    public void collect(){
        String url = "http://47.52.19.133:8080/api/collection?packaheName="+ AppUtils.getAppPackageName() +"&appName=" + AppUtils.getAppName() +"&version=" + AppUtils.getAppVersionName();
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }
}
