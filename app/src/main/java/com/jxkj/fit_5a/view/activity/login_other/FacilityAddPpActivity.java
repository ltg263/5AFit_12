package com.jxkj.fit_5a.view.activity.login_other;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.BleUtil;
import com.jxkj.fit_5a.lanya.LinkActivity2;
import com.jxkj.fit_5a.lanya.MainActivity2;
import com.jxkj.fit_5a.view.adapter.ImgAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FacilityAddPpActivity extends BaseActivity {
    @BindView(R.id.ble_cmd_edit1)
    EditText mBleCmdEdit1;
    @BindView(R.id.send_btn1)
    Button mSendBtn1;
    private BluetoothAdapter BA;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.show_msg_text)
     TextView consleText;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    @BindView(R.id.video_view)
    VideoView mVideoView;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv_d)
    ImageView mIvD;
    private ImgAdapter mImgAdapter;
    private Ble4_0Util ble4Util;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_pp;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("设备名称");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRvUi();
        initVvUi();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
            }
        }

//        ble4Util = new Ble4_0Util(this);
//        ble4Util.init();

//        searc();
    }


    private void initVvUi() {

        /***
         * 将播放器关联上一个音频或者视频文件
         * videoView.setVideoURI(Uri uri)
         * videoView.setVideoPath(String path)
         * 以上两个方法都可以。
         */
        mVideoView.setVideoPath("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4");

        /**
         * w为其提供一个控制器，控制其暂停、播放……等功能
         */
        mVideoView.setMediaController(new MediaController(this));

        /**
         * 视频或者音频到结尾时触发的方法
         */
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("通知", "完成");
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("通知", "播放中出现错误");
                return false;
            }
        });

    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mImgAdapter = new ImgAdapter(list);

        mRvAllList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mImgAdapter);

        mImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 1) {
                }
            }
        });

        mSendBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }
    @OnClick({R.id.ll_back, R.id.ll_connect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_connect:
//                if (btAdapt.getState() != BluetoothAdapter.STATE_ON) {// 如果蓝牙还没开启
//                    Toast.makeText(getBaseContext(), "请先开启蓝牙", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!btAdapt.isDiscovering()) {
//                    addPairedDevice();
//                    btAdapt.startDiscovery();
//                }
//                sendMessage(BluetoothServer.START_LINK);
//                sendLink();
                goConnect();
                break;
        }
    }

    private void searc() {
        ble4Util.startScan(new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
                Log.w("TAG", "name:" + bluetoothDevice.getName() + ";value:" + bluetoothDevice.getAddress());
                if (i == 10) {
                    ble4Util.stopScan();
                }
            }
        });
    }

    private void sendData() {
        String stateStr = mTv.getText().toString();
        if (stateStr.indexOf("成功") < 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("当前设备未连接成功，无法发送");
            builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
            return;
        }
        String sendStr = mBleCmdEdit1.getText().toString();
        byte[] aa ={(byte)0xF0,(byte) 0xA0,(byte)0x00,(byte)0x00,(byte)0x90};
        if (sendStr.length() > 0) {
            ble4Util.send(aa);
            consleText.setText(sendStr);
            Toast.makeText(this, "指令已发送", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "发送指令不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendLink() {
        ble4Util.stopScan();
        ble4Util.connect("E8:5D:86:08:00:B1", new BleUtil.CallBack() {
            @Override
            public void StateChange(int state, int newState) {
                String value = null;
                if (newState == BluetoothGatt.STATE_CONNECTED) {
                    value = "连接成功";
                } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                    value = "连接失败";
                } else if (newState == BluetoothGatt.STATE_CONNECTING) {
                    value = "连接设备中";
                } else if (newState == BluetoothGatt.STATE_DISCONNECTING) {
                    value = "断开连接中";
                }
                if (value != null) {
                    Log.w("TAG", "value:" + value);
                    Message message = new Message();
                    message.what = 99;
                    message.obj = value;
                    linkHandler.sendMessage(message);
                }
//                if ( LinkActivity2.linkHandler != null && value != null){
//                    //发送连接成功通知
//                    Message message = new Message();
//                    message.what = 99;
//                    message.obj = value;
//                    LinkActivity2.linkHandler.sendMessage(message);
//                }
            }

            @Override
            public void ReadValue(String value) {
                Message message = new Message();
                message.what = 101;
                message.obj = value;
                linkHandler.sendMessage(message);
//                if ( LinkActivity2.linkHandler != null){
//                    Message message = new Message();
//                    message.what = 101;
//                    message.obj = value;
//                    LinkActivity2.linkHandler.sendMessage(message);
//                }
                Log.w("TAG", "value:" + value);
            }
        });
//        Intent intent = new Intent(this,LinkActivity2.class);
//        startActivity(intent);
    }

    private Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99: {
                    //连接
                    mTv.setText(message.obj.toString());
                }
                break;
                case 101: {
                    //接收到数据
                    mTv.setText("data:" + message.obj.toString());

                }
                break;
            }

            return false;
        }
    });

    AnimationDrawable animationDrawable;

    private void goConnect() {
        mIv.setVisibility(View.GONE);
        mTv.setVisibility(View.GONE);
        mIvD.setVisibility(View.VISIBLE);
        animationDrawable = (AnimationDrawable) mIvD.getBackground();
        animationDrawable.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showDialogUi();
                    }
                });
            }
        }).start();
    }

    private void showDialogUi() {
        mIv.setVisibility(View.VISIBLE);
        mTv.setVisibility(View.VISIBLE);
        mIvD.setVisibility(View.GONE);
        animationDrawable.stop();
        DialogUtils.showDialogLyState(FacilityAddPpActivity.this, "这是一个标题", 1, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                startActivity(new Intent(FacilityAddPpActivity.this, InterestActivity.class));
            }
        });
    }

    private final int PERMISSION_REQUEST_COARSE_LOCATION = 0xb01;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ble4Util.disconnect();
    }
}
