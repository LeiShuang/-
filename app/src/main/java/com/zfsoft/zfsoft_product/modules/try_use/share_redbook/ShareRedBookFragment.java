package com.zfsoft.zfsoft_product.modules.try_use.share_redbook;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ShareUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 创建日期：2019/2/26 on 17:34
 * 描述:
 * 作者:Ls
 */
public class ShareRedBookFragment extends BaseFragment implements ShareRedBookContract.View, View.OnClickListener {
    @BindView(R.id.et_input_share_url)
    EditText mEtInputShareUrl;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.share_web_view)
    WebView mShareWebView;
    @Inject
    ShareRedBookPresenter mPresenter;

    @Inject
    public ShareRedBookFragment() {

    }

    private WebSettings mWebSettings;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_share_red_book;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        initWebView();
        mShareWebView.loadUrl("file:////android_asset/share.html");
    }

    private void initWebView() {
        mWebSettings = mShareWebView.getSettings();
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
    protected void initListener() {
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    public void submitShareUrlSuccess(SignBean info) {
        mBtnSubmit.setEnabled(true);
        if ("1".equals(info.getMsgtype())) {
            ToastUtils.showCenterShortToast(mContext,"分享成功");
            mEtInputShareUrl.setText("");
            getActivity().finish();
        } else if ("2".equals(info.getMsgtype())) {
            ToastUtils.showCenterShortToast(mContext,"分享失败");
        }
    }

    @Override
    public void submitFailed(String errorMsg) {
        mBtnSubmit.setEnabled(true);
        ToastUtils.showCenterShortToast(mContext,errorMsg);
    }

    @Override
    public void onDestroy() {
        mPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            String input = mEtInputShareUrl.getText().toString().trim();
            String userId = DbHelper.getUserId(mContext);
            String time = ShareUtils.getCurrentTime();
            if (TextUtils.isEmpty(input)) {
                ToastUtils.showCenterShortToast(mContext,"请输入您想分享的url地址");
            } else {
                mBtnSubmit.setEnabled(false);
                Map<String, String> params = new LinkedHashMap<>();
                params.put("hsk", Config.HSK);
                params.put("userId", userId);
                params.put("linkUrl", input);
                params.put("createDateTime", time);
                mPresenter.submitShareUrl(params);
            }

        }
    }

    @Override
    public void onStop() {
        mWebSettings.setJavaScriptEnabled(false);
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebSettings.setJavaScriptEnabled(true);
    }


}