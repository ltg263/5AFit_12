package com.jxkj.fit_5a.view.activity.login_other;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.conpoment.utils.ToastUtil;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    }

    @OnClick({R.id.tv_login_yzm, R.id.tv_login_wjmm, R.id.iv_login_wx,R.id.iv_iconsole,R.id.tv_go_login, R.id.ll_go_zc,R.id.tv_go_yzm})
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
                getPlatformInfo();
                break;
            case R.id.iv_iconsole:
                Intent intent = getPackageManager().getLaunchIntentForPackage("https://developer.android.com/training/app-links/deep-linking");
                startActivity(intent);
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
                            SharedUtils.singleton().put(ConstValues.USER_PASSWORD, finalMm);
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

    private void getPlatformInfo() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //6.0以上动态获取权限
                if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
                    return;
                }
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String registrationId = "";
                if(tm.getDeviceId()!=null){
                    registrationId = tm.getDeviceId();
                }
//                TextUtil.logOut("--:"+registrationId);
                Log.w("微信登录1：","-->>:"+registrationId);
                Log.w("微信登录2：","-->>:"+map.get("accessToken"));
                Log.w("微信登录3：","-->>:"+map.get("openid"));

//                HttpRequestUtils.getVerifyAppEmpower(LoginActivity.this,
//                        map.get("accessToken"), map.get("openid"), registrationId, null,
//                        new HttpRequestUtils.LoginInterface() {
//                            @Override
//                            public void succeed(String path) {
////                                if(StringUtil.isBlank(path.getHashMap().getUserNo())){
////                                    IntentUtils.getInstence().intent(getActivity(), BindingPhoneNumberActivity.class);
////                                    return;
////                                }
////                                getData();
//                            }
//                        });

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                dismiss();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                dismiss();
            }
        });
    }
    private void quitLogin() {
        UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//                GlideCircleTransform.glideCircleImg(R.drawable.ic_icon_img, mCivHead);
//                mTvName.setText("");
//                mTvId.setText("请先登录");
//                mTvConcernNumber.setText("0");
//                mTvDynamicNumber.setText("0");
//                mTvEtcNumber.setText("0");
//                mTvJfNumber.setText("0");
//                mTvYeNumber.setText("0");
//                tv_jkhb.setVisibility(View.GONE);
                SharedUtils.singleton().clear();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                dismiss();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                dismiss();
            }
        });
    }

    private void guitLogin(){

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
}
