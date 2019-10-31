package com.zfsoft.zfsoft_product.modules.home.search;

import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/4/6.
 */
public class SearchTryUserPresenter implements SearchTryUseContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private SearchTryUseContract.View mView;

    @Inject
    public SearchTryUserPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getThingsListInfo(String hsk, String sortType, String title, int type, int page, int size) {
        mHttpManager.request(mApiService.getTryUseThingsList(hsk,sortType,title,type, page, size), mCompositeDisposable, mView, new CallBackListener<ResponseListInfo<ThingsInfoEntity>>() {


            @Override
            public void onSuccess(ResponseListInfo<ThingsInfoEntity> data) {
                mView.loadSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.loadFailure(errorMsg);
            }
        });

    }

    @Override
    public void takeView(SearchTryUseContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
