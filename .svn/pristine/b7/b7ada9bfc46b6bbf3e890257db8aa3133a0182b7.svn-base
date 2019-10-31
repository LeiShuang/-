package com.zfsoft.zfsoft_product.modules.personal.my_platform;

import com.zfsoft.zfsoft_product.entity.PlatformBean;
import com.zfsoft.zfsoft_product.entity.RedBookBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.lang.invoke.MethodHandle;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/3/15.
 */
public class MyPlatformPresenter implements MyPlatformContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private MyPlatformContract.View mView;
    private ApiService mApiService;
    private HttpManager mHttpManager;

    @Inject
    public MyPlatformPresenter(ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void saveMyPlatformInfo(String hsk,String userId,String redName,String redAccount,String redHome,String redFans) {
        mHttpManager.request(mApiService.saveMyXhsInfo(hsk, userId, redName, redAccount, redHome, redFans), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.saveMyPlatformInfoSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.saveMyPlatformInfoFailure(errorMsg);
                    }
                });
    }

    @Override
    public void getMyPlatformInfo(String hsk, String userId) {
        mHttpManager.request(mApiService.getMyXhsInfo(hsk, userId), mCompositeDisposable, mView,
                new CallBackListener<RedBookBean>() {

                    @Override
                    public void onSuccess(RedBookBean data) {
                        mView.getMyPlatformInfoSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getMyPlatformInfoFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(MyPlatformContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
