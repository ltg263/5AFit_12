package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.CircleDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.UserTopAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserHomeActivity extends BaseActivity {

    //    @BindView(R.id.iv_parallax)
//    ImageView mIvParallax;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.rl_allinfo)
    RelativeLayout mRlAllinfo;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.toolbar_avatar)
    ImageView mToolbarAvatar;
    @BindView(R.id.iv_date)
    ImageView mIvDate;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar1)
    Toolbar mToolbar1;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.rl_actionbar)
    AppBarLayout mAppbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.rv_qz_list)
    RecyclerView mRvQzList;
    @BindView(R.id.rv_dt_list)
    RecyclerView mRvDtList;
    @BindView(R.id.rv_dt_list_sp)
    RecyclerView mRvDtListSp;

    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.iv_back1)
    ImageView mIvBack1;
    @BindView(R.id.tv_gz_zt)
    TextView mTvGzZt;
    @BindView(R.id.ll_gz)
    LinearLayout mLlGz;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.tv_dj)
    TextView mTvDj;
    @BindView(R.id.tv_gz)
    TextView mTvGz;
    @BindView(R.id.ll_gz_on)
    LinearLayout mLlGzOn;
    @BindView(R.id.tv_fs)
    TextView mTvFs;
    @BindView(R.id.ll_fs_on)
    LinearLayout mLlFsOn;
    @BindView(R.id.tv_sc)
    TextView mTvSc;
    @BindView(R.id.ll_sc_on)
    LinearLayout mLlScOn;
    @BindView(R.id.tv_lw)
    TextView mTvLw;
    @BindView(R.id.ll_lw_on)
    LinearLayout mLlLwOn;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl1)
    RelativeLayout mRl1;
    @BindView(R.id.rl2)
    RelativeLayout mRl2;
    @BindView(R.id.ll)
    LinearLayout mLl;
    String userId;
    CircleDynamicAdapter mCircleDynamicAdapter;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    private UserTopAdapter mUserTopAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_user_home;
    }

    @Override
    protected void initViews() {
        initRv();
        initListener();
        userId = getIntent().getStringExtra("userId");
        getCircleQueryJoined();
        getUserProfileOwn();
        getQueryByPublisher(2);
        getQueryByPublisher(3);
    }

    private void initRv() {
        mUserTopAdapter = new UserTopAdapter(null);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvQzList.setLayoutManager(ms);
        mRvQzList.setHasFixedSize(true);
        mRvQzList.setAdapter(mUserTopAdapter);
        mUserTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(UserHomeActivity.this, MineCircleActivity.class);
                mIntent.putExtra("id",mUserTopAdapter.getData().get(position).getId());
                startActivity(mIntent);
            }
        });

        mCircleDynamicAdapter = new CircleDynamicAdapter(null);
        mRvDtList.setLayoutManager(new LinearLayoutManager(this));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mCircleDynamicAdapter);
        mCircleDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociationActivity.startActivity(UserHomeActivity.this,
                        mCircleDynamicAdapter.getData().get(position).getPublisherId(),
                        mCircleDynamicAdapter.getData().get(position).getMomentId());
            }
        });


        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvDtListSp.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvDtListSp.setHasFixedSize(true);
        mRvDtListSp.setAdapter(mHomeThreeSqAdapter);
        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startActivity(UserHomeActivity.this,
                        mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                        mHomeThreeSqAdapter.getData().get(position).getMomentId());
            }
        });
    }

    private void initListener() {
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            //6
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mCollapsingToolbar.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                float fraction = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                mCollapsingToolbar.setAlpha(fraction);
                mToolbar1.setAlpha(fraction);
                mToolbar.setAlpha(1 - fraction);

            }
        });
    }

    @OnClick({R.id.tv_gz_zt,R.id.iv_back, R.id.rl1, R.id.rl2, R.id.ll_gz_on, R.id.ll_fs_on, R.id.ll_lw_on, R.id.ll_sc_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_gz_on:
                UserGzActivity.startActivity(this, userId);
                break;
            case R.id.ll_fs_on:
                UserFsActivity.startActivity(this, userId);
                break;
            case R.id.ll_lw_on:
                startActivity(new Intent(this, UserLwActivity.class));
                break;
            case R.id.ll_sc_on:
                UserScActivity.startActivity(this, userId);
                break;
            case R.id.tv_gz_zt:
                if(mTvGzZt.getText().toString().equals("关注")){
                    show();
                    HttpRequestUtils.postfollow(userId, new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            dismiss();
                            if(path.equals("0")){
                                mTvGzZt.setText("已关注");
                            }
                        }
                    });
                }else{
                    show();
                    HttpRequestUtils.postfollowCancel(userId, new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            dismiss();
                            if(path.equals("1")){
                                mTvGzZt.setText("关注");
                            }
                        }
                    });
                }
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                mRvDtList.setVisibility(View.VISIBLE);
                mRvDtListSp.setVisibility(View.GONE);
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                mRvDtList.setVisibility(View.GONE);
                mRvDtListSp.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_999999));
        mTv2.setTextColor(getResources().getColor(R.color.color_999999));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
//        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }


    private void getUserProfileOwn() {
        RetrofitUtil.getInstance().apiService()
                .getUserProfile(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserOwnInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserOwnInfo> userOwnInfoResult) {
                        if (isDataInfoSucceed(userOwnInfoResult)) {
                            initUi(userOwnInfoResult.getData());
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


    private void initUi(UserOwnInfo data) {
        GlideImageUtils.setGlideImage(this, data.getAvatar(), mIvHead);
        mTvGzZt.setText("关注");
        if(data.getRelation().equals("1") || data.getRelation().equals("3")){
            mTvGzZt.setText("已关注");
        }
        mNickname.setText(data.getNickName());
        mTvState.setText(data.getExplain());
        mTvGz.setText(data.getFollowCount());
        mTvFs.setText(data.getFansCount());
        mTvSc.setText(data.getFavoriteCount());
        mTvLw.setText(data.getGiftCount());
//        if(data.getRelation()==4){
//
//        }
    }


    private void getCircleQueryJoined(){
        RetrofitUtil.getInstance().apiService()
                .getCircleQueryJoined(userId,1,100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleQueryJoinedBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleQueryJoinedBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mUserTopAdapter.setNewData(result.getData().getList());
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

    private void getQueryByPublisher(int contentType) {
        RetrofitUtil.getInstance().apiService()
                .getQueryByPublisher(0, userId,contentType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0) {
                            if (contentType == 2) {
                                mCircleDynamicAdapter.setNewData(result.getData());
                            }
                            if (contentType == 3) {
                                mHomeThreeSqAdapter.setNewData(result.getData());
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

    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, UserHomeActivity.class);
        intent.putExtra("userId", userId);
        mContext.startActivity(intent);
    }
}
