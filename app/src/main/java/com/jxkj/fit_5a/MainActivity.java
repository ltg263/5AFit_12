package com.jxkj.fit_5a;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.OssService;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.SpecListBaen;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.view.fragment.HomeFourFragment;
import com.jxkj.fit_5a.view.fragment.HomeOneFragment;
import com.jxkj.fit_5a.view.fragment.HomeThreeFragment;
import com.jxkj.fit_5a.view.fragment.HomeTwoFragment;

import butterknife.BindView;
import butterknife.OnClick;
import cn.forward.androids.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.iv_main_1)
    ImageView mIvMain1;
    @BindView(R.id.tv_main_1)
    TextView mTvMain1;
    @BindView(R.id.ll_main_1)
    LinearLayout mLlMain1;
    @BindView(R.id.iv_main_2)
    ImageView mIvMain2;
    @BindView(R.id.tv_main_2)
    TextView mTvMain2;
    @BindView(R.id.ll_main_2)
    LinearLayout mLlMain2;
    @BindView(R.id.iv_main_3)
    ImageView mIvMain3;
    @BindView(R.id.tv_main_3)
    TextView mTvMain3;
    @BindView(R.id.ll_main_3)
    LinearLayout mLlMain3;
    @BindView(R.id.iv_main_4)
    ImageView mIvMain4;
    @BindView(R.id.tv_main_4)
    TextView mTvMain4;
    @BindView(R.id.ll_main_4)
    LinearLayout mLlMain4;
    private Fragment mFragments;
    private HomeOneFragment mHomeOneFragment;
    private HomeTwoFragment mHomeTwoFragment;
    private HomeThreeFragment mHomeThreeFragment;
    private HomeFourFragment mHomeFourFragment;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mHomeOneFragment = HomeOneFragment.newInstance();
        mHomeTwoFragment = HomeTwoFragment.newInstance();
        mHomeThreeFragment = HomeThreeFragment.newInstance();
        mHomeFourFragment = HomeFourFragment.newInstance();

        howFragment(1,mIvMain1,mTvMain1,mLlMain1);
        fragmentManager = getSupportFragmentManager();

        mFragments = mHomeOneFragment;
        fragmentManager.beginTransaction().replace(R.id.fl_content, mHomeOneFragment, "A").commitAllowingStateLoss();
        Ble4_0Util.initLsData();
        openLocation();
    }


    @OnClick({R.id.ll_main_1, R.id.ll_main_2, R.id.ll_main_3, R.id.ll_main_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_1:
                howFragment(1,mIvMain1,mTvMain1,mLlMain1);
                switchFragment(mHomeOneFragment,"A");
                break;
            case R.id.ll_main_2:
                howFragment(2,mIvMain2,mTvMain2,mLlMain2);
                switchFragment(mHomeTwoFragment,"B");
                break;
            case R.id.ll_main_3:
                howFragment(3,mIvMain3,mTvMain3,mLlMain3);
                switchFragment(mHomeThreeFragment,"C");
                break;
            case R.id.ll_main_4:
                howFragment(4,mIvMain4,mTvMain4,mLlMain4);
                switchFragment(mHomeFourFragment,"D");
                break;
        }
    }

    private void howFragment(int pos, ImageView iv, TextView tv, LinearLayout ll){
        mIvMain1.setSelected(false);
        mIvMain2.setSelected(false);
        mIvMain3.setSelected(false);
        mIvMain4.setSelected(false);

        mTvMain1.setVisibility(View.INVISIBLE);
        mTvMain2.setVisibility(View.INVISIBLE);
        mTvMain3.setVisibility(View.INVISIBLE);
        mTvMain4.setVisibility(View.INVISIBLE);

        mLlMain1.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
        mLlMain2.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
        mLlMain3.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
        mLlMain4.setBackgroundColor(getResources().getColor(R.color.color_ffffff));

        iv.setSelected(true);
        tv.setVisibility(View.VISIBLE);
        ll.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_99));
    }


    /**
     * 切换Fragment
     * <p>(hide、show、add)
     */
    private void switchFragment(Fragment mCurrentFragment, String tag) {
        if (mFragments != mCurrentFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!mCurrentFragment.isAdded() && null == fragmentManager.findFragmentByTag(tag)) {    // 先判断是否被add过
                fragmentTransaction.hide(mFragments).add(R.id.fl_content, mCurrentFragment, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中, 并添加已显示存在的fangment唯一标示tag
            } else {
                fragmentTransaction.hide(mFragments).show(mCurrentFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
            mFragments = mCurrentFragment;
        }
    }

    //判断GPS是否开启
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    private void openLocation() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            //开始定位
//          getCurrentLocationLatLng();
        }
//        getBoxReceive();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Ble4_0Util.initLsData();
    }

    private void getBoxReceive() {
        RetrofitUtil.getInstance().apiService()
                .getBoxReceive()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
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
