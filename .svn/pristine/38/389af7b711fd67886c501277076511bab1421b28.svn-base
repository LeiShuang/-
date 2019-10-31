package com.zfsoft.zfsoft_product.modules.report.other_info;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建日期：2019/1/18 on 15:31
 * 描述:含有两个头布局时的itemDecoration
 * 作者:Ls
 */
public class TwoHeaderItemDecoration extends RecyclerView.ItemDecoration  {
    private int size;

    public TwoHeaderItemDecoration(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position > 0){
           if (position % 2 == 1){
               //左边的item,第一个头布局的 position为-1，第二个头布局为0
               outRect.left = size * 2;
               outRect.right = size ;
               outRect.bottom = 0;
               outRect.top = size * 2;
           }

           if (position % 2 == 0){
               //右边的item,第一个头布局的 position为-1，第二个头布局为0
               outRect.left =  size;
               outRect.right = size * 2;
               outRect.bottom = 0;
               outRect.top = size * 2;
           }
        }
    }
}
