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
public class HomeTwoTaskSelect extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeTwoTaskSelect(@Nullable List<String> data) {
        super(R.layout.item_home_two_task_select, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        if(item.equals("-1")){
            helper.setGone(R.id.rl_select_no,false).setGone(R.id.rl_select_yes,true);
        }else{
            helper.setGone(R.id.rl_select_no,true).setGone(R.id.rl_select_yes,false);
        }
    }

}
