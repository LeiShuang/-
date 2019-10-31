package com.zfsoft.zfsoft_product.modules.personal.my_report;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.MyTryReportBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyReportsAdapter extends BaseQuickAdapter<TestReportBean,BaseViewHolder> {

    public MyReportsAdapter() {
        super(R.layout.item_my_report);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestReportBean item) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)helper.getView(R.id.iv_single).getLayoutParams();
        if (helper.getLayoutPosition() % 2  == 0){
            layoutParams.height = ScreenUtils.getScreenHeight(mContext) / 3;
            helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
        }else {
            layoutParams.height = (ScreenUtils.getScreenHeight(mContext) / 5) * 2;
            helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
        }
        helper.setText(R.id.tv_content,item.getTestreporttitle());
        helper.setText(R.id.tv_user_name,item.getTalentname());
        helper.setText(R.id.tv_thumb_up_number,item.getLikesum());
        if(item.getTestreporturl() != null){
            ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_single),item.getTestreporturl());
        }
        if(item.getProductPicUrl() != null){
            ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_single),item.getProductPicUrl());
        }
        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.civ_user_img),item.getTalenturl());

        if(item.getShzt() != null && !item.getShzt().equals("")){
            //审核状态1：提交审核；2：审核通过；3：审核不通过
            helper.getView(R.id.tv_report_state).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_report_state,item.getShztmc());

        }else {
            helper.getView(R.id.tv_report_state).setVisibility(View.INVISIBLE);
        }

    }
}
