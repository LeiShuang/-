package com.zfsoft.zfsoft_product.modules.personal.my_try;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyTrySingleAdapter extends BaseQuickAdapter<MyTrySingleBean,BaseViewHolder>{
    private int mType;

    public MyTrySingleAdapter(int type) {
        super(R.layout.item_my_try);
        mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyTrySingleBean item) {

        TextView tvWriteReport = helper.getView(R.id.tv_to_write);
        ImageView ivMore = helper.getView(R.id.iv_more);
        if(mType == 3){
            helper.addOnClickListener(R.id.tv_to_write);
            tvWriteReport.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.INVISIBLE);
        }else {
            tvWriteReport.setVisibility(View.INVISIBLE);
            ivMore.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_try_title,item.getCommoditytitle());
        helper.setText(R.id.tv_try_num,"数量  "+item.getCommoditysum());
        helper.setText(R.id.tv_try_apply,"申请人数  "+item.getApplicantssum());
        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_try),item.getCommodityu());
    }
}
