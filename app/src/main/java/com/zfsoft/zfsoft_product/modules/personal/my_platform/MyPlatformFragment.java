package com.zfsoft.zfsoft_product.modules.personal.my_platform;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.PlatformBean;
import com.zfsoft.zfsoft_product.entity.RedBookBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/3/15.
 */
public class MyPlatformFragment extends BaseFragment implements MyPlatformContract.View {

    @Inject
    public MyPlatformFragment() {
    }

    @Inject
    MyPlatformPresenter myPlatformPresenter;

    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rl_platform_back)
    RelativeLayout mRlBack;

    @BindView(R.id.et_number_book)
    EditText mEtRedBookId;//小红书号
    @BindView(R.id.et_nick_book)
    EditText mEtRedBookNick;//小红书昵称
    @BindView(R.id.et_address_book)
    EditText mEtRedBookAddress;//小红书主页
    @BindView(R.id.et_fans_book)
    EditText mEtRedBookFans;


    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_my_plarform;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        myPlatformPresenter.getMyPlatformInfo(Config.HSK,DbHelper.getUserId(mContext));
    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPlatformPresenter.saveMyPlatformInfo(Config.HSK,DbHelper.getUserId(getContext()),
                        mEtRedBookNick.getText().toString().trim(),
                        mEtRedBookId.getText().toString().trim(),
                        mEtRedBookAddress.getText().toString().trim(),
                        mEtRedBookFans.getText().toString().trim());
            }
        });
    }

    @Override
    public void initPresenter() {
        myPlatformPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPlatformPresenter.dropView();
    }


    @Override
    public void getMyPlatformInfoSuccess(RedBookBean redBookBean) {
        if (redBookBean != null) {
            if (redBookBean.getRedAccount() != null && !redBookBean.getRedAccount().equals("")) {
                mEtRedBookId.setText(redBookBean.getRedAccount());
            }

            if (redBookBean.getRedName() != null && !redBookBean.getRedName().equals("")) {
                mEtRedBookNick.setText(redBookBean.getRedName());
            }

            if (redBookBean.getRedHome() != null && !redBookBean.getRedHome().equals("")) {
                mEtRedBookAddress.setText(redBookBean.getRedHome());
            }

            if (redBookBean.getRedFans() != null && !redBookBean.getRedFans().equals("")) {
                mEtRedBookFans.setText(redBookBean.getRedFans());
            }
        }
    }

    @Override
    public void getMyPlatformInfoFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void saveMyPlatformInfoSuccess(SignBean signBean) {
        String msgtype = signBean.getMsgtype();
        if(msgtype.equals("1")){
            ToastUtils.showCenterToast(getContext(),"保存成功");
            getActivity().finish();
        }else {
            ToastUtils.showCenterToast(getContext(),"保存失败");
        }

    }

    @Override
    public void saveMyPlatformInfoFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        if (immersionBar == null) {
            return;
        }
        immersionBar.statusBarDarkFont(true);
        immersionBar.statusBarColor(R.color.colorWhite)
                .init();
    }
}
