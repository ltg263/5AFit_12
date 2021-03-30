package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.view.adapter.MineYhqAdapter;
import com.jxkj.fit_5a.view.adapter.TopicListTAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapFinish1Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private TopicListTAdapter mTopicListTAdapter;

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

        mTopicListTAdapter = new TopicListTAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTopicListTAdapter);

        mTopicListTAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
