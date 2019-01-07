package com.zfsoft.zfsoft_product.base;

/**
 * 创建日期：2018/12/24 on 9:59
 * 描述:
 * 作者:Ls
 */
public interface BaseView {

    /**
     * 实现Baseview的View在这个方法里初始化presenter
     */
    void initPresenter();

    /**
     * 判断Fragment是否依附在Activity上
     *
     * @return true:依附在Activity上  false:没有依附在Activity上
     */
    boolean isActive();
}
