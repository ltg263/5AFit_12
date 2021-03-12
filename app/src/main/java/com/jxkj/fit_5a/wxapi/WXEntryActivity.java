package com.jxkj.fit_5a.wxapi;

import android.util.Log;

import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class WXEntryActivity extends WXCallbackActivity {
    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);
        String code = ((SendAuth.Resp) resp).code;
        Log.w("-->>>","code:"+code);
//        login(code);

    }
//    public static void login(String code) {
//        RetrofitUtil.getInstance().apiService()
//                .register(code)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<Result>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Result result) {
////                        if (result.getStatus() == 0) {
////                        }else{
////                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }

}