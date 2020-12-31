package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.view.fragment.ShoppingFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    AutoHeightViewPager mViewPager;
    private static final String[] CHANNELS = new String[]{"兑好物", "兑好券"};
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping;
    }

    @Override
    protected void initViews() {
        initVP();

    }

    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments() {
        for (int i = 0; i < mDataList.size(); i++) {
            ShoppingFragment fragment = new ShoppingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("search", "");
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(fragments.size());
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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    initView(mTv1, mView1);
                }else{
                    initView(mTv2, mView2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
        mViewPager.setScrollable(false);
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
    }


    @OnClick({R.id.iv_back, R.id.tv_tiao_guo, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tiao_guo:
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_999999));
        mTv2.setTextColor(getResources().getColor(R.color.color_999999));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }
    public static void intentStartActivity(Context mContext){
        mContext.startActivity(new Intent(mContext, ShoppingActivity.class));
    }

}
