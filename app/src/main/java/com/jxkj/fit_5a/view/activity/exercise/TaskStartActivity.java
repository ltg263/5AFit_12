package com.jxkj.fit_5a.view.activity.exercise;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskStartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lianjie)
    TextView tv_lianjie;

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
                if(tv_lianjie.getText().toString().equals("运动设备未连接")){
                    ToastUtils.showShort("请先链接运动设备");
                    return;
                }
                startActivity(new Intent(this, CourseStartActivity.class));
                break;
            case R.id.ll_kcxz:
                if(tv_lianjie.getText().toString().equals("运动设备未连接")){
                    ToastUtils.showShort("请先链接运动设备");
                    return;
                }
                startActivity(new Intent(this, CourseSelectionActivity.class));
                break;
            case R.id.ll_xlkz:
                if(tv_lianjie.getText().toString().equals("运动设备未连接")){
                    ToastUtils.showShort("请先链接运动设备");
                    return;
                }
                RateControlActivity.intentActivity(this);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_lianjie.setText("运动设备未连接");
        if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
            tv_lianjie.setText(PopupWindowLanYan.BleName);
        }
    }

}
