package com.jxkj.fit_5a.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.view.activity.mine.ShoppingActivity;

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
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.tv_icon));
        helper.setText(R.id.tv_1,item.getCouponName())
                .setText(R.id.tv_2,item.getLimitAmount()+"元优惠券")
                .setText(R.id.tv_4,"有效期至："+item.getExpireDate());
        switch (item.getStatus()){
            case 1:
                helper.setBackgroundRes(R.id.rl,R.mipmap.ic_yhq_wsy)
                        .setVisible(R.id.iv_zt,false)
                        .setText(R.id.btn,"去使用")
                        .setBackgroundRes(R.id.btn,R.drawable.btn_shape_bj_theme_2);
                break;
            case 2:
                helper.setBackgroundRes(R.id.rl,R.mipmap.ic_yhq_ysx)
                        .setVisible(R.id.iv_zt,true).setImageResource(R.id.iv_zt,R.drawable.icon_yhq_1)
                        .setText(R.id.btn,"已使用")
                        .setBackgroundRes(R.id.btn,R.drawable.btn_shape_bj_cccccc_2);
                break;
            case 3:
                helper.setBackgroundRes(R.id.rl,R.mipmap.ic_yhq_ysx)
                        .setVisible(R.id.iv_zt,true).setImageResource(R.id.iv_zt,R.drawable.icon_yhq_2)
                        .setText(R.id.btn,"已失效")
                        .setBackgroundRes(R.id.btn,R.drawable.btn_shape_bj_cccccc_2);
                break;
        }
        //奖品来源1宝箱2排行榜3任务4系统发放5用户领取
        switch (item.getSource()){
            case 1:
                helper.setText(R.id.tv_3,"获得途径：宝箱");
                break;
            case 2:
                helper.setText(R.id.tv_3,"获得途径：排行榜");
                break;
            case 3:
                helper.setText(R.id.tv_3,"获得途径：任务");
                break;
            case 4:
                helper.setText(R.id.tv_3,"获得途径：系统发放");
                break;
            case 5:
                helper.setText(R.id.tv_3,"获得途径：自己领取");
                break;
        }
        helper.getView(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getStatus()==1){
                    ShoppingActivity.intentStartActivity(mContext);
                }
            }
        });
    }

}
