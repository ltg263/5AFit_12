package com.jxkj.fit_5a.view.activity.association;


import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.view.BlurringView;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.adapter.CircleDynamicAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineCircleActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.CoordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv_jiaru)
    TextView tv_jiaru;
    @BindView(R.id.blurring_view)
    BlurringView mBlurringView;
    @BindView(R.id.rl_11)
    RelativeLayout rl11;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_share)
    TextView mTvShare;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.iv_head_img)
    ImageView mIvHeadImg;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_renshu)
    TextView mTvRenshu;
    @BindView(R.id.tv_jianjie)
    TextView mTvJianjie;
    @BindView(R.id.tv_dongtai)
    TextView mTvDongtai;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_nr)
    TextView mTvNr;
    @BindView(R.id.tv_renshu_1)
    TextView mTvRenshu1;
    @BindView(R.id.tv_dk)
    TextView mTvDk;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.rl1)
    RelativeLayout mRl1;
    @BindView(R.id.rl2)
    RelativeLayout mRl2;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tv_add_dt)
    ImageView mTvAddDt;
    private int id;
    private CircleDynamicAdapter mCircleDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_circle;
    }

    @Override
    protected void initViews() {
        id = getIntent().getIntExtra("id", 0);
        getCircleDetails();

        mCircleDynamicAdapter = new CircleDynamicAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mCircleDynamicAdapter);

        mCircleDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociationActivity.startActivity(MineCircleActivity.this,
                        id+"",
                        mCircleDynamicAdapter.getData().get(position).getPublisherId(),
                        mCircleDynamicAdapter.getData().get(position).getMomentId());
            }
        });
        mBlurringView.setBlurredView(mRvList);
        rl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getQuery_popular();
    }

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_add_dt, R.id.tv_jiaru, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_jiaru:
                getCircleJoin(id);
                break;
            case R.id.tv_add_dt:
                CircleAddActivity.startActivity(this,id);
                break;
            case R.id.rl1:
                getQuery_popular();
                initView(mTv1, mView1);
                break;
            case R.id.rl2:
                getQguery_lately();
                initView(mTv2, mView2);
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_666666));
        mTv2.setTextColor(getResources().getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }

    private void getCircleDetails() {
        RetrofitUtil.getInstance().apiService()
                .getCircleDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleDetailsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            CircleDetailsBean data = result.getData();
                            if (data.isJoin()) {
                                tv_jiaru.setVisibility(View.GONE);
                                rl11.setVisibility(View.GONE);
                            }
                            GlideImgLoader.loadImageViewRadius(MineCircleActivity.this,data.getImgUrl(),10,mIvHeadImg);
                            mTv.setText(data.getName());
                            mTvRenshu.setText(data.getMemberCount()+"人");
                            mTvDongtai.setText(data.getMomentCount()+"条");
                            mTvJianjie.setText(data.getExplain());

                            GlideImgLoader.loadImageViewRadius(MineCircleActivity.this,data.getTaskImg(),10,mIvIcon);
                            mTvRenshu1.setText(data.getCompletedCount()+"人正在坚持");
                            mTvNr.setText(data.getTaskExplain());
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

    private void getCircleJoin(int id) {
        RetrofitUtil.getInstance().apiService()
                .getCircleJoin(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("加入成功");
                            tv_jiaru.setVisibility(View.GONE);
                            rl11.setVisibility(View.GONE);
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


    private void getQuery_popular() {
        RetrofitUtil.getInstance().apiService()
                .getQuery_popular(id, 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mCircleDynamicAdapter.setNewData(result.getData());
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


    private void getQguery_lately() {
        RetrofitUtil.getInstance().apiService()
                .getQguery_lately(id, 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mCircleDynamicAdapter.setNewData(result.getData());
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

    private void getCircleQuit(int id) {
        RetrofitUtil.getInstance().apiService()
                .getCircleQuit(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("退出成功");
                            tv_jiaru.setVisibility(View.VISIBLE);
                            rl11.setVisibility(View.VISIBLE);
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
