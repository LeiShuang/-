package com.zfsoft.zfsoft_product.modules.personal.my_concern;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.ConcernBean;
import com.zfsoft.zfsoft_product.entity.SignBean;

/**
 * Created by ckw
 * on 2019/2/22.
 */
public class MyConcernContract {
    public interface View extends BaseListView<ConcernBean>{
        void addAttentionSuccess(SignBean signBean);
        void addAttentionFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void getMyConcernList(String hsk,String userId,int page,int size);
        void getMyFansList(String hsk,String userId,int page,int size);
        //关注别人
        void addAttention(String hsk,String userId,String targetId,String type);
    }
}
