package com.jxkj.fit_5a.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class AssociationListAdapter extends BaseQuickAdapter<MomentDetailsBean, BaseViewHolder> {
    public AssociationListAdapter(@Nullable List<MomentDetailsBean> data) {
        super(R.layout.item_association_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MomentDetailsBean item) {
        helper.addOnClickListener(R.id.iv_head_img).addOnClickListener(R.id.rl_all_comment)
                .addOnClickListener(R.id.ll_xihuan).addOnClickListener(R.id.ll_shoucang)
                .addOnClickListener(R.id.tv_fenxiang).addOnClickListener(R.id.tv_ygz)
                .addOnClickListener(R.id.tv_wgz).addOnClickListener(R.id.tv_liuyan);
        helper.setText(R.id.tv_name,item.getUser().getNickName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setVisible(R.id.tv_topic,false)
                .setText(R.id.banner_home_one,item.getContent()+"")
                .setText(R.id.tv_shoucang,item.getFavoriteCount()+"")
                .setText(R.id.tv_xihuan,item.getLikeCount()+"")
                .setText(R.id.tv_liuyan,item.getCommentCount()+"")
                .setText(R.id.tv_fenxiang,"0")
                .setImageDrawable(R.id.iv_xihuan,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d))
                .setImageDrawable(R.id.iv_shoucang,mContext.getResources().getDrawable(R.drawable.icon_share_sc_d));
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_img));

        if(item.isIsLike()){
            helper.setImageDrawable(R.id.iv_xihuan,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }

        if(item.isIsFavorite()){
            helper.setImageDrawable(R.id.iv_shoucang,mContext.getResources().getDrawable(R.drawable.icon_share_sc_dx));
        }
        //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
        if(item.getUser().getRelation()==1 || item.getUser().getRelation()==3){
            helper.setVisible(R.id.tv_ygz,true).setVisible(R.id.tv_wgz,false);
        }else if(item.getUser().getRelation()==4){
            helper.setVisible(R.id.tv_ygz,false).setVisible(R.id.tv_wgz,false);
        }else{
            helper.setVisible(R.id.tv_ygz,false).setVisible(R.id.tv_wgz,true);
        }
        if(StringUtil.isNotBlank(item.getTopicArr())){
            try {
                JSONArray array = new JSONArray(item.getTopicArr());
                helper.setVisible(R.id.tv_topic,true).setText(R.id.tv_topic,"来自话题："+array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Banner mBanner =  helper.getView(R.id.banner);
        mBanner.setVisibility(View.GONE);
        if(StringUtil.isNotBlank(item.getMedia())){
            mBanner.setVisibility(View.VISIBLE);
            initBannerOne(mBanner,item.getMedia().split(","));
        }
    }

    private void initBannerOne(Banner mBanner, String[] split) {
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list_path.add(split[i]);
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "你点了第" + position + "张轮播图:" + lists.get(position).getId());
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(list_path);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

}
