package com.zfsoft.zfsoft_product.modules.home.notice;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.NoticeBean;

/**
 * Created by ckw
 * on 2019/4/12.
 */
public class NoticeContract {

    public interface View extends BaseView{
        void getNoticeDataSuccess(NoticeBean noticeBean);
        void getNoticeDataFailure(String errorMsg);
    }

    public interface Presenter extends BasePresenter<View>{
        void getNoticeData(String hsk,String noticeId);
    }
}
