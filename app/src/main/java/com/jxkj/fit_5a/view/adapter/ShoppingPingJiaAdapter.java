package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CommentListBean;
import com.jxkj.fit_5a.entity.ProductDetailsBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ShoppingPingJiaAdapter extends BaseQuickAdapter<CommentListBean.ListBean, BaseViewHolder> {
    public ShoppingPingJiaAdapter(@Nullable List<CommentListBean.ListBean> data) {
        super(R.layout.item_shopping_pinjia, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentListBean.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getAvatar(),helper.getView(R.id.iv_head_img));
        helper.setText(R.id.tv_nickName,item.getNickName())
                .setText(R.id.tv_content,item.getDetail()).setGone(R.id.rv_img_list,false);

        String[] strArr = item.getImgs().split(",");
        if(strArr!=null && strArr.length>0){
            helper.setGone(R.id.rv_img_list,true);
            if(strArr.length==1){
//                helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
//                        .setGone(R.id.siv_3,false).setGone(R.id.siv_4,true);
//                GlideImageUtils.setGlideImage(mContext,strArr[0],helper.getView(R.id.siv_4));

                helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,false)
                        .setVisible(R.id.siv_3,false).setGone(R.id.siv_4,false);
                GlideImageUtils.setGlideImage(mContext,strArr[0],helper.getView(R.id.siv_1));
            }else if(strArr.length==2){
                helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true)
                        .setVisible(R.id.siv_3,false).setGone(R.id.siv_4,false);
                GlideImageUtils.setGlideImage(mContext,strArr[0],helper.getView(R.id.siv_1));
                GlideImageUtils.setGlideImage(mContext,strArr[1],helper.getView(R.id.siv_2));
            }else if(strArr.length>2){
                helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true)
                        .setVisible(R.id.siv_3,true).setGone(R.id.siv_4,false);
                GlideImageUtils.setGlideImage(mContext,strArr[0],helper.getView(R.id.siv_1));
                GlideImageUtils.setGlideImage(mContext,strArr[1],helper.getView(R.id.siv_2));
                GlideImageUtils.setGlideImage(mContext,strArr[2],helper.getView(R.id.siv_3));
            }
        }
    }

}
