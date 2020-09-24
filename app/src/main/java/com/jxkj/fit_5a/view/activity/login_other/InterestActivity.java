package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InterestActivity extends BaseActivity {
    @BindView(R.id.tv_skip)
    TextView mTvSkip;
    @BindView(R.id.tv_refresh)
    TextView mTvRefresh;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    private ImgAdapter mImgAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_interest;
    }

    @Override
    protected void initViews() {
        initRvUi();
    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mImgAdapter = new ImgAdapter(list);

        mRvAllList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mImgAdapter);

        mImgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }

    @OnClick({R.id.tv_skip, R.id.tv_refresh, R.id.tv_go_xyb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
            case R.id.tv_go_xyb:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_refresh:
                break;
        }
    }
}
