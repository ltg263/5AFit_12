package com.jxkj.fit_5a.view.activity.exercise;


import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.BleUtil;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityManageActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskStartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lianjie)
    TextView tv_lianjie;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_start;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("经典运动");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        if(lists!=null && lists.size()>0 && StringUtil.isBlank(PopupWindowLanYan.BleName)){
            goLianJie(lists.get(0));
        }
    }


    @OnClick({R.id.ll_back, R.id.rl_sbgl,R.id.btn_start, R.id.ll_kcxz,R.id.ll_xlkz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_sbgl:
                List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
                if(lists!=null && lists.size()>0){
                    IntentUtils.getInstence().intent(this,HistoryEquipmentActivity.class);
                    return;
                }
                FacilityAddSbActivity.intentActivity(this);
                break;
            case R.id.btn_start:
                if(tv_lianjie.getText().toString().equals("暂未连接设备")){
                    ToastUtils.showShort("请先链接运动设备");
                    return;
                }
                startActivity(new Intent(this, CourseStartActivity.class));
                break;
            case R.id.ll_kcxz:
                if(tv_lianjie.getText().toString().equals("暂未连接设备")){
                    ToastUtils.showShort("请先链接运动设备");
                    return;
                }
                if(ConstValues_Ly.maxLoad<=1){
                    ToastUtils.showShort("该设备不支持间歇训练");
                    return;
                }

                startActivity(new Intent(this, CourseSelectionActivity.class));
                break;
            case R.id.ll_xlkz:
                if(tv_lianjie.getText().toString().equals("暂未连接设备")){
                    ToastUtils.showShort("请先链接运动设备");
                    return;
                }
                if(ConstValues_Ly.maxLoad<=1){
                    ToastUtils.showShort("该设备不支持心率模式");
                    return;
                }
                RateControlActivity.intentActivity(this);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_lianjie.setText("暂未连接设备");
        if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
            tv_lianjie.setText(PopupWindowLanYan.BleName);
        }
    }

    private void goLianJie(HistoryEquipmentData historyEquipmentData) {
        Log.w("historyEquipmentData","historyEquipmentData:"+historyEquipmentData.toString());
        ConstValues_Ly.BRAND_ID = historyEquipmentData.getBrandId();
        PopupWindowLanYan.ble4Util = new Ble4_0Util(this);
        PopupWindowLanYan.ble4Util.init();
        String[] uuidData = new String[3];
        uuidData[0] = historyEquipmentData.getServiceUUid();
        uuidData[1] = historyEquipmentData.getReadUUID();
        uuidData[2] = historyEquipmentData.getWriteUUID();
        PopupWindowLanYan.ble4Util.setUuidStr(uuidData);
        PopupWindowLanYan.ble4Util.stopScan();
        PopupWindowLanYan.ble4Util.connect(historyEquipmentData.getLyAddress(), new BleUtil.CallBack() {
            @Override
            public void StateChange(int state, int newState) {
                String value = null;
                PopupWindowLanYan.BleName = "";
                if (newState == BluetoothGatt.STATE_CONNECTED){
                    dismiss();
                    PopupWindowLanYan.BleName = historyEquipmentData.getName();
                    ConstValues_Ly.DEVICE_TYPE_ID_URL = historyEquipmentData.getDeviceTypeId();
                    value = "连接成功";
                } else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                    dismiss();
                    value = "连接失败";
                } else if(newState == BluetoothGatt.STATE_CONNECTING){
                    show("蓝牙连接中...");
                    value = "连接设备中";
                } else if(newState == BluetoothGatt.STATE_DISCONNECTING){
                    value = "断开连接中";
                }

                if (linkHandler != null && value != null){
                    //发送连接成功通知
                    Message message = new Message();
                    message.what = 99;
                    message.obj = value;
                    linkHandler.sendMessage(message);
                }
                ToastUtils.showShort(value);
            }

            @Override
            public void ReadValue(byte[] value) {
                dismiss();
                if (linkHandler != null){
                    Message message = new Message();
                    message.what = 101;
                    message.obj = value;
                    linkHandler.sendMessage(message);
                }

            }
        });
    }

    private Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99:
                    //连接
                    Ble4_0Util.OpenA2dp();
                    if(message.obj.toString().equals("连接成功")){
                        TimeThreadUtils.sendDataA2();
                        if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
                            tv_lianjie.setText(PopupWindowLanYan.BleName);
                        }
                    }
                    break;
                case 101:
//                    dialogInterface.btnConfirm(message.obj.toString());
                    byte[] resultData = (byte[]) message.obj;
                    if(resultData.length>4){
                        PopupWindowLanYan.setData(resultData);
                    } else {
                        Log.w("---》》》","错误："+ Arrays.toString(resultData));
                    }
                    break;
            }
            return false;
        }
    });


}
