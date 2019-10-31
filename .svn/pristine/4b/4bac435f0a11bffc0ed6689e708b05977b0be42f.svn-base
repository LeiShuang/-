package com.zfsoft.zfsoft_product.modules.personal.account_address.add;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.entity.AddressBean;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class NewAddAddressActivity extends BaseActivity {

    @Inject
    NewAddAddressFragment newAddAddressFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(manager,newAddAddressFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        AddressBean bean = bundle.getParcelable("bean");
        Bundle temp = new Bundle();
        temp.putParcelable("bean",bean);
        newAddAddressFragment.setArguments(temp);
    }

    @Override
    protected void initListener() {

    }
}
