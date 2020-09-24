package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.et_input_sjh_yes)
    TextView mEtInputSjhYes;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.et_input_mm_yes)
    TextView mEtInputMmYes;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.tv_login_yzm)
    TextView mTvLoginYzm;
    @BindView(R.id.tv_login_wjmm)
    TextView mTvLoginWjmm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.ll3)
    LinearLayout mLl3;
    int loginType = 1;//密码登录

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.tv_login_yzm, R.id.tv_login_wjmm, R.id.tv_go_login, R.id.ll_go_zc,R.id.tv_go_yzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_yzm:
                if(loginType==1){
                    loginType = 2;
                    mTvLoginYzm.setText("密码登录");
                    mLl2.setVisibility(View.INVISIBLE);
                    mLl3.setVisibility(View.VISIBLE);
                    mTvLoginWjmm.setVisibility(View.INVISIBLE);
                }else{
                    loginType = 1;
                    mTvLoginYzm.setText("验证码登录");
                    mLl2.setVisibility(View.VISIBLE);
                    mLl3.setVisibility(View.INVISIBLE);
                    mTvLoginWjmm.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_go_yzm:

                break;
            case R.id.tv_login_wjmm:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
            case R.id.tv_go_login:
                startActivity(new Intent(LoginActivity.this,WelcomeLoginActivity.class));
                break;
            case R.id.ll_go_zc:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
}
