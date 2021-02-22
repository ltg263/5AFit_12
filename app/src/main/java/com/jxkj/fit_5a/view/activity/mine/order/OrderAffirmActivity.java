package com.jxkj.fit_5a.view.activity.mine.order;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.CreateOrderBean;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ShowOrderInfo;
import com.jxkj.fit_5a.view.adapter.OrderAffirmAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderAffirmActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_syjf)
    TextView tv_syjf;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv_youhui)
    TextView tv_youhui;
    @BindView(R.id.iv_wx)
    ImageView iv_wx;
    @BindView(R.id.iv_zfb)
    ImageView iv_zfb;
    @BindView(R.id.tv_levelMessage)
    EditText tv_levelMessage;
    private OrderAffirmAdapter mOrderAffirmAdapter;
    private PostOrderInfo info;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_affirm;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我的订单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRv();

        info = (PostOrderInfo) getIntent().getSerializableExtra("info");
        postShowOrderInfo();
    }

    private void initRv() {
        mOrderAffirmAdapter = new OrderAffirmAdapter( null);//item.getProducts()
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mOrderAffirmAdapter);
        String couponCount = getIntent().getStringExtra(ConstValues.MY_COUPON_COUNT);
        if(StringUtil.isBlank(couponCount) || Double.valueOf(couponCount)==0){
            tv_youhui.setText("暂无优惠券");
        }else{
            tv_youhui.setText("有"+couponCount+"张优惠券");
        }
    }

    @OnClick({R.id.ll_back, R.id.rl_address,R.id.iv_zfb,R.id.iv_wx,R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_address:
                AddressActivity.startActivity(OrderAffirmActivity.this,2);
                break;
            case R.id.iv_zfb:
                iv_wx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                iv_zfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                break;
            case R.id.iv_wx:
                iv_wx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                iv_zfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                break;
            case R.id.tv_pay:
                postcreateOrder();
                break;
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            if (resultCode == 1) {
                AddressData addressData = (AddressData) data.getSerializableExtra("address");
                if (addressData == null) {
                    info.setAddressId("");
                    mTvName.setText("");
                    mTvAddress.setText("请选择收货地址");
                } else {
                    info.setAddressId(addressData.getId());
                    mTvName.setText("收件人："+addressData.getAcceptName()+"  电话："+addressData.getMobile());
                    mTvAddress.setText(addressData.getLocation());
                }
            }
        }

    }

    private void postcreateOrder() {
        info.setLevelMessage(tv_levelMessage.getText().toString());
        info.setOrderType("2");
        Log.w("info","info"+info.toString());
        RetrofitUtil.getInstance().apiService()
                .postcreateOrder(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CreateOrderBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CreateOrderBean> result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("创建成功");

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

    private void postShowOrderInfo() {
        RetrofitUtil.getInstance().apiService()
                .postShowOrderInfo(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ShowOrderInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ShowOrderInfo> result) {
                        if(isDataInfoSucceed(result)){
                            ShowOrderInfo data = result.getData();
                            if (data.getUserAddress() != null) {
                                mTvName.setText("收件人："+data.getUserAddress().getAcceptName()+"  电话："+data.getUserAddress().getMobile());
                                mTvAddress.setText(data.getUserAddress().getLocation());
                                info.setAddressId(data.getUserAddress().getId());
                            }
                            if(data.getOrderProducts()!=null){
                                mOrderAffirmAdapter.setNewData(data.getOrderProducts());
                            }
                            tv_syjf.setText(data.getUseableIntegral());
                            tv1.setText(data.getTotalQuantity()+"件商品");
                            if(Double.valueOf(data.getRealAmount())!=0){
                                tv11.setText(data.getTotalIntegral()+"积分" +"+￥"+data.getRealAmount());
                            }else{
                                tv11.setText(data.getTotalIntegral()+"积分");
                            }
                        }else{
                            OrderAffirmActivity.this.finish();
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
}
