package com.zfsoft.zfsoft_product.modules.personal.my_try;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyTryContract {
    public interface View extends BaseListView<MyTrySingleBean>{

    }

    public interface Presenter extends BasePresenter<View>{
        void getMyTryList(String hsk,String userId,String commoditytype,int page,int size);
    }
}
