package com.jxkj.fit_5a.view.activity.exercise;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.VpRecyclerView;
import com.jxkj.fit_5a.view.adapter.SelectMapAdapter;
import com.jxkj.fit_5a.view.fragment.SelectMapFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseStartActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    VpRecyclerView mRvList;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private SelectMapAdapter mSelectMapAdapter;

    List<String> list = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_course_start;
    }

    @Override
    protected void initViews() {

        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mSelectMapAdapter = new SelectMapAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mSelectMapAdapter);

        initVP();
//        mSelectMapAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                startActivity(new Intent(getActivity(), TaskSelectionActivity.class));
//            }
//        });
    }

    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public float getPageWidth(int position) {
                return (float) 0.8;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });

        mViewPager.setCurrentItem(0);
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        for(int i=0;i<list.size();i++){
            SelectMapFragment fragment = new SelectMapFragment();
            Bundle bundle = new Bundle();
//            bundle.putInt("type",type);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

}
