package com.zfsoft.zfsoft_product.net;

/**
 * 创建日期：2018/12/24 on 10:16
 * 描述:数据返回回调接口
 * 作者:Ls
 */
public interface CallBackListener<T> {
    /**
     * 成功的回调
     *
     * @param data 业务数据
     */
    void onSuccess(T data);

    /**
     * 失败的回调
     *
     * @param errorMsg 显示的错误信息
     */
    void onError(String errorMsg);
}
