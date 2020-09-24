package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.activity.exercise.CoursePatternActivity;
import com.jxkj.fit_5a.view.adapter.CourseSelectionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseSelectionFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.fragment_course_selection;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        CourseSelectionAdapter mCourseSelectionAdapter = new CourseSelectionAdapter(list);

        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mCourseSelectionAdapter);

        mCourseSelectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), CoursePatternActivity.class));
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
