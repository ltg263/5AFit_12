package com.jxkj.fit_5a.view.activity.exercise;


import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.SportLogDetailBean;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ExerciseRecordDetailsActivity extends BaseActivity {
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_xl)
    RecyclerView mRvListXl;
    @BindView(R.id.ll_txt)
    LinearLayout mLlTet;
    @BindView(R.id.tv_xz)
    TextView mTvXz;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_5)
    TextView mTv5;
    @BindView(R.id.tv_6)
    TextView mTv6;
    @BindView(R.id.tv_ztime)
    TextView tv_ztime;
    @BindView(R.id.vertical_progressbar1)
    ProgressBar mVerticalProgressbar1;
    @BindView(R.id.vertical_progressbar2)
    ProgressBar mVerticalProgressbar2;
    @BindView(R.id.vertical_progressbar3)
    ProgressBar mVerticalProgressbar3;
    @BindView(R.id.vertical_progressbar4)
    ProgressBar mVerticalProgressbar4;
    @BindView(R.id.vertical_progressbar5)
    ProgressBar mVerticalProgressbar5;
    @BindView(R.id.vertical_progressbar6)
    ProgressBar mVerticalProgressbar6;
    private AAChartModel aaChartModel;
    private ArrayList<BpmDataBean> mBpmDataBeans;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_record_details;
    }

    @Override
    protected void initViews() {
        geSportLogDetails();
    }

    private void geSportLogDetails() {
        RetrofitUtil.getInstance().apiService()
                .geSportLogDetails(getIntent().getStringExtra("id"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogDetailBean> result) {

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
