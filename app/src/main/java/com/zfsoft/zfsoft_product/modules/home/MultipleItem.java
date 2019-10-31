package com.zfsoft.zfsoft_product.modules.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ckw
 * on 2019/1/15.
 */
public class MultipleItem implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG_SIX = 2;
    public static final int IMG_SCROLL = 3;
    public static final int IMG_SINGLE = 4;

    public static final int TEXT_SPAN_SIZE = 6;
    public static final int IMG_SIX_SPAN_SIZE = 2;
    public static final int IMG_SCROLL_SPAN_SIZE = 6;
    public static final int IMG_SINGLE_SPAN_SIZE = 6;

    private int itemType;
    private int spanSize;

    private TitleEntity titleEntity;
    private SixImgEntity sixImgEntity;
    private ScrollImgListEntity scrollImgListEntity;
    private SingleImageList singleImageList;
//    private SingleImgEntity singleImgEntity;


    public SingleImageList getSingleImageList() {
        return singleImageList;
    }

    public void setSingleImageList(SingleImageList singleImageList) {
        this.singleImageList = singleImageList;
    }

    public TitleEntity getTitleEntity() {
        return titleEntity;
    }

    public SixImgEntity getSixImgEntity() {
        return sixImgEntity;
    }

    public ScrollImgListEntity getScrollImgListEntity() {
        return scrollImgListEntity;
    }

//    public SingleImgEntity getSingleImgEntity() {
//        return singleImgEntity;
//    }

    public MultipleItem(int itemType, int spanSize, TitleEntity titleEntity) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.titleEntity = titleEntity;
    }
    public MultipleItem(int itemType, int spanSize,SixImgEntity sixImgEntity) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.sixImgEntity = sixImgEntity;
    }

    public MultipleItem(int itemType, int spanSize,ScrollImgListEntity scrollImgListEntity) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.scrollImgListEntity = scrollImgListEntity;
    }

//    public MultipleItem(int itemType, int spanSize,SingleImgEntity singleImgEntity) {
//        this.itemType = itemType;
//        this.spanSize = spanSize;
//        this.singleImgEntity = singleImgEntity;
//    }

    public MultipleItem(int itemType, int spanSize,SingleImageList singleImageList) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.singleImageList = singleImageList;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    static class SingleImageList{
        private List<SingleImgEntity> list;

        public List<SingleImgEntity> getList() {
            return list;
        }

        public void setList(List<SingleImgEntity> list) {
            this.list = list;
        }
    }

    /*
    * 标题 实体类
    * */
    static class TitleEntity implements Serializable{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    /*
    * 六张图片实体类
    * */
        static class SixImgEntity implements Serializable{
            private String price;
            private String imgUrl;
            private String state;

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

//    }


    /*
    * 滚动图片的实体类
    * */
    static class ScrollImgListEntity implements Serializable{

        private List<ScrollImgEntity> list;

        public List<ScrollImgEntity> getList() {
            return list;
        }

        public void setList(List<ScrollImgEntity> list) {
            this.list = list;
        }

        static class ScrollImgEntity implements Serializable {
            private String talented;
            private String headUrl;
            private String name;
            private String fansNum;
            private String reportNum;
            private String type;

            public String getTalented() {
                return talented;
            }

            public void setTalented(String talented) {
                this.talented = talented;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFansNum() {
                return fansNum;
            }

            public void setFansNum(String fansNum) {
                this.fansNum = fansNum;
            }

            public String getReportNum() {
                return reportNum;
            }

            public void setReportNum(String reportNum) {
                this.reportNum = reportNum;
            }
        }
    }


    /*
    * 单个的图片实体类
    * */
    static class SingleImgEntity implements Serializable{
        private String headUrl;//上部的图片
        private String userUrl;//用户图片
        private String name;
        private String thumbUpNum;
        private String content;
        private String height;
        private String width;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumbUpNum() {
            return thumbUpNum;
        }

        public void setThumbUpNum(String thumbUpNum) {
            this.thumbUpNum = thumbUpNum;
        }
    }

}
