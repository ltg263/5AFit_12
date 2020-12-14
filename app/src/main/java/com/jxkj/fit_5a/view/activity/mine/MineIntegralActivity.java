package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.view.adapter.ShoppingIntegralJlAdapter;
import com.jxkj.fit_5a.view.adapter.ShoppingIntegralRmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineIntegralActivity extends BaseActivity {
    @BindView(R.id.rv_rmdh_list)
    RecyclerView mRvRmdhList;
    @BindView(R.id.rv_lsjl_list)
    RecyclerView mRvLsjlList;
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    private ShoppingIntegralRmAdapter mShoppingIntegralRmAdapter;
    private ShoppingIntegralJlAdapter mShoppingIntegralJlAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_integral;
    }

    @Override
    protected void initViews() {
        mTvJifenNum.setText(SharedUtils.singleton().get(ConstValues.MY_INTEGRAL,""));
        initRv();
    }
    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mShoppingIntegralRmAdapter = new ShoppingIntegralRmAdapter(list);
        LinearLayoutManager ms1 = new LinearLayoutManager(this);
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRmdhList.setLayoutManager(ms1);
        mRvRmdhList.setHasFixedSize(true);
        mRvRmdhList.setAdapter(mShoppingIntegralRmAdapter);

        mShoppingIntegralRmAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mShoppingIntegralJlAdapter = new ShoppingIntegralJlAdapter(list);
        mRvLsjlList.setLayoutManager(new LinearLayoutManager(this));
        mRvLsjlList.setHasFixedSize(true);
        mRvLsjlList.setAdapter(mShoppingIntegralJlAdapter);

        mShoppingIntegralJlAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right_text,R.id.tv_jfsc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                break;
            case R.id.tv_jfsc:
                ShoppingActivity.intentStartActivity(this);
                break;
        }
    }
}
