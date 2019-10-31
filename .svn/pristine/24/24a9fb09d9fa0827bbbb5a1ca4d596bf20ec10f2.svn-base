package com.zfsoft.zfsoft_product.modules.personal.integral_mall.integral_detail;

import com.zfsoft.zfsoft_product.entity.IntegralDetailBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class IntegralDetailPresenter implements IntegralDetailContract.Presenter {
    private HttpManager mHttpManager;
    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;
    private IntegralDetailContract.View mView;

    @Inject
    public IntegralDetailPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getIntegralDetailList(String hsk, int page, int size) {
        mHttpManager.request(mApiService.getIntegralDetailList(hsk, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<IntegralDetailBean>>() {
                    @Override
                    public void onSuccess(ResponseListInfo<IntegralDetailBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
//                        mView.loadFailure(errorMsg);
                        ResponseListInfo<IntegralDetailBean> beanResponseListInfo = new ResponseListInfo<>();
                        beanResponseListInfo.setSize(8);
                        List<IntegralDetailBean> list = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            IntegralDetailBean integralDetailBean = new IntegralDetailBean();
                            integralDetailBean.setTitle("签到得积分");
                            integralDetailBean.setIntegralNum("+10");
                            integralDetailBean.setTime("2019-1-23 14:24:59");
                            list.add(integralDetailBean);
                        }
                        beanResponseListInfo.setData(list);

                        mView.loadSuccess(beanResponseListInfo);
                    }
                });
    }

    @Override
    public void takeView(IntegralDetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}
