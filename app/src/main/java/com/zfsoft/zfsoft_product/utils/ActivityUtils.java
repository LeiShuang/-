package com.zfsoft.zfsoft_product.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vondear.rxtool.RxLogTool;

/**
 * @author wesley
 * @date: 2017/3/13
 * @Description: Fragment添加到Activity上的工具类
 */
public class ActivityUtils {

    private static final String TAG = "ActivityUtils";

    private ActivityUtils() {

    }

    /**
     * 把Fragment添加到Activity上
     *
     * @param manager         FragmentManager的实例
     * @param fragment        Fragment的实例
     * @param containerViewId 布局id
     */
    public static void addFragmentToActivity(FragmentManager manager, Fragment fragment,
                                             int containerViewId) {

        if (manager == null || fragment == null || fragment.isAdded()) {
            RxLogTool.e(TAG, " addFragmentToActivity " + " manager = " + manager + " " +
                    "fragment = " + fragment);
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }

    /**
     * 把Fragment添加到Activity上
     *
     * @param manager         FragmentManager的实例
     * @param fragment        Fragment的实例
     * @param containerViewId 布局id
     * @param tag             索引
     */
    public static void addFragmentToActivityWithTag(FragmentManager manager, Fragment fragment,
                                                    int containerViewId, String tag) {
        if (manager == null || fragment == null || tag == null || fragment.isAdded()) {
            RxLogTool.e(TAG, " addFragmentToActivity " + " manager = " + manager + " " +
                    "fragment = " + fragment + " tag = " + tag);
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerViewId, fragment, tag);
        transaction.commit();
    }

}