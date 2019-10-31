package com.zfsoft.zfsoft_product.modules.try_use.share_redbook;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.http.FieldMap;

/**
 * 创建日期：2019/2/26 on 18:34
 * 描述:分享小红书请求层
 * 作者:Ls
 */
public class ShareRedBookPresenter implements ShareRedBookContract.Presenter {
    private ApiService mApiService;
    private HttpManager mHttpManager;
    private CompositeDisposable mCompositeDisposable;
    private ShareRedBookContract.View mView;
    @Inject
    public ShareRedBookPresenter(ApiService apiService, HttpManager httpManager) {
        mApiService = apiService;
        mHttpManager = httpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void submitShareUrl(Map<String, String> params) {
        mHttpManager.request(mApiService.submitShareUrl(params), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.submitShareUrlSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.submitFailed(errorMsg);
            }
        });
    }

    @Override
    public void takeView(ShareRedBookContract.View view) {
        mView = view;

    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}