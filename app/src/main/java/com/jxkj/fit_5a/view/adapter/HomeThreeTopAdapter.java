package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeTopAdapter extends BaseQuickAdapter<CircleQueryJoinedBean.ListBean, BaseViewHolder> {
    public HomeThreeTopAdapter(@Nullable List<CircleQueryJoinedBean.ListBean> data) {
        super(R.layout.item_home_three_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleQueryJoinedBean.ListBean item) {
        if(item==null){
            helper.setVisible(R.id.tv,false);
            Glide.with(mContext).load(R.drawable.ic_home_top_all).into((ImageView) helper.getView(R.id.iv_img));
        }else{
            helper.setText(R.id.tv,item.getName());
            GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
            helper.setVisible(R.id.tv,true);
        }
    }

}
