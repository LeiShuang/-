package com.zfsoft.zfsoft_product.modules.home.notice;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.NoticeBean;
import com.zfsoft.zfsoft_product.utils.ToastUtils;
import com.zfsoft.zfsoft_product.widget.X5WebView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/4/12.
 */
public class NoticeFragment extends BaseFragment implements NoticeContract.View{

    @Inject
    public NoticeFragment(){}

    @Inject
    NoticePresenter mPresenter;

    @BindView(R.id.notice_webview)
    X5WebView mWebview;
    @BindView(R.id.web_progress_bar)
    ProgressBar mWebProgressBar;
    @BindView(R.id.tv_notice_title)
    TextView mTvTitle;
    @BindView(R.id.rl_notice_container)
    RelativeLayout mRlBack;

    private String mNoticeId;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mNoticeId = bundle.getString("noticeId");
    }

    @Override
    protected void operateViews(View view) {
        mPresenter.getNoticeData(Config.HSK,mNoticeId);
        initProgressBar();
        initWebView();
    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        if (immersionBar == null) {
            return;
        }
        immersionBar.statusBarDarkFont(true);
        immersionBar.statusBarColor(R.color.apply_color)
                .init();
    }

    @Override
    public void getNoticeDataSuccess(NoticeBean noticeBean) {
        mTvTitle.setText(noticeBean.getNoticeTitle());
        String head = "<head> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<style> p{font-size:18px;color:black;}img{max-width: 100%; width:100%; height: auto;}</style></head>";
        String resultStr = "<html>" + head + "<body>" + noticeBean.getNoticeContent() + "</body></html>";

        mWebview.loadDataWithBaseURL(null, resultStr, "text/html", "utf-8", null);
    }

    @Override
    public void getNoticeDataFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), errorMsg);
    }

    private void initProgressBar() {
        mWebProgressBar.setMax(100);
    }

    private void initWebView() {
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                int progress = mWebProgressBar.getProgress();
                if (progress == mWebProgressBar.getMax()) {
                    mWebProgressBar.setVisibility(View.GONE);
                } else {
                    mWebProgressBar.setVisibility(View.VISIBLE);
                    mWebProgressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

        });
        mWebview.setWebViewClient(new WebViewClient());


    }
}
