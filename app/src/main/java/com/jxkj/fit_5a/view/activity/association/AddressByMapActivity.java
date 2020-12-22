package com.jxkj.fit_5a.view.activity.association;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import butterknife.BindView;

public class AddressByMapActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.mTvSelectedCity)
    TextView mMTvSelectedCity;
    @BindView(R.id.mEtJiedaoName)
    EditText mMEtJiedaoName;
    @BindView(R.id.mMap)
    MapView mMMap;
    @BindView(R.id.mLvResult)
    ListView mMLvResult;
    @BindView(R.id.mLlMap)
    LinearLayout mMLlMap;
    @BindView(R.id.mLvSearch)
    ListView mMLvSearch;
    @BindView(R.id.mLlSearch)
    LinearLayout mMLlSearch;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;

    @Override
    protected int getContentView() {
        return R.layout.activity_select_address_by_map;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("选择地址");
        mTvRighttext.setText("完成");

        mTvRighttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMLlMap.getVisibility()==View.GONE) {
                    mMLlMap.setVisibility(View.VISIBLE);
                    mMLvSearch.setVisibility(View.GONE);
                } else {
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
            }
        });


        mMEtJiedaoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMLlMap.getVisibility()==View.VISIBLE) {
                    mMLlMap.setVisibility(View.GONE);
                    mMLlSearch.setVisibility(View.VISIBLE);
                }
            }
        });
        mMTvSelectedCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此处打开城市列表页面
            }
        });
    }


}
