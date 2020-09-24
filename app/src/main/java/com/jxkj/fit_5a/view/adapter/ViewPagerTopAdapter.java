package com.jxkj.fit_5a.view.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.ViewPagerTopBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ViewPagerTopAdapter extends BaseQuickAdapter<ViewPagerTopBean, BaseViewHolder> {
    public ViewPagerTopAdapter(@Nullable List<ViewPagerTopBean> data) {
        super(R.layout.item_view_pager_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ViewPagerTopBean item) {
        TextView tv = helper.getView(R.id.tv);
        View v = helper.getView(R.id.view);
        tv.setText(item.getTitle());

        tv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv.setTextColor(mContext.getResources().getColor(R.color.color_666666));
        v.setVisibility(View.INVISIBLE);

        if(item.isSelect()){
            tv.setTextColor(mContext.getResources().getColor(R.color.color_000000));
            tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            v.setVisibility(View.VISIBLE);
        }
    }

}
