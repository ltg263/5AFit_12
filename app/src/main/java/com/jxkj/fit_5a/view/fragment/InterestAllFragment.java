package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.CircleQueryBean;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.adapter.InterestListAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InterestAllFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private int id=0;
    private InterestListAdapter mInterestListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_interest_all;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id",0);
        }

        mInterestListAdapter = new InterestListAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mInterestListAdapter);

        mInterestListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(getActivity(), MineCircleActivity.class);
                mIntent.putExtra("id",mInterestListAdapter.getData().get(position).getId());
                startActivity(mIntent);
            }
        });
        getCircleQuery();
    }

    private void getCircleQuery() {
        RetrofitUtil.getInstance().apiService()
                .getCircleQuery(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleQueryBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleQueryBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getList()!=null && result.getData().getList().size()>0){
                                mRvList.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                                mInterestListAdapter.setNewData(result.getData().getList());
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


    @Override
    public void initImmersionBar() {

    }
}
