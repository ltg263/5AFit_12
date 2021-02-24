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
public class HomeDynamicAdapter extends BaseQuickAdapter<QueryPopularBean, BaseViewHolder> {
    public HomeDynamicAdapter(@Nullable List<QueryPopularBean> data) {
        super(R.layout.item_home_dynamic, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularBean item) {

        helper.setText(R.id.tv_name,item.getUser().getNickName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.tv_content,item.getSimpleContent())
                .setGone(R.id.tv_topic,false)
                .setGone(R.id.rv_img_list,false)
                .setGone(R.id.iv_baofang,false)
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

            try {
                JSONArray jsonArray = new JSONArray(item.getMedia());

                if(jsonArray.length()==1){
                    String imageUrl = jsonArray.getJSONObject(0).getString("imageUrl");
                    helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
                            .setGone(R.id.siv_3,false).setGone(R.id.siv_4,true);
                    GlideImageUtils.setGlideImage(mContext,imageUrl,helper.getView(R.id.siv_4));
                    if(item.getContentType().equals("3")){
                        helper.setGone(R.id.iv_baofang,true);
                    }
                }else if(jsonArray.length()==2){
                    String imageUrl1 = jsonArray.getJSONObject(0).getString("imageUrl");
                    String imageUrl2 = jsonArray.getJSONObject(1).getString("imageUrl");
                    helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true)
                            .setVisible(R.id.siv_3,false).setGone(R.id.siv_4,false);
                    GlideImageUtils.setGlideImage(mContext,imageUrl1,helper.getView(R.id.siv_1));
                    GlideImageUtils.setGlideImage(mContext,imageUrl2,helper.getView(R.id.siv_2));
                }else if(jsonArray.length()>2){
                    String imageUrl1 = jsonArray.getJSONObject(0).getString("imageUrl");
                    String imageUrl2 = jsonArray.getJSONObject(1).getString("imageUrl");
                    String imageUrl3 = jsonArray.getJSONObject(2).getString("imageUrl");
                    helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true)
                            .setVisible(R.id.siv_3,true).setGone(R.id.siv_4,false);
                    GlideImageUtils.setGlideImage(mContext,imageUrl1,helper.getView(R.id.siv_1));
                    GlideImageUtils.setGlideImage(mContext,imageUrl2,helper.getView(R.id.siv_2));
                    GlideImageUtils.setGlideImage(mContext,imageUrl3,helper.getView(R.id.siv_3));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}
