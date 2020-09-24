package com.jxkj.fit_5a.view.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.adapter.HomeCommodityAdapter;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TwoFragment extends BaseFragment {
    @BindView(R.id.tv_left_text)
    TextView mTvLeftText;
    @BindView(R.id.tv_right_text)
    TextView mTvRightText;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_top_jyz)
    TextView mTvTopJyz;
    @BindView(R.id.tv_top_jyy)
    TextView mTvTopJyy;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.tv_zs)
    TextView mTvZs;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.rv_rmsp_list)
    RecyclerView mRvRmspList;
    @BindView(R.id.rv_dtrm_list)
    RecyclerView mRvDtrmList;
    private HomeTopAdapter mHomeTopAdapter;
    private HomeCommodityAdapter mHomeCommodityAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_one;
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

        mHomeTopAdapter = new HomeTopAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(ms);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mHomeCommodityAdapter = new HomeCommodityAdapter(list);
        LinearLayoutManager ms1 = new LinearLayoutManager(getActivity());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRmspList.setLayoutManager(ms1);
        mRvRmspList.setHasFixedSize(true);
        mRvRmspList.setAdapter(mHomeCommodityAdapter);

        mHomeCommodityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(list);
        mRvDtrmList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDtrmList.setHasFixedSize(true);
        mRvDtrmList.setAdapter(mHomeDynamicAdapter);

        mHomeDynamicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }

    public static TwoFragment newInstance() {
        TwoFragment homeFragment = new TwoFragment();
        return homeFragment;
    }

    @OnClick({R.id.tv_left_text, R.id.tv_right_text, R.id.tv_top_jyz, R.id.tv_top_jyy, R.id.rv_top_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left_text:
                break;
            case R.id.tv_right_text:
                break;
            case R.id.tv_top_jyz:
                break;
            case R.id.tv_top_jyy:
                break;
            case R.id.rv_top_list:
                break;
        }
    }
}



