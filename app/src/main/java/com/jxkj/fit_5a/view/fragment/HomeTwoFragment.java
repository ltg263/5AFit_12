package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.ExerciseRecordActivity;
import com.jxkj.fit_5a.view.activity.exercise.HistoryEquipmentActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskStartActivity;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseActivity;
import com.jxkj.fit_5a.view.activity.home.RankListActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
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
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_lianjie)
    TextView tv_lianjie;
    @BindView(R.id.rv_two_list)
    RecyclerView mRvTwoList;
    @BindView(R.id.tv_phb_2)
    TextView mTvPhb2;
    @BindView(R.id.iv_phb_2)
    ImageView mIvPhb2;
    @BindView(R.id.tv_phb_22)
    TextView mTvPhb22;
    @BindView(R.id.tv_phb_1)
    TextView mTvPhb1;
    @BindView(R.id.iv_phb_1)
    ImageView mIvPhb1;
    @BindView(R.id.tv_phb_11)
    TextView mTvPhb11;
    @BindView(R.id.tv_phb_3)
    TextView mTvPhb3;
    @BindView(R.id.iv_phb_3)
    ImageView mIvPhb3;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_phb_33)
    TextView mTvPhb33;
    @BindView(R.id.tv_mingc)
    TextView mTvMingc;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_dll)
    TextView mTvDll;
    @BindView(R.id.tv_zan)
    TextView mTvZan;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
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


        mRlActionbar.setAlpha(1);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 500) {
                    mRlActionbar.setAlpha(1.0f - (float) scrollY / 500.0f);
                } else {
                    mRlActionbar.setAlpha(0);
                }
            }
        });

        mHomeTwoTopAdapter = new HomeTwoTopAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(ms);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTwoTopAdapter);

        mHomeTwoTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).equals("在线运动")) {
                    ToastUtils.showShort("在线运动暂未开放");
//                    MotorPatternActivity.startIntentActivity(getActivity());
                } else {
                    IntentUtils.getInstence().
                            intent(getActivity(), TaskSelectionActivity.class, "exercise_type", list.get(position));
                }
            }
        });
        mHomeTwoTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).equals("在线运动")) {
                    ToastUtils.showShort("在线运动暂未开放");
//                    MotorPatternActivity.startIntentActivity(getActivity());
                } else {
                    if (tv_lianjie.getText().toString().equals("暂未连接设备")) {
                        ToastUtils.showShort("请先链接运动设备");
                        return;
                    }
                    if (view.getId() == R.id.tv_go_1) {
                        goStartMap();
                    } else if (view.getId() == R.id.tv_go_2) {
                        startActivity(new Intent(getActivity(), TaskStartActivity.class));
                    }

                }
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
                        , typeD, mHomeTwoBelowAdapter.getData().get(position).isLike());
            }
        });
        getRankList(3);
    }

    private void goStartMap() {
        RetrofitUtil.getInstance().apiService()
                .getMapRandomDetails(ConstValues_Ly.DEVICE_TYPE_ID_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null) {
                            MapExerciseActivity.intentStartActivity(getActivity(), null);
                        } else {
                            DialogUtils.showDialogHint(getActivity(), result.getMesg(), true,null);
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
    public void onResume() {
        super.onResume();
        tv_lianjie.setText("暂未连接设备");
        if (StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
            tv_lianjie.setText(PopupWindowLanYan.BleName);
        }
    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeTwoFragment newInstance() {
        HomeTwoFragment homeFragment = new HomeTwoFragment();
        return homeFragment;
    }


    @OnClick({R.id.rl_sbgl, R.id.tv_two_ri, R.id.tv_two_zhou, R.id.tv_two_yue, R.id.tv_go_find, R.id.tv_ydjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_sbgl:
                List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
                if (lists != null && lists.size() > 0) {
                    IntentUtils.getInstence().intent(getActivity(), HistoryEquipmentActivity.class);
                    return;
                }
                FacilityAddSbActivity.intentActivity(getActivity());
                break;
            case R.id.tv_two_ri:
                getRankList(1);
                break;
            case R.id.tv_two_zhou:
                getRankList(2);
                break;
            case R.id.tv_two_yue:
                getRankList(3);
                break;
            case R.id.tv_go_find:
                startActivity(new Intent(getActivity(), RankListActivity.class));
                break;
            case R.id.tv_ydjl:
                startActivity(new Intent(getActivity(), ExerciseRecordActivity.class));
                break;
        }


//        if(tv_lianjie.getText().toString().equals("暂未连接设备")){
//            ToastUtils.showShort("请先链接运动设备");
//            return;
//        }
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
                        if (isDataInfoSucceed(result)) {
                            getRankStatsList(type);
                            List<RankListData.ListBean> list = result.getData().getList();
                            if (list != null && list.size() > 0) {
                                getRankDetails(list.get(0).getId());
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

    private void getRankDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getRankDetails(Integer.valueOf(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankDetailsData> result) {
                        if (isDataInfoSucceed(result)) {
                            List<RankDetailsData.RankRewardsBean> rankRewards = result.getData().getRankRewards();
                            if (rankRewards != null && rankRewards.size() > 0) {
                                for (int i = 0; i < rankRewards.size(); i++) {
                                    RankDetailsData.RankRewardsBean rankReward = rankRewards.get(i);
                                    switch (i) {
                                        case 0:
                                            mTvPhb1.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb1.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(), rankReward.getImgUrl(), mIvPhb1);
                                            mTvPhb11.setText(rankReward.getName());
                                            break;
                                        case 1:
                                            mTvPhb2.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb2.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(), rankReward.getImgUrl(), mIvPhb2);
                                            mTvPhb22.setText(rankReward.getName());
                                            break;
                                        case 2:
                                            mTvPhb3.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb3.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(), rankReward.getImgUrl(), mIvPhb3);
                                            mTvPhb33.setText(rankReward.getName());
                                            break;

                                    }
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


    private void getRankStatsList(int type) {
        typeD = type;
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
                        if (isDataInfoSucceed(result)) {
                            RankStatsData.UserBean userData = result.getData().getUser();
                            mTvName.setText(userData.getNickName());
                            mTvZan.setText(result.getData().getLikeCount());
                            mTvDll.setText(result.getData().getCalories() + "kcal");
                            mTvMingc.setText("未上榜");
                            if (result.getData().getRanking() != 0) {
                                mTvMingc.setText("No." + result.getData().getRanking());
                            }
//                            Glide.with(getActivity()).load(R.drawable.icon_zan_no).into((ImageView) helper.getView(R.id.iv_3));
//                            if(result.getData().isLike()){
//                                Glide.with(mContext).load(R.drawable.icon_zan_yes).into((ImageView) helper.getView(R.id.iv_3));
//                            }
                            GlideImageUtils.setGlideImage(getActivity(), userData.getAvatar(), iv_head);
                            mTvTwoYue.setBackgroundColor(0);
                            mTvTwoZhou.setBackgroundColor(0);
                            mTvTwoRi.setBackgroundColor(0);
                            if (typeD == 3) {
                                mTvTwoYue.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_2));
                            }
                            if (typeD == 2) {
                                mTvTwoZhou.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_2));
                            }
                            if (typeD == 1) {
                                mTvTwoRi.setBackground(getResources().getDrawable(R.drawable.btn_shape_bj_theme_2));
                            }
                            mHomeTwoBelowAdapter.setNewData(result.getData().getCaloriesRankingList());
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


    private void getStatsZan(String calRankId, int dimension, boolean hasZan) {
        if (!hasZan) {
            RetrofitUtil.getInstance().apiService()
                    .getStatsZan(calRankId, dimension)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (isDataInfoSucceed(result)) {
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
        } else {
            RetrofitUtil.getInstance().apiService()
                    .getCancelStatsZan(calRankId, dimension)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (isDataInfoSucceed(result)) {
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
}



