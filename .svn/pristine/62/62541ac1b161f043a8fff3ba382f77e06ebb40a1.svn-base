package com.zfsoft.zfsoft_product.modules.personal.accout_safe;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.ThirdBindBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/3/20.
 */
public class AccountSafePresenter implements AccountSafeContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private HttpManager mHttpManager;
    private AccountSafeContract.View mView;
    private ApiService mApiService;

    @Inject
    public AccountSafePresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();

    }

    @Override
    public void getThirdBindState(String hsk, String userId) {
        mHttpManager.request(mApiService.getThirdBindState(hsk, userId), mCompositeDisposable, mView,
                new CallBackListener<List<ThirdBindBean>>() {

                    @Override
                    public void onSuccess(List<ThirdBindBean> data) {
                        mView.getThirdBindStateSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getThirdBindStateFailure(errorMsg);
                    }
                });
    }

    @Override
    public void unbindThirdPlatform(String hsk, String userId, String openId) {
        mHttpManager.request(mApiService.unbindThirdPlatform(hsk, openId, userId), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.unbindThirdPlatformSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.unbindThirdPlatformFailure(errorMsg);
                    }
                });
    }

    @Override
    public void bindThirdPlatform(String hsk, String userId, String requestType, String code, String openId, String userName, String openType, String avatar) {
        mHttpManager.request(mApiService.bindThirdPlatform(hsk, userId, requestType, code, openId, userName, openType, avatar), mCompositeDisposable,
                mView, new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.bindThirdPlatformSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.bindThirdPlatformFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(AccountSafeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
