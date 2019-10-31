package com.zfsoft.zfsoft_product.modules.send_report;

import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;
import com.zfsoft.zfsoft_product.net.Response;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/3/16 on 13:51
 * 描述:首页的presenter用来请求
 * 作者:Ls
 */
public class SendReportPresenter implements SendReportContract.Presenter{
    private CompositeDisposable mCompositeDisposable;
    private SendReportContract.View mView;
    private ApiService mApiService;
    private HttpManager mHttpManager;

    @Inject
    public SendReportPresenter(ApiService apiService, HttpManager httpManager) {
        mApiService = apiService;
        mHttpManager = httpManager;
       mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getReportSize(String hsk, String userId, String commoditytype, int page, int size) {
        mHttpManager.request(mApiService.getMyTryProductList(hsk,userId,commoditytype,page,size),mCompositeDisposable,mView,
                new CallBackListener<ResponseListInfo<MyTrySingleBean>>(){

            @Override
            public void onSuccess(ResponseListInfo<MyTrySingleBean> data) {
                mView.getReportSizeSuccess(data);
            }

                    @Override
            public void onError(String errorMsg) {
                mView.getReportSizeFailed(errorMsg);
            }
        });
    }

    @Override
    public void takeView(SendReportContract.View view) {
       mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
