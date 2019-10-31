package com.zfsoft.zfsoft_product.modules.report;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.ProductTypeEntity;

import java.util.List;

/**
 * 创建日期：2019/2/15 on 17:39
 * 描述:
 * 作者:Ls
 */
public interface ReportContract {
    interface View extends BaseView{
        void getTypeSuccess(List<ProductTypeEntity> info);
        void getTypeFailed(String msg);
    }

    interface Presenter extends BasePresenter<View>{
        void getProductType(String hsk);
    }
}
