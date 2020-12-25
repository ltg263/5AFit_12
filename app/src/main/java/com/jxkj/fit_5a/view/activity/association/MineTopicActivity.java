package com.jxkj.fit_5a.view.activity.association;


import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.CircleDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineTopicActivity extends BaseActivity {
    @BindView(R.id.rv_list_sp)
    RecyclerView mRvListSp;
    @BindView(R.id.rv_list_tp)
    RecyclerView mRvListTp;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_topic;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("-1");
        list.add("");
        list.add("-2");
        list.add("");
        list.add("");
        list.add("");

        CircleDynamicAdapter mCircleDynamicAdapter = new CircleDynamicAdapter(null);
        mRvListTp.setLayoutManager(new LinearLayoutManager(this));
        mRvListTp.setHasFixedSize(true);
        mRvListTp.setAdapter(mCircleDynamicAdapter);

        mCircleDynamicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvListSp.setLayoutManager(manager);
        HomeThreeSqAdapter mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvListSp.setHasFixedSize(true);
        mRvListSp.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


    @OnClick({R.id.iv_back, R.id.tv_share,  R.id.rl1, R.id.rl2,R.id.tv_add_dt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_add_dt:
                TopicAddActivity.startActivity(this,0);
                break;
            case R.id.rl1:
                initView(mTv1,mView1);
                mRvListSp.setVisibility(View.VISIBLE);
                mRvListTp.setVisibility(View.GONE);
                break;
            case R.id.rl2:
                initView(mTv2,mView2);
                mRvListSp.setVisibility(View.GONE);
                mRvListTp.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void initView(TextView tv, View v){
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
