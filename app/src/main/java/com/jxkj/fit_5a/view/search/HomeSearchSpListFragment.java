package com.jxkj.fit_5a.view.search;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;

    private Bundle bundle;
    private HomeDynamicAdapter mHomeDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_order;
    }
    private String search = "";

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
        }
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mHomeDynamicAdapter);

        mHomeDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociationActivity.startActivity(getActivity(),
                        mHomeDynamicAdapter.getData().get(position).getPublisherId(),
                        mHomeDynamicAdapter.getData().get(position).getMomentId());
            }
        });
        getData(search);
    }

    @Override
    public void initImmersionBar() {

    }

    private void getData(String search) {
        RetrofitUtil.getInstance().apiService()
                .getQueryByKeyword(search,"0")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {

                        if(result.getCode()==0 &&result.getData().size()>0){
                            lv_not.setVisibility(View.GONE);
                            mRefreshLayout.setVisibility(View.VISIBLE);
                            mHomeDynamicAdapter.setNewData(result.getData());
                        }
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

    }
}
