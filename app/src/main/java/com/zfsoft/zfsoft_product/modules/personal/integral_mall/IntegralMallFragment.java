package com.zfsoft.zfsoft_product.modules.personal.integral_mall;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.entity.IntegralBean;
import com.zfsoft.zfsoft_product.modules.personal.integral_mall.integral_detail.IntegralDetailActivity;
import com.zfsoft.zfsoft_product.modules.report.report_child.ChildItemDecoration;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class IntegralMallFragment extends BaseListFragment<IntegralBean,IntegralMallPresenter>
        implements IntegralMallContract.View{

    private TextView mTvCurrentIntegral;
    private TextView mTvIntegralDetail;
    @Inject
    public IntegralMallFragment( ) {
    }

    @Inject
    IntegralMallPresenter integralMallPresenter;

    @Override
    protected void setSpecificPresenter() {
        integralMallPresenter.takeView(this);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.integral_header,null);
        mTvCurrentIntegral = view.findViewById(R.id.tv_current_integral);
        mTvIntegralDetail = view.findViewById(R.id.tv_integral_detail);
        adapter.addHeaderView(view);

        View titleView  = LayoutInflater.from(getContext()).inflate(R.layout.integral_tip,null);
        adapter.addHeaderView(titleView);

        mTvIntegralDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), IntegralDetailActivity.class);
            }
        });
    }



    @Override
    public void loadData() {
        super.loadData();
        integralMallPresenter.getIntegralProductList("",1,10);
    }



    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new IntegralItemDecoration(SizeUtils.dp2px(8,getContext()));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        integralMallPresenter.dropView();
    }

    @Override
    protected BaseQuickAdapter<IntegralBean, BaseViewHolder> getAdapter() {
        return new IntegralMallAdapter();
    }
}