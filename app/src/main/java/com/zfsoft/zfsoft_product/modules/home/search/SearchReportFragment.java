package com.zfsoft.zfsoft_product.modules.home.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.modules.personal.my_collection.MyCollectionReportAdapter;
import com.zfsoft.zfsoft_product.modules.personal.my_report.MyReportsAdapter;
import com.zfsoft.zfsoft_product.modules.report.StaggeredItemDecoration;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailActivity;
import com.zfsoft.zfsoft_product.utils.SizeUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/4/6.
 */
public class SearchReportFragment extends BaseListFragment<TestReportBean,SearchReportPresenter>
implements SearchReportContract.View{

    @Inject
    public SearchReportFragment() {
    }

    public static SearchReportFragment newInstance() {

        Bundle args = new Bundle();

        SearchReportFragment fragment = new SearchReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String mTitle;

    public void setSearchTitle(String title){
        mTitle = title;
        loadData();
    }

    @Inject
    SearchReportPresenter searchReportPresenter;

    @Override
    protected void setSpecificPresenter() {
        searchReportPresenter.takeView(this);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        TestReportBean testReportBean = (TestReportBean) adapter.getData().get(position);
        int id = testReportBean.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("reportId",id);
        bundle.putString("reportUserId",testReportBean.getTalented());
        RxActivityTool.skipActivity(getContext(), ReportDetailActivity.class,bundle);
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }

    @Override
    public void loadData() {
        super.loadData();
        searchReportPresenter.getSearchReportList(Config.HSK,mTitle,start_page,PAGE_SIZE);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new StaggeredItemDecoration(SizeUtils.dp2px(8,getContext()));
    }

    @Override
    protected BaseQuickAdapter<TestReportBean, BaseViewHolder> getAdapter() {
        return new MyReportsAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchReportPresenter.dropView();
    }


}
