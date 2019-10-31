package com.zfsoft.zfsoft_product.modules.report.other_info;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.OtherBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.entity.UserBean;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class OtherInformationContract {
    public interface View extends BaseListView<TestReportBean> {
        void getOtherPersonSuccess(OtherBean userBean);
        void getOtherPersonFailure(String errorMsg);
        void addAttentionSuccess(SignBean signBean);
        void addAttentionFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View> {
        void getMyReportList(String hsk,String userId,int page,int size);
        void getOtherPersonInfo(String hsk,String userId,String otherId);
        //关注别人
        void addAttention(String hsk,String userId,String targetId,String type);
    }
}