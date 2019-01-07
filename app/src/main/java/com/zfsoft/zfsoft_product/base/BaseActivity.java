package com.zfsoft.zfsoft_product.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.utils.ImmersionStatusBarUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * 创建日期：2018/12/24 on 9:36
 * 描述:基类Activity
 * 作者:Ls
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    private Toolbar mToolbar;

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    public boolean defToolBar;//默认的ToolBar
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
        mUnbinder = ButterKnife.bind(this);

        //初始化沉浸式
        if (isImmersionBarEnabled()){
            initImmersionBar();
        }
        defToolBar = true;
        setRequestedOrientation();
        setContentView(getLayoutId());
        initToolbar();
        initVariables();
        //处理从其他界面传过来的数据
        handleIntent();
        //view与数据绑定
        initView(savedInstanceState);
        setStatusBar();
        initListener();
        initAnimation();
    }

    /**
     * 设置状态栏的颜色
     */
    protected void setStatusBar() {
        if (immersionEnabled()) {
            ImmersionStatusBarUtils.initStatusBarInActivity(this, R.color.transparent,
                    R.color.transparent, mToolbar, true, 1.0f, keyBoardEnabled(), mImmersionBar);
        }
    }

    protected boolean keyBoardEnabled() {
        return false;
    }
    /**
     * 是否显示沉浸式状态栏
     *
     * @return true: 显示
     */
    protected boolean immersionEnabled() {
        return true;
    }
    //设置屏幕方向为竖屏
    protected void setRequestedOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 处理上个界面传过来的数据---所有的Intent跳转的数据都需要包装在Bundle中
     *
     * @param bundle 界面跳转时传递的数据
     */
    protected abstract void handleBundle(@NonNull Bundle bundle);
    /**
     * 初始化数据,如list，adapter
     * */
    public void initVariables(){

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        hideSoftKeyBoard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.imm = null;
        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    protected abstract void initListener();

    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();

    }

    //ToolBar相关

    //返回false的时候，就不再需要重写setToolbar方法，当需要显示toolbar的时候，返回true
//    protected abstract boolean needToolbar();
    private boolean needToolbar(){
        return true;
    }

    private void initToolbar(){
        if (defToolBar) {
            mToolbar = (Toolbar) findViewById(R.id.too_bar_id);
        }

    }

    /**
     * 设置toolbar的标题
     *
     * @param title 标题
     */
    protected void setToolbarTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * 设置toolbar的标题
     *
     * @param resId 资源id
     */
    protected void setToolbarTitle(int resId) {
        if (mToolbar != null) {
            mToolbar.setTitle(resId);
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * 设置toolbar右侧的textview
     * @param subTitle
     */
    public void setToolBarSubTitle(String subTitle){
        if(mToolbar != null){
            TextView titleSubView = findViewById(R.id.common_subtitle);
            titleSubView.setText(subTitle);
        }
    }

    public void setToolBarSubTitle(int resId){
        if(mToolbar != null){
            TextView titleSubView = findViewById(R.id.common_subtitle);
            titleSubView.setText(resId);
        }
    }

    /**
     * 自定义导航图标
     *
     * @param resId 图片的资源id
     */
    protected void setNavigationIcon(int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationIconClick();
                }
            });
        }
    }

    /**
     * 自定义导航栏图标
     *
     * @param drawable drawable对象
     */
    protected void setNavigationIcon(Drawable drawable) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(drawable);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationIconClick();
                }
            });
        }
    }



    /**
     * 设置toolbar的返回箭头是否显示
     *
     * @param enabled true:显示  false:不显示
     */
    protected void setDisplayHomeAsUpEnabled(boolean enabled) {
        if (mToolbar != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(enabled);
                if (enabled) {
                    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onNavigationIconClick();
                        }
                    });
                }
            }
        }
    }

    /**
     * toolbar左侧返回键点击
     */
    protected void onNavigationIconClick() {
        boolean animationEnabled = checkAnimationEnabled();
        if (animationEnabled) {
            onBackPressed();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //检查是否可以有动画
    private boolean checkAnimationEnabled() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animationEnabled();
    }

    //初始化动画
    private void initAnimation() {
        if (checkAnimationEnabled()) {
            setUpAnimation();
        }
    }

    /**
     * 设置需要转场动画---默认是true
     *
     * @return true: 需要 false: 不需要
     */
    protected boolean animationEnabled() {
        return true;
    }

    /**
     * 设置android 5.0的转场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setUpAnimation() {

        Transition transition_enter = new Slide(Gravity.RIGHT);
        transition_enter.setDuration(600);
        getWindow().setEnterTransition(transition_enter);


        Transition transition_return = new Fade();
        transition_return.setDuration(400);
        getWindow().setReturnTransition(transition_return);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //其他

    //跳转界面时判读Intent是否携带数据
    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                handleBundle(bundle);
            }
        }
    }


    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
}
