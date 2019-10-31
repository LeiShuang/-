package com.zfsoft.zfsoft_product.modules.report.report_child;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.TestReportBean;

/**
 * 创建日期：2019/1/25 on 17:28
 * 描述:
 * 作者:Ls
 */
public interface ReportChildDetailContract {
    interface View extends BaseListView<TestReportBean>{

    }

    interface Presenter extends BasePresenter<View>{
        void getHotTestReport(String hsk,String title,String type,int page,int size);
    }
}