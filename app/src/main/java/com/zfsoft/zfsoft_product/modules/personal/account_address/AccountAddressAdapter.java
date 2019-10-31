package com.zfsoft.zfsoft_product.modules.personal.account_address;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.AddressBean;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class AccountAddressAdapter  extends BaseQuickAdapter<AddressBean,BaseViewHolder>{


    public AccountAddressAdapter() {
        super(R.layout.item_account_address);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.tv_user_name,item.getXm());
        String add = item.getSf() + item.getCs() + item.getXj() + item.getXxdz();
        helper.setText(R.id.tv_address,add);
    }
}