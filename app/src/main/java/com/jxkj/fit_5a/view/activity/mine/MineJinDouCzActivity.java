package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.MineJDczSelectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineJinDouCzActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_jindou_cz;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("金豆充值");
        mTvRighttext.setText("账单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRv();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            list.add("");
        }
        MineJDczSelectAdapter mMineJDczSelectAdapter = new MineJDczSelectAdapter(list);
        mRvList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineJDczSelectAdapter);

        mMineJDczSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list.clear();
                for(int i = 0;i<3;i++){
                    list.add("");
                }
                list.set(position,"-");
                mMineJDczSelectAdapter.notifyDataSetChanged();
            }
        });
    }

}
