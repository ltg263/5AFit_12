package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.BpmDataBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class TaskFinishListAdapter extends BaseQuickAdapter<BpmDataBean, BaseViewHolder> {
    public TaskFinishListAdapter(@Nullable List<BpmDataBean> data) {
        super(R.layout.item_task_finish_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BpmDataBean item) {
        helper.setText(R.id.tv1,item.getName()).setText(R.id.tv4,item.getTime()+"s")
                .setText(R.id.tv3,"101%");
    }

}
