package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.ProductInfo;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.ResponseListInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;

import java.util.Map;

/**
 * 创建日期：2019/1/21 on 17:03
 * 描述:
 * 作者:Ls
 */
public interface TryUseDetailContract  {
    interface UseDetailView extends BaseView {
        //获取数据
        void getInfoSuccess(ThingsInfoEntity info);
        void getInfoFailed(String errorMsg);
        //收藏商品
        void collectSuccess(SignBean info);
        void collectFailed(String errorMsg);

        //申请
        void TryUseSuccess(SignBean info);
        void TryUseFailed(String errorMsg);

        void submitShareUrlSuccess(SignBean info);
        void submitShareUrlFailed(String errorMsg);
    }

    interface UseDetailPresenter extends BasePresenter<UseDetailView>{
        void getProductsDetails(String hsk,String id,String userId);

        void collectProduct(String hsk,String productId,String userId,int type);

        void tryUseProDuct(String hsk,String productId,String userId);

        void submitShareUrl(Map<String,String> map);
    }
    /***
     * 某个商品报告列表视图
     */

    interface UseProductView extends BaseListView<ReportInfo>{

    }

    interface  TryUseReportPresenter extends BasePresenter<UseProductView>{
        void getOneProductsTestReport(String hsk,String id,int page,int size);
    }
}
