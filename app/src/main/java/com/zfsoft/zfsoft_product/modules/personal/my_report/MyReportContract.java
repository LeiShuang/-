package com.zfsoft.zfsoft_product.modules.personal.my_report;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.TestReportBean;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyReportContract {
    public interface View extends BaseListView<TestReportBean> {

    }

    public interface Presenter extends BasePresenter<View> {
        void getMyReportList(String hsk,String userId,int page,int size);
    }
}
