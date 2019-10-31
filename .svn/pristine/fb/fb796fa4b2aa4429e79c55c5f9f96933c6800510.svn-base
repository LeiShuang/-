package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.ProductInfo;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/1/21 on 18:55
 * 描述:某个商品的试用报告presenter
 * 作者:Ls
 */

public class TryUseReportPresenter implements TryUseDetailContract.TryUseReportPresenter {
    private HttpManager mHttpManager;
    private TryUseDetailContract.UseProductView mView;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    @Inject
    public TryUseReportPresenter(HttpManager httpManager, ApiService apiService) {
        mHttpManager = httpManager;
        mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }
    @Override
    public void getOneProductsTestReport(String hsk, String id, int page, int size) {
        mHttpManager.request(mApiService.getOneProductsTestReport(hsk, id, page, size), mCompositeDisposable, mView, new CallBackListener<ResponseListInfo<ReportInfo>>() {

            @Override
            public void onSuccess(ResponseListInfo<ReportInfo> data) {
                mView.loadSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.loadFailure(errorMsg);
            }
        });
    }

    @Override
    public void takeView(TryUseDetailContract.UseProductView view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }


}
