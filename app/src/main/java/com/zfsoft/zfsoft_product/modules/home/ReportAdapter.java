package com.zfsoft.zfsoft_product.modules.home;


import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.liteav.demo.common.utils.DensityUtil;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;


/**
 * Created by ckw
 * on 2019/3/27.
 */
public class ReportAdapter extends BaseQuickAdapter<MultipleItem.SingleImgEntity,BaseViewHolder>{
    public ReportAdapter() {
        super(R.layout.item_image_single);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem.SingleImgEntity item) {
        helper.setText(R.id.tv_user_name,item.getName());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_user_name,item.getName());
        helper.setText(R.id.tv_thumb_up_number,item.getThumbUpNum());

        if (item != null) {
            //后台获取的图片宽高
            String width = item.getWidth();
            String height = item.getHeight();
            ViewGroup.LayoutParams layoutParams = helper.getView(R.id.iv_single).getLayoutParams();
            if(width != null && !width.equals("") && height != null && !height.equals("")){
                int originWidth = Integer.valueOf(width);
                int originHeight = Integer.valueOf(height);
                int imgWidth = (ScreenUtils.getScreenWidth(mContext) - DensityUtil.dip2px(mContext, 48)) / 2;
                int imgHeight = imgWidth * originHeight / originWidth;
                layoutParams.height = imgHeight;
                layoutParams.width = imgWidth;
                helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
            }else {
                if (helper.getLayoutPosition() % 2  == 0){
                    layoutParams.height = DensityUtil.dip2px(mContext,150) + (int) (Math.random() * 40);
                    helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
                }else {
                    layoutParams.height =  DensityUtil.dip2px(mContext,160) + (int) (Math.random() * 55);
                    helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
                }
            }

        }
        if(item.getHeadUrl() != null){
            ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_single),item.getHeadUrl());
        }

        if(item.getUserUrl() != null){
            ImageLoaderHelper.loadHeadImage(mContext,helper.getView(R.id.civ_user_img),item.getUserUrl());
        }
    }
}