package com.zfsoft.zfsoft_product.modules.personal.integral_mall;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vondear.rxtool.RxLogTool;

/**
 * Created by ckw
 * on 2019/1/22.
 */
public class IntegralItemDecoration extends RecyclerView.ItemDecoration {
    private int size;

    public IntegralItemDecoration(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        if(position >= 1){
            if (position % 2 == 1 ){
                //左边的item
                outRect.left = size * 2;
                outRect.right = size;
                outRect.bottom = 0;
                outRect.top = size * 2;
            }

            if (position % 2 == 0){
                outRect.left = size ;
                outRect.right = size * 2;
                outRect.bottom = 0;
                outRect.top = size * 2;
            }
        }





    }
}
