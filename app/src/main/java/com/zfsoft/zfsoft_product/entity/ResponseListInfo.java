package com.zfsoft.zfsoft_product.entity;

import java.util.List;

/**
 * @Description: 所有的滚动加载数据格式都是这样的
 */

public class ResponseListInfo<T> {

    private int size; //是否已经结束了
    private List<T> data; //业务数据

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setData(List<T> itemList) {
        this.data = itemList;
    }

    public List<T> getData() {
        return data;
    }

}
