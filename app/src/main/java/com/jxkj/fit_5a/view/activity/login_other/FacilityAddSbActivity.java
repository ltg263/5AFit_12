package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.FacilityManageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FacilityAddSbActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    private FacilityManageAdapter mFacilityManageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_sb;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("新增设备");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRvUi();
    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mFacilityManageAdapter = new FacilityManageAdapter(list);

        mRvAllList.setLayoutManager(new LinearLayoutManager(this));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mFacilityManageAdapter);

        mFacilityManageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(FacilityAddSbActivity.this, FacilityAddPpActivity.class));
            }
        });
    }


    @OnClick({R.id.ll_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_ok:
                break;
        }
    }
}
