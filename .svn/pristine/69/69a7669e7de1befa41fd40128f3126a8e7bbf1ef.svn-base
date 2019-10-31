package com.zfsoft.zfsoft_product.modules.personal.integral_mall.integral_detail;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.entity.IntegralDetailBean;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class IntegralDetailFragment extends BaseListFragment<IntegralDetailBean,IntegralDetailPresenter>
implements IntegralDetailContract.View{

    @Inject
    public IntegralDetailFragment() {
    }

    @Override
    protected void setSpecificPresenter() {
        integralDetailPresenter.takeView(this);
    }

    @Inject
    IntegralDetailPresenter integralDetailPresenter;


    @Override
    public void loadData() {
        super.loadData();
        integralDetailPresenter.getIntegralDetailList("",1,10);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }

    @Override
    protected BaseQuickAdapter<IntegralDetailBean, BaseViewHolder> getAdapter() {
        return new IntegralDetailAdapter();
    }
}
