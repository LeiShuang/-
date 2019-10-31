package com.zfsoft.zfsoft_product.modules.personal.my_concern;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/2/22.
 * 我的关注或者我的粉丝
 */
public class MyConcernActivity extends BaseActivity{

    @Inject
    MyConcernFragment myConcernFragment;

    private RelativeLayout mRlBack;

    private int mType;//0 我的关注 1我的粉丝
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRlBack = findViewById(R.id.rl_report_back);
        TextView tvTitle = findViewById(R.id.tv_title);
        if(mType == 0){
            tvTitle.setText("我的关注");
        }else {
            tvTitle.setText("我的粉丝");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("type",mType);
        myConcernFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), myConcernFragment,R.id.fragment_container);

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mType = bundle.getInt("type");
    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true,0.2f).init();
    }
}
