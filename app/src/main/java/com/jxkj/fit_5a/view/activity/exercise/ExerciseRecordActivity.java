package com.jxkj.fit_5a.view.activity.exercise;

import android.text.Html;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.SportLogBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.jxkj.fit_5a.view.adapter.TwoJlxqAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExerciseRecordActivity extends BaseActivity {
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_jlxq_list)
    RecyclerView mRvJlxqList;
    private TwoJlxqAdapter mTwoJlxqAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_record;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        HomeTopAdapter mHomeTopAdapter = new HomeTopAdapter(list);
        mRvTopList.setLayoutManager(new GridLayoutManager(this,3));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mTwoJlxqAdapter = new TwoJlxqAdapter(null);
        mRvJlxqList.setLayoutManager(new LinearLayoutManager(this));
        mRvJlxqList.setHasFixedSize(true);
        mRvJlxqList.setAdapter(mTwoJlxqAdapter);

        mTwoJlxqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
        getTemplateList();
    }
    private void getTemplateList() {
        RetrofitUtil.getInstance().apiService()
                .geSportLogList(0,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogBean> result) {
                        if(isDataInfoSucceed(result)){
                            mTwoJlxqAdapter.setNewData(result.getData().getList());
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
