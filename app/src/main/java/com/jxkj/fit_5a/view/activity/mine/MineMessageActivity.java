package com.jxkj.fit_5a.view.activity.mine;

import android.util.Log;
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
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;
import com.jxkj.fit_5a.view.adapter.MineMessageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineMessageActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private MineMessageAdapter mMineMessageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("消 息");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mMineMessageAdapter = new MineMessageAdapter(null);
        mRvList.setAdapter(mMineMessageAdapter);
        mMineMessageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(MineMessageActivity.this,MineMessageSubTypeActivity.class,
                        "subType",mMineMessageAdapter.getData().get(position).getSubType());
            }
        });
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        initRv();
    }

    private void initRv() {
        RetrofitUtil.getInstance().apiService()
                .getMessageSubtypeList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<MessageSubtypeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<MessageSubtypeBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData().size() > 0 && mMineMessageAdapter!=null) {
                                mLvNot.setVisibility(View.GONE);
                                mRvList.setVisibility(View.VISIBLE);
                                mMineMessageAdapter.setNewData(result.getData());
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
