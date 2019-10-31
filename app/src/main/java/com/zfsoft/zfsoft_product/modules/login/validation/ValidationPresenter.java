package com.zfsoft.zfsoft_product.modules.login.validation;


import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.modules.login.validation.ValidationContract;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class ValidationPresenter implements ValidationContract.Presenter{

    private HttpManager mHttpManager;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private ValidationContract.View mView;

    @Inject
    public ValidationPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loginByValidate(String hsk, String phone, String validateCode) {
        mHttpManager.request(mApiService.loginByValidation(hsk, phone, validateCode), mCompositeDisposable, mView,
                new CallBackListener<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean data) {
                        mView.loginByValidateSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loginByValidateFailure(errorMsg);
                    }
                });
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
    public void confirmRegisterState(String hsk, String phone) {
        mHttpManager.request(mApiService.confirmRegisterPhone(hsk, phone), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.getRegisterStateSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getRegisterStateFailure(errorMsg);
                    }
                });
    }

    @Override
    public void register(String hsk, String phone, String code, String password) {
        mHttpManager.request(mApiService.register(hsk, phone, code, password), mCompositeDisposable, mView,
                new CallBackListener<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean data) {
                        mView.getRegisterSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getRegisterFailure(errorMsg);
                    }
                });
    }


    @Override
    public void takeView(ValidationContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}