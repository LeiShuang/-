package com.zfsoft.zfsoft_product.modules.personal.message;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.MessageItemBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class MessageAdapter extends BaseQuickAdapter<MessageItemBean,BaseViewHolder> {

    public MessageAdapter(List<MessageItemBean> data) {
        super(R.layout.item_message,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageItemBean item) {
        helper.setText(R.id.tv_message_title,item.getTitle());
        helper.setText(R.id.tv_message_content,item.getContent());
        helper.setText(R.id.tv_show_time,item.getTime());

        Glide.with(mContext).load(item.getImgUrl()).into((CircleImageView)helper.getView(R.id.iv_head));

    }
}
