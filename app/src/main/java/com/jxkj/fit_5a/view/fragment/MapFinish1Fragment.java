package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.jxkj.fit_5a.view.adapter.MapFinishDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MapFinish1Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MapFinishDataAdapter mMapFinishDataAdapter;

    private ArrayList<BpmDataBean> mBpmDataBeans;
    BpmDataBean.BpmTopData mBpmTopData;
    @Override
    protected int getContentView() {
        return R.layout.fragment_map_finish_1;
    }

    @Override
    protected void initViews() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            mBpmDataBeans = bundle.getParcelableArrayList("mBpmDataBeans");
            if(mBpmDataBeans!=null){
                mBpmTopData = mBpmDataBeans.get(0).getBpmTopData();
            }
        }
        initRv();

    }

    private void initRv() {

        mMapFinishDataAdapter = new MapFinishDataAdapter(null);
        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMapFinishDataAdapter);

        mMapFinishDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        List<String> list = new ArrayList<>();
        list.add("卡路里");
        list.add("运动里程");
        list.add("运动时间");
        list.add("功率");
        list.add("段位");
        list.add("平均速度");
        list.add("最快速度");
        list.add("平均心跳");
        list.add("心率区间");
        list.add("BAI");

        mMapFinishDataAdapter.setNewData(list, mBpmTopData);
    }

    @Override
    public void initImmersionBar() {

    }
}
