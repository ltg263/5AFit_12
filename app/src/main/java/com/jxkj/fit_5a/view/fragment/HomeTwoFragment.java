package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.view.activity.home.RankListActivity;
import com.jxkj.fit_5a.view.activity.exercise.ExerciseRecordActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityManageActivity;
import com.jxkj.fit_5a.view.adapter.HomeTwoBelowAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTwoTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeTwoFragment extends BaseFragment {

    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.tv_two_ri)
    TextView mTvTwoRi;
    @BindView(R.id.tv_two_zhou)
    TextView mTvTwoZhou;
    @BindView(R.id.tv_two_yue)
    TextView mTvTwoYue;
    @BindView(R.id.rv_two_list)
    RecyclerView mRvTwoList;
    private HomeTwoTopAdapter mHomeTwoTopAdapter;
    private HomeTwoBelowAdapter mHomeTwoBelowAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_two;
    }

    @Override
    protected void initViews() {
        initRvUi();
    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("在线运动");
        list.add("经典运动");

        mHomeTwoTopAdapter = new HomeTwoTopAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(ms);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTwoTopAdapter);

        mHomeTwoTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().
                        intent(getActivity(), TaskSelectionActivity.class,"exercise_type",list.get(position));
            }
        });
        mHomeTwoBelowAdapter = new HomeTwoBelowAdapter(null);
        mRvTwoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTwoList.setHasFixedSize(true);
        mRvTwoList.setAdapter(mHomeTwoBelowAdapter);

        mHomeTwoBelowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                getStatsZan(mHomeTwoBelowAdapter.getData().get(position).getId()
                        ,!mHomeTwoBelowAdapter.getData().get(position).isHasZan());
            }
        });
        getRankList(1);
    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeTwoFragment newInstance() {
        HomeTwoFragment homeFragment = new HomeTwoFragment();
        return homeFragment;
    }


    @OnClick({R.id.rl_sbgl,R.id.tv_two_ri, R.id.tv_two_zhou, R.id.tv_two_yue,R.id.tv_go_find,R.id.tv_ydjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_sbgl:
                FacilityManageActivity.intentActivity(getActivity());
                break;
            case R.id.tv_two_ri:
                getRankList(3);
                break;
            case R.id.tv_two_zhou:
                getRankList(2);
                break;
            case R.id.tv_two_yue:
                getRankList(1);
                break;
            case R.id.tv_go_find:
                startActivity(new Intent(getActivity(), RankListActivity.class));
                break;
            case R.id.tv_ydjl:

                startActivity(new Intent(getActivity(), ExerciseRecordActivity.class));
                break;
        }
    }
    int typeD = 0;

    private void getRankList(int type) {
        RetrofitUtil.getInstance().apiService()
                .getRankList(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankListData> result) {
                        if(isDataInfoSucceed(result)){
                            typeD = type;

                            getRankStatsList(type);
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



    private void getRankStatsList(int type) {
        RetrofitUtil.getInstance().apiService()
                .getRankStatsList(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankStatsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankStatsData> result) {
                        if(isDataInfoSucceed(result)){
                            typeD = type;
                            mHomeTwoBelowAdapter.setNewData(result.getData().getList());
                            mTvTwoYue.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                            mTvTwoZhou.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                            mTvTwoRi.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                            if(type==1){
                                mTvTwoYue.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_2));
                            }
                            if(type==2){
                                mTvTwoZhou.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_2));
                            }
                            if(type==3){
                                mTvTwoRi.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_2));
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


    private void getStatsZan(int id,boolean hasZan) {
        RetrofitUtil.getInstance().apiService()
                .getStatsZan(id,hasZan)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            getRankStatsList(typeD);
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



