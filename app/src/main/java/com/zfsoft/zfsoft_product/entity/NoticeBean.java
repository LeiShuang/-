package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/4/12.
 */
public class NoticeBean {
    /*
    * "noticeId":3,
        "noticeTitle":"通知公告",
        "noticeContent":"小兔子准备与你们见面啦٩(๑❛ᴗ❛๑)۶",
        "noticeTime":1554972940000
    *
    * */

    private String noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }
}
