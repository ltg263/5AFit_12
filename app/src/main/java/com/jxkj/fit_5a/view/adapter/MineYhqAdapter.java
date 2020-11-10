package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineYhqAdapter extends BaseQuickAdapter<PrizeListData.ListBean, BaseViewHolder> {
    public MineYhqAdapter(@Nullable List<PrizeListData.ListBean> data) {
        super(R.layout.item_mine_yhq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PrizeListData.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.tv_icon));
        helper.setText(R.id.tv_1,item.getCouponName())
                .setText(R.id.tv_2,item.getLimitAmount()+"元优惠券")
                .setText(R.id.tv_3,"获得途径："+item.getDetails())
                .setText(R.id.tv_4,item.getUsageTime()+"-"+item.getExpireTime());
        switch (item.getStatus()){
            case 1:
                helper.setBackgroundRes(R.id.rl,R.mipmap.ic_yhq_wsy)
                        .setVisible(R.id.iv_zt,false)
                        .setBackgroundRes(R.id.btn,R.drawable.btn_shape_bj_theme_2);
                break;
            case 2:
                helper.setBackgroundRes(R.id.rl,R.mipmap.ic_yhq_ysx)
                        .setVisible(R.id.iv_zt,true).setImageResource(R.id.iv_zt,R.drawable.icon_yhq_1)
                        .setBackgroundRes(R.id.btn,R.drawable.btn_shape_bj_cccccc_2);
                break;
            case 3:
                helper.setBackgroundRes(R.id.rl,R.mipmap.ic_yhq_ysx)
                        .setVisible(R.id.iv_zt,true).setImageResource(R.id.iv_zt,R.drawable.icon_yhq_2)
                        .setBackgroundRes(R.id.btn,R.drawable.btn_shape_bj_cccccc_2);
                break;
        }
    }

}
