package com.zfsoft.zfsoft_product.modules.report.report_detail;

import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.CommentsBean;
import com.zfsoft.zfsoft_product.entity.ReportInfo;
import com.zfsoft.zfsoft_product.entity.SignBean;

import java.util.Map;

/**
 * 创建日期：2019/1/24 on 16:02
 * 描述:
 * 作者:Ls
 */
public class ReportDetailContract {
    //头布局
    interface View extends BaseView {
        void showReplyDialog(int fatherDiscussId,String replyName,int position);
        void showCommentInput(int position);
        void getReportHeaderSuccess(ReportInfo info);
        void getReportHeaderFailed(String errorMsg);


        void getCommentsSuccess(CommentsBean info);
        void getCommentsFailed(String errorMsg);

        void collectSuccess(SignBean info);
        void collectFailed(String errorMsg);

        void attentionSuccess(SignBean info);
        void attentionFailed(String errorMsg);

        void likeReportSuccess(SignBean info);
        void likeReportFailed(String errorMsg);

        void starDiscussSuccess(SignBean info);
        void starDiscussFailed(String errorMsg);

        void dicussSuccess(SignBean info);
        void discussFailed(String errorMsg);

        void discussFirstSuccess(SignBean info);
        void discussFirstFailed(String errorMsg);

        void submitShareUrlSuccess(SignBean info);
        void submitShareUrlFailed(String errorMsg);
    }


    interface Presenter extends BasePresenter<View>{
        void getReportInfo(String hsk,int productId,String userId);
        void collectReport(String hsk,int  reportId,String userId,int type);
        void getCommentsList(String hsk,int reportId,int page,int size,String userId);
        void likeReport(String hsk,int reportId,String userId);
        void attentionReporter(String hsk,String userId,String otherId,String type);
        void likeDiscuss(String hsk,int reportId,String userId,int type);
        void discussfirstComment(String hsk,int reportId,String userId,String content);
        void dicussComment(String hsk,int reportId,String userId,String content,int fatherDiscussId);
        void submitShareUrl(Map<String, String> params);
    }
}
