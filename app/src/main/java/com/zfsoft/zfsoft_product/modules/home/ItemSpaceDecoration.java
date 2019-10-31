package com.zfsoft.zfsoft_product.modules.home;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


/**
 * Created by ckw
 * on 2019/1/16.
 */
public class ItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private int space;//这里给设置8dp所代表的值
    private int m;//试用新品的item数量
    private int n;//试用达人所占个数（要么是0，要么是2）

    public ItemSpaceDecoration(int space) {
        this.space = space;
        this.m = 6;
        this.n = 2;
    }

    public ItemSpaceDecoration(int space,int m){
        this.space = space;
        this.m = m;
        this.n = 2;
    }

    public ItemSpaceDecoration(int space,int m,int n){
        this.space = space;
        this.m = m;
        this.n = n;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //这里的头布局为0

        //第一个范围 [2，m+1]
        int position = parent.getChildAdapterPosition(view);

        if(m > 0){
            if(position >= 2 && position <= (m+1)){
                if(position % 3 == 0){//中间位置
                    outRect.left = space;
                    outRect.right = space;
                    outRect.bottom = 0;
                }else if((position + 1) % 3 == 0){//左边位置
                    outRect.left = space * 2;
                    outRect.right = space ;
                    outRect.bottom = 0;
                }else {//右边位置
                    outRect.left = space ;
                    outRect.right = space * 2;
                    outRect.bottom = 0;
                }

                if(m <= 3){//只有一行
                    outRect.top = 0;
                }else {
                    if(position > 4 && position <= (m + 1)){
                        outRect.top = space * 2;
                    }
                }
            }

//            //第三个数据范围
//            if(position >= (m + n + 3)){
//                int temp = (m + n + 3) % 2;//判断第一个值是偶数还是奇数
//                if(temp == 1){
//                    if(position % 2 == 1){
//                        outRect.left = space * 2 ;
//                        outRect.right = space;
//                    }else {
//                        outRect.left = space;
//                        outRect.right = space * 2;
//                    }
//                }else {
//                    if(position % 2 == 0){
//                        outRect.left = space * 2 ;
//                        outRect.right = space;
//                    }else {
//                        outRect.left = space;
//                        outRect.right = space * 2;
//                    }
//                }
//
//                if(position >= (m + n + 5)){
//                    outRect.top = space * 2;
//                }
//            }




        }


    }
}