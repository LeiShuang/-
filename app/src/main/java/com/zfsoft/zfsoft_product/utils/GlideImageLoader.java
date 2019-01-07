package com.zfsoft.zfsoft_product.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;
import com.zfsoft.zfsoft_product.R;

/**
 * @author geyifeng
 * @date 2017/6/4
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .apply(new RequestOptions().placeholder(R.mipmap.contact_set_header))
                .into(imageView);
    }

}
