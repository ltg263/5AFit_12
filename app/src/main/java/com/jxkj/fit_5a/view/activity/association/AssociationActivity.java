package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.AssociationListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AssociationActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_association;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("社 区");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvRighttext.setText("动态");
        mTvRighttext.setTextColor(getResources().getColor(R.color.color_666666));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.ic_bianji));
        initRv();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        AssociationListAdapter mAssociationListAdapter = new AssociationListAdapter(list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mAssociationListAdapter);

        mAssociationListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_head_img:
                        startActivity(new Intent(AssociationActivity.this, UserHomeActivity.class));
                        break;
                }
            }
        });

    }

    @OnClick({R.id.ll_back, R.id.tv_righttext, R.id.iv_rightimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
            case R.id.iv_rightimg:
                startActivity(new Intent(this, AssociationAddActivity.class));
                break;
        }
    }
}
