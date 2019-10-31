package com.zfsoft.zfsoft_product.modules.personal.my_report;

import com.zfsoft.zfsoft_product.entity.MyTryReportBean;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyReportPresenter implements MyReportContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private MyReportContract.View mView;
    private ApiService mApiService;
    private HttpManager mHttpManager;

    @Inject
    public MyReportPresenter(ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMyReportList(String hsk,String userId,int page,int size) {
        mHttpManager.request(mApiService.getMyReportList(hsk, userId,page,size), mCompositeDisposable, mView,
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
    public void takeView(MyReportContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
