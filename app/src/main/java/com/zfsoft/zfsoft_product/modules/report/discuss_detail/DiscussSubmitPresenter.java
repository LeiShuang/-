package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import com.zfsoft.zfsoft_product.entity.DiscussDetailFirstEntity;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/1/29 on 21:03
 * 描述:
 * 作者:Ls
 */
public class DiscussSubmitPresenter implements DiscussDetailContract.discussPresenter {
    private CompositeDisposable mCompositeDisposable;
    private ApiService mApiService;
    private HttpManager mHttpManager;
    private DiscussDetailContract.DiscussView mView;

    @Inject
    public DiscussSubmitPresenter(HttpManager manager,ApiService service){
        mHttpManager = manager;
        mApiService = service;
        mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    public void submitSecond(String hsk, int reportId, String userId, String content, int fatherDiscussId) {
        mHttpManager.request(mApiService.saveSecondReportComment(hsk,reportId,userId,content,fatherDiscussId),mCompositeDisposable,mView,new CallBackListener<SignBean>(){

            @Override
            public void onSuccess(SignBean data) {
                mView.submitSecondDiscussSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.submitSecondDiscussFailed(errorMsg);
            }
        });
    }

    @Override
    public void getFIrstDiscuss(String hsk, String userId, String commentId) {
        mHttpManager.request(mApiService.getFirstDiscuss(hsk,userId,commentId),mCompositeDisposable,mView,new CallBackListener<DiscussDetailFirstEntity>(){

            @Override
            public void onSuccess(DiscussDetailFirstEntity data) {
                mView.getFirstDiscussSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
            mView.getFirstDiscussFailed(errorMsg);
            }
        });
    }

    @Override
    public void likeDiscuss(String hsk, int reportId, String userId, int type) {
        mHttpManager.request(mApiService.addUserLike(hsk, reportId, userId, type), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.starDiscussSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.starDiscussFailed(errorMsg);
            }
        });
    }

    @Override
    public void takeView(DiscussDetailContract.DiscussView view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}