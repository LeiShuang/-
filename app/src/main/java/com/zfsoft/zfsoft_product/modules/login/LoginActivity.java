package com.zfsoft.zfsoft_product.modules.login;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/12.
 */
public class LoginActivity extends BaseActivity{

    @Inject
    NewLoginFragment mLoginFragment;

    private FragmentManager manager;

    private int mFrom;//1来自退出登录


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(manager,mLoginFragment,R.id.fragment_login_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mFrom = bundle.getInt("from",0);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.login_bar_color).init();
    }

    @Override
    public void onBackPressed() {
        if(mFrom == 1){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else {
            super.onBackPressed();
        }
    }


    /*  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        exit();
    }

    private static boolean isExit = false;  // 标识是否退出

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;

        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);  // 利用handler延迟发送更改状态信息
            this.finish();
        } else {
            // 1\. 通过Context获取ActivityManager
            ActivityManager activityManager = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

            // 2\. 通过ActivityManager获取任务栈
            List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();

            // 3\. 逐个关闭Activity
            for (ActivityManager.AppTask appTask : appTaskList) {
                appTask.finishAndRemoveTask();

                System.exit(0);
            }
        }
    }*/
}
