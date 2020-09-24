package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.association.InterestAllActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.TopicAddActivity;
import com.jxkj.fit_5a.view.activity.association.TopicAllActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeRmhtAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeThreeFragment extends BaseFragment {


    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_rmht_list)
    RecyclerView mRvRmhtList;
    @BindView(R.id.rv_sq_list)
    RecyclerView mRvSqList;
    private HomeThreeTopAdapter mHomeThreeTopAdapter;
    private HomeThreeRmhtAdapter mHomeThreeRmhtAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three;
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
        list.add("-2");
        list.add("");
        list.add("-1");

        mHomeThreeTopAdapter = new HomeThreeTopAdapter(list);
//        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
//        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeThreeTopAdapter);

        mHomeThreeTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeTopAdapter.getData().get(position).equals("-1")){
                    startActivity(new Intent(getActivity(), InterestAllActivity.class));
                }else{
                    Intent mIntent = new Intent(getActivity(), MineCircleActivity.class);
                    mIntent.putExtra("type","已加入");
                    startActivity(mIntent);
                }
            }
        });

        mHomeThreeRmhtAdapter = new HomeThreeRmhtAdapter(list);
        mRvRmhtList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRmhtList.setHasFixedSize(true);
        mRvRmhtList.setAdapter(mHomeThreeRmhtAdapter);

        mHomeThreeRmhtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), MineTopicActivity.class));

            }
        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvSqList.setLayoutManager(manager);
        HomeThreeSqAdapter mHomeThreeSqAdapter = new HomeThreeSqAdapter(list);
        mRvSqList.setHasFixedSize(true);
        mRvSqList.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeSqAdapter.getData().get(position).equals("-2")){
                    startActivity(new Intent(getActivity(), VideoActivity.class));
                }else{
                    startActivity(new Intent(getActivity(), AssociationActivity.class));
                }
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeThreeFragment newInstance() {
        HomeThreeFragment homeFragment = new HomeThreeFragment();
        return homeFragment;
    }

    @OnClick({R.id.tv_refresh, R.id.tv_topic_all,R.id.tv_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                break;
            case R.id.tv_topic_all:
                startActivity(new Intent(getActivity(), TopicAllActivity.class));
                break;
            case R.id.tv_right_text:
                startActivity(new Intent(getActivity(), AssociationAddActivity.class));
                break;
        }
    }
}



