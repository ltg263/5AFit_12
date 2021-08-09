package com.jxkj.fit_5a.view.activity.home;

import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.MyWebView;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.web)
    MyWebView mWeb;

    @Override
    protected int getContentView() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews() {
        String type = getIntent().getStringExtra("type");
        mTvTitle.setText(type);
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        setWebViewClient();
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(type.equals("器材帮助")){
            mWeb.loadUrl("https://5afit.nbqichen.com/help/index.html#/helpEquip");
        }else if(type.equals("常见问题")){
            mWeb.loadUrl("https://5afit.nbqichen.com/help/index.html#/help");
        }else{
            mTvTitle.setText(type);
            mWeb.loadUrl(getIntent().getStringExtra("url"));
        }
    }

    private void setWebViewClient() {
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(false);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage


//        mWeb.setOnTouchScreenListener(new MyWebView.OnTouchScreenListener() {
//
//            @Override
//            public void onTouchScreen() {
//                Log.w("-->>","++++++");
////                isFlowing = true;
//                if (mRl.getVisibility() == View.GONE) {
//                    mRl.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onReleaseScreen() {
////                isFlowing = false;
//                Log.w("-->>","--->>");
//                if (mRl.getVisibility() == View.VISIBLE) {
//                    mRl.setVisibility(View.GONE);
//                }
//            }
//        });
    }
}