package com.zfsoft.zfsoft_product.modules.report.report_child;

import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailContract;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/1/25 on 17:30
 * 描述:报告界面的presenter
 * 作者:Ls
 */
public class ReportChildDetailPresenter implements ReportChildDetailContract.Presenter {
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private ReportChildDetailContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    @Inject
    public ReportChildDetailPresenter(HttpManager httpManager, ApiService apiService) {
        mHttpManager = httpManager;
        mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getHotTestReport(String hsk, String title,String type, int page, int size) {
        mHttpManager.request(mApiService.getHotTestReport(hsk,title,type,page,size),mCompositeDisposable,mView,new CallBackListener<ResponseListInfo<TestReportBean>>(){

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
    public void takeView(ReportChildDetailContract.View view) {
            mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }


}