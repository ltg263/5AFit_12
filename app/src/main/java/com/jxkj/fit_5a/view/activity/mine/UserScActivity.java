package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.entity.FavoriteQueryList;
import com.jxkj.fit_5a.view.adapter.UserScAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserScActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private UserScAdapter mUserScAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_user_gz;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("Ta的收藏");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));

        mUserScAdapter = new UserScAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mUserScAdapter);

        mUserScAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserHomeActivity.startActivity(UserScActivity.this,mUserScAdapter.getData().get(position).getUserId()+"");
            }
        });
        getFavoriteQueryOwn();
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }


    private void getFavoriteQueryOwn() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQueryOwn(getIntent().getStringExtra("userId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<FavoriteQueryList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<FavoriteQueryList> result) {
                        if (isDataInfoSucceed(result)) {
                            mUserScAdapter.setNewData(result.getData());
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



    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, UserScActivity.class);
        intent.putExtra("userId", userId);
        mContext.startActivity(intent);
    }
}
