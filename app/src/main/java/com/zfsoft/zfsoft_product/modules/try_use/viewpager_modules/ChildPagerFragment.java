package com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2019/1/5 on 13:17
 * 描述:viewpager下面的Fragment
 * 作者:Ls
 */
public class ChildPagerFragment extends Fragment {

    private String mIndex;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ChildFragmentAdapter mAdapter;
    private List<ThingsInfoEntity> mInfoLists;

    public static ChildPagerFragment newInstance(String index) {

        Bundle args = new Bundle();
        args.putString("index",index);
        ChildPagerFragment fragment = new ChildPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_fragemnt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
    }
    //初始化监听
    private void initListener() {

    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_try_child);
        mRecyclerView = view.findViewById(R.id.try_child_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ChildFragmentAdapter(R.layout.item_child_view,mInfoLists);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.scrollToPosition(0);
        mAdapter.setNewData(mInfoLists);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000,false);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleArguments();
        initData();
    }
    //初始化数据
    private void initData() {
        mInfoLists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ThingsInfoEntity entity = new ThingsInfoEntity();
            entity.setTitle("这是物品 " + i);
            entity.setSize("50ml");
            entity.setNumber("数量: " + i);
            entity.setPrice("售价 ￥" + i);
            entity.setFireNumber(String.valueOf(i));
            mInfoLists.add(entity);
        }
    }

    //处理Activity界面传递过来的参数
    private void handleArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mIndex = bundle.getString("index","");
        }
    }
}
