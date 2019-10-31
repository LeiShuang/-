package com.zfsoft.zfsoft_product.modules.report.report_detail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxDeviceTool;
import com.vondear.rxtool.RxLogTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.CommentsBean;
import com.zfsoft.zfsoft_product.entity.CommodityurlsBean;
import com.zfsoft.zfsoft_product.entity.InfoServer;
import com.zfsoft.zfsoft_product.entity.ReportDetailEntity;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.modules.login.LoginActivity;
import com.zfsoft.zfsoft_product.modules.report.discuss_detail.DiscussDetailActivity;
import com.zfsoft.zfsoft_product.modules.report.other_info.OtherInformationActivity;
import com.zfsoft.zfsoft_product.modules.try_use.share_redbook.ShareListenerCallBack;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.GlideImageLoader;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.KeyboardUtils;
import com.zfsoft.zfsoft_product.utils.ShareUtils;
import com.zfsoft.zfsoft_product.utils.SizeUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建日期：2019/1/8 on 17:27
 * 描述:报告详情Fragment
 * 作者:Ls
 */
public class ReportDetailFragment extends BaseFragment implements View.OnClickListener, ReportDetailContract.View, ShareListenerCallBack {
    @BindView(R.id.rv_report_detail)
    RecyclerView mRvReportDetail;
    @BindView(R.id.refresh_report)
    SmartRefreshLayout mRefreshReport;
    @BindView(R.id.iv_report_detail_back)
    ImageView mIvReportDetailBack;
    @BindView(R.id.iv_report_detail_share)
    ImageView mIvReportDetailShare;
    @BindView(R.id.iv_report_detail_like)
    ImageView mIvReportDetailLike;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private LinearLayoutManager mLayoutManager;
    private ReportDetailAdapter mAdapter;
    private int bannerHeight;
    private  Banner mBanner;
    private int mReportId;
    private TextView mTvTitle;
    private CircleImageView mIvPhoto;
    private TextView mTvName;
    private TextView mTvPersonal;
    private TextView mTvTime;
    private TextView mTvContent;
    private TextView mTvDiscussNumber;
    private TextView mTvStarNumber;
    private TextView mTvAttention;
    private TextView mTv_write;
    private static List<String> mImages;
    private int startPage = 1;
    private int page_size = 6;
    private List<ReportDetailEntity> mComments;
    private boolean mIsStar;
    private boolean mIsLiked;
    private ImageView mIvReportStar;
    private int mLikeTempNumber = 1;
    private String mReporterId;
    private EditText mReplyCommentText;
    private int currentClickPosition;
    private String mReporterName;
    private boolean mHasLogin;
    private StandardGSYVideoPlayer mGsyVideoPlayer;
    private boolean mIsPicBanner;
    private String mReportTitle;
    private String mShareImageUrl;
    private View mNoRefreshData;//自定义暂无更多的视图

    private int getCurrentPosition() {
        return currentClickPosition;
    }

    private void setCurrentPosition(int currentClickPosition) {
        this.currentClickPosition = currentClickPosition;
    }

    public static ReportDetailFragment newInstance(int reportId, String reporterId) {
        Bundle args = new Bundle();
        args.putInt("reportId", reportId);
        args.putString("reportUserId", reporterId);
        ReportDetailFragment fragment = new ReportDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public ReportDetailFragment() {

    }

    @Inject
    ReportDetailPresenter mPresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_report_detail;
    }

    @Override
    protected void initVariables() {
        mImages = new ArrayList<>();
        mHasLogin = DbHelper.checkUserIsLogin(mContext);

    }
    /**
     * 初始化暂无更多数据的视图
     * */
    private void initNodataView() {
        mNoRefreshData = LayoutInflater.from(mContext).inflate(R.layout.base_no_refresh_data_view,null,false);
        TextView tvNodataMore = (TextView) mNoRefreshData.findViewById(R.id.tv_no_more_data);
        tvNodataMore.setText(getString(R.string.no_more_discuss));
        mNoRefreshData.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    @Override
    protected void handleBundle(Bundle bundle) {
        if (bundle != null) {
            mReportId = bundle.getInt("reportId", 1);
            mReporterId = bundle.getString("reportUserId", "1");
        }
    }

    @Override
    public void onDestroy() {
        mNoRefreshData = null;
        super.onDestroy();
        mPresenter.dropView();
    }


    @Override
    protected void operateViews(View view) {

        getCommentData();
        //初始化ImmersionBar让ToolBar和状态栏不重叠
        ImmersionBar.setTitleBar(getActivity(), mToolbar);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRvReportDetail.setLayoutManager(mLayoutManager);
        mAdapter = new ReportDetailAdapter(mComments);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvReportDetail.setAdapter(mAdapter);
//        mAdapter.addData(mData);
        addHeaderView();
        mPresenter.getReportInfo(Config.HSK, mReportId, DbHelper.getUserId(mContext));

    }

    private void getCommentData() {
        String userId = DbHelper.getUserId(mContext);
        RxLogTool.i(startPage);
        mPresenter.getCommentsList(Config.HSK, mReportId, startPage, Config.LOADMORE.PAGE_SIZE, userId);

    }


    private void addHeaderView() {
        View mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.report_detail_banner, (ViewGroup) mRvReportDetail.getParent(), false);
        mBanner = mHeaderView.findViewById(R.id.report_detail_banner);
        mGsyVideoPlayer = mHeaderView.findViewById(R.id.gsy_layer);
        mGsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        mTv_write = mHeaderView.findViewById(R.id.tv_write_discuss);
        mTvTitle = mHeaderView.findViewById(R.id.tv_report_detail_title);
        mIvPhoto = mHeaderView.findViewById(R.id.iv_report_detail_photo);
        mTvName = mHeaderView.findViewById(R.id.iv_report_name);
        mTvPersonal = mHeaderView.findViewById(R.id.iv_report_speak);
        mTvTime = mHeaderView.findViewById(R.id.report_detail_time);
        mTvContent = mHeaderView.findViewById(R.id.report_detail_content);
        mTvContent.setTextIsSelectable(true);
        mTvDiscussNumber = mHeaderView.findViewById(R.id.tv_report_discuss);
        mTvStarNumber = mHeaderView.findViewById(R.id.tv_star);
        mIvReportStar = mHeaderView.findViewById(R.id.iv_star);
        mTvAttention = mHeaderView.findViewById(R.id.tv_attention);
        mAdapter.addHeaderView(mHeaderView);
        ViewGroup.LayoutParams bannerParams = mBanner.getLayoutParams();
        bannerParams.width = RxDeviceTool.getScreenWidth(mContext);
        bannerParams.height = RxDeviceTool.getScreenWidth(mContext);
        mBanner.setLayoutParams(bannerParams);
        ViewGroup.LayoutParams toolBarParams = mToolbar.getLayoutParams();
       // bannerHeight = bannerParams.height - toolBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());
        mBanner.setImages(mImages)
                .setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .setDelayTime(3000)
                .start();

    }

    @Override
    public void getReportHeaderSuccess(ReportInfo info) {

        if (info != null) {
            String hasstar = info.getHasstar();//"0"否
            String haslike = info.getHaslike();
            String type = info.getType();//"0"未关注
            mIsPicBanner = "1".equals(info.getLx()) ? true : false;
            boolean hasAttention = "1".equals(type) ? true : false;//是否关注
            //0否1是
            mIsStar = "1".equals(hasstar) ? true : false;//是否收藏

            mIsLiked = "1".equals(haslike) ? true : false;//是否点赞
            if (mIsLiked) {
                mIvReportStar.setImageResource(R.mipmap.ico_has_star);
            } else {
                mIvReportStar.setImageResource(R.mipmap.ico_unstar);
            }
            if (hasAttention) {
                mTvAttention.setText("取关");
                mTvAttention.setBackgroundResource(R.color.lightgray);
                attentionTemp = 2;//取消关注
            } else {
                mTvAttention.setText("关注");
                mTvAttention.setBackgroundResource(R.color.try_use_top_color);
                attentionTemp = 1;//关注
            }
            if (mIsStar) {
                //已经收藏
                mIvReportDetailLike.setImageResource(R.mipmap.ico_has_liked);
                mLikeTempNumber = 2;//取消收藏
            } else {
                mIvReportDetailLike.setImageResource(R.mipmap.ico_unlike);
                mLikeTempNumber = 1;//收藏
            }
            if (mIsPicBanner) {
                mBanner.setVisibility(View.VISIBLE);
                mGsyVideoPlayer.setVisibility(View.GONE);
                List<String> imgs = new ArrayList<>();
                for (int i = 0; i < info.getTestreporturl().size(); i++) {
                    imgs.add(info.getTestreporturl().get(i).getCommodityurl());
                }
                mBanner.update(imgs);
            } else {
                List<CommodityurlsBean> mp4UrlList = info.getTestreporturl();
                mBanner.setVisibility(View.GONE);
                mGsyVideoPlayer.setVisibility(View.VISIBLE);
                mGsyVideoPlayer.setUp(mp4UrlList.get(1).getCommodityurl(), true, "");
                GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);

            }
            mShareImageUrl= info.getTestreporturl().get(0).getCommodityurl();
            mReportTitle = info.getTestreporttitle();
            mTvTitle.setText(mReportTitle);
            ImageLoaderHelper.loadHeadImage(mContext, mIvPhoto, info.getTalenturl());
            mReporterName = info.getTalentname();
            mTvName.setText(info.getTalentname());
            mTvPersonal.setText(info.getTalentintroduction());

            mTvTime.setText(info.getTestreportdate());
            mTvContent.setText(info.getTestreportdetail());
            mTvDiscussNumber.setText(String.valueOf(info.getCommentsum()));
            mTvStarNumber.setText(String.valueOf(info.getLikesum()));
            if (DbHelper.checkUserIsLogin(mContext)){
                if (DbHelper.getUserId(mContext).equals(mReporterId)){
                    mTvAttention.setVisibility(View.GONE);
                }
            }
        } else {
            ToastUtils.showCenterToast(getContext(), "部分数据获取失败");
        }
    }

    @Override
    protected void initListener() {
        mIvPhoto.setOnClickListener(this);
        mIvReportStar.setOnClickListener(this);
        mTvAttention.setOnClickListener(this);
        mIvReportDetailBack.setOnClickListener(this);
        mIvReportDetailLike.setOnClickListener(this);
        mIvReportDetailShare.setOnClickListener(this);
        mTv_write.setOnClickListener(this);

        mRvReportDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalDy += dy;
                int originalHeight = SizeUtils.dp2px(200, mContext);
                //bannerHeight
                float alpha = totalDy / originalHeight;
                if (totalDy <= originalHeight) {
                    //如果小于 滑动截止高度（Banner的高度 - ToolBar的高度 - 状态栏高度）
                    if (mIsPicBanner) {
                        mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT, ContextCompat.getColor(getContext(), R.color.try_use_top_color), alpha));
                        mIvReportDetailBack.setVisibility(View.VISIBLE);
                    } else {
                        mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT, ContextCompat.getColor(getContext(), R.color.try_use_top_color), alpha));
                       mIvReportDetailBack.setVisibility(View.VISIBLE);

                    }

                } else {
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT, ContextCompat.getColor(getContext(), R.color.try_use_top_color), 1));
                    mIvReportDetailBack.setVisibility(View.VISIBLE);
                    mGsyVideoPlayer.onVideoPause();
                }

            }
        });

        mRefreshReport.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                RefreshState state = refreshLayout.getState();
                if (state == RefreshState.PullDownToRefresh) {
                    mToolbar.setVisibility(View.GONE);
                }
                startPage = 1;

                mPresenter.getCommentsList(Config.HSK, mReportId, startPage, page_size, DbHelper.getUserId(mContext));
                mToolbar.setVisibility(View.VISIBLE);
            }

        });
        mRefreshReport.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initLoadMore();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_father_pic) {
                    if(DbHelper.checkUserIsLogin(mContext)){
                        ReportDetailEntity reportDetailEntity = (ReportDetailEntity) adapter.getData().get(position);
                        String otherId = reportDetailEntity.getDiscussFatherEntity().getFatherUserId();
                        String otherName = reportDetailEntity.getDiscussFatherEntity().getFatherName();
                        Intent intent = new Intent(getActivity(), OtherInformationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("otherUserId", otherId);
                        bundle.putString("otherUserName", otherName);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }else {
                        RxActivityTool.skipActivity(mContext,LoginActivity.class);
                    }

                } else if (view.getId() == R.id.tv_reply) {
                    if (mHasLogin) {
                        ReportDetailEntity commentInfo = (ReportDetailEntity) adapter.getData().get(position);
                        int fatherId = commentInfo.getDiscussFatherEntity().getFatherId();
                        String fatherUserId = commentInfo.getDiscussFatherEntity().getFatherUserId();
                        String fatherName = commentInfo.getDiscussFatherEntity().getFatherName();
                        if (DbHelper.getUserId(mContext).equals(fatherUserId)) {
                            ToastUtils.showCenterShortToast(mContext,"不可对自己进行回复哦");
                        }else{
                            showReplyDialog(fatherId, fatherName, position);
                        }
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }

            } else if(view.getId()==R.id.iv_father_star)

            {
                if (mHasLogin) {
                    ReportDetailEntity reportDetailEntity = (ReportDetailEntity) adapter.getData().get(position);
                    boolean isstar = reportDetailEntity.getDiscussFatherEntity().isHasstar();
                    int fatherDiscussId = reportDetailEntity.getDiscussFatherEntity().getFatherId();
                    if (isstar) {
                        ToastUtils.showCenterToast(getContext(), "[点赞]是严肃的，不可以反悔哦");
                    } else {
                        setCurrentPosition(position);
                        mPresenter.likeDiscuss(Config.HSK, fatherDiscussId, DbHelper.getUserId(mContext), 2);
                    }
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }


            } else if(view.getId()==R.id.tv_sum_comment)

            {
                ReportDetailEntity tempInfo = (ReportDetailEntity) adapter.getData().get(position);
                ReportDetailEntity.ConmmentNumer discussInfo = tempInfo.getCommentNumber();
                Intent intent = new Intent(getActivity(), DiscussDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("fatherDiscuss", discussInfo);
                bundle.putInt("reportId", mReportId);
                intent.putExtras(bundle);
                getContext().startActivity(intent);

            } else if(view.getId()==R.id.tv_child_content){
                if (mHasLogin) {
                    ReportDetailEntity commentInfo = (ReportDetailEntity) adapter.getData().get(position);
                    int fatherId = commentInfo.getDiscussChildEntity().getId();
                    String userId = commentInfo.getDiscussChildEntity().getChildUserId();
                    String fatherName = commentInfo.getDiscussChildEntity().getName();
                    if (DbHelper.getUserId(mContext).equals(userId)) {
                        ToastUtils.showCenterShortToast(mContext,"不可以对自己进行回复哦");
                    } else {
                        //二级评论点击
                        showReplyDialog(fatherId, fatherName, position);
                    }
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }

            }
        }
    });
}

    private void initLoadMore() {
        startPage++;
        mPresenter.getCommentsList(Config.HSK, mReportId, startPage, page_size, DbHelper.getUserId(mContext));

    }

    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }


    private int attentionTemp;

    @Override
    public void onClick(View v) {

        int key = v.getId();
        switch (key) {
            case R.id.iv_report_detail_back:
                getActivity().setResult(100);
                getActivity().finish();
                break;

            case R.id.iv_report_detail_like:
                if (mHasLogin) {
                    mPresenter.collectReport(Config.HSK, mReportId, DbHelper.getUserId(mContext), mLikeTempNumber);
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }
                break;
            case R.id.iv_report_detail_share:
                if (mHasLogin) {
                    ShareUtils.setShareInfo(mContext,mReportTitle,String.valueOf(mReportId),"2",mReporterId,mReporterName,mShareImageUrl,this);
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }
                break;
            case R.id.tv_write_discuss:
                if (mHasLogin) {
                    showCommentInput(0);
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }
                break;
            case R.id.iv_star:
                if (mHasLogin) {
                    if (mIsLiked) {
                        ToastUtils.showCenterToast(getContext(), getString(R.string.click_star_attention));
                    } else {
                        mPresenter.likeReport(Config.HSK, mReportId, DbHelper.getUserId(mContext));
                    }
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }
                break;
            case R.id.tv_attention:
                if (mHasLogin) {
                    mPresenter.attentionReporter(Config.HSK, DbHelper.getUserId(mContext), mReporterId, String.valueOf(attentionTemp));
                } else {
                    RxActivityTool.skipActivity(mContext, LoginActivity.class);
                }
                break;
            case R.id.iv_report_detail_photo:
                if(DbHelper.checkUserIsLogin(mContext)){
                    if (!TextUtils.isEmpty(mReporterId) && !TextUtils.isEmpty(mReporterName)) {
                        Intent intent = new Intent(getActivity(), OtherInformationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("otherUserId", mReporterId);
                        bundle.putString("otherUserName", mReporterName);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    } else {
                        ToastUtils.showCenterToast(getContext(), "用户数据获取异常");
                    }
                }else {
                    RxActivityTool.skipActivity(mContext,LoginActivity.class);
                }


                break;
        }

    }


    private BottomSheetDialog mDialog;
    @Override
    public void showCommentInput(final int position) {
        mDialog = new BottomSheetDialog(mContext, R.style.dialog_soft_input);
        View commentView = LayoutInflater.from(getContext()).inflate(R.layout.common_dialog_layout, null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint(getString(R.string.please_input_discuss));
        mDialog.setContentView(commentView);
        //解决BoottomSheetDialog显示不全的问题

        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                setCurrentPosition(position);
                if (!TextUtils.isEmpty(commentContent)) {
                    mDialog.dismiss();
                    KeyboardUtils.hideInput(getContext(), commentText);
                    mPresenter.discussfirstComment(Config.HSK, mReportId, DbHelper.getUserId(mContext), commentContent);

                } else {
                    ToastUtils.showCenterToast(getContext(), "评论内容不能为空");
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length() > 1) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && s.length() > 1) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
        });
        mDialog.show();
    }

    private String mRealContent;

    /**
     * 弹出回复评论框
     */
    private String secondCommentContent;//二级回复内容
    private String secondCommentReplyPeople;//二级回复人的名字
    private int secondCommentLocalPosition;//位置
    @Override
    public void showReplyDialog(final int fatherDiscussId, final String replyName, final int position) {
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
                    mPresenter.dicussComment(Config.HSK, mReportId, DbHelper.getUserId(mContext), mRealContent, fatherDiscussId);
                } else {
                    ToastUtils.showCenterToast(getContext(), "回复内容不能为空");
                }
            }
        });

        mReplyCommentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length() > 1) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable) && editable.length() > 1) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
        });
        mDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }


    @Override
    public void getReportHeaderFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), errorMsg);
    }

    @Override
    public void getCommentsSuccess(CommentsBean info) {
        RefreshState refreshState = mRefreshReport.getState();
        if (info != null) {
            mSize = info.getSize();
        } else {
            showErrorView();
        }


        if (refreshState == RefreshState.Refreshing || refreshState == RefreshState.None) {
            //此时是刷新状态
            //如果数据为空，则显示错误布局()
            if (mSize != 0 && info.getData() == null) {
//                mRefreshReport.finishRefresh(1000, false);
                mRefreshReport.finishRefresh(500, false);
                showNormalView();
                return;
            }

            if (mSize == 0) {
//                mRefreshReport.finishRefresh(1000, true);
                mRefreshReport.finishRefresh(500,true);
                showNormalView();
                if (mNoRefreshData == null){
                    initNodataView();
                    mAdapter.addFooterView(mNoRefreshData);
                }
                return;
            }

            if (0 < mSize && mSize < page_size) {
//                mRefreshReport.finishRefresh(1000, true);
                mRefreshReport.finishRefresh(500, true);
                showNormalView();
                mAdapter.setNewData(InfoServer.getDiscussInfo(info));
                if (mNoRefreshData == null){
                    initNodataView();
                    mAdapter.addFooterView(mNoRefreshData);
                }
                return;
            }

            if (mSize == page_size){
//                mRefreshReport.finishRefresh(1000, true);
                mRefreshReport.finishRefresh(500,true);
                showNormalView();
                mAdapter.setNewData(InfoServer.getDiscussInfo(info));
                if (mNoRefreshData != null){
                    mAdapter.removeFooterView(mNoRefreshData);
                    mNoRefreshData = null;
                }
                mRefreshReport.setNoMoreData(false);
                return;
            }

        } else if (refreshState == RefreshState.Loading) {
            //此时是加载更多状态,已经加载完全部数据
            if (mSize == 0) {
                showNormalView();
                mRefreshReport.finishLoadMoreWithNoMoreData();
                if (mNoRefreshData == null){
                    initNodataView();
                    mAdapter.addFooterView(mNoRefreshData);
                }
                return;

            }
            if (isOvered()) {
                showNormalView();
                mRefreshReport.finishLoadMoreWithNoMoreData();
                mAdapter.addData(InfoServer.getDiscussInfo(info));
                if (mNoRefreshData == null){
                    initNodataView();
                    mAdapter.addFooterView(mNoRefreshData);
                }
                return;
            }

            if (!isOvered()) {
                showNormalView();
                mRefreshReport.finishLoadMore(0, true, false);
                mAdapter.addData(InfoServer.getDiscussInfo(info));
                return;
            }
        }

    }

    private void showNormalView() {
        mRvReportDetail.setVisibility(View.VISIBLE);
    }

    /**
     * 显示空布局，即评论为0的时候
     */
    private void showEmptyView() {
        mRvReportDetail.setVisibility(View.GONE);
    }

    /**
     * 显示错误布局
     */
    private void showErrorView() {
        //mRlNoData.setVisibility(View.VISIBLE);
        mRvReportDetail.setVisibility(View.GONE);
    }

    @Override
    public void getCommentsFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "评论数据异常,请稍后再试");
    }

    /**
     * 收藏或取消收藏成功
     */
    @Override
    public void collectSuccess(SignBean info) {

        String likeType = info.getMsgtype();
        if ("1".equals(likeType)) {
            if (mLikeTempNumber == 2) {
                //取消收藏成功
                mIvReportDetailLike.setImageResource(R.mipmap.ico_unlike);
                mLikeTempNumber = 1;
            } else {
                //收藏成功
                mIvReportDetailLike.setImageResource(R.mipmap.ico_has_liked);
                mLikeTempNumber = 2;
            }
        } else if ("1".equals(likeType)) {
            if (mLikeTempNumber == 2) {
                //取消收藏失败
                ToastUtils.showCenterToast(getContext(), "取消收藏失败");
            } else {
                //收藏失败
                ToastUtils.showCenterToast(getContext(), "收藏失败");
            }
        } else if ("3".equals(likeType)) {
            if (mLikeTempNumber == 2) {
                //重复取消收藏
                ToastUtils.showCenterToast(getContext(), "请勿重复取消收藏");
            } else {
                //重复收藏
                ToastUtils.showCenterToast(getContext(), "请勿重复收藏");
            }
        }
    }

    /**
     * 收藏或取消收藏失败
     */
    @Override
    public void collectFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "操作失败");
    }

    /**
     * 关注报告发布人成功
     */
    @Override
    public void attentionSuccess(SignBean info) {
        String attentionType = info.getMsgtype();
        if ("1".equals(attentionType)) {
            if (attentionTemp == 2) {
                mTvAttention.setText("关注");
                mTvAttention.setBackgroundResource(R.color.try_use_top_color);
                attentionTemp = 1;
            } else {
                mTvAttention.setText("取关");
                mTvAttention.setBackgroundResource(R.color.lightgray);
                attentionTemp = 2;
            }
        } else if ("2".equals(attentionType)) {
            if (attentionTemp == 2) {
                ToastUtils.showCenterToast(getContext(), "取关失败");
            } else {
                ToastUtils.showCenterToast(getContext(), "关注失败");
            }
        } else if ("3".equals(attentionTemp)) {
            if (attentionTemp == 2) {
                ToastUtils.showCenterToast(getContext(), "请勿重复取关");
            } else {
                ToastUtils.showCenterToast(getContext(), "请勿重复关注");
            }
        }
    }

    /**
     * 关注报告发布人失败
     */
    @Override
    public void attentionFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "关注操作失败");
    }

    /**
     * 发布报告点赞成功
     */
    @Override
    public void likeReportSuccess(SignBean info) {
        int startNumber = info.getLikesum();
        String startTempNumber = info.getMsgtype();
        if ("1".equals(startTempNumber)) {
            mIvReportStar.setImageResource(R.mipmap.ico_has_star);
            mTvStarNumber.setText(String.valueOf(startNumber));
            //点赞成功后把 是否点过赞标志 变为 true
            mIsLiked = true;
        } else if ("2".equals(startTempNumber)) {
            ToastUtils.showCenterToast(getContext(), "点赞失败");
        } else if ("3".equals(startTempNumber)) {
            ToastUtils.showCenterToast(getContext(), getString(R.string.click_star_attention));
        }
    }

    /**
     * 发布报告点赞失败
     */
    @Override
    public void likeReportFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "点赞操作失败");
    }

    /**
     * 点赞评论成功
     */
    @Override
    public void starDiscussSuccess(SignBean info) {
        String type = info.getMsgtype();

        if ("1".equals(type)) {
            int starNumber = info.getLikesum();
            ReportDetailEntity fatherEntity = mAdapter.getData().get(getCurrentPosition());
            RxLogTool.e("getCurrentPosition:" + getCurrentPosition());
            fatherEntity.getDiscussFatherEntity().setHasstar(true);
            fatherEntity.getDiscussFatherEntity().setDiscussStarNumber(starNumber);
            mAdapter.notifyItemChanged(getCurrentPosition() + 1);
        } else if ("2".equals(type)) {
            ToastUtils.showCenterToast(getContext(), "点赞失败");
        }
    }

    /**
     * 点赞评论失败
     */
    @Override
    public void starDiscussFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "点赞操作失败");
    }

    @Override
    public void dicussSuccess(SignBean info) {

        String discussTemp = info.getMsgtype();
        if ("1".equals(discussTemp)) {
            ToastUtils.showCenterToast(getContext(), "回复成功");
            //private String secondCommentContent;//二级回复内容
            //    private String secondCommentReplyPeople;//二级回复人的名字
            ReportDetailEntity reportLocalEntity = new ReportDetailEntity(ReportDetailEntity.VIEW_ITEM_TYPE_CHILD);
            reportLocalEntity.getDiscussChildEntity().setChildUserId(DbHelper.getUserId(mContext));
            reportLocalEntity.getDiscussChildEntity().setCommment(secondCommentContent);
            reportLocalEntity.getDiscussChildEntity().setRelayName(secondCommentReplyPeople);
            reportLocalEntity.getDiscussChildEntity().setName("我");
            mAdapter.getData().add(secondCommentLocalPosition + 1,reportLocalEntity);
            mAdapter.notifyItemInserted(secondCommentLocalPosition + 1);
            reportLocalEntity = null;
            secondCommentContent= "";
            mReplyCommentText.setText("");
        } else {
            mReplyCommentText.setText(mRealContent);
            ToastUtils.showCenterToast(getContext(), "回复失败");
        }
    }

    @Override
    public void discussFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), "回复失败");
    }

    @Override
    public void discussFirstSuccess(SignBean info) {
        String discussTemp = info.getMsgtype();
        int position = getCurrentPosition();
        if ("1".equals(discussTemp)) {
            ToastUtils.showCenterToast(getContext(), "评论成功");
            startPage = 1;
            getCommentData();
        } else {
            ToastUtils.showCenterToast(getContext(), "评论失败");
        }
    }

    @Override
    public void discussFirstFailed(String errorMsg) {
        ToastUtils.showCenterToast(getContext(), errorMsg);
    }
    /**
     * 分享url提交到到小红书成功
     * */
    @Override
    public void submitShareUrlSuccess(SignBean info) {
        if (info!= null){
            if ("1".equals(info.getMsgtype())){
                ToastUtils.showCenterShortToast(mContext,"分享保存成功");
            }else if ("2".equals(info.getMsgtype())){
                ToastUtils.showCenterShortToast(mContext,"分享保存失败");
            }
        }
    }
    /**
     * 分享url提交到到小红书失败
     * */
    @Override
    public void submitShareUrlFailed(String errorMsg) {
        ToastUtils.showCenterShortToast(mContext,"获取积分失败");
    }

    private int mSize;

    private boolean isOvered() {
        if (mSize < page_size) {
            return true;//已经结束
        } else {
            return false;
        }
    }

    @Override
    public void onPause() {
        if (mGsyVideoPlayer != null && mGsyVideoPlayer.getVisibility() == View.VISIBLE) {
            mGsyVideoPlayer.onVideoPause();
        }
        super.onPause();

    }

    @Override
    public void onResume() {
        if (mGsyVideoPlayer != null && mGsyVideoPlayer.getVisibility() == View.VISIBLE) {
            mGsyVideoPlayer.onVideoResume();
        }
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        if (mGsyVideoPlayer != null && mGsyVideoPlayer.getVisibility() == View.VISIBLE) {
            mGsyVideoPlayer.release();
        }
        super.onDestroyView();
    }
    /**
     * 分享接口回调
     * */
    @Override
    public void shareSuccessCallBack(String thingsId, String type, String mShareType, String time, String originalUserId, String isCopy,String realUrl) {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("userId",DbHelper.getUserId(mContext));
        map.put("relevantId",thingsId);
        map.put("type",type);
        map.put("shareSource","1");
        map.put("sharePurpose",mShareType);
        map.put("shareDateTime",time);
        map.put("originalUserId",originalUserId);
        map.put("isCopy",isCopy);
        map.put("allUrl",realUrl);
        mPresenter.submitShareUrl(map);
    }


}
