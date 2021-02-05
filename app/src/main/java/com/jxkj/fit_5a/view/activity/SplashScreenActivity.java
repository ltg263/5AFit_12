package com.jxkj.fit_5a.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SplashScreenActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.splashscreen;
    }

    @Override
    protected void initViews() {
        getUserDetail();
        new Handler().postDelayed(() -> startUi(),2000);
    }

    private void startUi() {
        if (StringUtil.isBlank(SharedUtils.getToken()) || !isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    private void getUserDetail() {
        RetrofitUtil.getInstance().apiService()
                .getUserDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserDetailData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserDetailData> result) {
                        if(result.getCode()==21001
                                && result.getMesg().equals("无效token")
                                && isLogin()){
                            HttpRequestUtils.userVerifyLogin();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
