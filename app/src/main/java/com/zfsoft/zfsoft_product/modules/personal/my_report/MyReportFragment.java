package com.zfsoft.zfsoft_product.modules.personal.my_report;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.MyTryReportBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.modules.report.StaggeredItemDecoration;
import com.zfsoft.zfsoft_product.modules.report.report_child.ChildItemDecoration;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyReportFragment extends BaseListFragment<TestReportBean,MyReportPresenter>
implements MyReportContract.View{

    @Inject
    public MyReportFragment() {
    }


    @Inject
    MyReportPresenter myReportPresenter;

    private MyReportsAdapter mAdapter;

    @Override
    protected void setSpecificPresenter() {
        myReportPresenter.takeView(this);
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
    public void loadData() {
        super.loadData();
        myReportPresenter.getMyReportList(Config.HSK, DbHelper.getUserId(mContext),start_page,PAGE_SIZE);
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

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
        mAdapter = new MyReportsAdapter();
        return mAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myReportPresenter.dropView();
    }
}