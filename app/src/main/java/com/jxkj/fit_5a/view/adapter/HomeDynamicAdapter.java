package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.QueryPopularBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeDynamicAdapter extends BaseQuickAdapter<QueryPopularBean.DataBean, BaseViewHolder> {
    public HomeDynamicAdapter(@Nullable List<QueryPopularBean.DataBean> data) {
        super(R.layout.item_home_dynamic, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularBean.DataBean item) {

        helper.setText(R.id.tv_name,item.getUser().getNickName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.tv_content,item.getSimpleContent())
                .setGone(R.id.tv_topic,false)
                .setGone(R.id.rv_img_list,false)
                .setText(R.id.tv_browse_num,"浏览 "+item.getPageviews()+" 次");

        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_img));

        if(StringUtil.isNotBlank(item.getTopicArr())){
            try {
                JSONArray array = new JSONArray(item.getTopicArr());
                helper.setGone(R.id.tv_topic,true).setText(R.id.tv_topic,array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(StringUtil.isNotBlank(item.getMedia())){
            helper.setGone(R.id.rv_img_list,true);
            String[] strArr = item.getMedia().split(",");

            if(strArr.length==1){
                helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
                        .setGone(R.id.siv_3,false).setGone(R.id.siv_4,true);
                GlideImageUtils.setGlideImage(mContext,strArr[0],helper.getView(R.id.siv_4));
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
