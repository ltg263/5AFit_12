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
public class HomeTwoTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeTwoTopAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_two_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.tv_go_1).addOnClickListener(R.id.tv_go_2);
        if(item.equals("在线运动")){
            helper.setText(R.id.tv_title,item).setText(R.id.tv_title_f,"Online Sports");
        }else{
            helper.setText(R.id.tv_title,item).setText(R.id.tv_title_f,"Classic Sports");
        }

    }

}
