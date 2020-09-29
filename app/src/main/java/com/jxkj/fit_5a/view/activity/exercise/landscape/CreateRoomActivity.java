package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.ToastUtil;
import com.jxkj.fit_5a.view.adapter.LandscapeCreateRoomAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateRoomActivity extends Activity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_7)
    ImageView iv_7;
    @BindView(R.id.iv_8)
    ImageView iv_8;
    @BindView(R.id.et_1)
    EditText et_1;
    @BindView(R.id.et_2)
    EditText et_2;
    private LandscapeCreateRoomAdapter mLandscapeCreateRoomAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_create_room);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mLandscapeCreateRoomAdapter = new LandscapeCreateRoomAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mLandscapeCreateRoomAdapter);

        mLandscapeCreateRoomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mLandscapeCreateRoomAdapter.setPos(position);
                mLandscapeCreateRoomAdapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.iv_1, R.id.iv_8, R.id.iv_3, R.id.iv_2, R.id.iv_7,R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                finish();
                break;
            case R.id.iv_2:
            case R.id.iv_8:
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_7:
            case R.id.iv_3:
                iv_7.setVisibility(View.INVISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                break;
            case R.id.btn:
                if(et_1.getText().toString().equals("")){
                    ToastUtils.showShort("房间名不能为空");
                    return;
                }
                CreateRoomMineActivity.intentStartActivity(CreateRoomActivity.this,
                        et_1.getText().toString(),et_2.getText().toString());
                break;
        }
    }
    public static void intentStartActivity(Context mContext){
        mContext.startActivity(new Intent(mContext,CreateRoomActivity.class));
    }
}
