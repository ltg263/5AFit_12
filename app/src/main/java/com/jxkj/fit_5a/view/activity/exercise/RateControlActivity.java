package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.adapter.FacilityManageAdapter;
import com.zkk.view.rulerview.RulerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.tv_text)
    TextView mTvText;
    int maxV = 210 - 33;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    private FacilityManageAdapter mFacilityManageAdapter;
    private List<TemplateBean.ListBean> textList;

    @Override
    protected int getContentView() {
        return R.layout.activity_rate_control;
    }

    @Override
    protected void initViews() {
        getTemplateList();
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

        mRulerWeight.setOnValueChangeListener(value -> {
            mTvTz.setText(value + "");
            double ab = value / maxV;
            for (int i = 0; i < textList.size(); i++) {
                TemplateBean.ListBean data = textList.get(i);
                double start = data.getStartInterval();
                double end = data.getEndInterval();
                if (ab >= start && ab <= end) {
                    String str0 = "<font color=\"" + data.getParams().get(0).getColor() + "\">" + data.getParams().get(0).getValue() + "</font>"
                            + (data.getContent().replace("${str}", ""));
                    mTvText.setText(Html.fromHtml(str0));
                }
            }
        });
        /**
         *
         * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
         * @param minValue   最大数值
         * @param maxValue   最小的数值
         * @param per   最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1 在demo中 身高mPerValue为1  体重mPerValue 为0.1
         */
        mRulerWeight.setValue(60, 0, maxV, 1);
    }

    List<String> listTime = new ArrayList<>();

    @OnClick({R.id.tv_righttext, R.id.iv_rightimg, R.id.tv_ok, R.id.rl_1, R.id.rl_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
            case R.id.iv_rightimg:
                startActivity(new Intent(this, FacilityAddSbActivity.class));
                break;
            case R.id.tv_ok:
                if(PopupWindowLanYan.ble4Util.isConnect()){
                    startActivity(new Intent(this, RatePatternActivity.class));
                }
                break;
            case R.id.rl_1:
                listTime.clear();
                listTime.add("0");
                listTime.add("1");
                listTime.add("2");
                listTime.add("3");
                listTime.add("4");
                listTime.add("5");
                listTime.add("6");
                PickerViewUtils.selectorCustom(this, listTime, "运动时间", mTvSj);
                break;
            case R.id.rl_2:
                listTime.clear();
                listTime.add("0");
                listTime.add("20");
                listTime.add("30");
                listTime.add("40");
                listTime.add("50");
                listTime.add("60");
                PickerViewUtils.selectorCustom(this, listTime, "运动时间", mTvJl);
                break;
        }
    }

    public static void intentActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext, RateControlActivity.class));
    }

    private void getTemplateList() {
        RetrofitUtil.getInstance().apiService()
                .getTemplateList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TemplateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TemplateBean> result) {
                        if (isDataInfoSucceed(result)) {
                            textList = result.getData().getList();
                            double ab = 80.0 / maxV;
                            for (int i = 0; i < textList.size(); i++) {
                                TemplateBean.ListBean data = textList.get(i);
                                double start = data.getStartInterval();
                                double end = data.getEndInterval();
                                if (ab >= start && ab <= end) {
                                    String str0 = "<font color=\"" + data.getParams().get(0).getColor() + "\">"
                                            + data.getParams().get(0).getValue() + "</font>"
                                            + (data.getContent().replace("${str}", ""));
                                    mTvText.setText(Html.fromHtml(str0));
                                }
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
