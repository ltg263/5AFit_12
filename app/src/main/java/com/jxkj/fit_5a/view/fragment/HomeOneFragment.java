package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.HelpListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.view.StringToUtil;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.exercise.RateControlActivity;
import com.jxkj.fit_5a.view.activity.home.TaskSignActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityManageActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.adapter.HomeCommodityAdapter;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeOneFragment extends BaseFragment {
    @BindView(R.id.tv_left_text)
    TextView mTvLeftText;
    @BindView(R.id.tv_message_content)
    TextView mTvMessageContent;
    @BindView(R.id.tv_right_text)
    TextView mTvRightText;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_top_jyz)
    TextView mTvTopJyz;
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
    @BindView(R.id.lineChart1)
    LineChart mLineChart;
    private HomeTopAdapter mHomeTopAdapter;
    private HomeCommodityAdapter mHomeCommodityAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_one;
    }

    @Override
    protected void initViews() {
        initRvUi();
        initLC();
        getAdList();
        getMomentQueryPopular();
    }

    private void initLC() {
        mLineChart.getDescription().setEnabled(false);

        mLineChart.setDrawGridBackground(false);

        mLineChart.setData(generateLineData());
        mLineChart.animateX(3000);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        Legend l = mLineChart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMaximum(1.2f);
        leftAxis.setAxisMinimum(-1.2f);

        mLineChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setEnabled(false);

    }
    protected LineData generateLineData() {

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        LineDataSet ds1 = new LineDataSet(FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "sine.txt"), "Sine function");
        LineDataSet ds2 = new LineDataSet(FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "cosine.txt"), "Cosine function");

        ds1.setLineWidth(2f);
        ds2.setLineWidth(2f);

        ds1.setDrawCircles(false);
        ds2.setDrawCircles(false);

        ds1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        ds2.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);

        // load DataSets from files in assets folder
        sets.add(ds1);
//        sets.add(ds2);
        LineData d = new LineData(sets);
        d.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf"));
        return d;
    }
    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mHomeTopAdapter = new HomeTopAdapter(list);
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

        mHomeCommodityAdapter = new HomeCommodityAdapter(list);
        LinearLayoutManager ms1 = new LinearLayoutManager(getActivity());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRmspList.setLayoutManager(ms1);
        mRvRmspList.setHasFixedSize(true);
        mRvRmspList.setAdapter(mHomeCommodityAdapter);

        mHomeCommodityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), ShoppingDetailsActivity.class));
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvDtrmList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDtrmList.setHasFixedSize(true);
        mRvDtrmList.setAdapter(mHomeDynamicAdapter);

        mHomeDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), AssociationActivity.class));
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeOneFragment newInstance() {
        HomeOneFragment homeFragment = new HomeOneFragment();
        return homeFragment;
    }

    @OnClick({R.id.tv_left_text,R.id.tv_right_text, R.id.tv_top_jyz,
            R.id.tv_top_jyy, R.id.rv_top_list,R.id.on_rv_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left_text:
                FacilityManageActivity.intentActivity(getActivity());
                break;
            case R.id.tv_right_text:
                RateControlActivity.intentActivity(getActivity());
                break;
            case R.id.tv_top_jyz:
                mIvY.setVisibility(View.INVISIBLE);
                mIvZ.setVisibility(View.VISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.black));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.color_666666));
                break;
            case R.id.tv_top_jyy:
                mIvY.setVisibility(View.VISIBLE);
                mIvZ.setVisibility(View.INVISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.rv_top_list:

                break;
            case R.id.on_rv_qd:
                startActivity(new Intent(getActivity(), TaskSignActivity.class));
                break;
        }
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
                        if(isDataInfoSucceed(result)){
                            mTvMessageContent.setText(
                                    StringToUtil.getStringAll(result.getData().getList()));
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

    private void getMomentQueryPopular(){
        RetrofitUtil.getInstance().apiService()
                .getMomentQueryPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<QueryPopularBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryPopularBean result) {
                        if (result.getCode()==0) {
                            mHomeDynamicAdapter.setNewData(result.getData());
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



