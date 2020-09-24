package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.adapter.UserLwMineAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwShouAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwSongAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineGiftFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine_gift;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setVisibility(View.VISIBLE);
        mLvNot.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        int type=0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initRv(type);

    }

    private void initRv(int type) {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
            list.add("");
        }

        BaseQuickAdapter mBaseQuickAdapter;
        if(type==0){
            mBaseQuickAdapter = new UserLwShouAdapter(list);
            mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }else if(type ==1){
            mBaseQuickAdapter = new UserLwSongAdapter(list);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        }else{
            mBaseQuickAdapter = new UserLwMineAdapter(list);
            mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mBaseQuickAdapter);

        mBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
