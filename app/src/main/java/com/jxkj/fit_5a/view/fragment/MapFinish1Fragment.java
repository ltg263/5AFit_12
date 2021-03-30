package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MapFinish1Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private HomeTopAdapter mHomeTopAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_map_finish_1;
    }

    @Override
    protected void initViews() {

        Bundle bundle = getArguments();
        int type = 0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initRv();

    }

    private void initRv() {

        mHomeTopAdapter = new HomeTopAdapter(null);
        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        SportLogStatsBean.StatsBean stats = new SportLogStatsBean.StatsBean();
        stats.setAvgCalories(123);
        stats.setAvgDistance(123);
        stats.setAvgDuration(123);
        stats.setBai(123);
        stats.setDatestr("1234");
        stats.setTotalCalories(123);
        stats.setTotalDuration(123);
        List<String> list = new ArrayList<>();
        list.add("总卡路里");
        list.add("总里程");
        list.add("总计时间");
        list.add("平均时间");
        list.add("BAI");
        mHomeTopAdapter.setNewData(list, stats);
    }

    @Override
    public void initImmersionBar() {

    }
}
