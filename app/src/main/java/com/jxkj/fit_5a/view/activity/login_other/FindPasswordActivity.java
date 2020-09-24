package com.jxkj.fit_5a.view.activity.login_other;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class FindPasswordActivity extends BaseActivity {


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
    @BindView(R.id.ll_go_dl)
    LinearLayout mLlGoDl;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_find_password;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.tv_go_yzm, R.id.tv_go_find, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_yzm:

                break;
            case R.id.tv_go_find:

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
