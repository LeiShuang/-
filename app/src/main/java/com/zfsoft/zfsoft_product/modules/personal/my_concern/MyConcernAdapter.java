package com.zfsoft.zfsoft_product.modules.personal.my_concern;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ConcernBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;



/**
 * Created by ckw
 * on 2019/2/22.
 */
public class MyConcernAdapter extends BaseQuickAdapter<ConcernBean,BaseViewHolder> {
    private int mType;

    public MyConcernAdapter(int type) {
        super(R.layout.item_my_concern);
        this.mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ConcernBean item) {
        TextView tvConcern = helper.getView(R.id.tv_concern);

        if(mType == 0){
            helper.setText(R.id.tv_try_title,item.getAttentionname());
            helper.setText(R.id.tv_try_num,item.getAttentionintroduction());

            if(item.getZt().equals("0")){
                if(item.getAttentionnstatus() != null && item.getAttentionnstatus().equals("0")){
                    tvConcern.setText("互相关注");
                    tvConcern.setBackgroundResource(R.drawable.background_full_concern);
                }else {
                    tvConcern.setText("已关注");
                    tvConcern.setBackgroundResource(R.drawable.background_full_concern);
                }

            }else if(item.getZt().equals("1")){
                tvConcern.setText("关注");
                tvConcern.setBackgroundResource(R.drawable.background_text_concern);
            }

        }else {
            helper.setText(R.id.tv_try_title,item.getFansname());
            helper.setText(R.id.tv_try_num,item.getFansintroduction());

            if(item.getZt().equals("0")){
                if(item.getFansstatus() != null && item.getFansstatus().equals("0")){
                    tvConcern.setText("互相关注");
                    tvConcern.setBackgroundResource(R.drawable.background_full_concern);
                }else {
//                    tvConcern.setText("已关注");
//                    tvConcern.setBackgroundResource(R.drawable.background_full_concern);
                    tvConcern.setText("关注");
                    tvConcern.setBackgroundResource(R.drawable.background_text_concern);
                }

            }else if(item.getZt().equals("1")){
                tvConcern.setText("关注");
                tvConcern.setBackgroundResource(R.drawable.background_text_concern);
            }

        }

        helper.addOnClickListener(R.id.tv_concern);


        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_my_concern),item.getImageurl());
    }
}
