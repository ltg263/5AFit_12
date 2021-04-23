package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.conpoment.view.RobotView;
import com.jxkj.fit_5a.conpoment.view.RobotView11;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.RatePatternBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.TaskFinishActivity;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.iv_img_)
    RobotView11 iv_img_;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_quan)
    TextView tv_quan;
    boolean isSuo = true;
    String mapId;
    String boxId;
    private MyReceiver mMyReceiver;
    int loadCurrent = 1;
    int loadMax = ConstValues_Ly.maxLoad;

    int maxV = 220;
    double bfb5,bfb6,bfb7,bfb8,bfb9,bfb;
    int age = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_AGE,"0"));
    private ArrayList<BpmDataBean> mBpmDataBeans = new ArrayList<>();

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

        if ((ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3])) {
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]) {
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5));
        } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2]) {
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]) {
            DialogUtils.showDialogHint(this, "请按设备Start/开始按钮来开始", true, null);
        }
        startTimestamp = System.currentTimeMillis();
        StyleKitName.mPathMeasure = null;
        StyleKitName.mCurrentPosition = null;
        /**
         * 广播动态注册
         */
        mMyReceiver = new MyReceiver();//集成广播的类
        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
        registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive

        initViews();
        initBpmData();
    }

    private void initBpmData() {
        bfb5 = (maxV-age)*0.5+40;
        bfb6 = (maxV-age)*0.6+40;
        bfb7 = (maxV-age)*0.7+40;
        bfb8 = (maxV-age)*0.8+40;
        bfb9 = (maxV-age)*0.9+40;
        bfb  = (maxV-age)*1+40;
        mBpmDataBeans.add(new BpmDataBean("非运动区间(0~50%)",0,bfb5,0));
        mBpmDataBeans.add(new BpmDataBean("热身心率区间(50~60%)",bfb5,bfb6,0));
        mBpmDataBeans.add(new BpmDataBean("燃脂心率区间(60~70%)",bfb6,bfb7,0));
        mBpmDataBeans.add(new BpmDataBean("有氧耐力心率区间(70~80%)",bfb7,bfb8,0));
        mBpmDataBeans.add(new BpmDataBean("无氧耐力心率区间(80~90%)",bfb8,bfb9,0));
        mBpmDataBeans.add(new BpmDataBean("极限心率区间(90~100%)",bfb9,bfb,0));
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

        if (window == null) {
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity.this, type -> {
                if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]){
                    DialogUtils.showDialogHint(this, "请按设备Start/开始按钮来开始", true, null);
                    return;
                }
                if (type == 0) {
                    iv_img.setState(type);
                    if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                    }
                } else if (type == 1) {
                    iv_img.setState(type);
                    if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                    }
                } else if (type == 2) {
                    if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                            && loadCurrent<loadMax){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent+1)));
                    }
                }else if (type == 3) {
                    if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                            && loadCurrent>1){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent-1)));
                    }
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
                        else if (mCurrentPosY - mPosY > 0 && Math.abs(mCurrentPosX - mPosX) < 10) {
                            Log.e("", "向下");
                            if (!isSuo) {
                                isSuo = true;
                                mTvTime.setVisibility(View.VISIBLE);
//                                mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                                if (window != null && window.isShowing()) {
                                    window.dismiss();
                                }
                            }
                        } else if (mCurrentPosY - mPosY < 0 && Math.abs(mCurrentPosX - mPosX) < 10) {
                            Log.e("", "向上");
                            if (isSuo) {
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
//                iv_img.setRed(20000);
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
//                            distance = 910d;
                            mTvDistance.setText(distance + "m");
                            if (Double.valueOf(result.getData().getDistance()) > 1000) {
                                mTvDistance.setText(StringUtil.getValue(Double.valueOf(distance) / 1000d) + "km");
                            }
                            GlideImgLoader.loadImage(MapExerciseActivity.this, result.getData().getImgUrl(), iv_img);
                            if (result.getData().getInfo() != null && result.getData().getInfo().size() > 0) {
                                iv_img.setData(result.getData().getInfo(), result.getData().getParam());
                                iv_img_.setData(result.getData().getInfo(), result.getData().getParam());
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
                if(window!=null && window.isShowing()){
                    window.dismiss();
                }
                psotUserSportLog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMyReceiver != null) {
            unregisterReceiver(mMyReceiver);
        }
    }

    private void psotUserSportLog() {
        if(duration==0){
            finish();
            return;
        }
        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
        Intent mIntent = new Intent(this, MapExerciseFinishActivity.class);
        String str = String.valueOf(Distance / duration * 60d * 60d);
        String pjDuration = "0";
        if (str.equals(".")) {
            pjDuration = str.format("%.2f");
        }
        double MaxSpeed = Double.valueOf(logs.get(0).getSpeed());
        int load_D = 0;
        int load_X =  Integer.valueOf(logs.get(0).getResistanceLevel());
        for (int i = 0; i < logs.size(); i++) {
            Log.w("MaxSpeed:","logs："+logs.toString());
            if (Double.valueOf(logs.get(i).getSpeed()) > MaxSpeed) {
                Log.w("MaxSpeed:","----");
                MaxSpeed = Double.valueOf(logs.get(i).getSpeed());
            }
            if(load_D<Integer.valueOf(logs.get(i).getResistanceLevel())){
                load_D = Integer.valueOf(logs.get(i).getResistanceLevel());
            }
            if(load_X>Integer.valueOf(logs.get(i).getResistanceLevel())){
                load_X = Integer.valueOf(logs.get(i).getResistanceLevel());
            }
        }
        String load_dx = load_X+"-"+load_D;
        if(load_X==load_D){
            load_dx = load_D+"";
        }

        Log.w("MaxSpeed:","MaxSpeed"+MaxSpeed);
        mBpmDataBeans.get(0).setBpmTopData(
                new BpmDataBean.BpmTopData(String.valueOf(Calories), String.valueOf(Distance),
                        duration + "", pjDuration, String.valueOf(MaxSpeed), "--","--",load_dx,"--","--"));
        mIntent.putParcelableArrayListExtra("mBpmDataBeans", mBpmDataBeans);
        mIntent.putParcelableArrayListExtra("logs",logs);
        startActivity(mIntent);
        iv_img.setCancel();
        iv_img = null;
        finish();

        PostUser.SportLogInfo sportLogInfo = new PostUser.SportLogInfo();
        sportLogInfo.setBai("11");
        sportLogInfo.setMapId(mapId);
        sportLogInfo.setDeviceBrandId(ConstValues_Ly.BRAND_ID);
        sportLogInfo.setCalories(String.valueOf(Calories));
        sportLogInfo.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID + "");
        sportLogInfo.setDistance(String.valueOf(Distance));
        sportLogInfo.setDuration(String.valueOf(duration));
        sportLogInfo.setEndTimestamp(String.valueOf(System.currentTimeMillis()));
        sportLogInfo.setStartTimestamp(String.valueOf(startTimestamp));
        sportLogInfo.setProtocolName("iconsole");
        sportLogInfo.setProtocolDeviceBrandParamId(11 + "" + '1');
        sportLogInfo.setHeartRateSource("2");//1=器材;2=藍牙心跳;3=Apple Watch
        sportLogInfo.setTrainingMode("QuickStart");//目前只有HeartRate(心率)、Program(课程)
        PostUser.SportLogInfo.DetailsBean deleteDatabase = new PostUser.SportLogInfo.DetailsBean();
        deleteDatabase.setLogs(logs);
        sportLogInfo.setDetails(deleteDatabase);
        HttpRequestUtils.psotUserSportLog(sportLogInfo);
        PopupWindowLanYan.ble4Util.disconnect();
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("MyTag", "onReceive: " + intent.getStringExtra("type"));
            if (intent.getStringExtra("type").equals("b2")) {
                ArrayList<Integer> dataList = intent.getIntegerArrayListExtra("data");
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0] && dataList.size() == 16) {
                    setData1(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1] && dataList.size() == 14) {
                    setData56(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3] && dataList.size() == 18) {
                    setData26(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4] && dataList.size() == 14) {
                    setData46(dataList);
                }
//                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2] && dataList.size() == 14) {
//                    //健腹轮
//                }
            }
        }
    }

    Double distance = 0.0;//总距离
    double Distance;
    double duration;
    int Calories;
    long startTimestamp;

    ArrayList<PostUser.SportLogInfo.DetailsBean.LogsBean> logs = new ArrayList<>();
    private void setData1(ArrayList<Integer> dataList) {
        int timeMinute = dataList.get(0);//时间-分
        int timeSecond = dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute, timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi, speedLow);

        int rpmHi = dataList.get(4);//每分钟转数 -千百
        int rpmLow = dataList.get(5);//每分钟转数 -十个
        int rpm = ConstValues_Ly.getQianBaiShiGe(rpmHi, rpmLow);

        int DistanceHi = dataList.get(6);//距离-百十
        int DistanceLow = dataList.get(7);//距离-个小数点下一位
        Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi, DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi, CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi, PulseLow);
        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi, WattLow);

        loadCurrent = dataList.get(14);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(15);
        String Unit = "Stop";
        if (dataList.get(15) == 1) {
            Unit = "Start";
        }
        // 时间   速度  转数  距离  卡路里   心率  功率    阻力  状态
        String re = "A2--->>>:时间：" + ZTime + ",速度：" + speed + ",转数：" + rpm + ",距离：" + Distance + ",卡路里：" + Calories+ ",脉跳：" + Pulse + ",瓦特：" + Watt + ",阻力：" + loadCurrent + ",状态：" + Unit;
        Log.w("---》》》", re);
        if (Unit.equals("Stop")) {
            window.setIvSelect(true);
            iv_img.setState(1);
            return;
        }
        window.setTextLoad(loadCurrent+"/"+loadMax);
        window.setIvSelect(false);
        String str = ""+(int)duration;

        if (speed != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil(Distance * 1000d/distance);
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText(String.valueOf(quanNum));
            iv_img.setRed(Math.round((distance -(Distance * 1000d-distance*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (speed == 0) {
                iv_img.setState(1);
            } else {
                iv_img.setState(0);
            }
        }


        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance + "km", speed + "km/h", ZTime, Calories + "kcal", Watt + "w", Pulse + "bpm","--");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                "0",String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm),String.valueOf(speed),String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
    }


    private void setData56(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

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

        String re = "A2--->>>:时间："+ZTime+",速度："+speed+",转数1："+rpm1+",转数2："+rpm2+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse;
        Log.w("---》》》", re);

        window.setIvSelect(false);
        String str = ""+duration;

        if (speed != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil(Distance * 1000d/distance);
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText(String.valueOf(quanNum));
            iv_img.setRed(Math.round((distance -(Distance * 1000d-distance*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (speed == 0) {
                iv_img.setState(1);
            } else {
                iv_img.setState(0);
            }
        }


        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance + "km", speed + "km/h", ZTime, Calories + "kcal", 0 + "w", Pulse + "bpm","--");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(
                String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                "0",String.valueOf(0),String.valueOf(0),
                String.valueOf(rpm1),String.valueOf(speed),String.valueOf(System.currentTimeMillis()),"0"));
    }


    private void setData26(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

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

        String re = "A2--->>>:时间："+ZTime+",行程："+stroke+",spm："+spm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",time1："+time1+",状态："+Unit;
        Log.w("---》》》", re);
        if(Unit.equals("Stop")){
            return;
        }
        window.setTextLoad(loadCurrent+"/"+loadMax);
        window.setIvSelect(false);
        String str = ""+(int)duration;

        if (stroke != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil(Distance * 1000d/distance);
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText(String.valueOf(quanNum));
            iv_img.setRed(Math.round((distance -(Distance * 1000d-distance*(quanNum-1))) / (stroke * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (stroke == 0) {
                iv_img.setState(1);
            } else {
                iv_img.setState(0);
            }
        }


        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance + "km", stroke + "km/h", ZTime, Calories + "kcal", Watt + "w", Pulse + "bpm","0");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                "0",String.valueOf(loadCurrent),String.valueOf(loadCurrent), String.valueOf(spm),String.valueOf(stroke),String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
        return;
    }

    private void setData46(ArrayList<Integer> dataList) {
        int timeSecond =  dataList.get(0);//时间-秒
        int timeMinute =  dataList.get(1);//时间-分
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int DistanceHi = dataList.get(2);
        int DistanceLow = dataList.get(3);
        Distance = DistanceHi+DistanceLow/100d;

        int CaloriesHi = dataList.get(4);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(5);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(6);//跳动 千,佰
        int PulseLow = dataList.get(7);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int speedHi = dataList.get(8);//速度-百十
        int speedLow = dataList.get(9);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int Incline = dataList.get(10);

        ConstValues_Ly.CURRENT_STATE = dataList.get(11);
        String Unit ="None";
        if(dataList.get(11)==2){
            Unit ="Start";
        }else if(dataList.get(11)==1){
            Unit ="Stop";
        }else if(dataList.get(11)==3){
            Unit ="pause";
        }
        //[52, 11, 0, 25, 0, 14, 0, 0, 0, 13, 3, 2, 3, 1]
        String re = "A2--->>>:时间："+ZTime+",距离："+Distance+",坡度："+Incline+",卡路里："+Calories+",脉跳："+Pulse+",速度："+speed+",状态："+Unit;
        Log.w("---》》》", re);
        if(Unit.equals("Stop") && duration!=0){
            psotUserSportLog();
            return;
        }
//        window.setTextLoad(loadCurrent+"/"+loadMax);
        window.setIvSelect(false);
        String str = ""+(int)duration;

        if (speed != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil(Distance * 1000d/distance);
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText(String.valueOf(quanNum));
            iv_img.setRed(Math.round((distance -(Distance * 1000d-distance*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (speed == 0) {
                iv_img.setState(1);
            } else {
                iv_img.setState(0);
            }
        }


        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance + "km", speed + "km/h", ZTime, Calories + "kcal", 0 + "w", Pulse + "bpm",Incline+"");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),String.valueOf(Pulse),
                Incline+"",null,"0","0",String.valueOf(speed),String.valueOf(System.currentTimeMillis()),"0"));
        return;
    }

    private void setBpmDataBeanTime(int pulse){
        Log.w("-->>","mBpmDataBeans"+mBpmDataBeans.toString());
        if(pulse>=mBpmDataBeans.get(0).getStartV() && pulse<mBpmDataBeans.get(0).getEndV()){
            mBpmDataBeans.get(0).setTime(mBpmDataBeans.get(0).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(1).getStartV() && pulse<mBpmDataBeans.get(1).getEndV()){
            mBpmDataBeans.get(1).setTime(mBpmDataBeans.get(1).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(2).getStartV() && pulse<mBpmDataBeans.get(2).getEndV()){
            mBpmDataBeans.get(2).setTime(mBpmDataBeans.get(2).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(3).getStartV() && pulse<mBpmDataBeans.get(3).getEndV()){
            mBpmDataBeans.get(3).setTime(mBpmDataBeans.get(3).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(4).getStartV() && pulse<mBpmDataBeans.get(4).getEndV()){
            mBpmDataBeans.get(4).setTime(mBpmDataBeans.get(4).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(5).getStartV() && pulse<mBpmDataBeans.get(5).getEndV()){
            mBpmDataBeans.get(5).setTime(mBpmDataBeans.get(5).getTime()+1);
            return;
        }
    }
}
