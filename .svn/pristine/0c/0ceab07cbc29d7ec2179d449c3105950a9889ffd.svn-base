package com.zfsoft.zfsoft_product.modules.personal;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.UserBean;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class PersonalContract {
    public interface View extends BaseView{
        //签到
        void getSignResultSuccess(SignBean signBean);
        void getSignResultFailure(String errorMsg);

        void getUserInfoSuccess(UserBean userBean);
        void getUserInfoFailure(String errorMsg);

    }
    public interface Presenter extends BasePresenter<View>{
        void startSign(String hsk,String userId);
        void getUserInfo(String hsk,String userId);
    }
}
