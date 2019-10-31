package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.TestreportreplyListBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/1/28 on 14:36
 * 描述:
 * 作者:Ls
 */
public class DiscussDetailPresenter implements DiscussDetailContract.Presenter{
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private HttpManager mHttpManager;
    private DiscussDetailContract.View mView;

    @Inject
    public DiscussDetailPresenter(ApiService apiService, HttpManager httpManager) {
        mApiService = apiService;
        mHttpManager = httpManager;
        mCompositeDisposable = new CompositeDisposable();

    }

    @Override
    public void getChildDiscussDetail(String hsk, int fatherCommentId, int page, int size) {
        mHttpManager.request(mApiService.getTestReportCommentReplys(hsk,fatherCommentId,page,size),mCompositeDisposable,mView,new CallBackListener<ResponseListInfo<TestreportreplyListBean>>(){

            @Override
            public void onSuccess(ResponseListInfo<TestreportreplyListBean> data) {
                mView.loadSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.loadFailure(errorMsg);
            }
        });
    }

    @Override
    public void takeView(DiscussDetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }

}
