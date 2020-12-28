package com.jxkj.fit_5a.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.entity.CommentMomentBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineCommentAdapter extends BaseQuickAdapter<CommentMomentBean, BaseViewHolder> {

    public MineCommentAdapter(@Nullable List<CommentMomentBean> data) {
        super(R.layout.item_mine_comment, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentMomentBean item) {
        helper.addOnClickListener(R.id.ll_xh);
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_1));
        helper.setText(R.id.tv_name_1,item.getUser().getNickName())
                .setText(R.id.tv_pl_content_1,item.getContent())
                .setGone(R.id.tv_num,false)
                .setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d))
                .setText(R.id.tv_xh_s,item.getLikeCount()+"");
        if(item.isIsLike()){
            helper.setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }

        RecyclerView RvList = helper.getView(R.id.rv_list);
        MineComment2Adapter mMineComment2Adapter = new MineComment2Adapter(null);
        RvList.setLayoutManager(new LinearLayoutManager(mContext));
        RvList.setHasFixedSize(true);
        RvList.setAdapter(mMineComment2Adapter);


        if(item.getCommentCount()>0){
            if(item.getCommentCount()>1){
                helper.setGone(R.id.tv_num,true).setText(R.id.tv_num,"展开 "+item.getCommentCount()+" 条回复");
            }
            HttpRequestUtils.getCommentQueryReply(item.getCommentId()+"", item.getMomentId()+"", item.getMomentPublisherId()+"", 1, 1, new HttpRequestUtils.ResultInterface() {
                @Override
                public void succeed(ResultList<CommentMomentBean> result) {
                    if(result!=null && result.getCode()==0 && result.getData().size()>0){
                        mMineComment2Adapter.setNewData(result.getData());
                    }
                }
            });
        }

        helper.getView(R.id.tv_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequestUtils.getCommentQueryReply(item.getCommentId()+"", item.getMomentId()+"", item.getMomentPublisherId()+"", 1, 100, new HttpRequestUtils.ResultInterface() {
                    @Override
                    public void succeed(ResultList<CommentMomentBean> result) {
                        if(result!=null && result.getCode()==0){
                            mMineComment2Adapter.setNewData(result.getData());
                            helper.setGone(R.id.tv_num,false);
                        }
                    }
                });
            }
        });

    }
}
