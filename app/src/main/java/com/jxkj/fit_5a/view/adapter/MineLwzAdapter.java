package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.MedalListData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineLwzAdapter extends BaseQuickAdapter<MedalListData, BaseViewHolder> {

    public MineLwzAdapter(@Nullable List<MedalListData> data) {
        super(R.layout.item_mine_lwj, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MedalListData item) {

    }
}
