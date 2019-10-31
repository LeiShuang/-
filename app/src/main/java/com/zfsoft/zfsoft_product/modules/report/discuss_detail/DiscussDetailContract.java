package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.BaseListView;
import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.DiscussDetailFirstEntity;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.TestreportreplyListBean;

/**
 * 创建日期：2019/1/28 on 11:31
 * 描述:
 * 作者:Ls
 */
public interface DiscussDetailContract {
    interface  View extends BaseListView<TestreportreplyListBean>{


    }
    interface DiscussView extends BaseView{
        void submitSecondDiscussSuccess(SignBean info);
        void submitSecondDiscussFailed(String msg);
        void getFirstDiscussSuccess(DiscussDetailFirstEntity info );
        void getFirstDiscussFailed(String msg);

        void starDiscussSuccess(SignBean info);
        void starDiscussFailed(String errorMsg);
    }

    interface Presenter extends BasePresenter<View>{
        void getChildDiscussDetail(String hsk,int fatherCommentId,int page,int size);


    }

    interface discussPresenter extends BasePresenter<DiscussView>{
        void submitSecond(String hsk,int reportId,String userId,String content,int fatherDiscussId);

        void getFIrstDiscuss(String hsk,String userId,String commentId);

        void likeDiscuss(String hsk,int reportId,String userId,int type);
    }


}
