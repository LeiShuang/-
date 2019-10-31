package com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建日期：2019/3/19 on 18:02
 * 描述:使用recyclerView的Item分割间距
 * 作者:Ls
 */
public class TryUseChildDecoration extends RecyclerView.ItemDecoration {
    private int size;

    public TryUseChildDecoration(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
            outRect.left = size ;
            outRect.right = size;
            outRect.bottom = size * 2;
            if(position == 0){
                outRect.top = size / 4;
            }else {
                outRect.top = 0;
            }

        }
}
