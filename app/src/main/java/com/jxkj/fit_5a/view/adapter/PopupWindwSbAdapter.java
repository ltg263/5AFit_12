package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.entity.TopicAllBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class PopupWindwSbAdapter extends BaseQuickAdapter<DeviceTypeData.ListBean, BaseViewHolder> {
    public PopupWindwSbAdapter(@Nullable List<DeviceTypeData.ListBean> data) {
        super(R.layout.item_topic_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceTypeData.ListBean item) {
        helper.setText(R.id.tv1,item.getName());
    }

}
