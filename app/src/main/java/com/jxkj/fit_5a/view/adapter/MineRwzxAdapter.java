package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineRwzxAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MineRwzxAdapter(@Nullable List<String> data) {
        super(R.layout.item_mine_rwzx, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }

}
