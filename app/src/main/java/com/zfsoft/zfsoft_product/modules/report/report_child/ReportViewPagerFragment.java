package com.zfsoft.zfsoft_product.modules.report.report_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryPresenter;
import com.zfsoft.zfsoft_product.modules.report.StaggeredItemDecoration;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailActivity;
import com.zfsoft.zfsoft_product.modules.report.SearchListenerCallBack;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import javax.inject.Inject;

/**
 * 创建日期：2019/1/15 on 16:39
 * 描述:报告ViewPager使用的Fragment
 * 接受的参数为  int 类型，用于区别请求的页面类型
 * 作者:Ls
 */
public class ReportViewPagerFragment extends BaseListFragment<TestReportBean,MyTryPresenter> implements ReportChildDetailContract.View,SearchListenerCallBack{

    private String mType;
    private String query;
    @Inject
   public ReportViewPagerFragment(){

    }
    public SearchListenerCallBack getSearchListener(){
        return this;
    }
    @Inject
    ReportChildDetailPresenter mPresenter;
    public static ReportViewPagerFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type",type);
        ReportViewPagerFragment fragment = new ReportViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void handleBundle(Bundle bundle) {
       if (bundle != null){
           mType = bundle.getString("type","all");
       }
    }

    @Override
    protected void setSpecificPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    public void loadData() {
        super.loadData();
        if (query == null){
            query = "";
            start_page = 1;
        }
        mPresenter.getHotTestReport(Config.HSK,query,mType,start_page,PAGE_SIZE);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        TestReportBean info = (TestReportBean) adapter.getData().get(position);

        Intent intent = new Intent(getActivity(),ReportDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("reportId",info.getId());
        bundle.putString("reportUserId",info.getTalented());
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }





    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new StaggeredItemDecoration(SizeUtils.dp2px(8,getContext()));

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        return staggeredGridLayoutManager;
    }

    @Override
    protected BaseQuickAdapter<TestReportBean, BaseViewHolder> getAdapter() {
        ReportChildFragmentAdapter adapter = new ReportChildFragmentAdapter();
        return adapter;
    }

    @Override
    public void submitSearch(String query) {
        this.query = query;
        start_page = 1;
        loadData();
    }

    @Override
    public void clearQuery() {
        query = "";
    }

}