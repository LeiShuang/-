package com.zfsoft.zfsoft_product.modules.report.submit_report;

import com.zfsoft.zfsoft_product.base.BasePresenter;
import com.zfsoft.zfsoft_product.base.BaseView;
import com.zfsoft.zfsoft_product.entity.SignBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建日期：2019/1/30 on 15:49
 * 描述:发布报告
 * 作者:Ls
 */
public interface SubmitReportContract {
    interface View extends BaseView{
        void submitPicSuccess(SignBean info);
        void submitPicsFailed(String errorMsg);

        void submitPics();
    }

    interface Presenter extends BasePresenter<View>{
        /**
         * 提交照片,视频
         *
         */
        void submitReportPics(Map<String, RequestBody> map, List<MultipartBody.Part> images);
    }
}