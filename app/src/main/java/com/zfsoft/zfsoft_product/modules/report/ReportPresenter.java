package com.zfsoft.zfsoft_product.modules.report;

import com.zfsoft.zfsoft_product.entity.ProductTypeEntity;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/2/15 on 17:47
 * 描述:
 * 作者:Ls
 */
public class ReportPresenter implements ReportContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private ReportContract.View mView;
    private HttpManager mHttpManager;

    @Inject
    public ReportPresenter(ApiService apiService, HttpManager httpManager) {
        mApiService = apiService;
        mHttpManager = httpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getProductType(String hsk) {
        mHttpManager.request(mApiService.getProductType(hsk), mCompositeDisposable, mView, new CallBackListener<List<ProductTypeEntity>>() {

            @Override
            public void onSuccess(List<ProductTypeEntity> data) {
                mView.getTypeSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.getTypeFailed(errorMsg);
            }
        });
    }

    @Override
    public void takeView(ReportContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
