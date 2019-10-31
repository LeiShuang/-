package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.vondear.rxtool.RxActivityTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.entity.ProductInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;
import com.zfsoft.zfsoft_product.modules.login.LoginActivity;
import com.zfsoft.zfsoft_product.modules.login.info.SetInfoActivity;
import com.zfsoft.zfsoft_product.modules.personal.account_address.AccountAddressActivity;
import com.zfsoft.zfsoft_product.modules.try_use.share_redbook.ShareListenerCallBack;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.GlideImageLoader;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;
import com.zfsoft.zfsoft_product.utils.ShareUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;
import com.zfsoft.zfsoft_product.widget.X5WebView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 创建日期：2019/1/12 on 14:23
 * 描述:产品详情fragment
 * 作者:Ls
 */
@ActivityScoped
public class ProductDetailFragment extends BaseFragment implements View.OnClickListener, TryUseDetailContract.UseDetailView, ShareListenerCallBack, EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_PERMISSION_SAVE_LOCAL = 3;
    @BindView(R.id.report_detail_banner)
    Banner mReportDetailBanner;
    @BindView(R.id.tv_use_title)
    TextView mTvUseTitle;
    @BindView(R.id.tv_thing_size)
    AppCompatTextView mTvThingSize;
    @BindView(R.id.tv_thing_price)
    AppCompatTextView mTvThingPrice;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_person_number)
    TextView mTvPersonNumber;
    @BindView(R.id.tv_try_use_detail_share)
    TextView mTvTryUseDetailShare;
    @BindView(R.id.tv_try_sue_detail_get)
    TextView mTvTrySueDetailGet;
    @BindView(R.id.iv_product_like)
    ImageView mIvProductLike;
    @BindView(R.id.tv_simple_title)
    TextView mTvSimpleTitle;
    @BindView(R.id.rl_share_gift)
    RelativeLayout mRlShareGift;
    @BindView(R.id.view_divider)
    View mViewDivider;
    @BindView(R.id.scroll)
    NestedScrollView mScroll;
    @BindView(R.id.count_down_time)
    CountdownView mCountDownTime;
    @BindView(R.id.super_player)
    StandardGSYVideoPlayer mSuperPlayer;
    @BindView(R.id.tv_nointroduction)
    TextView mTvNointroduction;
    @BindView(R.id.ll_webview)
    LinearLayout mLlWebview;
    @BindView(R.id.webview)
    X5WebView mWebview;
    @BindView(R.id.web_progress_bar)
    ProgressBar mWebProgressBar;


    private int mCollectType = 1;
    private ProductInfo mInfo;
    private String mThingsId;
    private OrientationUtils mOrientationUtils;
    private String mRealMp4Url;
    private String mGetType;
    private boolean mHasLogin;
    private String mThingsTitle;
    private List<String> mImages;

    @Inject
    public ProductDetailFragment() {

    }

    @Inject
    TryUseDetailPresenter mPresenter;

    public static ProductDetailFragment newInstance(String index) {
        Bundle args = new Bundle();
        args.putString("thingsId", index);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_producet_detail;
    }

    @Override
    protected void initVariables() {
        mHasLogin = DbHelper.checkUserIsLogin(mContext);
    }


    @Override
    protected void handleBundle(Bundle bundle) {
        mThingsId = bundle.getString("thingsId");
    }

    @Override
    protected void operateViews(View view) {
        mPresenter.getProductsDetails(Config.HSK, mThingsId, DbHelper.getUserId(mContext));
        initProgressBar();
        initWebView();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup.LayoutParams bannerParams = mReportDetailBanner.getLayoutParams();
        bannerParams.height = ScreenUtils.getScreenWidth(mContext);
        bannerParams.width = ScreenUtils.getScreenWidth(mContext);
        mReportDetailBanner.setLayoutParams(bannerParams);
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
                                  }
        );
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


    @Override
    public void shareSuccessCallBack(String thingsId, String type, String mShareType, String time, String originalUserId, String isCopy, String realUrl) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", DbHelper.getUserId(mContext));
        map.put("relevantId", thingsId);
        map.put("type", type);
        map.put("shareSource", "1");
        map.put("sharePurpose", mShareType);
        map.put("shareDateTime", time);
        map.put("originalUserId", originalUserId);
        map.put("isCopy", isCopy);
        map.put("allUrl", realUrl);
        mPresenter.submitShareUrl(map);
    }

    @Override
    public void onStart() {
        super.onStart();
        mReportDetailBanner.startAutoPlay();

    }

    @Override
    public void onStop() {
        super.onStop();
        mReportDetailBanner.stopAutoPlay();
    }

    @Override
    protected void initListener() {
        mRlShareGift.setOnClickListener(this);
        mTvTrySueDetailGet.setOnClickListener(this);
        mIvProductLike.setOnClickListener(this);
        mCountDownTime.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                mTvTrySueDetailGet.setText("试用已结束!!!");
                mTvTrySueDetailGet.setTextColor(getResources().getColor(R.color.white));
                mTvTrySueDetailGet.setBackgroundResource(R.color.grey);
                mTvTrySueDetailGet.setEnabled(false);
            }
        });

        mWebview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final WebView.HitTestResult hitTestResult = mWebview.getHitTestResult();
                // 如果是图片类型或者是带有图片链接的类型
                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                        hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    // 弹出保存图片的对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("提示");
                    builder.setMessage("保存图片到本地");
                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            picUrl = hitTestResult.getExtra();//获取图片链接
                            if (checkPermission(mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        url2BitMap(picUrl);
                                    }
                                }).start();
                            } else {
                                EasyPermissions.requestPermissions(getActivity(), getContext().getResources().getString(R.string.request_permissions_write_pic), REQUEST_PERMISSION_SAVE_LOCAL, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            }


                            //保存图片到相册，耗时操作，开启子线程
                          /*  new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    url2BitMap(picUrl);
                                }
                            }).start();*/

                            // new SaveImage().execute();
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean checkPermission(Context context, String[] perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 获取保存到本地权限成功
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case REQUEST_PERMISSION_SAVE_LOCAL:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url2BitMap(picUrl);
                    }
                }).start();
                break;
            default:
                break;
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            createAppSettingDialog();
        }
    }

    private AppSettingsDialog dialog = null;

    /**
     * 显示app权限设置页面
     */
    private void createAppSettingDialog() {

        if (dialog == null) {
            dialog = new AppSettingsDialog
                    .Builder(this)
                    .setTitle(R.string.request_permissions)
                    .setRationale(R.string.permissions_rationale)
                    .setPositiveButton(R.string.Ok)
                    .setNegativeButton(R.string.cancel)
                    .build();
        }
        dialog.show();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        if (key == R.id.rl_share_gift) {
            if (mHasLogin) {
                //分享得积分
                ShareUtils.setShareInfo(mContext, mThingsTitle, mThingsId, "1", "", "", mImages.get(0), this);
            } else {
                //未登录
                ToastUtils.showCenterToast(getContext(), "请先登录");
                RxActivityTool.skipActivity(mContext, LoginActivity.class);
            }


        } else if (key == R.id.tv_try_sue_detail_get) {
            if (mHasLogin) {
                if ("2".equals(mGetType) || "-1".equals(mGetType)) {
                    //立即申请
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("是否申请此商品?")
                            .setPositiveButton("立即申请", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mPresenter.tryUseProDuct(Config.HSK, mThingsId, DbHelper.getUserId(mContext));
                                }
                            }).setNegativeButton("取消申请", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtils.showCenterToast(getContext(), getString(R.string.cancel_get));
                        }
                    });
                    builder.show();
                } else {
                    ToastUtils.showCenterToast(getContext(), "请勿重复申请");
                }

            } else {
                //未登录
                ToastUtils.showCenterToast(getContext(), "请先登录");
                RxActivityTool.skipActivity(mContext, LoginActivity.class);
            }

        } else if (key == R.id.iv_product_like) {
            if (mHasLogin) {
                mPresenter.collectProduct(Config.HSK, mThingsId, DbHelper.getUserId(getContext()), mCollectType);
            } else {
                //未登录
                ToastUtils.showCenterToast(getContext(), "请先登录");
                RxActivityTool.skipActivity(mContext, LoginActivity.class);
            }

        }
    }


    @Override
    public void getInfoSuccess(ThingsInfoEntity info) {
        if (info != null) {
            mImages = new ArrayList<>();
            for (int i = 0; i < info.getCommodityurls().size(); i++) {
                mImages.add(info.getCommodityurls().get(i).getCommodityurl());
            }
            String productIntroductionUrl = info.getCommodityintroduction();
            if (TextUtils.isEmpty(productIntroductionUrl)) {
                mTvNointroduction.setVisibility(View.VISIBLE);
                mLlWebview.setVisibility(View.GONE);
                mTvNointroduction.setText("暂无简介");

            } else {
                mTvNointroduction.setVisibility(View.GONE);
                mLlWebview.setVisibility(View.VISIBLE);
                mWebview.setVisibility(View.VISIBLE);

                ViewGroup.LayoutParams params = mWebview.getLayoutParams();
                params.width = ScreenUtils.getScreenWidth(mContext);
                params.height = mWebview.getHeight() - mScroll.getHeight();
                mWebview.setLayoutParams(params);
                mWebview.loadDataWithBaseURL(null, productIntroductionUrl, "text/html", "utf-8", null);
            }
            mReportDetailBanner.setImages(mImages)
                    .setImageLoader(new GlideImageLoader())
                    .setBannerStyle(BannerConfig.NUM_INDICATOR)
                    .setDelayTime(5000)
                    .start();

            if (info.getHasstar().equals("1")) {
                mCollectType = 2;
                mIvProductLike.setImageResource(R.mipmap.ico_report_detail_liked);
            }
            mThingsTitle = info.getCommoditytitle();
            mTvUseTitle.setText(mThingsTitle);
            //"数量" + (TextUtils.isEmpty(info.getCommodityparameters())? "未知" :(
            mTvThingSize.setText(info.getCommodityparameters());

            mTvThingPrice.setText("售价: " + (TextUtils.isEmpty(info.getCommodityprice()) ? "暂无" : (info.getCommodityprice() + "元")));
            int second = info.getCommoditytime();
            if (!TextUtils.isEmpty(String.valueOf(second))) {
                mCountDownTime.start(Long.valueOf(String.valueOf(second) + "000"));
            }
            mTvNumber.setText("数量:" + (TextUtils.isEmpty(String.valueOf(info.getCommoditysum())) ? "未知" : String.valueOf(info.getCommoditysum())));
            mTvPersonNumber.setText("共" + String.valueOf(info.getCommodityapplicationsum()) + "人申请");
            mGetType = info.getApplytype();
            if ("0".equals(mGetType)) {
                //申请成功
                mTvTrySueDetailGet.setText("申请中");
                mTvTrySueDetailGet.setBackgroundResource(R.color.lightgray);
                mTvTrySueDetailGet.setEnabled(false);
            } else if ("1".equals(mGetType)) {
                mTvTrySueDetailGet.setText("申请成功");
                mTvTrySueDetailGet.setBackgroundResource(R.color.lightgray);
                mTvTrySueDetailGet.setEnabled(false);
            } else if ("2".equals(mGetType) || "-1".equals(mGetType)) {
                mTvTrySueDetailGet.setText("立即申请");
            } else if ("5".equals(mGetType)) {
                mTvTrySueDetailGet.setText("即将开始");
                mTvTrySueDetailGet.setBackgroundResource(R.color.lightgray);
                mTvTrySueDetailGet.setEnabled(false);
            } else if ("6".equals(mGetType)) {
                mTvTrySueDetailGet.setText("活动已结束");
                mTvTrySueDetailGet.setBackgroundResource(R.color.lightgray);
                mTvTrySueDetailGet.setEnabled(false);
            }

            mRealMp4Url = info.getCommoditydetailsURL();
            if (TextUtils.isEmpty(mRealMp4Url)) {
                mRealMp4Url = "http://200024424.vod.myqcloud.com/200024424_709ae516bdf811e6ad39991f76a4df69.f20.mp4";
            }

            mSuperPlayer.setUp(mRealMp4Url, true, "默认视频");
            mOrientationUtils = new OrientationUtils(getActivity(), mSuperPlayer);
            //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
            mSuperPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrientationUtils.resolveByClick();
                }
            });
            mSuperPlayer.setNeedShowWifiTip(true);
        }
    }

    @Override
    public void getInfoFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), errorMsg);
    }

    @Override
    public void collectSuccess(SignBean info) {
        String collectType = info.getMsgtype();
        if ("1".equals(collectType)) {
            if (mCollectType == 1) {
                ToastUtils.showCenterToast(getContext(), "收藏成功");
                mIvProductLike.setImageResource(R.mipmap.ico_report_detail_liked);
                mCollectType = 2;

            } else {
                ToastUtils.showCenterToast(getContext(), "取消收藏成功");
                mIvProductLike.setImageResource(R.mipmap.ico_report_like);
                mCollectType = 1;
            }

        } else if ("2".equals(collectType)) {

            if (mCollectType == 1) {
                ToastUtils.showCenterToast(getContext(), "收藏失败");
            } else {
                ToastUtils.showCenterToast(getContext(), "取消收藏失败");
            }
        } else if ("3".equals(collectType)) {
            if (mCollectType == 1) {
                mIvProductLike.setImageResource(R.mipmap.ico_report_detail_liked);
                ToastUtils.showCenterToast(getContext(), "已收藏");
            } else {
                mIvProductLike.setImageResource(R.mipmap.ico_report_like);
                ToastUtils.showCenterToast(getContext(), "已取消收藏");
            }
        }
    }

    @Override
    public void collectFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "收藏失败");
    }

    @Override
    public void TryUseSuccess(SignBean info) {
        String tryuseType = info.getMsgtype();
        if ("1".equals(tryuseType)) {
            mTvTrySueDetailGet.setText("申请中");
            mTvTrySueDetailGet.setBackgroundResource(R.color.lightgray);
            ToastUtils.showCenterToast(getContext(), "提交申请成功");

        } else if ("2".equals(tryuseType)) {
            ToastUtils.showCenterToast(getContext(), "提交申请失败");
        } else if ("3".equals(tryuseType)) {

            ToastUtils.showCenterToast(getContext(), "请勿重复申请!");
        } else if ("4".equals(tryuseType)) {
            ToastUtils.showCenterToast(getContext(), "请先填写个人信息");
            mContext.startActivity(new Intent(getActivity(), SetInfoActivity.class));
            getActivity().finish();
        } else if ("5".equals(tryuseType)) {
            ToastUtils.showCenterToast(getContext(), "请先完善收货地址");
            mContext.startActivity(new Intent(getActivity(), AccountAddressActivity.class));
            getActivity().finish();
        } else {
            mTvTrySueDetailGet.setText("等待审核");
            mTvTrySueDetailGet.setBackgroundResource(R.color.lightgray);
            ToastUtils.showCenterToast(getContext(), "等待审核");
        }
    }

    @Override
    public void TryUseFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), errorMsg);
    }

    /**
     * 保存url成功
     */
    @Override
    public void submitShareUrlSuccess(SignBean info) {
        if (info != null) {
            if ("1".equals(info.getMsgtype())) {
                ToastUtils.showCenterShortToast(mContext, "分享保存成功");
            } else if ("2".equals(info.getMsgtype())) {
                ToastUtils.showCenterShortToast(mContext, "分享保存失败");
            }
        }

    }

    /**
     * 保存url失败
     */
    @Override
    public void submitShareUrlFailed(String errorMsg) {
        ToastUtils.showCenterShortToast(mContext, "获取积分失败");
    }

    @Override
    public void onResume() {
        mSuperPlayer.onVideoResume();
        super.onResume();

    }

    @Override
    public void onPause() {
        mSuperPlayer.onVideoPause();
        super.onPause();
    }


    @Override
    public void onDestroy() {
        mSuperPlayer.release();
        if (mOrientationUtils != null) {
            mOrientationUtils.releaseListener();
        }
        super.onDestroy();
        mPresenter.dropView();
    }


    /**
     * 保存图片到本地操作
     */
    private String picUrl;

    /***
     * url转BitMap格式
     * */
    private void url2BitMap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                save2Album(bm);
            }

        } catch (Exception e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });

            e.printStackTrace();
        }
    }

    //保存到相册
    private void save2Album(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "lingtuyi");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String[] str = picUrl.split("/");
        String fileName = str[str.length - 1];
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            onSaveSuccess(file);
        } catch (IOException e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
