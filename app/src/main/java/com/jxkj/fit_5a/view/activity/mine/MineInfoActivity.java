package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.view.AddressPickTask;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;

public class MineInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_info_1)
    TextView mTvInfo1;
    @BindView(R.id.ll_info_1)
    LinearLayout mLlInfo1;
    @BindView(R.id.tv_info_2)
    TextView mTvInfo2;
    @BindView(R.id.ll_info_2)
    LinearLayout mLlInfo2;
    @BindView(R.id.tv_info_3)
    TextView mTvInfo3;
    @BindView(R.id.ll_info_3)
    LinearLayout mLlInfo3;
    @BindView(R.id.tv_info_4)
    TextView mTvInfo4;
    @BindView(R.id.ll_info_4)
    LinearLayout mLlInfo4;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.iv_img)
    RoundImageView mIvImg;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv_info_5)
    TextView mTvInfo5;
    @BindView(R.id.ll_info_5)
    LinearLayout mLlInfo5;
    @BindView(R.id.tv_info_6)
    TextView mTvInfo6;
    @BindView(R.id.ll_info_6)
    LinearLayout mLlInfo6;
    @BindView(R.id.tv_info_7)
    TextView mTvInfo7;
    @BindView(R.id.ll_info_7)
    LinearLayout mLlInfo7;
    @BindView(R.id.tv_info_8)
    TextView mTvInfo8;
    @BindView(R.id.ll_info_8)
    LinearLayout mLlInfo8;
    @BindView(R.id.tv_info_9)
    TextView mTvInfo9;
    @BindView(R.id.ll_info_9)
    LinearLayout mLlInfo9;
    @BindView(R.id.tv_go_find)
    TextView mTvGoFind;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initViews() {
        String imgUrl = "https://profile.csdnimg.cn/3/2/C/3_zerokkqq";
        Glide.with(this).load(imgUrl).into(mIvImg);
    }


    @OnClick({R.id.iv_back, R.id.ll_info_1, R.id.ll_info_2, R.id.ll_info_3, R.id.ll_info_4, R.id.rl, R.id.ll_info_5, R.id.ll_info_6, R.id.ll_info_7, R.id.ll_info_8, R.id.ll_info_9, R.id.tv_go_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl:
                MatisseUtils.gotoSelectPhoto(this, 1, true);
                break;
            case R.id.ll_info_1:
                break;
            case R.id.ll_info_2:
                break;
            case R.id.ll_info_3:
                onAddressPicker();
                break;
            case R.id.ll_info_4:
                MatisseUtils.gotoSelectPhotoBl(this, 1, true);
                break;
            case R.id.ll_info_5:
                break;
            case R.id.ll_info_6:
                break;
            case R.id.ll_info_7:
                break;
            case R.id.ll_info_8:
                break;
            case R.id.ll_info_9:
                break;
            case R.id.tv_go_find:
                break;
        }
    }

    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {

            @Override
            public void onAddressInitFailed() {
                ToastUtils.showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    mTvInfo3.setText(province.getAreaName() + "," + city.getAreaName());

                } else {
                    mTvInfo3.setText(province.getAreaName() + "," + city.getAreaName() + "," + county.getAreaName());
                }
            }
        });
        task.execute("北京", "北京", "北京");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        Glide.with(this).load(selectList.get(0).getCompressPath()).into(mIvImg);
                    }
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> selectListbj = PictureSelector.obtainMultipleResult(data);
                    if (selectListbj.size() > 0) {
                        Glide.with(this).load(selectListbj.get(0).getCompressPath()).into(mIv);
                        Glide.with(this).asBitmap().load(selectListbj.get(0).getCompressPath())
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                        Drawable drawable = new BitmapDrawable(resource);
                                        mRlActionbar.setBackground(drawable);
                                    }

                                });
                    }
                    break;
            }
        }
    }

}
