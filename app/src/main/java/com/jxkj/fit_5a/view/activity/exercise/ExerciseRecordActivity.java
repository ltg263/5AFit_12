package com.jxkj.fit_5a.view.activity.exercise;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartViewOne;
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
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.PopupWindowSb;
import com.jxkj.fit_5a.entity.SportLogBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.jxkj.fit_5a.view.adapter.TwoJlxqAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExerciseRecordActivity extends BaseActivity {
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_jlxq_list)
    RecyclerView mRvJlxqList;
    @BindView(R.id.AAChartView)
    AAChartViewOne mAAChartView;
    @BindView(R.id.tv_top_jyz)
    TextView mTvTopJyz;
    @BindView(R.id.tv_sb)
    TextView tv_sb;
    @BindView(R.id.tv_top_jyy)
    TextView mTvTopJyy;
    @BindView(R.id.iv_z)
    ImageView mIvZ;
    @BindView(R.id.iv_y)
    ImageView mIvY;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private TwoJlxqAdapter mTwoJlxqAdapter;
    String[] str7, str30;
    private AAChartModel aaChartModel;
    private HomeTopAdapter mHomeTopAdapter;
    String deviceType = null;
    private PopupWindowSb window;
    private List<DeviceTypeData.ListBean> dataSbType;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_record;
    }

    @Override
    protected void initViews() {
        str7 = StringUtil.getDays(7, "dd").toArray(new String[7]);
        str30 = StringUtil.getDays(30, "dd").toArray(new String[30]);

        mHomeTopAdapter = new HomeTopAdapter(null);
        mRvTopList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mTwoJlxqAdapter = new TwoJlxqAdapter(null);
        mRvJlxqList.setLayoutManager(new LinearLayoutManager(this));
        mRvJlxqList.setHasFixedSize(true);
        mRvJlxqList.setAdapter(mTwoJlxqAdapter);

        mTwoJlxqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
        getSportLogStats(null);
        getTemplateList();
        queryDeviceTypeLists();

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getTemplateList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getTemplateList();
            }
        });
    }

    private int page = 1;
    private AAOptions aaOptions;

    private void initAAChar(List<SportLogStatsBean.ListBean> lists) {
        AAChartModel aaChartModel = configureChartModel(lists);
//        if (aaOptions == null) {
        AATooltip aaTooltip = new AATooltip()
                .useHTML(true)
                .formatter("function () {\n" +
                        "function getDay(day){\n" +
                        "    var today = new Date();\n" +
                        "    var targetday_milliseconds=today.getTime() + 1000*60*60*24;\n" +
                        "    today.setTime(targetday_milliseconds);\n" +
                        "    var tYear = today.getFullYear();\n" +
                        "    var tMonth = today.getMonth();\n" +
                        "    tMonth = doHandleMonth(tMonth + 1);\n" +
                        "     if(new Date().getTime() < new Date(tYear+\"-\"+tMonth+\"-\"+day).getTime()){\n" +
                        "        tMonth = doHandleMonth(today.getMonth());\n" +
                        "     }\n" +
                        "    day = doHandleMonth(day);\n" +
                        "    return tYear+\"-\"+tMonth+\"-\"+day;\n" +
                        "}\n" +
                        "function doHandleMonth(month){\n" +
                        "    var m = month;\n" +
                        "    if(month.toString().length == 1){\n" +
                        "     m = \"0\" + month;\n" +
                        "    }\n" +
                        "    return m;\n" +
                        "}" +
                        "        var h = Math.floor(this.points[0].y / 3600) < 10 ? '0'+Math.floor(this.points[0].y / 3600) : Math.floor(this.points[0].y / 3600);\n" +
                        "        var m = Math.floor((this.points[0].y / 60 % 60)) < 10 ? '0' + Math.floor((this.points[0].y / 60 % 60)) : Math.floor((this.points[0].y / 60 % 60));\n" +
                        "        var s = Math.floor((this.points[0].y % 60)) < 10 ? '0' + Math.floor((this.points[0].y % 60)) : Math.floor((this.points[0].y % 60));\n" +
                        "        var str =  '';\n" +
                        "        if(h == \"00\"){\n" +
                        "            str = m + '分' + s +'秒';\n" +
                        "        }else{" +
                        "            str = h + '时' + m + '分' + s +'秒';\n" +
                        "        }\n" +
                        "        str = this.points[0].y+'秒';\n" +
                        "        str = str.replace('.','分');\n"+
                        "        var s0 = '' + '<b>' +  getDay(this.x) + '</b>' + '<br/>';\n" +
                        "        var colorDot1 = '<span style=\\\"' + 'color:#FFB300; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var colorDot2 = '<span style=\\\"' + 'color:#FFA1A1; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var colorDot3 = '<span style=\\\"' + 'color:#A1DFFF; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var s1 = colorDot1 + this.points[0].series.name + ': ' + str + '<br/>';\n" +
                        "        var s2 = colorDot2 + this.points[1].series.name + ': ' + this.points[1].y + 'cal' + '<br/>';\n" +
                        "        var s3 = colorDot3 + this.points[2].series.name + ': ' + this.points[2].y + 'km';\n" +
                        "        s0 += (s1 + s2+ s3);\n" +
                        "        return s0;\n" +
                        "    }");
        aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        aaOptions.tooltip(aaTooltip);
//        }
        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
    }


    private AAChartModel configureChartModel(List<SportLogStatsBean.ListBean> lists) {
        String[] str;
        if (!isDay7) {
            str = str30;
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
                                    .minWidth(1000)
                                    .scrollPositionX(1f)
                    )
                    .series(configureTheStyleForDifferentTypeChart(lists));
        } else {
            str = str7;
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
                                    .scrollPositionX(1f)
                    )
                    .series(configureTheStyleForDifferentTypeChart(lists));
        }
        return aaChartModel;
    }

    private AASeriesElement[] configureTheStyleForDifferentTypeChart(List<SportLogStatsBean.ListBean> lists) {
        ArrayList<String> a = StringUtil.getDays(7, "yyyyMMdd");
        if (!isDay7) {
            a.clear();
            a = StringUtil.getDays(30, "yyyyMMdd");
        }
        Object[] ydsc = new Object[a.size()];
        Object[] kll = new Object[a.size()];
        Object[] zlc = new Object[a.size()];
        for (int i = 0; i < a.size(); i++) {
            if (lists == null || lists.size() == 0) {
                ydsc[i] = 0;
                kll[i] = 0;
                zlc[i] = 0;
                continue;
            }
            for (int j = 0; j < lists.size(); j++) {
                ydsc[i] = 0;
                kll[i] = 0;
                zlc[i] = 0;
                if (a.get(i).equals(lists.get(j).getDatestr())) {
                    ydsc[i] = StringUtil.getTimeFenMiao(lists.get(j).getTotalDuration());
                    kll[i] = lists.get(j).getTotalCalories();
                    zlc[i] = lists.get(j).getTotalDistance();
                    break;
                }

            }
        }
        AASeriesElement element1 = new AASeriesElement()
                .name("运动时长")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#FFB300")
                .data(ydsc);

        AASeriesElement element2 = new AASeriesElement()
                .name("卡路里")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#FFA1A1")
                .data(kll);

        AASeriesElement element3 = new AASeriesElement()
                .name("总里程")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#A1DFFF")
                .data(zlc);

        return new AASeriesElement[]{element1, element2, element3};
    }

    private void getTemplateList() {
        RetrofitUtil.getInstance().apiService()
                .geSportLogList(deviceType,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(page==1){
                                mTwoJlxqAdapter.setNewData(result.getData().getList());
                            }else{
                                mTwoJlxqAdapter.addData(result.getData().getList());
                            }
                            mTwoJlxqAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();
                            int totalPage = StringUtil.getTotalPage(result.getData().getTotalCount(), ConstValues.PAGE_SIZE);
                            if(totalPage <= page){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private boolean isDay7 = true;

    private void getSportLogStats(String deviceType) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        if (isDay7) {
            calendar.add(Calendar.DAY_OF_YEAR, -7);
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, -30);
        }

        RetrofitUtil.getInstance().apiService()
                .getSportLogStats(String.valueOf(calendar.getTime().getTime()), String.valueOf(System.currentTimeMillis()), deviceType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogStatsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogStatsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<SportLogStatsBean.ListBean> lists = result.getData().getList();
                            initAAChar(lists);
                            List<String> list = new ArrayList<>();
                            list.add("总卡路里");
                            list.add("平均卡路里");
                            list.add("总里程");
                            list.add("总计时间");
                            list.add("平均时间");
                            list.add("BAI");
                            mHomeTopAdapter.setNewData(list, result.getData().getStats());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    @OnClick({R.id.iv_back, R.id.tv_sb, R.id.tv_top_jyz, R.id.tv_top_jyy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sb:
                if(dataSbType!=null &&dataSbType.size()>0){
                    popupWindw();
                }
                break;
            case R.id.tv_top_jyz:
                mIvY.setVisibility(View.INVISIBLE);
                mIvZ.setVisibility(View.VISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.black));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.color_666666));
                isDay7 = true;
                getSportLogStats(null);
                break;
            case R.id.tv_top_jyy:
                mIvY.setVisibility(View.VISIBLE);
                mIvZ.setVisibility(View.INVISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.black));
                isDay7 = false;
                getSportLogStats(null);
                break;
        }
    }

    private void queryDeviceTypeLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceTypeLists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceTypeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceTypeData> result) {
                        if (isDataInfoSucceed(result)) {
                            dataSbType = result.getData().getList();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void popupWindw() {
        if(window!=null && !window.isShowing()){
            window.showAsDropDown(tv_sb, Gravity.BOTTOM,  0, 0);
            return;
        }
        if(window != null) {
            window.dismiss();
            return;
        }
        window = new PopupWindowSb(this, dataSbType,(topicId, str) -> {
            window.dismiss();
            deviceType = topicId+"";
            tv_sb.setText(str);
            page = 1;
            getTemplateList();
        });
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setOutsideTouchable(true);
        window.showAsDropDown(tv_sb, Gravity.BOTTOM,  0, 0);
    }
}
