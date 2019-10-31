package com.zfsoft.zfsoft_product.modules.personal.my_try;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.TryNewProductBean;
import com.zfsoft.zfsoft_product.modules.report.submit_report.SubmitReportActivity;
import com.zfsoft.zfsoft_product.modules.try_use.try_use_detail.TryUseDetailActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyTrySingleFragment extends BaseListFragment<MyTrySingleBean,MyTryPresenter>  implements MyTryContract.View{

    @Inject
    public MyTrySingleFragment() {
    }

    @Inject
    MyTryPresenter myTryPresenter;

    private int  mType = 1;

    public static MyTrySingleFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type",type);
        MyTrySingleFragment fragment = new MyTrySingleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setSpecificPresenter() {
        myTryPresenter.takeView(this);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        MyTrySingleBean tryNewProductBean = (MyTrySingleBean) adapter.getData().get(position);
        String commodityid = tryNewProductBean.getCommodityid();
        Bundle bundle = new Bundle();
        bundle.putString("thingsId",commodityid);
        RxActivityTool.skipActivity(getContext(), TryUseDetailActivity.class,bundle);
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initVariables() {
        mType = getArguments().getInt("type",1);
    }


    @Override
    public void loadData() {
        super.loadData();
        myTryPresenter.getMyTryList(Config.HSK, DbHelper.getUserId(mContext),String.valueOf(mType),start_page,PAGE_SIZE);
    }


    @Override
    protected BaseQuickAdapter<MyTrySingleBean, BaseViewHolder> getAdapter() {
        MyTrySingleAdapter myTrySingleAdapter = new MyTrySingleAdapter(mType);
        myTrySingleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(mType == 3){
                    MyTrySingleBean myTrySingleBean = myTrySingleAdapter.getData().get(position);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("thingsId",myTrySingleBean.getCommodityid());
                    Intent intent = new Intent(getContext(),SubmitReportActivity.class);
                    intent.putExtra("thingsId",myTrySingleBean.getCommodityid());
                    MyTrySingleFragment.this.startActivityForResult(intent,1001);
//                    RxActivityTool.skipActivity(getContext(), SubmitReportActivity.class,bundle);
                }
            }
        });
        return myTrySingleAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myTryPresenter.dropView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }
}
