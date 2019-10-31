package com.zfsoft.zfsoft_product.modules.personal;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.UserBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class PersonalPresenter implements PersonalContract.Presenter {
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private PersonalContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public PersonalPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void startSign(String hsk, String userId) {
        mHttpManager.request(mApiService.getSignResult(hsk, userId), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {
                    @Override
                    public void onSuccess(SignBean data) {
                        mView.getSignResultSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getSignResultFailure(errorMsg);
                    }
                });
    }

    @Override
    public void getUserInfo(String hsk,String userId) {
        mHttpManager.request(mApiService.getPersonalInfo(hsk, userId), mCompositeDisposable, mView,
                new CallBackListener<UserBean>() {

                    @Override
                    public void onSuccess(UserBean data) {
                        mView.getUserInfoSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getUserInfoFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(PersonalContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
