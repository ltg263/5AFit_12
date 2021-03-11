package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.NotObtainedBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineYhq_ScAdapter extends BaseQuickAdapter<NotObtainedBean.ListBean, BaseViewHolder> {
    public MineYhq_ScAdapter(@Nullable List<NotObtainedBean.ListBean> data) {
        super(R.layout.item_mine_yhq_sc, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NotObtainedBean.ListBean item) {
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.tv_icon));
        helper.setText(R.id.tv_1,item.getCouponName()).addOnClickListener(R.id.btn)
                .setText(R.id.tv_2,item.getCouponName())
                .setText(R.id.tv_4,"有效期："+item.getValidityDays()+"天");
        if(item.getValidityDays()==-1){
            helper.setText(R.id.tv_4,"长期有效");
        }
    }

}
