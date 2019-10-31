package com.zfsoft.zfsoft_product.modules.personal.integral_mall;

import com.zfsoft.zfsoft_product.entity.IntegralBean;
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
 * on 2019/1/21.
 */
public class IntegralMallPresenter implements IntegralMallContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private HttpManager mHttpManager;
    private IntegralMallContract.View mView;
    private ApiService mApiService;

    @Inject
    public IntegralMallPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getIntegralProductList(String hsk, int page, int size) {
        mHttpManager.request(mApiService.getIntegralProductList(hsk, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<IntegralBean>>() {
                    @Override
                    public void onSuccess(ResponseListInfo<IntegralBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
//                        mView.loadFailure(errorMsg);
                        ResponseListInfo<IntegralBean> bean = new ResponseListInfo<>();
                        bean.setSize(8);
                        List<IntegralBean> list = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            IntegralBean integralBean = new IntegralBean();
                            integralBean.setIntegralNeed("250");
                            integralBean.setIntegralPrice("12");
                            integralBean.setIntegralTitle("圣诞护肤礼包");
                            integralBean.setIntegralUrl("http://dpic.tiankong.com/a6/rz/QJ8813728866.jpg");
                            list.add(integralBean);
                        }
                        bean.setData(list);

                        mView.loadSuccess(bean);

                    }
                });
    }

    @Override
    public void takeView(IntegralMallContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }


}
