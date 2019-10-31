package com.zfsoft.zfsoft_product.modules.try_use.share_redbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

/**
 * 创建日期：2019/2/26 on 17:34
 * 描述:小红书分享Activity
 * 作者:Ls
 */
public class ShareRedBookActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    ShareRedBookFragment mFragment;
    private RelativeLayout mIvBack;
    private TextView tvGo;
    //小红书包名
    public static final String APP_PACKAGE_NAME = "com.xingin.xhs";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_red_book;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mIvBack = (RelativeLayout)findViewById(R.id.rl_share_back);
        tvGo = (TextView)findViewById(R.id.tv_go_red_book);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(this);
        tvGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int key =v.getId();
        switch (key){
            case R.id.rl_share_back:
                finish();
                break;
            case R.id.tv_go_red_book:
                launchRedBook(v);
                break;
                default:
                    break;
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true,0.2f).init();
    }

    /**
     * 跳转到小红书
     * */
    private void launchRedBook(View v) {
        if (isAppInstalled(v.getContext(),APP_PACKAGE_NAME)){
            //如果有根据包名跳转
            v.getContext().startActivity(v.getContext().getPackageManager().getLaunchIntentForPackage(APP_PACKAGE_NAME));
        }else{
            //如果没有，走进入系统商店找到这款APP，提示你去下载这款APP的程序
            goToDownMarket(v.getContext(), APP_PACKAGE_NAME);
        }
    }
    /**
     * 跳转到下载市场
     * */
    private void goToDownMarket(Context context, String packageName) {
        ToastUtils.showCenterShortToast(this,"暂未下载小红书，请先下载");
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (Exception e) {

        }
    }
    /**
     * 是否已安装
     * */
    private boolean isAppInstalled(Context context, String appPackageName) {
        try {
            context.getPackageManager().getPackageInfo(appPackageName,0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}