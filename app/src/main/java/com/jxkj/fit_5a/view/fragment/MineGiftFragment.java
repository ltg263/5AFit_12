package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.view.adapter.UserLwMineAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwShouAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwSongAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

        Bundle bundle = getArguments();
        int type=0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initRv(type);

    }

    UserLwShouAdapter mUserLwShouAdapter;
    UserLwSongAdapter mUserLwSongAdapter;
    UserLwMineAdapter mUserLwMineAdapter;
    private void initRv(int type) {
        if(type==0){
            mUserLwShouAdapter = new UserLwShouAdapter(null);
            mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            mRvList.setHasFixedSize(true);
            mRvList.setAdapter(mUserLwShouAdapter);
            getUserGiftLogList(false);
        }else if(type ==1){
            mUserLwSongAdapter = new UserLwSongAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRvList.setHasFixedSize(true);
            mRvList.setAdapter(mUserLwSongAdapter);
            getUserGiftLogList(true);
        }else{
            mUserLwMineAdapter = new UserLwMineAdapter(null);
            mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            mRvList.setHasFixedSize(true);
            mRvList.setAdapter(mUserLwMineAdapter);
            getUserGiftList();
        }
    }

    @Override
    public void initImmersionBar() {

    }

    private void getUserGiftList() {
        RetrofitUtil.getInstance().apiService()
                .getUserGiftList(true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GiftListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GiftListData> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData().getList().size()>0){
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                                mUserLwMineAdapter.setNewData(result.getData().getList());
                            }
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
    private void getUserGiftLogList(boolean flag) {
        RetrofitUtil.getInstance().apiService()
                .getUserGiftLogList(flag)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GiftLogListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GiftLogListData> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData().getList().size()>0) {
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                                if (flag) {
                                    mUserLwSongAdapter.setNewData(result.getData().getList());
                                } else {
                                    mUserLwShouAdapter.setNewData(result.getData().getList());
                                }
                            }
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
