package com.zfsoft.zfsoft_product.modules.personal.account_address.add;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class NewAddAddressPresenter implements NewAddAddressContract.Presenter {
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;
    private NewAddAddressContract.View mView;

    @Inject
    public NewAddAddressPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void addMyAddress(Map<String, String> map) {
        mHttpManager.request(mApiService.addAddress(map), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.addMyAddressSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.addMyAddressFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(NewAddAddressContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}