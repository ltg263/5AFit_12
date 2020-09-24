package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.view.fragment.InterestAllFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InterestAllActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    AutoHeightViewPager mViewpager;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.tv_right_text)
    TextView mTvRightText;

    @Override
    protected int getContentView() {
        return R.layout.activity_interest_all;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();

        list.add("健身运动1");
        list.add("健身运动2");
        list.add("健身运动3");
        list.add("健身运动4");
        list.add("健身运动5");
        list.add("健身运动6");
        list.add("健身运动7");

        initTabs(list);
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments(List<String> size) {
        for (int i = 0; i < size.size(); i++) {
            InterestAllFragment fragment = new InterestAllFragment();
            fragments.add(fragment);
        }
        return fragments;
    }
    private void initTabs(List<String> lists) {
        List<String> titles = new ArrayList<>();
        for(int i=0;i<lists.size();i++){
            titles.add(lists.get(i));
        }
        final List<Fragment> mFragments = getFragments(lists);
        mViewpager.setOffscreenPageLimit(titles.size());


        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                // 切换到当前页面，重置高度
                mViewpager.requestLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabs.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }
    boolean isY = false;
    @OnClick({R.id.iv_back, R.id.tv_search_topic,R.id.tv_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                if(isY){
                    isY = false;
                    startActivity(new Intent(this,CreateCircleActivity.class));
                    return;
                }
                DialogUtils.showDialogNoCircle(this,  "创建圈子权限", 1, new DialogUtils.DialogLyInterface() {
                    @Override
                    public void btnConfirm() {
                        isY = true;
                    }
                });
                break;
            case R.id.tv_search_topic:
                break;
        }
    }

}
