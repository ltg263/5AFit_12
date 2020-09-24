package com.jxkj.fit_5a.view.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.OrderInfoData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2615:09
 */
public class OrderShoppingDetailsAdapter extends BaseQuickAdapter<OrderInfoData.ListBean.ProductsBean, BaseViewHolder> {
    Context activity;
    public OrderShoppingDetailsAdapter(Context activity, @Nullable List<OrderInfoData.ListBean.ProductsBean> data) {
        super(R.layout.item_order_shopping_details, data);
        this.activity = activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderInfoData.ListBean.ProductsBean item) {
//        ImageView iv_img = helper.getView(R.id.iv_img);
//        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_shop_name = helper.getView(R.id.tv_shop_name);
//        TextView tv_price = helper.getView(R.id.tv_price);
//        tv_name.setText(item.getName());
        String str = "单价：<font color=\"#FF6666\">4890积分 + 24元</font>";
        tv_shop_name.setText( Html.fromHtml(str));
//        tv_price.setText("￥"+item.getRealPrice());
//        GlideImgLoader.loadImageViewRadius(activity, item.getImgUrl(),10, iv_img);


    }


}
