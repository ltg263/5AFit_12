package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.GetConfigReq;
import com.jxkj.fit_5a.conpoment.view.PickerScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CoursePatternActivity extends BaseActivity {
    @BindView(R.id.address)
    PickerScrollView addressSelector;

    @Override
    protected int getContentView() {
        return R.layout.activity_course_pattern;
    }

    @Override
    protected void initViews() {
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"20\",\"state\":\"1\"},{\"ID\":\"1\",\"categoryName\":\"40\",\"state\":\"1\"},{\"ID\":\"2\",\"categoryName\":\"60\",\"state\":\"1\"},{\"ID\":\"  0\",\"categoryName\":\"80\",\"state\":\"1\"},{\"ID\":\"1\",\"categoryName\":\"100\",\"state\":\"1\"}]}";
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        List<GetConfigReq.DatasBean> datasBeanList = null;
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList = getConfigReq.getDatas();
        }
        // 设置数据，默认选择第一条
        addressSelector.setData(datasBeanList);
        addressSelector.setSelected(0);

        //滚动监听
        addressSelector.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(GetConfigReq.DatasBean pickers) {
                Log.w("-->>:", "pos:" + pickers.getCategoryName());
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok:
                startActivity(new Intent(this,CourseStartActivity.class));
                break;
        }
    }
}
