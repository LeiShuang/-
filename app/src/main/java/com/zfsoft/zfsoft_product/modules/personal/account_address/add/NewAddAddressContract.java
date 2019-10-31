package com.zfsoft.zfsoft_product.modules.personal.account_address.add;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.AddressBean;
import com.zfsoft.zfsoft_product.entity.SignBean;

import java.util.List;
import java.util.Map;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class NewAddAddressContract {
    public interface View extends BaseView{
        void addMyAddressSuccess(SignBean msg);
        void addMyAddressFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void addMyAddress(Map<String,String>map);
    }
}
