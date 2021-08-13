package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.AuthAgent;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.tencent.connect.common.Constants.KEY_ENABLE_SHOW_DOWNLOAD_URL;
import static com.tencent.connect.common.Constants.KEY_QRCODE;
import static com.tencent.connect.common.Constants.KEY_RESTORE_LANDSCAPE;
import static com.tencent.connect.common.Constants.KEY_SCOPE;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.et_input_sjh_yes)
    TextView mEtInputSjhYes;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.et_input_mm_yes)
    TextView mEtInputMmYes;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.tv_login_yzm)
    TextView mTvLoginYzm;
    @BindView(R.id.tv_login_wjmm)
    TextView mTvLoginWjmm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.ll3)
    LinearLayout mLl3;
    int loginType = 2;//密码登录
    private TimeCounter mTimeCounter;
    private Tencent mTencent;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginType = 2;
        mTvLoginYzm.setText("密码登录");
        mLl2.setVisibility(View.INVISIBLE);
        mLl3.setVisibility(View.VISIBLE);
        mTvLoginWjmm.setVisibility(View.INVISIBLE);

        mTencent = Tencent.createInstance(ConstValues.APP_ID_TENCENT, this, ConstValues.APP_AUTHORITIES);
    }

    @OnClick({R.id.tv_login_yzm, R.id.tv_login_wjmm, R.id.iv_login_wx,R.id.iv_login_qq,R.id.iv_iconsole,R.id.tv_go_login, R.id.ll_go_zc,R.id.tv_go_yzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_yzm:
                if(loginType==1){
                    loginType = 2;
                    mTvLoginYzm.setText("密码登录");
                    mLl2.setVisibility(View.INVISIBLE);
                    mLl3.setVisibility(View.VISIBLE);
                    mTvLoginWjmm.setVisibility(View.INVISIBLE);
                }else{
                    loginType = 1;
                    mTvLoginYzm.setText("验证码登录");
                    mLl2.setVisibility(View.VISIBLE);
                    mLl3.setVisibility(View.INVISIBLE);
                    mTvLoginWjmm.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_login_wx:
                break;
            case R.id.iv_login_qq:
                if(!mTencent.isQQInstalled(this)){
                    ToastUtils.showShort("未安装QQ");
                    return;
                }
                onClickLogin();
                break;
            case R.id.iv_iconsole:
                startIconsoleApp();
                break;
            case R.id.tv_go_yzm:
                String sjh = mEtInputSjh.getText().toString();
                if(!TextUtils.isEmpty(sjh)&&sjh.length()==11){
                    mTimeCounter = new TimeCounter(60 * 1000, 1000, mTvGoYzm);
                    mTimeCounter.start();
                    goGetYzm(sjh);
                }else{
                    ToastUtils.showShort("请输入正确的手机号");
                }
                break;
            case R.id.tv_login_wjmm:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
            case R.id.tv_go_login:
                userVerifyLogin();
                break;
            case R.id.ll_go_zc:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    private void startIconsoleApp() {
        Uri uri = Uri.parse("iconsoleplus://3ptoken?rights=read_profile,read_workouts&linkback=FiveAFitness://3ptoken.result&appname=5AFit");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify if app XYZ has this screen path
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            startActivity(mapIntent);
        }else{
           ToastUtils.showShort("请先安装iConsole");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("123", "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void userVerifyLogin() {
        String sjh = mEtInputSjh.getText().toString();
        String yzm = mEtInputYzm.getText().toString();
        String mm = mEtInputMm.getText().toString();

        if(StringUtil.isBlank(sjh)){
            ToastUtils.showShort("填写不完整");
            return;
        }
        if(loginType == 1) {
            yzm = null;
            if(StringUtil.isBlank(mm)){
                ToastUtils.showShort("填写不完整");
                return;
            }
        }
        if(loginType == 2){
            mm = null;
            if(StringUtil.isBlank(yzm)){
                ToastUtils.showShort("填写不完整");
                return;
            }
        }
        show();
        String finalMm = mm;
        RetrofitUtil.getInstance().apiService()
                .userVerifyLogin(3,sjh,mm,yzm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginInfo> result) {
                        if(isDataInfoSucceed(result)){
                            SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+result.getData().getTokenId());
                            if(finalMm!=null){
                                SharedUtils.singleton().put(ConstValues.USER_PASSWORD, finalMm);
                            }
                            saveUserInfo(result.getData());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                        Log.w("e","e:"+e);
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }


    public static void saveUserInfo(LoginInfo data) {
        SharedUtils.singleton().put(ConstValues.ISLOGIN,true);
        SharedUtils.singleton().put(ConstValues.USER_PHONE,data.getUserPermissionBaseDTO().getUserNo());
        SharedUtils.singleton().put(ConstValues.USERID,data.getUserPermissionBaseDTO().getId());
        SharedUtils.singleton().put(ConstValues.USER_NAME,data.getUserPermissionBaseDTO().getNickName());
        SharedUtils.singleton().put(ConstValues.AVATAR,data.getUserPermissionBaseDTO().getAvatar());
    }


    private void goGetYzm(String sjh) {
        RetrofitUtil.getInstance().apiService()
                .getVerifyCode(sjh,0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("发送成功");
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
    protected void onDestroy() {
        super.onDestroy();
        if(mTimeCounter!=null){
            mTimeCounter.cancel();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mTimeCounter!=null){
            mTimeCounter.cancel();
        }
    }
    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            // 强制扫码登录
            this.getIntent().putExtra(AuthAgent.KEY_FORCE_QR_LOGIN, false);
            HashMap<String, Object> params = new HashMap<String, Object>();
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
                params.put(KEY_RESTORE_LANDSCAPE, true);
            }
            params.put(KEY_SCOPE, "all");
            params.put(KEY_QRCODE, false);
            params.put(KEY_ENABLE_SHOW_DOWNLOAD_URL, false);
            mTencent.login(this, loginListener, params);
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
//            if (isServerSideLogin) { // Server-Side 模式的登录, 先退出，再进行SSO登录
//                mTencent.logout(this);
//                mTencent.login(this, "all", loginListener);
//                isServerSideLogin = false;
//                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
//                return;
//            }
//            mTencent.logout(this);
//            // 第三方也可以选择注销的时候不去清除第三方的targetUin/targetMiniAppId
//            saveTargetUin("");
//            saveTargetMiniAppId("");
//            updateUserInfo();
//            updateLoginButton();
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:++++++" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };
    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new DefaultUiListener() {

                @Override
                public void onError(UiError e) {
                    Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:e" + e);
                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:response" + response);
//                    mHandler.sendMessage(msg);
//                    new Thread(){
//
//                        @Override
//                        public void run() {
//                            JSONObject json = (JSONObject)response;
//                            if(json.has("figureurl")){
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
//                                } catch (JSONException e) {
//                                    SLog.e(TAG, "Util.getBitmap() jsonException : " + e.getMessage());
//                                }
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
//                        }
//
//                    }.start();
                }

                @Override
                public void onCancel() {
                    Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:onCancel");

                }
            };
            UserInfo info = new UserInfo(this, mTencent.getQQToken());
            info.getUserInfo(listener);

        } else {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:123456");
//            mUserInfo.setText("");
//            mUserInfo.setVisibility(android.view.View.GONE);
//            mUserLogo.setVisibility(android.view.View.GONE);
        }
    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            Log.w("-->>>","token:"+token);
            Log.w("-->>>","expires:"+expires);
            Log.w("-->>>","openId:"+openId);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {

        }
    }
    private class BaseUiListener extends DefaultUiListener {

        @Override
        public void onComplete(Object response) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:response"+response);
            if (null == response) {
                ToastUtils.showShort( "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (jsonResponse.length() == 0) {
                ToastUtils.showShort( "返回为空", "登录失败");
                return;
            }
            ToastUtils.showShort( "登录成功");
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:errorDetail"+e.errorDetail);
            ToastUtils.showShort("onError: " + e.errorDetail);
        }

        @Override
        public void onCancel() {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:onCancel");
            ToastUtils.showShort("onCancel: ");
        }
    }
}
