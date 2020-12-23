package com.jxkj.fit_5a.view.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchGoodsResultActivity extends BaseActivity {


    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rl_actionbar)
    RelativeLayout llActionbar;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    @BindView(R.id.tabs_tribe)
    TabLayout mTabTribe;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    String[] tabListBlq = {"圈子", "动态"};
    int position = 0;
    private String search;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_goods_result;
    }

    @Override
    protected void initViews() {
        mTabTribe.setTabMode(TabLayout.MODE_FIXED);
        mTabTribe.setTabGravity(TabLayout.GRAVITY_FILL);
        search = getIntent().getStringExtra("search");
        position = getIntent().getIntExtra("position", 0);
        tvTopTitle.setText(search);
        initVP();
    }

    @OnClick({R.id.img_top_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initVP() {
        getFragments();
        mViewpager.setOffscreenPageLimit(tabListBlq.length);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tabListBlq.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabListBlq[position];
            }
        });

        mTabTribe.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(position);
    }
    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        HomeSearchDpListFragment fragment = new HomeSearchDpListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("search",search);
        fragment.setArguments(bundle);
        fragments.add(fragment);

        HomeSearchSpListFragment fragment1 = new HomeSearchSpListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("search",search);
        fragment1.setArguments(bundle1);
        fragments.add(fragment1);
        return fragments;
    }

}
