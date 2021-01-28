package com.jxkj.fit_5a.lanya;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LinkActivity2 extends Activity implements View.OnClickListener {

    private static StringBuffer consoleStrBuff;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date date;
    static int pos = 0;
    private static TextView bleStatusText ,showMsgText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link2);

        ConstValues_Ly.CLIENT_ID = 0x00;//Client ID
        ConstValues_Ly.METER_ID = 0x00;//Meter ID

        bleStatusText = (TextView)findViewById(R.id.link_info_text);
        showMsgText= (TextView)findViewById(R.id.show_msg_text);
        findViewById(R.id.link_info_text).setOnClickListener(this);
        findViewById(R.id.send_btn0).setOnClickListener(this);
        findViewById(R.id.send_btn1).setOnClickListener(this);
        findViewById(R.id.send_btn2).setOnClickListener(this);
        findViewById(R.id.send_btn30).setOnClickListener(this);
        findViewById(R.id.send_btn31).setOnClickListener(this);
        findViewById(R.id.send_btn32).setOnClickListener(this);
        findViewById(R.id.send_btn33).setOnClickListener(this);
        findViewById(R.id.send_btn40).setOnClickListener(this);
        findViewById(R.id.send_btn41).setOnClickListener(this);
        findViewById(R.id.send_btn50).setOnClickListener(this);
        findViewById(R.id.send_btn51).setOnClickListener(this);
        findViewById(R.id.send_btn52).setOnClickListener(this);

    }

    public static void appendConsole(String msg) {
        if (consoleStrBuff == null) consoleStrBuff = new StringBuffer();
        if (consoleStrBuff.length() > 1024) consoleStrBuff.delete(0, 200);
        if (date == null) date = new Date();
        date.setTime(System.currentTimeMillis());
        consoleStrBuff.append(dateFormat.format(date) + ":" + msg + "\n");
        showMsgText.setText(consoleStrBuff);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity2.ble4Util.disconnect();
    }
    public static Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99: {
                    //连接
                    if (bleStatusText != null) {
                        bleStatusText.setText(message.obj.toString());
                        Ble4_0Util.OpenA2dp();
                    }
                }
                break;
                case 101: {
                    //接收到数据
                    byte[] strData = (byte[]) message.obj;

                    new DataManage(strData);

                    Log.w("---》》》", "strData:" + Arrays.toString(strData));
                    String sts = "";
                    for (int i = 0; i < strData.length; i++) {
                        sts += Integer.toHexString(strData[i] & 0xFF)+",";
                    }
                    appendConsole("接收：A0:"+sts);
                    Log.w("---》》》", "str:" + sts);
                    if(pos!=0 && strData.length > 2 && Integer.toHexString(strData[1] & 0xFF).equals("b7")){
                        appendConsole("接收：错误信息");
                        break;
                    }
                    if (pos == 0 && Integer.toHexString(strData[1] & 0xFF).equals("b0")) {
                        appendConsole("接收：A0_链接成功："+sts);
                        break;
                    }
                    if (pos == 1 && Integer.toHexString(strData[1] & 0xFF).equals("b1")) {
                        appendConsole("接收：A1:"+sts);
                        break;
                    }
//                    if (pos == 2 && Integer.toHexString(strData[1] & 0xFF).equals("b2")) {
//                        appendConsole("接收：A2"+sts);
//                        break;
//                    }
//                    if (pos == 3 && Integer.toHexString(strData[1] & 0xFF).equals("b3")) {
//                        appendConsole("接收：A3"+sts);
//                        break;
//                    }
//                    if (pos == 4 && Integer.toHexString(strData[1] & 0xFF).equals("b4")) {
//                        appendConsole("接收：A4"+sts);
//                        break;
//                    }

                    if (pos == 0) {
                        ConstValues_Ly.CLIENT_ID = strData[2];
                        ConstValues_Ly.METER_ID = strData[3];
                        byte[] sendData = {ConstValues_Ly.START, ConstValues_Ly.MESSAGE_A0, ConstValues_Ly.CLIENT_ID,
                                ConstValues_Ly.METER_ID, ConstValues_Ly.getCHECKSUM(ConstValues_Ly.MESSAGE_A0)};
                        MainActivity2.ble4Util.send(sendData);
                    }
                }
                break;
            }

            return false;
        }
    });

    @Override
    public void onClick(View view) {
        if(!isSendData()){
            return;
        }
        switch (view.getId()) {
            case R.id.link_info_text:
                if (bleStatusText.getText().toString().indexOf("成功") < 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LinkActivity2.this);
                    builder.setTitle("提示");
                    builder.setMessage("断开当前连接？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity2.ble4Util.disconnect();
                            LinkActivity2.this.finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
                break;
            case R.id.send_btn0:
                pos = 0;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
                break;
            case R.id.send_btn1:
                pos = 1;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A1));
                break;
            case R.id.send_btn2:
                pos = 2;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A2));
                break;
            case R.id.send_btn30:
                pos = 3;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0XF0));
                break;
            case R.id.send_btn31:
                pos = 3;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X01));
                break;
            case R.id.send_btn32:
                pos = 3;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X02));
                break;
            case R.id.send_btn33:
                pos = 3;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X03));
                break;
            case R.id.send_btn40:
                pos = 4;
//                Data0：Client ID
//                Data1：Meter ID
                //02:00
//                Data2：Minute Set Byte (分00~99) 03

                //156
//                Data3：Distance Set Hi Byte (佰,拾 00~99) 10
//                Data4：Distance Set Low Byte (個,小數點以下一位 0.0~9.9)3d
                //40
//                Data5：Calories Set Hi Byte (千,佰 00~99)01
//                Data6：Calories Set Low Byte (拾,個00~99)29
                //85
//                Data7：Pulse Set Hi Byte (千,佰 00~99)01
//                Data8：Pulse Set Low Byte (拾,個00~99)56
                //120
//                Data9：Watt Set Hi Byte (佰,拾 00~99)0d
//                Data10：Watt Set Low Byte (個,小數點以下一位 0.0~9.9)01

//                Data11：0x00：KM  0x01：ML  01

//              Time= 02:00
//              Distance=156
//              Calories=40
//              Pulse=85
//              Watt=120
//              UNIT=KM
                byte[] data_km =  ConstValues_Ly.getA4Data(2,156,40,85,120,"KM");
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A4, data_km));
                break;
            case R.id.send_btn41:
                pos = 4;
                byte[] data_ml =  ConstValues_Ly.getA4Data(86,156,40,85,120,"ML");
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A4, data_ml));
                break;
            case R.id.send_btn50:
                pos = 5;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                break;
            case R.id.send_btn51:
                pos = 5;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                break;
            case R.id.send_btn52:
                pos = 5;
                sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                break;
        }
    }

    private void sendData(byte[] sendData) {
        if (sendData.length > 0) {
            MainActivity2.ble4Util.send(sendData);
            Toast.makeText(LinkActivity2.this, "指令已发送", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LinkActivity2.this, "发送指令不能为空！", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isSendData() {
        if (pos != 0 && ConstValues_Ly.CLIENT_ID == 0 && ConstValues_Ly.METER_ID == 0) {
            Toast.makeText(LinkActivity2.this, "先进行连接", Toast.LENGTH_SHORT).show();
            return false;
        }
        String stateStr = bleStatusText.getText().toString();
        if (stateStr.indexOf("成功") < 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LinkActivity2.this);
            builder.setTitle("提示");
            builder.setMessage("当前设备未连接成功，无法发送");
            builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
            return false;
        }
        return true;
    }
}
