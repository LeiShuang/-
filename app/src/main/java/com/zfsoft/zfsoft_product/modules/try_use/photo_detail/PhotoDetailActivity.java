package com.zfsoft.zfsoft_product.modules.try_use.photo_detail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.MyUtils;
import com.zfsoft.zfsoft_product.utils.SystemUiVisibilityUtil;
import com.zfsoft.zfsoft_product.widget.PullBackLayout;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 创建日期：2019/3/7 on 16:02
 * 描述:头像详情Activity
 * 作者:Ls
 */
public class PhotoDetailActivity extends BaseActivity implements PullBackLayout.Callback {


   private PhotoView mPhotoTouchIv;
   private PullBackLayout mPullBackLayout;
   private RelativeLayout mBackground;
    private boolean mIsToolBarHidden;
    private boolean mIsStatusBarHidden;
    private ColorDrawable mColorDrawable;
    private String mUrl;
    private Toolbar mToolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).transparentStatusBar().fullScreen(true);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.toolbar);
        mBackground = findViewById(R.id.background);
        mPhotoTouchIv = findViewById(R.id.photo_touch_iv);
        mPullBackLayout = findViewById(R.id.pull_back_layout);
        setDisplayHomeAsUpEnabled(true);
        mPullBackLayout.setCallback(this);
        toolBarFadeIn();
        initBackground();
        loadPhotoIv();
        initImageView();
        setPhotoViewClickEvent();
    }

    private void setPhotoViewClickEvent() {
        mPhotoTouchIv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                hideOrShowToolbar();
                hideOrShowStatusBar();
            }
        });
    }

    private void initImageView() {
        loadPhotoIv();
    }

    private void loadPhotoIv() {
        Glide.with(this).load(mUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.icon_default)
                .crossFade().into(mPhotoTouchIv);
    }

    private void initBackground() {
        mColorDrawable = new ColorDrawable(Color.BLACK);
        MyUtils.getRootView(this).setBackgroundDrawable(mColorDrawable);
    }

    private void toolBarFadeIn() {
        mIsToolBarHidden = true;
        hideOrShowToolbar();
    }


    protected void hideOrShowToolbar() {
        mToolbar.animate()
                .alpha(mIsToolBarHidden ? 1.0f : 0.0f)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }

    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(PhotoDetailActivity.this);
        } else {
            SystemUiVisibilityUtil.exit(PhotoDetailActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mUrl = bundle.getString("photo_detail");
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPullStart() {
        toolBarFadeOut();

        mIsStatusBarHidden = true;
        hideOrShowStatusBar();
    }

    private void toolBarFadeOut() {
        mIsToolBarHidden = false;
        hideOrShowToolbar();
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        mColorDrawable.setAlpha((int) (0xff/*255*/ * (1f - progress)));
    }

    @Override
    public void onPullCancel() {
        toolBarFadeIn();
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }

    @Override
    protected void onNavigationIconClick() {
        super.onNavigationIconClick();
    }


}