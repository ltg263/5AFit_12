package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.CommentListBean;
import com.jxkj.fit_5a.view.adapter.ShoppingPingJiaAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private ShoppingPingJiaAdapter mShoppingPingJiaAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("全部评论");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initRv();
    }

    private void initRv() {
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mLvNot.setVisibility(View.GONE);
        mRvList.setVisibility(View.VISIBLE);
        mShoppingPingJiaAdapter = new ShoppingPingJiaAdapter(null);
        mRvList.setAdapter(mShoppingPingJiaAdapter);
        getProductDetails();
    }


    private void getProductDetails() {
        RetrofitUtil.getInstance().apiService()
                .getCommentList(getIntent().getStringExtra("id"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CommentListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CommentListBean> result) {
                        if(isDataInfoSucceed(result)){
                            mShoppingPingJiaAdapter.setNewData(result.getData().getList());
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
