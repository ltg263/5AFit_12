package com.jxkj.fit_5a.view.activity.mine.order;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.view.adapter.OrderShoppingDetailsAdapter;

import java.util.ArrayList;
import java.util.List;
//
import butterknife.BindView;
public class OrderDetailsActivity extends BaseActivity {


    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initViews() {

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        List<OrderInfoData.ListBean.ProductListBean> data = new ArrayList<>();
        data.add(null);
        OrderShoppingDetailsAdapter mOrderShoppingDetailsAdapter = new OrderShoppingDetailsAdapter(this, data);//item.getProducts()
        mRvList.setAdapter(mOrderShoppingDetailsAdapter);
    }

}
