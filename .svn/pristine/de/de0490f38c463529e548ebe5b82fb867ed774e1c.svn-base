package com.zfsoft.zfsoft_product.modules.send_report;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;

import java.util.List;

/**
 * 创建日期：2019/3/16 on 13:53
 * 描述:首页view和presenter接口
 * 作者:Ls
 */
public interface SendReportContract {
    interface View extends BaseView{
        void getReportSizeSuccess(ResponseListInfo<MyTrySingleBean> data);
        void getReportSizeFailed(String errorMsg);
    }

    interface Presenter extends BasePresenter<View>{
        void getReportSize(String hsk,String userId,String commoditytype,int page,int size);
    }
}
