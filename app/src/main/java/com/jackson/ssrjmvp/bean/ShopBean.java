package com.jackson.ssrjmvp.bean; /**
 * ShopBean  2018-02-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import java.io.Serializable;
import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 06
 */
public class ShopBean extends BaseBean<ShopBean.ShopDetails>{

    private List<ShopDetails> merchantList;
    private List<HuiShopDetails> huiMerchantList;

    public List<ShopDetails> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<ShopDetails> merchantList) {
        this.merchantList = merchantList;
    }

    public List<HuiShopDetails> getHuiMerchantList() {
        return huiMerchantList;
    }

    public void setHuiMerchantList(List<HuiShopDetails> huiMerchantList) {
        this.huiMerchantList = huiMerchantList;
    }

    public class HuiShopDetails implements Serializable {
        private String merId;
        private String merName;
        private String merPhoto;
        private String industryName;
        private String discount;

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getMerId() {
            return merId;
        }

        public void setMerId(String merId) {
            this.merId = merId;
        }

        public String getMerName() {
            return merName;
        }

        public void setMerName(String merName) {
            this.merName = merName;
        }

        public String getMerPhoto() {
            return merPhoto;
        }

        public void setMerPhoto(String merPhoto) {
            this.merPhoto = merPhoto;
        }
    }

    public class ShopDetails implements Serializable {
        private String merchantID;
        private String merchantName;
        private String merchantPhoto;
        private String landmark;
        private String latitude;
        private String longitude;
        private String distance;
        private int couponFlag;
        private boolean isMember;
        private String discountMerFlag;
        private String discount;
        //changce by 田俊杰 2016.03.28
        private String actID;// 活动id
        private String actMsg;//活动名称
        private String actName;//活动内容，
        private String sdate;//活动开始日期，
        private String edate;//活动结束日期，
        //     private String memRules;


        private String headImg;
        private String landMark;
        private boolean joined;
        private List<String> mRules;

        private String isDiscountMer;
        private String salesCount;

        private String isOpenTakeout; //外卖

        private String industryName;//行业类型
        private String percapitaConsume;//人均消费

        private String commentCount;//评论数量
        private String goodComment;//好评度
        private String merInfo;//商户详情



        public String getMerInfo() {
            return merInfo;
        }

        public void setMerInfo(String merInfo) {
            this.merInfo = merInfo;
        }

        public String getGoodComment() {
            return goodComment;
        }

        public void setGoodComment(String goodComment) {
            this.goodComment = goodComment;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getPercapitaConsume() {
            return percapitaConsume;
        }

        public void setPercapitaConsume(String percapitaConsume) {
            this.percapitaConsume = percapitaConsume;
        }

        public ShopDetails(){

        }

        public ShopDetails(String latitude,String longitude){
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public void setMember(boolean member) {
            isMember = member;
        }

        public List<String> getmRules() {
            return mRules;
        }

        public void setmRules(List<String> mRules) {
            this.mRules = mRules;
        }

        public String getIsDiscountMer() {
            return isDiscountMer;
        }

        public void setIsDiscountMer(String discountMer) {
            this.isDiscountMer = discountMer;
        }

        public String getSalesCount() {
            return salesCount;
        }

        public void setSalesCount(String salesCount) {
            this.salesCount = salesCount;
        }

        public String getMerchantID() {
            return merchantID;
        }

        public void setMerchantID(String merchantID) {
            this.merchantID = merchantID;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantPhoto() {
            return merchantPhoto;
        }

        public void setMerchantPhoto(String merchantPhoto) {
            this.merchantPhoto = merchantPhoto;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getCouponFlag() {
            return couponFlag;
        }

        public void setCouponFlag(int couponFlag) {
            this.couponFlag = couponFlag;
        }

        public boolean isMember() {
            return isMember;
        }

        public void setIsMember(boolean isMember) {
            this.isMember = isMember;
        }

        public String getDiscountMerFlag() {
            return discountMerFlag;
        }

        public void setDiscountMerFlag(String discountMerFlag) {
            this.discountMerFlag = discountMerFlag;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getLandMark() {
            return landMark;
        }

        public void setLandMark(String landMark) {
            this.landMark = landMark;
        }

        public boolean isJoined() {
            return joined;
        }

        public void setJoined(boolean joined) {
            this.joined = joined;
        }

        public List<String> getMemRules() {
            return mRules;
        }

        public void setMemRules(List<String> memRules) {
            this.mRules = memRules;
        }

        public String getActID() {
            return actID;
        }

        public void setActID(String actID) {
            this.actID = actID;
        }

        public String getActMsg() {
            return actMsg;
        }

        public void setActMsg(String actMsg) {
            this.actMsg = actMsg;
        }

        public String getActName() {
            return actName;
        }

        public void setActName(String actName) {
            this.actName = actName;
        }

        public String getSdate() {
            return sdate;
        }

        public void setSdate(String sdate) {
            this.sdate = sdate;
        }

        public String getEdate() {
            return edate;
        }

        public void setEdate(String edate) {
            this.edate = edate;
        }

        public String getIsOpenTakeout() {
            return isOpenTakeout;
        }

        public void setIsOpenTakeout(String isOpenTakeout) {
            this.isOpenTakeout = isOpenTakeout;
        }




    }

}

