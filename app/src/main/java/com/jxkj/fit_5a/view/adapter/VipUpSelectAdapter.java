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
 * 7
 * 6
 * 6
 * 6
 * 6
 */
public class VipUpSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public VipUpSelectAdapter(@Nullable List<String> data) {
        super(R.layout.item_view_up_select, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        if(item.equals("-")){
            helper.setGone(R.id.rv_select_yes,true);
            helper.setGone(R.id.rv_select_no,false);
        }else{
            helper.setGone(R.id.rv_select_yes,false);
            helper.setGone(R.id.rv_select_no,true);
        }
    }

}
