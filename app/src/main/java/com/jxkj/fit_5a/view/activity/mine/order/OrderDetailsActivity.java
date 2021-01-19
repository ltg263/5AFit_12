package com.jxkj.fit_5a.view.activity.mine.order;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.OrderDetailsData;
import com.jxkj.fit_5a.view.adapter.OrderShoppingDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//

public class OrderDetailsActivity extends BaseActivity {


    @BindView(R.id.tv_zt)
    TextView mTvZt;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_order_1)
    TextView mTvOrder1;
    @BindView(R.id.tv_order_2)
    TextView mTvOrder2;
    @BindView(R.id.tv_order_3)
    TextView mTvOrder3;
    @BindView(R.id.tv_order_4)
    TextView mTvOrder4;
    @BindView(R.id.tv_order_5)
    TextView mTvOrder5;
    @BindView(R.id.bnt0)
    TextView btn0;
    @BindView(R.id.bnt1)
    TextView btn1;
    @BindView(R.id.bnt2)
    TextView btn2;
    private OrderShoppingDetailsAdapter mOrderShoppingDetailsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initViews() {

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mOrderShoppingDetailsAdapter = new OrderShoppingDetailsAdapter(this, null);//item.getProducts()
        mRvList.setAdapter(mOrderShoppingDetailsAdapter);
        getOrderDetails(getIntent().getStringExtra("id"));
    }


    private void getOrderDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getOrderDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrderDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrderDetailsData> result) {
                        if (isDataInfoSucceed(result)) {
                            initUi(result.getData());
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
//单个订单状态1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
    private void initUi(OrderDetailsData data) {
        mOrderShoppingDetailsAdapter.setNewData(data.getProductList());
        mTvName.setText("收件人："+data.getDetail().getAcceptName()+"  "+data.getDetail().getMobile());
        mTvAddress.setText(data.getDetail().getLocation());
        mTvOrder1.setText("订单编号："+data.getOrderNo());
        mTvOrder2.setText("支付方式："+(data.getOrderNo().equals("1")?"微信":"支付宝"));
        mTvOrder3.setText("交易编号："+"---");
        mTvOrder4.setText("下单时间："+data.getCreateTime());
        btn0.setVisibility(View.GONE);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        //1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
        switch (data.getStatus()) {
            case "1"://待支付
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("取消订单");
                btn2.setVisibility(View.VISIBLE);
                btn2.setText("去支付");
                break;
            case "2"://待发货
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("提醒发货");
                break;
            case "3"://待收货
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("查看物流");
                btn2.setVisibility(View.VISIBLE);
                btn2.setText("确认收货");
                break;
            case "4"://待评价
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("删除订单");
                btn2.setVisibility(View.VISIBLE);
                btn2.setText("去评价");
                break;
            case "5"://已完成
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("申请退款");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("删除订单");
                break;
            case "6"://已取消
            case "7"://已过期
            case "8"://已超时
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("删除订单");
                break;
            default:
                btn0.setVisibility(View.GONE);
        }
        mTvOrder5.setText("发货时间："+data.getExpireTime());
    }


    @OnClick({R.id.iv_back, R.id.bnt1, R.id.bnt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bnt1:
                break;
            case R.id.bnt2:
                break;
        }
    }
}
