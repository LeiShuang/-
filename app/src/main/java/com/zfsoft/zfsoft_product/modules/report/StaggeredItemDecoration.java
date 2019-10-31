package com.zfsoft.zfsoft_product.modules.report;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by ckw
 * on 2019/2/11.
 * 适配瀑布流
 */
public class StaggeredItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public StaggeredItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        // 获取item在span中的下标
        int spanIndex = params.getSpanIndex();//只有 0 或者 1

        // 左边
        if (spanIndex % 2 == 0) {
            outRect.left = space * 2;
            outRect.right = space;
        } else {
            // 右边，item为奇数位，设置其左间隔为8dp
            outRect.left = space;
            outRect.right = space * 2;
        }

        if(parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1){
            outRect.top = space * 2;
        }else {
            outRect.top = space;
        }

        outRect.bottom = space;

    }

}
