package com.zfsoft.zfsoft_product.modules.report.submit_report;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lzy.imagepicker.bean.ImageItem;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * 创建日期：2019/1/23 on 11:25
 * 描述:发布图片的界面,接收参数 thingsId   ===》产品id，用来判断是发布哪个产品的报告
 * 作者:Ls
 */
public class SubmitReportActivity extends BaseActivity {
    public static final int IMAGE_ITEM_ADD = -1;
    private ArrayList<ImageItem> mMSelectedImages;
    @Inject
    SubmitReportFragment mSubmitReportFragment;
    private String mProductId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        if (mProductId != null){
            bundle.putString("thingsId",mProductId);
            mSubmitReportFragment.setArguments(bundle);
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mSubmitReportFragment, R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mProductId = bundle.getString("thingsId",null);
    }

    @Override
    protected void initListener() {

    }
}
