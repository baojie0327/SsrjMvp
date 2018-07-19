package com.jackson.ssrjmvp.bean; /**
 * AB  2018-07-17
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 17
 */
public class AB {



    private int code;
    private String msg;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * name : 轮播图
         * type : 1
         * items : [{"bigImg":"","img":"//img30.360buyimg.com/img/jfs/t22237/97/1730180168/109513/d9384b3c/5b3463acNe27fd6f8.jpg","url":"openapp.jdmedicine://yiyaoapp.jd.com/search?q=北京佳康","title":null,"sort":0},{"bigImg":"","img":"//img30.360buyimg.com/img/jfs/t23689/363/685821911/60129/b64983eb/5b3c30b8N8dd9b2de.png","url":"openapp.jdmedicine://yiyaoapp.jd.com/search?q=北京佳康","title":null,"sort":0},{"bigImg":"","img":"//img30.360buyimg.com/img/jfs/t20449/339/1306018294/81933/c54273ef/5b23e545N36e04ceb.jpg","url":"openapp.jdmedicine://yiyaoapp.jd.com/couponCenter","title":null,"sort":0}]
         */

        private Object id;
        private String name;
        private int type;
        private List<ItemsBean> items;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * bigImg :
             * img : //img30.360buyimg.com/img/jfs/t22237/97/1730180168/109513/d9384b3c/5b3463acNe27fd6f8.jpg
             * url : openapp.jdmedicine://yiyaoapp.jd.com/search?q=北京佳康
             * title : null
             * sort : 0
             */

            private String bigImg;
            private String img;
            private String url;
            private Object title;
            private int sort;

            public String getBigImg() {
                return bigImg;
            }

            public void setBigImg(String bigImg) {
                this.bigImg = bigImg;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}

