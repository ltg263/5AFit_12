package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.view.adapter.LandscapeMotorPatternAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MotorPatternActivity extends Activity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_select_z)
    ImageView mIvSelectZ;
    @BindView(R.id.iv_select_y)
    ImageView mIvSelectY;
    private LandscapeMotorPatternAdapter mLandscapeMotorPatternAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_motor_pattern);
        ButterKnife.bind(this);
        getSportMapList();
    }
    private void getSportMapList() {
        RetrofitUtil.getInstance().apiService()
                .getSportMapList(0,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapListSposrt>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapListSposrt> result) {
                        if(result.getCode()==0){
                            initViews();
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
    private void initViews() {
        mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z1));
        mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y2));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mLandscapeMotorPatternAdapter = new LandscapeMotorPatternAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mLandscapeMotorPatternAdapter);

        mLandscapeMotorPatternAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClassicExerciseActivity.intentStartActivity(MotorPatternActivity.this);
            }
        });
    }

    @OnClick({R.id.iv_1,R.id.bnt1,R.id.bnt2,R.id.iv_select_z,R.id.iv_select_y})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                finish();
                break;
            case R.id.iv_select_z:
                mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z1));
                mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y2));

                break;
            case R.id.iv_select_y:
                mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z2));
                mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y1));
                break;
            case R.id.bnt1:

                break;
            case R.id.bnt2:
                CreateRoomActivity.intentStartActivity(this);
                break;
        }
    }

    public static void startIntentActivity(Context mContext){
        mContext.startActivity(new Intent(mContext,MotorPatternActivity.class));
    }
}
