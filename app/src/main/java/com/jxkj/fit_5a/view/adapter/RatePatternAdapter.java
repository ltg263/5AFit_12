package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.RatePatternBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class RatePatternAdapter extends BaseQuickAdapter<RatePatternBean, BaseViewHolder> {
    public RatePatternAdapter(@Nullable List<RatePatternBean> data) {
        super(R.layout.item_rate_pattern, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RatePatternBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_v,item.getV());
    }

}
