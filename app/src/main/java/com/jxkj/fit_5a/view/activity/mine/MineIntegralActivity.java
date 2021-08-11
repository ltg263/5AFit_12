package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.view.adapter.ShoppingIntegralJlAdapter;
import com.jxkj.fit_5a.view.adapter.ShoppingIntegralRmAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineIntegralActivity extends BaseActivity {
    @BindView(R.id.rv_rmdh_list)
    RecyclerView mRvRmdhList;
    @BindView(R.id.rv_lsjl_list)
    RecyclerView mRvLsjlList;
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    @BindView(R.id.tv_integralGrandAmount)
    TextView tv_integralGrandAmount;
    @BindView(R.id.tv_integralThatDayGrandAmount)
    TextView tv_integralThatDayGrandAmount;
    @BindView(R.id.tv_logPay)
    TextView tv_logPay;
    @BindView(R.id.tv_time)
    TextView tv_time;
    private CustomPopWindow distancePopWindow;
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
        getProductList();
        getUserStatistic();
    }
    private void initRv() {
        mShoppingIntegralRmAdapter = new ShoppingIntegralRmAdapter(null);
        LinearLayoutManager ms1 = new LinearLayoutManager(this);
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRmdhList.setLayoutManager(ms1);
        mRvRmdhList.setHasFixedSize(true);
        mRvRmdhList.setAdapter(mShoppingIntegralRmAdapter);

        mShoppingIntegralRmAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(MineIntegralActivity.this,
                        mShoppingIntegralRmAdapter.getData().get(position).getId());
            }
        });

        mShoppingIntegralJlAdapter = new ShoppingIntegralJlAdapter(null);
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

    @OnClick({R.id.iv_back, R.id.tv_right_text,R.id.tv_jfsc,R.id.tv_logPay,R.id.iv_1,R.id.iv_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                break;
            case R.id.iv_1:
                tv_time.setText(getLastMonth(-1));
                break;
            case R.id.iv_2:
                tv_time.setText(getLastMonth(1));
                break;
            case R.id.tv_logPay:
                if(distancePopWindow!=null){
                    distancePopWindow.showAsDropDown(tv_logPay, -100, 0);
                    return;
                }
                initPopupWindow();
                break;
            case R.id.tv_jfsc:
                ShoppingActivity.intentStartActivity(this);
                break;
        }
    }

    public String getLastMonth(int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
        Date date = null;
        try {
            date = format.parse(tv_time.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + x); // 设置为上一个月
        return format.format(calendar.getTime());
    }

    private void initPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.popup_window_cqxc, null, false);
        TextView tv_tp= view.findViewById(R.id.tv_tp);
        TextView tv_sp= view.findViewById(R.id.tv_sp);
        tv_tp.setText("支出记录");
        tv_sp.setText("收入记录");
        tv_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_logPay.setText("支出记录");
                distancePopWindow.dissmiss();

            }
        });
        tv_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_logPay.setText("收入记录");
                distancePopWindow.dissmiss();
            }
        });
        //创建并显示popWindow
        distancePopWindow = new CustomPopWindow.PopupWindowBuilder(MineIntegralActivity.this)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        rbDistance.setChecked(false);
                    }
                })
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(tv_logPay, -100, 0);//显示PopupWindow
    }

    private void getProductList() {
        RetrofitUtil.getInstance().apiService()
                .getProductList(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if(isDataInfoSucceed(result)){
                            mShoppingIntegralRmAdapter.setNewData(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

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
                                mTvJifenNum.setText(data.getIntegral());
                                tv_integralGrandAmount.setText(data.getIntegralGrandAmount().replace(".00",""));
                                tv_integralThatDayGrandAmount.setText(data.getIntegralThatDayGrandAmount().replace(".00",""));
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
}
