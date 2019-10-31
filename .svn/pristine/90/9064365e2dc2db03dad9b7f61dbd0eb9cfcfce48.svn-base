package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.entity.ReportDetailEntity;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * 创建日期：2019/1/28 on 11:29
 * 描述:二级评论详情界面
 * 作者:Ls
 */
public class DiscussDetailActivity extends BaseActivity {
    @Inject
    DiscussDetailFragment mDiscussDetailFragment;
    private ReportDetailEntity.ConmmentNumer mEntity;
    private ImageView mIvBack;
    private int mReportId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discuss_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mIvBack = findViewById(R.id.iv_disucss_detail_bacl);
        Bundle bundle = new Bundle();
        bundle.putSerializable("fatherDiscuss",mEntity);
        bundle.putInt("mReportId",mReportId);
        mDiscussDetailFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mDiscussDetailFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        //一级评论数据已在前一个页面放入到 commNumber实体当中
        mEntity = (ReportDetailEntity.ConmmentNumer) bundle.getSerializable("fatherDiscuss");
        mReportId = bundle.getInt("reportId",1);
    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
