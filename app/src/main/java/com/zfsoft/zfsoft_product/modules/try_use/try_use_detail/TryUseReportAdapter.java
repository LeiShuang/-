package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;


import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.imagepicker.loader.ImageLoader;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/12 on 15:12
 * 描述:试用报告列表适配器
 * 作者:Ls
 */
public class TryUseReportAdapter extends BaseQuickAdapter<ReportInfo, BaseViewHolder> {
    public TryUseReportAdapter() {
        super(R.layout.try_use_report_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportInfo item) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)helper.getView(R.id.ic_use_report).getLayoutParams();
        if (item.getWidth() != null && item.getHeight() != null){
            //得到高度 除以 宽度的 值
            double scaleNumber = Integer.valueOf(item.getHeight()) /(double)Integer.valueOf(item.getWidth());
            //图片的宽度 = 屏幕宽度 - 瀑布流左边的margin - 右边的margin  - 中间的间距
            double picWidth = (double)(ScreenUtils.getScreenWidth(mContext) - SizeUtils.dp2px(8,mContext) * 2 * 3) / 2 ;
            //图片高度  = 宽度  * 原始图片高宽比;
            int  picHidth = (int)(picWidth * scaleNumber);

            layoutParams.width = (int) picWidth;
            layoutParams.height = picHidth;
            helper.getView(R.id.ic_use_report).setLayoutParams(layoutParams);
        }else {
            if (helper.getLayoutPosition() % 2  == 0){
                layoutParams.height = ScreenUtils.getScreenHeight(mContext) / 3;
                helper.getView(R.id.ic_use_report).setLayoutParams(layoutParams);
            }else {
                layoutParams.height = (ScreenUtils.getScreenHeight(mContext) / 5) * 2;
                helper.getView(R.id.ic_use_report).setLayoutParams(layoutParams);
            }
        }


        Glide.with(mContext).load(item.getProductPicUrl()).placeholder(R.mipmap.icon_default_banner).
                error(R.mipmap.icon_default_banner).
                into((ImageView) helper.getView(R.id.ic_use_report));
        Glide.with(mContext).load(item.getTalenturl()).placeholder(R.mipmap.icon_default_head).
                error(R.mipmap.icon_default_head).
                into((CircleImageView) helper.getView(R.id.tv_report_pic));
        helper.setText(R.id.tv_use_report_title, item.getTestreporttitle());
        helper.setText(R.id.tv_user_report_name, item.getTalentname());
        helper.setText(R.id.tv_star_number, String.valueOf(item.getLikesum()));
    }
}

