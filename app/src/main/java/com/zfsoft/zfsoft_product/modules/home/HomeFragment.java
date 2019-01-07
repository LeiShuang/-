package com.zfsoft.zfsoft_product.modules.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.youth.banner.Banner;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建日期：2018/12/24 on 10:54
 * 描述:
 * 作者:Ls
 */
@ActivityScoped
public class HomeFragment extends BaseFragment {
    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.et_home_search)
    EditText mEtHomeSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame_home)
    FrameLayout mFrameHome;
    @BindView(R.id.refresh_home)
    RefreshLayout mRefreshHome;
    private int bannerHeight;
    private List<String> mItemList = new ArrayList<>();
    private List<String> mImages = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private HomeAdapter mAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariables() {

    }

    @Inject
    public HomeFragment() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        //初始化ImmersionBar让ToolBar和状态栏不重叠
        ImmersionBar.setTitleBar(getActivity(), mToolbar);
        //初始化数据
        initData();
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRvHome.setLayoutManager(mLayoutManager);
        mAdapter = new HomeAdapter();
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvHome.setAdapter(mAdapter);
        addHeaderView();
        addTwoPhoto();
        mAdapter.setNewData(mItemList);
    }
    //顶部轮播图下方的两张广告位
    private void addTwoPhoto() {
        View twoPic = LayoutInflater.from(getContext()).inflate(R.layout.home_header_two_pic,(ViewGroup) mRvHome.getParent(),false);
        mAdapter.addHeaderView(twoPic,-1);
    }

    private void newData() {
        for (int i = 0; i < 20; i++) {
            mItemList.add("item" + i);
        }
    }

    //给RecyclerView添加头布局
    private void addHeaderView() {
        View mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.home_header_banner,(ViewGroup) mRvHome.getParent(),false);
        Banner banner = mHeaderView.findViewById(R.id.home_banner);
        banner.setImages(mImages)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(5000)
                .start();

        mAdapter.addHeaderView(mHeaderView,-2);
        ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
        ViewGroup.LayoutParams toolBarParams = mToolbar.getLayoutParams();
        bannerHeight = bannerParams.height - toolBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());
    }

    private void initData() {
        //初始化
        for (int i = 0; i < 20; i++) {
            mItemList.add("item" + i);
        }
        mImages.add("http://desk.zol.com.cn/showpic/1024x768_63850_14.html");
        mImages.add("http://desk.zol.com.cn/showpic/1024x768_63850_14.html");
        mImages.add("http://desk.zol.com.cn/showpic/1024x768_63850_14.html");
        mImages.add("http://desk.zol.com.cn/showpic/1024x768_63850_14.html");
    }

    @Override
    protected void initListener() {
       mRvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
           private int totalDy = 0;
           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               totalDy += dy;
               float alpha = totalDy / bannerHeight;
               if (totalDy <= bannerHeight){
                   //如果小于 滑动截止高度（Banner的高度 - ToolBar的高度 - 状态栏高度）
                   mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT, ContextCompat.getColor(getContext(),R.color.colorPrimary),alpha));
               }else {
                   mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT,ContextCompat.getColor(getContext(),R.color.colorPrimary),1));
               }
           }
       });

        mRefreshHome.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                RefreshState state = refreshLayout.getState();
                if (state == RefreshState.PullDownToRefresh){
                    mToolbar.setVisibility(View.GONE);
                }
                refreshLayout.finishRefresh(2000);
                mItemList.clear();
                newData();
                mAdapter.setNewData(mItemList);
                mToolbar.setVisibility(View.VISIBLE);
            }

        });



        mRefreshHome.setOnLoadMoreListener(null);
    }

    @Override
    public void initPresenter() {

    }

}