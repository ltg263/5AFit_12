package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.conpoment.view.RobotView;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.RatePatternBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapExerciseActivity extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_img)
    RobotView iv_img;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    boolean isSuo = false;
    String mapId;
    String boxId;
    private MyReceiver mMyReceiver;


    public static void intentStartActivity(Context mContext, String mapId) {
        Intent intent = new Intent(mContext, MapExerciseActivity.class);
        intent.putExtra("mapId", mapId);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_map_exercise);
        ButterKnife.bind(this);
        initViews();

        /**
         * 广播动态注册
         */
        mMyReceiver = new MyReceiver();//集成广播的类
        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
        registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive
    }


    PopupWindowTopicUtils_Map window;

    private void initViews() {
        mapId = getIntent().getStringExtra("mapId");
        getMapDetails();

        if(window==null){
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity.this, type -> {
                if(type ==0){
                    iv_img.setRed(0);
                }else if(type==1){
                    iv_img.setRed(1);
                }else if(type==2){

                }

            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    @OnClick({R.id.iv_1, R.id.iv_2, R.id.iv_3, R.id.iv_4, R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                if (mIv3.getVisibility() == View.VISIBLE) {
                    mIv3.setVisibility(View.GONE);
                    mIv4.setVisibility(View.GONE);
                } else {
                    mIv3.setVisibility(View.VISIBLE);
                    mIv4.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_2:
                if (isSuo) {
                    isSuo = false;
                    mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_9));
                    window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
                    mTvTime.setVisibility(View.GONE);
                } else {
                    isSuo = true;
                    mTvTime.setVisibility(View.VISIBLE);
                    mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                    if (window != null && window.isShowing()) {
                        window.dismiss();
                    }
                }
                break;
            case R.id.iv_3:
                iv_img.start(60000);
                break;
            case R.id.iv_4:
                outRoom();
                break;
            case R.id.iv:
                if (StringUtil.isNotBlank(boxId)) {

                }
                break;
        }
    }


    private void getMapDetails() {
        RetrofitUtil.getInstance().apiService()
                .getMapDetails("17")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null) {
                            String distance = result.getData().getDistance();
                            mTvDistance.setText(distance + "m");
                            if (Double.valueOf(result.getData().getDistance()) > 1000) {
                                mTvDistance.setText(StringUtil.getValue(Double.valueOf(distance) / 1000d) + "km");
                            }
                            GlideImgLoader.loadImage(MapExerciseActivity.this,result.getData().getImgUrl(),iv_img);
                            if(result.getData().getInfo()!=null && result.getData().getInfo().size()>0){
                                iv_img.setData(result.getData().getInfo());
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        outRoom();
        return super.onKeyDown(keyCode, event);
    }

    private void outRoom() {
        DialogUtils.showDialogOutRoom(MapExerciseActivity.this, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMyReceiver!=null){
            unregisterReceiver(mMyReceiver);
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
//                    setData56(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2] && dataList.size()==14){
                    //健腹轮
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3] && dataList.size()==18){
//                    setData26(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4] && dataList.size()==13){
//                    setData46(dataList);
                }
            }
        }
    }

    private void setData1(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        int duration = timeMinute * 60 + timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int rpmHi = dataList.get(4);//每分钟转数 -千百
        int rpmLow = dataList.get(5);//每分钟转数 -十个
        int rpm = ConstValues_Ly.getQianBaiShiGe(rpmHi,rpmLow);

        int DistanceHi = dataList.get(6);//距离-百十
        int DistanceLow = dataList.get(7);//距离-个小数点下一位
        double Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi, DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        int Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi, CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);
        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

        Integer loadCurrent = dataList.get(14);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(15);
        String Unit ="Stop";
        if(dataList.get(15)==1){
            Unit ="Start";
        }

        String re = "A2--->>>:时间："+ZTime+",速度："+speed+",转数："+rpm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",阻力："+loadCurrent+",状态："+Unit;
        Log.w("---》》》", re);
        if(Unit.equals("Stop")){
            return;
        }

    }

}
