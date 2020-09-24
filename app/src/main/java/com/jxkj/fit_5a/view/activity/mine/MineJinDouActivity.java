package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.MineJinDouAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineJinDouActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineJinDouAdapter mMineJinDouAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_jindou;
    }

    @Override
    protected void initViews() {
        initRv();
    }
    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

        mMineJinDouAdapter = new MineJinDouAdapter(list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineJinDouAdapter);

        mMineJinDouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right_text,R.id.tv_cz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                break;
            case R.id.tv_cz:
                startActivity(new Intent(this,MineJinDouCzActivity.class));
                break;
        }
    }
}
