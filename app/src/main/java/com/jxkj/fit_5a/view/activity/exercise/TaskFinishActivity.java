package com.jxkj.fit_5a.view.activity.exercise;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AADataLabels;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAPie;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.view.adapter.TaskFinishListAdapter;
import com.jxkj.fit_5a.view.adapter.TaskFinishPjAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TaskFinishActivity extends BaseActivity {
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_xl)
    RecyclerView mRvListXl;
    @BindView(R.id.ll_txt)
    LinearLayout mLlTet;
    @BindView(R.id.tv_xz)
    TextView mTvXz;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_5)
    TextView mTv5;
    @BindView(R.id.tv_6)
    TextView mTv6;
    @BindView(R.id.tv_ztime)
    TextView tv_ztime;
    @BindView(R.id.vertical_progressbar1)
    ProgressBar mVerticalProgressbar1;
    @BindView(R.id.vertical_progressbar2)
    ProgressBar mVerticalProgressbar2;
    @BindView(R.id.vertical_progressbar3)
    ProgressBar mVerticalProgressbar3;
    @BindView(R.id.vertical_progressbar4)
    ProgressBar mVerticalProgressbar4;
    @BindView(R.id.vertical_progressbar5)
    ProgressBar mVerticalProgressbar5;
    @BindView(R.id.vertical_progressbar6)
    ProgressBar mVerticalProgressbar6;
    private AAChartModel aaChartModel;
    private ArrayList<BpmDataBean> mBpmDataBeans;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_finish;
    }

    @Override
    protected void initViews() {
        mBpmDataBeans = getIntent().getParcelableArrayListExtra("mBpmDataBeans");
        initTopData();
        initRv();
        aaChartModel = configurePieChart();
        mAAChartView.aa_drawChartWithChartModel(aaChartModel);
    }
    int Ztime = 0;
    private void initTopData() {
        BpmDataBean.BpmTopData mBpmTopData = mBpmDataBeans.get(0).getBpmTopData();
        Ztime = Integer.valueOf(mBpmTopData.getDuration());
        mTv1.setText(mBpmTopData.getCalories() + "cal");
        mTv2.setText(mBpmTopData.getDistance() + "km");
        mTv3.setText(StringUtil.getTimeGeShi(Ztime));
        mTv4.setText(mBpmTopData.getPjDuration() + "km/h");
        mTv5.setText(mBpmTopData.getMaxSpeed() + "km/h");
        mTv6.setText(mBpmTopData.getHeartRate() + "次/分钟");
        tv_ztime.setText("总时间："+StringUtil.getTimeGeShi(Ztime));
        DecimalFormat df = new DecimalFormat("######0.00");
        mVerticalProgressbar1.setProgress((int)(Double.valueOf(df.format(mBpmDataBeans.get(0).getTime()/Ztime))*100));
        mVerticalProgressbar1.setProgress((int)(Double.valueOf(df.format(mBpmDataBeans.get(1).getTime()/Ztime))*100));
        mVerticalProgressbar1.setProgress((int)(Double.valueOf(df.format(mBpmDataBeans.get(2).getTime()/Ztime))*100));
        mVerticalProgressbar1.setProgress((int)(Double.valueOf(df.format(mBpmDataBeans.get(3).getTime()/Ztime))*100));
        mVerticalProgressbar1.setProgress((int)(Double.valueOf(df.format(mBpmDataBeans.get(4).getTime()/Ztime))*100));
        mVerticalProgressbar1.setProgress((int)(Double.valueOf(df.format(mBpmDataBeans.get(5).getTime()/Ztime))*100));
    }

    AAChartModel configurePieChart() {
        return new AAChartModel()
                .chartType(AAChartType.Pie)
                .title("")
                .yAxisTitle("")
                .backgroundColor("#ffffff")
                .dataLabelsEnabled(true)//是否直接显示扇形图数据
                .legendEnabled(false)
                .series(new AAPie[]{
                                new AAPie()
                                        .name("心率分析")
                                        .innerSize("20%")
                                        .size(150f)
                                        .dataLabels(new AADataLabels()
                                                .enabled(true)
                                                .useHTML(true)
                                                .distance(5f)
                                                .format("<b>{point.name}</b>: <br> {point.percentage:.1f} %"))
                                        .data(new Object[][]{
                                        {mBpmDataBeans.get(0).getName(), mBpmDataBeans.get(0).getTime()},
                                        {mBpmDataBeans.get(1).getName(), mBpmDataBeans.get(1).getTime()},
                                        {mBpmDataBeans.get(2).getName(), mBpmDataBeans.get(2).getTime()},
                                        {mBpmDataBeans.get(3).getName(), mBpmDataBeans.get(3).getTime()},
                                        {mBpmDataBeans.get(4).getName(), mBpmDataBeans.get(4).getTime()},
                                        {mBpmDataBeans.get(5).getName(), mBpmDataBeans.get(5).getTime()},
                                })
                                ,
                        }
                );
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("没有感觉");
        list.add("非常\n" + "非常弱");
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


        TaskFinishListAdapter mTaskFinishListAdapter = new TaskFinishListAdapter(mBpmDataBeans,Ztime);
        mRvListXl.setLayoutManager(new LinearLayoutManager(this));
        mRvListXl.setHasFixedSize(true);
        mRvListXl.setAdapter(mTaskFinishListAdapter);

        mTaskFinishListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }


    @OnClick({R.id.iv_back, R.id.tv_go_ffhd, R.id.tv_go_xxyx, R.id.tv_xz})
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
                if (strXz.equals("条形图")) {
                    mTvXz.setText("饼状图");
                    mAAChartView.setVisibility(View.VISIBLE);
                    mLlTet.setVisibility(View.GONE);
                } else {
                    mTvXz.setText("条形图");
                    mAAChartView.setVisibility(View.GONE);
                    mLlTet.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
