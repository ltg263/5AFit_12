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
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.VerifyAppOauthQq;
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
                if(!ThirdLoginUtils.mTencent.isQQInstalled(this)){
                    ToastUtils.showShort("未安装QQ");
                    return;
                }
                ThirdLoginUtils.onClickLoginQQweb(this, new ThirdLoginUtils.ThirdLoginInterface() {
                    @Override
                    public void loginInterface(String token) {
                        postVerifyAppOauth(token);
                    }
                });
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
            Tencent.onActivityResultData(requestCode,resultCode,data,ThirdLoginUtils.loginListener);
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
        SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+data.getTokenId());
        SharedUtils.singleton().put(ConstValues.USER_PHONE,data.getUserPermissionBaseDTO().getUserNo());
        SharedUtils.singleton().put(ConstValues.USERID,data.getUserPermissionBaseDTO().getId());
        SharedUtils.singleton().put(ConstValues.USER_NAME,data.getUserPermissionBaseDTO().getNickName());
        SharedUtils.singleton().put(ConstValues.USER_IMG,data.getUserPermissionBaseDTO().getAvatar());
        SharedUtils.singleton().put(ConstValues.USER_AGE,data.getUserPermissionBaseDTO().getAge());
        SharedUtils.singleton().put(ConstValues.USER_GENDER,data.getUserPermissionBaseDTO().getGender());
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

    private void postVerifyAppOauth(String token) {
        RetrofitUtil.getInstance().apiService()
                .postVerifyAppOauth("qqweb",token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VerifyAppOauthQq>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VerifyAppOauthQq> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData().isBindFlag()){
                                SharedUtils.singleton().put(ConstValues.ISLOGIN,true);
                                SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+result.getData().getTokenId());
                                SharedUtils.singleton().put(ConstValues.USER_PHONE,result.getData().getUserBase().getUserNo());
                                SharedUtils.singleton().put(ConstValues.USERID,result.getData().getUserBase().getId());
                                SharedUtils.singleton().put(ConstValues.USER_NAME,result.getData().getUserBase().getNickName());
                                SharedUtils.singleton().put(ConstValues.USER_IMG,result.getData().getUserBase().getAvatar());
                                SharedUtils.singleton().put(ConstValues.USER_AGE,result.getData().getUserBase().getAge());
                                SharedUtils.singleton().put(ConstValues.USER_GENDER,result.getData().getUserBase().getGender());
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }else{
                                SharedUtils.singleton().put(ConstValues.THIRD_LOGIN_BIND_INFO,result.getData().getThirdLoginBindInfo());
                                IntentUtils.getInstence().intent(LoginActivity.this,LoginBindPhoneActivity.class);
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

}
