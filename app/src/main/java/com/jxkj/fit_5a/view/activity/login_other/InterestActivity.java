package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.InterestLists;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.view.activity.mine.MineInfoActivity;
import com.jxkj.fit_5a.view.adapter.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InterestActivity extends BaseActivity {
    @BindView(R.id.tv_skip)
    TextView mTvSkip;
    @BindView(R.id.tv_refresh)
    TextView mTvRefresh;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    private ImgAdapter mImgAdapter;
    String sbType,csrq,sg,tz,interest;

    @Override
    protected int getContentView() {
        return R.layout.activity_interest;
    }

    @Override
    protected void initViews() {
        sbType = getIntent().getStringExtra("sbType");
        csrq = getIntent().getStringExtra("csrq");
        sg = getIntent().getStringExtra("sg");
        tz = getIntent().getStringExtra("tz");
        getInterestList();
    }

    private void getInterestList() {
        RetrofitUtil.getInstance().apiService()
                .getInterestList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<InterestLists>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<InterestLists> result) {
                        if(isDataInfoSucceed(result)){
                            initRvUi(result.getData().getList());
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
    private void initRvUi(List<InterestLists.ListBean> list) {
        if(list.size()>0){
            list.get(0).setSelect(true);
        }
        mImgAdapter = new ImgAdapter(list);

        mRvAllList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mImgAdapter);
        mImgAdapter.setOnItemClickListener((adapter, view, position) -> {
            int num = 0;
            for(int i=0;i<mImgAdapter.getData().size();i++){
                if(mImgAdapter.getData().get(i).isSelect()){
                    num++;
                }
            }
            if(num>=3 && !mImgAdapter.getData().get(position).isSelect()){
                ToastUtils.showShort("最多能选择三个兴趣");
                return;
            }
            if(mImgAdapter.getData().get(position).isSelect()){
                mImgAdapter.getData().get(position).setSelect(false);
            }else{
                mImgAdapter.getData().get(position).setSelect(true);
            }
            mImgAdapter.notifyDataSetChanged();
        });
    }

    @OnClick({R.id.tv_skip, R.id.tv_refresh, R.id.tv_go_xyb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_xyb:
                List<InterestLists.ListBean> data = mImgAdapter.getData();
                interest = "";
                for(int i=0;i<data.size();i++){
                    if (data.get(i).isSelect()){
                        interest+=data.get(i).getId()+",";
                    }
                }
                postUserUpdate();
                break;
            case R.id.tv_skip:
                interest = null;
                postUserUpdate();
                break;
            case R.id.tv_refresh:
                getInterestList();
                break;
        }
    }

    private void postUserUpdate() {
        PostUser.UserInfoUpdate userInfoUpdate = new PostUser.UserInfoUpdate();
        userInfoUpdate.setHeight(sg);
        userInfoUpdate.setWeight(tz);
        userInfoUpdate.setGender(sbType);
        userInfoUpdate.setBirthDay(csrq);
        userInfoUpdate.setInterest(interest);
        Log.w("csrq：","sg:"+sg);
        Log.w("csrq：","tz:"+tz);
        Log.w("csrq：","csrq:"+csrq);
        Log.w("csrq：","interest:"+interest);
        RetrofitUtil.getInstance().apiService()
                .postUserUpdate(userInfoUpdate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            startActivity(new Intent(InterestActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
