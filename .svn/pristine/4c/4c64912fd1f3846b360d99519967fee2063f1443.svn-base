package com.zfsoft.zfsoft_product.modules.personal.my_collection;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.entity.TryNewProductBean;
import com.zfsoft.zfsoft_product.modules.personal.my_report.MyReportsAdapter;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryContract;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryPresenter;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTrySingleAdapter;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailActivity;
import com.zfsoft.zfsoft_product.modules.try_use.try_use_detail.TryUseDetailActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyCollectionSingleFragment extends BaseListFragment<CollectionBean,MyCollectionPresenter>  implements MyCollectionContract.View{

    @Inject
    public MyCollectionSingleFragment() {
    }

    @Inject
    MyCollectionPresenter myCollectionPresenter;

    private int mType;

    public static MyCollectionSingleFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type",type);
        MyCollectionSingleFragment fragment = new MyCollectionSingleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setSpecificPresenter() {
        myCollectionPresenter.takeView(this);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        if(mType == 2){//收藏的试用报告 这里废弃
            CollectionBean testReportBean = (CollectionBean) adapter.getData().get(position);
            String id = testReportBean.getTestreportid();
            if(id == null){
                ToastUtils.showCenterToast(getContext(),"该报告暂时无法查看");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("reportId",Integer.parseInt(id));
            RxActivityTool.skipActivity(getContext(), ReportDetailActivity.class,bundle);
        }else {
            CollectionBean tryNewProductBean = (CollectionBean) adapter.getData().get(position);
            String commodityid = tryNewProductBean.getCommodityid();
//            Bundle bundle = new Bundle();
//            bundle.putString("thingsId",commodityid);

            Intent intent = new Intent(getContext(),TryUseDetailActivity.class);
            intent.putExtra("thingsId",commodityid);
            MyCollectionSingleFragment.this.startActivityForResult(intent,100);
//            RxActivityTool.skipActivity(getContext(), TryUseDetailActivity.class,bundle);
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
    protected BaseQuickAdapter<CollectionBean, BaseViewHolder> getAdapter() {
            return new MyCollectionSingleAdapter(mType);
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
