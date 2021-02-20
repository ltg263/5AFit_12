package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceCourseData;
import com.jxkj.fit_5a.base.DeviceCourseTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.VerticalSeekBar;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoursePatternActivity extends BaseActivity {

    List<String> list = new ArrayList<>();
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_v)
    TextView mTvV;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.vertical_progressbar1)
    VerticalSeekBar mVerticalProgressbar1;
    @BindView(R.id.vertical_progressbar2)
    VerticalSeekBar mVerticalProgressbar2;
    @BindView(R.id.vertical_progressbar3)
    VerticalSeekBar mVerticalProgressbar3;
    @BindView(R.id.vertical_progressbar4)
    VerticalSeekBar mVerticalProgressbar4;
    @BindView(R.id.vertical_progressbar5)
    VerticalSeekBar mVerticalProgressbar5;
    @BindView(R.id.vertical_progressbar6)
    VerticalSeekBar mVerticalProgressbar6;
    @BindView(R.id.vertical_progressbar7)
    VerticalSeekBar mVerticalProgressbar7;
    @BindView(R.id.vertical_progressbar8)
    VerticalSeekBar mVerticalProgressbar8;
    @BindView(R.id.vertical_progressbar9)
    VerticalSeekBar mVerticalProgressbar9;
    @BindView(R.id.vertical_progressbar10)
    VerticalSeekBar mVerticalProgressbar10;
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.wheelview)
    WheelView mWheelview;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    AAOptions aaOptions;
    private Byte[] a;
    List<DeviceCourseData.ListBean.DetailsBean.ResistancesBean> resistances;
    Integer currentLoad = 1;
    Integer currentTime = 20;

    @Override
    protected int getContentView() {
        return R.layout.activity_course_pattern;
    }

    @Override
    protected void initViews() {
        a = new Byte[]{0, 0, 0, 0, 0, 0,0, 0, 0, 0};
        mTvV.setText(currentLoad+"/"+ConstValues_Ly.maxLoad);
        //模拟请求后台返回数据
        initData();
        ihnti();
        queryDeviceCourseTypeDetails(getIntent().getStringExtra("id"));
    }

    private void ihnti() {

        AAChartModel aaChartModel = configureChartModel();
        if (aaOptions == null) {
            aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        }
        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
        mAAChartView.setOnClick(false);
        mAAChartView.setIsClearBackgroundColor(true);

    }

    private AAChartModel configureChartModel() {


        AASeriesElement element1 = new AASeriesElement()
                .name("Tokyo")
                .lineWidth(2f)
                .color("#FF9933")
                .data(a);

        AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Spline)
                .title("")
                .yAxisTitle("")
                .xAxisVisible(false)
                .xAxisLabelsEnabled(false)
                .yAxisLabelsEnabled(false)
                .legendEnabled(false)
                .yAxisMax((float)ConstValues_Ly.maxLoad)
                .yAxisGridLineWidth(0f)
                .markerRadius(0f)
                .scrollablePlotArea(
                        new AAScrollablePlotArea()
//                                .minWidth(3000)
                                .scrollPositionX(1f)
                )
                .series(new AASeriesElement[]{element1});

        return aaChartModel;
    }

    private void initData() {

        list.add("20");
        list.add("40");
        list.add("60");
        list.add("80");
        list.add("100");
        mWheelview.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        mWheelview.setSkin(WheelView.Skin.None); // common皮肤
        mWheelview.setWheelData(list);  // 数据集合
        mWheelview.setSelection(1);
        mWheelview.setWheelSize(3);
        mWheelview.setExtraText("min", getResources().getColor(R.color.color_999999), 30, 150);

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        style.selectedTextZoom = 2;
        mWheelview.setStyle(style);

        mWheelview.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                currentTime = Integer.valueOf(list.get(position));
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.tv_ok,R.id.iv_hfmr,R.id.iv_jian,R.id.iv_jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok:
                long time = currentTime/10*60*1000;
                Intent intent = new Intent(this, RatePatternActivity.class);
                byte[] data = new byte[a.length];
                for(int i=0;i<a.length;i++){
                    data[i]=a[i];
                }
                intent.putExtra("data",data);
                intent.putExtra("time",time);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_hfmr:
                initProgress();
                break;
            case R.id.iv_jian:
                if(currentLoad>0){
                    currentLoad--;
                    mTvV.setText(currentLoad+"/"+ConstValues_Ly.maxLoad);
                }
                break;
            case R.id.iv_jia:
                if(currentLoad<ConstValues_Ly.maxLoad){
                    currentLoad++;
                    mTvV.setText(currentLoad+"/"+ConstValues_Ly.maxLoad);
                }
                break;
        }
    }
    //设置值动画 progressbar动起来

    private void initProgressBar(int pos,VerticalSeekBar verticalProgressbar) {
        verticalProgressbar.setMax(ConstValues_Ly.maxLoad);
        verticalProgressbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {//设置滑动监听
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.w("123", "正在拖动" + progress + "\n");

                a[pos] = (byte)progress;
                AASeriesElement element1 = new AASeriesElement()
                        .lineWidth(1f)
                        .data(a);
                mAAChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(new AASeriesElement[]{element1});
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                stringBuffer=new StringBuffer();
//                stringBuffer.append("开始拖动+\n");
                Log.w("123", "开始拖动");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.w("123", "停止拖动+\n");
            }
        });
    }


    private void queryDeviceCourseTypeDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceCourseTypeDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceCourseData.ListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceCourseData.ListBean> result) {
                        if(isDataInfoSucceed(result)){
                            resistances = result.getData().getDetails().getResistances();
                            initProgress();
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

    private void initProgress() {
        for(int i=0;i<resistances.size();i++){
            a[i] = Byte.valueOf(resistances.get(i).getValue());
        }
        AASeriesElement element1 = new AASeriesElement()
                .lineWidth(1f)
                .data(a);
        mAAChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(new AASeriesElement[]{element1});

        mVerticalProgressbar1.setProgress( a[0]);
        initProgressBar(0,mVerticalProgressbar1);
        mVerticalProgressbar2.setProgress( a[1]);
        initProgressBar(1,mVerticalProgressbar2);
        mVerticalProgressbar3.setProgress( a[2]);
        initProgressBar(2,mVerticalProgressbar3);
        mVerticalProgressbar4.setProgress( a[3]);
        initProgressBar(3,mVerticalProgressbar4);
        mVerticalProgressbar5.setProgress( a[4]);
        initProgressBar(4,mVerticalProgressbar5);
        mVerticalProgressbar6.setProgress(a[5]);
        initProgressBar(5,mVerticalProgressbar6);
        mVerticalProgressbar7.setProgress(a[6]);
        initProgressBar(6,mVerticalProgressbar7);
        mVerticalProgressbar8.setProgress(a[7]);
        initProgressBar(7,mVerticalProgressbar8);
        mVerticalProgressbar9.setProgress( a[8]);
        initProgressBar(8,mVerticalProgressbar9);
        mVerticalProgressbar10.setProgress( a[9]);
        initProgressBar(9,mVerticalProgressbar10);
    }
}
