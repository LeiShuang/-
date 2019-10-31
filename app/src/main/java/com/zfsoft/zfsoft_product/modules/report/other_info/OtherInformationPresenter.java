package com.zfsoft.zfsoft_product.modules.report.other_info;

import com.zfsoft.zfsoft_product.entity.OtherBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.entity.UserBean;
import com.zfsoft.zfsoft_product.modules.personal.my_report.MyReportContract;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class OtherInformationPresenter implements OtherInformationContract.Presenter {
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;
    private OtherInformationContract.View mView;

    @Inject
    public OtherInformationPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMyReportList(String hsk, String userId, int page, int size) {
        mHttpManager.request(mApiService.getMyReportList(hsk, userId, page, size), mCompositeDisposable, mView,
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
    public void getOtherPersonInfo(String hsk, String userId,String targetId) {
        mHttpManager.request(mApiService.getOtherInfo(hsk, userId,targetId), mCompositeDisposable, mView,
                new CallBackListener<OtherBean>() {

                    @Override
                    public void onSuccess(OtherBean data) {
                        mView.getOtherPersonSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.getOtherPersonFailure(errorMsg);
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
    public void takeView(OtherInformationContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}