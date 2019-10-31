package com.zfsoft.zfsoft_product.modules.personal.message.message_list;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.entity.MessageListBean;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class MessageListFragment extends BaseListFragment<MessageListBean,MessageListPresenter> {
    @Override
    protected void setSpecificPresenter() {

    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }

    @Override
    protected BaseQuickAdapter<MessageListBean, BaseViewHolder> getAdapter() {
        return null;
    }
}
