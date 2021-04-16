package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.adapter.FacilityManageAdapter;
import com.zkk.view.rulerview.RulerView;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
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
    int maxV = 220;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    private FacilityManageAdapter mFacilityManageAdapter;
    private List<TemplateBean.ListBean> textList = new ArrayList<>();
    private List<BpmDataBean> mBpmDataBeans = new ArrayList<>();
    String movingTye = "";
    int age = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_AGE,""));
    double bfb5,bfb6,bfb7,bfb8,bfb9,bfb;
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

        bfb5 = (maxV-age)*0.5+40;
        bfb6 = (maxV-age)*0.6+40;
        bfb7 = (maxV-age)*0.7+40;
        bfb8 = (maxV-age)*0.8+40;
        bfb9 = (maxV-age)*0.9+40;
        bfb  = (maxV-age)*1+40;
        initBpmData();
    }

    private void initBpmData() {
        mBpmDataBeans.add(new BpmDataBean("非运动区间(0~50%)",0,bfb5,0));
        mBpmDataBeans.add(new BpmDataBean("热身心率区间(50~60%)",bfb5,bfb6,0));
        mBpmDataBeans.add(new BpmDataBean("燃脂心率区间(60~70%)",bfb6,bfb7,0));
        mBpmDataBeans.add(new BpmDataBean("有氧耐力心率区间(70~80%)",bfb7,bfb8,0));
        mBpmDataBeans.add(new BpmDataBean("无氧耐力心率区间(80~90%)",bfb8,bfb9,0));
        mBpmDataBeans.add(new BpmDataBean("极限心率区间(90~100%)",bfb9,bfb,0));

        setMovingTye(120.0f);
    }

    private void initRvUi() {
        //亲, 您的年纪是30岁, 参考最大心跳值110下/min
        if(!SharedUtils.singleton().get(ConstValues.USER_AGE,"").equals("0")){
//            age = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_AGE,""));
        }
        String str = "亲, 您的年纪是<font color=\"#000000\"><big><big>"+age+"</big></big></font>" +
                "参考最大心跳值<font color=\"#000000\"><big><big>"+209+"</big></big></font>下/min";
        mTvXlzb.setText(Html.fromHtml(str));
        //0h0m表示无时间限制
        //(220-11)*131
        String str1 = "<font color=\"#000000\"><big><big>0</big></big></font>" +
                "h<font color=\"#000000\"><big><big>0</big></ /font>m表示无时间限制";
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
            setMovingTye(value);
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
        mRulerWeight.setValue(60, 40, 220, 1);
    }

    private void setMovingTye(float value) {
        if(value>0 && value<bfb5){
            movingTye = "非运动区间(0~50%)(0bpm~"+(int) Math.ceil(bfb5)+"bpm)";
        }else if(value>bfb5 && value<bfb6){
            movingTye = "热身心率区间(50~60%)("+(int) Math.ceil(bfb5)+"bpm~"+(int) Math.ceil(bfb6)+"bpm)";
        }else if(value>bfb6 && value<bfb7){
            movingTye = "燃脂心率区间(60~70%)("+(int) Math.ceil(bfb6)+"bpm~"+(int) Math.ceil(bfb7)+"bpm)";
        }else if(value>bfb7 && value<bfb8){
            movingTye = "有氧耐力心率区间(70~80%)("+(int) Math.ceil(bfb7)+"bpm~"+(int) Math.ceil(bfb8)+"bpm)";
        }else if(value>bfb8 && value<bfb9){
            movingTye = "无氧耐力心率区间(80~90%)("+(int) Math.ceil(bfb8)+"bpm~"+(int) Math.ceil(bfb9)+"bpm)";
        }else if(value>bfb9){
            movingTye = "极限心率区间(90~100%)("+(int) Math.ceil(bfb9)+"bpm~"+(int) Math.ceil(bfb)+"bpm)";
        }
    }

    List<String> listTime = new ArrayList<>();

    @OnClick({R.id.ll_back,R.id.tv_righttext, R.id.iv_rightimg, R.id.tv_ok, R.id.rl_1, R.id.rl_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
            case R.id.iv_rightimg:
                FacilityAddSbActivity.intentActivity(this);
                break;
            case R.id.tv_ok:
                if(PopupWindowLanYan.ble4Util!=null && PopupWindowLanYan.ble4Util.isConnect()){
                    Intent mIntent = new Intent(this, RatePatternActivity.class);
                    mIntent.putExtra("movingTye",movingTye);
                    mIntent.putParcelableArrayListExtra("mBpmDataBeans", (ArrayList<? extends Parcelable>) mBpmDataBeans);
                    startActivity(mIntent);
                    finish();
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
                            textList.addAll(result.getData().getList());
                            double ab = 120.0 / maxV;
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
