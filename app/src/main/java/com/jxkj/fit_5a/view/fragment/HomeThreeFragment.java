package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.CommunityHomeInfoBean;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.association.InterestAllActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.TopicAllActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeRmhtAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeTopAdapter;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeThreeFragment extends BaseFragment {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_rmht_list)
    RecyclerView mRvRmhtList;
    @BindView(R.id.rv_sq_list)
    RecyclerView mRvSqList;
    @BindView(R.id.rl_actionbar)
    LinearLayout mRlActionbar;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    private HomeThreeTopAdapter mHomeThreeTopAdapter;
    private HomeThreeRmhtAdapter mHomeThreeRmhtAdapter;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    int page = 1;
    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three;
    }

    @Override
    protected void initViews() {
        initRvUi();
//        getCircleQueryJoined();
//        getHotTopicList();
        getMomentQueryPopular();



        mRlActionbar.setAlpha(1);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY<=500){
                    mRlActionbar.setAlpha(1.0f-(float) scrollY / 500.0f);
                }else{
                    mRlActionbar.setAlpha(0);
                }
            }
        });
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getMomentQueryPopular();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getMomentQueryPopular();
            }
        });
    }

    private void initRvUi() {
        mHomeThreeTopAdapter = new HomeThreeTopAdapter(null);
        mRvTopList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeThreeTopAdapter);

        mHomeThreeTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeTopAdapter.getData().get(position)==null){
                    startActivity(new Intent(getActivity(), InterestAllActivity.class));
                }else{
                    Intent mIntent = new Intent(getActivity(), MineCircleActivity.class);
                    mIntent.putExtra("id",mHomeThreeTopAdapter.getData().get(position).getId());
                    startActivity(mIntent);
                }
            }
        });

        List<CircleQueryJoinedBean.ListBean> data = new ArrayList<>();
        data.add(null);
        mHomeThreeTopAdapter.setNewData(data);

        mHomeThreeRmhtAdapter = new HomeThreeRmhtAdapter(null);
        mRvRmhtList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRmhtList.setHasFixedSize(true);
        mRvRmhtList.setAdapter(mHomeThreeRmhtAdapter);

        mHomeThreeRmhtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MineTopicActivity.startActivity(getActivity(),mHomeThreeRmhtAdapter.getData().get(position));

            }
        });
        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvSqList.setHasFixedSize(true);
        mRvSqList.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvSqList.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_head_img:
                    case R.id.tv_name:
                        UserHomeActivity.startActivity(getActivity(),mHomeThreeSqAdapter.getData().get(position).getUser().getUserId()+"");
                        break;
                    case R.id.ll_xh:
                        xihuan(mHomeThreeSqAdapter.getData().get(position),mHomeThreeSqAdapter);
                        break;
                }
            }
        });
        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeSqAdapter.getData().get(position).getContentType().equals("3")){
                    VideoActivity.startActivity(getActivity(),
                            mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                            mHomeThreeSqAdapter.getData().get(position).getMomentId());
                }else{
                    AssociationActivity.startActivity(getActivity(),
                            mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                            mHomeThreeSqAdapter.getData().get(position).getMomentId());
                }
            }
        });
    }
    public static void xihuan(QueryPopularBean data,HomeThreeSqAdapter mAdapter){
        if(data.isIsLike()){
            HttpRequestUtils.postLikeCancel1("0",data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        data.setIsLike(false);
                        data.setLikeCount((Integer.parseInt(data.getLikeCount())-1)+"");
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else{
            HttpRequestUtils.postLike1("0",data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")) {
                        data.setIsLike(true);
                        data.setLikeCount((Integer.parseInt(data.getLikeCount())+1)+"");
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
    @Override
    public void initImmersionBar() {

    }

    public static HomeThreeFragment newInstance() {
        HomeThreeFragment homeFragment = new HomeThreeFragment();
        return homeFragment;
    }

    @OnClick({R.id.tv_refresh, R.id.tv_topic_all,R.id.tv_right_text,R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                IntentUtils.getInstence().intent(getActivity(), SearchGoodsActivity.class,"searchType",1);
                break;
            case R.id.tv_refresh:
                getCircleQueryJoined();
                break;
            case R.id.tv_topic_all:
                startActivity(new Intent(getActivity(), TopicAllActivity.class));
                break;
            case R.id.tv_right_text:
                initPopupWindow();
                break;
        }
    }
    PopupWindowTy window;

    List<String> list = new ArrayList<>();
    private void initPopupWindow() {
        list.clear();
        list.add("相册");
        list.add("视频");
        if (window == null) {
            window = new PopupWindowTy(getActivity(), list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    int type = 3;
                    if (position == 0) {
                        type = 2;
                    }
                    IntentUtils.getInstence().intent(getActivity(), AssociationAddActivity.class,"type",type);
                }
            });

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        window.showAtLocation(mRvTopList, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }

    private int totalPage;
    private void getCircleQueryJoined(){
        RetrofitUtil.getInstance().apiService()
                .getCircleQueryJoined(SharedUtils.getUserId()+"",1,3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleQueryJoinedBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleQueryJoinedBean> result) {
                        if (result.getCode()==0) {
                            List<CircleQueryJoinedBean.ListBean> data = result.getData().getList();
                            data.add(null);
                            mHomeThreeTopAdapter.setNewData(data);
                            totalPage = StringUtil.getTotalPage(result.getData().getTotal(), 3);
                            page++;
                            if(totalPage <= page){
                                page = 1;
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

    private void getMomentQueryPopular(){
        RetrofitUtil.getInstance().apiService()
                .getCommunityHomeInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CommunityHomeInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CommunityHomeInfoBean> result) {
                        if (result.getCode()==0) {
                            List<CircleQueryJoinedBean.ListBean> data = result.getData().getHotCircles();
                            data.add(null);
                            mHomeThreeTopAdapter.setNewData(data);
                            mHomeThreeRmhtAdapter.setNewData(result.getData().getHotTopics());
                            mHomeThreeSqAdapter.setNewData(result.getData().getHotMoments());
                            refreshLayout.finishLoadMoreWithNoMoreData();
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

    private void getHotTopicList(){
        RetrofitUtil.getInstance().apiService()
                .getHotTopicList(null,1,3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<HotTopicBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<HotTopicBean> result) {
                        if (result.getCode()==0) {
                            mHomeThreeRmhtAdapter.setNewData(result.getData());
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



