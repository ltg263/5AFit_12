package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.view.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

public class MagicIndicatorUtils {

    /**
     * 基础的下划线
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_1(Context mContext, List<String> mDataList,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        mMagicIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.color_666666));
                simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.color_000000));
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 1));
                indicator.setYOffset(20);
                indicator.setColors(mContext.getResources().getColor(R.color.color_text_theme));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    /**
     * 基础的下划线+动作
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_2(Context mContext, List<String> mDataList,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        mMagicIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.color_666666));
                simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.color_000000));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 1));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(mContext.getResources().getColor(R.color.color_text_theme));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    /**
     * 指标为圆点
     * @param mContext
     * @param circleCount
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_3(Context mContext,int circleCount,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        MagicIndicatorScaleCircleNavigator scaleCircleNavigator = new MagicIndicatorScaleCircleNavigator(mContext);
        scaleCircleNavigator.setCircleCount(3);
        scaleCircleNavigator.setNormalCircleColor(Color.parseColor("#CCCCCC"));
        scaleCircleNavigator.setSelectedCircleColor(Color.parseColor("#000000"));
        scaleCircleNavigator.setCircleClickListener(new MagicIndicatorScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        mMagicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }
}
