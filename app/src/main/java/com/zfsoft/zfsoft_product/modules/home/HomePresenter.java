package com.zfsoft.zfsoft_product.modules.home;

import com.zfsoft.zfsoft_product.entity.BannerBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.TestPersonBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.entity.TryNewProductBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/18.
 */
public class HomePresenter implements HomeContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private HomeContract.View mView;
    private HttpManager mHttpManager;

    @Inject
    public HomePresenter(ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBannerList(String hsk ) {
        mHttpManager.request(mApiService.getBannerList(hsk), mCompositeDisposable, mView,
                new CallBackListener<List<BannerBean>>() {
                    @Override
                    public void onSuccess(List<BannerBean> data) {
                        mView.getBannerListSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getBannerListFailure(errorMsg);
                    }
                });
    }

    @Override
    public void getTryNewProductList(String hsk,String userId) {
        mHttpManager.request(mApiService.getTryNewProductList(hsk,userId), mCompositeDisposable, mView,
                new CallBackListener<List<TryNewProductBean>>() {
                    @Override
                    public void onSuccess(List<TryNewProductBean> data) {
                        mView.getTryNewProductListSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getTryNewProductListFailure(errorMsg);
                    }
                });
    }

    @Override
    public void tryUseProDuct(String hsk, String productId, String userId) {
        mHttpManager.request(mApiService.getProductApplication(hsk,productId,userId),mCompositeDisposable,mView,new CallBackListener<SignBean>(){

            @Override
            public void onSuccess(SignBean data) {
                mView.TryUseSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.TryUseFailed(errorMsg);
            }
        });
    }

    @Override
    public void getTestReportList(String hsk) {
        mHttpManager.request(mApiService.getTestReportList(hsk), mCompositeDisposable, mView,
                new CallBackListener<List<TestReportBean>>() {


                    @Override
                    public void onSuccess(List<TestReportBean> data) {
                        mView.getTestReportListSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getTestReportListFailure(errorMsg);
                    }
                });
    }

    @Override
    public void getTestPersonList(String hsk,String userId) {
        mHttpManager.request(mApiService.getTestPersonList(hsk,userId), mCompositeDisposable, mView,
                new CallBackListener<List<TestPersonBean>>() {

                    @Override
                    public void onSuccess(List<TestPersonBean> data) {
                        mView.getTestPersonListSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getTestPersonListFailure(errorMsg);
                    }
                });
    }

    @Override
    public void addAttention(String hsk, String userId, String targetId,String type) {
        mHttpManager.request(mApiService.attentionPerson(hsk, userId, targetId,type), mCompositeDisposable, mView,
                new CallBackListener<SignBean>() {

                    @Override
                    public void onSuccess(SignBean data) {
                        mView.addAttentionSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.addAttentionFailure(errorMsg);
                    }
                });
    }

    @Override
    public void takeView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}