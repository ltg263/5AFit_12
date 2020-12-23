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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/6/116:57
 */
public class HomeSearchDpListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRecyclerView;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private Bundle bundle;

    @Override
    protected int getContentView() {
        return R.layout.fragment_order;
    }
    private String search="";
//    HomeShopJiaListAdapter mHomeShopJiaListAdapter;

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
        }
//        getData();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    public void initImmersionBar() {

    }

//    private void getData() {
//        show(getActivity(), "获取中...");
//        RetrofitUtil.getInstance().apiService()
//                .homeBusinessList(null,search,
//                        SharedUtils.singleton().get(ConstValues.LAT, ""),
//                        SharedUtils.singleton().get(ConstValues.LON, ""),
//                        null)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<BaseResult<BusinessFjShopBean>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseResult<BusinessFjShopBean> result) {
//                        dismiss();
//                        if (result.getCode().equals("000000")) {
//                            if(result.getData().getList().size()>0){
//                                lv_not.setVisibility(View.GONE);
//                                mRefreshLayout.setVisibility(View.VISIBLE);
//
//                                initList(result.getData().getList());
//                            }
//                            mRefreshLayout.finishRefresh();
//                            mRefreshLayout.finishLoadMore();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        dismiss();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        dismiss();
//                    }
//                });
//
//    }
//    private void initList(List<BusinessFjShopBean.ListBean> list) {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mHomeShopJiaListAdapter = new HomeShopJiaListAdapter(list);
//        mRecyclerView.setAdapter(mHomeShopJiaListAdapter);
//        mHomeShopJiaListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                IntentUtils.getInstence().intent(getActivity(),
//                        HomeShopDetailsActivity.class, "id", list.get(position).getId()+"");
//            }
//        });
//    }
}
