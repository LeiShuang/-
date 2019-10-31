package com.zfsoft.zfsoft_product.utils;

import android.content.Context;

/**
 * @author wesley
 * @date: 2017/3/15
 * @Description: 尺寸相关的工具类
 */

public class SizeUtils {

    private static final String TAG = "SizeUtils";

    private SizeUtils() {

    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(float dpValue, Context context) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(float pxValue, Context context) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal)
    {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
