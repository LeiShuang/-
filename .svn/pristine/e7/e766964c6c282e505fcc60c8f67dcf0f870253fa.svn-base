package com.zfsoft.zfsoft_product.base;

import android.app.Activity;
import android.content.Context;

import com.mob.MobSDK;
import com.vondear.rxtool.RxTool;
import com.zfsoft.zfsoft_product.di.AppComponent;
import com.zfsoft.zfsoft_product.di.DaggerAppComponent;
import com.zfsoft.zfsoft_product.utils.Utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * 创建日期：2018/12/24 on 10:08
 * 描述:程序入口
 * 作者:Ls
 */
public class BaseApplication extends DaggerApplication{
    private static BaseApplication mInstance;
    private static List<Activity> activityList = new LinkedList<Activity>();
    private static AppComponent mAppComponent;
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        mAppComponent = DaggerAppComponent.builder().application(this).build();
        return mAppComponent;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public void exitAllActivity(){
        for(Activity activity:activityList) {
            activity.finish();
        }
    }
    /**
     * 获取AppComponent实例
     * */
    public static  AppComponent getAppComponent(){
        return mAppComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //shareSDK分享初始化
        MobSDK.init(this);
     //   initWeChat();
        Utils.init(this);

        //RxTool初始化
        RxTool.init(this);
        mInstance = this;
    }

    private void initWeChat() {
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("Id","1");
        hashMap.put("SortId","1");
        hashMap.put("AppId","wxadc2f39cdfc88fe7");
        hashMap.put("AppSecret","e4e16c5ce5acfa9abf480a102de94d5e");
        hashMap.put("BypassApproval","false");
        hashMap.put("UserName","gh_afb25ac019c9");
        hashMap.put("Path","pages/index/index.html?id=1");
//        hashMap.put("ShareByAppClient","true");
        hashMap.put("Enable","true");
        ShareSDK.setPlatformDevInfo(Wechat.NAME,hashMap);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
