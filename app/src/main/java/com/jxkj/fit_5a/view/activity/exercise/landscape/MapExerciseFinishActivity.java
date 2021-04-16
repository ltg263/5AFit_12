package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.view.fragment.MapFinish1Fragment;
import com.jxkj.fit_5a.view.fragment.MapFinish2Fragment;
import com.jxkj.fit_5a.view.fragment.MapFinish3Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MapExerciseFinishActivity extends BaseActivity {
    @BindView(R.id.iv_zuo)
    ImageView mIvZuo;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_you)
    ImageView mIvYou;
    private ArrayList<BpmDataBean> mBpmDataBeans;
    private ArrayList<PostUser.SportLogInfo.DetailsBean.LogsBean> logs;
    @Override
    protected int getContentView() {
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_map_exercise_finish;
    }

    @Override
    protected void initViews() {
        mIvZuo.setVisibility(View.INVISIBLE);
        mBpmDataBeans = getIntent().getParcelableArrayListExtra("mBpmDataBeans");
        logs = getIntent().getParcelableArrayListExtra("logs");
        initVP();
    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIvZuo.setVisibility(View.VISIBLE);
                mIvYou.setVisibility(View.VISIBLE);
                if(position==0){
                    mIvZuo.setVisibility(View.INVISIBLE);
                }
                if(position==2){
                    mIvYou.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
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
        MapFinish1Fragment fragment = new MapFinish1Fragment();
        Bundle bundle = new Bundle();
//        bundle.putString("search",search);
        fragment.setArguments(bundle);
        fragments.add(fragment);

        MapFinish2Fragment fragment1 = new MapFinish2Fragment();
        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("mBpmDataBeans",mBpmDataBeans);
        bundle1.putParcelableArrayList("logs",logs);
        fragment1.setArguments(bundle1);
        fragments.add(fragment1);

        MapFinish3Fragment fragment2 = new MapFinish3Fragment();
        Bundle bundle2 = new Bundle();
//        bundle1.putString("search",search);
        fragment2.setArguments(bundle2);
        fragments.add(fragment2);
        return fragments;
    }
    @OnClick({R.id.iv_1, R.id.tv_ok, R.id.iv_zuo, R.id.iv_you})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                finish();
                break;
            case R.id.tv_ok:

                break;
            case R.id.iv_zuo:
                if(mViewPager.getCurrentItem()>0){
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
                }
                break;
            case R.id.iv_you:
                if(mViewPager.getCurrentItem()<3){
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                }
                break;
        }
    }
}
