package com.zfsoft.zfsoft_product.modules.login.third_phone;

import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/3/19.
 */
public class ThirdPhonePresenter implements ThirdPhoneContract.Presenter {

    private HttpManager mHttpManager;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private ThirdPhoneContract.View mView;

    @Inject
    public ThirdPhonePresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getSmsCode(String hsk, String phone, String userId, String type) {
        mHttpManager.request(mApiService.getSmsCode(hsk, phone, userId, type), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.getSmsCodeSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getSmsCodeFailure(errorMsg);
                    }
                });
    }

    @Override
    public void loginByThirdPhone(String hsk, String openId, String userName, String openType, String requestType, String code, String telnumber, String msgCode, String avatar) {
        mHttpManager.request(mApiService.thirdLogin(hsk, openId, userName, openType, requestType, code, telnumber, msgCode, avatar), mCompositeDisposable,
                mView, new CallBackListener<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean data) {
                        mView.loginByThirdPhoneSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loginByThirdPhoneFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(ThirdPhoneContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
