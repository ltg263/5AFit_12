package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.FollowFansList;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserGzAdapter extends BaseQuickAdapter<FollowFansList.DataBean, BaseViewHolder> {
    public UserGzAdapter(@Nullable List<FollowFansList.DataBean> data) {
        super(R.layout.item_user_gz, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FollowFansList.DataBean item) {
        FollowFansList.DataBean.UserBean user = item.getUser();
        if(user==null){
            return;
        }
        //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
        if(user.getRelation()==1 || user.getRelation()==3){
            helper.setVisible(R.id.tv_ygz,true).setVisible(R.id.tv_wgz,false);
        }else if(user.getRelation()==4){
            helper.setVisible(R.id.tv_ygz,false).setVisible(R.id.tv_wgz,false);
        }else{
            helper.setVisible(R.id.tv_ygz,false).setVisible(R.id.tv_wgz,true);
        }
        GlideImageUtils.setGlideImage(mContext,user.getAvatar(),helper.getView(R.id.iv_head_img));
        helper.setText(R.id.tv_user_name,user.getNickName()).setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd"));
        if(user.getGender()==1){
            Glide.with(mContext).load(R.drawable.icon_xb_nan).into((ImageView) helper.getView(R.id.iv_xb));
        }else{
            Glide.with(mContext).load(R.drawable.icon_xb_nv).into((ImageView) helper.getView(R.id.iv_xb));
        }
        helper.addOnClickListener(R.id.tv_ygz).addOnClickListener(R.id.tv_wgz);
    }

}
