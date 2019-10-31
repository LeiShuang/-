package com.zfsoft.zfsoft_product.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zfsoft.zfsoft_product.R;

/**
 * 创建日期：2019/1/14 on 10:06
 * 描述:评论输入框
 * 作者:Ls
 */
public class InputDialog extends Dialog{
    public InputDialog(@NonNull Context context) {
        super(context);
        init(context);
    }
    public Context mContext;
    public View mRootView;
    public void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
        setContentView(mRootView);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);

    }
}
