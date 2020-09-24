package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.MineWzqAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineWzqActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineWzqAdapter mMineWzqAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_wzq;
    }

    @Override
    protected void initViews() {
        mIvBack.setOnClickListener(v -> finish());
        initRv();
    }

    private void initRv() {
        List<String> list  = new ArrayList<>();
        for(int i = 0;i<11;i++){
            list.add("");
        }

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mMineWzqAdapter = new MineWzqAdapter(list);
        mRvList.setAdapter(mMineWzqAdapter);
    }
}
