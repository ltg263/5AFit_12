package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.CircleQueryBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class InterestListAdapter extends BaseQuickAdapter<CircleQueryBean.ListBean, BaseViewHolder> {
    public InterestListAdapter(@Nullable List<CircleQueryBean.ListBean> data) {
        super(R.layout.item_interest_all, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleQueryBean.ListBean item) {
        helper.setText(R.id.tv1,item.getName())
            .setText(R.id.tv2,item.getExplain())
        .setText(R.id.tv3,item.getMemberCount()+"人")
        .setText(R.id.tv4,item.getMomentCount()+"条");
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.iv));
        if(item.isJoin()){
            helper.setText(R.id.tv5,"已加入");
        }else{
            helper.setText(R.id.tv5,"未加入");
        }
    }

}
