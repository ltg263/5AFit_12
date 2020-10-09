package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.StepArcView;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.adapter.RatePatternAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RatePatternActivity extends BaseActivity {


    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.sv)
    StepArcView mSv;
    @BindView(R.id.ll_xl)
    ImageView mLlXl;
    @BindView(R.id.ll_zh)
    ImageView mLlZh;
    @BindView(R.id.ll_qd)
    LinearLayout mLlQd;
    @BindView(R.id.tv_top_xl)
    TextView mTvTopXl;
    @BindView(R.id.tv_top_qd)
    TextView mTvTopQd;
    @BindView(R.id.tv_top_zh)
    TextView mTvTopZh;

    @Override
    protected int getContentView() {
        return R.layout.activity_rate_pattern;
    }

    @Override
    protected void initViews() {
        mSv.setCurrentCount(100, 80);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        RatePatternAdapter mRatePatternAdapter = new RatePatternAdapter(list);
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mRatePatternAdapter);

        mRatePatternAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

    }


    @OnClick({R.id.iv_back, R.id.view,R.id.tv_top_xl, R.id.tv_top_qd, R.id.tv_top_zh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.view:
                DialogUtils.showDialogStartYd(this, new DialogUtils.DialogInterfaceS() {
                    @Override
                    public void btnType(int pos) {
                        if (pos == 2) {
                            startActivity(new Intent(RatePatternActivity.this, TaskFinishActivity.class));
                        }else{
                            MotorPatternActivity.startIntentActivity(RatePatternActivity.this);
                        }
                    }
                });
                break;
            case R.id.tv_top_xl:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_text_theme));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_666666));
                mLlXl.setVisibility(View.VISIBLE);
                mLlZh.setVisibility(View.INVISIBLE);
                mLlQd.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_top_qd:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_text_theme));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_666666));
                mLlXl.setVisibility(View.INVISIBLE);
                mLlZh.setVisibility(View.INVISIBLE);
                mLlQd.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_top_zh:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_text_theme));
                mLlXl.setVisibility(View.INVISIBLE);
                mLlZh.setVisibility(View.VISIBLE);
                mLlQd.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
