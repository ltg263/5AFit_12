package com.jxkj.fit_5a.view.activity.association;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateCircleActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_create_circle;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("创建圈子");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }


    @OnClick({R.id.ll_back, R.id.tv_go_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_go_find:
                DialogUtils.showDialogCgCircle(this,  "创建圈子权限", 1, new DialogUtils.DialogLyInterface() {
                    @Override
                    public void btnConfirm() {
                    }
                });
                break;
        }
    }
}
