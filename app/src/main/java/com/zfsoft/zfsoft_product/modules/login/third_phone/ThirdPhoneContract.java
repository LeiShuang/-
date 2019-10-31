package com.zfsoft.zfsoft_product.modules.login.third_phone;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;

/**
 * Created by ckw
 * on 2019/3/19.
 */
public class ThirdPhoneContract {

    public interface View extends BaseView{
        void getSmsCodeSuccess(SignBean signBean);
        void getSmsCodeFailure(String errorMsg);
        void loginByThirdPhoneSuccess(LoginBean loginBean);
        void loginByThirdPhoneFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void getSmsCode(String hsk,String phone,String userId,String type);
        void loginByThirdPhone(String hsk,String openId,String userName,String openType,String requestType,
                               String code,String telnumber,String msgCode,String avatar);
    }
}
