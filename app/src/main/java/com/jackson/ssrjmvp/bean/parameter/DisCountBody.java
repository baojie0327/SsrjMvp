package com.jackson.ssrjmvp.bean.parameter; /**
 * DisCountBody  2018-02-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 06
 */
public class DisCountBody {


    /**
     * page : 0
     * rowsPerPage : 10
     * city : 北京
     * memberID :
     * nameOrLandMark :
     * merchantType :
     * latitude : 39.933080
     * longitude : 116.515896
     */

    private int page;
    private int rowsPerPage;
    private String city;
    private String memberID;
    private String nameOrLandMark;
    private String merchantType;
    private String latitude;
    private String longitude;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getNameOrLandMark() {
        return nameOrLandMark;
    }

    public void setNameOrLandMark(String nameOrLandMark) {
        this.nameOrLandMark = nameOrLandMark;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
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
}

