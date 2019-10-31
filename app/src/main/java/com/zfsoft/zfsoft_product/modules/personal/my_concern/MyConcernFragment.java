package com.zfsoft.zfsoft_product.modules.personal.my_concern;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.ConcernBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.modules.home.HomeFragment;
import com.zfsoft.zfsoft_product.modules.report.other_info.OtherInformationActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/2/22.
 */
public class MyConcernFragment extends BaseListFragment<ConcernBean,MyConcernPresenter> implements MyConcernContract.View{

    private ConcernBean mConcernBean;

    @Inject
    public MyConcernFragment() {
    }

    private int mType;

    @Inject
    MyConcernPresenter myConcernPresenter;

    private MyConcernAdapter mAdapter;


    @Override
    protected void setSpecificPresenter() {
        myConcernPresenter.takeView(this);
    }

    @Override
    public void loadData() {
        super.loadData();

        if(mType == 0){
            myConcernPresenter.getMyConcernList(Config.HSK, DbHelper.getUserId(mContext),start_page,PAGE_SIZE);
        }else {
            myConcernPresenter.getMyFansList(Config.HSK,DbHelper.getUserId(mContext),start_page,PAGE_SIZE);
        }
    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        Intent intent = new Intent(getContext(),OtherInformationActivity.class);

        mConcernBean = mAdapter.getData().get(position);
        if(mType == 0){
            if (mConcernBean.getAttentionnid() != null) {
                intent.putExtra("otherUserId", mConcernBean.getAttentionnid());
            }
            if (mConcernBean.getAttentionname() != null ) {
                intent.putExtra("otherUserName", mConcernBean.getAttentionname());
            }
        }else {
            if(mConcernBean.getFansid() != null){
                intent.putExtra("otherUserId", mConcernBean.getFansid());
            }
            if (mConcernBean.getFansname() != null ) {
                intent.putExtra("otherUserName", mConcernBean.getFansname());
            }
        }

        MyConcernFragment.this.startActivityForResult(intent,1);
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {

    }

    @Override
    protected BaseQuickAdapter<ConcernBean, BaseViewHolder> getAdapter() {
        mType = getArguments().getInt("type");
        mAdapter = new MyConcernAdapter(mType);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //关注 与 取消关注
                mConcernBean = mAdapter.getData().get(position);
                if(mType == 0 ){//我的关注
//                    if(mConcernBean.getZt().equals("0") && mConcernBean.getAttentionnstatus() != null && mConcernBean.getAttentionnstatus().equals("0")){
                        //目前是已经关注状态
                        myConcernPresenter.addAttention(Config.HSK, DbHelper.getUserId(mContext),mConcernBean.getAttentionnid(),"2");
//                    }else {
//                        myConcernPresenter.addAttention(Config.HSK, DbHelper.getUserId(mContext),mConcernBean.getAttentionnid(),"1");
//                    }
                }else {//我的粉丝
                    if(mConcernBean.getZt().equals("0") && mConcernBean.getFansstatus() != null && mConcernBean.getFansstatus().equals("0")){
                        //目前是已经关注状态
                        myConcernPresenter.addAttention(Config.HSK, DbHelper.getUserId(mContext),mConcernBean.getFansid(),"2");
                    }else {
                        myConcernPresenter.addAttention(Config.HSK, DbHelper.getUserId(mContext),mConcernBean.getFansid(),"1");
                    }
                }

            }
        });

        return mAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myConcernPresenter.dropView();
    }

    @Override
    public void addAttentionSuccess(SignBean signBean) {
        String msgtype = signBean.getMsgtype();
        if(mType == 0){
            if(msgtype.equals("1")){//取消关注成功
                ToastUtils.showCenterToast(getContext(),"取关成功");
            }else {
                ToastUtils.showCenterToast(getContext(),"取关失败");
            }
        }else {
            if(mConcernBean.getZt().equals("0") && mConcernBean.getFansstatus() != null && mConcernBean.getFansstatus().equals("0") ){//关注状态下调了接口
                if(msgtype.equals("1")){//取消关注成功
                    mConcernBean.setZt("1");
                    ToastUtils.showCenterToast(getContext(),"取关成功");
                }else {
                    mConcernBean.setZt("0");
                    ToastUtils.showCenterToast(getContext(),"取关失败");
                }
            }
            else {
                if(msgtype.equals("1")){
                    mConcernBean.setZt("0");
                    ToastUtils.showCenterToast(getContext(),"关注成功");
                }else {
                    mConcernBean.setZt("1");
                    ToastUtils.showCenterToast(getContext(),"关注失败");
                }
            }
        }


        loadData();
    }

    @Override
    public void addAttentionFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }
}