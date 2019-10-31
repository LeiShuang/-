package com.zfsoft.zfsoft_product.modules.login.validation;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class ValidationContract {
    public interface View extends BaseView{
        void loginByValidateSuccess(LoginBean loginBean);
        void loginByValidateFailure(String errorMsg);
        void getSmsCodeSuccess(SignBean signBean);
        void getSmsCodeFailure(String errorMsg);
        void getRegisterStateSuccess(SignBean signBean);
        void getRegisterStateFailure(String errorMsg);
        void getRegisterSuccess(LoginBean loginBean);
        void getRegisterFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void loginByValidate(String hsk,String phone,String validateCode);
        void getSmsCode(String hsk,String phone,String userId,String type);
        void confirmRegisterState(String hsk,String phone);
        void register(String hsk,String phone,String code,String password);
    }
}
