package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.CircleTaskData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeTwoTaskSelect extends BaseQuickAdapter<CircleTaskData, BaseViewHolder> {
    public HomeTwoTaskSelect(@Nullable List<CircleTaskData> data) {
        super(R.layout.item_home_two_task_select, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleTaskData item) {
        if(item.isSelect()){
            helper.setGone(R.id.rl_select_no,false).setGone(R.id.rl_select_yes,true);
        }else{
            helper.setGone(R.id.rl_select_no,true).setGone(R.id.rl_select_yes,false);
        }
        helper.setText(R.id.tv_title,item.getName()).setText(R.id.tv_title1,item.getName())
                .setText(R.id.tv_day,item.getHasDisplay()).setText(R.id.tv_day1,item.getHasDisplay())
                .setText(R.id.tv_explain,item.getExplain()).setText(R.id.tv_explain1,item.getExplain());
    }

}
