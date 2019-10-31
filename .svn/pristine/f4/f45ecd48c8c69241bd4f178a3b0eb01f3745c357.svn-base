package com.zfsoft.zfsoft_product.modules.personal.integral_mall.integral_detail;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.IntegralDetailBean;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class IntegralDetailAdapter extends BaseQuickAdapter<IntegralDetailBean,BaseViewHolder> {
    public IntegralDetailAdapter() {
        super(R.layout.item_integral_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralDetailBean item) {
        helper.setText(R.id.tv_detail_title,item.getTitle());
        helper.setText(R.id.tv_detail_time,item.getTime());
        helper.setText(R.id.tv_detail_num,item.getIntegralNum());
    }
}
