package com.jxkj.fit_5a.view.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.QueryPopularBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeSqAdapter extends BaseQuickAdapter<QueryPopularBean, BaseViewHolder> {
    public HomeThreeSqAdapter(@Nullable List<QueryPopularBean> data) {
        super(R.layout.item_home_three_sq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularBean item) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        if(StringUtil.isNotBlank(item.getMedia())){
            try {
                JSONArray array = new JSONArray(item.getMedia());
                GlideImgLoader.loadImageAndDefault(mContext,array.getJSONObject(0).getString("imageUrl"),helper.getView(R.id.iv_icon));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        helper.setText(R.id.tv_title,item.getSimpleContent()).setText(R.id.tv_name,item.getUser().getNickName())
                .setGone(R.id.iv_baofang,false).setText(R.id.tv_num,item.getLikeCount()+"");
        GlideImgLoader.loadImageViewWithCirclr(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_img));

        if(item.getContentType().equals("3")){
            helper.setGone(R.id.iv_baofang,true);
        }
    }

}
