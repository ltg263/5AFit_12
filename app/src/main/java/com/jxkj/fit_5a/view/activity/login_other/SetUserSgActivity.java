package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.view.RulerView_xz;
import com.jxkj.fit_5a.view.activity.mine.MineInfoActivity;
import com.zkk.view.rulerview.RulerView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SetUserSgActivity extends BaseActivity {


    @BindView(R.id.tv_sg)
    TextView mTvSg;
    @BindView(R.id.tv_tz)
    TextView mTvTz;
    @BindView(R.id.iv_xb)
    ImageView mIvXb;
    @BindView(R.id.ruler_weight)
    RulerView mRulerWeight;
    @BindView(R.id.ruler_height)
    RulerView_xz mRulerHeight;
    int sbType;
    String csrq;
    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_set_user_sg;
    }

    @Override
    protected void initViews() {
        MainApplication.getContext().addActivity(this);
        sbType = getIntent().getIntExtra("sbType",0);
        csrq = getIntent().getStringExtra("csrq");
        if(sbType==1){
            mIvXb.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ren_nan));
        }
        mRulerWeight.setOnValueChangeListener(value -> mTvTz.setText(value + ""));
        /**
         *
         * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
         * @param minValue   最大数值
         * @param maxValue   最小的数值
         * @param per   最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1 在demo中 身高mPerValue为1  体重mPerValue 为0.1
         */
        mRulerWeight.setValue(60.0f, 40, 300, 0.1f);

        mRulerHeight.setOnValueChangeListener(value -> mTvSg.setText((int) value + "cm"));
        /**
         *
         * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
         * @param minValue   最大数值
         * @param maxValue   最小的数值
         * @param per   最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1 在demo中 身高mPerValue为1  体重mPerValue 为0.1
         */
        mRulerHeight.setValue(130, 40, 250, 1f);
    }

    @OnClick({R.id.tv_go_xyb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_xyb:
                // 日期格式为yyyy-MM-dd
                Intent intent = new Intent(SetUserSgActivity.this, InterestActivity.class);
                intent.putExtra("sbType",sbType+"");
                intent.putExtra("csrq",csrq+"");
                intent.putExtra("sg",mTvSg.getText().toString().replace("cm",""));
                intent.putExtra("tz",mTvTz.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
