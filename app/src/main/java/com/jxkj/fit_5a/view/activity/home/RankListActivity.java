package com.jxkj.fit_5a.view.activity.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import butterknife.BindView;

public class RankListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_rank_list;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("卡路里排名");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
