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
public class MineJDczSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MineJDczSelectAdapter(@Nullable List<String> data) {
        super(R.layout.item_view_jdcs_select, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        if(item.equals("-")){
            helper.setVisible(R.id.rv_select_yes,true);
            helper.setVisible(R.id.rv_select_no,false);
        }else{
            helper.setVisible(R.id.rv_select_yes,false);
            helper.setVisible(R.id.rv_select_no,true);
        }
    }

}
