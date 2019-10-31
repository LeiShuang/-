package com.zfsoft.zfsoft_product.base;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;


import com.zfsoft.zfsoft_product.R;

import java.lang.reflect.Method;

/**
 * @author wesley
 * @date: 2017/3/27
 * @Description: 上传头像的对话框
 */

public class MineBottomSheetDialogFragment extends BaseBottomSheetDialogFragment implements
        View.OnClickListener {

    private OnViewClickListener listener;

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    private boolean isNavigationBarShow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            boolean  result  = realSize.y!=size.y;
            return realSize.y!=size.y;
        }else {
            boolean menu = ViewConfiguration.get(getActivity()).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if(menu || back) {
                return false;
            }else {
                return true;
            }
        }
    }

    /**
     * 获取屏幕尺寸，但是不包括虚拟功能高度
     *
     * @return
     */
    public int getNoHasVirtualKey() {
        int height =getActivity().getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 通过反射，获取包含虚拟键的整体屏幕高度
     *
     * @return
     */
    private int getHasVirtualKey() {
        int dpi = 0;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    private int getVirtualKeyHeight(){
        return getHasVirtualKey() - getNoHasVirtualKey();
    }

    @Override
    protected Dialog createBottomSheetDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet_mine_fragment, null);
        TextView tv_select_from_album = (TextView) view.findViewById(R.id.select_from_album);
        TextView tv_take_pictures = (TextView) view.findViewById(R.id.take_pictures);
        TextView tv_cancel = (TextView) view.findViewById(R.id.cancel);
        TextView tv_virtual = (TextView)view.findViewById(R.id.tv_virtual_item); //为了适配某些手机有虚拟按钮
        if(isNavigationBarShow()){
            tv_virtual.setVisibility(View.VISIBLE);
            tv_virtual.setHeight(getVirtualKeyHeight());
        }else {
            tv_virtual.setVisibility(View.GONE);
        }
        tv_select_from_album.setOnClickListener(this);
        tv_take_pictures.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        dialog.setContentView(view);
        return dialog;
    }

    public static MineBottomSheetDialogFragment newInstance() {
        return new MineBottomSheetDialogFragment();
    }


    public void setOnViewClickListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if (view == null || listener == null) {
            return;
        }

        int key = view.getId();
        if (key == R.id.select_from_album) {
            listener.onSelectFromAlbumClick();


        /*
         * 拍照
         */
        } else if (key == R.id.take_pictures) {
            listener.onTakePicturesClick();


        /*
         * 取消
         */
        } else if (key == R.id.cancel) {

        } else {

        }

        dismiss();
    }

    /**
     * 自定义回调接口
     */
    public interface OnViewClickListener {

        /**
         * 从相册中选取
         */
        void onSelectFromAlbumClick();

        /**
         * 拍照
         */
        void onTakePicturesClick();
    }
}
