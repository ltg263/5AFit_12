package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.adapter.InterestListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InterestAllFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.fragment_interest_all;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("123456");
        list.add("123456");
        list.add("123456");
        list.add("123456");
        InterestListAdapter mInterestListAdapter = new InterestListAdapter(list);

        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mInterestListAdapter);

        mInterestListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(getActivity(), ScrollingActivity.class));
                Intent mIntent = new Intent(getActivity(), MineCircleActivity.class);
                mIntent.putExtra("type","未加入");
                startActivity(mIntent);
            }
        });

        //
    }

    @Override
    public void initImmersionBar() {

    }
}
