package com.jxkj.fit_5a.view.activity.exercise;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.SelectMapAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseStartActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private SelectMapAdapter mSelectMapAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_course_start;
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

        mSelectMapAdapter = new SelectMapAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mSelectMapAdapter);

        mSelectMapAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(getActivity(), TaskSelectionActivity.class));
            }
        });
    }
}
