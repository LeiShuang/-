package com.zfsoft.zfsoft_product.modules.personal.integral_mall;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.IntegralBean;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class IntegralMallAdapter extends BaseQuickAdapter<IntegralBean,BaseViewHolder> {
    public IntegralMallAdapter() {
        super(R.layout.item_integral_mall);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralBean item) {
        helper.setText(R.id.tv_price_one,item.getIntegralPrice());
        helper.setText(R.id.tv_content,item.getIntegralTitle());
        helper.setText(R.id.tv_integral_number,item.getIntegralNeed());
        Glide.with(mContext).load(item.getIntegralUrl()).into((ImageView) helper.getView(R.id.iv_single));
    }
}
