package com.zfsoft.zfsoft_product.modules.personal.my_collection;

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
import com.zfsoft.zfsoft_product.modules.personal.my_report.MyReportsAdapter;
import com.zfsoft.zfsoft_product.modules.report.StaggeredItemDecoration;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailActivity;
import com.zfsoft.zfsoft_product.modules.try_use.try_use_detail.TryUseDetailActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.SizeUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/2/11.
 */
public class MyCollectionReportFragment extends BaseListFragment<CollectionBean,MyCollectionPresenter> implements MyCollectionContract.View {
    @Inject
    public MyCollectionReportFragment() {
    }

    @Inject
    MyCollectionPresenter myCollectionPresenter;

    private int mType;

    public static MyCollectionReportFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type",type);
        MyCollectionReportFragment fragment = new MyCollectionReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setSpecificPresenter() {
        myCollectionPresenter.takeView(this);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        if(mType == 2){//收藏的试用报告
            CollectionBean testReportBean = (CollectionBean) adapter.getData().get(position);
            String id = testReportBean.getTestreportid();
            if(id == null){
                ToastUtils.showCenterToast(getContext(),"该报告暂时无法查看");
                return;
            }
//            Bundle bundle = new Bundle();
//            bundle.putInt("reportId",Integer.parseInt(id));

            Intent intent = new Intent(getContext(),ReportDetailActivity.class);
            intent.putExtra("reportId",Integer.parseInt(id));
            MyCollectionReportFragment.this.startActivityForResult(intent,100);
//            RxActivityTool.skipActivity(getContext(), ReportDetailActivity.class,bundle);
        }else {//废弃
            CollectionBean tryNewProductBean = (CollectionBean) adapter.getData().get(position);
            String commodityid = tryNewProductBean.getCommodityid();
            Bundle bundle = new Bundle();
            bundle.putString("thingsId",commodityid);
            RxActivityTool.skipActivity(getContext(), TryUseDetailActivity.class,bundle);
        }
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
        mType = getArguments().getInt("type");
        myCollectionPresenter.getMyCollectionList(Config.HSK, DbHelper.getUserId(mContext),String.valueOf(mType),start_page,PAGE_SIZE);
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
    protected BaseQuickAdapter<CollectionBean, BaseViewHolder> getAdapter() {
        return new MyCollectionReportAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myCollectionPresenter.dropView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }
}
