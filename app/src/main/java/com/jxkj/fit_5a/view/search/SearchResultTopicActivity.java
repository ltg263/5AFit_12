package com.jxkj.fit_5a.view.search;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.adapter.TopicListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchResultTopicActivity extends BaseActivity {


    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String search;
    private TopicListAdapter mTopicListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_result_topic;
    }

    @Override
    protected void initViews() {
        search = getIntent().getStringExtra("search");
        tvTopTitle.setText(search);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);

        mTopicListAdapter = new TopicListAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTopicListAdapter);

        mTopicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(SearchResultTopicActivity.this, MineTopicActivity.class));
            }
        });
        getAllTopic();
    }

    @OnClick({R.id.img_top_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }

    private void getAllTopic(){
        RetrofitUtil.getInstance().apiService()
                .getAllTopic(search,1,100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<HotTopicBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<HotTopicBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()!=null && result.getData().size()>0){
                                llNoData.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mTopicListAdapter.setNewData(result.getData());
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
