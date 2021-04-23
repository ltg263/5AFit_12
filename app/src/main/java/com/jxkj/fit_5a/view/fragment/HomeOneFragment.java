package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AATooltip;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.AdminInspireBean;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.exercise.HistoryEquipmentActivity;
import com.jxkj.fit_5a.view.activity.home.RankListActivity;
import com.jxkj.fit_5a.view.activity.home.TaskSignActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiaosu.view.text.DataSetAdapter;
import com.xiaosu.view.text.VerticalRollingTextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeOneFragment extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.tv_left_text)
    TextView mTvLeftText;
    @BindView(R.id.tv_message_content)
    VerticalRollingTextView mTvMessageContent;
    @BindView(R.id.tv_right_text)
    TextView mTvRightText;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_top_jyz)
    TextView mTvTopJyz;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_top)
    ImageView iv_top;
    @BindView(R.id.tv_top_jyy)
    TextView mTvTopJyy;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.tv_zs)
    TextView mTvZs;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.iv_y)
    ImageView mIvY;
    @BindView(R.id.iv_z)
    ImageView mIvZ;
    @BindView(R.id.rv_rmsp_list)
    RecyclerView mRvRmspList;
    @BindView(R.id.rv_dtrm_list)
    RecyclerView mRvDtrmList;
    @BindView(R.id.tv_phb_2)
    TextView mTvPhb2;
    @BindView(R.id.iv_phb_2)
    ImageView mIvPhb2;
    @BindView(R.id.tv_phb_22)
    TextView mTvPhb22;
    @BindView(R.id.tv_phb_1)
    TextView mTvPhb1;
    @BindView(R.id.iv_phb_1)
    ImageView mIvPhb1;
    @BindView(R.id.tv_phb_11)
    TextView mTvPhb11;
    @BindView(R.id.tv_phb_3)
    TextView mTvPhb3;
    @BindView(R.id.iv_phb_3)
    ImageView mIvPhb3;
    @BindView(R.id.tv_phb_33)
    TextView mTvPhb33;
    private HomeTopAdapter mHomeTopAdapter;
    private HomeShoppingAdapter mHomeShoppingAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;
    private AAChartModel aaChartModel;
    private boolean isDay7 = true;
    String[] str7, str30;

    private int page = 1;
    @Override
    protected int getContentView() {
        return R.layout.fragment_home_one;
    }

    @Override
    protected void initViews() {
        str7 = StringUtil.getDays(7, "dd").toArray(new String[7]);
        str30 = StringUtil.getDays(30, "dd").toArray(new String[30]);
        initRvUi();
        getProductList(1);
        getAdList();
        getMomentQueryPopular();
        getSportLogStats(null);
        getRankList();
        getAdminInspire();

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getMomentQueryPopular();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getProductList(1);
                getAdList();
                getMomentQueryPopular();
                getSportLogStats(null);
                getAdminInspire();
            }
        });
    }


    private void initRvUi() {
        mHomeTopAdapter = new HomeTopAdapter(null);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(ms);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        LinearLayoutManager ms1 = new LinearLayoutManager(getActivity());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRmspList.setLayoutManager(ms1);
        mRvRmspList.setHasFixedSize(true);
        mRvRmspList.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(getActivity(), mHomeShoppingAdapter.getData().get(position).getId());
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvDtrmList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDtrmList.setHasFixedSize(true);
        mRvDtrmList.setAdapter(mHomeDynamicAdapter);

        mHomeDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mHomeDynamicAdapter.getData().get(position).getContentType().equals("3")) {
                    String media = mHomeDynamicAdapter.getData().get(position).getMedia();
                    try {
                        JSONArray jsonArray = new JSONArray(media);
//                        VideoActivity.startActivity(getActivity(), jsonArray.getJSONObject(0).getString("vedioId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    VideoActivity.startActivity(getActivity(),
                            mHomeDynamicAdapter.getData().get(position).getPublisherId(),
                            mHomeDynamicAdapter.getData().get(position).getMomentId());
                } else {
                    AssociationActivity.startActivity(getActivity(),
                            mHomeDynamicAdapter.getData().get(position).getPublisherId(),
                            mHomeDynamicAdapter.getData().get(position).getMomentId());
                }
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }

    private void getAdminInspire() {
        RetrofitUtil.getInstance().apiService()
                .getAdminInspire()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdminInspireBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdminInspireBean> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()!=null && result.getData()!=null){
                                tv_title.setText(result.getData().getTitle());
                                Glide.with(getActivity()).load(result.getData().getBackImg()).into(iv_top);
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

    public static HomeOneFragment newInstance() {
        HomeOneFragment homeFragment = new HomeOneFragment();
        return homeFragment;
    }

    @OnClick({R.id.tv_left_text, R.id.tv_right_text, R.id.tv_top_jyz,R.id.tv_phb_ck,R.id.tv_gdsp,
            R.id.tv_top_jyy, R.id.on_rv_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left_text:
                IntentUtils.getInstence().intent(getActivity(),HistoryEquipmentActivity.class);
                break;
            case R.id.tv_right_text:
                IntentUtils.getInstence().intent(getActivity(), FacilityAddSbActivity.class,"type","00");
                break;
            case R.id.tv_top_jyz:
                mIvY.setVisibility(View.INVISIBLE);
                mIvZ.setVisibility(View.VISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.black));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.color_666666));
                isDay7 = true;
                getSportLogStats(null);
                break;
            case R.id.tv_top_jyy:
                mIvY.setVisibility(View.VISIBLE);
                mIvZ.setVisibility(View.INVISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.black));
                isDay7 = false;
                getSportLogStats(null);
                break;
            case R.id.on_rv_qd:
                startActivity(new Intent(getActivity(), TaskSignActivity.class));
                break;
            case R.id.tv_phb_ck:
                startActivity(new Intent(getActivity(), RankListActivity.class));
                break;
            case R.id.tv_gdsp:
                ShoppingActivity.intentStartActivity(getActivity());
                break;
        }
    }

    private void getSportLogStats(String deviceType) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        if (isDay7) {
            calendar.add(Calendar.DAY_OF_YEAR, -7);
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, -30);
        }

        RetrofitUtil.getInstance().apiService()
                .getSportLogStats(String.valueOf(calendar.getTime().getTime()), String.valueOf(System.currentTimeMillis()), deviceType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogStatsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogStatsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<SportLogStatsBean.ListBean> lists = result.getData().getList();
                            initAAChar(lists);
                            List<String> list = new ArrayList<>();
                            list.add("总卡路里");
                            list.add("总里程");
                            list.add("总计时间");
                            list.add("平均时间");
                            list.add("BAI");
                            mHomeTopAdapter.setNewData(list, result.getData().getStats());
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

    private AAOptions aaOptions;

    private void initAAChar(List<SportLogStatsBean.ListBean> lists) {
        AAChartModel aaChartModel = configureChartModel(lists);
//        if (aaOptions == null) {
        AATooltip aaTooltip = new AATooltip()
                .useHTML(true)
                .formatter("function () {\n" +
                        "function getDay(day){\n" +
                        "    var today = new Date();\n" +
                        "    var targetday_milliseconds=today.getTime() + 1000*60*60*24;\n" +
                        "    today.setTime(targetday_milliseconds);\n" +
                        "    var tYear = today.getFullYear();\n" +
                        "    var tMonth = today.getMonth();\n" +
                        "    tMonth = doHandleMonth(tMonth + 1);\n" +
                        "     if(new Date().getTime() < new Date(tYear+\"-\"+tMonth+\"-\"+day).getTime()){\n" +
                        "        tMonth = doHandleMonth(today.getMonth());\n" +
                        "     }\n" +
                        "    day = doHandleMonth(day);\n" +
                        "    return tYear+\"-\"+tMonth+\"-\"+day;\n" +
                        "}\n" +
                        "function doHandleMonth(month){\n" +
                        "    var m = month;\n" +
                        "    if(month.toString().length == 1){\n" +
                        "     m = \"0\" + month;\n" +
                        "    }\n" +
                        "    return m;\n" +
                        "}" +
                        "        var h = Math.floor(this.points[0].y / 3600) < 10 ? '0'+Math.floor(this.points[0].y / 3600) : Math.floor(this.points[0].y / 3600);\n" +
                        "        var m = Math.floor((this.points[0].y / 60 % 60)) < 10 ? '0' + Math.floor((this.points[0].y / 60 % 60)) : Math.floor((this.points[0].y / 60 % 60));\n" +
                        "        var s = Math.floor((this.points[0].y % 60)) < 10 ? '0' + Math.floor((this.points[0].y % 60)) : Math.floor((this.points[0].y % 60));\n" +
                        "        var str =  '';\n" +
                        "        if(h == \"00\"){\n" +
                        "            str = m + '分' + s +'秒';\n" +
                        "        }else{" +
                        "            str = h + '时' + m + '分' + s +'秒';\n" +
                        "        }\n" +
                        "        str = this.points[0].y+'秒';\n" +
                        "        str = str.replace('.','分');\n"+
                        "        var s0 = '' + '<b>' +  getDay(this.x) + '</b>' + '<br/>';\n" +
                        "        var colorDot1 = '<span style=\\\"' + 'color:#FFB300; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var colorDot2 = '<span style=\\\"' + 'color:#FFA1A1; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var colorDot3 = '<span style=\\\"' + 'color:#A1DFFF; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var s1 = colorDot1 + this.points[0].series.name + ': ' + str + '<br/>';\n" +
                        "        var s2 = colorDot2 + this.points[1].series.name + ': ' + this.points[1].y + 'cal' + '<br/>';\n" +
                        "        var s3 = colorDot3 + this.points[2].series.name + ': ' + this.points[2].y + 'km';\n" +
                        "        s0 += (s1 + s2+ s3);\n" +
                        "        return s0;\n" +
                        "    }");
        aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        aaOptions.tooltip(aaTooltip);
//        }
        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
    }

    private AAChartModel configureChartModel(List<SportLogStatsBean.ListBean> lists) {
        String[] str;
        if (!isDay7) {
            str = str30;
            aaChartModel = new AAChartModel()
                    .chartType(AAChartType.Areaspline)
                    .title("")
                    .yAxisTitle("")
                    .yAxisLabelsEnabled(false)
                    .categories(str)
                    .yAxisGridLineWidth(0f)
                    .xAxisGridLineWidth(0f)
                    .legendEnabled(false)
                    .yAxisGridLineWidth(0f)
                    .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)
                    .gradientColorEnable(true)
                    .markerRadius(0f)
                    .markerSymbol(AAChartSymbolType.Circle)
                    .scrollablePlotArea(
                            new AAScrollablePlotArea()
                                    .minWidth(str.length*50)
                                    .scrollPositionX(1f)
                    )
                    .series(configureTheStyleForDifferentTypeChart(lists));
        } else {
            str = str7;
            aaChartModel = new AAChartModel()
                    .chartType(AAChartType.Areaspline)
                    .title("")
                    .yAxisTitle("")
                    .yAxisLabelsEnabled(false)
                    .categories(str)
                    .yAxisGridLineWidth(0f)
                    .xAxisGridLineWidth(0f)
                    .legendEnabled(false)
                    .yAxisGridLineWidth(0f)
                    .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)
                    .gradientColorEnable(true)
                    .markerRadius(0f)
                    .markerSymbol(AAChartSymbolType.Circle)
                    .scrollablePlotArea(
                            new AAScrollablePlotArea()
                                    .scrollPositionX(1f)
                    )
                    .series(configureTheStyleForDifferentTypeChart(lists));
        }
        return aaChartModel;
    }

    private AASeriesElement[] configureTheStyleForDifferentTypeChart(List<SportLogStatsBean.ListBean> lists) {
        ArrayList<String> a = StringUtil.getDays(7, "yyyyMMdd");
        if (!isDay7) {
            a.clear();
            a = StringUtil.getDays(30, "yyyyMMdd");
        }
        Object[] ydsc = new Object[a.size()];
        Object[] kll = new Object[a.size()];
        Object[] zlc = new Object[a.size()];
        for (int i = 0; i < a.size(); i++) {
            if (lists == null || lists.size() == 0) {
                ydsc[i] = 0;
                kll[i] = 0;
                zlc[i] = 0;
                continue;
            }
            for (int j = 0; j < lists.size(); j++) {
                ydsc[i] = 0;
                kll[i] = 0;
                zlc[i] = 0;
                if (a.get(i).equals(lists.get(j).getDatestr())) {
                    ydsc[i] = StringUtil.getTimeFenMiao(lists.get(j).getTotalDuration());
                    kll[i] = lists.get(j).getTotalCalories();
                    zlc[i] = lists.get(j).getTotalDistance();
                    break;
                }

            }
        }
        AASeriesElement element1 = new AASeriesElement()
                .name("运动时长")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#FFB300")
                .data(ydsc);

        AASeriesElement element2 = new AASeriesElement()
                .name("卡路里")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#FFA1A1")
                .data(kll);

        AASeriesElement element3 = new AASeriesElement()
                .name("总里程")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#A1DFFF")
                .data(zlc);

        return new AASeriesElement[]{element1, element2, element3};
    }

    private void getRankList() {
        RetrofitUtil.getInstance().apiService()
                .getRankList(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankListData> result) {
                        if(isDataInfoSucceed(result)){
                            List<RankListData.ListBean> list = result.getData().getList();
                            if(list!=null && list.size()>0){
                                getRankDetails(list.get(0).getId());
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

    private void getRankDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getRankDetails(Integer.valueOf(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankDetailsData> result) {
                        if (isDataInfoSucceed(result)) {
                            List<RankDetailsData.RankRewardsBean> rankRewards = result.getData().getRankRewards();
                            if(rankRewards!=null && rankRewards.size()>0){
                                for(int i=0;i<rankRewards.size();i++){
                                    RankDetailsData.RankRewardsBean rankReward = rankRewards.get(i);
                                    switch (i){
                                        case 0:
                                            mTvPhb1.setText("第"+rankReward.getStartRank()+"-"+rankReward.getEndRank()+"名");
                                            if(rankReward.getStartRank()==rankReward.getEndRank()){
                                                mTvPhb1.setText("第"+rankReward.getStartRank()+"名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(),rankReward.getImgUrl(),mIvPhb1);
                                            mTvPhb11.setText(rankReward.getName());
                                            break;
                                        case 1:
                                            mTvPhb2.setText("第"+rankReward.getStartRank()+"-"+rankReward.getEndRank()+"名");
                                            if(rankReward.getStartRank()==rankReward.getEndRank()){
                                                mTvPhb2.setText("第"+rankReward.getStartRank()+"名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(),rankReward.getImgUrl(),mIvPhb2);
                                            mTvPhb22.setText(rankReward.getName());
                                            break;
                                        case 2:
                                            mTvPhb3.setText("第"+rankReward.getStartRank()+"-"+rankReward.getEndRank()+"名");
                                            if(rankReward.getStartRank()==rankReward.getEndRank()){
                                                mTvPhb3.setText("第"+rankReward.getStartRank()+"名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(),rankReward.getImgUrl(),mIvPhb3);
                                            mTvPhb33.setText(rankReward.getName());
                                            break;

                                    }
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

    private void getProductList(Integer hasHot) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(hasHot)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeShoppingAdapter.setNewData(result.getData().getList());
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


    private void getAdList() {
        RetrofitUtil.getInstance().apiService()
                .getAdList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdListData> result) {
                        if (isDataInfoSucceed(result)) {
//                            mTvMessageContent.setText(
//                                    StringToUtil.getStringAll(result.getData().getList()));

                            List<AdListData.ListBean> data = result.getData().getList();
                            if(data!=null && data.size()>0){
                                mTvMessageContent.setDataSetAdapter(new DataSetAdapter<AdListData.ListBean>(data) {
                                    @Override
                                    protected CharSequence text(AdListData.ListBean s) {
                                        return s.getTitle();
                                    }
                                });
                                mTvMessageContent.run();
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

    private void getMomentQueryPopular() {
        RetrofitUtil.getInstance().apiService()
                .getMomentQueryPopular(page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0) {
                            mHomeDynamicAdapter.setNewData(result.getData());
                            refreshLayout.finishLoadMoreWithNoMoreData();
//                            totalPage = StringUtil.getTotalPage(result.getData().getTotalCount(), ConstValues.PAGE_SIZE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }
}



