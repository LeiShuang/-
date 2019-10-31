package com.zfsoft.zfsoft_product.modules.personal.accout_safe.modify;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/29.
 */
public class ModifyPresenter implements ModifyContract.Presenter {
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;
    private ModifyContract.View mView;

    @Inject
    public ModifyPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void forChangePassword(String hsk,String userId, String phone, String code) {
        mHttpManager.request(mApiService.forChangePassword(hsk, userId, phone, code), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.modifySuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.modifyFailure(errorMsg);
                    }
                });
    }

    @Override
    public void forChangePhoneNum(String hsk,String userId, String phone, String code) {
        mHttpManager.request(mApiService.forChangePhone(hsk, userId, phone, code), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.modifySuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.modifyFailure(errorMsg);
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
    public void confirmSmsCode(String hsk, String phone, String userId, String type,String code) {
        mHttpManager.request(mApiService.confirmSmsCode(hsk, phone, userId, type, code), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {


                    @Override
                    public void onSuccess(SignBean data) {
                        mView.confirmSmsCodeSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.confirmSmsCodeFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(ModifyContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
