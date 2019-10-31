package com.zfsoft.zfsoft_product.modules.login;

import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private HttpManager mHttpManager;
    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;
    private LoginContract.View mView;

    @Inject
    public LoginPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loginByPassword(String hsk, String userName, String password) {
        mHttpManager.request(mApiService.loginByPassword(hsk, userName, password), mCompositeDisposable, mView,
                new CallBackListener<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean data) {
                        mView.loginByPasswordSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loginByPasswordFailure(errorMsg);
                    }
                });
    }

    @Override
    public void loginByThirdPlatform(String hsk, String requestType, String code, String openId, String userName, String expiredTime, String type, String avatar) {
        mHttpManager.request(mApiService.confirmThirdLogin(hsk, requestType, code, openId, userName, expiredTime, type, avatar), mCompositeDisposable, mView,
                new CallBackListener<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean data) {
                        mView.loginByPlatformSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loginByPlatformFailure(errorMsg);
                    }
                });
    }



    @Override
    public void takeView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}