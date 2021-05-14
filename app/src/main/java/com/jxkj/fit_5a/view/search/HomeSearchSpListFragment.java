package com.jxkj.fit_5a.view.search;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.fragment.HomeThreeFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/6/116:57
 */
public class HomeSearchSpListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvSqList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;

    private Bundle bundle;
    private String search = "";
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    @Override
    protected int getContentView() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
        }
        getData();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    public void initImmersionBar() {

    }
    //内容类型(1:文本;2:图文;3:视频
    private void getData() {
        RetrofitUtil.getInstance().apiService()
                .getQuery_by_keyword(search, "3",1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() != null && result.getData().size() > 0) {
                                lv_not.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                initList(result.getData());
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

    private void initList(List<QueryPopularBean> list) {

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(list);
        mRvSqList.setLayoutManager(manager);
        mRvSqList.setHasFixedSize(true);
        mRvSqList.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startActivity(getActivity(),
                        mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                        mHomeThreeSqAdapter.getData().get(position).getMomentId());
            }
        });

        mHomeThreeSqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_head_img:
                    case R.id.tv_name:
                        UserHomeActivity.startActivity(getActivity(),mHomeThreeSqAdapter.getData().get(position).getUser().getUserId()+"");
                        break;
                    case R.id.ll_xh:
                        HomeThreeFragment.xihuan(mHomeThreeSqAdapter.getData().get(position),mHomeThreeSqAdapter);
                        break;
                }
            }
        });
    }
}
