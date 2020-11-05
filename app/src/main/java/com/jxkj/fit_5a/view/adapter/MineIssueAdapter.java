package com.jxkj.fit_5a.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.HelpListData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineIssueAdapter extends BaseQuickAdapter<HelpListData.ListBean, BaseViewHolder> {
    public MineIssueAdapter(@Nullable List<HelpListData.ListBean> data) {
        super(R.layout.item_mine_issue, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HelpListData.ListBean item) {

        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_content,item.getDetail())
                .setText(R.id.tv_num,(getLoadMoreViewPosition()+1)+"");
        TextView tvC = helper.getView(R.id.tv_content);
        helper.getView(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvC.getVisibility()==View.VISIBLE){
                    tvC.setVisibility(View.GONE);
                }else{
                    tvC.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
