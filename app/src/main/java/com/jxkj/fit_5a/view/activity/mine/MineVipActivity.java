package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.view.adapter.VipUpSelectAdapter;
import com.jxkj.fit_5a.view.adapter.VipZxtqAdapter;
import com.jxkj.fit_5a.view.fragment.VipItemFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineVipActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list_xf)
    RecyclerView mRvListXf;
    @BindView(R.id.rv_list_b)
    RecyclerView mRvListB;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_vip;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("会员中心");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initVP();
        initRv();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            list.add("");
        }
        VipUpSelectAdapter mVipUpSelectAdapter = new VipUpSelectAdapter(list);
        mRvListXf.setLayoutManager(new GridLayoutManager(this, 3));
        mRvListXf.setHasFixedSize(true);
        mRvListXf.setAdapter(mVipUpSelectAdapter);

        mVipUpSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list.clear();
                for(int i = 0;i<3;i++){
                    list.add("");
                }
                list.set(position,"-");
                mVipUpSelectAdapter.notifyDataSetChanged();
            }
        });


        VipZxtqAdapter mVipZxtqAdapter = new VipZxtqAdapter(list);
        mRvListB.setLayoutManager(new LinearLayoutManager(this));
        mRvListB.setHasFixedSize(true);
        mRvListB.setAdapter(mVipZxtqAdapter);

        mVipZxtqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
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

        mViewPager.setCurrentItem(0);

        MagicIndicatorUtils.initMagicIndicator_3(this, fragments.size(), mMagicIndicator, mViewPager);
    }


    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments() {
        for (int i = 0; i < 3; i++) {
            VipItemFragment fragment = new VipItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", "" + i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

}
