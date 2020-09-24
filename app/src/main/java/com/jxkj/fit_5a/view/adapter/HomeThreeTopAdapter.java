package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeThreeTopAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_three_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

        if(item.equals("-1")){
            Glide.with(mContext).load(R.drawable.ic_home_top_all).into((ImageView) helper.getView(R.id.iv_img));
        }
    }

}
