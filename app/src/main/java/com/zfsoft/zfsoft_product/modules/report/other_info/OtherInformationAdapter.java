package com.zfsoft.zfsoft_product.modules.report.other_info;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/17 on 18:33
 * 描述:他人个人信息展示页面适配器
 * 作者:Ls
 */
public class OtherInformationAdapter extends BaseQuickAdapter<TestReportBean,BaseViewHolder> {
    public OtherInformationAdapter() {
        super(R.layout.item_report_child_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestReportBean item) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)helper.getView(R.id.ic_report_show_pic).getLayoutParams();
        if (helper.getLayoutPosition() % 2  == 0){
            layoutParams.height = (ScreenUtils.getScreenHeight(mContext) / 5) * 2;
            helper.getView(R.id.ic_report_show_pic).setLayoutParams(layoutParams);
        }else {
            layoutParams.height = ScreenUtils.getScreenHeight(mContext) / 3;
            helper.getView(R.id.ic_report_show_pic).setLayoutParams(layoutParams);
        }
        ImageLoaderHelper.loadHeadImage(mContext, (ImageView) helper.getView(R.id.tv_reporter_pic),item.getTalenturl());
        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.ic_report_show_pic),item.getTestreporturl());
        helper.setText(R.id.tv_report_title,item.getTestreporttitle());

        helper.setText(R.id.tv_reporter_name,item.getTalentname());
        helper.setText(R.id.tv_star_number,item.getLikesum());

    }
}