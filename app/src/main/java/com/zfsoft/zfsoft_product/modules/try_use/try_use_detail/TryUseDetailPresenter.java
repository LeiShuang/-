package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;

import com.zfsoft.zfsoft_product.entity.ProductInfo;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;
import com.zfsoft.zfsoft_product.net.Response;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/1/21 on 17:02
 * 描述:试用详情presenter
 * 作者:Ls
 */
public class TryUseDetailPresenter implements TryUseDetailContract.UseDetailPresenter {

    private HttpManager mHttpManager;
    private TryUseDetailContract.UseDetailView mView;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    @Inject
    public TryUseDetailPresenter(HttpManager httpManager, ApiService apiService) {
        mHttpManager = httpManager;
        mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();

    }
    /**
     * 获取产品详情
     * */
    @Override
    public void getProductsDetails(String hsk, String id,String userId) {
            mHttpManager.request(mApiService.getProductsDetails(hsk, id,userId), mCompositeDisposable, mView, new CallBackListener<ThingsInfoEntity>() {



                @Override
                public void onSuccess(ThingsInfoEntity data) {
                    mView.getInfoSuccess(data);
                }

                @Override
                public void onError(String errorMsg) {
                    mView.getInfoFailed(errorMsg);
                }
            });
    }

    @Override
    public void collectProduct(String hsk, String productId, String userId,int type) {
        mHttpManager.request(mApiService.getCollectCommodity(hsk, productId, userId,type), mCompositeDisposable, mView, new CallBackListener<SignBean>() {


            @Override
            public void onSuccess(SignBean data) {
                mView.collectSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.collectFailed(errorMsg);
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
    public void submitShareUrl(Map<String, String> map) {
        mHttpManager.request(mApiService.submitUrl(map),mCompositeDisposable,mView,new CallBackListener<SignBean>(){

            @Override
            public void onSuccess(SignBean data) {
                mView.submitShareUrlSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
            mView.submitShareUrlFailed(errorMsg);
            }
        });
    }


    @Override
    public void takeView(TryUseDetailContract.UseDetailView view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}