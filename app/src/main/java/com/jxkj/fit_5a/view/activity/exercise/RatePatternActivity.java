package com.jxkj.fit_5a.view.activity.exercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.StepArcView;
import com.jxkj.fit_5a.entity.RatePatternBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.RatePatternAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RatePatternActivity extends BaseActivity {


    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.sv)
    StepArcView mSv;
    @BindView(R.id.ll_xl)
    ImageView mLlXl;
    @BindView(R.id.ll_zh)
    ImageView mLlZh;
    @BindView(R.id.ll_qd)
    LinearLayout mLlQd;
    @BindView(R.id.tv_top_xl)
    TextView mTvTopXl;
    @BindView(R.id.tv_top_qd)
    TextView mTvTopQd;
    @BindView(R.id.tv_top_zh)
    TextView mTvTopZh;
    @BindView(R.id.tv_v)
    TextView tv_v;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_movingTye)
    TextView tv_movingTye;
    @BindView(R.id.tv_distance)
    TextView tv_distance;
    int loadCurrent = 1;
    int loadMax = ConstValues_Ly.maxLoad;
    private RatePatternAdapter mRatePatternAdapter;

    int currentPos = 1;
    long time = 0;
    private List<Byte> mData1 = new ArrayList<>();
    private List<Byte> mData2 = new ArrayList<>();
    private List<Byte> mData3 = new ArrayList<>();
    private String movingTye;

    @Override
    protected int getContentView() {
        return R.layout.activity_rate_pattern;
    }

    @Override
    protected void initViews() {
        initAAChar();
        if(PopupWindowLanYan.ble4Util==null || !PopupWindowLanYan.ble4Util.isConnect()){
            ToastUtils.showShort("请先链接设备");
            finish();
            return;
        }
        movingTye = getIntent().getStringExtra("movingTye");
        if(StringUtil.isNotBlank(movingTye)){
            tv_movingTye.setText(movingTye);
        }
        if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]) ){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1]){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5));
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2]){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }
        startTimestamp = System.currentTimeMillis();
        time = getIntent().getLongExtra("time",0);
        if(time>0){
            byte[] data =  getIntent().getByteArrayExtra("data");
            TimeThreadUtils.sendDataA6(time,data);
        }
        mSv.setCurrentCount(100, 80);
        mRatePatternAdapter = new RatePatternAdapter(null);
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mRatePatternAdapter);

        mRatePatternAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        /**
         * 广播动态注册
         */
        mMyReceiver = new MyReceiver();//集成广播的类
        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
        registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive

    }
    MyReceiver mMyReceiver;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMyReceiver!=null){
            unregisterReceiver(mMyReceiver);
        }
    }

    private AAOptions aaOptions;
    private AAChartModel aaChartModel;
    private void initAAChar() {

        AAChartModel aaChartModel = configureChartModel();
        if (aaOptions == null) {
            aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        }
        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
    }


    private AAChartModel configureChartModel() {
        aaChartModel = new AAChartModel()
                .chartType(AAChartType.Areaspline)
                .title("")
                .yAxisTitle("")
                .yAxisLabelsEnabled(false)
                .xAxisLabelsEnabled(true)
                .yAxisGridLineWidth(0f)
                .xAxisVisible(false)
                .legendEnabled(false)
                .markerRadius(0f)
                .markerSymbol(AAChartSymbolType.Circle)
                .scrollablePlotArea(
                        new AAScrollablePlotArea()
                                .minWidth(50)
                                .scrollPositionX(1f)
                )
                .series(getDataList(mData1,mData2,mData3));
        return aaChartModel;
    }

    private AASeriesElement[] getDataList(List<Byte> data1,List<Byte> data2,List<Byte> data3){
        AASeriesElement element = null;
        if(currentPos==1){
            element = new AASeriesElement()
                    .name("心率")
                    .lineWidth(1f)
                    .color("#FFA1A1")
                    .data(data1.toArray());
        }else if(currentPos==2){
            element = new AASeriesElement()
                    .name("强度")
                    .lineWidth(1f)
                    .color("#A1DFFF")
                    .data(data2.toArray());
        }else if(currentPos==3){
            element = new AASeriesElement()
                    .name("综合")
                    .lineWidth(1f)
                    .color("#FFB300")
                    .data(data3.toArray());
        }

        return new AASeriesElement[]{element};
    }

    @OnClick({R.id.iv_back,R.id.iv_jian,R.id.iv_jia, R.id.view,R.id.tv_top_xl, R.id.tv_top_qd, R.id.tv_top_zh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_jian:
                if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                        && loadCurrent>1){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent-1)));
                }
                break;
            case R.id.iv_jia:
                if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                        && loadCurrent<loadMax){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent+1)));
                }
                break;
            case R.id.view:
                if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]) ){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                }
                DialogUtils.showDialogStartYd(this, new DialogUtils.DialogInterfaceS() {
                    @Override
                    public void btnType(int pos) {
                        if (pos == 2) {
                            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                            startActivity(new Intent(RatePatternActivity.this, TaskFinishActivity.class));
                            finish();
                            PostUser.SportLogInfo sportLogInfo= new PostUser.SportLogInfo();
                            sportLogInfo.setBai("11");
                            sportLogInfo.setBrandId(ConstValues_Ly.BRAND_ID);
                            sportLogInfo.setCalories(String.valueOf(Calories));
                            sportLogInfo.setDeviceType(ConstValues_Ly.DEVICE_TYPE_ID);
                            sportLogInfo.setDistance(String.valueOf(Distance));
                            sportLogInfo.setDuration(String.valueOf(duration));
                            sportLogInfo.setEndTimestamp(String.valueOf(System.currentTimeMillis()));
                            sportLogInfo.setStartTimestamp(String.valueOf(startTimestamp));
                            sportLogInfo.setHeartRateSource("2");//1=器材;2=藍牙心跳;3=Apple Watch

                            if(StringUtil.isNotBlank(movingTye)){
                                sportLogInfo.setTrainingMode("HeartRate");//目前只有HeartRate(心率)、Program(课程)
                            }else{
                                sportLogInfo.setTrainingMode("Program");
                            }
                            PostUser.SportLogInfo.DetailsBean deleteDatabase = new PostUser.SportLogInfo.DetailsBean();
                            deleteDatabase.setLogs(logs);
                            sportLogInfo.setDetails(deleteDatabase);
                            HttpRequestUtils.psotUserSportLog(sportLogInfo);
                            PopupWindowLanYan.ble4Util.disconnect();
                        }else{
                            if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]) ){
                                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                            }
                            dismiss();
                        }
                    }
                });
                break;
            case R.id.tv_top_xl:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_text_theme));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_666666));
                mLlXl.setVisibility(View.VISIBLE);
                mLlZh.setVisibility(View.INVISIBLE);
                mLlQd.setVisibility(View.INVISIBLE);
                currentPos = 1;
                aaChartModel.series(getDataList(mData1,mData2,mData3));
                mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
                break;
            case R.id.tv_top_qd:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_text_theme));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_666666));
                mLlXl.setVisibility(View.INVISIBLE);
                mLlZh.setVisibility(View.INVISIBLE);
                mLlQd.setVisibility(View.VISIBLE);
                currentPos = 2;
                aaChartModel.series(getDataList(mData1,mData2,mData3));
                mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
                break;
            case R.id.tv_top_zh:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_text_theme));
                mLlXl.setVisibility(View.INVISIBLE);
                mLlZh.setVisibility(View.VISIBLE);
                mLlQd.setVisibility(View.INVISIBLE);
                currentPos = 3;
                aaChartModel.series(getDataList(mData1,mData2,mData3));
                mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
                break;
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("MyTag", "onReceive: "+intent.getStringExtra("type"));
            if(intent.getStringExtra("type").equals("b2")){
                ArrayList<Integer> dataList = intent.getIntegerArrayListExtra("data");
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] && dataList.size()==16){
                    setData1(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1] && dataList.size()==14){
                    setData56(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2] && dataList.size()==14){
//                    setData76(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3] && dataList.size()==18){
                    setData26(dataList);
                }
            }
        }
    }
    int Calories;
    double Distance;
    int duration;
    long startTimestamp;

    List<PostUser.SportLogInfo.DetailsBean.LogsBean> logs = new ArrayList<>();
    private void setData1(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String time = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int rpmHi = dataList.get(4);//每分钟转数 -千百
        int rpmLow = dataList.get(5);//每分钟转数 -十个
        int rpm = ConstValues_Ly.getQianBaiShiGe(rpmHi,rpmLow);

        int DistanceHi = dataList.get(6);//距离-百十
        int DistanceLow = dataList.get(7);//距离-个小数点下一位
        Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);
        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

        loadCurrent = dataList.get(14);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(15);
        String Unit ="Stop";
        if(dataList.get(15)==1){
            Unit ="Start";
        }

        String re = "A2--->>>:时间："+time+",速度："+speed+",转数："+rpm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",阻力："+loadCurrent+",状态："+Unit;
        Log.w("---》》》", re);
        tv_v.setText(loadCurrent+"/"+loadMax);
        tv_time.setText(time);
        tv_distance.setText(Distance+"KM");
        List<RatePatternBean> list = new ArrayList<>();
        list.add(new RatePatternBean("卡路里",Calories+"cal"));
        list.add(new RatePatternBean("当前速度",speed+"km/h"));
        list.add(new RatePatternBean("当前功率",Watt+"w"));
        list.add(new RatePatternBean("当前段位",loadCurrent+""));
        list.add(new RatePatternBean("RPM/SPM","--"));

        mRatePatternAdapter.setNewData(list);
        mRatePatternAdapter.notifyDataSetChanged();

        mData1.add((byte) Pulse);
        mData2.add((byte) rpm);
        mData3.add((byte) Watt);
        List<Byte> mData11= new ArrayList<>();
        if(mData1.size()>10){
            mData11.addAll(mData1.subList(Math.max(mData1.size() - 10, 0), mData1.size()));
        }else{
            mData11.addAll(mData1);
        }

        List<Byte> mData22= new ArrayList<>();
        if(mData2.size()>10){
            mData22.addAll(mData2.subList(Math.max(mData2.size() - 10, 0), mData2.size()));
        }else{
            mData22.addAll(mData2);
        }

        List<Byte> mData33= new ArrayList<>();
        if(mData3.size()>10){
            mData33.addAll(mData3.subList(Math.max(mData3.size() - 10, 0), mData3.size()));
        }else{
            mData33.addAll(mData3);
        }

        mAAChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(getDataList(mData11,mData22,mData33));

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(
                String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm),String.valueOf(speed),String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
        return;
    }


    private void setData56(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String time = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int rpm1Hi = dataList.get(4);//每分钟转数 -千百
        int rpm1Low = dataList.get(5);//每分钟转数 -十个
        int rpm1 = ConstValues_Ly.getQianBaiShiGe(rpm1Hi,rpm1Low);

        int rpm2Hi = dataList.get(6);//每分钟转数 -千百
        int rpm2Low = dataList.get(7);//每分钟转数 -十个
        int rpm2 = ConstValues_Ly.getQianBaiShiGe(rpm2Hi,rpm2Low);

        int DistanceHi = dataList.get(8);//距离-百十
        int DistanceLow = dataList.get(9);//距离-个小数点下一位
        Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(10);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(11);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(12);//跳动 千,佰
        int PulseLow = dataList.get(13);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        String re = "A2--->>>:时间："+time+",速度："+speed+",转数1："+rpm1+",转数2："+rpm2+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse;
        Log.w("---》》》", re);
        tv_v.setText(loadCurrent+"/"+loadMax);
        tv_time.setText(time);
        tv_distance.setText(Distance+"KM");
        List<RatePatternBean> list = new ArrayList<>();
        list.add(new RatePatternBean("卡路里",Calories+"cal"));
        list.add(new RatePatternBean("当前速度",speed+"km/h"));
        list.add(new RatePatternBean("1每分钟",rpm1+"转"));
        list.add(new RatePatternBean("2每分钟",rpm2+"转"));
        list.add(new RatePatternBean("心率",Pulse+""));

        mRatePatternAdapter.setNewData(list);
        mRatePatternAdapter.notifyDataSetChanged();

        mData1.add((byte) Pulse);
        mData2.add((byte) rpm1);
        mData3.add((byte) rpm2);
        List<Byte> mData11= new ArrayList<>();
        if(mData1.size()>10){
            mData11.addAll(mData1.subList(Math.max(mData1.size() - 10, 0), mData1.size()));
        }else{
            mData11.addAll(mData1);
        }

        List<Byte> mData22= new ArrayList<>();
        if(mData2.size()>10){
            mData22.addAll(mData2.subList(Math.max(mData2.size() - 10, 0), mData2.size()));
        }else{
            mData22.addAll(mData2);
        }

        List<Byte> mData33= new ArrayList<>();
        if(mData3.size()>10){
            mData33.addAll(mData3.subList(Math.max(mData3.size() - 10, 0), mData3.size()));
        }else{
            mData33.addAll(mData3);
        }

        mAAChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(getDataList(mData11,mData22,mData33));

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(
                String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm1),String.valueOf(speed),String.valueOf(System.currentTimeMillis()),null));
        return;
    }

    private void setData26(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String time = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int strokeHi = dataList.get(2);
        int strokeLow = dataList.get(3);
        double stroke = ConstValues_Ly.getQianBaiShiGe(strokeHi,strokeLow);

        int spmHi = dataList.get(4);
        int spmLow = dataList.get(5);
        int spm = ConstValues_Ly.getQianBaiShiGe(spmHi,spmLow);

        int DistanceHi = dataList.get(6);
        int DistanceLow = dataList.get(7);
        Distance = ConstValues_Ly.getQianBaiShiGe(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

        int timeMinute1 =  dataList.get(14);//时间-分
        int timeSecond1 =  dataList.get(15);//时间-秒
//        int duration1 = timeMinute * 60 + timeSecond;
        String time1 = ConstValues_Ly.getTime(timeMinute1,timeSecond1);

        loadCurrent = dataList.get(16);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(17);

        String Unit ="Stop";
        if(dataList.get(17)==1){
            Unit ="Start";
        }

        String re = "A2--->>>:时间："+time+",行程："+stroke+",spm："+spm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",阻力："+loadCurrent+",状态："+Unit;
        Log.w("---》》》", re);
        tv_v.setText(loadCurrent+"/"+loadMax);
        tv_time.setText(time);
        tv_distance.setText(Distance+"KM");
        List<RatePatternBean> list = new ArrayList<>();
        list.add(new RatePatternBean("卡路里",Calories+"cal"));
        list.add(new RatePatternBean("当前速度",stroke+"km/h"));
        list.add(new RatePatternBean("当前功率",Watt+"w"));
        list.add(new RatePatternBean("当前段位",loadCurrent+""));
        list.add(new RatePatternBean("RPM/SPM","--"));

        mRatePatternAdapter.setNewData(list);
        mRatePatternAdapter.notifyDataSetChanged();

        mData1.add((byte) Pulse);
        mData2.add((byte) spm);
        mData3.add((byte) Watt);
        List<Byte> mData11= new ArrayList<>();
        if(mData1.size()>10){
            mData11.addAll(mData1.subList(Math.max(mData1.size() - 10, 0), mData1.size()));
        }else{
            mData11.addAll(mData1);
        }

        List<Byte> mData22= new ArrayList<>();
        if(mData2.size()>10){
            mData22.addAll(mData2.subList(Math.max(mData2.size() - 10, 0), mData2.size()));
        }else{
            mData22.addAll(mData2);
        }

        List<Byte> mData33= new ArrayList<>();
        if(mData3.size()>10){
            mData33.addAll(mData3.subList(Math.max(mData3.size() - 10, 0), mData3.size()));
        }else{
            mData33.addAll(mData3);
        }

        mAAChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(getDataList(mData11,mData22,mData33));

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(
                String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(spm),String.valueOf(stroke),String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
        return;
    }
}
