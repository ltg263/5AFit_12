package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogChoicePackage;
import com.jxkj.fit_5a.conpoment.view.JudgeNestedScrollView;
import com.jxkj.fit_5a.conpoment.view.SquareBannerLayout;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ProductDetailsBean;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.view.activity.mine.order.AddressActivity;
import com.jxkj.fit_5a.view.activity.mine.order.OrderAffirmActivity;
import com.jxkj.fit_5a.view.adapter.ShoppingPingJiaAdapter;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_gui_ge)
    TextView tv_gui_ge;
    @BindView(R.id.tv_commentTotal)
    TextView tv_commentTotal;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.banner)
    SquareBannerLayout mBanner;
    @BindView(R.id.jnsw)
    JudgeNestedScrollView jnsw;
    @BindView(R.id.rl_pin_lun)
    RecyclerView mRlPinLun;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.ll_evalute)
    LinearLayout llEvalute;
    @BindView(R.id.ll_gg)
    LinearLayout llGg;
    @BindView(R.id.web)
    WebView mWebView;
    private String id;
    private ShoppingPingJiaAdapter mShoppingPingJiaAdapter;
    List<ProductDetailsBean.SpecsLisBean> specsLis;//规格
    String imgUrl="";
    private AddressData addressData;
    private List<ProductDetailsBean.SkuListBean> skuList;//规格

    public static void startActivity(Context mContext, String id) {
        Intent intent = new Intent(mContext, ShoppingDetailsActivity.class);
        intent.putExtra("id", id);
        mContext.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_details;
    }

    @Override
    protected void initViews() {
        id = getIntent().getStringExtra("id");
        initTitle();
    }

    private void initTitle() {
        tvTitle.setText("商品详情");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.ic_zhuan_fa));
        initRv();
        getProductDetails(id);
    }

    private void initRv() {
        mShoppingPingJiaAdapter = new ShoppingPingJiaAdapter(null);
        mRlPinLun.setLayoutManager(new LinearLayoutManager(this));
        mRlPinLun.setHasFixedSize(true);
        mRlPinLun.setAdapter(mShoppingPingJiaAdapter);

        mShoppingPingJiaAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

    }

    @OnClick({R.id.ll_back,R.id.ll_gg,R.id.tv_address,R.id.ll_all_evalute,R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_gg:
                ShowChoicePackageDialog();
                break;
            case R.id.tv_address:
                AddressActivity.startActivity(ShoppingDetailsActivity.this,2);
                break;
            case R.id.ll_all_evalute:
                IntentUtils.getInstence().intent(this, CommentListActivity.class,"id",id);
                break;
            case R.id.tv_ok:
                postShowOrderInfo();
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            if (resultCode == 1) {
                addressData = (AddressData) data.getSerializableExtra("address");
                if (addressData == null) {
                    mTvAddress.setText("请选择收货地址");
                } else {
                    mTvAddress.setText(addressData.getRegions() + addressData.getLocation());
                }
            }
        }

    }

    private void postShowOrderInfo() {
        PostOrderInfo info = new PostOrderInfo();
        info.setAddressId(addressData.getId());
        List<PostOrderInfo.EntityListBean> entityList = new ArrayList<>();
        PostOrderInfo.EntityListBean entityListBean = new PostOrderInfo.EntityListBean();
        entityListBean.setSkuId(skuId);
        entityListBean.setProductId(id);
        entityListBean.setQuantity("1");
        entityList.add(entityListBean);
        info.setEntityList(entityList);

        RetrofitUtil.getInstance().apiService()
                .postShowOrderInfo(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        isDataInfoSucceed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        startActivity(new Intent(this, OrderAffirmActivity.class));
    }
    String skuId = null;

    private void ShowChoicePackageDialog() {
        DialogChoicePackage choicePackageDialog = new DialogChoicePackage(
                ShoppingDetailsActivity.this, specsLis, skuList, imgUrl,
                new DialogChoicePackage.OnChoicePackageDialogListener() {
            @Override
            public void addListener(String skuId, String text) {
                tv_gui_ge.setText(text);
            }

            @Override
            public void buyListener(String skuId, int num) {

            }
        });
        choicePackageDialog.showDialog();
    }


    private void getProductDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getProductDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductDetailsBean> result) {
                        if(isDataInfoSucceed(result)){
                            initViewUi(result.getData());
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


    private void initViewUi(ProductDetailsBean detailsBean) {
        skuList = detailsBean.getSkuList();
        initBanner(detailsBean.getImgs());
        initWebView(detailsBean.getDetails());
        imgUrl = detailsBean.getImgUrl();
        specsLis = detailsBean.getSpecsLis();
        if(skuList!=null && skuList.size()>0){
            llGg.setVisibility(View.VISIBLE);
            tv_gui_ge.setText(skuList.get(0).getSpecText());
            skuId = skuList.get(0).getId();
        }
        tvName.setText(detailsBean.getName());
        tvIntro.setText(detailsBean.getSubTitle());
        tvPrice.setText(detailsBean.getPrice());
        tvSales.setText(detailsBean.getDeductIntegral());
        tv_commentTotal.setText("宝贝评价("+detailsBean.getCommentTotal()+"）");
        mShoppingPingJiaAdapter.setNewData(detailsBean.getCommentList());
    }

    private void initWebView(String details) {
        WebSettings webSettings = mWebView.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        //支持javascript
//        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.loadData(
                StringUtil.getNewContent1(details), "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }


    private void initBanner(String imgs) {
        String[] strArr = imgs.split(",");
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "你点了第" + position + "张轮播图:" + titles.get(position));
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(Arrays.asList(strArr));
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

}
