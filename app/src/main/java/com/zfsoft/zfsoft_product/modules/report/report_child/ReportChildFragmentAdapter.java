package com.zfsoft.zfsoft_product.modules.report.report_child;


import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.liteav.demo.common.utils.DensityUtil;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/15 on 16:42
 * 描述:报告模块下的fragment适配器
 * 作者:Ls
 */
public class ReportChildFragmentAdapter extends BaseQuickAdapter<TestReportBean, BaseViewHolder> {
    public ReportChildFragmentAdapter() {
        super(R.layout.item_report_child_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestReportBean item) {
        if (item != null) {
            ViewGroup.LayoutParams layoutParams = helper.getView(R.id.ic_report_show_pic).getLayoutParams();
            if (!TextUtils.isEmpty(item.getWidth())  && !TextUtils.isEmpty(item.getHeight())){
                //得到高度 除以 宽度的 值
                double scaleNumber = Integer.valueOf(item.getHeight()) /(double)Integer.valueOf(item.getWidth());
                //图片的宽度 = 屏幕宽度 - 瀑布流左边的margin - 右边的margin  - 中间的间距
                double picWidth = (double)(ScreenUtils.getScreenWidth(mContext) - SizeUtils.dp2px(8,mContext) * 2 * 3) / 2 ;
                //图片高度  = 宽度  * 原始图片高宽比;
                int  picHidth = (int)(picWidth * scaleNumber);

                layoutParams.width = (int) picWidth;
                layoutParams.height = picHidth;
                helper.getView(R.id.ic_report_show_pic).setLayoutParams(layoutParams);
            }else{
                if (helper.getLayoutPosition() % 2  == 0){
                    layoutParams.height = DensityUtil.dip2px(mContext,150) + (int) (Math.random() * 40);
                    helper.getView(R.id.ic_report_show_pic).setLayoutParams(layoutParams);
                }else {
                    layoutParams.height =  DensityUtil.dip2px(mContext,160) + (int) (Math.random() * 55);
                    helper.getView(R.id.ic_report_show_pic).setLayoutParams(layoutParams);
                }
            }

            Glide.with(mContext)
                    .load(item.getTestreporturl())
                    .placeholder(R.mipmap.icon_default)
                    .error(R.mipmap.icon_default)
                    .centerCrop()
                    .into((ImageView) helper.getView(R.id.ic_report_show_pic));
            if (item.getLx().equals("1")){
                helper.getView(R.id.iv_video_start).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.iv_video_start).setVisibility(View.VISIBLE);
            }

            helper.setText(R.id.tv_report_title, item.getTestreporttitle());
            Glide.with(mContext)
                    .load(item.getTalenturl())
                    .placeholder(R.mipmap.icon_default_head)
                    .error(R.mipmap.icon_default_head)
                    .into((CircleImageView) helper.getView(R.id.tv_reporter_pic));

            helper.setText(R.id.tv_reporter_name, item.getTalentname());
            helper.setText(R.id.tv_star_number, item.getLikesum());
        }

    }


}
