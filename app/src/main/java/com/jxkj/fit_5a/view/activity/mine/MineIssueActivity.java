package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.MineIssueAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineIssueActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_issue;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("常见问题");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));


        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        MineIssueAdapter mMineIssueAdapter = new MineIssueAdapter(list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineIssueAdapter);

        mMineIssueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(MineIssueActivity.this, MineTopicActivity.class));
            }
        });
    }


    @OnClick({R.id.ll_back, R.id.ll_home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_home_search:
                break;
        }
    }
}
