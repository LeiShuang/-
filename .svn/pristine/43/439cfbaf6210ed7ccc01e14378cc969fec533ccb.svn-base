package com.zfsoft.zfsoft_product.modules.login.info;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class SetInfoContract {
    public interface View extends BaseView{
        void uploadInfoSuccess(SignBean signBean);
        void uploadInfoFailure(String errorMsg);
        void getUserInfoSuccess(User user);
        void getUserInfoFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void uploadPersonalInfo(Map<String, RequestBody> map, List<MultipartBody.Part> images);
        void getUserInfo(String hsk,String userId);
    }
}
