package com.zfsoft.zfsoft_product.modules.personal.accout_safe.modify;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.SignBean;

/**
 * Created by ckw
 * on 2019/1/29.
 */
public class ModifyContract {
    public interface View extends BaseView{
        void modifySuccess(SignBean signBean);
        void modifyFailure(String errorMsg);
        void getSmsCodeSuccess(SignBean signBean);
        void getSmsCodeFailure(String errorMsg);
        void confirmSmsCodeSuccess(SignBean signBean);
        void confirmSmsCodeFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void forChangePassword(String hsk,String userId,String phone,String code);
        void forChangePhoneNum(String hsk,String userId,String phone,String code);
        void getSmsCode(String hsk,String phone,String userId,String type);
        void confirmSmsCode(String hsk,String phone,String userId,String type,String code);
    }
}
