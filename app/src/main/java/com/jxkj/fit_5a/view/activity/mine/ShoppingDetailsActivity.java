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
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogChoicePackage;
import com.jxkj.fit_5a.conpoment.view.JudgeNestedScrollView;
import com.jxkj.fit_5a.conpoment.view.SquareBannerLayout;
import com.jxkj.fit_5a.view.activity.mine.order.OrderAffirmActivity;
import com.jxkj.fit_5a.view.adapter.ShoppingPingJiaAdapter;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.banner)
    SquareBannerLayout mBanner;
    @BindView(R.id.jnsw)
    JudgeNestedScrollView jnsw;
    @BindView(R.id.ll_all_evalute)
    LinearLayout llAllEvalute;
    @BindView(R.id.rl_pin_lun)
    RecyclerView mRlPinLun;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.ll_evalute)
    LinearLayout llEvalute;
    @BindView(R.id.web)
    WebView mWebView;
    private int id;
    private ShoppingPingJiaAdapter mShoppingPingJiaAdapter;

    public static void startActivity(Context mContext, int id) {
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
        id = getIntent().getIntExtra("id", 0);
        initTitle();

//        initViewUi();
    }

    private void initTitle() {
        tvTitle.setText("商品详情");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.ic_zhuan_fa));
        initBanner();
        initRv();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mShoppingPingJiaAdapter = new ShoppingPingJiaAdapter(list);
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

    private void initViewUi(String content) {

        WebSettings webSettings = mWebView.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        //支持javascript
//        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.loadData(
                StringUtil.getNewContent1(content), "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }


    private void initBanner() {
        List<String> bannerUrls= new ArrayList<>();
        bannerUrls.add("https://yjkc.qufujin1688.com/yjkc/upload//productBanner/5F4729274464616A609E5233485C6953.jpg");
        bannerUrls.add("https://yjkc.qufujin1688.com/yjkc/upload//productBanner/5F4729274464616A609E5233485C6953.jpg");
        bannerUrls.add("https://yjkc.qufujin1688.com/yjkc/upload//productBanner/5F4729274464616A609E5233485C6953.jpg");
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
        mBanner.setImages(bannerUrls);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    @OnClick({R.id.ll_back,R.id.tv_gui_ge,R.id.tv_address,R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_gui_ge:
                ShowChoicePackageDialog();
                break;
            case R.id.tv_address:
                break;
            case R.id.tv_ok:
                startActivity(new Intent(this, OrderAffirmActivity.class));
                break;
        }
    }

    private void ShowChoicePackageDialog() {
        DialogChoicePackage choicePackageDialog = new DialogChoicePackage(ShoppingDetailsActivity.this);
        choicePackageDialog.setOnChoicePackageDialogListener(new DialogChoicePackage.OnChoicePackageDialogListener() {
            @Override
            public void addListener(String skuId, int num) {

            }

            @Override
            public void buyListener(String skuId, int num) {

            }

        });
        choicePackageDialog.showDialog();
    }
}
