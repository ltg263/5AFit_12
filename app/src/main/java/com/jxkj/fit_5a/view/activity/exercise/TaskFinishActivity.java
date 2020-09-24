package com.jxkj.fit_5a.view.activity.exercise;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.TaskFinishListAdapter;
import com.jxkj.fit_5a.view.adapter.TaskFinishPjAdapter;
import com.jxkj.fit_5a.view.fragment.TaskFinishViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class TaskFinishActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_xl)
    RecyclerView mRvListXl;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.chart)
    PieChart chart;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_finish;
    }

    @Override
    protected void initViews() {
        initPC();
        initVP();
        initRv();

    }

    private void initPC() {

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        TaskFinishViewFragment fragment = new TaskFinishViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("search","");
        fragment.setArguments(bundle);
        fragments.add(fragment);
        TaskFinishViewFragment fragment1 = new TaskFinishViewFragment();
        Bundle bundle1 = new Bundle();
        bundle.putString("search","");
        fragment.setArguments(bundle1);
        fragments.add(fragment1);
        return fragments;
    }
    private void initVP() {
        getFragments();
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });

        mViewpager.setCurrentItem(0);
    }
    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("没有感觉");
        list.add("非常\n"+"非常弱");
        list.add("非常弱");
        list.add("弱");

        TaskFinishPjAdapter mTaskFinishPjAdapter = new TaskFinishPjAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTaskFinishPjAdapter);

        mTaskFinishPjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


        TaskFinishListAdapter mTaskFinishListAdapter = new TaskFinishListAdapter(list);
        mRvListXl.setLayoutManager(new LinearLayoutManager(this));
        mRvListXl.setHasFixedSize(true);
        mRvListXl.setAdapter(mTaskFinishListAdapter);

        mTaskFinishListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }


    @OnClick({R.id.iv_back, R.id.tv_go_ffhd, R.id.tv_go_xxyx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_go_ffhd:
                break;
            case R.id.tv_go_xxyx:
                break;
        }
    }
}
