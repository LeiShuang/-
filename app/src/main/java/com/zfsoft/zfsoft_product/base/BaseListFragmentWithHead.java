package com.zfsoft.zfsoft_product.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingle.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtool.RxNetTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/31.
 */
public abstract class  BaseListFragmentWithHead <T,K extends BasePresenter> extends BaseFragment implements BaseListView<T>{
    @BindView(R.id.common_recycler)
    RecyclerView mCommonRecycler;
    @BindView(R.id.common_refresh)
    SmartRefreshLayout mCommonRefresh;
    @BindView(R.id.ll_refresh_noData)
    RelativeLayout mLlRefreshNoData;
    @BindView(R.id.ll_net_error)
    RelativeLayout mLlNetError;
    @BindView(R.id.ll_refresh_error)
    RelativeLayout mLlRefreshError;
    @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    @BindView(R.id.iv_noData)
    ImageView mIvNoData;
    @BindView(R.id.iv_no_net)
    ImageView mIvNoNet;
    @BindView(R.id.iv_error)
    ImageView mIvError;
    protected int start_page = 1; //起始页
    protected static final int PAGE_SIZE = Config.LOADMORE.PAGE_SIZE; //每页加载多少条数据
    private BaseQuickAdapter mAdapter;


    @Override
    protected int getLayoutResID() {
        return R.layout.common_refresh_layout;
    }

    @Override
    protected void initVariables() {

    }


    @Override
    protected void handleBundle(Bundle bundle) {

    }



    @Override
    protected void operateViews(View view) {

        dealWithNoNet();
        initRefreshSetting(mCommonRefresh);
        setDefaultRecyclerView();
        if (!RxNetTool.isAvailable(mContext)){
            showNoNetReLoad();
        }
    }

    protected abstract void setSpecificPresenter();

    private void setDefaultRecyclerView() {
        mCommonRecycler.setLayoutManager(getLayoutManager());
        mCommonRecycler.addItemDecoration(getItemDecoration());

        mAdapter = getAdapter();
        mAdapter.openLoadAnimation();
        mCommonRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onItemCLick(adapter,position);
            }
        });
        initHeader(mAdapter);
        initFootView(mAdapter);
        loadData();
    }

    protected abstract void onItemCLick(BaseQuickAdapter adapter, int position);

    protected abstract void initFootView(BaseQuickAdapter adapter);

    protected abstract void initHeader(BaseQuickAdapter adapter);





    /**
     * 加载数据
     * */
    public  void loadData(){

        showLoadingView();

    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.common_divider));
        return itemDecoration;
    }

    /**
     * @des 获取LayoutManager  默认单列，子类修改只需重写该方法
     *
     * @return LayoutManager实例
     */
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }
    //先处理无网络和错误布局
    private void dealWithNoNet() {
        mIvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoNetReload();
                return;
            }
        });
        mLlNetError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RxNetTool.isAvailable(getContext())){
                    onNoNetReload();
                    return;
                }

                ToastUtils.showCenterToast(getContext(),"网络异常");
            }
        });
    }

    @Override
    public void showErrorView() {
        mLlNetError.setVisibility(View.GONE);
        mLlRefreshNoData.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.VISIBLE);
        mCommonRefresh.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void loadSuccess(ResponseListInfo<T> info) {
        RefreshState refreshState = mCommonRefresh.getState();
        if (info != null) {
            mSize = info.getSize();
        }


        if (refreshState == RefreshState.Refreshing || refreshState == RefreshState.None){
            hideLoadingView();
            //此时是刷新状态
            //如果数据为空，则显示错误布局
            if (info == null){
                mCommonRefresh.finishRefresh(1500,false);
                showErrorView();
                return;
            }

            if (info.getData().size() == 0){
                mCommonRefresh.finishRefresh(1500,true);
//                showEmptyView();
                return;
            }

            if (0 <  info.getData().size() && info.getData().size() <= PAGE_SIZE ){
                mCommonRefresh.finishRefresh(1500,true);
                mAdapter.setNewData(info.getData());
                showNormalContentView();
                return;
            }



        }else if (refreshState == RefreshState.Loading){
            hideLoadingView();
            //此时是加载更多状态,已经加载完全部数据
            if (mSize == 0){
                mCommonRefresh.finishLoadMore(1500,true,true);
                return;

            }
            if (isOvered()){
                mCommonRefresh.finishLoadMore(1500,true,true);
                showNormalContentView();
                mAdapter.addData(info.getData());
                return;
            }

            if (!isOvered()){
                mCommonRefresh.finishLoadMore(1500,true,false);
                showNormalContentView();
                mAdapter.addData(info.getData());
                return;
            }
        }


    }

    @Override
    public void loadFailure(String errorMsg) {
        showErrorView();
    }

    @Override
    public boolean checkDataIsNull(List<T> list) {
        return list == null || list.size() == 0;
    }

    @Override
    public void showEmptyView() {
        mLlNetError.setVisibility(View.GONE);
        mLlRefreshNoData.setVisibility(View.VISIBLE);
        mCommonRefresh.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.GONE);
    }
    /**
     * 显示没有网络的布局
     * */
    @Override
    public void showNoNetReLoad() {
        mLlNetError.setVisibility(View.VISIBLE);
        mLlRefreshNoData.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.GONE);
        mCommonRefresh.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.GONE);

    }
    /**
     * @des  显示正常布局
     * */
    @Override
    public void showNormalContentView() {
        mLlNetError.setVisibility(View.GONE);
        mLlRefreshNoData.setVisibility(View.GONE);
        mCommonRefresh.setVisibility(View.VISIBLE);
        mLlRefreshError.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.GONE);
    }



    /**
     * 加载loading图
     * */
    @Override
    public void showLoadingView() {
        RefreshState refreshState = mCommonRefresh.getState();
        if(refreshState == RefreshState.None){
            mLoadingView.setLoadingText("正在加载,请稍后");
            mLoadingView.setVisibility(View.VISIBLE);
        }else {
            mLoadingView.setVisibility(View.GONE);
        }
        mLlNetError.setVisibility(View.GONE);
        mLlRefreshNoData.setVisibility(View.GONE);
        mCommonRefresh.setVisibility(View.VISIBLE);
        mLlRefreshError.setVisibility(View.GONE);
    }
    /**
     * 关闭loading图
     * */
    @Override
    public void hideLoadingView() {
        mCommonRefresh.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mLlNetError.setVisibility(View.GONE);
        mLlRefreshNoData.setVisibility(View.GONE);
        mLlRefreshError.setVisibility(View.GONE);
    }

    /**
     * 获取RecyclerArrayAdapter实例
     *
     * @return RecyclerArrayAdapter实例
     */
    protected  abstract BaseQuickAdapter<T,BaseViewHolder> getAdapter();
    /**
     * 刷新操作
     * */
    @Override
    public void onRefreshing(RefreshLayout refreshLayout) {
        start_page = 1;
        loadData();

    }
    /**
     * 加载更多操作
     * */
    @Override
    public void onLoadMoreData(RefreshLayout refreshLayout) {
        start_page++;
        loadData();

    }
    /**
     * @Des 初始化refreshLayout
     * */
    @Override
    public void initRefreshSetting(SmartRefreshLayout smartRefreshLayout) {
        mCommonRefresh.setBackgroundResource(R.color.lightgray);
        //添加刷新监听
        mCommonRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                onRefreshing(refreshLayout);
            }
        });
        mCommonRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                onLoadMoreData(refreshLayout);
            }
        });

    }
    /**
     * 网络异常 重新加载按钮点击回调监听方法
     */
    @Override
    public void onNoNetReload() {
        loadData();
    }


    @Override
    public void initPresenter() {
        setSpecificPresenter();
    }

    private int mSize;
    private boolean isOvered(){
        if (mSize < PAGE_SIZE) {
            return true;//已经结束
        }else {
            return false;
        }
    }


}