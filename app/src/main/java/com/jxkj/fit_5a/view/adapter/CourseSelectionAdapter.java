package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceCourseData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class CourseSelectionAdapter extends BaseQuickAdapter<DeviceCourseData.ListBean, BaseViewHolder> {
    public CourseSelectionAdapter(@Nullable List<DeviceCourseData.ListBean> data) {
        super(R.layout.item_course_selection, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceCourseData.ListBean item) {
        helper.setText(R.id.tv_name,item.getName()).setText(R.id.tv_sub_title,item.getIntroduct());
    }

}
