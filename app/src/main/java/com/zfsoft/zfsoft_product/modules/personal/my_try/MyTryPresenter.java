package com.zfsoft.zfsoft_product.modules.personal.my_try;

import android.support.v7.view.menu.MenuView;

import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
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
public class MyTryPresenter implements MyTryContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private MyTryContract.View mView;
    private ApiService mApiService;
    private HttpManager mHttpManager;

    @Inject
    public MyTryPresenter( ApiService mApiService, HttpManager mHttpManager) {
        this.mApiService = mApiService;
        this.mHttpManager = mHttpManager;
        mCompositeDisposable = new CompositeDisposable();
    }

//    @Override
//    public void getMyTryList(String hsk, String link) {
//        mHttpManager.request(mApiService.getMyTryProductList(hsk, link), mCompositeDisposable, mView,
//                new CallBackListener<ResponseListInfo<MyTrySingleBean>>() {
//                    @Override
//                    public void onSuccess(ResponseListInfo<MyTrySingleBean> data) {
//                        mView.loadSuccess(data);
//                    }
//
//                    @Override
//                    public void onError(String errorMsg) {
////                        mView.loadFailure(errorMsg);
//                        //TODO 这里加假数据
//                        ResponseListInfo<MyTrySingleBean> bean = new ResponseListInfo<>();
//                        bean.setSize(8);
//                        List<MyTrySingleBean> list = new ArrayList<>();
//                        for (int i = 0; i < 8; i++) {
//                            MyTrySingleBean myTrySingleBean = new MyTrySingleBean();
//                            myTrySingleBean.setProductApplyNum("申请人数：123");
//                            myTrySingleBean.setProductNum("数量：10");
//                            myTrySingleBean.setProductTitle("兰蔻气垫");
//                            myTrySingleBean.setProductUrl("http://dpic.tiankong.com/a6/rz/QJ8813728866.jpg");
//                            list.add(myTrySingleBean);
//                        }
//                        bean.setData(list);
//
//                        mView.loadSuccess(bean);
//                    }
//
//                });
//    }

    @Override
    public void takeView(MyTryContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void getMyTryList(String hsk, String userId, String commoditytype, int page, int size) {
        mHttpManager.request(mApiService.getMyTryProductList(hsk, userId, commoditytype, page, size), mCompositeDisposable, mView,
                new CallBackListener<ResponseListInfo<MyTrySingleBean>>() {


                    @Override
                    public void onSuccess(ResponseListInfo<MyTrySingleBean> data) {
                        mView.loadSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mView.loadFailure(errorMsg);
                    }
                });
    }
}