package com.zfsoft.zfsoft_product.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ckw
 * on 2019/2/11.
 */
public class ToastUtils {
    public static void showCenterToast(Context context,String msg){
        Toast toast = Toast.makeText(context, msg , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showCenterShortToast(Context context,String msg){
        Toast toast = Toast.makeText(context, msg , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}