package com.zfsoft.zfsoft_product.modules.home.notice;

import com.zfsoft.zfsoft_product.entity.NoticeBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/4/12.
 */
public class NoticePresenter implements NoticeContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private HttpManager mHttpManager;
    private NoticeContract.View mView;
    private ApiService mApiService;

    @Inject
    public NoticePresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getNoticeData(String hsk, String noticeId) {
        mHttpManager.request(mApiService.getNoticeData(hsk, noticeId), mCompositeDisposable, mView, new CallBackListener<NoticeBean>() {

            @Override
            public void onSuccess(NoticeBean data) {
                mView.getNoticeDataSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.getNoticeDataFailure(errorMsg);
            }
        });
    }

    @Override
    public void takeView(NoticeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
