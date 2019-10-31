package com.zfsoft.zfsoft_product.modules.home.search;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;

/**
 * Created by ckw
 * on 2019/4/6.
 */
public interface SearchReportContract {
    public interface View extends BaseListView<TestReportBean> {

    }

    public interface Presenter extends BasePresenter<View>{
        void getSearchReportList(String hsk,String title,int page,int size);
    }
}
