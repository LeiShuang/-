package com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;

import java.util.List;

/**
 * 创建日期：2019/1/5 on 14:09
 * 描述:
 * 作者:Ls
 */
public class ChildFragmentAdapter extends BaseQuickAdapter<ThingsInfoEntity,BaseViewHolder> {
    public ChildFragmentAdapter(@LayoutRes int layoutResId, @Nullable List<ThingsInfoEntity> data) {
        super(R.layout.item_child_view,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThingsInfoEntity item) {
        helper.setText(R.id.tv_child_title,item.getTitle());
        helper.setText(R.id.tv_child_size,item.getSize());
        helper.setText(R.id.tv_child_numer,item.getNumber());
        helper.setText(R.id.tv_child_price,item.getPrice());
        helper.setText(R.id.tv_child_fire,item.getFireNumber());
    }
}