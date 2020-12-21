package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.UserTopAdapter;
import com.jxkj.fit_5a.view.adapter.UserTopXAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserHomeActivity extends BaseActivity {

//    @BindView(R.id.iv_parallax)
//    ImageView mIvParallax;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.rl_allinfo)
    RelativeLayout mRlAllinfo;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.toolbar_avatar)
    ImageView mToolbarAvatar;
    @BindView(R.id.iv_date)
    ImageView mIvDate;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar1)
    Toolbar mToolbar1;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.rl_actionbar)
    AppBarLayout mAppbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.rv_qz_list)
    RecyclerView mRvQzList;
    @BindView(R.id.rv_dt_list)
    RecyclerView mRvDtList;
    @BindView(R.id.rv_dt_list_sp)
    RecyclerView mRvDtListSp;

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
        return R.layout.activity_user_home;
    }

    @Override
    protected void initViews() {
        initRv();



        initListener();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        for(int i= 0;i<10;i++){
            list.add("");
        }
        UserTopAdapter mUserTopAdapter = new UserTopAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvQzList.setLayoutManager(ms);
        mRvQzList.setHasFixedSize(true);
        mRvQzList.setAdapter(mUserTopAdapter);

        UserTopXAdapter mUserTopxAdapter = new UserTopXAdapter(list);
        mRvDtList.setLayoutManager(new LinearLayoutManager(this));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mUserTopxAdapter);


        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvDtListSp.setLayoutManager(manager);
        HomeThreeSqAdapter mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvDtListSp.setHasFixedSize(true);
        mRvDtListSp.setAdapter(mHomeThreeSqAdapter);
        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void initListener() {
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//6
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mCollapsingToolbar.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                float fraction = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                mCollapsingToolbar.setAlpha(fraction);
                mToolbar1.setAlpha(fraction);
                mToolbar.setAlpha(1-fraction);

            }
        });
    }
    @OnClick({R.id.iv_back,  R.id.rl1, R.id.rl2,R.id.ll_gz_on,R.id.ll_fs_on,R.id.ll_lw_on,R.id.ll_sc_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_gz_on:
                startActivity(new Intent(this,UserGzActivity.class));
                break;
            case R.id.ll_fs_on:
                startActivity(new Intent(this,UserFsActivity.class));
                break;
            case R.id.ll_lw_on:
                startActivity(new Intent(this,UserLwActivity.class));
                break;
            case R.id.ll_sc_on:
                startActivity(new Intent(this,UserScActivity.class));
                break;
            case R.id.rl1:
                initView(mTv1,mView1);
                mRvDtList.setVisibility(View.VISIBLE);
                mRvDtListSp.setVisibility(View.GONE);
                break;
            case R.id.rl2:
                initView(mTv2,mView2);
                mRvDtList.setVisibility(View.GONE);
                mRvDtListSp.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void initView(TextView tv, View v){
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_999999));
        mTv2.setTextColor(getResources().getColor(R.color.color_999999));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
//        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }
}
