package com.zfsoft.zfsoft_product.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ckw
 * on 2019/4/3.
 */
public class QuadView extends View {

    private int mWidth;
    private int mHeight;
    public QuadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
            setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0){
            mWidth = w;
            mHeight = h;
        }
    }
}
