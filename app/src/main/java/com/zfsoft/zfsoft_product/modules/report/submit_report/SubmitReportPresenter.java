package com.zfsoft.zfsoft_product.modules.report.submit_report;

import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建日期：2019/1/30 on 15:51
 * 描述:提交图片presenter
 * 作者:Ls
 */
public class SubmitReportPresenter implements SubmitReportContract.Presenter {
    private HttpManager mHttpManager;
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private SubmitReportContract.View mView;

    @Inject
    public SubmitReportPresenter(HttpManager httpManager, ApiService apiService) {
        mHttpManager = httpManager;
        mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void submitReportPics(Map<String, RequestBody> map, List<MultipartBody.Part> fileArr) {
        mHttpManager.request(mApiService.submitPics(map, fileArr), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.submitPicSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.submitPicsFailed(errorMsg);
            }
        });
    }

    @Override
    public void takeView(SubmitReportContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }


}
