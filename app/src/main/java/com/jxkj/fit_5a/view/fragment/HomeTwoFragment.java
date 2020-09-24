package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.activity.exercise.ExerciseRecordActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionActivity;
import com.jxkj.fit_5a.view.activity.home.TaskSignActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityManageActivity;
import com.jxkj.fit_5a.view.adapter.HomeCommodityAdapter;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTwoBelowAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTwoTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeTwoFragment extends BaseFragment {

    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.tv_two_ri)
    TextView mTvTwoRi;
    @BindView(R.id.tv_two_zhou)
    TextView mTvTwoZhou;
    @BindView(R.id.tv_two_yue)
    TextView mTvTwoYue;
    @BindView(R.id.rv_two_list)
    RecyclerView mRvTwoList;
    private HomeTwoTopAdapter mHomeTwoTopAdapter;
    private HomeTwoBelowAdapter mHomeTwoBelowAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_two;
    }

    @Override
    protected void initViews() {
        initRvUi();

    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mHomeTwoTopAdapter = new HomeTwoTopAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(ms);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTwoTopAdapter);

        mHomeTwoTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), TaskSelectionActivity.class));
            }
        });
        mHomeTwoBelowAdapter = new HomeTwoBelowAdapter(list);
        mRvTwoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTwoList.setHasFixedSize(true);
        mRvTwoList.setAdapter(mHomeTwoBelowAdapter);

        mHomeTwoBelowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeTwoFragment newInstance() {
        HomeTwoFragment homeFragment = new HomeTwoFragment();
        return homeFragment;
    }


    @OnClick({R.id.rl_sbgl,R.id.tv_two_ri, R.id.tv_two_zhou, R.id.tv_two_yue,R.id.tv_go_find,R.id.tv_ydjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_sbgl:
                startActivity(new Intent(getActivity(), FacilityManageActivity.class));
                break;
            case R.id.tv_two_ri:
                break;
            case R.id.tv_two_zhou:
                break;
            case R.id.tv_two_yue:
                break;
            case R.id.tv_go_find:
                startActivity(new Intent(getActivity(), TaskSelectionActivity.class));
                break;
            case R.id.tv_ydjl:
                startActivity(new Intent(getActivity(), ExerciseRecordActivity.class));
                break;
        }
    }
}



