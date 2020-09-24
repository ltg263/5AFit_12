package com.jxkj.fit_5a.view.activity.mine.order;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private static final String[] CHANNELS = new String[]{"全部", "待付款", "待发货", "待收货", "待评论"};
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private int type;

    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我的订单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        type = getIntent().getIntExtra("type",0);
        initVP();
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        for(int i=0;i<mDataList.size();i++){
            OrderListFragment fragment = new OrderListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type",type);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });

        mViewPager.setCurrentItem(type);
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }
}
