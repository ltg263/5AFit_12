package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.TopicListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TopicAllActivity extends BaseActivity {
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.view3)
    View mView3;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_topic_all;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        TopicListAdapter mTopicListAdapter = new TopicListAdapter(list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTopicListAdapter);

        mTopicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(TopicAllActivity.this, MineTopicActivity.class));
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.tv_search_topic, R.id.rl1, R.id.rl2, R.id.rl3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search_topic:
                break;
            case R.id.rl1:
                initView(mTv1,mView1);
                break;
            case R.id.rl2:
                initView(mTv2,mView2);
                break;
            case R.id.rl3:
                initView(mTv3,mView3);
                break;
        }
    }
    private void initView(TextView tv,View v){
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_666666));
        mTv2.setTextColor(getResources().getColor(R.color.color_666666));
        mTv3.setTextColor(getResources().getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);
        mView3.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }
}
