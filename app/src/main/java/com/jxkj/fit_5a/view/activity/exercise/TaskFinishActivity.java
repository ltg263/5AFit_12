package com.jxkj.fit_5a.view.activity.exercise;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AADataLabels;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAPie;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.view.adapter.TaskFinishListAdapter;
import com.jxkj.fit_5a.view.adapter.TaskFinishPjAdapter;
import com.jxkj.fit_5a.view.fragment.TaskFinishViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class TaskFinishActivity extends BaseActivity {
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_xl)
    RecyclerView mRvListXl;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.ll_txt)
    LinearLayout mLlTet;
    @BindView(R.id.tv_xz)
    TextView mTvXz;
    private AAChartModel aaChartModel;
    private ArrayList<BpmDataBean> mBpmDataBeans;
    @Override
    protected int getContentView() {
        return R.layout.activity_task_finish;
    }

    @Override
    protected void initViews() {
        mBpmDataBeans = getIntent().getParcelableArrayListExtra("mBpmDataBeans");
        initVP();
        initRv();
        aaChartModel = configurePieChart();
        mAAChartView.aa_drawChartWithChartModel(aaChartModel);
    }

    AAChartModel configurePieChart() {
        return new AAChartModel()
                .chartType(AAChartType.Pie)
                .title("")
                .yAxisTitle("")
                .backgroundColor("#ffffff")
                .dataLabelsEnabled(true)//是否直接显示扇形图数据
                .legendEnabled(false)
                .series(new AAPie[] {
                                new AAPie()
                                        .name("name")
                                        .innerSize("20%")
                                        .size(150f)
                                        .dataLabels(new AADataLabels()
                                                .enabled(true)
                                                .useHTML(true)
                                                .distance(5f)
                                                .format("<b>{point.name}</b>: <br> {point.percentage:.1f} %"))
                                        .data(new Object[][] {
                                        {mBpmDataBeans.get(0).getName(),67},
                                        {mBpmDataBeans.get(1).getName(),999},
                                        {mBpmDataBeans.get(2).getName(),83},
                                        {mBpmDataBeans.get(3).getName(),11},
                                        {mBpmDataBeans.get(4).getName(),30},
                                        {mBpmDataBeans.get(5).getName(),30},
                                })
                                ,
                        }
                );
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
        list.add("适度");
        list.add("有些强");
        list.add("强");
        list.add("非常强");
        list.add("非常\n非常强");

        TaskFinishPjAdapter mTaskFinishPjAdapter = new TaskFinishPjAdapter(list);
        mTaskFinishPjAdapter.setSelectPos(0);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTaskFinishPjAdapter);

        mTaskFinishPjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTaskFinishPjAdapter.setSelectPos(position);
                mTaskFinishPjAdapter.notifyDataSetChanged();

            }
        });


        TaskFinishListAdapter mTaskFinishListAdapter = new TaskFinishListAdapter(mBpmDataBeans);
        mRvListXl.setLayoutManager(new LinearLayoutManager(this));
        mRvListXl.setHasFixedSize(true);
        mRvListXl.setAdapter(mTaskFinishListAdapter);

        mTaskFinishListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }


    @OnClick({R.id.iv_back, R.id.tv_go_ffhd, R.id.tv_go_xxyx,R.id.tv_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_go_ffhd:
                break;
            case R.id.tv_go_xxyx:
                finish();
                break;
            case R.id.tv_xz:
                String strXz = mTvXz.getText().toString();
                if(strXz.equals("条形图")){
                    mTvXz.setText("饼状图");
                    mAAChartView.setVisibility(View.VISIBLE);
                    mLlTet.setVisibility(View.GONE);
                }else{
                    mTvXz.setText("条形图");
                    mAAChartView.setVisibility(View.GONE);
                    mLlTet.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
