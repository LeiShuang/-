package com.zfsoft.zfsoft_product.modules.personal.account_address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.AddressBean;
import com.zfsoft.zfsoft_product.modules.personal.account_address.add.NewAddAddressActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class AccountAddressFragment extends BaseFragment implements AccountAddressContract.View {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;

    @BindView(R.id.srl_address)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;

    private RelativeLayout mRlAddNewAddress;

    private AccountAddressAdapter mAdapter;

    @Inject
    public AccountAddressFragment() {
    }

    private List<AddressBean> mList;

    @Inject
    AccountAddressPresenter mAccountAddressPresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_account_address;
    }

    @Override
    protected void initVariables() {
        mList = new ArrayList<>();
    }

    @Override
    protected void handleBundle(Bundle bundle) {


    }

    @Override
    protected void operateViews(View view) {
        mSmartRefreshLayout.setEnableRefresh(false);
        mRvAddress.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new AccountAddressAdapter();
        mRvAddress.setAdapter(mAdapter);
        mAccountAddressPresenter.getMyAddressList(Config.HSK, DbHelper.getUserId(mContext));

        View foot = LayoutInflater.from(mContext).inflate(R.layout.foot_address,null);
        mRlAddNewAddress = foot.findViewById(R.id.rl_add_address);
        mAdapter.addFooterView(foot);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(),NewAddAddressActivity.class);
                intent.putExtra("bean",mList.get(position));
                AccountAddressFragment.this.startActivityForResult(intent,100);
            }
        });
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mRlAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),NewAddAddressActivity.class);
                AccountAddressFragment.this.startActivityForResult(intent,100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAccountAddressPresenter.getMyAddressList(Config.HSK, DbHelper.getUserId(mContext));
    }

    @Override
    public void initPresenter() {
        mAccountAddressPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAccountAddressPresenter.dropView();
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

    @Override
    public void getMyAddressListSuccess(List<AddressBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.setNewData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMyAddressListFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }
}