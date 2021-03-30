package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;

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
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.view.adapter.TaskFinishListHpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MapFinish2Fragment extends BaseFragment {

    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private ArrayList<BpmDataBean> mBpmDataBeans;

    @Override
    protected int getContentView() {
        return R.layout.fragment_map_finish_2;
    }

    @Override
    protected void initViews() {

        Bundle bundle = getArguments();
        int type = 0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        List<Double> lists = new ArrayList<>();
        lists.add(0d);
        lists.add(0d);
        lists.add(0d);
        lists.add(0d);
        lists.add(0d);
        lists.add(0d);
        initAAChar(lists);
        mBpmDataBeans = bundle.getParcelableArrayList("mBpmDataBeans");
        mBpmDataBeans = new ArrayList<>();

        mBpmDataBeans.add(new BpmDataBean("非运动区间(0~50%)",0,1,0));
        mBpmDataBeans.add(new BpmDataBean("热身心率区间(50~60%)",1,1,0));
        mBpmDataBeans.add(new BpmDataBean("燃脂心率区间(60~70%)",1,1,0));
        mBpmDataBeans.add(new BpmDataBean("有氧耐力心率区间(70~80%)",1,1,0));
        mBpmDataBeans.add(new BpmDataBean("无氧耐力心率区间(80~90%)",1,1,0));
        mBpmDataBeans.add(new BpmDataBean("极限心率区间(90~100%)",1,1,0));
        setListRv();

    }

    private void setListRv() {

        TaskFinishListHpAdapter mTaskFinishListHpAdapter = new TaskFinishListHpAdapter(mBpmDataBeans,123456);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTaskFinishListHpAdapter);

        mTaskFinishListHpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


    @Override
    public void initImmersionBar() {

    }

    private AAChartModel aaChartModel;
    private AAOptions aaOptions;

    private void initAAChar(List<Double> lists) {
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
                        "        str = str.replace('.','分');\n" +
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

    private AAChartModel configureChartModel( List<Double> lists) {
        aaChartModel = new AAChartModel()
                .chartType(AAChartType.Areaspline)
                .title("")
                .yAxisTitle("")
                .yAxisLabelsEnabled(false)
                .categories(StringUtil.getDays(30, "dd").toArray(new String[30]))
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
                                .minWidth(StringUtil.getDays(30, "dd").toArray(new String[30]).length*50)
                                .scrollPositionX(1f)
                )
                .series(configureTheStyleForDifferentTypeChart(lists));
        return aaChartModel;
    }
    private AASeriesElement[] configureTheStyleForDifferentTypeChart(List<Double> lists) {
        ArrayList<String> a = StringUtil.getDays(30, "yyyyMMdd");
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
                if (a.get(i).equals(lists.get(j))) {
                    ydsc[i] = lists.get(j);
                    kll[i] = lists.get(j);
                    zlc[i] = lists.get(j);
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
}
