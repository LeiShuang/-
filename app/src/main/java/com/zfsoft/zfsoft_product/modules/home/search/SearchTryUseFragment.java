package com.zfsoft.zfsoft_product.modules.home.search;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;
import com.zfsoft.zfsoft_product.modules.try_use.try_use_detail.TryUseDetailActivity;
import com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules.TryUseChildFragmentAdapter;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/4/6.
 */
public class SearchTryUseFragment extends BaseListFragment<ThingsInfoEntity,SearchTryUserPresenter> implements
SearchTryUseContract.View{

    private TryUseChildFragmentAdapter mAdapter;

    public static SearchTryUseFragment newInstance() {

        Bundle args = new Bundle();

        SearchTryUseFragment fragment = new SearchTryUseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String mTitle;

    public void setSearchTitle(String title){
        mTitle = title;
        loadData();
    }

    @Inject
    SearchTryUserPresenter mPresenter;

    @Inject
    public SearchTryUseFragment() {
    }

    @Override
    protected void setSpecificPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    public void loadData() {
        super.loadData();
        mPresenter.getThingsListInfo(Config.HSK,"",mTitle,0,start_page,PAGE_SIZE);
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        Intent intent = new Intent(getActivity(), TryUseDetailActivity.class);
        Bundle bundle = new Bundle();
        ThingsInfoEntity entity = (ThingsInfoEntity) adapter.getData().get(position);
        bundle.putString("thingsId",entity.getCommodityid());
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
    protected BaseQuickAdapter<ThingsInfoEntity, BaseViewHolder> getAdapter() {
        mAdapter = new TryUseChildFragmentAdapter();
        return mAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }
}
