package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CommentMomentBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineComment2Adapter extends BaseQuickAdapter<CommentMomentBean, BaseViewHolder> {
    public MineComment2Adapter(@Nullable List<CommentMomentBean> data) {
        super(R.layout.item_mine_comment2, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentMomentBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_11));
        helper.setText(R.id.tv_name_11,item.getUser().getNickName()).setText(R.id.tv_pl_content_11,item.getContent());
    }
}
