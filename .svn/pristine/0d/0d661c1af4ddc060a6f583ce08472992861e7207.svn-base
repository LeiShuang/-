package com.zfsoft.zfsoft_product.modules.personal.my_collection;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.CollectionBean;
import com.zfsoft.zfsoft_product.entity.MyTrySingleBean;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyCollectionSingleAdapter extends BaseQuickAdapter<CollectionBean,BaseViewHolder>{
    private int type;

    public MyCollectionSingleAdapter(int type) {
        super(R.layout.item_my_try);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBean item) {
        TextView tvWriteReport = helper.getView(R.id.tv_to_write);
        tvWriteReport.setVisibility(View.GONE);
        helper.setText(R.id.tv_try_title,item.getCommoditytitle());
        helper.setText(R.id.tv_try_num,"数量  "+item.getCommoditysum());
        helper.setText(R.id.tv_try_apply,"申请人数  "+item.getApplicantssum());
        ImageLoaderHelper.loadImage(mContext,helper.getView(R.id.iv_try),item.getCommodityu());

    }
}
