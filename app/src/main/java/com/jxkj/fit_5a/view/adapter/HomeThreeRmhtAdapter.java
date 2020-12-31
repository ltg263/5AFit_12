package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.view.PileAvertView;
import com.jxkj.fit_5a.entity.HotTopicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeRmhtAdapter extends BaseQuickAdapter<HotTopicBean, BaseViewHolder> {
    public HomeThreeRmhtAdapter(@Nullable List<HotTopicBean> data) {
        super(R.layout.item_home_three_rmht, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotTopicBean item) {
        PileAvertView pile_view_1 = helper.getView(R.id.pile_view_1);
        List<String> urls=new ArrayList<>();
        urls.clear();
        for(int i=0;i<item.getUser().size();i++){
            urls.add(item.getUser().get(i).getAvatar());
        }
        pile_view_1.setAvertImages(urls);

        GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv));
        helper.setText(R.id.tv_name,"# "+item.getName()).setText(R.id.tv_state,item.getIntroduction());


    }

}
