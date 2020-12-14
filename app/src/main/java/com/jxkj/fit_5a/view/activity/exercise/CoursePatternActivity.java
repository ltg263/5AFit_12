package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Intent;
import android.view.View;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class CoursePatternActivity extends BaseActivity {


    List<String> list = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_course_pattern;
    }

    @Override
    protected void initViews() {
        //模拟请求后台返回数据
        initData();

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            String itemData = "" + i;
            list.add(itemData);
        }

        WheelView wheelView =  findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView.setSkin(WheelView.Skin.None); // common皮肤
        wheelView.setWheelData(list);  // 数据集合
        wheelView.setSelection(1);
        wheelView.setWheelSize(3);
        wheelView.setExtraText("min",getResources().getColor(R.color.color_999999),30,150);

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        style.selectedTextZoom = 2;
        wheelView.setStyle(style);

    }

    @OnClick({R.id.iv_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok:
//                startActivity(new Intent(this, CourseStartActivity.class));
                break;
        }
    }
}
