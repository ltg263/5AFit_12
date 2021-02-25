package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
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
public class CircleDynamicAdapter extends BaseQuickAdapter<QueryPopularBean, BaseViewHolder> {
    public CircleDynamicAdapter(@Nullable List<QueryPopularBean> data) {
        super(R.layout.item_circle_dynamic, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularBean item) {

        helper.setText(R.id.tv_name,item.getUser().getNickName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.tv_content,item.getSimpleContent())
                .setGone(R.id.rv_img_list,false)
                .setText(R.id.tv_xihuan,item.getLikeCount()+"")
                .setText(R.id.tv_liuyan,item.getCommentCount()+"")
                .setText(R.id.tv_browse_num,"浏览 "+item.getPageviews()+" 次");

        helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
                .setGone(R.id.siv_3,false).setGone(R.id.siv_4,false);

        if(item.getUser().getRelation()==4){
            helper.setVisible(R.id.tv_wgz,false).setVisible(R.id.tv_ygz,false);
        }else if(item.getUser().getRelation()==1||item.getUser().getRelation()==3){
            helper.setVisible(R.id.tv_wgz,false).setVisible(R.id.tv_ygz,true);
        }else{
            helper.setVisible(R.id.tv_wgz,true).setVisible(R.id.tv_ygz,false);
        }

        if(item.isIsLike()){
            helper.setImageDrawable(R.id.iv_xihuan,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }else{
            helper.setImageDrawable(R.id.iv_xihuan,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
        }
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_img));

        if(StringUtil.isNotBlank(item.getMedia())){
            helper.setGone(R.id.rv_img_list,true);
            try {
                //{
                //        "imageUrl":"http://5a-fit-oss.nbqichen.com/video/cover/4450DBEBB7FD72852C8BAE1C5C8DBB99.jpg",
                //        "type":"3",
                //        "vedioId":"2900752ca76047ed8e3f195f5d8d7325",
                //        "vedioDuration":1.8016666173934937
                //    }
                JSONArray jsonArray = new JSONArray(item.getMedia());

                if(jsonArray.length()==1){
                    String imageUrl = jsonArray.getJSONObject(0).getString("imageUrl");
                    helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
                            .setGone(R.id.siv_3,false).setGone(R.id.siv_4,true);
                    GlideImageUtils.setGlideImage(mContext,imageUrl,helper.getView(R.id.siv_4));
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
