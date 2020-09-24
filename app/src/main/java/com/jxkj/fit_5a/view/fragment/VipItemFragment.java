package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;

import butterknife.BindView;

public class VipItemFragment extends BaseFragment {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.iv_vip)
    ImageView mIvVip;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_sign)
    TextView mTvSign;
    @BindView(R.id.tv_no)
    TextView mTvNo;
    @BindView(R.id.tv_xf)
    TextView mTvXf;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    private Bundle bundle;
    String type;

    @Override
    protected int getContentView() {
        return R.layout.fragment_vip_item;
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
        if (type.equals("1")) {
            mRl.setBackground(getActivity().getResources().getDrawable(R.drawable.vip_shape_bj_15_1));
            mIvVip.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_vip_1));
            mTvXf.setText("立即续费");
        } else {
            mRl.setBackground(getActivity().getResources().getDrawable(R.drawable.vip_shape_bj_15_2));
            mIvVip.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_vip_2));
            mTvXf.setText("开通会员");
        }
    }

    @Override
    public void initImmersionBar() {

    }
}
