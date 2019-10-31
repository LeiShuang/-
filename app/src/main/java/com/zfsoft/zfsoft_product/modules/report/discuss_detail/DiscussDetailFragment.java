package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseListFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.DiscussDetailFirstEntity;
import com.zfsoft.zfsoft_product.entity.ReportDetailEntity;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.TestreportreplyListBean;
import com.zfsoft.zfsoft_product.modules.report.other_info.OtherInformationActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.KeyboardUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/28 on 11:30
 * 描述:二级评论详情界面，列表fragment
 * 作者:Ls
 */
public class DiscussDetailFragment extends BaseListFragment<TestreportreplyListBean, DiscussDetailPresenter> implements DiscussDetailContract.View,DiscussDetailContract.DiscussView, View.OnClickListener {

    private CircleImageView mCircleImageView;
    private TextView mTvStar;
    private TextView mTvFatherName;
    private TextView mTvContent;
    private TextView mTvTime;
    private ReportDetailEntity.ConmmentNumer mFatherEntity;
    private DiscussDetailAdapter mAdapter;
    private int mReportId;
    private ImageView mIvStar;
    private boolean mIsStar;

    @Override
    protected void setSpecificPresenter() {
        mPresenter.takeView(this);
        mSubmitPresenter.takeView(this);
    }

    @Inject
    DiscussDetailPresenter mPresenter;
    @Inject
    DiscussSubmitPresenter mSubmitPresenter;
    @Inject
    public DiscussDetailFragment() {

    }

    @Override
    protected void onItemCLick(BaseQuickAdapter adapter, int position) {
        List<TestreportreplyListBean> comments = new ArrayList<>();
        comments = (List<TestreportreplyListBean>)adapter.getData();
        if (comments.get(position).getUserid().equals(DbHelper.getUserId(mContext))){
            ToastUtils.showCenterShortToast(mContext,"不能对自己进行回复哦o(╯□╰)o");
        }else{
            TestreportreplyListBean bean = (TestreportreplyListBean) adapter.getData().get(position);
            showReplyDialog(mFatherEntity.getFatherDiscussId(),bean.getReplyname(),position);
        }

    }

    @Override
    protected void handleBundle(Bundle bundle) {
        if (bundle != null) {
            mFatherEntity = (ReportDetailEntity.ConmmentNumer) bundle.getSerializable("fatherDiscuss");
            mReportId = bundle.getInt("mReportId",1);
        }
    }

    @Override
    public void loadData() {
        super.loadData();
        if (mFatherEntity != null){
            mPresenter.getChildDiscussDetail(Config.HSK,mFatherEntity.getFatherDiscussId(),start_page,PAGE_SIZE);
        }
    }

    @Override
    protected void initFootView(BaseQuickAdapter adapter) {

    }

    @Override
    protected void initHeader(BaseQuickAdapter adapter) {
        mSubmitPresenter.getFIrstDiscuss(Config.HSK,DbHelper.getUserId(mContext),String.valueOf(mFatherEntity.getFatherDiscussId()));
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.child_detail_header_view, null, false);
        mCircleImageView =  headerView.findViewById(R.id.circle_father_image);
        mTvStar = headerView.findViewById(R.id.tv_father_star);
        mIvStar = headerView.findViewById(R.id.iv_father_star);
        mTvFatherName = headerView.findViewById(R.id.tv_discuss_father_name);
        mTvContent = headerView.findViewById(R.id.tv_father_discuss_content);
        mTvTime = headerView.findViewById(R.id.tv_father_discuss_time);
        mTvContent.setOnClickListener(this);
        mCircleImageView.setOnClickListener(this);
        mTvStar.setText(String.valueOf(mFatherEntity.getFatherStarNumber()));
        mTvFatherName.setText(mFatherEntity.getFatherName());
        mTvContent.setText(mFatherEntity.getFatherContent());
        mTvTime.setText(mFatherEntity.getFatherDiscussTime());
        ImageLoaderHelper.loadImage(mContext,mCircleImageView,mFatherEntity.getFatherDiscussUrl());
        mAdapter.addHeaderView(headerView);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        mAdapter = new DiscussDetailAdapter();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.circle_image:
                        TestreportreplyListBean childEntity = (TestreportreplyListBean) adapter.getData().get(position);
                        //   int childUserId = childEntity.getReplayid();
                        Intent intent = new Intent(getActivity(), OtherInformationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("otherUserId",childEntity.getUserid());
                        bundle.putString("otherUserName",childEntity.getReplyname());
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                        break;
                    default:
                        break;

                }
            }
        });
        return mAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
        mSubmitPresenter.dropView();
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key) {
            case R.id.iv_father_star:
                  if (mIsStar) {
                      ToastUtils.showCenterToast(getContext(),"[点赞]是严肃的，不可以反悔哦");
                  }else{
                      mSubmitPresenter.likeDiscuss(Config.HSK,mReportId,DbHelper.getUserId(mContext),2);
                  }
                //点赞
                break;
            case R.id.tv_father_discuss_content:
                if (mFatherEntity.getFatherUserId().equals(DbHelper.getUserId(mContext))){
                    ToastUtils.showCenterShortToast(mContext,"不能对自己进行回复哦o(╯□╰)o");
                }else{
                    //回复评论一级
                    showReplyDialog(mFatherEntity.getFatherDiscussId(),mFatherEntity.getFatherName(),0);
                }

                break;

            case R.id.circle_father_image:
                Intent intent = new Intent(getActivity(), OtherInformationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("otherUserId", mFatherEntity.getFatherUserId());
                bundle.putString("otherUserName",mFatherEntity.getFatherName());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }

    private BottomSheetDialog mDialog;


    private String mRealContent;
    private EditText    mReplyCommentText;
    /**
     * 弹出回复评论框
     */
    private String secondCommentContent;//二级回复内容
    private String secondCommentReplyPeople;//二级回复人的名字
    private int secondCommentLocalPosition;//位置
    private void showReplyDialog(final int fatherDiscussId, final String replyName,final int position) {
        mDialog = new BottomSheetDialog(mContext, R.style.dialog_soft_input);
        View commentView = LayoutInflater.from(mContext).inflate(R.layout.common_dialog_layout, null);
        mReplyCommentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        mReplyCommentText.setHint("@" + replyName);
        mDialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealContent = mReplyCommentText.getText().toString().trim();
                // String replyContent = "@" + replyName + "  " + realContent;
                if (!TextUtils.isEmpty(mRealContent)) {
                    secondCommentContent = mRealContent;
                    secondCommentReplyPeople = replyName;
                    secondCommentLocalPosition = position;
                    KeyboardUtils.hideInput(getContext(), mReplyCommentText);
                    mDialog.dismiss();
                    mSubmitPresenter.submitSecond(Config.HSK, mReportId, DbHelper.getUserInfo(mContext).getUserid(), mRealContent, fatherDiscussId);
                } else {
                    ToastUtils.showCenterToast(getContext(),"回复内容不能为空");
                }
            }
        });

        mReplyCommentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length() > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable) && editable.length() > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
        });
        mDialog.show();
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
    public void submitSecondDiscussSuccess(SignBean info) {
        if ("1".equals(info.getMsgtype())){
            ToastUtils.showCenterToast(getContext(),"回复成功");
//            loadData();
            TestreportreplyListBean bean = new TestreportreplyListBean();
            bean.setReplydate("刚刚");
            bean.setReplydetail(secondCommentContent);
            bean.setUserid(DbHelper.getUserId(mContext));
            if (TextUtils.isEmpty(DbHelper.getUserInfo(mContext).getXm())){
                bean.setReplyname("我");
            }else{
                bean.setReplyname(DbHelper.getUserInfo(mContext).getXm());
            }
            bean.setReplyurl(DbHelper.getUserInfo(mContext).getMyimg());
            bean.setReplytoname(secondCommentReplyPeople);
            mAdapter.getData().add(secondCommentLocalPosition ,bean);
            mAdapter.notifyItemInserted(secondCommentLocalPosition);
            secondCommentContent= "";
            mReplyCommentText.setText("");
        }else if ("2".equals(info.getMsgtype())){
            ToastUtils.showCenterToast(getContext(),"回复失败");
        }
    }

    @Override
    public void submitSecondDiscussFailed(String msg) {
        ToastUtils.showCenterToast(getContext(),"回复操作失败");
    }
    /***
     * 获取一级评论成功
     * */
    private int starTemp;
    @Override
    public void getFirstDiscussSuccess(DiscussDetailFirstEntity info) {
           String fatherName = info.getCommentname();
           int fatherId = info.getCommentid();
           String fatherDetail = info.getCommentdetail();
           String fatherUrl = info.getCommenturl();
           int starNumber = info.getCommentlikesum();
           String fathertime = info.getCommentdate();
           String hasStar = info.getHaslike();//"0"否"1"是
        mIsStar = "true".equals(hasStar) ? true :false;
        if (mIsStar){
            mIvStar.setImageResource(R.mipmap.ico_has_star);

        }else {
            mIvStar.setImageResource(R.mipmap.ico_unstar);
        }

        mTvStar.setText(String.valueOf(starNumber));
        mTvFatherName.setText(fatherName);
        mTvContent.setText(fatherDetail);
        mTvTime.setText(fathertime);
        ImageLoaderHelper.loadHeadImage(mContext,mCircleImageView,fatherUrl);
        mIvStar.setOnClickListener(this);

    }
    /***
     * 获取一级评论失败
     * */
    @Override
    public void getFirstDiscussFailed(String msg) {
        ToastUtils.showCenterShortToast(mContext,"获取一级评论失败");
    }

    @Override
    public void starDiscussSuccess(SignBean info) {
        String type = info.getMsgtype();

        if ("1".equals(type)) {
            int starNumber = info.getLikesum();
           mIvStar.setImageResource(R.mipmap.ico_has_star);
           mTvStar.setText(String.valueOf(starNumber));
           mIsStar =true;
        } else if ("2".equals(type)) {
            ToastUtils.showCenterToast(getContext(),"点赞失败");
        }
    }

    @Override
    public void starDiscussFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),"点赞操作失败");
    }
}