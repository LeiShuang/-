package com.zfsoft.zfsoft_product.modules.personal.account_address;

import com.zfsoft.zfsoft_product.entity.AddressBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class AccountAddressPresenter implements AccountAddressContract.Presenter {
    private HttpManager mHttpManager;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private AccountAddressContract.View mView;

    @Inject
    public AccountAddressPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMyAddressList(String hsk, String userId) {
        mHttpManager.request(mApiService.getAddressList(hsk, userId), mCompositeDisposable, mView,
                new CallBackListener<List<AddressBean>>() {

                    @Override
                    public void onSuccess(List<AddressBean> data) {
                        mView.getMyAddressListSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getMyAddressListFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(AccountAddressContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
