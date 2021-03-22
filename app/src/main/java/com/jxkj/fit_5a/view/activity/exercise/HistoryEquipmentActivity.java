package com.jxkj.fit_5a.view.activity.exercise;

import android.bluetooth.BluetoothGatt;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.BleUtil;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.adapter.HistoryEquipmentAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HistoryEquipmentActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    List<HistoryEquipmentData> lists;
    private HistoryEquipmentAdapter mHistoryEquipmentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_history_equipment;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("历史设备");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
        mTvRighttext.setText("新增");
        
//        SharedHistoryEquipment.singleton().putSharedHistoryEquipment(lists);

        lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        mHistoryEquipmentAdapter = new HistoryEquipmentAdapter(lists);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHistoryEquipmentAdapter);

        mHistoryEquipmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHistoryEquipmentAdapter.getData().get(position).getState().equals("1")){
                    if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
                        ToastUtils.showShort("请想断开当前连接");
                        return;
                    }
                    goLianJie(mHistoryEquipmentAdapter.getData().get(position));

                }else{
                    DialogUtils.showDialogHint(HistoryEquipmentActivity.this, "您确定要断开当前连接吗？", false, new DialogUtils.ErrorDialogInterface() {
                        @Override
                        public void btnConfirm() {
                            PopupWindowLanYan.ble4Util.disconnect();
                            mHistoryEquipmentAdapter.setNewData(SharedHistoryEquipment.singleton().getSharedHistoryEquipment());
                        }
                    });
                }
            }
        });
    }

    private void goLianJie(HistoryEquipmentData historyEquipmentData) {
        Log.w("historyEquipmentData","historyEquipmentData:"+historyEquipmentData.toString());
        PopupWindowLanYan.ble4Util = new Ble4_0Util(this);
        PopupWindowLanYan.ble4Util.init();
        PopupWindowLanYan.ble4Util.setServiceUUid(historyEquipmentData.getServiceUUid());
        PopupWindowLanYan.ble4Util.stopScan();
        PopupWindowLanYan.ble4Util.connect(historyEquipmentData.getLyAddress(), new BleUtil.CallBack() {
            @Override
            public void StateChange(int state, int newState) {
                String value = null;
                PopupWindowLanYan.BleName = "";
                if (newState == BluetoothGatt.STATE_CONNECTED){
                    dismiss();
                    PopupWindowLanYan.BleName = historyEquipmentData.getName();
                    value = "连接成功";
                } else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                    dismiss();
                    value = "连接失败";
                } else if(newState == BluetoothGatt.STATE_CONNECTING){
                    show();
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
                        finish();
                    }
                    break;
                case 101:
//                    dialogInterface.btnConfirm(message.obj.toString());
                    byte[] resultData = (byte[]) message.obj;
                    if(resultData.length>4){
                        PopupWindowLanYan.setData(resultData);
                    }else{
                        Log.w("---》》》","错误："+ Arrays.toString(resultData));
                    }
                    break;
            }
            return false;
        }
    });

    @OnClick({R.id.ll_back, R.id.tv_righttext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
                FacilityAddSbActivity.intentActivity(this);
                break;
        }
    }
}
