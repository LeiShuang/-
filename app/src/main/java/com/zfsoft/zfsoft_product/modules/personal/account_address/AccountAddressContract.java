package com.zfsoft.zfsoft_product.modules.personal.account_address;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.AddressBean;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class AccountAddressContract {
    public interface View extends BaseView {
        void getMyAddressListSuccess(List<AddressBean> list);
        void getMyAddressListFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View> {
        void getMyAddressList(String hsk,String userId);
    }
}
