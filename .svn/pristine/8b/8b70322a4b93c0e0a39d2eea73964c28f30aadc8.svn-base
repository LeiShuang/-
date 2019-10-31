package com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules;


import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.ThingsInfoEntity;
import com.zfsoft.zfsoft_product.utils.ScreenUtils;
import com.zfsoft.zfsoft_product.utils.SizeUtils;

/**
 * 创建日期：2019/1/5 on 14:09
 * 描述:试用Fragment  下的子view适配器
 * 作者:Ls
 */
public class TryUseChildFragmentAdapter extends BaseQuickAdapter<ThingsInfoEntity,BaseViewHolder> {
    public TryUseChildFragmentAdapter() {
        super(R.layout.item_child_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThingsInfoEntity item) {
        if(item.getCommodityurls().size() != 0){
            ViewGroup.LayoutParams params = helper.getView(R.id.iv_child_pic).getLayoutParams();
            params.width = ScreenUtils.getScreenWidth(mContext) - (SizeUtils.dp2px(8,mContext) * 2);
            params.height = ScreenUtils.getScreenWidth(mContext) - (SizeUtils.dp2px(8,mContext) * 2);
            helper.getView(R.id.iv_child_pic).setLayoutParams(params);
            Glide.with(mContext).load(item.getCommodityurls().get(0).getCommodityurl()).placeholder(R.mipmap.icon_default_banner).
                    error(R.mipmap.icon_default_banner).
                    into((ImageView)helper.getView(R.id.iv_child_pic));
        }
        helper.setText(R.id.tv_child_title,item.getCommoditytitle());
        helper.setText(R.id.tv_child_numer,"数量:  " + String.valueOf(item.getCommoditysum()));
        helper.setText(R.id.tv_child_fire, "已有" + String.valueOf(item.getCommoditybrowse()) +"人浏览");
    }
}
