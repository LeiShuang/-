package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.TestreportreplyListBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;

import java.net.URLDecoder;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/28 on 11:53
 * 描述:
 * 作者:Ls
 */
public class DiscussDetailAdapter extends BaseQuickAdapter<TestreportreplyListBean, BaseViewHolder> {
    public DiscussDetailAdapter() {
        super(R.layout.discuss_child_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestreportreplyListBean item) {
        ImageLoaderHelper.loadHeadImage(mContext, (CircleImageView) helper.getView(R.id.circle_image), item.getReplyurl());
        helper.setText(R.id.tv_discuss_child_name, item.getReplyname() + " 回复 " + item.getReplytoname() + " : ");
        helper.setText(R.id.tv_child_discuss_content, item.getReplydetail());
        helper.setText(R.id.tv_child_discuss_time, item.getReplydate())
                .addOnClickListener(R.id.circle_image);
    }
}