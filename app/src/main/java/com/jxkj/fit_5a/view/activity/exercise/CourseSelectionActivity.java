package com.jxkj.fit_5a.view.activity.exercise;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.view.fragment.CourseSelectionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseSelectionActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    AutoHeightViewPager mViewpager;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @Override
    protected int getContentView() {
        return R.layout.activity_course_selection;
    }

    @Override
    protected void initViews() {
        List<String> list =new ArrayList<>();
        list.add("基础");
        list.add("中强度");
        list.add("高强度间歇");
        list.add("耐久力");
        list.add("爆发力");
        list.add("爆发力1");
        list.add("爆发力2");

        initTabs(list);

    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments(List<String> size) {
        for (int i = 0; i < size.size(); i++) {
            CourseSelectionFragment fragment = new CourseSelectionFragment();
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

}
