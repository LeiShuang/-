package com.zfsoft.zfsoft_product.modules.report.report_detail;

import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.CommentsBean;
import com.zfsoft.zfsoft_product.entity.ProductInfo;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.CallBackListener;
import com.zfsoft.zfsoft_product.net.HttpManager;
import com.zfsoft.zfsoft_product.net.Response;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 创建日期：2019/1/24 on 16:02
 * 描述:报告详情Presenter
 * 作者:Ls
 */
public class ReportDetailPresenter implements ReportDetailContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private HttpManager mHttpManager;
    private ReportDetailContract.View mView;
    private ApiService mApiService;

    @Inject
    public ReportDetailPresenter(HttpManager httpManager, ApiService apiService) {
        mHttpManager = httpManager;
        mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    /**
     * 获取报告头信息
     */
    @Override
    public void getReportInfo(String hsk, int productId, String userId) {
        mHttpManager.request(mApiService.getTestReportDetail(hsk, productId, userId), mCompositeDisposable, mView, new CallBackListener<ReportInfo>() {

            @Override
            public void onSuccess(ReportInfo data) {
                mView.getReportHeaderSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.getReportHeaderFailed(errorMsg);
            }
        });
    }

    /**
     * 收藏试用报告
     */
    @Override
    public void collectReport(String hsk, int reportId, String userId, int type) {
        mHttpManager.request(mApiService.getCollectTestReport(hsk, reportId, userId, type), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.collectSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.collectFailed(errorMsg);
            }
        });
    }

    /**
     * 获取评论列表
     */
    @Override
    public void getCommentsList(String hsk, int reportId, int page, int size,String userId) {
        mHttpManager.request(mApiService.getTestReportComments(hsk, reportId, page, size,userId), mCompositeDisposable, mView, new CallBackListener<CommentsBean>() {

            @Override
            public void onSuccess(CommentsBean data) {
                mView.getCommentsSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.getCommentsFailed(errorMsg);
            }
        });
    }

    /**
     * 点赞试用报告
     */
    @Override
    public void likeReport(String hsk, int reportId, String userId) {
        mHttpManager.request(mApiService.getLikeTestReport(hsk, reportId, userId), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.likeReportSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.likeReportFailed(errorMsg);
            }
        });
    }

    @Override
    public void attentionReporter(String hsk, String userId, String otherId, String type) {
        mHttpManager.request(mApiService.attentionPerson(hsk, userId, otherId, type), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.attentionSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.attentionFailed(errorMsg);
            }
        });
    }

    /**
     * 点赞评论
     */
    @Override
    public void likeDiscuss(String hsk, int reportId, String userId, int type) {
        mHttpManager.request(mApiService.addUserLike(hsk, reportId, userId, type), mCompositeDisposable, mView, new CallBackListener<SignBean>() {

            @Override
            public void onSuccess(SignBean data) {
                mView.starDiscussSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.starDiscussFailed(errorMsg);
            }
        });
    }
    /**
     * 一级评论请求
     * */
    @Override
    public void discussfirstComment(String hsk, int reportId, String userId, String content) {
        mHttpManager.request(mApiService.saveTestReportComment(hsk,reportId,userId,content),mCompositeDisposable,mView,new CallBackListener<SignBean>(){

            @Override
            public void onSuccess(SignBean data) {
                mView.discussFirstSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.discussFirstFailed(errorMsg);
            }
        });
    }

    /**
     * 二级评论请求
     * */
    @Override
    public void dicussComment(String hsk, int reportId, String userId,String content,int fatherDiscussId) {
        mHttpManager.request(mApiService.saveSecondReportComment(hsk,reportId,userId,content,fatherDiscussId),mCompositeDisposable,mView,new CallBackListener<SignBean>(){

            @Override
            public void onSuccess(SignBean data) {
                mView.dicussSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.discussFailed(errorMsg);
            }
        });
    }

    @Override
    public void submitShareUrl(Map<String, String> params) {
        mHttpManager.request(mApiService.submitUrl(params),mCompositeDisposable,mView,new CallBackListener<SignBean>(){

            @Override
            public void onSuccess(SignBean data) {
                mView.submitShareUrlSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.submitShareUrlFailed(errorMsg);
            }
        });
    }


    /**
     * 关注报告发布人
     */

    @Override
    public void takeView(ReportDetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
