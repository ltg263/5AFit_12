package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.FavoriteQueryList;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserScAdapter extends BaseQuickAdapter<FavoriteQueryList, BaseViewHolder> {
    public UserScAdapter(@Nullable List<FavoriteQueryList> data) {
        super(R.layout.item_user_sc, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FavoriteQueryList item) {

    }

}
