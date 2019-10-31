package com.zfsoft.zfsoft_product.modules.report.report_detail;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ReportDetailEntity;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;

import java.net.URLDecoder;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/8 on 20:27
 * 描述:评论界面多种类型适配器
 * 作者:Ls
 */
public class ReportDetailAdapter extends BaseMultiItemQuickAdapter<ReportDetailEntity,BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ReportDetailAdapter(List<ReportDetailEntity> data) {
        super(data);
        addItemType(ReportDetailEntity.VIEW_ITEM_TYPE_FATHER, R.layout.report_father_item);
        addItemType(ReportDetailEntity.VIEW_ITEM_TYPE_CHILD, R.layout.item_discuss_child);
        addItemType(ReportDetailEntity.VIEW_ITEM_TPE_COMMENT,R.layout.item_comment_number);
        //item_discuss_child
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportDetailEntity item) {
        switch (item.getItemType()) {
            case ReportDetailEntity.VIEW_ITEM_TYPE_FATHER:
                ImageLoaderHelper.loadHeadImage(mContext, (CircleImageView) helper.getView(R.id.iv_father_pic),item.getDiscussFatherEntity().getFatherPicUrl());
                helper.setText(R.id.tv_father_star, String.valueOf(item.getDiscussFatherEntity().getDiscussStarNumber()));
                helper.setText(R.id.tv_report_father_name, item.getDiscussFatherEntity().getFatherName());
                if (item.getDiscussFatherEntity().isHasstar()){
                    helper.setImageResource(R.id.iv_father_star,R.mipmap.ico_has_star);
                }else {
                    helper.setImageResource(R.id.iv_father_star,R.mipmap.ico_unstar);
                }

                helper.setText(R.id.tv_father_content, item.getDiscussFatherEntity().getDiscussContent());
                helper.setText(R.id.tv_discuss_father_time, item.getDiscussFatherEntity().getDiscussTime())
                        .addOnClickListener(R.id.iv_father_pic)
                        .addOnClickListener(R.id.tv_reply)
                        .addOnClickListener(R.id.iv_father_star);
                break;

            case ReportDetailEntity.VIEW_ITEM_TYPE_CHILD:
                helper.setText(R.id.tv_child_content,item.getDiscussChildEntity().getName() + "  回复 " + item.getDiscussChildEntity().getRelayName() +  " : " +item.getDiscussChildEntity().getCommment())
                        .addOnClickListener(R.id.tv_child_content);
                break;

             case ReportDetailEntity.VIEW_ITEM_TPE_COMMENT:
                 if (item.getCommentNumber() != null){
                     helper.setText(R.id.tv_sum_comment,"查看全部" + String.valueOf(item.getCommentNumber().getNumber()) + "条回复")
                             .addOnClickListener(R.id.tv_sum_comment);
                 }

                 break;
        }
    }


}