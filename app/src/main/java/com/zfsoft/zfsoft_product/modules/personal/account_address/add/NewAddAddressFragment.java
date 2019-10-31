package com.zfsoft.zfsoft_product.modules.personal.account_address.add;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hengyi.wheelpicker.listener.OnCityWheelComfirmListener;
import com.hengyi.wheelpicker.ppw.CityWheelPickerPopupWindow;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.AddressBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.PhoneUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;
import com.zfsoft.zfsoft_product.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class NewAddAddressFragment extends BaseFragment implements NewAddAddressContract.View {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;//返回
    @BindView(R.id.tv_save)
    TextView mTvSave;//保存
    @BindView(R.id.iv_select_address)
    ImageView mIvSelectAddress;
    @BindView(R.id.rl_select_address)
    RelativeLayout mRlSelectAddress;
    @BindView(R.id.tv_province)
    TextView mTvProvince;//省份
    @BindView(R.id.tv_city)
    TextView mTvCity;//市
    @BindView(R.id.tv_district)
    TextView mTvDistrict;//区
    @BindView(R.id.et_name)
    EditText mEtName;//收货人姓名
    @BindView(R.id.et_phone)
    EditText mEtPhone;//收货人手机号
    @BindView(R.id.et_address)
    EditText mEtAddress;//详细地址
    @BindView(R.id.btn_switch)
    SwitchButton mBtnSwitch;//选择默认地址

    private String mDefaultAddress = "1";//0 默认 1不默认

    private String mProvince;
    private String mCity;
    private String mDistrict;
    private String mPostcode = "";//邮编
    private CityWheelPickerPopupWindow wheelPickerPopupWindow;

    private AddressBean mAddressBean;

    @Inject
    NewAddAddressPresenter mNewAddAddressPresenter;

    private Map<String,String> mParams;
    @Inject
    public NewAddAddressFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_new_add_address;
    }

    @Override
    protected void initVariables() {
        mParams = new HashMap<>();
    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mAddressBean = bundle.getParcelable("bean");
    }

    @Override
    protected void operateViews(View view) {
        wheelPickerPopupWindow = new CityWheelPickerPopupWindow(getActivity());

        if(mAddressBean != null){
            if(mAddressBean.getSfmrdz() != null && mAddressBean.getSfmrdz().equals("0")){//默认地址
                mBtnSwitch.setOpen(true);
                mDefaultAddress = "0";
            }
            mTvProvince.setText(mAddressBean.getSf());
            mTvCity.setText(mAddressBean.getCs());
            mTvDistrict.setText(mAddressBean.getXj());
            mEtAddress.setText(mAddressBean.getXxdz());
            mEtPhone.setText(mAddressBean.getSjh());
            mEtName.setText(mAddressBean.getXm());

            mProvince = mAddressBean.getSf();
            mCity = mAddressBean.getCs();
            mDistrict = mAddressBean.getXj();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewAddAddressPresenter.dropView();
    }


    @Override
    protected void initListener() {
        mBtnSwitch.setOnSwitchListener(new SwitchButton.OnSwitchListener() {
            @Override
            public void openbutton() {
                mDefaultAddress = "0";
            }

            @Override
            public void closebutton() {
                mDefaultAddress = "1";
            }
        });
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkParams() ){
                    if(PhoneUtils.isMobileNO(mEtPhone.getText().toString().trim())){
                        if(mAddressBean != null){
                            mParams.put("id",String.valueOf(mAddressBean.getId()));
                        }
                        mParams.put("hsk", Config.HSK);
                        mParams.put("yhm", DbHelper.getUserId(mContext));
                        mParams.put("xm",mEtName.getText().toString().trim());//姓名
                        mParams.put("sjh",mEtPhone.getText().toString().trim());//手机号
                        mParams.put("yb",mPostcode);
                        mParams.put("sf",mProvince);
                        mParams.put("cs",mCity);
                        mParams.put("xj",mDistrict);
                        mParams.put("xxdz",mEtAddress.getText().toString().trim());
                        mParams.put("sfmrdz",mDefaultAddress);
                        mNewAddAddressPresenter.addMyAddress(mParams);
                    }else {
                        ToastUtils.showCenterToast(getContext(),"请填写正确的手机号");
                    }

                }else {
                    ToastUtils.showCenterToast(getContext(),"请填写所有的参数");
                }

            }
        });
        mRlSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelPickerPopupWindow.show();
            }
        });

        wheelPickerPopupWindow.setListener(new OnCityWheelComfirmListener() {
            @Override
            public void onSelected(String Province, String City, String District, String PostCode) {
                if(!Province.isEmpty()){
                    mTvProvince.setText(Province);
                }
                if (!City.isEmpty()) {
                    mTvCity.setText(City);
                }

                if (!District.isEmpty()) {
                    mTvDistrict.setText(District);
                }
                mProvince = Province;
                mCity = City;
                mDistrict = District;
                mPostcode = PostCode;
            }
        });

        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    @Override
    public void initPresenter() {
        mNewAddAddressPresenter.takeView(this);
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
    public void addMyAddressSuccess(SignBean signBean) {
        String msgtype = signBean.getMsgtype();
        if("1".equals(msgtype)){
            ToastUtils.showCenterToast(getContext(),"提交成功");
            getActivity().setResult(100);
            getActivity().finish();
        }else {
            ToastUtils.showCenterToast(getContext(),"提交失败");
        }

    }

    @Override
    public void addMyAddressFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
//        getActivity().setResult(100);
//        getActivity().finish();
    }

    private boolean checkParams(){
        if(mEtName.getText().toString().isEmpty() || mEtName.getText().toString().trim().equals("")){
            return false;
        }
        if(mEtPhone.getText().toString().isEmpty() || mEtPhone.getText().toString().trim().equals("")){
            return false;
        }

        if(mProvince == null || mCity == null || mDistrict == null){
            return false;
        }

        if(mEtAddress.getText().toString().isEmpty() || mEtAddress.getText().toString().trim().equals("")){
            return false;
        }
        return true;
    }
}
