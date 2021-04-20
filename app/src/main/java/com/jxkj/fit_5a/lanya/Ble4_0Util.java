package com.jxkj.fit_5a.lanya;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;


public class Ble4_0Util implements BleUtil {

    private String serviceUUid = "49535343-fe7d-4ae5-8fa9-9fafd205e455";        // 服务uuid
    private String readUUID = "";            // 读数据uuid
    private String writeUUID = "49535343-8841-43f4-a8d4-ecbe34729bb3";    // 写数据uuid
    private String clientCharConfig = "";
    private static final int PERMISSION_REQUEST_CODE = 0x114; // 系统权限管理页面的参数
    public static final int READ_NOTIFY_CODE = 0x12;     //可读可通知
    public static final int WRITE_READ_CODE = 0x0e;     //可写可读
    public static final int WRITE_CODE = 0x04;     //可写可读
    public static final int READ_CODE = 0x02;     //可写可读
    public static final int WRITE_NOTIFY_CODE = 0x18;     //可写可通知

    private Activity context;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGattService gattServiceMain;
    private BluetoothGattCharacteristic mDevWriteCharacteristic; // 写服务
    private BluetoothGattCharacteristic mDevReadCharacteristic; // 读服务
    private BluetoothAdapter.LeScanCallback leScanCallback;
    private BluetoothDevice curConnectDev;
    int newStates = -1;

    public Ble4_0Util(Activity context) {
        this.context = context;
    }

    public BluetoothDevice getCurConnectDev() {
        return curConnectDev;
    }

    @Override
    public boolean init() {
        //先检查权限 anroid 6.0以上 需要动态获取 位置权限
        checkBluetoothPermission();
        //不支持 蓝牙
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return false;
        }
        final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);

        mBluetoothAdapter = bluetoothManager.getAdapter();
        //不支持 蓝牙
        if (mBluetoothAdapter == null) return false;

        //没有打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) mBluetoothAdapter.enable();

        return true;
    }

    public boolean isConnect() {
        return mBluetoothGatt != null;
    }

    public String getServiceUUid() {
        return serviceUUid;
    }

    public void setServiceUUid(String serviceUUid) {
        this.serviceUUid = serviceUUid;
    }

    public String getReadUUID() {
        return readUUID;
    }

    public void setReadUUID(String readUUID) {
        this.readUUID = readUUID;
    }

    public String getWriteUUID() {
        return writeUUID;
    }

    public void setWriteUUID(String writeUUID) {
        this.writeUUID = writeUUID;
    }

    @Override
    public boolean connect(String blemac, final CallBack callback) {
        callback.StateChange(0, BluetoothGatt.STATE_CONNECTING);
        curConnectDev = mBluetoothAdapter.getRemoteDevice(blemac);
        if (curConnectDev == null) {
            Log.e("BLE", "蓝牙" + blemac + "未找到");
            return false;
        }
        //已连接 先断开连接
        if (null != mBluetoothGatt) {
            mBluetoothGatt.close();
            mBluetoothGatt = null;
        }
        //不能拿到 名称和 蓝牙mac的按假连接处理
        if (null == curConnectDev.getName() && null == curConnectDev.getAddress()) {
            return false;
        }
        Log.w("---》》》:","----------");
        //连接蓝牙
        mBluetoothGatt = curConnectDev.connectGatt(context, false, new BluetoothGattCallback() {
            @Override
            public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                super.onPhyUpdate(gatt, txPhy, rxPhy, status);
            }

            @Override
            public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                super.onPhyRead(gatt, txPhy, rxPhy, status);
            }

            @Override
            public void onConnectionStateChange(final BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                Log.w("---》》》","onConnectionStateChange"+newState);
                newStates = newState;
                if (newState == BluetoothGatt.STATE_CONNECTED) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //设置接收数据长度，默认20
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mBluetoothGatt.requestMtu(512);
                    }
                }
                if (newState == BluetoothGatt.STATE_DISCONNECTED && StringUtil.isBlank(PopupWindowLanYan.BleName)) {
                    callback.StateChange(status, newStates);
                    disconnect();
                }
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
                List<BluetoothGattService> serviceList = gatt.getServices();
                for (BluetoothGattService gattService : serviceList) {
                    Log.w("---》》》:",gattService.getUuid().toString());
                    if (gattService.getUuid().toString().indexOf(serviceUUid) < 0) {
                        continue;
                    }
                    gattServiceMain = gattService;
                    List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
                    for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                        if (mDevReadCharacteristic == null && gattCharacteristic.getUuid().toString().indexOf(readUUID) >= 0) {
                            mDevReadCharacteristic = gattCharacteristic;
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    mBluetoothGatt.readCharacteristic(mDevReadCharacteristic);
                                }
                            }).start();
                            mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true);

                            List<BluetoothGattDescriptor> descriptorlist = gattCharacteristic.getDescriptors();

                            lp:
                            for (BluetoothGattDescriptor descriptor : descriptorlist) {
                                if (descriptor.getUuid().toString().indexOf(clientCharConfig) >= 0) {
                                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                    mBluetoothGatt.writeDescriptor(descriptor);
                                    break lp;
                                }
                            }
                        }
                        if (mDevWriteCharacteristic == null && (gattCharacteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE
                                || gattCharacteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE
                                || gattCharacteristic.getProperties() == 12)
                                && gattCharacteristic.getUuid().toString().indexOf(readUUID) >= 0) {
                            mDevWriteCharacteristic = gattCharacteristic;
                            Log.e("---》》》", "连接成功"+isConnect());
                            callback.StateChange(status, newStates);
                        }
                    }
                }
            }

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);
            }

            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicWrite(gatt, characteristic, status);
            }

            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
                // 处理解释反馈指令
                callback.ReadValue(characteristic.getValue());
            }


            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorRead(gatt, descriptor, status);
            }

            @Override
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorWrite(gatt, descriptor, status);
            }

            @Override
            public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                super.onReliableWriteCompleted(gatt, status);
            }

            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                super.onReadRemoteRssi(gatt, rssi, status);
            }

            @Override
            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                super.onMtuChanged(gatt, mtu, status);
                if (BluetoothGatt.GATT_SUCCESS == status) {
                    Log.w("---》》》","onMtuChanged success MTU = " + mtu);
                    gatt.discoverServices();
                } else {
                    Log.w("---》》》","onMtuChanged fail ");
                }
            }
        });

        return true;
    }

    @Override
    public boolean disconnect() {
        Log.w("---》》》","---》》》");
        if (null != mBluetoothGatt) {
            mBluetoothGatt.disconnect();
            mBluetoothGatt.close();
            mBluetoothGatt = null;
            PopupWindowLanYan.BleName = "";
            ConstValues_Ly.METER_ID = 0x00;
            ConstValues_Ly.CLIENT_ID = 0x00;
            ConstValues_Ly.CURRENT_STATE = 0;
            ConstValues_Ly.maxLoad = 0;
            ConstValues_Ly.DEVICE_TYPE_ID = 0;
            ConstValues_Ly.SB_NAME = "";
            initLsData();
        }
        mDevWriteCharacteristic = null;
        return true;
    }
    public static void initLsData() {
        List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        if(lists!=null){
            for(int i=0;i<lists.size();i++){
                lists.get(i).setState("1");
            }
        }
        SharedHistoryEquipment.singleton().putSharedHistoryEquipment(lists);
    }

    @Override
    public boolean close() {
        mBluetoothAdapter = null;
        return true;
    }

    @Override
    public boolean send(String str) {
        if (mDevWriteCharacteristic == null) {
            getService();
        }
        return sendStrToDev(str);
    }


    public boolean send(byte[] byteCmd) {
        if (mDevWriteCharacteristic == null) {
            getService();
        }
        return sendByteToDev(byteCmd);
    }

    public void sendData(byte[] sendData) {
        Log.w("---》》》", "sendData:" + Arrays.toString(sendData));
        if (sendData.length > 0) {
            send(sendData);
//            ToastUtils.showShort("指令已发送");
        } else {
            ToastUtils.showShort("发送指令不能为空！");
        }
    }

    private boolean getService() {
        if (null == mBluetoothGatt) {
            return false;
        }
        if (mDevWriteCharacteristic != null) {
            return true;
        }
        if (gattServiceMain == null) return false;

        //获取写的特征值
        List<BluetoothGattCharacteristic> characteristicList = gattServiceMain.getCharacteristics();
        for (BluetoothGattCharacteristic characteristic : characteristicList) {
            Log.w("---》》》","writeUUID:"+characteristic.getUuid().toString());
            if ((characteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE
                    || characteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE
                    || characteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) &&
                    characteristic.getUuid().toString().indexOf(writeUUID) >= 0) {
                mDevWriteCharacteristic = characteristic;
                break;
            }
        }

        return mDevWriteCharacteristic == null;
    }


    private boolean sendStrToDev(String str) {
        if (mDevWriteCharacteristic == null) {
            return false;
        }
        byte[] value = new byte[10];
        value[0] = (byte) 0x00;
        mDevWriteCharacteristic.setValue(value[0], BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mDevWriteCharacteristic.setValue(str);
        return mBluetoothGatt.writeCharacteristic(mDevWriteCharacteristic);
    }

    private boolean sendByteToDev(byte[] byteCmd) {
        if (mDevWriteCharacteristic == null) {
            return false;
        }
        mDevWriteCharacteristic.setValue(byteCmd);
        return mBluetoothGatt.writeCharacteristic(mDevWriteCharacteristic);
    }

    @Override
    public boolean startScan(BluetoothAdapter.LeScanCallback callBack) {

        if (mBluetoothAdapter == null) {
            return false;
        }
        if (mBluetoothAdapter.isDiscovering()) {
            return false;
        }

        mBluetoothAdapter.startLeScan(callBack);
        this.leScanCallback = callBack;
        return true;
    }

    @Override
    public boolean stopScan() {
        if (this.leScanCallback == null) {
            return false;
        }
        mBluetoothAdapter.stopLeScan(this.leScanCallback);
        this.leScanCallback = null;
        return true;
    }

    /*
    校验蓝牙权限
   */
    private void checkBluetoothPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        //校验是否已具有模糊定位权限
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
        }

    }

    public static void OpenA2dp() {

        BluetoothProfile.ServiceListener bs = new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                Log.w("Kavenir", "onServiceConnected");
                List<BluetoothDevice> bluetoothDevices = proxy.getConnectedDevices();
                if (bluetoothDevices.size() > 0) {
                    Log.w("Kavenir", "onServiceConnected==" + bluetoothDevices.get(0).getName());
                    if (profile == BluetoothProfile.HEADSET) {
                        BluetoothHeadset bh = (BluetoothHeadset) proxy;
                        if (bh.getConnectionState(bluetoothDevices.get(0)) == BluetoothProfile.STATE_CONNECTED) {
                            try {
                                bh.getClass().getMethod("connect", BluetoothDevice.class).invoke(bh, bluetoothDevices.get(0));
                                Log.w("Kavenir", "onServiceConnected==" + "headset通道");
                            } catch (Exception e) {
                            }
                        }
                    } else if (profile == BluetoothProfile.A2DP) {
                        BluetoothA2dp a2dp = (BluetoothA2dp) proxy;
                        if (a2dp.getConnectionState(bluetoothDevices.get(0)) == BluetoothProfile.STATE_CONNECTED) {
                            try {
                                a2dp.getClass().getMethod("connect", BluetoothDevice.class).invoke(a2dp, bluetoothDevices.get(0));
                                Log.w("Kavenir", "onServiceConnected==" + "a2dp通道");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onServiceDisconnected(int profile) {
                Log.e("Kavenir", "未连接");
            }
        };

    }
}
