package com.zfsoft.zfsoft_product.modules.home;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;

/**
 * 创建日期：2019/1/3 on 15:18
 * 描述:首页Adapter
 * 作者:Ls
 */
public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeAdapter() {
        super(R.layout.item_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text,item);
    }
}
