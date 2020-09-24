package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.adapter.FacilityManageAdapter;
import com.zkk.view.rulerview.RulerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RateControlActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_lsydsb_list)
    RecyclerView mRvLsydsbList;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.tv_jl)
    TextView mTvJl;
    @BindView(R.id.ruler_weight)
    RulerView mRulerWeight;
    @BindView(R.id.tv_tz)
    TextView mTvTz;
    @BindView(R.id.tv_xlzb)
    TextView mTvXlzb;
    @BindView(R.id.tv_ydsj)
    TextView mTvYdsj;
    private FacilityManageAdapter mFacilityManageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_rate_control;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("心率控制");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
        mTvRighttext.setText("新增");
        initRvUi();
    }

    private void initRvUi() {
    //亲, 您的年纪是30岁, 参考最大心跳值110下/min
        String str = "亲, 您的年纪是<font color=\"#000000\"><big><big>30</big></big></font>" +
                "参考最大心跳值<font color=\"#000000\"><big><big>110</big></big></font>下/min";
        mTvXlzb.setText(Html.fromHtml(str));
    //0h0m表示无时间限制
        String str1 = "<font color=\"#000000\"><big><big>0</big></big></font>" +
                "h<font color=\"#000000\"><big><big>0</big></big></font>m表示无时间限制";
        mTvYdsj.setText(Html.fromHtml(str1));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

        mFacilityManageAdapter = new FacilityManageAdapter(list);

        mRvLsydsbList.setLayoutManager(new LinearLayoutManager(this));
        mRvLsydsbList.setHasFixedSize(true);
        mRvLsydsbList.setAdapter(mFacilityManageAdapter);

        mRulerWeight.setOnValueChangeListener(value -> mTvTz.setText(value + ""));
        /**
         *
         * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
         * @param minValue   最大数值
         * @param maxValue   最小的数值
         * @param per   最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1 在demo中 身高mPerValue为1  体重mPerValue 为0.1
         */
        mRulerWeight.setValue(60, 40, 300, 0.1f);
    }

    @OnClick({R.id.tv_righttext, R.id.iv_rightimg,R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_righttext:
            case R.id.iv_rightimg:
                startActivity(new Intent(this, FacilityAddSbActivity.class));
                break;
            case R.id.tv_ok:
                startActivity(new Intent(this, RatePatternActivity.class));
                break;
        }
    }
}
