package com.zfsoft.zfsoft_product.modules.login;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.modules.login.validation.ValidationContract;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class LoginContract {
    public interface View extends BaseView {
        void loginByPasswordSuccess(LoginBean loginBean);
        void loginByPasswordFailure(String errorMsg);
        void loginByPlatformSuccess(LoginBean loginBean);
        void loginByPlatformFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View> {
        void loginByPassword(String hsk,String userName,String password);
        void loginByThirdPlatform(String hsk,String requestType,String code,
                                  String openId,String userName,String expiredTime,
                                  String type,String avatar);
    }
}