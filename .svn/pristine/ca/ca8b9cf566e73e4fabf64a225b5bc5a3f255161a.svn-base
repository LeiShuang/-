package com.zfsoft.zfsoft_product.modules.home.search;

import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/4/6.
 */
public class SearchReportPresenter implements SearchReportContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private HttpManager mHttpManager;
    private SearchReportContract.View mView;

    @Inject
    public SearchReportPresenter(ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getSearchReportList(String hsk, String title, int page, int size) {
        mHttpManager.request(mApiService.getSearchReportList(hsk, title, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<TestReportBean>>() {

                    @Override
                    public void onSuccess(ResponseListInfo<TestReportBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loadFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(SearchReportContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
