package com.jxkj.fit_5a.view.activity.exercise;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.jxkj.fit_5a.view.adapter.TwoJlxqAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExerciseRecordActivity extends BaseActivity {
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_jlxq_list)
    RecyclerView mRvJlxqList;
    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_record;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        HomeTopAdapter mHomeTopAdapter = new HomeTopAdapter(list);
        mRvTopList.setLayoutManager(new GridLayoutManager(this,3));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        TwoJlxqAdapter mTwoJlxqAdapter = new TwoJlxqAdapter(list);
        mRvJlxqList.setLayoutManager(new LinearLayoutManager(this));
        mRvJlxqList.setHasFixedSize(true);
        mRvJlxqList.setAdapter(mTwoJlxqAdapter);

        mTwoJlxqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }
}
