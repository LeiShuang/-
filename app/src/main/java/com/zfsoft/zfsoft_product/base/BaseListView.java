package com.zfsoft.zfsoft_product.base;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;

import java.util.List;

/**
 * 创建日期：2019/1/8 on 9:46
 * 描述:所有有滚动加载的情况协议接口都继承这个接口
 * 作者:Ls
 */
public interface BaseListView<T> extends BaseView {
    /**
     * 数据获取成功
     *
     * @param info 列表信息
     */
    void loadSuccess(ResponseListInfo<T> info);

    /**
     * 数据获取失败
     *
     * @param errorMsg 失败的信息
     */
    void loadFailure(String errorMsg);

    /**
     * 判断数据是否为空
     *
     * @param list 数据集合
     * @return true :为空 false: 不为空
     */
    boolean checkDataIsNull(List<T> list);

    /**
     * 初始化空视图
     * */
    void showEmptyView();

    /**
     * 重新加载
     * */
    void showNoNetReLoad();

    /**
     * 显示正常布局
     * */

    void showNormalContentView();

    /**
     * 加载loading图片
     * */
    void showLoadingView();
    /**
     * 显示错误布局
     * */
    void showErrorView();
    /**
     * 隐藏loading
     *
     * */
    void hideLoadingView();


    /**
     * 下拉刷新回调
     * */
    void onRefreshing(RefreshLayout refreshLayout);

    /**
     * 上拉加载更多
     * */

    void onLoadMoreData(RefreshLayout refreshLayout);

    /**
     * 初始化加载框架设置
     * */
    void initRefreshSetting(SmartRefreshLayout smartRefreshLayout);

    /**
     * 处理没有网络情况
     * */
    void onNoNetReload();


}