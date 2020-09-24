package com.jxkj.fit_5a.view.activity.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.OrderAffirmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderAffirmActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_affirm;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我的订单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRv();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        OrderAffirmAdapter mOrderAffirmAdapter = new OrderAffirmAdapter(this, list);//item.getProducts()
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
}
