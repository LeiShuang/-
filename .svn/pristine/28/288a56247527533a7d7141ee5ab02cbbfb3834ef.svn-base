package com.zfsoft.zfsoft_product.modules.report.report_child;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.vondear.rxtool.RxLogTool;

/**
 * 创建日期：2019/1/16 on 16:17
 * 描述:报告双列列表 左右距离(不含头布局的才适用)
 * 作者:Ls
 */
public class ChildItemDecoration extends RecyclerView.ItemDecoration {
    private int size;

    public ChildItemDecoration(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        if (position % 2 == 0){
            //左边的item
            outRect.left = size * 2;
            outRect.right = size;
            outRect.bottom = 0;
            outRect.top = size * 2;
        }

        if (position % 2 == 1){
            outRect.left = size ;
            outRect.right = size * 2;
            outRect.bottom = 0;
            outRect.top = size * 2;
        }



    }
}
