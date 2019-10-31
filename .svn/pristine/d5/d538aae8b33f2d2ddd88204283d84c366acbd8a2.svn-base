package com.zfsoft.zfsoft_product.modules.try_use.share_redbook;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.SignBean;

import java.util.Map;

import retrofit2.http.FieldMap;

/**
 * 创建日期：2019/2/26 on 18:35
 * 描述:
 * 作者:Ls
 */
public interface ShareRedBookContract {
    interface View extends BaseView{
        void submitShareUrlSuccess(SignBean info);
        void submitFailed(String errorMsg);
    }

    interface Presenter extends BasePresenter<View>{
        void submitShareUrl(Map<String, String> params);
    }
}
