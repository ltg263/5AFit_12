package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.view.activity.mine.MineGiftActivity;
import com.jxkj.fit_5a.view.activity.mine.MineHomeActivity;
import com.jxkj.fit_5a.view.activity.mine.MineInfoActivity;
import com.jxkj.fit_5a.view.activity.mine.MineIntegralActivity;
import com.jxkj.fit_5a.view.activity.mine.MineIssueActivity;
import com.jxkj.fit_5a.view.activity.mine.MineIssueQcActivity;
import com.jxkj.fit_5a.view.activity.mine.MineJinDouActivity;
import com.jxkj.fit_5a.view.activity.mine.MineMessageActivity;
import com.jxkj.fit_5a.view.activity.mine.MineRegardsActivity;
import com.jxkj.fit_5a.view.activity.mine.MineRwzxActivity;
import com.jxkj.fit_5a.view.activity.mine.MineSetActivity;
import com.jxkj.fit_5a.view.activity.mine.MineVipActivity;
import com.jxkj.fit_5a.view.activity.mine.MineWzqActivity;
import com.jxkj.fit_5a.view.activity.mine.MineYhqActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingActivity;
import com.jxkj.fit_5a.view.activity.mine.order.AddressActivity;
import com.jxkj.fit_5a.view.activity.mine.order.OrderActivity;

import butterknife.OnClick;

public class HomeFourFragment extends BaseFragment {

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initViews() {

    }


    @Override
    public void initImmersionBar() {

    }

    public static HomeFourFragment newInstance() {
        HomeFourFragment homeFragment = new HomeFourFragment();
        return homeFragment;
    }

    @OnClick({R.id.iv_img,R.id.on_iv_set, R.id.rl_jfsc,R.id.on_iv_msg, R.id.on_bnt_zjxf, R.id.on_ll_mine_jd, R.id.on_ll_mine_jf, R.id.on_ll_mine_fpq, R.id.on_ll_mine_lw, R.id.on_tv_mine_grzy, R.id.on_ll_mine_qb, R.id.on_ll_mine_dfk, R.id.on_ll_mine_dfh, R.id.on_ll_mine_dsh, R.id.on_ll_mine_dpj, R.id.on_ll_mine_rwzx, R.id.on_ll_mine_wzq, R.id.on_ll_mine_zxkh, R.id.on_ll_mine_shdz, R.id.on_ll_mine_qcbz, R.id.on_ll_mine_cjwt, R.id.on_ll_mine_gywm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_img:
                startActivity(new Intent(getActivity(), MineInfoActivity.class));
                break;
            case R.id.on_iv_set:
                startActivity(new Intent(getActivity(), MineSetActivity.class));
                break;
            case R.id.on_iv_msg:
//                startActivity(new Intent(getActivity(), MineMessageHdActivity.class));
                startActivity(new Intent(getActivity(), MineMessageActivity.class));
                break;
            case R.id.on_bnt_zjxf:
                startActivity(new Intent(getActivity(), MineVipActivity.class));
                break;
            case R.id.on_ll_mine_jd:
                startActivity(new Intent(getActivity(), MineJinDouActivity.class));
                break;
            case R.id.on_ll_mine_jf:
                startActivity(new Intent(getActivity(), MineIntegralActivity.class));
                break;
            case R.id.on_ll_mine_fpq:
                startActivity(new Intent(getActivity(), MineYhqActivity.class));
                break;
            case R.id.on_ll_mine_lw:
                startActivity(new Intent(getActivity(), MineGiftActivity.class));
                break;
            case R.id.on_tv_mine_grzy:
                startActivity(new Intent(getActivity(), MineHomeActivity.class));
                break;
            case R.id.rl_jfsc:
                startActivity(new Intent(getActivity(), ShoppingActivity.class));
                break;
            case R.id.on_ll_mine_qb:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class,"type",0);
                break;
            case R.id.on_ll_mine_dfk:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class,"type",1);
                break;
            case R.id.on_ll_mine_dfh:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class,"type",2);
                break;
            case R.id.on_ll_mine_dsh:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class,"type",3);
                break;
            case R.id.on_ll_mine_dpj:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class,"type",4);
                break;
            case R.id.on_ll_mine_rwzx:
                startActivity(new Intent(getActivity(), MineRwzxActivity.class));
                break;
            case R.id.on_ll_mine_wzq:
                startActivity(new Intent(getActivity(), MineWzqActivity.class));
                break;
            case R.id.on_ll_mine_zxkh:
                break;
            case R.id.on_ll_mine_shdz:
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            case R.id.on_ll_mine_qcbz:
                startActivity(new Intent(getActivity(), MineIssueQcActivity.class));
                break;
            case R.id.on_ll_mine_cjwt:
                startActivity(new Intent(getActivity(), MineIssueActivity.class));
                break;
            case R.id.on_ll_mine_gywm:
                startActivity(new Intent(getActivity(), MineRegardsActivity.class));
                break;
        }
    }
}



