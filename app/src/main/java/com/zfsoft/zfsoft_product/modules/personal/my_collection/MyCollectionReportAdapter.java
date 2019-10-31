package com.zfsoft.zfsoft_product.modules.personal.my_collection;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.liteav.demo.common.utils.DensityUtil;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;

/**
 * Created by ckw
 * on 2019/2/11.
 */
public class MyCollectionReportAdapter extends BaseQuickAdapter<CollectionBean,BaseViewHolder> {

    public MyCollectionReportAdapter() {
        super(R.layout.item_my_report);
    }
    @Override
    protected void convert(BaseViewHolder helper, CollectionBean item) {
        if (item != null) {
            ViewGroup.LayoutParams layoutParams = helper.getView(R.id.iv_single).getLayoutParams();
            if (helper.getLayoutPosition() % 2  == 0){
                layoutParams.height = DensityUtil.dip2px(mContext,150) + (int) (Math.random() * 40);
                helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
            }else {
                layoutParams.height =  DensityUtil.dip2px(mContext,160) + (int) (Math.random() * 55);
                helper.getView(R.id.iv_single).setLayoutParams(layoutParams);
            }
        }
        helper.setText(R.id.tv_content,item.getTestreporttitle());
        helper.setText(R.id.tv_user_name,item.getTalentname());
        helper.setText(R.id.tv_thumb_up_number,item.getLikesum());
        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_single),item.getTestreporturl());
        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.civ_user_img),item.getTalenturl());

    }
}
