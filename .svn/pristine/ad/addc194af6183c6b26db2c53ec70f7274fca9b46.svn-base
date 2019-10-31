package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.entity.InfoServer;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryPresenter;
import com.zfsoft.zfsoft_product.modules.report.report_child.ChildItemDecoration;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailActivity;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import javax.inject.Inject;

/**
 * 创建日期：2019/1/12 on 14:27
 * 描述:某个商品的试用报告Fragment
 * 作者:Ls
 */
@ActivityScoped
public class TryUseReportFragment extends BaseListFragment<ReportInfo,TryUseReportPresenter> implements TryUseDetailContract.UseProductView{

    private String mId;

    @Inject
    public TryUseReportFragment() {
    }
    @Inject
    TryUseReportPresenter mPresenter;
    public static TryUseReportFragment newInstance(String id) {
        
        Bundle args = new Bundle();
        args.putString("thingsId",id);
        TryUseReportFragment fragment = new TryUseReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private StaggeredGridLayoutManager mManager;
    private TryUseReportAdapter mAdapter;

    @Override
    protected void handleBundle(Bundle bundle) {
       if (bundle != null){
           mId = bundle.getString("thingsId","");
       }
    }

    @Override
    protected void setSpecificPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        Intent intent = new Intent(getActivity(),ReportDetailActivity.class);
        Bundle bundle = new Bundle();
        int reportId = mAdapter.getData().get(position).getId();
        bundle.putInt("reportId",reportId);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    public void loadFailure(String errorMsg) {
        super.loadFailure(errorMsg);
    }

    @Override
    public void loadData() {
        super.loadData();
      mPresenter.getOneProductsTestReport(Config.HSK,mId,start_page,PAGE_SIZE);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new ChildItemDecoration(SizeUtils.dp2px(8,getContext()));

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        return mManager;
    }

    @Override
    protected BaseQuickAdapter<ReportInfo, BaseViewHolder> getAdapter() {
        mAdapter = new TryUseReportAdapter();
        return mAdapter;
    }
}
