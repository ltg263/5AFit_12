package com.jxkj.fit_5a.view.activity.exercise;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AATooltip;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.SportLogDetailBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.adapter.ExerciseRecordAdapter;
import com.jxkj.fit_5a.view.adapter.TaskFinishListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ExerciseRecordDetailsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_trainingMode)
    TextView mTvTrainingMode;
    @BindView(R.id.tv_duration)
    TextView tv_duration;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_mansu)
    TextView mTvMansu;
    @BindView(R.id.tv_kuaisu)
    TextView mTvKuaisu;
    @BindView(R.id.tv_xz)
    TextView mTvXz;
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.vertical_progressbar1)
    ProgressBar mVerticalProgressbar1;
    @BindView(R.id.tv_bfb1)
    TextView mTvBfb1;
    @BindView(R.id.tv_sj1)
    TextView mTvSj1;
    @BindView(R.id.vertical_progressbar2)
    ProgressBar mVerticalProgressbar2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv_bfb2)
    TextView mTvBfb2;
    @BindView(R.id.tv_sj2)
    TextView mTvSj2;
    @BindView(R.id.vertical_progressbar3)
    ProgressBar mVerticalProgressbar3;
    @BindView(R.id.view3)
    View mView3;
    @BindView(R.id.tv_bfb3)
    TextView mTvBfb3;
    @BindView(R.id.tv_sj3)
    TextView mTvSj3;
    @BindView(R.id.vertical_progressbar4)
    ProgressBar mVerticalProgressbar4;
    @BindView(R.id.view4)
    View mView4;
    @BindView(R.id.tv_bfb4)
    TextView mTvBfb4;
    @BindView(R.id.tv_sj4)
    TextView mTvSj4;
    @BindView(R.id.vertical_progressbar5)
    ProgressBar mVerticalProgressbar5;
    @BindView(R.id.view5)
    View mView5;
    @BindView(R.id.tv_bfb5)
    TextView mTvBfb5;
    @BindView(R.id.tv_sj5)
    TextView mTvSj5;
    @BindView(R.id.vertical_progressbar6)
    ProgressBar mVerticalProgressbar6;
    @BindView(R.id.view6)
    View mView6;
    @BindView(R.id.tv_bfb6)
    TextView mTvBfb6;
    @BindView(R.id.tv_sj6)
    TextView mTvSj6;
    @BindView(R.id.ll_txt)
    LinearLayout mLlTxt;
    @BindView(R.id.tvsd)
    TextView mTvsd;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.AAChartViewB)
    AAChartView mAAChartViewB;
    @BindView(R.id.tv_zgsd)
    TextView tv_zgsd;
    @BindView(R.id.tv_pjsd)
    TextView tv_pjsd;
    @BindView(R.id.tv_ztime)
    TextView mTvZtime;
    @BindView(R.id.view_l)
    View mViewL;
    @BindView(R.id.rv_list_xl)
    RecyclerView mRvListXl;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private ExerciseRecordAdapter mExerciseRecordAdapter;
    private TaskFinishListAdapter mTaskFinishListAdapter;

    double bfb5,bfb6,bfb7,bfb8,bfb9,bfb;
    int maxV = 220;
    int age = 40;
    private List<BpmDataBean> mBpmDataBeans = new ArrayList<>();
    private AAChartModel aaChartModel;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_record_details;
    }

    @Override
    protected void initViews() {


        bfb5 = (maxV-age)*0.5+40;
        bfb6 = (maxV-age)*0.6+40;
        bfb7 = (maxV-age)*0.7+40;
        bfb8 = (maxV-age)*0.8+40;
        bfb9 = (maxV-age)*0.9+40;
        bfb  = (maxV-age)*1+40;
        initBpmData();

        mExerciseRecordAdapter = new ExerciseRecordAdapter(null);
        rv_list.setLayoutManager(new GridLayoutManager(this, 3));
        rv_list.setHasFixedSize(true);
        rv_list.setAdapter(mExerciseRecordAdapter);

        mExerciseRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mTaskFinishListAdapter = new TaskFinishListAdapter(null);
        mRvListXl.setLayoutManager(new LinearLayoutManager(this));
        mRvListXl.setHasFixedSize(true);
        mRvListXl.setAdapter(mTaskFinishListAdapter);

        mTaskFinishListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        geSportLogDetails();
    }

    private void initBpmData() {
        mBpmDataBeans.add(new BpmDataBean("非运动区间(0~50%)",0,bfb5,0));
        mBpmDataBeans.add(new BpmDataBean("热身心率区间(50~60%)",bfb5,bfb6,0));
        mBpmDataBeans.add(new BpmDataBean("燃脂心率区间(60~70%)",bfb6,bfb7,0));
        mBpmDataBeans.add(new BpmDataBean("有氧耐力心率区间(70~80%)",bfb7,bfb8,0));
        mBpmDataBeans.add(new BpmDataBean("无氧耐力心率区间(80~90%)",bfb8,bfb9,0));
        mBpmDataBeans.add(new BpmDataBean("极限心率区间(90~100%)",bfb9,bfb,0));

    }


    private void geSportLogDetails() {
        RetrofitUtil.getInstance().apiService()
                .geSportLogDetails(getIntent().getStringExtra("id"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogDetailBean> result) {
                        initData(result.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData(SportLogDetailBean data) {
        mTvDistance.setText(data.getDistance());
        mTvTrainingMode.setText(data.getTrainingMode());
        mTvTime.setText(StringUtil.getTimeToYMD(Long.valueOf(data.getCreateTimestamp()),"yyyy-MM-dd"));
        tv_duration.setText(StringUtil.getTimeGeShi(Long.valueOf(data.getDuration())));
        mTvMansu.setText("最慢"+StringUtil.getValue(data.getMinSpeed()));
        mTvKuaisu.setText("最快"+StringUtil.getValue(data.getMaxSpeed()));
        mTvZtime.setText("总时间："+StringUtil.getTimeGeShi(Long.valueOf(data.getDuration())));
        tv_zgsd.setText(StringUtil.getValue(data.getMaxSpeed())+"km/h");
        tv_pjsd.setText(StringUtil.getValue(data.getAvgSpeed())+"km/h");
        List<String> list = new ArrayList<>();
        list.add("卡路里");
        list.add("路程");
        list.add("时间");
        list.add("平均速度");
        list.add("最大速度");
        list.add("平均心跳");
        list.add("功率");
        list.add("强度");
        list.add("心率区间");
        mExerciseRecordAdapter.setNewData(list,data);
        mTaskFinishListAdapter.setZtime(Integer.valueOf(data.getDuration()));
        mTaskFinishListAdapter.setNewData(mBpmDataBeans);

        initAAChar(data.getDetails().getLogs());
        
    }

    private void initAAChar(List<SportLogDetailBean.DetailsBean.LogsBean> logs) {
        AAChartModel aaChartModel = configureChartModel(logs);
        AATooltip aaTooltip = new AATooltip()
                .useHTML(true)
                .formatter("function () {\n" +
                        "function getDay(day){\n" +
                        "        var h = Math.floor(day / 3600) < 10 ? '0'+Math.floor(day / 3600) : Math.floor(day / 3600);\n" +
                        "        var m = Math.floor((day / 60 % 60)) < 10 ? Math.floor((day / 60 % 60)) : Math.floor((day / 60 % 60));\n" +
                        "        var s = Math.floor((day % 60)) < 10 ? '0' + Math.floor((day % 60)) : Math.floor((day % 60));\n" +
                        "        var str = day+'秒';\n" +
                        "        if(h == \"00\"){\n" +
                        "            str = m + '分' + s +'秒';\n" +
                        "        }else{" +
                        "            str = h + '时' + m + '分' + s +'秒';\n" +
                        "        }\n" +
                        "    return str;" +
                        "       }\n" +
                        "        var s0 = '' + '<b>' +  getDay(this.x) + '</b>' + '<br/>';\n" +
                        "        var colorDot1 = '<span style=\\\"' + 'color:#FFB300; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var s1 = colorDot1 + this.points[0].series.name + ': ' + this.points[0].y+'km/h' + '<br/>';\n" +
                        "        s0 += s1;\n" +
                        "        return s0;\n" +
                        "    }");
        aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        aaOptions.tooltip(aaTooltip);
        mAAChartViewB.aa_drawChartWithChartOptions(aaOptions);
    }
    AAOptions aaOptions;
    private AAChartModel configureChartModel(List<SportLogDetailBean.DetailsBean.LogsBean> lists) {
        String[] str = new String[lists.size()];
        Double[] strV = new Double[lists.size()];
        for(int i=0;i<lists.size();i++){
            str[i] = i+"";
            strV[i] = Double.valueOf(lists.get(i).getSpeed());
        }
        aaChartModel = new AAChartModel()
                .chartType(AAChartType.Areaspline)
                .title("")
                .yAxisTitle("")
                .yAxisLabelsEnabled(false)
                .categories(str)
                .yAxisGridLineWidth(0f)
                .xAxisGridLineWidth(0f)
                .legendEnabled(false)
                .yAxisGridLineWidth(0f)
                .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)
                .gradientColorEnable(true)
                .markerRadius(0f)
                .markerSymbol(AAChartSymbolType.Circle)
                .scrollablePlotArea(
                        new AAScrollablePlotArea()
                                .minWidth(2000)
                                .scrollPositionX(1f)
                )
                .series(new AASeriesElement[]{new AASeriesElement()
                        .name("速度：")
                        .lineWidth(1f)
                        .fillOpacity(0.5f)
                        .color("#FFB300")
                        .data(strV)});
        return aaChartModel;
    }


    @OnClick({R.id.iv_back, R.id.rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.rl:
            case R.id.tv_xz:
                String strXz = mTvXz.getText().toString();
                if (strXz.equals("条形图")) {
                    mTvXz.setText("饼状图");
                    mAAChartView.setVisibility(View.VISIBLE);
                    mLlTxt.setVisibility(View.GONE);
                } else {
                    mTvXz.setText("条形图");
                    mAAChartView.setVisibility(View.GONE);
                    mLlTxt.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
