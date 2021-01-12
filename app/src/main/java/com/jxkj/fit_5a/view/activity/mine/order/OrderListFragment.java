package com.jxkj.fit_5a.view.activity.mine.order;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.view.adapter.OrderListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;

    private Bundle bundle;
    private int totalPage;

    @Override
    protected int getContentView() {
        return R.layout.fragment_order_list;
    }

    private List<OrderInfoData.ListBean> orderDataList = new ArrayList<>();
    private int type = 0;
    OrderListAdapter orderListAdapter;

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initOrder();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                orderDataList.clear();
                getData();
            }
        });
    }

    @Override
    public void initImmersionBar() {
        if(orderListAdapter.getData().size()==0){
            refreshData();
        }

    }

    private void initOrder() {

        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderListAdapter = new OrderListAdapter(orderDataList);
        rvList.setAdapter(orderListAdapter);
        orderListAdapter.setMyOrderAdapterListener(new OrderListAdapter.MyOrderAdapterListener() {
            @Override
            public void setLxcj(int position) {
//                IntentUtils.getInstence().callPhone(getActivity(),orderDataList.get(position).getTel());
            }

            @Override
            public void setQxdd(int position) {
//                getCancelOrder(orderDataList.get(position).getId()+"");
            }

            @Override
            public void setQzf(int position) {
                Bundle bundle1 = new Bundle();
//                bundle1.putString("id", orderDataList.get(position).getId() + "");
//                bundle1.putString("price", orderDataList.get(position).getRealAmount() + "");
//                bundle1.putString("time", orderDataList.get(position).getExpireTime() + "");
//                IntentUtils.getInstence().intent(getActivity(), ShoppingPaymentActivity.class, "order", bundle1);
            }

            @Override
            public void setQrsh(int position) {
//                getTakeOverOrder(orderDataList.get(position).getId()+"");
            }

            @Override
            public void setQpj(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("id", orderDataList.get(position).getId()+"");
//                bundle.putString("businessId", orderDataList.get(position).getBusinessId()+"");
//                bundle.putString("businessImgUrl", orderDataList.get(position).getBusinessImgUrl());
//                bundle.putString("businessName", orderDataList.get(position).getBusinessName());
//                List<OrderInfoData.ListBean.ProductsBean> list = orderDataList.get(position).getProducts();
//                List<EvaluteGoodsInfo> listGoods = new ArrayList<>();
//                for (int i= 0;i<list.size();i++){
//                    listGoods.add(new EvaluteGoodsInfo(list.get(i).getProductId()+"",list.get(i).getImgUrl(),list.get(i).getName(),1));
//                }
//
//                bundle.putParcelableArrayList("listGoods", (ArrayList<? extends Parcelable>) listGoods);
//
//                IntentUtils.getInstence().intent(getActivity(), MineOrderEvaluateGoodsActivity.class, "OrderInfoData", bundle);
            }

            @Override
            public void setZlyd(int position) {
                getAgainOrder(orderDataList.get(position).getId()+"");
            }

            @Override
            public void setSc(int position) {
                postDelete(orderDataList.get(position).getId()+"");
            }

            @Override
            public void setSqtk(int position) {
//                MineOrderSqtkGoodsActivity.startActivityIntent(getActivity(),orderDataList.get(position));
            }
        });
        orderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(getActivity(), OrderDetailsActivity.class, "id", "");
            }
        });

        getData();
    }

    private void getAgainOrder(String id) {
        PostUser.Expediting expediting = new PostUser.Expediting();
        expediting.setOrderId(id);
        RetrofitUtil.getInstance().apiService()
                .postExpediting(expediting)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("已通知卖家");
                        }

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

    private void postDelete(String id) {
        PostUser.Expediting expediting = new PostUser.Expediting();
        expediting.setOrderId(id);
        RetrofitUtil.getInstance().apiService()
                .postDelete(expediting)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("已通知卖家");
                        }

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

    public void refreshData() {
        page = 1;
        orderDataList.clear();
        show(getActivity());
        getData();
    }

    private int page = 1;

    private void getData() {
        String status = null;
        if(type !=0){
            status = String.valueOf(type);
        }
        RetrofitUtil.getInstance().apiService()
                .getOrderAll(page, ConstValues.PAGE_SIZE, status)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrderInfoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<OrderInfoData> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getList().size()>0){
                                lv_not.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                            }
                            orderDataList.addAll(result.getData().getList());
                            orderListAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();
                            totalPage = StringUtil.getTotalPage(result.getData().getTotalCount(), ConstValues.PAGE_SIZE);
                            if(totalPage <= page){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }

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
