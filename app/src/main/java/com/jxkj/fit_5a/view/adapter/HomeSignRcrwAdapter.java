package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.TaskListBase;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeSignRcrwAdapter extends BaseQuickAdapter<TaskListBase.ListBean, BaseViewHolder> {
    public HomeSignRcrwAdapter(@Nullable List<TaskListBase.ListBean> data) {
        super(R.layout.item_home_sign_rcrw, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TaskListBase.ListBean item) {
        helper.setText(R.id.tv_num,item.getCurCount()+"/"+item.getCount())
                .setText(R.id.tv_title,item.getName());
        helper.setProgress(R.id.pb_progressbar,item.getCurCount(),item.getCount());
        if(item.getStatus()==1){
            helper.setText(R.id.tv_go_sign,"未完成");
            helper.addOnClickListener(R.id.tv_go_sign);
        }else if(item.getStatus()==2){
            helper.setText(R.id.tv_go_sign,"已完成");
        }else if(item.getStatus()==3){
            helper.setText(R.id.tv_go_sign,"已过期");
        }
    }

}
