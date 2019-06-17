package com.jackson.ssrjmvp.bean; /**
 *  UserLoginBean 2017-10-09
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

/**
 * 用户登录实体类
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 09
 */
public class UserLoginBean extends BaseBean{
    private String userAlias;
    private String memberID;
    private String memberCode;
    private String userType;
    private String isShared;

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsShared() {
        return isShared;
    }

    public void setIsShared(String isShared) {
        this.isShared = isShared;
    }
}

