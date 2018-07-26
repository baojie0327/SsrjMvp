package com.jackson.ssrjmvp.bean; /**
 * HomeBean  2018-07-17
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import java.io.Serializable;
import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 17
 */
public class HomeBean extends BaseBean<HomeBean.DataBean>{



    public static class DataBean implements Serializable{
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
            private String title;
            private int sort;
            private String id;
            private String name;
            private String type;
            private ItemBean item;


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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public ItemBean getItem() {
                return item;
            }

            public void setItem(ItemBean item) {
                this.item = item;
            }

            public static class ItemBean{
                private String img;
                private String type;
                private String content;
                private String skuId;
                private String shopId;
                private String factoryName;
                private String mainImg;
                private String shopName;
                private String skuName;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public String getShopId() {
                    return shopId;
                }

                public void setShopId(String shopId) {
                    this.shopId = shopId;
                }

                public String getFactoryName() {
                    return factoryName;
                }

                public void setFactoryName(String factoryName) {
                    this.factoryName = factoryName;
                }

                public String getMainImg() {
                    return mainImg;
                }

                public void setMainImg(String mainImg) {
                    this.mainImg = mainImg;
                }

                public String getShopName() {
                    return shopName;
                }

                public void setShopName(String shopName) {
                    this.shopName = shopName;
                }

                public String getSkuName() {
                    return skuName;
                }

                public void setSkuName(String skuName) {
                    this.skuName = skuName;
                }
            }




        }
    }

}

