package com.zfsoft.zfsoft_product.modules.personal.accout_safe;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.ThirdBindBean;

import java.util.List;

/**
 * Created by ckw
 * on 2019/3/20.
 */
public class AccountSafeContract {

    public interface View extends BaseView{
        void getThirdBindStateSuccess(List<ThirdBindBean> list);
        void getThirdBindStateFailure(String errorMsg);
        void unbindThirdPlatformSuccess(SignBean signBean);
        void unbindThirdPlatformFailure(String errorMsg);
        void bindThirdPlatformSuccess(SignBean signBean);
        void bindThirdPlatformFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void getThirdBindState(String hsk,String userId);
        void unbindThirdPlatform(String hsk,String userId,String openId);
        void bindThirdPlatform(String hsk,String userId,String requestType,String code,
                               String openId,String userName,String openType,String avatar);
    }
}
