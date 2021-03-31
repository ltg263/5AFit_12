package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.conpoment.view.RobotView;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class MapExerciseActivity extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
//    @BindView(R.id.iv_2)
//    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_dian)
    ImageView iv_dian;
    @BindView(R.id.iv_img)
    RobotView iv_img;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    boolean isSuo = true;
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

        if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]) ){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1]){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5));
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2]){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }

        StyleKitName.mPathMeasure = null;
        StyleKitName.mCurrentPosition = null;
        /**
         * 广播动态注册
         */
        mMyReceiver = new MyReceiver();//集成广播的类
        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
        registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive

        initViews();
    }


    PopupWindowTopicUtils_Map window;

    private float mPosX;
    private float mPosY;
    private float mCurrentPosX;
    private float mCurrentPosY;
    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        mapId = getIntent().getStringExtra("mapId");
        getMapDetails();

        if(window==null){
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity.this, type -> {
                if(type ==0){
                    iv_img.setState(type);
                    if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]
                            || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]) ){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                    }
                }else if(type==1){
                    iv_img.setState(type);
                    if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]
                            || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]) ){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                    }
                }else if(type==2){

                }

            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        iv_img.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // 按下
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    // 移动
                    case MotionEvent.ACTION_MOVE:
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();
                        if (mCurrentPosX - mPosX > 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向右");
                        else if (mCurrentPosX - mPosX < 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向左");
                        else if (mCurrentPosY - mPosY > 0 && Math.abs(mCurrentPosX - mPosX) < 10){
                            Log.e("", "向下");
                            if(!isSuo){
                                isSuo = true;
                                mTvTime.setVisibility(View.VISIBLE);
//                                mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                                if (window != null && window.isShowing()) {
                                    window.dismiss();
                                }
                            }
                        }else if (mCurrentPosY - mPosY < 0 && Math.abs(mCurrentPosX - mPosX) < 10){
                            Log.e("", "向上");
                            if(isSuo){
                                isSuo = false;
//                                mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_9));
                                window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
                                mTvTime.setVisibility(View.GONE);
                            }
                        }
                        break;
                    // 拿起
                    case MotionEvent.ACTION_UP:

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @OnClick({R.id.iv_1, R.id.iv_3, R.id.iv_4, R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                if (mIv3.getVisibility() == View.VISIBLE) {
                    mIv3.setVisibility(View.GONE);
                    mIv3.setAnimation(AnimationUtils.makeOutAnimation(this, true));
                    mIv4.setVisibility(View.GONE);
                    mIv4.setAnimation(AnimationUtils.makeOutAnimation(this, true));
                } else {
                    mIv3.setVisibility(View.VISIBLE);
                    mIv3.setAnimation(AnimationUtils.makeInAnimation(this, true));
                    mIv4.setVisibility(View.VISIBLE);
                    mIv4.setAnimation(AnimationUtils.makeInAnimation(this, true));
                }
                break;
            case R.id.iv_3:
//                iv_img.startPathAnim(60000);
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
                .getMapDetails(mapId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null) {
                            distance = Double.valueOf(result.getData().getDistance());
                            mTvDistance.setText(distance + "m");
                            if (Double.valueOf(result.getData().getDistance()) > 1000) {
                                mTvDistance.setText(StringUtil.getValue(Double.valueOf(distance) / 1000d) + "km");
                            }
                            GlideImgLoader.loadImage(MapExerciseActivity.this,result.getData().getImgUrl(),iv_img);
                            if(result.getData().getInfo()!=null && result.getData().getInfo().size()>0){
                                iv_img.setData(result.getData().getInfo(),result.getData().getParam());
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
                Intent mIntent = new Intent(MapExerciseActivity.this,MapExerciseFinishActivity.class);
                startActivity(mIntent);
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
    Double distance = 0.0;//总距离
    double currentSpeed = 0.0;
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
            window.setIvSelect(true);
            iv_img.setState(1);
            return;
        }
        window.setIvSelect(false);
        if(currentSpeed!=speed && speed!=0){
            iv_img.setRed(Math.round((distance-Distance*1000)/(speed*1000)*60*60*1000));
        }else{
            if(speed==0){
                iv_img.setState(1);
            }else{
                iv_img.setState(0);
            }
        }
        currentSpeed = speed;
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance+"km",speed+"km/h",ZTime,Calories+"cal",Watt+"w",Pulse+"bpm");

    }

}
