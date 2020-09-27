package com.jxkj.fit_5a.view.activity.exercise;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskStartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_start;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("经典运动");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }


    @OnClick({R.id.ll_back, R.id.rl_sbgl,R.id.btn_start, R.id.ll_kcxz,R.id.ll_xlkz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_sbgl:
                break;
            case R.id.btn_start:

                break;
            case R.id.ll_kcxz:
                startActivity(new Intent(this, CourseSelectionActivity.class));
                break;
            case R.id.ll_xlkz:
                RateControlActivity.intentActivity(this);
                break;
        }
    }
}
