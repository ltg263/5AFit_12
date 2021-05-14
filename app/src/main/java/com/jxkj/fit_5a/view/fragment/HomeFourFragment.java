package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.activity.mine.MineGiftActivity;
import com.jxkj.fit_5a.view.activity.mine.MineHomeActivity;
import com.jxkj.fit_5a.view.activity.mine.MineInfoActivity;
import com.jxkj.fit_5a.view.activity.mine.MineIntegralActivity;
import com.jxkj.fit_5a.view.activity.mine.MineJinDouActivity;
import com.jxkj.fit_5a.view.activity.mine.MineLwjActivity;
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

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFourFragment extends BaseFragment {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_backImg)
    ImageView mIvBackImg;
    @BindView(R.id.on_iv_set)
    ImageView mOnIvSet;
    @BindView(R.id.on_iv_msg)
    ImageView mOnIvMsg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_level)
    TextView mTvLevel;
    @BindView(R.id.tv_level_sy)
    TextView mTvLevelSy;
    @BindView(R.id.on_bnt_zjxf)
    TextView mOnBntZjxf;
    @BindView(R.id.tv_nickName)
    TextView mTvNickName;
    @BindView(R.id.tv_explain)
    TextView mTvExplain;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.on_ll_mine_jd)
    LinearLayout mOnLlMineJd;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.on_ll_mine_jf)
    LinearLayout mOnLlMineJf;
    @BindView(R.id.tv_couponCount)
    TextView mTvCouponCount;
    @BindView(R.id.on_ll_mine_fpq)
    LinearLayout mOnLlMineFpq;
    @BindView(R.id.tv_giftCount)
    TextView mTvGiftCount;
    @BindView(R.id.on_ll_mine_lw)
    LinearLayout mOnLlMineLw;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.on_tv_mine_grzy)
    TextView mOnTvMineGrzy;
    @BindView(R.id.iv_avatar)
    RoundImageView mIvAvatar;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.on_ll_mine_qb)
    LinearLayout mOnLlMineQb;
    @BindView(R.id.on_ll_mine_dfk)
    LinearLayout mOnLlMineDfk;
    @BindView(R.id.on_ll_mine_dfh)
    LinearLayout mOnLlMineDfh;
    @BindView(R.id.on_ll_mine_dsh)
    LinearLayout mOnLlMineDsh;
    @BindView(R.id.on_ll_mine_dpj)
    LinearLayout mOnLlMineDpj;
    @BindView(R.id.on_ll_mine_rwzx)
    LinearLayout mOnLlMineRwzx;
    @BindView(R.id.on_ll_mine_wzq)
    LinearLayout mOnLlMineWzq;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.rl_jfsc)
    RelativeLayout mRlJfsc;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.on_ll_mine_zxkh)
    LinearLayout mOnLlMineZxkh;
    @BindView(R.id.on_ll_mine_shdz)
    LinearLayout mOnLlMineShdz;
    @BindView(R.id.on_ll_mine_qcbz)
    LinearLayout mOnLlMineQcbz;
    @BindView(R.id.on_ll_mine_cjwt)
    LinearLayout mOnLlMineCjwt;
    @BindView(R.id.on_ll_mine_gywm)
    LinearLayout mOnLlMineGywm;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initViews() {

    }


    @Override
    public void initImmersionBar() {
        getUserDetail();
        getUserStatistic();
    }

    public static HomeFourFragment newInstance() {
        HomeFourFragment homeFragment = new HomeFourFragment();
        return homeFragment;
    }

    @OnClick({R.id.iv_avatar, R.id.on_iv_set, R.id.rl_jfsc, R.id.on_iv_msg, R.id.on_bnt_zjxf,R.id.on_ll_mine_lwj, R.id.on_ll_mine_jd, R.id.on_ll_mine_jf, R.id.on_ll_mine_fpq, R.id.on_ll_mine_lw, R.id.on_tv_mine_grzy, R.id.on_ll_mine_qb, R.id.on_ll_mine_dfk, R.id.on_ll_mine_dfh, R.id.on_ll_mine_dsh, R.id.on_ll_mine_dpj, R.id.on_ll_mine_rwzx, R.id.on_ll_mine_wzq, R.id.on_ll_mine_zxkh, R.id.on_ll_mine_shdz, R.id.on_ll_mine_qcbz, R.id.on_ll_mine_cjwt, R.id.on_ll_mine_gywm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
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
                ShoppingActivity.intentStartActivity(getActivity());
                break;
            case R.id.on_ll_mine_qb:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 0);
                break;
            case R.id.on_ll_mine_dfk:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 1);
                break;
            case R.id.on_ll_mine_dfh:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 2);
                break;
            case R.id.on_ll_mine_dsh:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 3);
                break;
            case R.id.on_ll_mine_dpj:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 4);
                break;
            case R.id.on_ll_mine_lwj:
                startActivity(new Intent(getActivity(), MineLwjActivity.class));
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
//                startActivity(new Intent(getActivity(), MineIssueQcActivity.class));
                IntentUtils.getInstence().intent(getContext(), WebViewActivity.class,"type","器材帮助");
                break;
            case R.id.on_ll_mine_cjwt:
//                startActivity(new Intent(getActivity(), MineIssueActivity.class));
                IntentUtils.getInstence().intent(getContext(), WebViewActivity.class,"type","常见问题");
                break;
            case R.id.on_ll_mine_gywm:
                startActivity(new Intent(getActivity(), MineRegardsActivity.class));
                break;
        }
    }

    private void getUserStatistic() {
        RetrofitUtil.getInstance().apiService()
                .getUserStatistic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserInfoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserInfoData> result) {
                        if (isDataInfoSucceed(result)) {
                            UserInfoData data = result.getData();
                            if(data!=null){
                                mTvBalance.setText(data.getBalance());
                                mTvCouponCount.setText(data.getCouponCount());
                                mTvGiftCount.setText(data.getGiftCount());
                                mTvIntegral.setText(data.getIntegral());
                                SharedUtils.singleton().put(ConstValues.MY_BALANCE,data.getBalance()+"");
                                SharedUtils.singleton().put(ConstValues.MY_COUPON_COUNT,data.getCouponCount()+"");
                                SharedUtils.singleton().put(ConstValues.MY_GIFTCOUNT,data.getGiftCount()+"");
                                SharedUtils.singleton().put(ConstValues.MY_INTEGRAL,data.getIntegral()+"");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getUserDetail() {
        RetrofitUtil.getInstance().apiService()
                .getUserDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserDetailData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserDetailData> result) {
                        if (isDataInfoSucceed(result)) {
                            initUI(result.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void initUI(UserDetailData data) {
        if(StringUtil.isNotBlank(data.getBackImg())){
            GlideImageUtils.setGlideImage(getActivity(),data.getBackImg(),mIvBackImg);
        }
        GlideImageUtils.setGlideImage(getActivity(),data.getAvatar(),mIvAvatar);
        mTvNickName.setText(data.getNickName());
        if(StringUtil.isNotBlank(data.getExplain())){
            mTvExplain.setText(data.getExplain());
        }
        SharedUtils.singleton().put(ConstValues.USER_AGE,data.getAge());
        SharedUtils.singleton().put(ConstValues.USER_GENDER,data.getGender());
        SharedUtils.singleton().put(ConstValues.USER_IMG,data.getAvatar());


        if(StringUtil.isBlank(data.getLevelExpireTime())){
            return;
        }
        long endTime = StringUtil.getMsToTime(data.getLevelExpireTime(),"yyyy-MM-dd HH:mm:ss");
        long surrentMillis = System.currentTimeMillis();
        if(endTime > surrentMillis){
            double aa =  (endTime - surrentMillis)/ (1000*60*60*24);
            mTvLevel.setText("已开通会员");
            mTvLevelSy.setText("还有"+aa+"天到期");
            mOnBntZjxf.setText("立即续费");
        }
    }
}



