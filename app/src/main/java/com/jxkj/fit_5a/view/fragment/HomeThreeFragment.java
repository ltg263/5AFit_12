package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.association.InterestAllActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.TopicAllActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeRmhtAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeTopAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeThreeFragment extends BaseFragment {


    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_rmht_list)
    RecyclerView mRvRmhtList;
    @BindView(R.id.rv_sq_list)
    RecyclerView mRvSqList;
    private HomeThreeTopAdapter mHomeThreeTopAdapter;
    private HomeThreeRmhtAdapter mHomeThreeRmhtAdapter;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    int page,pageSize;
    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three;
    }

    @Override
    protected void initViews() {
        initRvUi();
        page = 1;
        pageSize = 3;
        getCircleQueryJoined();
        getMomentQueryPopular();
    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

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

        mHomeThreeRmhtAdapter = new HomeThreeRmhtAdapter(list);
        mRvRmhtList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRmhtList.setHasFixedSize(true);
        mRvRmhtList.setAdapter(mHomeThreeRmhtAdapter);

        mHomeThreeRmhtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), MineTopicActivity.class));

            }
        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvSqList.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvSqList.setHasFixedSize(true);
        mRvSqList.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeSqAdapter.getData().get(position).getContentType()==3){
                    startActivity(new Intent(getActivity(), VideoActivity.class));
                }else{
                    startActivity(new Intent(getActivity(), AssociationActivity.class));
                }
            }
        });
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
                IntentUtils.getInstence().intent(getActivity(), SearchGoodsActivity.class);
                break;
            case R.id.tv_refresh:
                getCircleQueryJoined();
                break;
            case R.id.tv_topic_all:
                startActivity(new Intent(getActivity(), TopicAllActivity.class));
                break;
            case R.id.tv_right_text:
                startActivity(new Intent(getActivity(), AssociationAddActivity.class));
                break;
        }
    }

    private int totalPage;
    private void getCircleQueryJoined(){
        RetrofitUtil.getInstance().apiService()
                .getCircleQueryJoined(SharedUtils.getUserId()+"",page,pageSize)
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
                            totalPage = StringUtil.getTotalPage(result.getData().getTotal(), pageSize);
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
                .subscribe(new Observer<QueryPopularBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryPopularBean result) {
                        if (result.getCode()==0) {
                            mHomeThreeSqAdapter.setNewData(result.getData());
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



