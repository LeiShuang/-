package com.zfsoft.zfsoft_product.modules.personal.my_collection;

import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryContract;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class MyCollectionPresenter implements MyCollectionContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private MyCollectionContract.View mView;
    private ApiService mApiService;
    private HttpManager mHttpManager;

    @Inject
    public MyCollectionPresenter(ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMyCollectionList(String hsk, String userId, String commoditytype, int page, int size) {
        mHttpManager.request(mApiService.getMyCollectionList(hsk, userId, commoditytype, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<CollectionBean>>() {


                    @Override
                    public void onSuccess(ResponseListInfo<CollectionBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loadFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(MyCollectionContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }


}
