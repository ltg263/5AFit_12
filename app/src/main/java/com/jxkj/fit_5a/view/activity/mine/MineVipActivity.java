package com.jxkj.fit_5a.view.activity.mine;

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
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.entity.SpecListBaen;
import com.jxkj.fit_5a.view.adapter.VipUpSelectAdapter;
import com.jxkj.fit_5a.view.adapter.VipZxtqAdapter;
import com.jxkj.fit_5a.view.fragment.VipItemFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.iv_wx)
    ImageView mIvWx;
    @BindView(R.id.iv_zfb)
    ImageView mIvZfb;
    @BindView(R.id.tv_je)
    TextView mTvJe;
    @BindView(R.id.tv_pay)
    TextView mTvPay;

    int payType = 1;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_vip;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("会员中心");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        getSpecList();
        initRv();
    }

    private void getSpecList() {
        RetrofitUtil.getInstance().apiService()
                .getSpecList(null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SpecListBaen>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SpecListBaen> result) {
                        if (isDataInfoSucceed(result)) {
                            initVP(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                list.add("-");
            } else {
                list.add("");
            }
        }
        VipUpSelectAdapter mVipUpSelectAdapter = new VipUpSelectAdapter(list);
        mRvListXf.setLayoutManager(new GridLayoutManager(this, 3));
        mRvListXf.setHasFixedSize(true);
        mRvListXf.setAdapter(mVipUpSelectAdapter);

        mVipUpSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list.clear();
                for (int i = 0; i < 3; i++) {
                    list.add("");
                }
                list.set(position, "-");
                hasAuto = false;
                if(position==0){
                    hasAuto = true;
                }
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
    int levelSpecId = 0;
    boolean hasAuto = true;

    private void initVP(List<SpecListBaen.ListBean> list) {
        getFragments(list);
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
                levelSpecId = list.get(position).getId();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);

        MagicIndicatorUtils.initMagicIndicator_3(this, list.size(), mMagicIndicator, mViewPager);
    }


    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments(List<SpecListBaen.ListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            VipItemFragment fragment = new VipItemFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("ListBean", list.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    @OnClick({R.id.ll_back, R.id.iv_wx, R.id.iv_zfb, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_wx:
                if(payType==2){
                    mIvWx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                    mIvZfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                    payType = 1;
                }
                break;
            case R.id.iv_zfb:
                if(payType==1){
                    mIvWx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                    mIvZfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                    payType = 2;
                }
                break;
            case R.id.tv_pay:
                postCreateLevel();
                break;
        }
    }


    private void postCreateLevel() {
        RetrofitUtil.getInstance().apiService()
                .postCreateLevel(levelSpecId+"",hasAuto)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
