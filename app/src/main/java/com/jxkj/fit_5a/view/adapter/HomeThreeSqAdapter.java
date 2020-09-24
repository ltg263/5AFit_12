package com.jxkj.fit_5a.view.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
public class HomeThreeSqAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeThreeSqAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_three_sq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;

        if(item.equals("-2")){
            Glide.with(mContext).load(R.mipmap.ic_13).into((ImageView) helper.getView(R.id.iv_icon));
        }else{
            Glide.with(mContext).load(R.mipmap.ic_12).into((ImageView) helper.getView(R.id.iv_icon));
        }
    }

}
