package com.jxkj.fit_5a.view.activity;

import android.content.Intent;
import android.view.View;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import butterknife.OnClick;


public class SplashScreenActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.splashscreen;
    }

    @Override
    protected void initViews() {

    }



    @OnClick({R.id.tv_go, R.id.tv_go_x})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.tv_go_x:
                break;
        }
    }
}
