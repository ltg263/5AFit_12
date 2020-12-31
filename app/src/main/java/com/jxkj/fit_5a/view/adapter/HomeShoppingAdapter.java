package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.ProductListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeShoppingAdapter extends BaseQuickAdapter<ProductListBean.ListBean, BaseViewHolder> {
    public HomeShoppingAdapter(@Nullable List<ProductListBean.ListBean> data) {
        super(R.layout.item_home_commodity, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProductListBean.ListBean item) {
        GlideImageUtils.setGlideImage(mContext, item.getImgUrl(), helper.getView(R.id.iv_shop_pic));
        helper.setText(R.id.tv_shop_title, item.getName()).setText(R.id.tv_jf, item.getDeductIntegral()+"")
                .setText(R.id.tv_je, "￥ " + item.getPrice());
    }

}
