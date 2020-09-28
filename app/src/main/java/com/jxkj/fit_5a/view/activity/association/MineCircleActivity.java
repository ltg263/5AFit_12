package com.jxkj.fit_5a.view.activity.association;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.BlurringView;
import com.jxkj.fit_5a.view.adapter.CircleDynamicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineCircleActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.CoordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv_jiaru)
    TextView tv_jiaru;
    @BindView(R.id.blurring_view)
    BlurringView mBlurringView;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_circle;
    }

    @Override
    protected void initViews() {
        if (getIntent().getStringExtra("type").equals("未加入")) {
            mBlurringView.setVisibility(View.VISIBLE);
            tv_jiaru.setVisibility(View.VISIBLE);
        }
        List<String> list = new ArrayList<>();
        list.add("-1");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        CircleDynamicAdapter mCircleDynamicAdapter = new CircleDynamicAdapter(list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mCircleDynamicAdapter);

        mCircleDynamicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
        mBlurringView.setBlurredView(mRvList);
    }

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_add_dt, R.id.tv_jiaru, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_jiaru:
                ToastUtils.showShort("加入成功");
                tv_jiaru.setVisibility(View.GONE);
                mBlurringView.setVisibility(View.GONE);
                break;
            case R.id.tv_add_dt:
                startActivity(new Intent(this, CircleAddActivity.class));
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_666666));
        mTv2.setTextColor(getResources().getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }

}
