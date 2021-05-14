package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineSetActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("设 置");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }


    @OnClick({R.id.ll_back, R.id.ll1, R.id.ll2,R.id.tv_tui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll1:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                break;
            case R.id.ll2:
                break;
            case R.id.tv_tui:

                DialogUtils.showDialogHint(this, "您确定要退出吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        startActivity(new Intent(MineSetActivity.this, LoginActivity.class));
                        MainApplication.getContext().AppExit();
                        SharedUtils.singleton().clear();
                        deleteOauth();
                        finish();
                    }
                });
                break;
        }
    }


    private void deleteOauth() {
        UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN,new UMAuthListener() {

            @Override
            public void onError(SHARE_MEDIA platform, int arg1,Throwable arg2) {
                Log.e("weixin deleteAuth", "=== deleteAuth onError ===");
            }

            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int arg1, Map<String, String> arg2) {
                Log.e("weixin deleteAuth", "=== deleteAuth onComplete ===");
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int arg1) {
                Log.e("weixin deleteAuth", "=== deleteAuth onCancel ===");
            }
        });
    }
}

