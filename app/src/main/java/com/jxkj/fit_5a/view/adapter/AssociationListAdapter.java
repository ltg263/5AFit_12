package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class AssociationListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AssociationListAdapter(@Nullable List<String> data) {
        super(R.layout.item_association_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_head_img);
        Banner mBanner =  helper.getView(R.id.banner);
        initBannerOne(mBanner);
    }

    private void initBannerOne(Banner mBanner) {
        ArrayList<String> list_title = new ArrayList<>();
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list_path.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1096319023,1267316854&fm=26&gp=0.jpg");
//            list_title.add(lists.get(i).getTitle());
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
