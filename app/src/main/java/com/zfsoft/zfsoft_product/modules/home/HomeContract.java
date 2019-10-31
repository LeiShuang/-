package com.zfsoft.zfsoft_product.modules.home;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.BannerBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.TestPersonBean;
import com.zfsoft.zfsoft_product.entity.TestReportBean;
import com.zfsoft.zfsoft_product.entity.TryNewProductBean;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/18.
 */
public class HomeContract {
    public interface View extends BaseView{
        void getBannerListSuccess(List<BannerBean> list);
        void getBannerListFailure(String errorMsy);
        void getTryNewProductListSuccess(List<TryNewProductBean> list);
        void getTryNewProductListFailure(String errorMsy);
        void getTestReportListSuccess(List<TestReportBean> list);
        void getTestReportListFailure(String errorMsg);
        void getTestPersonListSuccess(List<TestPersonBean> list);
        void getTestPersonListFailure(String errorMsg);
        void addAttentionSuccess(SignBean signBean);
        void addAttentionFailure(String errorMsg);
        //申请
        void TryUseSuccess(SignBean info);
        void TryUseFailed(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View> {
        void getBannerList(String hsk);
        //首页试用新品
        void getTryNewProductList(String hsk,String userId);

        //申请试用新品
        void tryUseProDuct(String hsk,String productId,String userId);

        //首页试用报告
        void getTestReportList(String hsk);

        //试用达人
        void getTestPersonList(String hsk,String userId);

        //关注别人
        void addAttention(String hsk,String userId,String targetId,String type);

    }
}