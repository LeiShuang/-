package com.zfsoft.zfsoft_product.modules.personal.my_platform;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.PlatformBean;
import com.zfsoft.zfsoft_product.entity.RedBookBean;
import com.zfsoft.zfsoft_product.entity.SignBean;

/**
 * Created by ckw
 * on 2019/3/15.
 */
public class MyPlatformContract {
    public interface View extends BaseView{
        void getMyPlatformInfoSuccess(RedBookBean redBookBean);
        void getMyPlatformInfoFailure(String errorMsg);
        void saveMyPlatformInfoSuccess(SignBean signBean);
        void saveMyPlatformInfoFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void saveMyPlatformInfo(String hsk,String userId,String redName,String redAccount,String redHome,String redFans);
        void getMyPlatformInfo(String hsk,String userId);
    }
}
