package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.view.adapter.LandscapeCreateRoomMineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateRoomMineActivity extends Activity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_name)
    TextView mTvName;
    private LandscapeCreateRoomMineAdapter mLandscapeCreateRoomMineAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_create_room_mine);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mTvName.setText(getIntent().getStringExtra("name"));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mLandscapeCreateRoomMineAdapter = new LandscapeCreateRoomMineAdapter(list);
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mLandscapeCreateRoomMineAdapter);

        mLandscapeCreateRoomMineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(getActivity(), TaskSelectionActivity.class));
            }
        });

    }

    @OnClick({R.id.iv_1,R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                finish();
                break;
            case R.id.tv_ok:
                ClassicExerciseActivity.intentStartActivity(this,"");
                break;

        }
    }

    public static void intentStartActivity(Context mContext, String name, String password) {
        Intent mIntent = new Intent(mContext, CreateRoomMineActivity.class);
        mIntent.putExtra("name", name);
        mIntent.putExtra("password", password);
        mContext.startActivity(mIntent);
    }
}
