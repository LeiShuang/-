package com.zfsoft.zfsoft_product.modules.personal.message.message_list;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.entity.MessageListBean;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class MessageListContract {
    public interface View extends BaseListView<MessageListBean>{

    }

    public interface Presenter extends BasePresenter<View>{
    }
}
