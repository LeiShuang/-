package com.zfsoft.zfsoft_product.modules.login.user_agreement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;


/**
 * Created by ckw
 * on 2019/3/15.
 */
public class UserAgreementActivity extends BaseActivity {


    private WebView mWebview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_agreement;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mWebview = findViewById(R.id.webview_agreement);
        initWebView();
        mWebview.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
        mWebview.setBackgroundResource(R.color.login_bar_color);
        mWebview.loadUrl("file:////android_asset/Agreement.html");
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }

    private void initWebView() {
        WebSettings mWebSettings = mWebview.getSettings();
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        mWebSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        mWebSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWebSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        mWebSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        mWebSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        mWebSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //关闭webview中缓存
        mWebSettings.setAllowFileAccess(true); //设置可以访问文件
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        mWebSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.login_bar_color).statusBarDarkFont(true,0.2f).init();
    }

}