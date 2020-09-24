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
public class TaskFinishPjAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TaskFinishPjAdapter(@Nullable List<String> data) {
        super(R.layout.item_task_finish_pj, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv,item);

        switch (helper.getLayoutPosition()){
            case 0:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj1);
                break;
            case 1:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj2);
                break;
            case 2:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj3);
                break;
            case 3:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj4);
                break;
        }
    }

}
