package com.jxkj.fit_5a.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.ShoppingRmAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShoppingFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_all)
    RecyclerView mRvListAll;
    @BindView(R.id.banner)
    Banner mBanner;
    private ShoppingRmAdapter mShoppingRmAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initViews() {
        initBannerOne();
        initRv();
    }

    private void initBannerOne() {
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


    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mShoppingRmAdapter = new ShoppingRmAdapter(list);
        LinearLayoutManager ms1 = new LinearLayoutManager(getActivity());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms1);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mShoppingRmAdapter);

        mShoppingRmAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });


        HomeShoppingAdapter mHomeShoppingAdapter = new HomeShoppingAdapter(list);
        mRvListAll.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingAdapter);

        mShoppingRmAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

    }

    @Override
    public void initImmersionBar() {

    }
}
