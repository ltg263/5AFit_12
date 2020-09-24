package com.jxkj.fit_5a.view.activity.exercise;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.HomeTwoTaskSelect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskSelectionActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_selection;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("-1");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        HomeTwoTaskSelect mHomeTwoTaskSelect = new HomeTwoTaskSelect(list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeTwoTaskSelect);
        mHomeTwoTaskSelect.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<String> data = mHomeTwoTaskSelect.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.set(i, "");
                }
                data.set(position, "-1");
                mHomeTwoTaskSelect.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_tiao_guo, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tiao_guo:
            case R.id.tv_ok:
                startActivity(new Intent(this, TaskStartActivity.class));
                break;
        }
    }
}
