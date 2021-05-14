package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class FacilityAddAdapter extends BaseQuickAdapter<DeviceDrandData.ListBean, BaseViewHolder> {
    public FacilityAddAdapter(@Nullable List<DeviceDrandData.ListBean> data) {
        super(R.layout.item_facility_add, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceDrandData.ListBean item) {
        GlideImgLoader.loadImage(mContext,item.getImg(), helper.getView(R.id.iv));
        helper.setText(R.id.tv,item.getName());
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.rl,R.drawable.bj_shape_line_theme_6);
        }else{
            helper.setBackgroundRes(R.id.rl,0);
        }
        GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv));
    }

}
