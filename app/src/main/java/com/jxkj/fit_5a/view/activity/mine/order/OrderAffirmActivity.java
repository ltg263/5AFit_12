package com.jxkj.fit_5a.view.activity.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ShowOrderInfo;
import com.jxkj.fit_5a.view.adapter.OrderAffirmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderAffirmActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    private OrderAffirmAdapter mOrderAffirmAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_affirm;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我的订单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRv();

        PostOrderInfo info = (PostOrderInfo) getIntent().getSerializableExtra("info");
        postShowOrderInfo(info);
    }

    private void initRv() {
        mOrderAffirmAdapter = new OrderAffirmAdapter( null);//item.getProducts()
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mOrderAffirmAdapter);
    }

    @OnClick({R.id.ll_back, R.id.rl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                break;
            case R.id.rl_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
        }
    }

    private void postShowOrderInfo(PostOrderInfo info) {
        RetrofitUtil.getInstance().apiService()
                .postShowOrderInfo(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ShowOrderInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ShowOrderInfo> result) {
                        if(isDataInfoSucceed(result)){
                            ShowOrderInfo data = result.getData();
                            if (data.getUserAddress() != null) {
                                mTvName.setText("收件人："+data.getUserAddress().getAcceptName()+"  电话："+data.getUserAddress().getMobile());
                                mTvAddress.setText(data.getUserAddress().getLocation());
                            }
                            if(data.getOrderProducts()!=null){
                                mOrderAffirmAdapter.setNewData(data.getOrderProducts());
                            }
                        }else{
                            OrderAffirmActivity.this.finish();
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
