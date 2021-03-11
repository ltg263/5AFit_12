package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineYhq_DlqAdapter extends BaseQuickAdapter<DiscountUsableNotBean.ListBean, BaseViewHolder> {
    public MineYhq_DlqAdapter(@Nullable List<DiscountUsableNotBean.ListBean> data) {
        super(R.layout.item_mine_yhq_dlq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DiscountUsableNotBean.ListBean item) {
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.tv_icon));
        helper.setText(R.id.tv_1,item.getCouponName())
                .setText(R.id.tv_2,item.getCouponName())
                .setText(R.id.tv_4,"有效期："+item.getValidityDays()+"天");
    }

}
