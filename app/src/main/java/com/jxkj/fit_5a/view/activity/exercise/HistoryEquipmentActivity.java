package com.jxkj.fit_5a.view.activity.exercise;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityManageActivity;
import com.jxkj.fit_5a.view.adapter.HistoryEquipmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HistoryEquipmentActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    List<HistoryEquipmentData> listss;
    private HistoryEquipmentAdapter mHistoryEquipmentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_history_equipment;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("历史设备");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
        mTvRighttext.setText("新增");
        
        List<HistoryEquipmentData> lists = new ArrayList<>();
        HistoryEquipmentData data = new HistoryEquipmentData();
        data.setImg("");
        data.setName("haha");
        data.setState("1");
        data.setTime("123456");
        lists.add(data);
        SharedUtils.singleton().put("HistoryEquipmentData", lists);

        listss = SharedUtils.singleton().get("HistoryEquipmentData", null);

        mHistoryEquipmentAdapter = new HistoryEquipmentAdapter(listss);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHistoryEquipmentAdapter);

        mHistoryEquipmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_righttext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
                FacilityAddSbActivity.intentActivity(this);
                break;
        }
    }
}
