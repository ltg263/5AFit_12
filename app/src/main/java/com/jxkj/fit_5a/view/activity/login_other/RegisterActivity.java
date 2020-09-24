package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.et_input_sjh_yes)
    TextView mEtInputSjhYes;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.et_input_mm_yes)
    TextView mEtInputMmYes;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.tv_go_yzm, R.id.tv_go_register, R.id.ll_go_dl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_yzm:
                break;
            case R.id.tv_go_register:
                startActivity(new Intent(RegisterActivity.this, SetUserXbActivity.class));
                break;
            case R.id.ll_go_dl:
                finish();
                break;
        }
    }
}
