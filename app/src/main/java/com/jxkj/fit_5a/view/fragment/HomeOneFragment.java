package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartAnimationType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AADataElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.view.StringToUtil;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.exercise.RateControlActivity;
import com.jxkj.fit_5a.view.activity.home.TaskSignActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityManageActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
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
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
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
    private HomeTopAdapter mHomeTopAdapter;
    private HomeShoppingAdapter mHomeShoppingAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_one;
    }

    @Override
    protected void initViews() {
        initRvUi();
        getProductList(1);
        getAdList();
        getMomentQueryPopular();
        initAAChar();
    }

    private AAOptions aaOptions;
    private AAChartModel aaChartModel;
    private void initAAChar() {

        AAChartModel aaChartModel = configureChartModel();
        if (aaOptions == null) {
            aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        }

        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
    }


    private AAChartModel configureChartModel() {

        AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Spline)
                .title("")
                .yAxisTitle("")
                .legendEnabled(false)
                .yAxisGridLineWidth(0f)
                .scrollablePlotArea(
                        new AAScrollablePlotArea()
                                .minWidth(500)
                                .scrollPositionX(1f)
                )
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("Tokyo")
                                .data(configureSeriesDataArray())

                });
        this.aaChartModel = aaChartModel;

        configureTheStyleForDifferentTypeChart();
        return aaChartModel;
    }

    private void configureTheStyleForDifferentTypeChart() {
        aaChartModel
                .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)//设置折线连接点样式为:边缘白色
                .markerRadius(2f);


        AASeriesElement element1 = new AASeriesElement()
                .name("卡路里")
                .lineWidth(3f)
                .data(new Object[]{50, 320, 130, 370, 230, 400,});

        AASeriesElement element2 = new AASeriesElement()
                .name("总里程")
                .lineWidth(3f)
                .data(new Object[]{80, 190, 210, 340, 240, 350,});

        AASeriesElement element3 = new AASeriesElement()                                                                                                             
                .name("总时间")
                .lineWidth(3f)
                .data(new Object[]{120, 370, 180, 280, 260, 100,});

        aaChartModel
                .animationType(AAChartAnimationType.SwingFromTo)
                .series(new AASeriesElement[]{element1, element2, element3});
    }

    private AADataElement[] configureSeriesDataArray() {
        int maxRange = 388;
        AADataElement[] numberArr1 = new AADataElement[maxRange];

        double y1;
        int max = 38, min = 1;
        int random = (int) (Math.random() * (max - min) + min);
        for (int i = 0; i < maxRange; i++) {
            y1 = Math.sin(random * (i * Math.PI / 180)) + i * 2 * 0.01;
            AADataElement aaDataElement = new AADataElement()
                    .y((float) y1);

            numberArr1[i] = aaDataElement;
        }

        return numberArr1;
    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("卡路里");
        list.add("总里程");
        list.add("总时间");
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

        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        LinearLayoutManager ms1 = new LinearLayoutManager(getActivity());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRmspList.setLayoutManager(ms1);
        mRvRmspList.setHasFixedSize(true);
        mRvRmspList.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(getActivity(),mHomeShoppingAdapter.getData().get(position).getId());
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvDtrmList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDtrmList.setHasFixedSize(true);
        mRvDtrmList.setAdapter(mHomeDynamicAdapter);

        mHomeDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociationActivity.startActivity(getActivity(),
                        mHomeDynamicAdapter.getData().get(position).getPublisherId(),
                        mHomeDynamicAdapter.getData().get(position).getMomentId());
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
                        if(isDataInfoSucceed(result)){
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
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
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



