package com.jxkj.fit_5a.view.activity.association;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.view.activity.mine.MineHomeActivity;
import com.jxkj.fit_5a.view.activity.mine.MineInfoActivity;
import com.jxkj.fit_5a.view.adapter.CircleDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineTopicActivity extends BaseActivity {
    @BindView(R.id.rv_list_sp)
    RecyclerView mRvListSp;
    @BindView(R.id.rv_list_tp)
    RecyclerView mRvListTp;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
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
    @BindView(R.id.tv_nr)
    TextView mTvNr;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    private CircleDynamicAdapter mCircleDynamicAdapter;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    HotTopicBean mHotTopicBean;

    private int page = 1;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_topic;
    }

    @Override
    protected void initViews() {
        mHotTopicBean = (HotTopicBean) getIntent().getSerializableExtra("mHotTopicBean");
        initTopViews();

        mCircleDynamicAdapter = new CircleDynamicAdapter(null);
        mRvListTp.setLayoutManager(new LinearLayoutManager(this));
        mRvListTp.setHasFixedSize(true);
        mRvListTp.setAdapter(mCircleDynamicAdapter);

        mCircleDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociationActivity.startActivity(MineTopicActivity.this,
                        mCircleDynamicAdapter.getData().get(position).getPublisherId(),
                        mCircleDynamicAdapter.getData().get(position).getMomentId());
            }
        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvListSp.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvListSp.setHasFixedSize(true);
        mRvListSp.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startActivity(MineTopicActivity.this,
                        mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                        mHomeThreeSqAdapter.getData().get(position).getMomentId());
            }
        });

        getQueryByPublisher(2);
        getQueryByPublisher(3);
    }

    private void initTopViews() {
        mTv.setText(mHotTopicBean.getName());
        mTvRenshu.setText(mHotTopicBean.getPageviews()+"次");
        mTvDongtai.setText(mHotTopicBean.getArticlesCount()+"条");
        mTvNr.setText(mHotTopicBean.getIntroduction());
        GlideImgLoader.loadImageViewWithCirclr(this,mHotTopicBean.getImgUrl(),mIvHeadImg);
//        Glide.with(this).asBitmap().load(mChildrenBean.getImgUrl())
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        Drawable drawable = new BitmapDrawable(resource);
//                        mRlActionbar.setBackground(drawable);
//                    }
//                });
    }


    @OnClick({R.id.iv_back, R.id.tv_share, R.id.rl1, R.id.rl2, R.id.tv_add_dt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_add_dt:
                TopicAddActivity.startActivity(this, 0);
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                mRvListSp.setVisibility(View.VISIBLE);
                mRvListTp.setVisibility(View.GONE);
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                mRvListSp.setVisibility(View.GONE);
                mRvListTp.setVisibility(View.VISIBLE);
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

    private void getQueryByPublisher(int contentType) {
        RetrofitUtil.getInstance().apiService()
                .getMomentQueryPopularTopic(contentType+"",mHotTopicBean.getName(),page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(contentType==2){
                                mCircleDynamicAdapter.setNewData(result.getData());
                            }
                            if(contentType==3){
                                mHomeThreeSqAdapter.setNewData(result.getData());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
//                        refreshLayout.finishRefresh();
//                        refreshLayout.finishLoadMore();
                    }
                });
    }

    public static void startActivity(Context mContext, HotTopicBean mHotTopicBean) {
        Intent intent = new Intent(mContext, MineTopicActivity.class);
        intent.putExtra("mHotTopicBean", mHotTopicBean);
        mContext.startActivity(intent);
    }
}
