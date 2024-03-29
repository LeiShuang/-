package com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryPresenter;
import com.zfsoft.zfsoft_product.modules.report.report_child.ChildItemDecoration;
import com.zfsoft.zfsoft_product.modules.try_use.RadioButtonCallBackListener;
import com.zfsoft.zfsoft_product.modules.try_use.try_use_detail.TryUseDetailActivity;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

import javax.inject.Inject;

/**
 * 创建日期：2019/1/5 on 13:17
 * 描述:试用Fragment 下viewpager所使用的Fragment
 * 作者:Ls
 */
@ActivityScoped
public class TryUseViewPagerFragment extends BaseListFragment<ThingsInfoEntity,MyTryPresenter> implements  TryUseChildFragmentContract.View,RadioButtonCallBackListener{

    private int mType;//当前fragment的分类
    private String mListSortType;//"0"是默认最新；"1"是最火
    private TryUseChildFragmentAdapter mAdapter;

    @Inject
    public TryUseViewPagerFragment() {

    }


    @Override
    protected void initVariables() {
        mListSortType = "0";//给一个默认值"0",通过用户和手机交互来改变该值
    }

    @Inject
    TryUseChildFragmentPresenter mPresenter;

    public static TryUseViewPagerFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type",type);
        TryUseViewPagerFragment fragment = new TryUseViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void handleBundle(Bundle bundle) {
        if (bundle != null){
            mType = bundle.getInt("type");
        }
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new TryUseChildDecoration(SizeUtils.dp2px(8,getContext()));
    }

    @Override
    protected void setSpecificPresenter() {
        mPresenter.takeView(this);
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

    public RadioButtonCallBackListener getRadioButtonListener(){
        return this;
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
         mPresenter.getThingsListInfo(Config.HSK,mListSortType,"",mType,start_page,PAGE_SIZE);
    }

    @Override
    public void initPresenter() {
        super.initPresenter();
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


    @Override
    public void changeSortType(String sortType) {
        mListSortType = sortType;
        start_page = 1;
        loadData();
    }
}
