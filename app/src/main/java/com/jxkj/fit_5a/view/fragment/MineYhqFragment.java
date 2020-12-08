package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.view.adapter.UserLwMineAdapter;
import com.jxkj.fit_5a.view.adapter.MineYhqAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwSongAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineYhqFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.ll_dh)
    LinearLayout ll_dh;

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine_yhq;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setVisibility(View.VISIBLE);
        mLvNot.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        int type = 0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initRv(type);

    }

    MineYhqAdapter mBaseQuickAdapter;
    private void initRv(int type) {

        if (type == 0) {
            ll_dh.setVisibility(View.VISIBLE);
            mBaseQuickAdapter = new MineYhqAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else if (type == 1) {
            ll_dh.setVisibility(View.GONE);
            mBaseQuickAdapter = new MineYhqAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            ll_dh.setVisibility(View.GONE);
            mBaseQuickAdapter = new MineYhqAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mBaseQuickAdapter);
        getUserPrize(type);
        mBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
    private void getUserPrize(int type) {
        if(type == 0){
            type = 1;
        }else if(type ==1){
            type = 2;
        }else{
            type =3;
        }
        RetrofitUtil.getInstance().apiService()
                .getUserPrizeList(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<PrizeListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<PrizeListData> result) {
                        if(isDataInfoSucceed(result)){
                            mBaseQuickAdapter.setNewData(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
