package com.zfsoft.zfsoft_product.modules.login.info;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class SetInfoPresenter implements SetInfoContract.Presenter {

    private HttpManager mHttpManager;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private SetInfoContract.View mView;

    @Inject
    public SetInfoPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void uploadPersonalInfo(Map<String, RequestBody> map, List<MultipartBody.Part> images) {
        mHttpManager.request(mApiService.uploadPersonalInfo(map, images), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.uploadInfoSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.uploadInfoFailure(errorMsg);
                    }
                });
    }

    @Override
    public void getUserInfo(String hsk, String userId) {
        mHttpManager.request(mApiService.getUserInfo(hsk, userId), mCompositeDisposable, mView,
                new CallBackListener<User>() {

                    @Override
                    public void onSuccess(User data) {
                        mView.getUserInfoSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getUserInfoFailure(errorMsg);
                    }
                });
    }


    @Override
    public void takeView(SetInfoContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
