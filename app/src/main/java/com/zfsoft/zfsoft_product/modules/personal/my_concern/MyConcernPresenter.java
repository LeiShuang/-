package com.zfsoft.zfsoft_product.modules.personal.my_concern;

import com.zfsoft.zfsoft_product.entity.ConcernBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/2/22.
 */
public class MyConcernPresenter implements MyConcernContract.Presenter{

    private CompositeDisposable mCompositeDisposable;
    private MyConcernContract.View mView;
    private ApiService mApiService;
    private HttpManager mHttpManager;

    @Inject
    public MyConcernPresenter(ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMyConcernList(String hsk, String userId, int page, int size) {
        mHttpManager.request(mApiService.getMyConcernList(hsk, userId, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<ConcernBean>>() {

                    @Override
                    public void onSuccess(ResponseListInfo<ConcernBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loadFailure(errorMsg);
                    }
                });
    }

    @Override
    public void getMyFansList(String hsk, String userId, int page, int size) {
        mHttpManager.request(mApiService.getMyFansList(hsk, userId, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<ConcernBean>>() {

                    @Override
                    public void onSuccess(ResponseListInfo<ConcernBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loadFailure(errorMsg);
                    }
                });
    }

    @Override
    public void addAttention(String hsk, String userId, String targetId, String type) {
        mHttpManager.request(mApiService.attentionPerson(hsk, userId, targetId, type), mCompositeDisposable, mView,
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
    public void takeView(MyConcernContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
