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
public class LandscapeCreateRoomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    int pos = -1;
    public LandscapeCreateRoomAdapter(@Nullable List<String> data) {
        super(R.layout.item_landscape_create_room, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setBackgroundRes(R.id.ll,0)
                .setVisible(R.id.iv_yy,true);
        if(helper.getLayoutPosition()==pos){
            helper.setBackgroundRes(R.id.ll,R.drawable.shape_yy_ff_all_10)
                    .setVisible(R.id.iv_yy,false);
        }
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
