package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineSetActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("设 置");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }

    @OnClick({R.id.ll_back, R.id.ll1, R.id.ll2,R.id.tv_tui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll1:
                break;
            case R.id.ll2:
                break;
            case R.id.tv_tui:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
