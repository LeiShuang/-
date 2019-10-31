package com.zfsoft.zfsoft_product.modules.login.splash;

import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.modules.login.LoginActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class SplashFragment extends BaseFragment {

//    private boolean isFirstTimeIn; //用户对象

    @Inject
    public SplashFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initVariables() {
//        isFirstTimeIn = DbHelper.checkUserIsFirstTimeIn(mContext);
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mDisposable  != null) {
                            mDisposable .dispose();
                        }
                        RxActivityTool.skipActivity(mContext, HomeActivity.class);
//                        if (DbHelper.checkUserIsLogin(mContext)) {
//                            RxActivityTool.skipActivity(mContext, HomeActivity.class);
//                        }else {
//                            RxActivityTool.skipActivity(mContext, LoginActivity.class);
//                        }
                    }
                });



    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initPresenter() {

    }


}